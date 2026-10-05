import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import rest.assertions.BasicApiAssert;
import rest.endpoints.GoodsApi;

import java.util.Properties;


@DisplayName("[DELETE]/goods/Id")

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Tag("DeleteGood")
public class DeleteGoodTest {
    Properties config = ConfigMain.loadProperties();
   String bad_id = config.getProperty("API_BAD_ID");



    @Test
    @Order(14)
    @DisplayName("200")
    @Step("Успешное удаление первого созданного товара")
    void deleteGoodId() {
        String id = TestData.itemId;
        System.out.println(id);
        Response response = new GoodsApi().DeleteGoodId(id);
        BasicApiAssert.assertThat(response)
                .statusCodeIsEquals(200);
    }
    @Test
    @Order(15)
    @DisplayName("200")
    @Step("Успешное удаление второго созданного товара")
    void deleteGoodId2() {
        String id = TestData.itemId2;
        System.out.println(id);
        Response response = new GoodsApi().DeleteGoodId(id);
        BasicApiAssert.assertThat(response)
                .statusCodeIsEquals(200);
    }

    @Test
    @Order(16)
    @DisplayName("404")
    @Step("Не успешный кейс удаления товара с несуществующим id")
    void deleteGoodWithWrongId() {
        Response response = new GoodsApi().DeleteGoodId(bad_id);
        BasicApiAssert.assertThat(response)
                .statusCodeIsEquals(404);

    }

}
