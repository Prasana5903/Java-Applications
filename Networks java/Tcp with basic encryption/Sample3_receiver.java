import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.net.*;
import java.io.*;
class TestReceiver implements ActionListener
{
	JFrame jf;
	JLabel l1,l2,l3;
	JTextField t1,t2,t3;
	JButton bdec;
	TestReceiver()
	{
		jf=new JFrame("Receiver");
		jf.setVisible(true);
		jf.setLayout(new FlowLayout());
		jf.setSize(1000,1000);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.getContentPane().setBackground(Color.LIGHT_GRAY);
		
		l1=new JLabel("Received message: ");
		l2=new JLabel("Key");
		l3=new JLabel("Decrypted Message");
		
		t1=new JTextField(30);
		t2=new JTextField(30);
		t3=new JTextField(30);
		
		bdec=new JButton("Decrypt");
		bdec.addActionListener(this);
		
		
		jf.add(l1);
		jf.add(t1);
		jf.add(l2);
		jf.add(t2);
		jf.add(bdec);
		jf.add(l3);
		jf.add(t3);
	}
	public void actionPerformed(ActionEvent ae)
	{
		JButton b=(JButton)ae.getSource();
		if(b==bdec)
		{
			try
			{
				Socket s=new Socket("localhost",2000);
				DataInputStream din=new DataInputStream(s.getInputStream());
				//byte barr[]=new byte[100];
				String rmsg=din.readLine();
				t1.setText(rmsg);
				//String dmsg=t1.getText();
				int key=2;
				t2.setText("2");
				//Integer.parseInt(t2.getText());
				StringBuffer sb=new StringBuffer(rmsg);
				for(int i=0;i<sb.length();i++)
				{
					char temp=sb.charAt(i);
					temp-=2;
					sb.setCharAt(i,temp);
				}
				rmsg=sb.toString();
				t3.setText(rmsg);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	}
	public static void main(String args[])
	{
		new TestReceiver();
	}
}