import java.util.*;
import java.net.*;
import java.io.*;
class TestServer
{
	public static void main(String args[])
	{
		ServerSocket ss=null;
		Socket s=null;
		try
		{
			ss=new ServerSocket(2001);
			
			System.out.println("Server Listening....");
			while(true)
			{
				s=ss.accept();
				System.out.println("Connection Established...");
				ServerThread st=new ServerThread(s);
				st.start();
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
class ServerThread extends Thread
{
	Socket s=null;
	BufferedReader br=null;
	PrintStream p=null;
	ServerThread(Socket k)
	{
		s=k;
	}
	public void run()
	{
		
		try
		{
			br=new BufferedReader(new InputStreamReader(s.getInputStream()));
			p=new PrintStream(s.getOutputStream());
			String r_msg=br.readLine();
			StringBuffer sb=new StringBuffer(r_msg);
			for(int i=0;i<sb.length();i++) //DECRYPTING ..SUBTRACTS 2 FROM EACH CHARACTER...
			{
				char temp=sb.charAt(i);
				temp-=2;					//USING STRING BUFFER TO EDIT STRINGS...
				sb.setCharAt(i,temp);
			}
			r_msg=sb.toString();
			while(r_msg.compareTo("Exit")!=0) //compareTo() also used
			{
				p.println(r_msg);//just sending back the same msg from client...
				p.flush();
				System.out.println("Message received from client: "+r_msg);
				r_msg=br.readLine();
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}










