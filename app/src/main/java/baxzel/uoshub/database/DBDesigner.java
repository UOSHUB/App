package baxzel.uoshub.database;


/**
 * Created by Muhammad Owais on 02-Dec-17.
 */


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBDesigner extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "uoshub.db";
    private static final int 	DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE_TABLE_DETAILS = "create table details "
            + "(studentID integer primary key,"
            + "major text not null,"
            + "college text not null,"
            + "firstName text not null,"
            + "lastName text not null);";

    private static final String DATABASE_CREATE_TABLE_UPDATES = "create table updates "
            + "(dismiss integer primary key,"
            + "title text not null,"
            + "course integer not null,"
            + "time text not null);";

    private static final String DATABASE_CREATE_TABLE_COURSES = "create table courses "
            + "(id text primary key,"
            + "title text not null,"
            + "doctor text not null,"
            + "email text,"
            + "days text not null,"
            + "start text not null,"
            + "end text not null,"
            + "location text);";

    private static final String DATABASE_CREATE_TABLE_EMAILS = "create table emails "
            + "(id text primary key,"
            + "title text not null,"
            + "sender text not null,"
            + "fromm text not null,"
            + "event text not null,"
            + "time text not null);";

    private static final String DATABASE_CREATE_TABLE_DEADLINES = "create table deadlines "
            + "(id integer primary key autoincrement,"
            + "title text not null,"
            + "course integer not null,"
            + "dueDate text not null,"
            + "time text not null);";

    private static final String DATABASE_CREATE_TABLE_CALENDAR = "create table calendar "
            + "(id integer primary key autoincrement,"
            + "text text not null,"
            + "date text not null);";

    private static final String DATABASE_CREATE_TABLE_GRADES = "create table grades "
            + "(id integer primary key autoincrement,"
            + "title text not null,"
            + "course integer not null,"
            + "outOf integer not null,"
            + "grade integer not null,"
            + "time text not null);";

    private static final String DATABASE_CREATE_TABLE_HOLDS = "create table holds "
            + "(id integer primary key autoincrement,"
            + "reason text not null,"
            + "type text not null,"
            + "start text not null,"
            + "end text not null);";

    public DBDesigner(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE_TABLE_DETAILS);
        database.execSQL(DATABASE_CREATE_TABLE_UPDATES);
        database.execSQL(DATABASE_CREATE_TABLE_COURSES);
        database.execSQL(DATABASE_CREATE_TABLE_EMAILS);
        database.execSQL(DATABASE_CREATE_TABLE_DEADLINES);
        database.execSQL(DATABASE_CREATE_TABLE_CALENDAR);
        database.execSQL(DATABASE_CREATE_TABLE_GRADES);
        database.execSQL(DATABASE_CREATE_TABLE_HOLDS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBDesigner.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS details");
        db.execSQL("DROP TABLE IF EXISTS updates");
        db.execSQL("DROP TABLE IF EXISTS courses");
        db.execSQL("DROP TABLE IF EXISTS emails");
        db.execSQL("DROP TABLE IF EXISTS deadlines");
        db.execSQL("DROP TABLE IF EXISTS calendar");
        db.execSQL("DROP TABLE IF EXISTS grades");
        db.execSQL("DROP TABLE IF EXISTS holds");
        onCreate(db);
    }
}
