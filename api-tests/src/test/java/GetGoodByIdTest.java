import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import rest.assertions.BasicApiAssert;
import rest.endpoints.GoodsApi;

import java.util.Properties;


@DisplayName("[GET]/goods/Id")

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Tag("GetGoodById")
public class GetGoodByIdTest {
    Properties config = ConfigMain.loadProperties();
    String incorrect_id = config.getProperty("API_INCORRECT_ID");
    String bad_id = config.getProperty("API_BAD_ID");


    @Test
    @Order(7)
    @DisplayName("200")
    @Step("Успешное получение товара с id")
    void getGoodId() {
        String id = TestData.itemId;
        Response response = new GoodsApi().getGoodId(id);
        BasicApiAssert.assertThat(response)
                .statusCodeIsEquals(200)
                .fieldIsExists("id")
                .fieldIsExists("name")
                .fieldIsExists("price");

    }

    @Test
    @Order(8)
    @DisplayName("404")
    @Step("Проверка что запрос товара с id = 0 возвращает 404 код")
    void getGoodWithWrongId1() {
        Response response = new GoodsApi().getGoodId(bad_id);
        BasicApiAssert.assertThat(response)
                .statusCodeIsEquals(404);

    }

    @Test
    @Order(9)
    @DisplayName("500")
    @Step("Проверка что запрос товара с некорректным id возвращает 500 код")
    void getGoodWithWrongId() {
        Response response = new GoodsApi().getGoodId(incorrect_id);
        BasicApiAssert.assertThat(response)
                .statusCodeIsEquals(500);

    }
}
