import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import rest.assertions.BasicApiAssert;
import rest.endpoints.GoodsApi;

import java.util.Properties;
import java.util.Random;

@DisplayName("[PATCH]/goods/Id")

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Tag("ChangeGood")
public class ChangeGoodTests {
    public static String sharedId2;
    Properties config = ConfigMain.loadProperties();
    String good_name = config.getProperty("API_GOOD_NAME_CHANGE");
    String bad_good_name = config.getProperty("API_GOOD_NAME_CHANGE_FALSE");
    String bad_id = config.getProperty("API_BAD_ID");
    Random random = new Random();
    int s = random.nextInt(1, 100);
    double price = random.nextDouble(1, 1000);
    String goodName = good_name+s;

    @Test
    @Order(10)
    @DisplayName("200")
    @Step("Успешное изменение товара")
    void changeGoodId() {
        String id = TestData.itemId;
        Response response = new GoodsApi().ChangeGoodId(id,goodName,price);
        BasicApiAssert.assertThat(response)
                .statusCodeIsEquals(200)
                .fieldIsExists("id")
                .fieldIsExists("name")
                .fieldIsExists("price");
    }

    @Test
    @Order(11)
    @DisplayName("Create Duplicate")
    @Step("Создание дубликата товара")
    void addNewGoodWithWrongName(){
        Response response = new GoodsApi().createGood(bad_good_name,price);
        String idAsString;
        idAsString = response.jsonPath().getString("data.id");
        sharedId2 = idAsString;
        TestData.itemId2 = sharedId2;
        System.out.println(sharedId2);
        System.out.println(TestData.itemId2);

    }

    @Test
    @Order(12)
    @DisplayName("400")
    @Step("Проверка невозможности создания двух товаров с одинаковым именем")
    void changeGoodId1() {
        String id = TestData.itemId;
        Response response = new GoodsApi().ChangeGoodId(id,bad_good_name,price);
        BasicApiAssert.assertThat(response)
                .statusCodeIsEquals(400);

    }
    @Test
    @Order(13)
    @Step("Проверка что если попытаться изменить несуществующий товар то получаем код - 404")
    @DisplayName("404")
    void changeGoodId2() {
        Response response = new GoodsApi().ChangeGoodId(bad_id,goodName, price);
        BasicApiAssert.assertThat(response)
                .statusCodeIsEquals(404);

    }

}
