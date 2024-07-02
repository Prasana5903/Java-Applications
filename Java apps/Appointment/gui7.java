import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
class MyException extends Exception
{
	MyException(String s)
	{
		super(s);
	}
}
class Appointment 
{
	String repName,whomToMeet,date;
	Appointment(String n,String w,String d)
	{
		repName=n;
		whomToMeet=w;
		date=d;
	}
}
class OrganizedAppointment
{
	ArrayList<Appointment> aList=new ArrayList<Appointment>();
	void addIntoList(String name,String whom,String d)
	{
		aList.add(new Appointment(name,whom,d));
	}
	boolean searchRep(String n)
	{
		for(int i=0;i<aList.size();i++)
		{
			if(n.equals(aList.get(i).repName))
			{
				return true;
			}
		}
		return false;
	}
	boolean searchDate(String d)
	{
		for(int i=0;i<aList.size();i++)
		{
			if(d.equals(aList.get(i).date))
			{
				return true;
			}
		}
		return false;
	}
}
class GUI implements ActionListener
{
	OrganizedAppointment oApp=new OrganizedAppointment();
	JFrame jf=new JFrame("APPOINTMENTS");
	JLabel lName,lWhom,lDate,lSearchRep,lSearchDate;
	JTextField tName,tWhom,tDate,tSearchRep,tSearchDate;
	JButton submit,bSearchName,bSearchDate;
	JTextArea ta=new JTextArea();
	GUI()
	{
		//JFrame..
		jf.setSize(1000,1000);
		jf.setVisible(true);
		jf.setLayout(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.getContentPane().setBackground(Color.GRAY);
		
		//JLabel..
		lName=new JLabel("Rep Name");
		lName.setBounds(150,10,150,20);
		lWhom=new JLabel("Whom To Meet");
		lWhom.setBounds(150,50,150,20);
		lDate=new JLabel("Date");
		lDate.setBounds(150,90,150,20);
		lSearchRep=new JLabel("Name to search");
		lSearchRep.setBounds(150,130,150,20);
		lSearchDate=new JLabel("Date to search");
		lSearchDate.setBounds(150,170,150,20);
		
		//JTextField..
		tName=new JTextField();
		tName.setBounds(320,10,150,20);
		tWhom=new JTextField();
		tWhom.setBounds(320,50,150,20);
		tDate=new JTextField();
		tDate.setBounds(320,90,150,20);
		tSearchRep=new JTextField();
		tSearchRep.setBounds(320,130,150,20);
		tSearchDate=new JTextField();
		tSearchDate.setBounds(320,170,150,20);
		
		//JButton...
		submit=new JButton("Submit");
		bSearchName=new JButton("Search with name");
		bSearchDate=new JButton("Search with Date");
		submit.setBounds(400,200,150,20);
		bSearchName.setBounds(570,200,200,20);
		bSearchDate.setBounds(790,200,200,20);
		submit.addActionListener(this);
		bSearchName.addActionListener(this);
		bSearchDate.addActionListener(this);
		
		//JTextArea...
		ta.setBounds(100,240,800,200);
		
		//Adding the components into JFrame...
		jf.add(lName);
		jf.add(lWhom);
		jf.add(lDate);
		jf.add(lSearchRep);
		jf.add(lSearchDate);
		jf.add(tName);
		jf.add(tWhom);
		jf.add(tDate);
		jf.add(tSearchRep);
		jf.add(tSearchDate);
		jf.add(submit);
		jf.add(bSearchName);
		jf.add(bSearchDate);
		jf.add(ta);
	}
	public void actionPerformed(ActionEvent ae)
	{
		String sName=tName.getText();
		String sWhom=tWhom.getText();
		String sDate=tDate.getText();
		JButton temp=(JButton)ae.getSource();
		if(temp==submit)
		{
			if(!(sName.equals("")) && !(sWhom.equals("")) && !(sDate.equals("")))
			{
				oApp.addIntoList(sName,sWhom,sDate);
				
				//emptying the text field for next inputs....
				tName.setText(null);
				tWhom.setText(null);
				tDate.setText(null);
			}
			else
			{
				try
				{
					JOptionPane.showMessageDialog(jf,"Please enter all details..");
					throw new MyException("Insufficient details provided");
				}
				catch(MyException e){System.out.println(e);}
			}
		}
		else if(temp==bSearchName)
		{
			String search_Name=tSearchRep.getText();
			if(oApp.searchRep(search_Name))
			{
				ta.setText("Found!!"+"\n");
				ta.append("Appointment details"+"\n");
				ta.append("Rep Name \t\t WhomTOMeet \t\t Date \n");
				for(int i=0;i<oApp.aList.size();i++)
				{
					if(search_Name.equals(oApp.aList.get(i).repName))
					{
						String text=(oApp.aList.get(i).repName+" \t\t "+oApp.aList.get(i).whomToMeet+" \t\t   "+oApp.aList.get(i).date+"\n");
						ta.append(text);
					}
				}
				tSearchRep.setText(null);
			}
			else
			{
				ta.setText("NOT Found!!");
				try
				{
					throw new MyException("Rep name not found!!");
				}
				catch(MyException e){System.out.println(e);};
			}
		}
		else if(temp==bSearchDate)
		{
			String search_Date=tSearchDate.getText();
			if(oApp.searchDate(search_Date))
			{
				ta.setText("Found!!"+"\n");
				ta.append("Appointment details"+"\n");
				ta.append("Rep Name \t\t WhomTOMeet \t\t Date \n");
				for(int i=0;i<oApp.aList.size();i++)
				{
					if(search_Date.equals(oApp.aList.get(i).date))
					{
						String text=(oApp.aList.get(i).repName+" \t\t "+oApp.aList.get(i).whomToMeet+" \t\t "+oApp.aList.get(i).date+"\n");
						ta.append(text);
					}
				}
				tSearchDate.setText(null);
			}
			else
			{
				ta.setText("NOT Found!!");
				try
				{
					throw new MyException("Date not found!!");
				}
				catch(MyException e){System.out.println(e);};
			}
		}
	}
	public static void main(String z[])
	{
		new GUI();
	}
}