package cz.cvut.fel.ts1.sp.tests;

import cz.cvut.fel.ts1.sp.pages.MainPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaginationTest extends TestCase{


    @Test
    void checkPagination_true() {
        MainPage main = new MainPage(getDriver());

        int countShips = Integer.parseInt(main.getCountShips().substring(13));
        int countRowToBe = main.getCountLimitTableShip();
        int expected = (int) Math.ceil(countShips / countRowToBe);

        int result = main.getCountPagesTable();

        assertEquals(expected, result);
    }
}
