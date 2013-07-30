package se.wibi.observer;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockDialogFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;



public class MainActivity extends SherlockFragmentActivity{	
    private ArrayList<RowServer> serverList = new ArrayList<RowServer>();
    private ListView serverListView;
    private Button btnAddServer;
    //Tar en view och gör ett objekt av det.
    LayoutInflater inf;
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// connect to the server
		new ServerOnlineTask().execute();	
	}
	private class ServerOnlineTask extends AsyncTask<Void, Void, Void> {
		private ProgressDialog progressDialog;
		int iconServerStatus;
		//Progress bar loading activate, should count server
		protected void onPreExecute() {
            progressDialog = ProgressDialog.show(MainActivity.this,"", "Loading. lease wait...", true);
		}
		@Override
		protected Void doInBackground(Void... arg0) {
			
			try{
            	DatabaseHandler db = new DatabaseHandler(MainActivity.this);
            	for (RowServer rs : db.getAllServers()){
                    int id = rs.getId();
            		String name = rs.getName();
                    String ip = rs.getIp();
                    int port = rs.getPort();
                    boolean isReachable =  isReachable(ip, port);
                	//Check if server is online
                    if(isReachable) iconServerStatus = R.drawable.serverup;
                	else iconServerStatus = R.drawable.serverdown;
                	
                    //creating new server with icon
                    RowServer rsNew = new RowServer(iconServerStatus, id, name, ip, port);
                    
                    //Adding the server to the public serverlist
                    serverList.add(rsNew);
            	}
            	
			}catch (Exception e){ //Bad catch
				Log.d("TAGG", ""+e);
				return null;
				
			}
			return null;
		}
		@Override
        protected void onPostExecute(Void result) {   	
			//remove the progress indicator
			progressDialog.dismiss();

			//Creating a custome adapter, sending in context, what view to use and a list of Servers
			ServerAdapter adapter = new ServerAdapter(MainActivity.this, R.layout.list_server_layout, serverList, getIntent());
			
			serverListView = (ListView)findViewById(R.id.list);
			
			inf = (LayoutInflater)MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View footer = inf.inflate(R.layout.add_new_server, null);
			
			serverListView.addFooterView(footer);
			//public RowServer(String name, String ip, int port, String username, String password){
			btnAddServer = (Button)findViewById(R.id.addNewServer);
			btnAddServer.setOnClickListener( new OnClickListener(){
	        	 
		        @Override
					public void onClick(View v) {
		        		RowServer rs = new RowServer("Not defined","External IP,",4444,"username","Password");
						new DatabaseHandler(MainActivity.this).addServer(rs);
						updateActivity();
					}
		        	
		        });
			
			//Id list is list name in main.xml
			
			serverListView.setAdapter(adapter);
			serverListView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener(){

			@Override
				public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
					RowServer rs = new RowServer();
					rs = serverList.get(position);
					Intent mIntent = new Intent(MainActivity.this, ServerHostActivity.class);
				    Bundle extras = new Bundle();
				    extras.putString("server", ""+rs.getId());
				    mIntent.putExtras(extras);
				    startActivity(mIntent);					
				}
			});
			
		
		}
		
	}
	//Check if server is reachable from socket with ip:port
	private boolean isReachable(String ip, int port){
		boolean exists = false;
		try {
		    SocketAddress sockaddr = new InetSocketAddress(ip, port);
		    // Create an unbound socket
		    Socket sock = new Socket();

		    // This method will block no more than timeoutMs.
		    // If the timeout occurs, SocketTimeoutException is thrown.
		    int timeoutMs = 3000;   // 2 seconds
		    sock.connect(sockaddr, timeoutMs);
		    exists = true;
		}
		catch(Exception e){
			exists = false;
		}
		return exists;
	}
    private void showDialog() {
    	FragmentManager fm = getSupportFragmentManager();
        HelpDialogFragment testDialog = new HelpDialogFragment();
        testDialog.setRetainInstance(true);
        testDialog.show(fm, "fragment_name");
    }
 

	public boolean onCreateOptionsMenu(Menu menu) {
	// TODO Auto-generated method stub
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}
	 
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
			case R.id.itemRefresh:
				updateActivity();
				return true;
			case R.id.itemHelp:
				showDialog();
			return true;
		default:
	return true;
	}	
	}
	public void updateActivity(){
		Intent intent = getIntent();
		finish();
		startActivity(intent);
	}
}
