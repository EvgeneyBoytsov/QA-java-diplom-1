import org.junit.Before;
import org.junit.Test;
import praktikum.Ingredient;
import praktikum.IngredientType;
import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.FILLING;

public class IngredientTest {

    private Ingredient ingredient;

    private final String EXPECTED_NAME = "angus";
    private final float EXPECTED_PRICE = 500f;
    private final IngredientType EXPECTED_TYPE = FILLING;

    /**
     * Создание экземпляра ингредиента
     */
    @Before
    public void createNewIngredient() {
        ingredient = new Ingredient(EXPECTED_TYPE, EXPECTED_NAME, EXPECTED_PRICE);
    }

    /**
     * Проверка названия ингредиента
     */
    @Test
    public void checkIngredientGetName() {
        String actualName = ingredient.getName();
        assertEquals("Неверное название ингредиента", EXPECTED_NAME, actualName);
    }

    /**
     * Проверка цены ингредиента
     */
    @Test
    public void checkIngredientGetPrice() {
        float actualPrice = ingredient.getPrice();
        float DELTA = 0f;
        assertEquals("Неверная цена ингредиента", EXPECTED_PRICE, actualPrice, DELTA);
    }
}