package cz.cvut.fel.ts1.sp.tests;

import cz.cvut.fel.ts1.sp.pages.MainPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdateOrderTest extends TestCase {

    @ParameterizedTest
    @CsvFileSource(files = "csvs/order_update.csv")
    void updateShip_true(String newState, String newDistance, String expectedState,String expectedDistance){
        MainPage main = new MainPage(getDriver());
        while(main.getCountOrder() != 0) {
            main.deleteOrder();
        }
        main.createOrder("100");

        List<String> shipFirst = main.getShipFirstRow();

        main.updateOrder(newState, newDistance);
        List<String> orderAfter = main.getOrderFirstRow();

        Double expectedPriceAfter = (Double.parseDouble(shipFirst.get(7)) * Integer.parseInt(expectedDistance));

        assertEquals(expectedState, orderAfter.get(1));
        assertEquals(expectedDistance, orderAfter.get(2));
        assertEquals( expectedPriceAfter , Double.parseDouble(orderAfter.get(3)));
    }
}
