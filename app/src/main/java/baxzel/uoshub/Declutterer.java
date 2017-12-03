package baxzel.uoshub;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;

public class Declutterer{
    public static String URLHolder(String URLName){
        String URL = null;
        switch(URLName){
            case "Login":
                URL = "https://www.uoshub.com/api/login/";
                break;
            case "Updates":
                URL = "https://www.uoshub.com/api/updates/";
                break;
            case "Courses":
                URL = "https://www.uoshub.com/api/terms/201710/";
                break;
            case "Emails":
                URL = "https://www.uoshub.com/api/emails/personal";
                break;
            case "Deadlines":
                URL = "https://www.uoshub.com/api/updates/";
                break;
            case "Calendar":
                URL = "https://www.uoshub.com/api/calendar/201720/";
                break;
            case "Grades":
                URL = "https://www.uoshub.com/api/grades/201710/";
                break;
            case "Holds":
                URL = "https://www.uoshub.com/api/holds/";
                break;
            case "Details":
                URL = "https://www.uoshub.com/api/details/";
                break;
        }
        return URL;
    }

    static void Cookier(){
        CookieManager cookieManager = new CookieManager(null, CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(cookieManager);
    }
}