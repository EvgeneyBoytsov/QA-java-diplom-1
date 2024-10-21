import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;
import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(Parameterized.class)
public class BurgerWithParametrizedTest {

    private Burger burger;
    private Bun bun;

    private final IngredientType type;
    private final String name;
    private final float price;

    public BurgerWithParametrizedTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    /**
     * Создание экземпляров бургрера и булочки
     */
    @Before
    public void createNewInstance() {
        bun = new Bun("original", 100);
        burger = new Burger();
    }

    @Parameterized.Parameters(name = "{index} : type = {2}")
    public static Object[][] getIngredientData() {
        return new Object[][] {
                {SAUCE, "tar-tar", 50f},
                {FILLING, "chery", 25f},
        };
    }

    /**
     * Проверка расчета цены бургера с различными видами ингредиентов
     */
    @Test
    public void burgerGetPriceTest() {
        Ingredient ingredient = new Ingredient(type,name,price);
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        float expectedPrice = bun.price * 2 + ingredient.price;
        float actualPrice = burger.getPrice();

        float DELTA = 0f;
        assertEquals("Неверный расчет цены", expectedPrice, actualPrice, DELTA);
    }
}