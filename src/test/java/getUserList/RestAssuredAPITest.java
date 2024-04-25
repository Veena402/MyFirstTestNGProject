package getUserList;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;
import org.testng.Assert;

import java.util.List;

public class RestAssuredAPITest
{
    @Test
    public void productDetails()
    {
        // Specify the base URL to the RESTful web service
//        RestAssured.baseURI = "https://dummyjson.com/products/1/";
//
//        // Get the RequestSpecification of the request to be sent to the server
//        RequestSpecification httpRequest = RestAssured.given();
//
//
//        Response response = httpRequest.get("");
//
//        // Get the status code of the request.
//        //If request is successful, status code will be 200
//        int statusCode = response.getStatusCode();
//
//        // Assert that correct status code is returned.
//        Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/,
//                "Correct status code returned");
//        System.out.println(statusCode);
//



        RestAssured.baseURI = "https://dummyjson.com/products/1/";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("products.title");
        ResponseBody body = response.getBody();
        System.out.println("the value is"+ body.asString());

//        String bodyasString =body.asString();
//        System.out.println(bodyasString);


        // Get all the headers and then iterate over allHeaders to print each header
//        Headers allHeaders = response.headers();
//        // Iterate over all the Headers
//        for(Header header : allHeaders)
//        {
//            System.out.println("Key: " + header.getName() + " Value: " + header.getValue());
//        }




    }
}

