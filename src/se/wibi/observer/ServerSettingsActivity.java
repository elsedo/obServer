package se.wibi.observer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


 
public class ServerSettingsActivity extends Fragment{
 
    public static final String EXTRA_TITLE = "title";
    EditText etServerName;
    EditText etIP;
    EditText etPort;
    EditText etUsername;
    EditText etPassword;
    int serverId;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	View view = inflater.inflate(R.layout.server_settings, container, false);
    	
    	DatabaseHandler db = new DatabaseHandler(getActivity());
    	RowServer rs = new RowServer();
    	//Getting ServerId from bundle
    	String sServername = ServerHostActivity.sServerName;
    	serverId = Integer.parseInt(sServername);
    	rs = db.getServer(serverId);
    	
    	etServerName = (EditText)view.findViewById(R.id.etservername);
    	etServerName.setText(rs.getName());
    	etIP = (EditText)view.findViewById(R.id.etip);
    	etIP.setText(rs.getIp());
    	etPort = (EditText)view.findViewById(R.id.etport);
    	etPort.setText(""+rs.getPort());
    	etUsername = (EditText)view.findViewById(R.id.etusername);
    	etUsername.setText(rs.getUsername());
    	etPassword = (EditText)view.findViewById(R.id.etpassword);
    	etPassword.setText(rs.getPassword());
    	
    	final Button loginButton = (Button) view.findViewById(R.id.addServerSettingsbtn);
        loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
            	if(isIntegerParseInt(etPort.getText().toString())){
            		
            		String name = etServerName.getText().toString();
            		String ip = etIP.getText().toString();
            		int port = Integer.parseInt(etPort.getText().toString());
            		String username = etUsername.getText().toString();
                	String password = etPassword.getText().toString();
            		RowServer rs = new RowServer(serverId, name,ip, port, username, password);
            		//Uppdatera servern.
            		
            		DatabaseHandler db = new DatabaseHandler(getActivity());
            		db.updateServer(rs);
            	}
            }
        });
    	return view;
    }
    public static boolean isIntegerParseInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException nfe) {}
        return false;
    }
   public static Bundle createBundle( String title ) {
        Bundle bundle = new Bundle();
        bundle.putString( EXTRA_TITLE, title );
        return bundle;
    }


 
}