package cz.cvut.fel.ts1.sp.tests;

import cz.cvut.fel.ts1.sp.pages.MainPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class SortAndFilterTests extends TestCase {

    @BeforeAll
    static void resetData() {
        MainPage main = new MainPage(getDriver());
        while (main.getCountOrder() != 0) {
            main.deleteOrder();
        }
        while (Integer.parseInt(main.getCountShips().substring(13)) != 0) {
            main.deleteShip();
        }

        main.clickCreateNewShip();
        main.fillFormCreateShip(
                "Orion III",
                "Mars",
                "Merchant",
                "2995",
                "true",
                "0.82",
                "617",
                "100.1");
        main.sendFormCreateShip();
        main.fillFormCreateShip(
                "Daedalus",
                "Jupiter",
                "Merchant",
                "3001",
                "true",
                "0.94",
                "1619",
                "10.1");
        main.sendFormCreateShip();
        main.fillFormCreateShip(
                "Eagle Transporter",
                "Earth",
                "Transport",
                "2989",
                "true",
                "0.79",
                "4527",
                "200.5");
        main.sendFormCreateShip();
        main.fillFormCreateShip(
                "F-302 Mongoose",
                "Neptune",
                "Military",
                "3011",
                "false",
                "0.64",
                "128",
                "20.2");
        main.sendFormCreateShip();
        main.fillFormCreateShip(
                "Explorer",
                "Saturn",
                "Merchant",
                "3007",
                "false",
                "0.69",
                "4495",
                "100.0");
        main.sendFormCreateShip();
        main.fillFormCreateShip(
                "Icarus I",
                "Mercury",
                "Transport",
                "2999",
                "false",
                "0.07",
                "826",
                "700.5");
        main.sendFormCreateShip();
        main.fillFormCreateShip(
                "Hermes",
                "Venus",
                "Merchant",
                "3010",
                "false",
                "0.05",
                "445",
                "10.2");
        main.sendFormCreateShip();
        main.fillFormCreateShip(
                "Odyssey",
                "Neptune",
                "Transport",
                "2988",
                "false",
                "0.44",
                "1436",
                "30.5");
        main.sendFormCreateShip();
        main.fillFormCreateShip(
                "Orbit Jet",
                "Venus",
                "Transport",
                "3011",
                "false",
                "0.55",
                "1931",
                "290.5");
        main.sendFormCreateShip();
        main.fillFormCreateShip("Aries Ib",
                "Saturn",
                "Military",
                "2999",
                "false",
                "0.07",
                "826",
                "700.5");
        main.sendFormCreateShip();
    }

    @Test
    void sortShip_setSortById_sortedShipsById() {
        MainPage main = new MainPage(getDriver());
        main.setCountShipInPage("10");

        List<Long> expected = getNumberLong(main.getAllIdsShip());

        main.setFilter("Id");

        List<Long> result = getNumberLong(main.getAllIdsShip());

        assertEquals(expected, result);
    }

    @Test
    void sortShip_setSortBySpeed_sortedShipsBySpeed() {
        MainPage main = new MainPage(getDriver());
        main.setCountShipInPage("10");

        List<Double> expected = getNumberDouble(main.getAllSpeedShip());

        main.setFilter("Speed");

        List<Double> result = getNumberDouble(main.getAllSpeedShip());

        assertEquals(expected, result);
    }

    @Test
    void sortShip_setSortByProdYear_sortedShipsByProdYear() {
        MainPage main = new MainPage(getDriver());
        main.setCountShipInPage("10");

        List<Long> expected = getNumberLong(main.getAllProdDates());

        main.setFilter("Prod year");

        List<Long> result = getNumberLong(main.getAllProdDates());

        assertEquals(expected, result);
    }

    @Test
    void sortShip_setSortByRating_sortedShipsByRating() {
        MainPage main = new MainPage(getDriver());
        main.setCountShipInPage("10");

        List<Double> expected = getNumberDouble(main.getAllRating());

        main.setFilter("Rating");

        List<Double> result = getNumberDouble(main.getAllRating());

        assertEquals(expected, result);
    }

    private List<Long> getNumberLong(List<String> list) {
        List<Long> numbers = new ArrayList<>();
        for (String s : list)
            numbers.add(Long.parseLong(s));
        Collections.sort(numbers);
        return numbers;
    }

    private List<Double> getNumberDouble(List<String> list) {
        List<Double> numbers = new ArrayList<>();
        for (String s : list)
            numbers.add(Double.parseDouble(s));
        Collections.sort(numbers);
        return numbers;
    }

    @ParameterizedTest
    @CsvFileSource(files = "csvs/ship_filter.csv")
    void filterShips_setFilters_filteredShips(String name,
                                              String planet,
                                              String shipType,
                                              String after,
                                              String before,
                                              String isUsed,
                                              String minSpeed,
                                              String maxSpeed,
                                              String minCrewSize,
                                              String maxCrewSize,
                                              String minRating,
                                              String maxRating,
                                              String minPrice,
                                              String maxPrice,
                                              String expectedCount) {
        MainPage main = new MainPage(getDriver());
        main.filterShips(name, planet, shipType, after, before, isUsed, minSpeed, maxSpeed, minCrewSize,
                maxCrewSize, minRating, maxRating, minPrice, maxPrice);

        int result = Integer.parseInt(main.getCountShips().substring(13));

        assertEquals(Integer.parseInt(expectedCount), result);
    }
}