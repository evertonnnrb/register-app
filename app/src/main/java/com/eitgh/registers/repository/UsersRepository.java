package com.eitgh.registers.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.eitgh.registers.db.DbCore;
import com.eitgh.registers.entities.User;
import com.eitgh.registers.exceptions.ErrorLoginException;

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

    public User login(String email, String password) {
        String[] columns = new String[]{email, password};
        User user = null;
        String sql = "select * from user where email = ? and password = ?";
        try {
            Cursor cursor = db.rawQuery(sql, new String[]{email, password});
            if (cursor.moveToFirst()) {
                if (email.equals(cursor.getString(2))) {
                    if (password.equals(cursor.getString(3))) {
                        user = new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                    }
                }
            }
        } catch (ErrorLoginException e) {
            e.getMessage();
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
