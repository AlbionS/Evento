package albion.shabani.channelmessaging.BDD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
/**
 * Created by shabania on 26/02/2018.
 */
public class FriendsDB extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "MyDB.db";
    public static final String USER_TABLE_NAME = "User";
    public static final String KEY_USERID = "userID";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_IMAGE_URL = "image_url";
    private static final String USER_TABLE_CREATE = "CREATE TABLE " + USER_TABLE_NAME + " (" + KEY_USERID + " INTEGER, " + KEY_USERNAME + " TEXT, " + KEY_IMAGE_URL + " TEXT);";


    public FriendsDB(Context context) {
        super(context, Environment.getExternalStorageDirectory() + "/" + DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USER_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        onCreate(db);
    }
}