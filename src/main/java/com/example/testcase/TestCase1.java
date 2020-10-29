package com.example.testcase;

import com.example.commonutil.Constant;
import com.example.commonutil.HeadersData;
import com.example.config.BaseAPI;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.testng.annotations.Test;

public class TestCase1 extends BaseAPI {

    public Response response;

    public TestCase1() {
    }

    //@Test(priority = 1)
    public boolean validateGIAMSendboxOAuth(){
        boolean flag = false;
        try {
            //1 -	GIAM Sandbox OAuth call
            Response response1 =  getResponse("", HeadersData.OAuthCallHeaders());
            String flowID = response1.getHeader("Content-Type").split("=")[1];

            //2 -  Initial GET with FlowID Copy
            Response response2 =  getResponse(""+flowID, HeadersData.flowIDHeaders());

            //3 -  POST with user credentials Copy
            Response response3 =  postData(""+flowID, "POST", Constant.user_credentials_copy,HeadersData.OAuthCallHeaders());

            //4 -  Initial GET with FlowID Copy
            Response response4 =  getResponse(""+flowID+"", HeadersData.flowIDHeaders());
            Document document = Jsoup.parse(response4.getBody().toString());
            Element ele = document.select("input").first();
            String code = ele.attr("value");

            //5 -  Exchange Auth code for access token
            Response response5 =  postData(""+code, "POST", "",HeadersData.authCodeAcceessTokenHeaders(code));


        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Test
    public void test2(){
        try {
            //1 -	GIAM Sandbox OAuth call
/*
            Response response1 =  getResponse("users?page=2",HeadersData.common());
            String flowID = response1.getHeader("Content-Type").split("=")[1];
            System.out.println("flowID"+flowID);

            //2 -  Initial GET with FlowID Copy
            Response response2 =  getResponse(""+flowID, HeadersData.flowIDHeaders());
*/
            //3 -  POST with user credentials Copy
            Response response3 =  postData("/users", "POST", "{\n" +
                    "    \"username\": \"sadik.maksudov@first.com\",\n" +
                    "    \"password\": \"Welcome123\"\n" +
                    "}",HeadersData.common());

            response3.getBody().print();

       /*     //4 -  Initial GET with FlowID Copy
            Response response4 =  getResponse(""+flowID+"", HeadersData.flowIDHeaders());
            Document document = Jsoup.parse(response4.getBody().toString());
            Element ele = document.select("input").first();
            String code = ele.attr("value");

            //5 -  Exchange Auth code for access token
            Response response5 =  postData(""+code, "POST", "",HeadersData.authCodeAcceessTokenHeaders(code));
*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
