import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bun;
    @Mock
    private Ingredient ingredient;
    @Mock
    private Ingredient sauce;
    @Mock
    private Ingredient filling;

    /**
     * Создание экземпляра бургера
     */
    @Before
    public void createBurger() {
        burger = new Burger();
    }

    /**
     * Проверка добавления булочки в бургер
     */
    @Test
    public void checkSetBun() {
        burger.setBuns(bun);

        Bun actualBun = burger.bun;
        assertEquals("Неправильный выбор булочки", bun, actualBun);
    }

    /**
     * Проверка добавления ингредиентов в бургер
     */
    @Test
    public void checkAddIngredient() {
        burger.addIngredient(ingredient);

        List<Ingredient> actualIngredient = burger.ingredients;
        List<Ingredient> expectedIngredient = List.of(ingredient);
        assertEquals("Неверный ингредиент", expectedIngredient, actualIngredient);
    }

    /**
     * Проверка удаления ингредиентов в бургере
     */
    @Test
    public void checkRemoveIngredient() {
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);

        List<Ingredient> actualIngredient = burger.ingredients;
        List<Ingredient> expectedIngredient = List.of();
        assertEquals("Удаление не произошло", expectedIngredient, actualIngredient);
    }

    /**
     * Проверка перемещения позиций ингредиентов в бургере
     */
    @Test
    public void checkMoveIngredient() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        burger.moveIngredient(0,1);

        Ingredient actualIngredient = burger.ingredients.get(1);
        Ingredient expectedIngredient = sauce;
        assertEquals("Неверное изменение позиции ингредиента", expectedIngredient, actualIngredient);
    }

    /**
     * Проверка расчета цены бургера
     */
    @Test
    public void checkBurgerGetPrice() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.getPrice();

        Mockito.verify(bun).getPrice();
        Mockito.verify(ingredient).getPrice();
    }

    /**
     * Проверка получения рецепта бургера
     */
    @Test
    public void checkGetReceipt() {
        Mockito.when(bun.getName()).thenReturn("original");
        Mockito.when(bun.getPrice()).thenReturn(100f);
        burger.setBuns(bun);

        Mockito.when(ingredient.getType()).thenReturn(SAUCE);
        Mockito.when(ingredient.getName()).thenReturn("tar-tar");
        Mockito.when(ingredient.getPrice()).thenReturn(49f);

        Mockito.when(ingredient.getType()).thenReturn(FILLING);
        Mockito.when(ingredient.getName()).thenReturn("angus");
        Mockito.when(ingredient.getPrice()).thenReturn(149f);
        burger.addIngredient(ingredient);

        StringBuilder receipt = new StringBuilder(String.format("(==== %s ====)%n", bun.getName()));
        List<Ingredient> ingredients = burger.ingredients;

        for (Ingredient ingredient : ingredients) {
            receipt.append(String.format("= %s %s =%n", this.ingredient.getType().name().toLowerCase(), ingredient.getName()));
        }

        receipt.append(String.format("(==== %s ====)%n", bun.getName()));
        receipt.append(String.format("%nPrice: %f%n", burger.getPrice()));

        String expectedReceipt = receipt.toString();
        String actualReceipt = burger.getReceipt();

        assertEquals("Неверный рецепт", expectedReceipt, actualReceipt);
    }
}