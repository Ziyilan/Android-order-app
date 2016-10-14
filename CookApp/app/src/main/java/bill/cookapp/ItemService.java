package bill.cookapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.ArrayList;
import layout.ItemDbSchema;

public class ItemService {
    ItemDbSchema db;

    public ItemService(Context context) {
        db = new ItemDbSchema(context);
    }

    // add new food item to database
    public void addMenuItem(MenuItem item) {
        SQLiteDatabase sql = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ItemDbSchema.FOOD_TITLE, item.getItemName());
        values.put(ItemDbSchema.INGREDIENT_TITLE, item.getIngredients().toString());
        values.put(ItemDbSchema.AMOUNT_TITLE, item.getAmount().toString());
        try {
            sql.insertOrThrow(ItemDbSchema.TABLE_NAME, ItemDbSchema.FOOD_TITLE, values);
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        } finally {
            sql.close();
        }
    }

    // delete food item from database
    public void deleteMenuItem (MenuItem task) {
        SQLiteDatabase sql = db.getWritableDatabase();
        String selection = ItemDbSchema.ID_TITLE + " =?";
        String[] selectionArgs = {String.valueOf(task.getId())};
        sql.delete(ItemDbSchema.TABLE_NAME, selection, selectionArgs);
        sql.close();
    }

    // return all food items from database
    public ArrayList<MenuItem> getAll() {
        ArrayList<MenuItem> taskArray = new ArrayList<>();
        SQLiteDatabase sql = db.getReadableDatabase();
        Cursor c = sql.rawQuery("select * from " + ItemDbSchema.TABLE_NAME, null);
        c.moveToFirst();
        while(!c.isAfterLast()) {
            long readID = c.getLong(0);
            String readFood = c.getString(1);
            String readIngredient = c.getString(2);
            String readAmount = c.getString(3);
            MenuItem taskInput = new MenuItem(readFood, fromStringToIntArray(readAmount), fromStringToStringArray(readIngredient));
            taskInput.setId(readID);
            taskArray.add(taskInput);
            c.moveToNext();
        }
        sql.close();
        return taskArray;
    }

    // update ingredient for a given food item
    public void updateIngredients(long id, ArrayList<String> ingredients){
        SQLiteDatabase sql = db.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ItemDbSchema.INGREDIENT_TITLE, ingredients.toString());
        sql.update(ItemDbSchema.TABLE_NAME, contentValues, "ID="+id, null);
    }

    // update name of food item
    public void updateFoodName(long id, String foodName){
        SQLiteDatabase sql = db.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ItemDbSchema.FOOD_TITLE, foodName);
        sql.update(ItemDbSchema.TABLE_NAME, contentValues, "ID="+id, null);
    }

    // convert string stored in database to integer arraylist
    private ArrayList<Integer> fromStringToIntArray (String string) {
        String[] strings = string.replace("[", "").replace("]", "").split(", ");
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < result.size(); i++) {
            result.add(Integer.parseInt(strings[i]));
        }
        return result;
    }

    // convert string stored in database to string arraylist
    private ArrayList<String> fromStringToStringArray (String string) {
        String[] strings = string.replace("[", "").replace("]", "").split(", ");
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < strings.length; i++) {
            result.add(strings[i]);
        }
        return result;
    }



}