package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasswordRecoveryPage {
    WebDriver driver;

// создаем драйвер
public PasswordRecoveryPage(WebDriver driver) {
    this.driver = driver;
    }

//    гиперссылка Восстановить пароль
private By loginLinkPasswordRecoveryPage = By.xpath("//a[@class='Auth_link__1fOlj' and text()='Войти']");

@Step("Нажимаем на Восстановить пароль")
public void clickLoginLinkPasswordRecoveryPage(){
    driver.findElement(loginLinkPasswordRecoveryPage).click();
    }
}
