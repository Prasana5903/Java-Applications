import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.net.*;
import java.io.*;
class TestServer implements ActionListener
{
	JFrame jf;
	JLabel l1,l2,l3;
	JTextField t1,t2,t3;
	JButton ben,bsend;
	TestServer()
	{
		jf=new JFrame("Server");
		jf.setVisible(true);
		jf.setLayout(new FlowLayout());
		jf.setSize(1000,1000);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.getContentPane().setBackground(Color.LIGHT_GRAY);
		
		l1=new JLabel("Enter message: ");
		l2=new JLabel("Key");
		l3=new JLabel("Encrypted Message");
		
		t1=new JTextField(30);
		t2=new JTextField(30);
		t3=new JTextField(30);
		
		ben=new JButton("Encrypt");
		bsend=new JButton("Send");
		ben.addActionListener(this);
		bsend.addActionListener(this);
		
		
		jf.add(l1);
		jf.add(t1);
		jf.add(l2);
		jf.add(t2);
		jf.add(l3);
		jf.add(t3);
		jf.add(ben);
		jf.add(bsend);
	}
	public void actionPerformed(ActionEvent ae)
	{
		JButton b=(JButton)ae.getSource();
		if(b==ben)
		{
			String msg=t1.getText();
			//int key=2;
			t2.setText("2");
			String n = t2.getText();
			
			int key = Integer.parseInt(n);
			StringBuffer sb=new StringBuffer(msg);
			for(int i=0;i<sb.length();i++)
			{
				char temp=sb.charAt(i);
				temp+=key;
				sb.setCharAt(i,temp);
			}
			msg=sb.toString();
			t3.setText(msg);
		}
		if(b==bsend)
		{
			try
			{
				ServerSocket ss=new ServerSocket(2000);
				Socket s=ss.accept();
				System.out.println("Connected...");
				PrintStream out=new PrintStream(s.getOutputStream());
				String send_msg=t3.getText();
				out.println(send_msg);
				out.flush();
				JOptionPane.showMessageDialog(jf,"Successfully sent!");
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	}
	public static void main(String args[])
	{
		new TestServer();
	}
}