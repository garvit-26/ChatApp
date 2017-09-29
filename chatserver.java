 import java.io.*;
 import java.net.*;
 import java.util.ArrayList;
 import java.util.Scanner;
 import javax.swing.JOptionPane;

  public class chatserver {

  	public static ArrayList<Socket> ConnectionArray = new ArrayList<Socket>();
  	public static ArrayList<String> CurrentUsers = new ArrayList<String>();

  	public static void main(String[] args) throws IOException{

  		try{

  			final int PORT = 1026;
  			ServerSocket SERVER = new ServerSocket(PORT);
  			System.out.println("waiting for clients....");

  			while(true){

  				Socket SOCK = SERVER.accept();
  				ConnectionArray.add(SOCK);
  				System.out.println("client connected from:"+SOCK.getLocalAddress().getHostName());

  				AddUserName(SOCK);

  				chatserverreturn CHAT = new chatserverreturn(SOCK);
  				Thread X = new Thread(CHAT);
  				X.start();

  			}
  		}
  	
          catch(Exception X) { System.out.print(X);}    
  	}

           public static void AddUserName(Socket X) throws IOException{

           	Scanner INPUT = new Scanner(X.getInputStream());
           	String UserName = INPUT.nextLine();
           	CurrentUsers.add(UserName);

           	for(int i=1;i<=chatserver.ConnectionArray.size();i++){

           		Socket TEMP_SOCK = (Socket)chatserver.ConnectionArray.get(i-1);
           		PrintWriter OUT = new PrintWriter(TEMP_SOCK.getOutputStream());
           		OUT.println("#?!"+CurrentUsers);
           		OUT.flush();
           	}
           }         

  }