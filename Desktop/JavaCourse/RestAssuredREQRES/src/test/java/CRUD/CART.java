package CRUD;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CART {

    String url="https://simple-grocery-store-api.glitch.me";


    @Test
    public String postCart(){
      String cartId=  given().baseUri(url).contentType(ContentType.JSON)
                .when().post("/carts").then().log().body().extract().body().jsonPath().getString("cartId");
      return cartId;
    }

    @Test
    public void getCarts(){
        given().baseUri(url).when().get("/carts/"+postCart()).then().log().body();
    }

    @Test
    public void
}
