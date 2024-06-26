package test;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.support;

import static io.restassured.RestAssured.get;

public class WebAPI
{

    String testname=null;
    String url,statuscode,title;
    support obj;

    @BeforeClass
    public void setUp(){

        obj=new support();
    }
    @Test
    public void do_All_Web_App_Test(){

        // Validate Google Application ...

        testname = "TC001";
        float f;
        int val;
        url = obj.read_And_Print_XL_AsPerTestData(testname,"RequestURL").trim();
        statuscode = obj.read_And_Print_XL_AsPerTestData(testname,"Status Code");
        f = Float.parseFloat(statuscode);
        val = (int)f;
        statuscode = String.valueOf(val);
        System.out.println("The XL Status Code =="+statuscode);
        title = obj.read_And_Print_XL_AsPerTestData(testname,"Title").trim();
        Do_Web_API_Validation();

        // Validate GeekForGeeks Application ...

        testname = "TC002";
        url = obj.read_And_Print_XL_AsPerTestData(testname,"RequestURL").trim();
        statuscode = obj.read_And_Print_XL_AsPerTestData(testname,"Status Code");
        f = Float.parseFloat(statuscode);
        val = (int)f;
        statuscode = String.valueOf(val);
        System.out.println("The XL Status Code =="+statuscode);
        title = obj.read_And_Print_XL_AsPerTestData(testname,"Title").trim();
        Do_Web_API_Validation();

        // Validate W3Schools Application ...

        testname = "TC003";
        url = obj.read_And_Print_XL_AsPerTestData(testname,"RequestURL").trim();
        statuscode = obj.read_And_Print_XL_AsPerTestData(testname,"Status Code");
        f = Float.parseFloat(statuscode);
        val = (int)f;
        statuscode = String.valueOf(val);
        System.out.println("The XL Status Code =="+statuscode);
        title = obj.read_And_Print_XL_AsPerTestData(testname,"Title").trim();
        Do_Web_API_Validation();


    }
    public void Do_Web_API_Validation(){

        Response response = get(url);
        String res = response.getBody().asString();
        // First get the count of node you want to test ...
        Assert.assertEquals(String.valueOf(response.getStatusCode()),statuscode);
        Assert.assertTrue(res.contains(title));


    }
}

