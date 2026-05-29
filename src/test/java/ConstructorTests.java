import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class СonstructorTests extends BaseUITest{

    @Test
@DisplayName("Проверка работы раздела Конструктор: переход в Булки")
@Description("Проверяем, что после клика на Булки элемент получает класс активной вкладки")
public void checkBunsTabActivation() {
    steps.startOfWork();
    // переходим в раздел и получаем актуальный класс
    String finalClass = mainPage.getActiveClassAfterClickingBuns();

    assertTrue("Класс элемента не содержит 'tab_tab_type_current'",
                finalClass.contains("tab_tab_type_current"));
    }

@Test
@DisplayName("Проверка работы раздела Конструктор: переход в Соусы")
@Description("Проверяем, что после клика на Соусы элемент получает класс активной вкладки")
public void checkSaucesTabActivation() {
    steps.startOfWork();
    // переходим в раздел и получаем актуальный класс
    String finalClass = mainPage.getActiveClassAfterClickingSauces();

    assertTrue("Класс элемента не содержит 'tab_tab_type_current'",
            finalClass.contains("tab_tab_type_current"));
    }

@Test
@DisplayName("Проверка работы раздела Конструктор: переход в Начинки")
@Description("Проверяем, что после клика на Начинки элемент получает класс активной вкладки")
public void checkFillingsTabActivation() {
     steps.startOfWork();
     // переходим в раздел и получаем актуальный класс
     String finalClass = mainPage.getActiveClassAfterClickingFillings();

     assertTrue("Класс элемента не содержит 'tab_tab_type_current'",
                finalClass.contains("tab_tab_type_current"));
    }
}
