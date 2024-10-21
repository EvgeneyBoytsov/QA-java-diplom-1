import lombok.AllArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;
import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(Parameterized.class)
@AllArgsConstructor
public class IngredientWithParametrizedTest {

    private final IngredientType type;
    private final String name;
    private final float price;

    @Parameterized.Parameters(name = "{index} : type = {0}")
    public static Object[][] getIngredientData() {
        return new Object[][] {
                {SAUCE, "tar-tar", 50f},
                {FILLING, "chery", 25f},
        };
    }

    /**
     * Проверка типов ингредиента
     */
    @Test
    public void testIngredientType(){
        Ingredient ingredient = new Ingredient(type,name,price);
        IngredientType actualType = ingredient.getType();

        assertEquals("Неверный тип ингредиента",type,actualType);
    }
}