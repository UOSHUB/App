package baxzel.uoshub.database;

/**
 * Created by Muhammad Owais on 03-Dec-17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class DBManager {

    private SQLiteDatabase database;
    private DBDesigner dbHelper;

    public DBManager(Context context) {
        dbHelper = new DBDesigner(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        database.close();
    }

    public void addUpdate(Integer dismiss, String title, String course, String time)
    {
        ContentValues values = new ContentValues();
        values.put("dismiss", dismiss);
        values.put("title", title);
        values.put("course", course);
        values.put("time", time);

        database.insert("updates", null, values);
    }

    public void addCourse(String id, String title, String doctor, String email,
                          String days, String start, String end, String location)
    {
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

    public void addEmail(String id, String title, String sender,
                         String from, String event, String time)
    {
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("title", title);
        values.put("sender", sender);
        values.put("from", from);
        values.put("event", event);
        values.put("time", time);

        database.insert("emails", null, values);
    }

    public void addDeadline(String title, Integer course, String dueDate,
                            String time)
    {
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("course", course);
        values.put("dueDate", dueDate);
        values.put("time", time);

        database.insert("deadlines", null, values);
    }

    public void addCal_entry(String text, String date)
    {
        ContentValues values = new ContentValues();
        values.put("text", text);
        values.put("date", date);

        database.insert("calendar", null, values);
    }

    public void addGrade(String title, Integer course, Integer outOf,
                         Integer grade, String time)
    {
        ContentValues values = new ContentValues();

        values.put("title", title);
        values.put("course", course);
        values.put("outOf", outOf);
        values.put("grade", grade);
        values.put("time", time);

        database.insert("grades", null, values);
    }

    public void addHold(String reason, String type, String start,
                        String end)
    {
        ContentValues values = new ContentValues();
        values.put("reason", reason);
        values.put("type", type);
        values.put("start", start);
        values.put("end", end);

        database.insert("holds", null, values);
    }

/*    public List<Donation> getAll() {
        List<Donation> donations = new ArrayList<Donation>();
        Cursor cursor = database.rawQuery("SELECT * FROM donations", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Donation d = toDonation(cursor);
            donations.add(d);
            cursor.moveToNext();
        }
        cursor.close();
        return donations;
    }*/

/*    private Donation toDonation(Cursor cursor) {
        Donation pojo = new Donation();
        pojo.id = cursor.getInt(0);
        pojo.amount = cursor.getInt(1);
        pojo.method = cursor.getString(2);
        return pojo;
    }

    public void setTotalDonated(Base base) {
        Cursor c = database.rawQuery("SELECT SUM(amount) FROM donations", null);
        c.moveToFirst();
        if (!c.isAfterLast())
            base.app.totalDonated = c.getInt(0);
    }*/

    public void reset() {
        database.delete("donations", null, null);
    }
}
