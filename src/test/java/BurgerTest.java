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
    private Bun bunMock;
    @Mock
    private Ingredient ingredientMock;
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
    public void setBunTest() {
        burger.setBuns(bunMock);

        Bun actualBun = burger.bun;
        assertEquals("Неправильный выбор булочки", bunMock, actualBun);
    }

    /**
     * Проверка добавления ингредиентов в бургер
     */
    @Test
    public void addIngredientTest() {
        burger.addIngredient(ingredientMock);

        List<Ingredient> actualIngredient = burger.ingredients;
        List<Ingredient> expectedIngredient = List.of(ingredientMock);
        assertEquals("Неверный ингредиент", expectedIngredient, actualIngredient);
    }

    /**
     * Проверка удаления ингредиентов в бургере
     */
    @Test
    public void removeIngredientTest() {
        burger.addIngredient(ingredientMock);
        burger.removeIngredient(0);

        List<Ingredient> actualIngredient = burger.ingredients;
        List<Ingredient> expectedIngredient = List.of();
        assertEquals("Удаление не произошло", expectedIngredient, actualIngredient);
    }

    /**
     * Проверка перемещения позиций ингредиентов в бургере
     */
    @Test
    public void moveIngredientTest() {
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
    public void getPriceTest() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);
        burger.getPrice();

        Mockito.verify(bunMock).getPrice();
        Mockito.verify(ingredientMock).getPrice();
    }

    /**
     * Проверка получения рецепта бургера
     */
    @Test
    public void getReceiptTest() {
        Mockito.when(bunMock.getName()).thenReturn("original");
        Mockito.when(bunMock.getPrice()).thenReturn(100f);
        burger.setBuns(bunMock);

        Mockito.when(ingredientMock.getType()).thenReturn(SAUCE);
        Mockito.when(ingredientMock.getName()).thenReturn("tar-tar");
        Mockito.when(ingredientMock.getPrice()).thenReturn(49f);

        Mockito.when(ingredientMock.getType()).thenReturn(FILLING);
        Mockito.when(ingredientMock.getName()).thenReturn("angus");
        Mockito.when(ingredientMock.getPrice()).thenReturn(149f);
        burger.addIngredient(ingredientMock);

        StringBuilder receipt = new StringBuilder(String.format("(==== %s ====)%n", bunMock.getName()));
        List<Ingredient> ingredients = burger.ingredients;

        for (Ingredient ingredient : ingredients) {
            receipt.append(String.format("= %s %s =%n", ingredientMock.getType().name().toLowerCase(), ingredient.getName()));
        }

        receipt.append(String.format("(==== %s ====)%n", bunMock.getName()));
        receipt.append(String.format("%nPrice: %f%n", burger.getPrice()));

        String expectedReceipt = receipt.toString();
        String actualReceipt = burger.getReceipt();

        assertEquals("Неверный рецепт", expectedReceipt, actualReceipt);
    }
}