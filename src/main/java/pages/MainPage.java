package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    WebDriver driver;
//    адрес сайта
private static final String SITE_URL = "https://stellarburgers.education-services.ru";
//    страница регистрации
private static final String REGISTSRATION_URL ="https://stellarburgers.education-services.ru/register";
//    кнопка Войти в аккаунт
private By loginButtonMain = By.xpath(".//button[text()='Войти в аккаунт']");
//    кнопка Личный кабинет
private By profileButton  = By.xpath("//p[text()='Личный Кабинет']");
//    раздел Булки
private By bunsLink = By.xpath("//div[span[text()='Булки']]");
//    раздел Соусы
private By saucesLink = By.xpath("//div[span[text()='Соусы']]");
//    раздел Начинки
private By fillingsLink = By.xpath("//div[span[text()='Начинки']]");


// создаем драйвер
public MainPage(WebDriver driver) {
    this.driver = driver;
}

//  открываем сайт бургеров
public void openSite() {
    driver.get(SITE_URL);
}

//  открываем страницк регистрации
public void openRegPage() {
    driver.get(REGISTSRATION_URL);
}

@Step("Нажимаем на Войти в аккаунт")
public void clickLoginButton() {
    driver.findElement(loginButtonMain).click();
}

    @Step("Нажимаем кнопку Личный кабинет ")
    public void clickProfileButton() {
        driver.findElement(profileButton).click();
    }

    @Step("Нажимаем на Булки")
    public void clickBunsLink() {
        driver.findElement(bunsLink).click();
    }

    @Step("Нажимаем на Соусы")
    public void clickSaucesLink() {
        driver.findElement(saucesLink).click();
    }

    @Step("Нажимаем на Начинки")
    public void clickFillingsLink() {
        driver.findElement(fillingsLink).click();
    }

    @Step("Переходим в раздел Булки и проверяем активный класс")
    public String getActiveClassAfterClickingBuns() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // сначала кликаем на Соусы, чтобы убрать активность с Булок
        driver.findElement(saucesLink).click();

        WebElement bunsTab = driver.findElement(bunsLink);
        bunsTab.click();

        return wait.until(driver -> {
            String currentClass = driver.findElement(bunsLink).getAttribute("class");
            return currentClass.contains("tab_tab_type_current") ? currentClass : null;
        });
    }

    @Step("Переходим в раздел Соусы и проверяем активный класс")
    public String getActiveClassAfterClickingSauces() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement saucesTab = driver.findElement(saucesLink);
        saucesTab.click();

        return wait.until(driver -> {
            String currentClass = driver.findElement(saucesLink).getAttribute("class");
            return currentClass.contains("tab_tab_type_current") ? currentClass : null;
        });
    }

    @Step("Переходим в раздел Начинки и проверяем активный класс")
    public String getActiveClassAfterClickingFillings() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement fillingsTab = driver.findElement(fillingsLink);
        fillingsTab.click();

        return wait.until(driver -> {
            String currentClass = driver.findElement(fillingsLink).getAttribute("class");
            return currentClass.contains("tab_tab_type_current") ? currentClass : null;
        });
    }
}