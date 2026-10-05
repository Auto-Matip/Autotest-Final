package main;
import org.assertj.core.api.AbstractAssert;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.value;

public class AdminLoginFormAssert extends AbstractAssert<AdminLoginFormAssert, MainPageTask.AdminLoginForm> {

    public AdminLoginFormAssert(MainPageTask.AdminLoginForm adminLoginForm) {
        super(adminLoginForm, AdminLoginFormAssert.class);
    }

    public void CheckAllertText() {
        actual.AllertText.shouldHave(text("Неверные учетные данные пользователя"));

    }



    public void CheckAllertVisible() {
        actual.AllertText.should(visible);

    }
    public void CheckAllertIsNotVisible() {
        actual.AllertText.shouldNot(visible);

    }
    public void clickSignInButtonIsVisible() {
        actual.SignInButton.should(visible);
    }
    public void LoginFieldIsVisible() {
        actual.LoginField.should(visible);
    }
    public void PasswordFieldIsVisible() {
        actual.PasswordField.should(visible);
    }

    public void LoginFieldContainsText(String login) {
        actual.LoginField.shouldHave(value(login));
    }

    public void PasswordFieldFieldContainsText(String password) {
        actual.PasswordField.shouldHave(value(password));
    }

    public void ProductNameFieldIsVisible() {
        actual.ProductNameField.should(visible);
    }
    public void ProductPriceFieldIsVisible() {
        actual.ProductPriceField.should(visible);
    }
    public void ProductAddButtonIsVisible() {
        actual.ProductAddButton.should(visible);
    }
    public void ProductHasAddedNotificationIsVisible() {
        actual.ProductHasAddedNotification.should(visible);
    }
    public void ProductNameFieldContainsText(String login) {
        actual.ProductNameField.shouldHave(value(login));
    }
    public void ProductPriceFieldContainsText(String login) {
        actual.ProductPriceField.shouldHave(value(login));
    }

    public void ProductHasAddedNotificationText() {
        actual.ProductHasAddedNotification.shouldHave(text("Товар успешно добавлен!"));

    }




}

