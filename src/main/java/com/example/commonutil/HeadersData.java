package com.example.commonutil;

import java.util.HashMap;
import java.util.Map;

public class HeadersData {

    public static Map<String, String> OAuthCallHeaders(){
        Map<String, String> header = new HashMap<String, String>();
        header.put("response_mode","form_post");
        header.put("response_type","code");
        header.put("redirect_uri","https://localhost:1500/login");
        header.put("client_secret","giam");
        header.put("client_id","GiamSandbox");
        return header;
    }

    public static Map<String, String> authCodeAcceessTokenHeaders(String code){
        Map<String, String> header = new HashMap<String, String>();
        header.put("grant_type","autorization_code");
        header.put("redirect_uri","https://localhost:1500/login");
        header.put("code",code);
        return header;
    }

    public static Map<String, String> flowIDHeaders(){
        Map<String, String> header = new HashMap<String, String>();
        header.put("X-XSRF-Header","PingFederate");
        return header;
    }

    public static Map<String, String> common(){
        Map<String, String> header = new HashMap<String, String>();
        header.put("Content-Type","application/json;");
        return header;
    }
}
