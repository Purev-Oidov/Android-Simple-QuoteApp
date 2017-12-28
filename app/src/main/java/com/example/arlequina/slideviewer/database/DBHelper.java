package com.example.arlequina.slideviewer.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.arlequina.slideviewer.model.Quote;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ARLEQUINA on 12/28/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String db_name = "QuoteApp.db";
    public static final String db_table = "quote_tbl";
    public static final String db_location = "/data/data/com.example.arlequina.slideviewer/databases/";

    public static final String quote_id = "ID";
    public static final String quote_text = "QuoteText";
    public static final String quote_auth = "QuoteAuthor";

    private Context context;
    private SQLiteDatabase mDatabase;

    public String qtext, qauth;

    public DBHelper(Context context) {
        super(context, db_name, null, 25);
        this.context = context;
    }


    public void openDatabase() throws SQLException
    {
        String path =  db_location + db_name;
        mDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.CREATE_IF_NECESSARY);
    }

    public synchronized void closeDatabase(){
        if(mDatabase != null)
            mDatabase.close();
        SQLiteDatabase.releaseMemory();
        super.close();
    }


    public List<Quote> getList()
    {
        mDatabase = this.getReadableDatabase();
        Quote quote = null;
        List<Quote> quoteList = new ArrayList<>();
        openDatabase();
        String query = "SELECT * FROM " + db_table;
        Cursor cursor = mDatabase.rawQuery(query, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast())
        {
            qtext = cursor.getString(cursor.getColumnIndex(quote_text));
            qauth = cursor.getString(cursor.getColumnIndex(quote_auth));
            quote = new Quote(qtext, qauth);
            quoteList.add(quote);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return quoteList;
    }

    public void copyDatabase() throws  IOException
    {
        try{
            InputStream is = context.getAssets().open(DBHelper.db_name);
            String outFileName = db_location + db_name;
            File databaseFile = new File( "/data/data/com.example.arlequina.slideviewer/databases");
            // check if databases folder exists, if not create one and its subfolders
            if (!databaseFile.exists()){
                databaseFile.mkdir();
            }

            OutputStream os = new FileOutputStream(outFileName);
            byte[] buff = new byte[1024];
            int length = 0;

            while((length = is.read(buff)) > 0){
                os.write(buff,0,length);
            }
            os.flush();
            os.close();
            Log.v("MainActivity","Copied");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
