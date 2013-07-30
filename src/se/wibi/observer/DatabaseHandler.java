package se.wibi.observer;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper implements DatabaseConstants{

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		 String CREATE_SERVERS_TABLE = "CREATE TABLE " + TABLE_SERVERS + "("
	                + _ID + " INTEGER PRIMARY KEY," +COMPUTER_NAME + " TEXT,"
	                + COMPUTER_HOST + " TEXT," +COMPUTER_PORT + " INTEGER,"
	                + USERNAME + " TEXT," + PASSWORD + " TEXT" + ")";
	     db.execSQL(CREATE_SERVERS_TABLE);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVERS);
        // Create tables again
        onCreate(db);
		
	}
	 //Database methods
    public void addServer(RowServer rs){
    	SQLiteDatabase db = this.getWritableDatabase();
    	ContentValues values = new ContentValues();
    	
    	//Placing database values
        values.put(COMPUTER_NAME, rs.getName());
        values.put(COMPUTER_HOST, rs.getIp());
        values.put(COMPUTER_PORT, rs.getPort());
        values.put(USERNAME, rs.getUsername());
        values.put(PASSWORD, rs.getPassword());
    	
        //Inserting values into database table TABLE_SERVERS
        db.insert(TABLE_SERVERS, null, values);
    }
    public RowServer getServer(int id){
    	SQLiteDatabase db = this.getReadableDatabase();
    	 
        Cursor cursor = db.query(TABLE_SERVERS, new String[] { _ID,
        		COMPUTER_NAME, COMPUTER_HOST,COMPUTER_PORT,USERNAME,PASSWORD }, _ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        
        RowServer rs = new RowServer();
        rs.setId(Integer.parseInt(cursor.getString(0)));
        rs.setName(cursor.getString(1));
        rs.setIp(cursor.getString(2));
        rs.setPort(Integer.parseInt(cursor.getString(3)));
        rs.setUsername(cursor.getString(4));
        rs.setPassword(cursor.getString(5));
        return rs;
    }
    public List<RowServer> getAllServers(){
        List<RowServer> rowServerList = new ArrayList<RowServer>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SERVERS;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                RowServer rs = new RowServer();
                rs.setId(Integer.parseInt(cursor.getString(0)));
                rs.setName(cursor.getString(1));
                rs.setIp(cursor.getString(2));
                rs.setPort(Integer.parseInt(cursor.getString(3)));
                rs.setUsername(cursor.getString(4));
                rs.setPassword(cursor.getString(5));
                // Adding contact to list
                rowServerList.add(rs);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return rowServerList;
    }
    /*
     * Server update. Called from ServerSettingsActivity
     * when users presses send button.
     */
    
    public void updateServer(RowServer rs){
    	SQLiteDatabase db = this.getWritableDatabase();
    	ContentValues values = new ContentValues();
       
        values.put(COMPUTER_NAME, rs.getName());
        values.put(COMPUTER_HOST, rs.getIp());
        values.put(COMPUTER_PORT, rs.getPort());
        values.put(USERNAME, rs.getUsername());
        values.put(PASSWORD, rs.getPassword());
    	
        // updating row
       db.update(TABLE_SERVERS, values, _ID + " = ?", new String[] { String.valueOf(rs.getId()) });
    	
    }
    public void deleteServer(RowServer rs){
    	 SQLiteDatabase db = this.getWritableDatabase();
         db.delete(TABLE_SERVERS, _ID + " = ?",
                 new String[] { String.valueOf(rs.getId()) });
         db.close();
    }
}
