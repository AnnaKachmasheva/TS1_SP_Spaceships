package cz.cvut.fel.ts1.sp.tests;

import cz.cvut.fel.ts1.sp.pages.MainPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountRowsInTableShipsTest extends TestCase {

    @ParameterizedTest
    @ValueSource(strings = {"1", "3", "5", "10", "20"})
    void pagination_true(String counts) {
        MainPage main = new MainPage(getDriver());

        main.setCountShipInPage(counts);

        int countShips = Integer.parseInt(main.getCountShips().substring(13));

        int expected = Math.min(countShips, Integer.parseInt(counts));
        int result = countShips > Integer.parseInt(counts) ? main.getCountRowsTableShip() : countShips;

        assertEquals(expected, result);
    }
}