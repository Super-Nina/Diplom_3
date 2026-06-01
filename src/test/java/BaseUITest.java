import org.openqa.selenium.chrome.ChromeOptions;
import pages.*;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import steps.Steps;


public class BaseUITest {
    WebDriver driver;
    MainPage mainPage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    RegistrationPage registrationPage;
    PasswordRecoveryPage passwordRecoveryPage;
    Steps steps;

    @Before
//    возможность переключать браузеры
    public void startBrowser() {
        String browser = System.getProperty("browser", "chrome");
        if (browser.equals("chrome")) {
            startBrowserChrome();
        } else if (browser.equals("yandex")) {
            startBrowserYandex();
        }
    }

    public void startBrowserChrome() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-tools", "--no-extensions");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        registrationPage = new RegistrationPage(driver);
        passwordRecoveryPage = new PasswordRecoveryPage(driver);
        steps = new Steps(driver, mainPage, loginPage,dashboardPage, registrationPage, passwordRecoveryPage);
    }

    public void startBrowserYandex() {
        System.setProperty("webdriver.chrome.driver",
                "C:/yandexdriver-26.4.3.894-win64/yandexdriver.exe");
        ChromeOptions options = new ChromeOptions();
    //  точный путь к Яндекс Браузеру
        options.setBinary("C:\\Program Files (x86)\\Yandex\\YandexBrowser\\Application\\browser.exe");
        options.addArguments("--disable-dev-tools", "--no-extensions");
    // Создаём драйвер с настройками
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        registrationPage = new RegistrationPage(driver);
        passwordRecoveryPage = new PasswordRecoveryPage(driver);
        steps = new Steps(driver, mainPage, loginPage,dashboardPage, registrationPage, passwordRecoveryPage);
    }

    //закрываем браузер
    @After
    public void tearDown() {
        driver.quit();
    }
}

