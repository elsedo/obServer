package se.wibi.observer;

public class RowServer {
    private int id;
	private String name;
	private String ip;
	private int port;
	int icon;
	private String username;
	private String password;
	
	//Empty constructor
	public RowServer(){
		
	}
	
	
	//For ListViewLayouting
	public RowServer(int icon, int id, String name, String ip, int port){
	    this.icon = icon;
	    this.id = id;
	    this.name = name;
		this.ip = ip;
		this.port = port;
    }
	//For database Viewing
	public RowServer(String name, String ip, int port, String username, String password){
		this.name = name;
		this.ip = ip;
		this.port = port;
		this.username = username;
		this.password = password;
	}
	
	//For getting single server
	public RowServer(int id, String name, String ip, int port, String username, String password){
		this.id = id;
		this.name = name;
		this.ip = ip;
		this.port = port;
		this.username = username;
		this.password = password;
	}
	
	
	public int getId(){return this.id;};
	public String getName(){return this.name;}
	public String getIp(){return this.ip;}
	public int getPort(){return this.port;}
	public int getIcon(){return this.icon;}
	public String getUsername(){return this.username;}
	public String getPassword(){return this.password;}
	
	public void setId(int id){ this.id=id;}
	public void setName(String name){ this.name = name ;}
	public void setIp(String ip){ this.ip = ip ;}
	public void setPort(int port){ this.port = port ;}
	public void setIcon(int icon){this.icon = icon;}	
	public void setUsername(String username){ this.username = username ;}
	public void setPassword(String password){ this.password = password ;}
	
	@Override
	public String toString() {
		return name;
	}
}
