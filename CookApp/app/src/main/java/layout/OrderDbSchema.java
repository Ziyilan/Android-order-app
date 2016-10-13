package layout;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class OrderDbSchema extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Order.db";

    public static final String TABLE_NAME = "Orders";
    public static final String ID_TITLE = "ID";
    public static final String CUSTOMER_TITLE = "CustName";
    public static final String FOOD_TITLE = "Food";
    public static final String QUANTITY_TITLE = "Quantity";
    public static final String STATUS_TITLE = "Status";
    public static final String COMMENT_TITLE = "Comments";

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = " , ";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID_TITLE + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    CUSTOMER_TITLE + TEXT_TYPE + COMMA_SEP +
                    FOOD_TITLE + TEXT_TYPE + COMMA_SEP +
                    QUANTITY_TITLE + INTEGER_TYPE + COMMA_SEP +
                    STATUS_TITLE + INTEGER_TYPE + COMMA_SEP +
                    COMMENT_TITLE + TEXT_TYPE + " )";

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

    public OrderDbSchema(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION+1);
    }

}
