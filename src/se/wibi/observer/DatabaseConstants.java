/*
 * Interface for EventsData and other classes using a database
 */

package se.wibi.observer;
import android.provider.BaseColumns;
public interface DatabaseConstants extends BaseColumns {
	//Database version
	public static final int DATABASE_VERSION = 1;
	//Database name
	public static final String DATABASE_NAME = "db_servers";
	//tablename
	public static final String TABLE_SERVERS = "tbl_server";
	// database columns
	public static final String COMPUTER_NAME = "name";
	public static final String COMPUTER_HOST = "host";
	public static final String COMPUTER_PORT = "port";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
}
