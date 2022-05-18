package cz.cvut.fel.ts1.sp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class MainPage {

    private WebDriver driver;

    // for create ship
    @FindBy(how = How.XPATH, using = "/html/body/div/button")
    private WebElement createNewShipBtn;

    @FindBy(how = How.ID, using = "inputNameNew")
    private WebElement inputNameNewShip;

    @FindBy(how = How.ID, using = "inputPlanetNew")
    private WebElement inputPlanetNewShip;

    @FindBy(how = How.ID, using = "inputProdYearNew")
    private WebElement inputProdYearNewShip;

    @FindBy(how = How.ID, using = "inputCrewSizeNew")
    private WebElement inputCrewSizeNewShip;

    @FindBy(how = How.ID, using = "inputSpeedNew")
    private WebElement inputSpeedNewShip;

    @FindBy(how = How.ID, using = "inlineRadioNew1")
    private WebElement radioOptionPreOwnedNewShip;

    @FindBy(how = How.ID, using = "inlineRadioNew2")
    private WebElement radioOptionNewNewShip;

    @FindBy(how = How.ID, using = "inputPriceNew")
    private WebElement inputPriceNewShip;

    @FindBy(how = How.ID, using = "inputShipTypeNew")
    private WebElement selectShipTypeNewShip;

    @FindBy(how = How.XPATH, using = "/html/body/div/form[1]/button")
    private WebElement createShipBtn;

    @FindBy(how = How.ID, using = "count")
    private WebElement countShips;

    //  for  update ship
    @FindBy(how = How.XPATH, using = "//*[@id='mainTable']/tr[1]/td[10]/button")
    private WebElement firstShipUpdateBtn;

    @FindBy(how = How.XPATH, using = "/html/body/div/table[1]/tbody/tr[2]/td[1]/input")
    private WebElement inputNameUpdateShip;

    @FindBy(how = How.XPATH, using = "/html/body/div/table[1]/tbody/tr[2]/td[2]/input")
    private WebElement inputPlanetUpdateShip;

    @FindBy(how = How.XPATH, using = "/html/body/div/table[1]/tbody/tr[2]/td[4]/input")
    private WebElement inputProdYearUpdateShip;

    @FindBy(how = How.XPATH, using = "/html/body/div/table[1]/tbody/tr[2]/td[7]/input")
    private WebElement inputCrewSizeUpdateShip;

    @FindBy(how = How.XPATH, using = "/html/body/div/table[1]/tbody/tr[2]/td[6]/input")
    private WebElement inputSpeedUpdateShip;

    @FindBy(how = How.XPATH, using = "/html/body/div/table[1]/tbody/tr[2]/td[3]/select")
    private WebElement selectShipTypeUpdateShip;

    @FindBy(how = How.XPATH, using = "/html/body/div/table[1]/tbody/tr[2]/td[5]/select")
    private WebElement selectIsUsedUpdateShip;

    @FindBy(how = How.XPATH, using = "/html/body/div/table[1]/tbody/tr[2]/td[8]/input")
    private WebElement inputPriceUpdateShip;

    @FindBy(how = How.XPATH, using = "/html/body/div/table[1]/tbody/tr[2]/td[10]/button")
    private WebElement firstShipSaveBtn;

    // for delete ship
    @FindBy(how = How.XPATH, using = "//*[@id='mainTable']/tr[1]/td[11]/button[1]")
    private WebElement firstShipDeleteBtn;

    // for create order
    @FindBy(how = How.XPATH, using = "/html/body/div/table[1]/tbody/tr[1]/td[11]/button[2]")
    private WebElement firstNewOrderBtn;

    @FindBy(how = How.XPATH, using = "/html/body/div/table[1]/tbody/tr[2]/td[3]/button")
    private WebElement firstNewOrderSaveBtn;

    // for get count orders
    @FindBy(how = How.XPATH, using = "/html/body/div/table[2]/tbody/tr")
    private List<WebElement> allOrders;

    // for delete order
    @FindBy(how = How.XPATH, using = "/html/body/div/table[2]/tbody/tr[1]/td[6]/button")
    private WebElement firstDeleteOrderBtn;

    // change the number of rows in ship table
    @FindBy(how = How.XPATH, using = "/html/body/div/select[2]")
    private WebElement selectCountRows;

    // for sort
    @FindBy(how = How.XPATH, using = "/html/body/div/select[1]")
    private WebElement selectFilter;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // for filter
    @FindBy(how = How.ID, using = "inputName")
    private WebElement inputNameShipFilter;

    @FindBy(how = How.ID, using = "inputPlanet")
    private WebElement inputPlanetShipFilter;

    @FindBy(how = How.ID, using = "inputProdYearAfter")
    private WebElement inputPlanetShipProdYearMin;

    @FindBy(how = How.ID, using = "inputProdYearBefore")
    private WebElement inputPlanetShipProdYearMax;

    @FindBy(how = How.ID, using = "inputCrewSizeMin")
    private WebElement inputPlanetShipCrewSizeMin;

    @FindBy(how = How.ID, using = "inputCrewSizeMax")
    private WebElement inputPlanetShipCrewSizeMax;

    @FindBy(how = How.ID, using = "inputSpeedMin")
    private WebElement inputPlanetShipSpeedMin;

    @FindBy(how = How.ID, using = "inputSpeedMax")
    private WebElement inputPlanetShipSpeedMax;

    @FindBy(how = How.ID, using = "inputRatingMin")
    private WebElement inputPlanetShipRatingMin;

    @FindBy(how = How.ID, using = "inputRatingMax")
    private WebElement inputPlanetShipRatingMax;

    @FindBy(how = How.ID, using = "inputPriceMin")
    private WebElement inputPlanetShipPriceMin;

    @FindBy(how = How.ID, using = "inputPriceMax")
    private WebElement inputPlanetShipPriceMax;

    @FindBy(how = How.ID, using = "inputShipType")
    private WebElement selectShipTypeFilter;

    @FindBy(how = How.ID, using = "inlineRadio1")
    private WebElement radioOptionFilterAny;

    @FindBy(how = How.ID, using = "inlineRadio2")
    private WebElement radioOptionFilterPreOwned;

    @FindBy(how = How.ID, using = "inlineRadio3")
    private WebElement radioOptionFilterNew;

    @FindBy(how = How.XPATH, using = "/html/body/div/form[2]/button")
    private WebElement searchBth;

    public void clickCreateNewShip() {
        createNewShipBtn.click();
    }

    public void fillFormCreateShip(String name,
                                       String planet,
                                       String shipType,
                                       String prodYear,
                                       String isNew,
                                       String speed,
                                       String crewSize,
                                       String price) {

        inputNameNewShip.sendKeys(name);
        inputPlanetNewShip.sendKeys(planet);
        inputProdYearNewShip.sendKeys(prodYear);
        if (isNew.equals("true"))
            radioOptionNewNewShip.click();
        else
            radioOptionPreOwnedNewShip.click();
        inputSpeedNewShip.sendKeys(speed);
        inputCrewSizeNewShip.sendKeys(crewSize);
        inputPriceNewShip.sendKeys(price);
        Select select = new Select(selectShipTypeNewShip);
        select.selectByVisibleText(shipType);
    }

    public void sendFormCreateShip() {
        createShipBtn.click();
    }

    public String getCountShips() {
        return countShips.getText();
    }

    public List<String> getShipFirstRow() {
        List<String> rowData = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            WebElement el = driver.findElement(By.xpath("/html/body/div/table[1]/tbody/tr[1]/td[" + i + "]"));
            rowData.add(el.getText());
        }
        return rowData;
    }

    public void updateShip(String name,
                                   String planet,
                                   String shipType,
                                   String prodYear,
                                   String isNew,
                                   String speed,
                                   String crewSize,
                                   String price) {
        firstShipUpdateBtn.click();

        inputNameUpdateShip.clear();
        inputNameUpdateShip.sendKeys(name);
        inputPlanetUpdateShip.clear();
        inputPlanetUpdateShip.sendKeys(planet);
        Select selectType = new Select(selectShipTypeUpdateShip);
        selectType.selectByVisibleText(shipType);
        inputProdYearUpdateShip.clear();
        inputProdYearUpdateShip.sendKeys(prodYear);
        Select selectIsNew = new Select(selectIsUsedUpdateShip);
        selectIsNew.selectByVisibleText(isNew);
        inputSpeedUpdateShip.clear();
        inputSpeedUpdateShip.sendKeys(speed);
        inputCrewSizeUpdateShip.clear();
        inputCrewSizeUpdateShip.sendKeys(crewSize);
        inputPriceUpdateShip.clear();
        inputPriceUpdateShip.sendKeys(price);

        firstShipSaveBtn.click();
    }

    public void deleteShip() {
        firstShipDeleteBtn.click();
    }

    public void createOrder(String distance) {
        firstNewOrderBtn.click();

        WebElement id = driver.findElement(By.xpath("/html/body/div/table[1]/tbody/tr[1]/th"));
        String idStr = id.getText();
        WebElement inputDistance = driver.findElement(By.id("orderDistance" + idStr));
        inputDistance.sendKeys(distance);

        firstNewOrderSaveBtn.click();
    }

    public void filterShips(String name,
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
                                String maxPrice) {
        inputNameShipFilter.clear();
        inputNameShipFilter.sendKeys(name != null ? name : "");
        inputPlanetShipFilter.clear();
        inputPlanetShipFilter.sendKeys(planet != null ? planet : "");
        inputPlanetShipProdYearMin.clear();
        inputPlanetShipProdYearMin.sendKeys(after != null ? after : "");
        inputPlanetShipProdYearMax.clear();
        inputPlanetShipProdYearMax.sendKeys(before != null ? before : "");
        inputPlanetShipCrewSizeMin.clear();
        inputPlanetShipCrewSizeMin.sendKeys(minCrewSize != null ? minCrewSize : "");
        inputPlanetShipCrewSizeMax.clear();
        inputPlanetShipCrewSizeMax.sendKeys(maxCrewSize != null ? maxCrewSize : "");
        inputPlanetShipSpeedMin.clear();
        inputPlanetShipSpeedMin.sendKeys(minSpeed != null ? minSpeed : "");
        inputPlanetShipSpeedMax.clear();
        inputPlanetShipSpeedMax.sendKeys(maxSpeed != null ? maxSpeed : "");
        inputPlanetShipRatingMin.clear();
        inputPlanetShipRatingMin.sendKeys(minRating != null ? minRating : "");
        inputPlanetShipRatingMax.clear();
        inputPlanetShipRatingMax.sendKeys(maxRating != null ? maxRating : "");
        inputPlanetShipPriceMin.clear();
        inputPlanetShipPriceMin.sendKeys(minPrice != null ? minPrice : "");
        inputPlanetShipPriceMax.clear();
        inputPlanetShipPriceMax.sendKeys(maxPrice != null ? maxPrice : "");
        Select select = new Select(selectShipTypeFilter);
        select.selectByVisibleText(shipType == null ? "Any" : shipType);
        if (isUsed != null && isUsed.equals("New"))
            radioOptionFilterNew.click();
        else if(isUsed != null && isUsed.equals("Pre-owned"))
            radioOptionFilterPreOwned.click();
        else
            radioOptionFilterAny.click();

        searchBth.click();
    }

    public int getCountOrder() {
        return allOrders.size();
    }

    public boolean isUsed() {
        boolean isUsed = false;
        String idShip = driver.findElement(By.xpath("/html/body/div/table[1]/tbody/tr[1]/th")).getText();
        for (int i = 1; i <= allOrders.size(); i++) {
            WebElement idEl = driver.findElement(By.xpath("/html/body/div/table[2]/tbody/tr[" + i + "]/td[1]"));
            if (idEl.getText().equals(idShip)) {
                WebElement isUsedEl =  driver.findElement(By.xpath("/html/body/div/table[2]/tbody/tr[" + i + "]/td[2]"));
                if (isUsedEl.getText().equals("active"))
                    isUsed = true;
            }
        }
        return isUsed;
    }

    public void deleteOrder() {
        if (allOrders.size() > 0) {
            firstDeleteOrderBtn.click();
        }
    }

    public void updateOrder(String state, String distance) {
        WebElement btnUpdate = driver.findElement(By.xpath("/html/body/div/table[2]/tbody/tr[1]/td[5]/button"));
        btnUpdate.click();

        WebElement selectEl = driver.findElement(By.xpath("/html/body/div/table[2]/tbody/tr[2]/td[2]/select"));
        Select select = new Select(selectEl);
        select.selectByVisibleText(state);

        WebElement elDistance = driver.findElement(By.xpath("/html/body/div/table[2]/tbody/tr[2]/td[4]/input"));
        elDistance.clear();
        elDistance.sendKeys(distance);

        WebElement btnSave = driver.findElement(By.xpath("/html/body/div/table[2]/tbody/tr[2]/td[6]/button"));
        btnSave.click();
    }

    public List<String> getOrderFirstRow() {
        List<String> rowData = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            WebElement el = driver.findElement(By.xpath("/html/body/div/table[2]/tbody/tr[1]/td[" + i + "]"));
            rowData.add(el.getText());
        }
        return rowData;
    }

    public void setCountShipInPage(String count) {
        Select selectType = new Select(selectCountRows);
        selectType.selectByVisibleText(count);
    }

    public int getCountRowsTableShip() {
        List<WebElement> list = driver.findElements(By.xpath("/html/body/div/table[1]/tbody/tr"));
        return list.size();
    }

    public int getCountLimitTableShip() {
        Select selectType = new Select(selectCountRows);
        return Integer.parseInt(selectType.getFirstSelectedOption().getText());
    }

    public void setFilter(String filter) {
        Select selectType = new Select(selectFilter);
        selectType.selectByVisibleText(filter);
    }

    public List<String> getAllIdsShip() {
        List<WebElement> elements = driver.findElements(By.xpath("/html/body/div/table[1]/tbody/tr/th"));
        return convertWebElementToString(elements);
    }

    public List<String> getAllSpeedShip() {
        List<WebElement> elements = driver.findElements(By.xpath("/html/body/div/table[1]/tbody/tr/td[6]"));
        return convertWebElementToString(elements);
    }

    public List<String> getAllProdDates() {
        List<WebElement> elements = driver.findElements(By.xpath("/html/body/div/table[1]/tbody/tr/td[4]"));
        return convertWebElementToString(elements);
    }

    public List<String> getAllRating() {
        List<WebElement> elements = driver.findElements(By.xpath("/html/body/div/table[1]/tbody/tr/td[9]"));
        return convertWebElementToString(elements);
    }

    private List<String> convertWebElementToString(List<WebElement> elements) {
        List<String> list = new ArrayList<>();
        for (WebElement el : elements) {
            list.add(el.getText());
        }
        return list;
    }

    public int getCountPagesTable() {
        return driver.findElements(By.xpath("/html/body/div/div[2]/ul/li")).size();
    }
}