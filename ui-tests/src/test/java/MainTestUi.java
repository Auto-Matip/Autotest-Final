
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import main.MainPageAssertTask;
import main.MainPageTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static com.codeborne.selenide.Selenide.*;

@Tag("ui")
public class MainTestUi {
    MainPageTask mainPageTask = new MainPageTask();
    MainPageTask.CartPopup cartPopup = mainPageTask.new CartPopup();
    MainPageTask.AdminLoginForm AdminLoginForm = mainPageTask.new AdminLoginForm();
    MainPageAssertTask mainPageAssertTask = new MainPageAssertTask(mainPageTask);

    @BeforeEach
    void setup(){
        Properties config = ConfigMain.loadProperties();
        String url = config.getProperty("URL");
        String listener = config.getProperty("LISTENER");
        open(url);
        SelenideLogger.addListener(listener, new AllureSelenide()
                .screenshots(true)
                .savePageSource(false)
        );
    }
    @Test
    @Step("Добавить три единицы товара в корзину и оплатить их")
    void Task1() {
        Properties config = ConfigMain.loadProperties();
        String product_count = config.getProperty("PRODUCT_COUNT");
        String timeoutstr = config.getProperty("TIMEOUT");
        String totalprice = config.getProperty("TOTAL_PRICE");
        long timeout = Long.parseLong(timeoutstr);
        mainPageAssertTask.cartButtonIsVisible();
        mainPageAssertTask.adminPannelButtonIsVisible();
        mainPageAssertTask.addToCartButtonIsVisible();
        mainPageAssertTask.increaseButtonIsVisible();
        mainPageAssertTask.productCardListShouldBeGreater(1);
        mainPageAssertTask.orderProcessingNotificationIsNotVisible();
        mainPageTask.inputProductCount(0,product_count);
        sleep(timeout);
        mainPageAssertTask.countInputContainsValue(0,product_count);
        sleep(timeout);
        mainPageTask.clickAddtoCartButton();
        mainPageAssertTask.orderAddToCartNotificationIsVisible();
        mainPageAssertTask.orderAddToCartNotificationHaveCorrectText();
        sleep(timeout);
        mainPageTask.clickCartButton();
        sleep(timeout);
        mainPageAssertTask.orderProcessingNotificationIsNotVisible();
        mainPageAssertTask.orderAddToCartNotificationIsNotVisible();
        mainPageAssertTask.TotalPriceTextIsVisible();
        mainPageAssertTask.TotalPriceTextHaveCorrectPrice(totalprice);
        sleep(timeout);
        cartPopup.clickmakeOrderButton();
        mainPageAssertTask.orderProcessingNotificationIsVisible();
        mainPageAssertTask.orderProcessingNotificationHaveCorrectText();
        sleep(timeout);
        mainPageAssertTask.orderProcessingNotificationIsNotVisible();
    }

    @Test
    @Step("Добавить в корзину несколько разных товаров и проверить, что общая цена в корзине считается корректно")
    void Task2() {
        Properties config = ConfigMain.loadProperties();
        String product_count_str = config.getProperty("PRODUCT_COUNT");
        String timeoutstr = config.getProperty("TIMEOUT");
        String totalprice = config.getProperty("TOTAL_PRICE_GOODS");
        String good_name_1 = config.getProperty("GOOD_NAME1");
        String good_name_2 = config.getProperty("GOOD_NAME2");
        String good_name_3 = config.getProperty("GOOD_NAME3");
        String good_price_1 = config.getProperty("GOOD_PRICE1");
        String good_price_2 = config.getProperty("GOOD_PRICE2");
        String good_price_3 = config.getProperty("GOOD_PRICE3");

        long timeout = Long.parseLong(timeoutstr);
        int product_count = Integer.parseInt(product_count_str);
        mainPageAssertTask.cartButtonIsVisible();
        mainPageAssertTask.adminPannelButtonIsVisible();
        mainPageAssertTask.addToCartButtonIsVisible();
        mainPageAssertTask.increaseButtonIsVisible();
        mainPageAssertTask.productCardListShouldBeGreater(product_count);
        mainPageTask.inputAddToCartButtonList(0);
        mainPageAssertTask.orderAddToCartNotificationHaveCorrectTextForProduct(good_name_1);
        sleep(timeout);
        mainPageTask.inputAddToCartButtonList(1);
        mainPageAssertTask.orderAddToCartNotificationHaveCorrectTextForProduct(good_name_2);
        sleep(timeout);
        mainPageTask.inputAddToCartButtonList(2);
        mainPageAssertTask.orderAddToCartNotificationHaveCorrectTextForProduct(good_name_3);
        mainPageAssertTask.orderAddToCartNotificationIsVisible();
        sleep(timeout);
        mainPageTask.clickCartButton();
        sleep(timeout);
        mainPageAssertTask.CheckProductPriceHasCorrectPrice(0,good_price_1);
        mainPageAssertTask.CheckProductPriceHasCorrectPrice(1,good_price_2);
        mainPageAssertTask.CheckProductPriceHasCorrectPrice(2,good_price_3);
        mainPageAssertTask.TotalPriceTextIsVisible();
        mainPageAssertTask.TotalPriceTextHaveCorrectPrice(totalprice);
        sleep(timeout);
        cartPopup.clickmakeOrderButton();
        mainPageAssertTask.orderProcessingNotificationIsVisible();
        mainPageAssertTask.orderProcessingNotificationHaveCorrectText();

    }
    @Test
    @Step("Войти в админку и добавить товар. Проверить уведомление после добавления товара")
    void Task3() {
        Properties config = ConfigMain.loadProperties();
        String product_id_str = config.getProperty("PRODUCT_ID");
        String login = config.getProperty("LOGIN");
        String password = config.getProperty("PASSWORD");
        String good_name = config.getProperty("GOOD_NAME");
        String good_price = config.getProperty("GOOD_PRICE");
        String timeoutstr = config.getProperty("TIMEOUT");
        long timeout = Long.parseLong(timeoutstr);
        int product_id = Integer.parseInt(product_id_str);
        mainPageTask.clickAdminButton();
        AdminLoginForm.clickSignInButtonIsVisible();
        AdminLoginForm.LoginFieldIsVisible();
        AdminLoginForm.PasswordFieldIsVisible();
        sleep(timeout);
        AdminLoginForm.inputLogin(login);
        AdminLoginForm.LoginFieldContainsText(login);
        AdminLoginForm.inputPassword(password);
        AdminLoginForm.PasswordFieldFieldContainsText(password);
        sleep(timeout);
        AdminLoginForm.clickSignInButton();
        AdminLoginForm.CheckAllertIsNotVisible();
        sleep(timeout);
        AdminLoginForm.ProductNameFieldIsVisible();
        AdminLoginForm.ProductPriceFieldIsVisible();
        AdminLoginForm.ProductAddButtonIsVisible();
        AdminLoginForm.inputProductName(good_name);
        AdminLoginForm.ProductNameFieldContainsText(good_name);
        sleep(timeout);
        AdminLoginForm.inputProductPrice(good_price);
        AdminLoginForm.ProductPriceFieldContainsText(good_price);
        sleep(timeout);
        AdminLoginForm.ProductAddButtonClick();
        AdminLoginForm.ProductHasAddedNotificationIsVisible();
        AdminLoginForm.ProductHasAddedNotificationText();
        sleep(timeout);
        AdminLoginForm.DeleteProductButtonClick(product_id);
        switchTo().alert().accept();
        sleep(timeout);
        AdminLoginForm.ComeBackButtonIsVisible();
        AdminLoginForm.ComeBackButtonClick();
        sleep(timeout);
    }

    @Test
    @Step("Войти в админку и отредактировать товар.")
    void Task4() {
        Properties config = ConfigMain.loadProperties();
        String good_name = config.getProperty("GOOD_NAME");
        String good_price = config.getProperty("GOOD_PRICE_NEW");
        String timeoutstr = config.getProperty("TIMEOUT");
        long timeout = Long.parseLong(timeoutstr);
        String login = config.getProperty("LOGIN");
        String password = config.getProperty("PASSWORD");
        mainPageTask.clickAdminButton();
        sleep(timeout);
        AdminLoginForm.ProductNameFieldIsVisible();
        AdminLoginForm.ProductPriceFieldIsVisible();
        AdminLoginForm.ProductAddButtonIsVisible();
        AdminLoginForm.inputProductName(4,good_name);
        AdminLoginForm.inputProductPrice(4,good_price);
        AdminLoginForm.SaveButtonClick(3);
        AdminLoginForm.ProductHasUpdateNotificationIsVisible();
        AdminLoginForm.ProductHasUpdateNotificationHasText();
        sleep(timeout);
        AdminLoginForm.ComeBackButtonIsVisible();
        AdminLoginForm.ComeBackButtonClick();
        mainPageAssertTask.productPriceListContainsPrice(good_price + " ₽");
        mainPageAssertTask.productNameListContainsProduct(good_name);
        sleep(timeout);
    }

}
