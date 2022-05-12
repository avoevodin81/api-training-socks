package com.socks.ui.tests;

import com.codeborne.selenide.Condition;
import com.socks.api.conditions.Conditions;
import com.socks.api.payloads.UserPayload;
import com.socks.api.services.UserApiService;
import com.socks.ui.pages.LoggedUserPage;
import com.socks.ui.pages.MainPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

public class TestLogin extends BaseUITest {

    private final UserApiService userApiService = new UserApiService();

    @Test
    public void userCanLoginWithValidCredentials() {
        // given
        UserPayload user = new UserPayload()
                .username(RandomStringUtils.randomAlphanumeric(6))
                .email("demo@test.com")
                .password(RandomStringUtils.randomAlphanumeric(6));
        userApiService.registerUser(user).shouldHave(Conditions.statusCode(HttpStatus.SC_OK));

        // when
        MainPage.open().loginAs(user.username(), user.password());

        LoggedUserPage loggedUserPage = at(LoggedUserPage.class);
        loggedUserPage.logoutBtn().shouldHave(Condition.text("Logout"));
        loggedUserPage.userName().shouldHave(Condition.text("Logged in as"));
    }
}
