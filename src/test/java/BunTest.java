import org.junit.Before;
import org.junit.Test;
import praktikum.Bun;
import static org.junit.Assert.assertEquals;

public class BunTest {
    private Bun bun;
    private final String EXPECTED_NAME = "Оригинальная";
    private final float EXPECTED_PRICE = 150f;

    /**
     * Создание экземпляра булочки
     */
    @Before
    public void createNewBun() {
        bun = new Bun(EXPECTED_NAME, EXPECTED_PRICE);
    }

    /**
     * Проверка получения названия булочки
     */
    @Test
    public void bunGetNameTest() {
        String actualName = bun.getName();
        assertEquals("Неверное название булочки", EXPECTED_NAME, actualName);
    }

    /**
     * Проверка получения цены булочки
     */
    @Test
    public void bunGetPriceTest() {
        float actualPrice = bun.getPrice();
        float DELTA = 0f;
        assertEquals("Неверная цена булочки", EXPECTED_PRICE, actualPrice, DELTA);
    }
}