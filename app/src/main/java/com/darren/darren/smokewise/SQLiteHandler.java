package com.darren.darren.smokewise;

/**
 * Created by Darren on 29-Jan-16.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.android.gms.wallet.LineItem;

import java.util.HashMap;

public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "android_api";

    // Login table name
    private static final String TABLE_USER = "user";

    private static final String TABLE_USER_CATEGORY = "user_category";

    // Login Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_UID = "uid";
    private static final String KEY_AGE =  "age";
    private static final String KEY_GENDER =  "gender";
    private static final String KEY_DAILY_SMOKES =  "dailySmokes";
    private static final String KEY_STOP_ATTEMPTS =  "stopAttempts";
    private static final String KEY_ETHNICITY =  "ethnicity";
    private static final String KEY_OCCUPATION =  "occupation";

    private static final String KEY_CREATED_AT = "created_at";


    private static final String KEY_HEALTH = "health";
    private static final String KEY_APPEARANCE = "appearance";
    private static final String KEY_FAMILY =  "family";
    private static final String KEY_ROLE =  "role";
    private static final String KEY_EMPLOYMENT =  "employment";
    private static final String KEY_FINANCIAL =  "financial";
    private static final String KEY_SOCIAL =  "social";
    private static final String KEY_NICOTINE =  "nicotine";
    private static final String KEY_OTHER = "other";
    private static final String KEY_DATE_QUIT = "dateQuit";


    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_EMAIL + " TEXT UNIQUE,"
                + KEY_UID + " TEXT,"
                + KEY_DATE_QUIT + " TEXT,"
                + KEY_AGE + " TEXT,"
                + KEY_GENDER + " TEXT,"
                + KEY_DAILY_SMOKES + " TEXT,"
                + KEY_STOP_ATTEMPTS + " TEXT,"
                + KEY_ETHNICITY + " TEXT,"
                + KEY_OCCUPATION + " TEXT,"
                + KEY_CREATED_AT + " TEXT" + ")";
        db.execSQL(CREATE_LOGIN_TABLE);

        Log.d(TAG, "Database tables created");

        String CREATE_CATEGORY_TABLE = "CREATE TABLE " + TABLE_USER_CATEGORY + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_EMAIL + " TEXT UNIQUE,"
                + KEY_HEALTH + " TEXT,"
                + KEY_UID + " TEXT,"
                + KEY_APPEARANCE + " APPEARANCE,"
                + KEY_FAMILY + " FAMILY,"
                + KEY_ROLE + " ROLE,"
                + KEY_EMPLOYMENT + " EMPLOYMENT,"
                + KEY_FINANCIAL + " FINANCIAL,"
                + KEY_SOCIAL + " SOCIAL,"
                + KEY_NICOTINE + " NICOTINE,"
                + KEY_OTHER + " OTHER,"
                + KEY_CREATED_AT + " TEXT" + ")";
        db.execSQL(CREATE_CATEGORY_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        // Create tables again
        onCreate(db);
    }

    /**
     * Storing user details in database
     */
/*    public void addUser(String name, String email, String uid, String created_at) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name); // Name
        values.put(KEY_EMAIL, email); // Email
        values.put(KEY_UID, uid); // Unique ID
        values.put(KEY_CREATED_AT, created_at); // Created At

        // Inserting Row
        long id = db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New user inserted into sqlite: " + id);
    }*/

    public void addUser(String name, String email, String uid, String dateQuit, String age, String gender, String dailySmokes, String stopAttempts, String ethnicity, String occupation, String created_at) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name); // Name
        values.put(KEY_EMAIL, email); // Email
        values.put(KEY_UID, uid); // Unique ID
        values.put(KEY_DATE_QUIT, dateQuit);
        values.put(KEY_AGE, age);
        values.put(KEY_GENDER, gender);
        values.put(KEY_DAILY_SMOKES, dailySmokes);
        values.put(KEY_STOP_ATTEMPTS, stopAttempts);
        values.put(KEY_ETHNICITY, ethnicity);
        values.put(KEY_OCCUPATION, occupation);
        values.put(KEY_CREATED_AT, created_at); // Created At

        // Inserting Row
        long id = db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New user inserted into sqlite: " + id);
    }

    public void addUserCategory(String email, String health, String appearance, String family, String role, String employment, String financial, String social,
                                String nicotine, String other, String created_at) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EMAIL, email); // Email
        //values.put(KEY_UID, uid); // Unique ID
        values.put(KEY_HEALTH, health);
        values.put(KEY_APPEARANCE, appearance);
        values.put(KEY_FAMILY, family);
        values.put(KEY_ROLE, role);
        values.put(KEY_EMPLOYMENT, employment);
        values.put(KEY_FINANCIAL, financial);
        values.put(KEY_SOCIAL, social);
        values.put(KEY_NICOTINE, nicotine);
        values.put(KEY_OTHER, other);
        values.put(KEY_CREATED_AT, created_at); // Created At

        // Inserting Row
        //long id = db.insert(TABLE_USER_CATEGORY, null, values);
        db.close(); // Closing database connection

        //Log.d(TAG, "New user inserted into sqlite: " + id);
    }

    /**
     * Getting user data from database
     */
    public HashMap<String, String> getUserCategoryDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER_CATEGORY;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("email", cursor.getString(1));
            user.put("uid", cursor.getString(2));
            user.put("health", cursor.getString(3));
            user.put("appearance", cursor.getString(4));
            user.put("family", cursor.getString(5));
            user.put("role", cursor.getString(6));
            user.put("employment", cursor.getString(7));
            user.put("financial", cursor.getString(8));
            user.put("social", cursor.getString(9));
            user.put("nicotine", cursor.getString(10));
            user.put("other", cursor.getString(11));
            user.put("created_at", cursor.getString(12));
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + user.toString());

        return user;
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("name", cursor.getString(1));
            user.put("email", cursor.getString(2));
            user.put("uid", cursor.getString(3));
            user.put("age", cursor.getString(4));
            user.put("gender", cursor.getString(5));
            user.put("dailySmokes", cursor.getString(6));
            user.put("stopAttempts", cursor.getString(7));
            user.put("ethnicity", cursor.getString(8));
            user.put("occupation", cursor.getString(9));
            user.put("created_at", cursor.getString(10));
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + user.toString());

        return user;
    }

    /**
     * Re crate database Delete all tables and create them again
     */
    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_USER, null, null);
        db.close();

        Log.d(TAG, "Deleted all user info from sqlite");
    }

}