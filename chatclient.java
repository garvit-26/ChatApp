import java.net.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;
public class chatclient implements Runnable
{
	Socket SOCK;
	Scanner INPUT;
	Scanner SEND = new Scanner(System.in);
	PrintWriter OUT;
	public chatclient(Socket X)
	{
		this.SOCK = X;
	}
	public void run()
	{
		try
		{
			try
			{
				INPUT = new Scanner(SOCK.getInputStream());
				OUT= new PrintWriter(SOCK.getOutputStream());
				OUT.flush();
				CheckStream();
			}
			finally
			{
				SOCK.close();
			}

		}
		catch(Exception X)
		{
		 System.out.println(X);
	    }
	}
	    public void DISCONNECT() throws IOException
	{
		OUT.println(chatclientgui.UserName + "has disconnected.");
		OUT.flush();
		SOCK.close();
		JOptionPane.showMessageDialog(null,"disconnected");
		System.exit(0);
	}
	    public void CheckStream()
	{
		while(true)
		{
			RECIEVE();
		}
	}
	    public void RECIEVE()
	{
		if(INPUT.hasNext())
		{
			String MESSAGE = INPUT.nextLine();
			if(MESSAGE.contains("#?!"))
			{
				String TEMP1 = MESSAGE.substring(3);
				TEMP1=TEMP1.replace("[","");
				TEMP1= TEMP1.replace("]","");
				String[] CurrentUsers = TEMP1.split(", ");
				chatclientgui.JL_ONLINE.setListData(CurrentUsers);
			}
			  else
				{
					chatclientgui.TA_CONVERSATION.append(MESSAGE+ "\n");
			    }
			}
		}
	
	public void SEND(String X)
	{
		 OUT.println(chatclientgui.UserName + ": " + X);
		 OUT.flush();
		 chatclientgui.TF_Message.setText("");
	}
}