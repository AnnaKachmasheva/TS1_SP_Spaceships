package cz.cvut.fel.ts1.sp.tests;

import cz.cvut.fel.ts1.sp.pages.MainPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteOrderTest extends TestCase {

    @Test
    void deleteOrder_true() {
        MainPage main = new MainPage(getDriver());

        int expected = main.getCountOrder() != 0 ? main.getCountOrder() - 1 : main.getCountOrder();

        main.deleteOrder();

        int result = main.getCountOrder();

        assertEquals(expected, result);
    }
}