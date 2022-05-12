package com.socks.ui.pages;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    public static MainPage open() {
        Selenide.open("/");
        return Selenide.page(MainPage.class);
    }

    public void loginAs(String userName, String password) {
        $("#login > a").click();
        $("#username-modal").setValue(userName);
        $("#password-modal").setValue(password);
        $("#login-modal p button").click();
    }
}
