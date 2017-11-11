package baxzel.uoshub;

import android.support.v4.app.FragmentActivity;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;

import baxzel.uoshub.layout.CalendarFragment;
import baxzel.uoshub.layout.GradesFragment;
import baxzel.uoshub.layout.HoldsFragment;

public class Declutterer extends FragmentActivity{
    public String URLHolder(String URLName){
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

        return URL;
    }

    public static void Cookier(){
        CookieManager cookieManager = new CookieManager(null, CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(cookieManager);
    }

    boolean mCalendarBoolean, mGradesBoolean, mHoldsBoolean;

    public void setCalendarFragment(boolean theCalendarBoolean){
        mCalendarBoolean = theCalendarBoolean;
    }

    public boolean getCalendarFragment(){
        return mCalendarBoolean;
    }

    public void setGradesFragment(boolean theGradesBoolean){
        mGradesBoolean = theGradesBoolean;
    }

    public boolean getGradesFragment(){
        return mGradesBoolean;
    }

    public void setHoldsFragment(boolean theHoldsBoolean){
        mHoldsBoolean = theHoldsBoolean;
    }

    public boolean getHoldsFragment(){
        return mHoldsBoolean;
    }

    public void theThinger(){
        if(mCalendarBoolean == true){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2, new CalendarFragment()).commit();
            new Declutterer().setGradesFragment(false);
            new Declutterer().setHoldsFragment(false);
        }
        if(mGradesBoolean == true){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2, new GradesFragment()).commit();
            new Declutterer().setCalendarFragment(false);
            new Declutterer().setHoldsFragment(false);
        }
        else if (mHoldsBoolean == true){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2, new HoldsFragment()).commit();
            new Declutterer().setCalendarFragment(false);
            new Declutterer().setGradesFragment(false);
        }
    }
}