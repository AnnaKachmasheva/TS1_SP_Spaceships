package cz.cvut.fel.ts1.sp.tests;

import cz.cvut.fel.ts1.sp.pages.MainPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateOrderTest extends TestCase {

    @ParameterizedTest
    @CsvFileSource(files = "csvs/order_create.csv")
    void createOrder_true(String distance, String changeResult) {
        MainPage main = new MainPage(getDriver());

        int expected = main.getCountOrder() + (main.isUsed() ? 0 : Integer.parseInt(changeResult));

        main.createOrder(distance);

        int result = main.getCountOrder();

        assertEquals(expected, result);
    }
}
