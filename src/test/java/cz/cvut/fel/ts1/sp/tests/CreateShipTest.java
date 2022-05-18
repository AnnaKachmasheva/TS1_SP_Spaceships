package cz.cvut.fel.ts1.sp.tests;

import cz.cvut.fel.ts1.sp.pages.MainPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateShipTest extends TestCase {

    @ParameterizedTest
    @CsvFileSource(files = "csvs/ships_create.csv")
    void createShip_true(String name,
                         String planet,
                         String shipType,
                         String prodYear,
                         String isNew,
                         String speed,
                         String crewSize,
                         String price,
                         String changeCount) {
        MainPage main = new MainPage(getDriver());


        int expected = Integer.parseInt(main.getCountShips().substring(13)) + Integer.parseInt(changeCount);

        main.clickCreateNewShip();
        main.fillFormCreateShip(name, planet, shipType, prodYear, isNew, speed, crewSize, price);
        main.sendFormCreateShip();

        int result = Integer.parseInt(main.getCountShips().substring(13));

        assertEquals(expected, result);
    }
}