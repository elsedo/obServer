package se.wibi.observer;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ServerAdapter extends ArrayAdapter<RowServer> {

	private ArrayList<RowServer> servers; 
	int layoutResourceId;
	Context context;
	//removed final
	Intent intent;
   
	
	    public ServerAdapter(Context context, int layoutResourceId, ArrayList<RowServer> data, Intent intent) {
	        super(context, layoutResourceId, data);
	        this.layoutResourceId = layoutResourceId;
	        this.context = context;
	        this.servers = data;
	        this.intent = intent;
	       
	    }

	    @Override
	    public View getView(final int position, View convertView, ViewGroup parent) {
	    	View row = convertView;
	        ServerHolder holder = null;
	        if(row == null)
	        {
	         	LayoutInflater inflater = ((Activity)context).getLayoutInflater();
	            row = inflater.inflate(layoutResourceId, parent, false);
	           
	            holder = new ServerHolder();
	            holder.imgIcon = (ImageView)row.findViewById(R.id.thumbImage);
	            holder.txtTitle = (TextView)row.findViewById(R.id.name);
	            holder.removeIcon = (ImageView)row.findViewById(R.id.removeServer);
	            row.setTag(holder);
	        }else{
	            holder = (ServerHolder)row.getTag();
	        }
	        
	        
	        
	        //To be able to withdraw server id for removal and setting icons.
	        final RowServer rs = servers.get(position);
	        
	        //need a database to remove servers
	        final DatabaseHandler db = new DatabaseHandler(context);
	        //Failed to get a clicklistener on all object so until further notice
	        //removing Servers from here
	        holder.removeIcon.setOnClickListener( new OnClickListener(){
	        	 
	        @Override
				public void onClick(View v) {
	        		db.deleteServer(rs);	
	        		Intent intent = ServerAdapter.this.intent;
	        		Context context = ServerAdapter.this.context;
	       				       			
	       			
	       			Intent mIntent = new Intent(context, MainActivity.class);
				  
				   context.startActivity(mIntent);					
	       			
	       			
	       			
	       			
	       			
	       			
	        		
				}
	        	
	        });
	        
	       
	        holder.imgIcon.setImageResource(rs.getIcon());
	        holder.txtTitle.setText(rs.getName());
	        return row;
	    }   
	    static class ServerHolder
	    {
	        ImageView imgIcon;
	        ImageView removeIcon;
	        TextView txtTitle;
	    }
	}
