package bill.cookapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import layout.OrderDbSchema;


public class OrderService {
    OrderDbSchema db;

    public OrderService(Context context) {
        db = new OrderDbSchema(context);
    }

//    add new order to database
    public void addOrder(OrderItem orderItem) {
        SQLiteDatabase sql = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(OrderDbSchema.CUSTOMER_TITLE, orderItem.getCustomer_name());
        values.put(OrderDbSchema.FOOD_TITLE, orderItem.getFood().toString());
        values.put(OrderDbSchema.QUANTITY_TITLE, orderItem.getQuantity().toString());
        values.put(OrderDbSchema.STATUS_TITLE, orderItem.getStatus());
        values.put(OrderDbSchema.COMMENT_TITLE, orderItem.getComment());

        try {
            sql.insertOrThrow(OrderDbSchema.TABLE_NAME, OrderDbSchema.CUSTOMER_TITLE, values);
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        } finally {
            sql.close();
        }
    }

//    delete order from database
    public void deleteOrderItem (OrderItem orderItem) {
        SQLiteDatabase sql = db.getWritableDatabase();
        String selection = OrderDbSchema.ID_TITLE + " =?";
        String[] selectionArgs = {String.valueOf(orderItem.getId())};
        sql.delete(OrderDbSchema.TABLE_NAME, selection, selectionArgs);
        sql.close();
    }

//    get all orders from database
    public ArrayList<OrderItem> getAll() {
        ArrayList<OrderItem> orderArray = new ArrayList<>();
        SQLiteDatabase sql = db.getReadableDatabase();
        Cursor c = sql.rawQuery("select * from " + OrderDbSchema.TABLE_NAME, null);
        c.moveToFirst();
        while(!c.isAfterLast()) {
            long readID = c.getLong(0);
            String readCustName = c.getString(1);
            String readFood = c.getString(2);
            String readQuantity = c.getString(3);
            String readComment = c.getString(5);
            OrderItem orderInput = new OrderItem(readCustName,fromStringToIntArray(readQuantity),
                    fromStringToStringArray(readFood), readComment);
            orderInput.setId(readID);
            orderArray.add(orderInput);
            c.moveToNext();
        }
        sql.close();
        return orderArray;
    }

//    convert the string loaded from database back to integer arraylist
    private ArrayList<Integer> fromStringToIntArray (String string) {
        String replace = string.replace("[","");
        String replace1 = replace.replace("]","");
        ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(replace1.split(",")));
        ArrayList<Integer> favList = new ArrayList<Integer>();
        for(String fav:arrayList){
            favList.add(Integer.parseInt(fav.trim()));
        }
        return favList;
    }

    //    convert the string loaded from database back to string arraylist
    private ArrayList<String> fromStringToStringArray (String string) {
        String[] strings = string.replace("[", "").replace("]", "").split(", ");
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < strings.length; i++) {
            result.add(strings[i]);
        }
        return result;
    }


}
