package rest;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Properties;

import static io.restassured.RestAssured.given;
import static rest.endpoints.Urls.GOODS;

public class RestApiBuilder {
    static Properties config = ConfigMain.loadProperties();
    static String url = config.getProperty("URL");
    static String login = config.getProperty("LOGIN");
    static String password = config.getProperty("PASSWORD");
    RequestSpecification spec;
      private static final String BASIC_URL = url,
            LOGIN = login,
            PASS = password;

    public RestApiBuilder() {
        spec = given()
                .filter(new AllureRestAssured())
                .baseUri(BASIC_URL).basePath(GOODS)
                .log().all()
                .relaxedHTTPSValidation();
    }

    public RestApiBuilder(String url){
        spec = given()
                .filter(new AllureRestAssured())
                .baseUri(url);
    }





    public RestApiBuilder addAuth(String login, String password) {
        spec = spec.auth().basic(login, password);
        return this;
    }

    public RestApiBuilder setContentJSON() {
        spec = spec.contentType(ContentType.JSON);
        return this;
    }

    public RequestSpecification getSpec() {
        return spec;
    }


    public static RestApiBuilder getBuilder() {
        return new RestApiBuilder().addAuth(LOGIN, PASS);
    }

    public static RestApiBuilder getBuilderWithoutAuth() {
        return new RestApiBuilder();
    }


}