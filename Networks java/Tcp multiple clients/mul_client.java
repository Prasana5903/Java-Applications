import java.net.*;
import java.io.*;
import java.util.*;

class TestClient
{
	public static void main(String args[])
	{
		Socket s=null;
		BufferedReader br1=null,br2=null;
		PrintStream p=null;
		try
		{
			s=new Socket("localhost",2001);
			System.out.println("Connection Established with server..");
			br1=new BufferedReader(new InputStreamReader(s.getInputStream()));
			br2=new BufferedReader(new InputStreamReader(System.in));
			p=new PrintStream(s.getOutputStream());
			
			System.out.println("Enter msg to server..");
			
			String to_server=br2.readLine();
			StringBuffer sb=new StringBuffer(to_server);
			for(int i=0;i<sb.length();i++) //ENCRYPTING DATA...INCREMENT EACH CHARACTER BY 2	
			{
				char temp=sb.charAt(i);
				temp+=2;					//USING STRING BUFFER TO EDIT STRINGS...
				sb.setCharAt(i,temp);
			}
			to_server=sb.toString();
			String from_server=null;
			
			while(to_server.compareTo("Exit")!=0)
			{
				p.println(to_server);
				p.flush();
				from_server=br1.readLine();
				System.out.println("msg recieved from server: "+from_server);
				System.out.println("Enter msg to server: ");
				to_server=br2.readLine();
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}