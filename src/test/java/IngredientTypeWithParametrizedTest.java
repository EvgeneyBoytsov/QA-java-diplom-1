import lombok.AllArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.IngredientType;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
@AllArgsConstructor
public class IngredientTypeWithParametrizedTest {

    private final IngredientType type;
    private final String name;

    @Parameterized.Parameters(name = "{index} : type = {0}")
    public static Object[][] getIngredientData() {
        return new Object[][] {
                {IngredientType.SAUCE, "SAUCE"},
                {IngredientType.FILLING, "FILLING"},
        };
    }

    /**
     * Проверка типов ингредиента
     */
    @Test
    public void checkIngredientTypeTestWithParameterized() {
        assertEquals("Неверный тип ингредиента", type.name(), name);
    }

    @Test
    public void checkIngredientTypeTest() {
        assertEquals("Неверный тип ингредиента", IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
    }
}