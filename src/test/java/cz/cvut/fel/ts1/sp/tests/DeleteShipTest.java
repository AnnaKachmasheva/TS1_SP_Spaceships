package cz.cvut.fel.ts1.sp.tests;

import cz.cvut.fel.ts1.sp.pages.MainPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteShipTest extends TestCase {

    @BeforeAll
    static void resetData() {
        MainPage main = new MainPage(getDriver());
        while (main.getCountOrder() != 0) {
            main.deleteOrder();
        }
        main.deleteShip();
    }

    @Test
    void deleteShip_true() {
        MainPage main = new MainPage(getDriver());

        int expected = Integer.parseInt(main.getCountShips().substring(13)) - 1;

        main.deleteShip();

        int result = Integer.parseInt(main.getCountShips().substring(13));

        assertEquals(expected, result);
    }
}
