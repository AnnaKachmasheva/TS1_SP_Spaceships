package cz.cvut.fel.ts1.sp.tests;

import cz.cvut.fel.ts1.sp.pages.MainPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdateShipTest extends TestCase {

    @ParameterizedTest
    @CsvFileSource(files = "csvs/ships_update.csv")
    void updateShip_true(String newName,
                         String newPlanet,
                         String newShipType,
                         String newProdYear,
                         String newIsNew,
                         String newSpeed,
                         String newCrewSize,
                         String newPrice,
                         String expectedName,
                         String expectedPlanet,
                         String expectedShipType,
                         String expectedProdYear,
                         String expectedIsNew,
                         String expectedSpeed,
                         String expectedCrewSize,
                         String expectedPrice,
                         String expectedRating) {
        MainPage main = new MainPage(getDriver());
        main.updateShip(newName, newPlanet, newShipType, newProdYear, newIsNew, newSpeed, newCrewSize, newPrice);
        List<String> shipAfter = main.getShipFirstRow();

        assertEquals(expectedName, shipAfter.get(0));
        assertEquals(expectedPlanet, shipAfter.get(1));
        assertEquals(expectedShipType, shipAfter.get(2));
        assertEquals(expectedProdYear, shipAfter.get(3));
        assertEquals(expectedIsNew, shipAfter.get(4));
        assertEquals(expectedSpeed, shipAfter.get(5));
        assertEquals(expectedCrewSize, shipAfter.get(6));
        assertEquals(expectedPrice, shipAfter.get(7));
        assertEquals(expectedRating, shipAfter.get(8));
    }
}
