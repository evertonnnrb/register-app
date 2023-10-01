package com.eitgh.registers.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.eitgh.registers.db.DbCore;
import com.eitgh.registers.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UsersRepository {
    private SQLiteDatabase db;

    public UsersRepository(Context context) {
        DbCore core = new DbCore(context);
        db = core.getWritableDatabase();
    }

    public void saveUser(User user) {
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("email", user.getEmail());
        values.put("password", user.getPassword());
        db.insert("user", null, values);
    }

    public User login(String name, String password) {
        String[] columns = new String[]{name, password};
        User user = null;
        Cursor cursor = db.query("user", columns, null, null, null, null, null);
        if (cursor.getCount()>0){
            user = new User(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
            cursor.close();
        }
        return user;
    }

    public List<User> find() {
        List<User> users = new ArrayList<>();
        String[] columns = new String[]{"_id", "name", "email", "password"};
        Cursor cursor = db.query("user", columns, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                User user = new User(cursor.getInt(0),
                        cursor.getString(1), cursor.getString(2), cursor.getString(3));

                users.add(user);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return users;
    }

    public void delete(User user) {
        db.delete("user", "_id" + user.getId(), new String[]{"" + user.getId()});
    }

    public void updateUser(User user) {
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("email", user.getEmail());
        values.put("password", user.getPassword());
        db.update("user", values, "_id = ?", new String[]{"" + user.getId()});
    }
}
