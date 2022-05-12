package com.socks.ui.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoggedUserPage {

    public SelenideElement logoutBtn() {
        return $("#logout > a");
    }

    public SelenideElement userName() {
        return $("#howdy > a");
    }
}
