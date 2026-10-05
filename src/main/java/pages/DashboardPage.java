package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage {
    WebDriver driver;

    // создаем драйвер
    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    // кнопка Оформить заказ
    private By createOrderButton = By.xpath(".//button[text()='Оформить заказ']");

    @Step("проверка видимости кнопки Оформить заказ после процедуры авторизации")
    public boolean isCreateOrderButtonVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(createOrderButton));
        boolean iscreateOrderButtonVisible =  driver.findElement(createOrderButton).isDisplayed();
        return iscreateOrderButtonVisible;
    }
}
