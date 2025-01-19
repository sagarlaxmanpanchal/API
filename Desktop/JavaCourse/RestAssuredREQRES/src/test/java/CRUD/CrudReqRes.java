package CRUD;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class CrudReqRes {

    public String url ="https://reqres.in/api/users/932";
    public int id;
    @Test(enabled = false)
    void getUser(){
        given().contentType(ContentType.JSON)
                .when().get(url)
                .then().statusCode(200);
    }

    @Test(enabled = false)
    void getUser1(){
        when().get("https://reqres.in/api/users/2")
                .then().assertThat().statusCode(200)
                .log().body()
                .and().body("data.id",equalTo(2))
                .and().body("data.first_name",containsString("Janet"));
    }
    @Test(enabled = false)
    void getUser2(){
        when().get("https://reqres.in/api/users?page=-1")
                .then().assertThat().statusCode(200)
                .log().body()
               .and().body("data[0].id",equalTo(1));
    }

    @Test(enabled = false)
    void getUser3(){
        given().baseUri("https://reqres.in/api")
                .when().get("/users/2")
                .then().assertThat().statusCode(200);
    }

    @Test(enabled = true,priority = 0)
    void post(){
        HashMap<String,String> hm=new HashMap<>();
        hm.put("name","Sagarika");
        hm.put("job","QA");

       id= Integer.parseInt(given().contentType(ContentType.JSON).baseUri("https://reqres.in/").body(hm)
                .when().post("/api/users")
                .then().log().body().extract().jsonPath().getString("id"));
        System.out.println(id);

//        id= given().contentType(ContentType.JSON).baseUri("https://reqres.in/").body(hm)
//                .when().post("/api/users")
//                .then().assertThat().statusCode(201).log().body();


    }


    @Test(enabled = true,priority = 1)
    void  put(){
        HashMap<String,String> hm= new HashMap();
        hm.put("name","Ravi");

        given().contentType(ContentType.JSON).baseUri("https://reqres.in/").body(hm).
                when().put("/api/users/"+id)
                .then().assertThat().statusCode(200).assertThat().body("name",containsString("Ravi"));


    }
    @Test(enabled = true,priority = 2)
    void delete(){

        given().contentType(ContentType.JSON).baseUri("https://reqres.in/").
                when().delete("/api/users/"+id)
                .then().assertThat().statusCode(204);
    }
}
