import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class airlines
{
	String name,id,fnum,source,destn;
	airlines(String n,String i,String num,String s,String d)
	{
		name=n;
		id=i;
		fnum=num;
		source=s;
		destn=d;
	}
}
class air_management
{
	ArrayList<airlines> alist=new ArrayList<airlines>();
	void add_passenger(String n,String i,String num,String s,String d)
	{
		alist.add(new airlines(n,i,num,s,d));
	}
	boolean search_passenger(String ID)
	{
		for(int i=0;i<alist.size();i++)
		{
			if(ID.equals(alist.get(i).id))
			{
				return true;
			}
		}
		return false;
	}
	int retrieve_index(String ID)
	{
		int rvalue=-1;
		for(int i=0;i<alist.size();i++)
		{
			if(ID.equals(alist.get(i).id))
			{
				rvalue=i;
				return rvalue;
			}
		}
		return -1;
	}
	String display(int i)
	{
		String text=(alist.get(i).name+" \t\t "+alist.get(i).id+"\t\t\t"+alist.get(i).fnum+" \t\t"+alist.get(i).source+" \t\t"+alist.get(i).destn+"\n");
		return text;
	}
}
class GUI implements ActionListener
{
	air_management am=new air_management();
	JFrame jf=new JFrame("Airline Management");
	JLabel lname,lid,lnum,ls,ld,lsearch;
	JTextField tname,tid,tnum,ts,td,tsearch;
	JTextArea ta=new JTextArea();
	JButton badd,bs,bdisp;
	GUI()
	{
		jf.setSize(1000,1000);
		jf.setVisible(true);
		jf.setLayout(null);
		jf.getContentPane().setBackground(Color.CYAN);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		lname=new JLabel("Passenger name");
		lname.setBounds(150,10,150,20);
		tname=new JTextField();
		tname.setBounds(320,10,150,20);
		lid=new JLabel("Passenger ID");
		lid.setBounds(150,50,150,20);
		tid=new JTextField();
		tid.setBounds(320,50,150,20);
		lnum=new JLabel("Flight number");
		lnum.setBounds(150,90,150,20);
		tnum=new JTextField();
		tnum.setBounds(320,90,150,20);
		ls=new JLabel("Source");
		ls.setBounds(150,130,150,20);
		ts=new JTextField();
		ts.setBounds(320,130,150,20);
		ld=new JLabel("Destination");
		ld.setBounds(150,170,150,20);
		td=new JTextField();
		td.setBounds(320,170,150,20);
		lsearch=new JLabel("Enter Id to search");
		lsearch.setBounds(150,210,150,20);
		tsearch=new JTextField();
		tsearch.setBounds(320,210,150,20);
		
		badd=new JButton("ADD");
		badd.setBounds(400,270,100,20);
		bs=new JButton("Search with ID");
		bs.setBounds(550,270,150,20);
		bdisp=new JButton("Display");
		bdisp.setBounds(770,270,150,20);
		badd.addActionListener(this);
		bs.addActionListener(this);
		bdisp.addActionListener(this);
		
		ta.setBounds(100,350,1000,200);
		
		jf.add(lname);
		jf.add(lid);
		jf.add(lnum);
		jf.add(ls);
		jf.add(ld);
		jf.add(lsearch);
		jf.add(tname);
		jf.add(tid);
		jf.add(tnum);
		jf.add(ts);
		jf.add(td);
		jf.add(tsearch);
		jf.add(badd);
		jf.add(bs);
		jf.add(bdisp);
		jf.add(ta);
	}
	public void actionPerformed(ActionEvent ae)
	{
		JButton b=(JButton)ae.getSource();
		String s1=tname.getText();
		String s2=tid.getText();
		String s3=tnum.getText();
		String s4=ts.getText();
		String s5=td.getText();
		if(b==badd)
		{
			
			if(!(s1.equals("")) && !(s2.equals("")) && !(s3.equals("")) && !(s4.equals("")) && !(s5.equals("")))
			{
				am.add_passenger(s1,s2,s3,s4,s5);
				tname.setText(null);
				tid.setText(null);
				tnum.setText(null);
				ts.setText(null);
				td.setText(null);
			}
			else
			{
				JOptionPane.showMessageDialog(jf,"Please enter all details!!!");
			}
		}
		else if(b==bs)
		{
			String s=tsearch.getText();
			String details;
			if(am.search_passenger(s) && !(s.equals(null)))
			{
				ta.setText("Found!!"+"\n");
				ta.append("Details..."+"\n");
				ta.append("NAME \t\t ID \t\t FLIGHT NUMBER \t\t SOURCE \t\t DESTINATION \n");
				int index=am.retrieve_index(s);
				details=am.display(index);
				ta.append(details);
			}
			else
			{
				ta.setText("NOT Found!!!");
			}
		}
		else if(b==bdisp)
		{
			ta.setText("NAME \t\t ID \t\t FLIGHT NUMBER \t\t SOURCE \t\t DESTINATION \n");
			String details;
			for(int i=0;i<am.alist.size();i++)
			{
				details=am.display(i);
				ta.append(details);
			}
		}
	}
	public static void main(String z[])
	{
		new GUI();
	}
}