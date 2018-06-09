package albion.shabani.channelmessaging.BDD;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import albion.shabani.channelmessaging.BDD.FriendsDB;
import albion.shabani.channelmessaging.Clases.User;

/**
 * Created by shabania on 26/02/2018.
 */


public class UserDataSource {
    // Database fields
    private SQLiteDatabase database;
    private FriendsDB dbHelper;
    private String[] allColumns = {FriendsDB.KEY_USERID, FriendsDB.KEY_USERNAME, FriendsDB.KEY_IMAGE_URL};

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public UserDataSource(Context context) {
        dbHelper = new FriendsDB(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public User createUser(String username, String image_url) {
        ContentValues values = new ContentValues();
        values.put(FriendsDB.KEY_USERNAME, username);
        values.put(FriendsDB.KEY_IMAGE_URL, image_url);
        UUID newID = UUID.randomUUID();
        values.put(FriendsDB.KEY_USERID, newID.toString());
        database.insert(FriendsDB.USER_TABLE_NAME, null, values);
        Cursor cursor = database.query(FriendsDB.USER_TABLE_NAME, allColumns, FriendsDB.KEY_USERID + " = \"" + newID.toString() + "\"", null, null, null, null);
        cursor.moveToFirst();
        User newUser = cursorToUser(cursor);
        cursor.close();
        return newUser;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        Cursor cursor = database.query(FriendsDB.USER_TABLE_NAME, allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            User aUser = cursorToUser(cursor);
            users.add(aUser);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return users;
    }

    private User cursorToUser(Cursor cursor) {
        User comment = new User();
        String result = cursor.getString(0);
        comment.setUserID(UUID.fromString(result));
        comment.setUsername(cursor.getString(1));
        comment.setImage_url(cursor.getString(2));
        return comment;
    }

    public void deleteUser(User aUser) {
        UUID id = aUser.getUserID();
        database.delete(FriendsDB.USER_TABLE_NAME, FriendsDB.KEY_USERID + " = \"" + id.toString() + "\"", null);
    }


}
