package baxzel.uoshub.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DBManager{
    private SQLiteDatabase database;
    private DBDesigner dbHelper;

    public DBManager(Context context){
        dbHelper = new DBDesigner(context);
    }

    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        database.close();
    }

    public void addDetail(Integer studentID, String major, String college, String firstName, String lastName){
        ContentValues values = new ContentValues();
        values.put("studentID", studentID);
        values.put("major", major);
        values.put("college", college);
        values.put("firstName", firstName);
        values.put("lastName", lastName);

        database.insert("details", null, values);
    }

    public void addDetails(JSONArray details){
        try{
            JSONObject item = details.getJSONObject(0);
            addDetail(
                    item.getInt("studentID"),
                    item.getString("major"),
                    item.getString("college"),
                    item.getString("firstName"),
                    item.getString("lastName")
            );
        } catch(JSONException e){
            e.printStackTrace();
        }
    }

    public JSONArray getDetails(){
        JSONArray Details = new JSONArray();
        String mQuery = "SELECT * From details";
        Cursor mCur = database.rawQuery(mQuery, new String[]{});
        if(mCur.moveToFirst())
            do{
                JSONObject tuple = new JSONObject();

                String studentID = mCur.getString(0);
                String major = mCur.getString(1);
                String college = mCur.getString(2);
                String firstName = mCur.getString(3);
                String lastName = mCur.getString(4);

                try{
                    tuple.put("studentID", studentID);
                    tuple.put("major", major);
                    tuple.put("college", college);
                    tuple.put("firstName", firstName);
                    tuple.put("lastName", lastName);
                } catch(JSONException e){
                    e.printStackTrace();
                }
                Details.put(tuple);
            } while (mCur.moveToNext());

        return Details;
    }

    public void addUpdate(Integer dismiss, String title, String course, String time){
        ContentValues values = new ContentValues();
        values.put("dismiss", dismiss);
        values.put("title", title);
        values.put("course", course);
        values.put("time", time);

        database.insert("updates", null, values);
    }

    public void addUpdates(JSONArray updates){
        for(int i = 0; i < updates.length(); i++)
            try{
                JSONObject item = updates.getJSONObject(i);
                addUpdate(
                    item.getInt("dismiss"),
                    item.getString("title"),
                    item.getString("course"),
                    item.getString("time")
                );
            } catch(JSONException e){
                e.printStackTrace();
            }
    }

    public JSONArray getUpdates(){
        JSONArray Updates = new JSONArray();
        String mQuery = "SELECT * From updates";
        Cursor mCur = database.rawQuery(mQuery, new String[]{});
        if(mCur.moveToFirst())
            do{
                JSONObject tuple = new JSONObject();

                String dismiss = mCur.getString(0);
                String title = mCur.getString(1);
                String course = mCur.getString(2);
                String time = mCur.getString(3);

                try{
                    tuple.put("dismiss",dismiss);
                    tuple.put("title", title);
                    tuple.put("course", course);
                    tuple.put("time", time);
                } catch(JSONException e){
                    e.printStackTrace();
                }

                Updates.put(tuple);
            }
            while (mCur.moveToNext());

        return Updates;
    }

    public void addCourse(String id, String title, String doctor, String email, String days, String start, String end, String location){
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("title", title);
        values.put("doctor", doctor);
        values.put("email", email);
        values.put("days", days);
        values.put("start", start);
        values.put("end", end);
        values.put("location", location);

        database.insert("courses", null, values);
    }

    public void addCourses(JSONArray courses){
        for(int i = 0; i < courses.length(); i++)
            try{
                JSONObject item = courses.getJSONObject(i);
                addCourse(
                        item.getString("id"),
                        item.getString("title"),
                        item.getString("doctor"),
                        item.getString("email"),
                        item.getString("days"),
                        item.getString("start"),
                        item.getString("end"),
                        item.getString("location")
                );
            } catch(JSONException e){
                e.printStackTrace();
            }
    }

    public JSONArray getCourses(){
        JSONArray Courses = new JSONArray();
        String mQuery = "SELECT * From courses";
        Cursor mCur = database.rawQuery(mQuery, new String[]{});
        if(mCur.moveToFirst())
            do{
                JSONObject tuple = new JSONObject();

                String id = mCur.getString(0);
                String title = mCur.getString(1);
                String doctor = mCur.getString(2);
                String email = mCur.getString(3);
                String days = mCur.getString(4);
                String start = mCur.getString(5);
                String end = mCur.getString(6);
                String location = mCur.getString(7);

                try{
                    tuple.put("id", id);
                    tuple.put("title", title);
                    tuple.put("doctor", doctor);
                    tuple.put("email", email);
                    tuple.put("days", days);
                    tuple.put("start", start);
                    tuple.put("end", end);
                    tuple.put("location", location);
                } catch(JSONException e){
                    e.printStackTrace();
                }

                Courses.put(tuple);
            } while (mCur.moveToNext());

        return Courses;
    }

    public void addEmail(String id, String title, String sender, String from, String event, String time){
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("title", title);
        values.put("sender", sender);
        values.put("fromm", from);
        values.put("event", event);
        values.put("time", time);

        database.insert("emails", null, values);
    }

    public void addEmails(JSONArray emails){
        for(int i = 0; i < emails.length(); i++)
            try{
                JSONObject item = emails.getJSONObject(i);
                addEmail(
                    item.getString("id"),
                    item.getString("title"),
                    item.getString("sender"),
                    item.getString("from"),
                    item.getString("event"),
                    item.getString("time")
                );
            } catch(JSONException e){
                e.printStackTrace();
            }
    }

    public JSONArray getEmails(){
        JSONArray Emails = new JSONArray();
        String mQuery = "SELECT * From emails";
        Cursor mCur = database.rawQuery(mQuery, new String[]{});
        if(mCur.moveToFirst())
            do{
                JSONObject tuple = new JSONObject();

                String title = mCur.getString(1);
                String sender = mCur.getString(2);
                String from = mCur.getString(3);
                String event = mCur.getString(4);
                String time = mCur.getString(5);

                try{
                    tuple.put("title", title);
                    tuple.put("sender", sender);
                    tuple.put("from", from);
                    tuple.put("event", event);
                    tuple.put("time", time);
                } catch(JSONException e){
                    e.printStackTrace();
                }

                Emails.put(tuple);
            } while (mCur.moveToNext());

        return Emails;
    }

    public void addDeadline(String title, Integer course, String dueDate, String time){
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("course", course);
        values.put("dueDate", dueDate);
        values.put("time", time);

        database.insert("deadlines", null, values);
    }

    public void addDeadlines(JSONArray deadlines){
        for(int i = 0; i < deadlines.length(); i++)
            try{
                JSONObject item = deadlines.getJSONObject(i);
                addDeadline(
                    item.getString("title"),
                    item.getInt("course"),
                    item.getString("dueDate"),
                    item.getString("time")
                );
            } catch(JSONException e){
                e.printStackTrace();
            }
    }

    public JSONArray getDeadlines(){
        JSONArray Deadlines = new JSONArray();
        String mQuery = "SELECT * From deadlines";
        Cursor mCur = database.rawQuery(mQuery, new String[]{});
        if(mCur.moveToFirst())
            do{
                JSONObject tuple = new JSONObject();

                String title = mCur.getString(1);
                String course = mCur.getString(2);
                String dueDate = mCur.getString(3);
                String time = mCur.getString(4);

                try{
                    tuple.put("title", title);
                    tuple.put("course", course);
                    tuple.put("dueDate", dueDate);
                    tuple.put("time", time);
                } catch(JSONException e){
                    e.printStackTrace();
                }

                Deadlines.put(tuple);
            } while(mCur.moveToNext());

        return Deadlines;
    }

    public void addCal_entry(String text, String date){
        ContentValues values = new ContentValues();
        values.put("text", text);
        values.put("date", date);

        database.insert("calendar", null, values);
    }

    public void addCal_entries(JSONArray calendar){
        for(int i = 0; i < calendar.length(); i++)
            try{
                JSONObject item = calendar.getJSONObject(i);
                addCal_entry(
                    item.getString("text"),
                    item.getString("date")
                );
            } catch (JSONException e) {
                e.printStackTrace();
            }
    }

    public JSONArray getCalendar(){
        JSONArray Calendar = new JSONArray();
        String mQuery = "SELECT * From calendar";
        Cursor mCur = database.rawQuery(mQuery, new String[]{});
        if(mCur.moveToFirst())
            do{
                JSONObject tuple = new JSONObject();

                String text = mCur.getString(1);
                String date = mCur.getString(2);

                try{
                    tuple.put("text", text);
                    tuple.put("date", date);
                } catch(JSONException e){
                    e.printStackTrace();
                }

                Calendar.put(tuple);
            } while (mCur.moveToNext());

        return Calendar;
    }

    public void addGrade(String title, Integer course, Integer outOf, Integer grade, String time){
        ContentValues values = new ContentValues();

        values.put("title", title);
        values.put("course", course);
        values.put("outOf", outOf);
        values.put("grade", grade);
        values.put("time", time);

        database.insert("grades", null, values);
    }

    public void addGrades(JSONArray grades){
        for(int i = 0; i < grades.length(); i++)
            try{
                JSONObject item = grades.getJSONObject(i);
                addGrade(
                        item.getString("title"),
                        item.getInt("course"),
                        item.getInt("outOf"),
                        item.getInt("grade"),
                        item.getString("time")
                );
            } catch (JSONException e) {
                e.printStackTrace();
            }
    }

    public JSONArray getGrades(){
        JSONArray Grades = new JSONArray();
        String mQuery = "SELECT * From grades";
        Cursor mCur = database.rawQuery(mQuery, new String[]{});
        if(mCur.moveToFirst())
            do{
                JSONObject tuple = new JSONObject();

                String title = mCur.getString(1);
                String course = mCur.getString(2);
                String outOf = mCur.getString(3);
                String grade = mCur.getString(4);
                String time = mCur.getString(5);

                try {
                    tuple.put("title", title);
                    tuple.put("course", course);
                    tuple.put("outOf", outOf);
                    tuple.put("grade", grade);
                    tuple.put("time", time);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Grades.put(tuple);
            } while (mCur.moveToNext());

        return Grades;
    }

    public void addHold(String reason, String type, String start, String end){
        ContentValues values = new ContentValues();
        values.put("reason", reason);
        values.put("type", type);
        values.put("start", start);
        values.put("end", end);

        database.insert("holds", null, values);
    }

    public void addHolds(JSONArray holds) {
        for(int i = 0; i < holds.length(); i++)
            try {
                JSONObject item = holds.getJSONObject(i);
                addHold(
                        item.getString("reason"),
                        item.getString("type"),
                        item.getString("start"),
                        item.getString("end")
                );
            } catch (JSONException e) {
                e.printStackTrace();
            }
    }

    public JSONArray getHolds(){
        JSONArray Holds = new JSONArray();
        String mQuery = "SELECT * From holds";
        Cursor mCur = database.rawQuery(mQuery, new String[]{});
        if(mCur.moveToFirst())
            do {
                JSONObject tuple = new JSONObject();

                String reason = mCur.getString(1);
                String type = mCur.getString(2);
                String start = mCur.getString(3);
                String end = mCur.getString(4);

                try {
                    tuple.put("reason", reason);
                    tuple.put("type", type);
                    tuple.put("start", start);
                    tuple.put("end", end);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Holds.put(tuple);
            } while (mCur.moveToNext());

        return Holds;
    }

    public void reset(){
        database.delete("donations", null, null);
    }
}