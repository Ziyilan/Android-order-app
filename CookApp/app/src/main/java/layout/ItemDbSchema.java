package layout;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class ItemDbSchema extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Item.db";
    public static final String TABLE_NAME = "Menu";
    public static final String ID_TITLE = "ID";
    public static final String FOOD_TITLE = "Food";
    public static final String INGREDIENT_TITLE = "Ingredients";
    public static final String AMOUNT_TITLE = "Amount";


    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID_TITLE + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    FOOD_TITLE + TEXT_TYPE + COMMA_SEP +
                    INGREDIENT_TITLE + TEXT_TYPE + COMMA_SEP +
                    AMOUNT_TITLE + INTEGER_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static final int DATABASE_VERSION = 1;                  //set the database version; if the database schema is changed increment version by one



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);

        onCreate(sqLiteDatabase);
    }

    public ItemDbSchema(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION+1);
    }
}
