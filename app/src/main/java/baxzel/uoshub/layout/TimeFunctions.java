package baxzel.uoshub.layout;

import java.util.Date;

/**
 * Created by Muhammad Owais on 29-Nov-17.
 */

public class TimeFunctions {
    public static String time_ago(Date date) {

        String period = "";
        Date now = new Date();

        String direction = "ago";
        Long time = (now.getTime() - date.getTime()) / 86400000;
        if(time < 0) {
            if(now.getDate() == date.getDate())
                return "Today";
            if(now.getDate() + 1 == date.getDate())
                return "Tomorrow";
            period = "day";
            direction = "left";
        } else if(time >= 1)
            period = "day";
        else {
            time *= 24;
            if(time >= 1)
                period = "hour";
            else {
                time *= 60;
                period = "min";
            }
        }
        time = Math.abs(time);
        if(time > 1) period += "s";
        return time+" "+period+" "+direction;
    };

};