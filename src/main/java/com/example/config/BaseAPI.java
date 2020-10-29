package com.example.config;

import com.example.commonutil.Constant;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.ITestResult;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.Map;

public class BaseAPI {

    public RequestSpecification request;
    public Response response;

    public BaseAPI(){}

    @BeforeClass
    public void testStart() throws Exception{
        RestAssured.baseURI = Constant.baseURL;
        request = RestAssured.given();
    }

    public static String inputStreamToString(InputStream is) throws IOException {
        String line;
        StringBuilder total = new StringBuilder();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        while ((line = rd.readLine()) != null) {
            total.append(line);
        }
        return total.toString();
    }

    public Response getResponse(String uri, Map<String, String> header) throws Exception{
        return request.headers(header).get(uri);
    }

    public Response postData(String uri, String method, String body, Map<String, String> header) throws Exception{
        if ("PUT".equals(method.toUpperCase())) {
            response = request.headers(header).body(body).put(uri);
        } else if ("DELETE".equals(method.toUpperCase())) {
            response = request.headers(header).body(body).delete(uri);
        } else {
            response = request.headers(header).body(body).post(uri);
        }
        return response;
    }

    public static String response(int responseCode){
        String response = "";
        switch (responseCode) {
            case 200:
                response = "200 - Success";
                break;
            case 400:
                response = "400 - Bad Request";
                break;
            case 401:
                response = "401 - Unauthorized";
                break;
            case 403:
                response = "403 - Forbidden";
                break;
            case 404:
                response = "404 - Not Found";
                break;
            case 405:
                response = "405 - Method Not Allowed";
                break;
            case 406:
                response = "406 - Not Acceptable";
                break;
            case 500 :
                response = "500 - Internal Server Error";
                break;
            case 501:
                response = "501 - Not Implemented";
                break;
            case 412:
                response = "412 - Precondition Failed";
                break;
            case 415:
                response = "415 - Unsupported Media Type";
                break;
            case 301:
                response = "301 - Moved Permanently";
                break;
            default:
                response = "000 - Fail";
                break;
        }
        return response.toUpperCase();
    }
}
