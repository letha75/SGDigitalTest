package Steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import cucumber.api.DataTable;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class CareerSearch {
    WebDriver driver;

    public CareerSearch() {
        System.setProperty("webdriver.chrome.driver", "c://driver//chromedriver.exe");
        //initialize chrome driver instance
        driver = new ChromeDriver();
    }

    @Given("^User launch SGDigital homepage$")
    public void userLaunchSGDigitalHomepage() {
        //start a new chrome browser
        driver.get("https://www.sgdigital.com");
        driver.manage().window().maximize();
    }

    @Then("^Careers menu item is displayed$")
    public void careersMenuItemIsDisplayed() {
        boolean blLinkPresent = driver.findElement(By.linkText("Careers")).isDisplayed();
        if (blLinkPresent)  {
            System.out.println("Careers link is available in SGDigital home Page.");
        } else {
            System.out.println("Careers link is not available in SGDigital home page.");
        }

    }

    @When("^User clicks on Careers link$")
    public void userClicksOnCareersLink() {
        driver.findElement(By.linkText("Careers")).click();
        System.out.println("\nUser clicked on Careers Link");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Then("^Careers Page is displayed with current vacancies listed$")
    public void careersPageIsDisplayedWithCurrentVacanciesListed() {
        boolean blVacancyCount = driver.findElement(By.className("srJobListJobTitle")).isDisplayed();
        if (blVacancyCount)  {
            System.out.println("\nCurrent Vacancies are displayed.");
        } else {
            System.out.println("\nCurrent Vacancies are not displayed..");
        }
    }

    @And("^I click enter key$")
    public void iClickEnterKey() {
        driver.findElement(By.className("srSearchInput")).sendKeys(Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    @Then("^Vacancies based on the criteria is displayed$")
    public void vacanciesBasedOnTheCriteriaIsDisplayed() {

    }


    @Then("^vacancies specific to location is displayed$")
    public void vacanciesSpecificToLocationIsDisplayed() {

    }

    @Then("^Close Browser$")
    public void closeBrowser() {
        //Close Browser
        driver.quit();
    }

    @And("^User enter following as keyword$")
    public void userEnterFollowingAsKeyword(DataTable table) {
        List<List<String>> data = table.raw();
        for (int i = 1; i <= (data.size() - 1); i++) {
            driver.findElement(By.className("srSearchInput")).clear();
            driver.findElement(By.className("srSearchInput")).sendKeys(data.get(i).get(0));
            System.out.println("\nKeyword entered is "+data.get(i).get(0));
        }

    }

    @And("^user selects following location$")
    public void userSelectsFollowingLocation(DataTable table) {

        List<List<String>> dataloc = table.raw();
        for (int i = 1; i <= (dataloc.size() - 1); i++) {
            WebElement dropdown = driver.findElement(By.className("srSearchOption"));
            dropdown.click();
            String locValue = dataloc.get(i).get(0);
            System.out.println("\nLocation selected is "+locValue);
            List<WebElement> options = dropdown.findElements(By.className("srSearchOptionListElement"));
            for (WebElement option : options) {
                if (option.getText().equals(locValue)) {
                    option.click();
                    break;
                }
            }
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
    }

}