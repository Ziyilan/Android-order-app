package bill.cookapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import layout.ItemDbSchema;

public class DBService {
    ItemDbSchema db;

    public DBService(Context context) {
        db = new ItemDbSchema(context);
    }

    public void addMenuItem(MenuItem item) {
        SQLiteDatabase sql = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ItemDbSchema.NAME_TITLE, item.getItemName());
        values.put(ItemDbSchema.STATUS_TITLE, item.getStatus());
        try {
            sql.insertOrThrow(ItemDbSchema.TABLE_NAME, ItemDbSchema.NAME_TITLE, values);
        } finally {
            sql.close();
        }
    }

    public void deleteMenuItem (MenuItem task) {
        SQLiteDatabase sql = db.getWritableDatabase();
        String selection = ItemDbSchema.ID_TITLE + " =?";
        String[] selectionArgs = {String.valueOf(task.getId())};
        sql.delete(ItemDbSchema.TABLE_NAME, selection, selectionArgs);
        sql.close();
    }

    public ArrayList<MenuItem> getAll() {
        ArrayList<MenuItem> taskArray = new ArrayList<>();
        SQLiteDatabase sql = db.getReadableDatabase();


        Cursor c = sql.rawQuery("select * from " + ItemDbSchema.TABLE_NAME, null);

        c.moveToFirst();

        while(!c.isAfterLast()) {
            long readID = c.getLong(0);
            String readName = c.getString(1);
            int readStatus = c.getInt(2);
            MenuItem taskInput = new MenuItem(readName, readStatus);
            taskInput.setId(readID);
            taskArray.add(taskInput);

            c.moveToNext();
        }

        sql.close();
        return taskArray;
    }

    public ArrayList<MenuItem> updateMenuItem(MenuItem task) {
        ArrayList<MenuItem> taskArray = new ArrayList<>();
        SQLiteDatabase sql = db.getWritableDatabase();

        StringBuilder sb = new StringBuilder();
        sb.append("update ");
        sb.append(ItemDbSchema.TABLE_NAME);
        sb.append(" set ");
        sb.append(ItemDbSchema.NAME_TITLE);
        sb.append("='");
        sb.append(task.getItemName());
        sb.append("', ");
        sb.append(ItemDbSchema.STATUS_TITLE);
        sb.append("=");
        sb.append(task.getStatus());
        sb.append(" where ");
        sb.append(ItemDbSchema.ID_TITLE);
        sb.append("=");
        sb.append(task.getId());

        Cursor c = sql.rawQuery(sb.toString(), null);

        c.moveToFirst();
        c.close();



        return taskArray;
    }




}