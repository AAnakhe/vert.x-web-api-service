package com.ajavacode.server.util;


import java.util.Arrays;

public class UrlUtil {

    public static String url(String... url) {
        if (url.length > 1) {
            return "/v1" + String.join("/", url);
        } else {
            return "/v1/" + url[0];
        }
    }

}
