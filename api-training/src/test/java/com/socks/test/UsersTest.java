package com.socks.test;

import com.github.javafaker.Faker;
import com.socks.api.config.ProjectConfig;
import com.socks.api.payloads.UserPayload;
import com.socks.api.responses.UserRegistrationResponse;
import com.socks.api.services.UserApiService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Locale;

import static com.socks.api.conditions.Conditions.bodyField;
import static com.socks.api.conditions.Conditions.statusCode;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.not;

public class UsersTest {

    private final UserApiService userApiService = new UserApiService();
    private Faker faker;

    @BeforeClass
    public void setUp() {
        ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());

        RestAssured.baseURI = config.baseUrl();
        faker = new Faker(new Locale(config.locale()));
    }

    @Test(enabled = false)
    public void testCanRegisterNewUser() {
        UserPayload user = new UserPayload()
                .username(RandomStringUtils.randomAlphanumeric(6))
                .email("test@mail.com")
                .password("test123");

        RestAssured
                .given().contentType(ContentType.JSON).log().all()
                .body(user)
                .when()
                .post("register")
                .then().log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("id", not(emptyString()));
    }

    @Test(enabled = false)
    public void testCanNotRegisterSameUserTwice() {
        UserPayload user = new UserPayload()
                .username(RandomStringUtils.randomAlphanumeric(6))
                .email("test@mail.com")
                .password("test123");

        RestAssured
                .given().contentType(ContentType.JSON).log().all()
                .body(user)
                .when()
                .post("register")
                .then().log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("id", not(emptyString()));

        RestAssured
                .given().contentType(ContentType.JSON).log().all()
                .body(user)
                .when()
                .post("register")
                .then().log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

    @Test
    public void testCanRegisterNewUserWithService() {
        UserPayload user = new UserPayload()
                .username(faker.name().username())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password(5, 20, true, true, true));

        userApiService.registerUser(user)
                .shouldHave(statusCode(HttpStatus.SC_OK))
//                .asPojo(UserRegistrationResponse.class)
                .shouldHave(bodyField("id", not(emptyString())));
    }
}
