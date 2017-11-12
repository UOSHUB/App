package baxzel.uoshub;

import android.support.v4.app.FragmentActivity;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;

public class Declutterer extends FragmentActivity{
    public static String URLHolder(String URLName){
        String URL = new String();
        if(URLName == "Updates")
            URL = "https://www.uoshub.com/api/updates/";
        else if(URLName == "Courses")
            URL = "https://www.uoshub.com/api/terms/201710/";
        else if(URLName == "Emails")
            URL = "https://www.uoshub.com/api/emails/personal";
        else if(URLName == "Deadlines")
            URL = "https://www.uoshub.com/api/updates/";
        else if(URLName == "Holds")
            URL = "https://www.uoshub.com/api/holds/";
        else if(URLName == "Calendar")
            URL = "https://www.uoshub.com/api/calendar/201720/";
        else if(URLName == "Grades")
            URL = "https://www.uoshub.com/api/grades/201710/";
        else if(URLName == "Login")
            URL = "https://www.uoshub.com/api/login/";
        else if(URLName == "Details")
            URL = "https://www.uoshub.com/api/details/";

        return URL;
    }

    public static void Cookier(){
        CookieManager cookieManager = new CookieManager(null, CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(cookieManager);
    }
}