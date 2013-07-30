package se.wibi.observer;
import android.os.Bundle;
import android.util.Log;
public class ServerHostActivity extends TabSwipeActivity {
	public static String sServerName;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Placing server clicked id number in static String so all Fragments can reach it.
        sServerName = getIntent().getExtras().getString("server");
        
        addTab( "Info", ServerInfoActivity.class, ServerInfoActivity.createBundle( "Fragment 1") );
        addTab( "Graph", ServerGraphActivity.class, ServerGraphActivity.createBundle( "Fragment 2") );
        addTab( "Settings", ServerSettingsActivity.class, ServerSettingsActivity.createBundle( "sServerName") );
    }
 
}