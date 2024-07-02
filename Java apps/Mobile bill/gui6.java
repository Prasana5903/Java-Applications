import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
class mobile
{
	String brand,model,year;
	int price;
	mobile(String b,String m,String y,int p)
	{
		brand=b;
		model=m;
		year=y;
		price=p;
	}
}
class GUI implements ActionListener
{
	JFrame jf=new JFrame("Mobile Shopping");
	JLabel heading=new JLabel("------ONLINE MOBILE SHOPPING-------");
	JLabel lb,lm,ly,lp;
	JTextField tm,tp;
	String Brands[]={"Samsung","Sony","Mototrola"};
	JList<String> list1=new JList(Brands);
	JRadioButton rb1,rb2,rb3;
	ButtonGroup bg1;
	JButton submit=new JButton("Submit");
	JTextArea ta=new JTextArea();
	GUI()
	{
		jf.setSize(1000,1000);
		jf.setVisible(true);
		jf.setLayout(null);
		jf.getContentPane().setBackground(Color.LIGHT_GRAY);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		heading.setBounds(700,2,700,20);
		lb=new JLabel("Brand name");
		lb.setBounds(150,10,150,20);
		list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list1.setBounds(320,10,150,60);
		lm=new JLabel("Model");
		lm.setBounds(150,100,150,20);
		tm=new JTextField();
		tm.setBounds(320,100,150,20);
		ly=new JLabel("Year");
		ly.setBounds(150,140,150,20);
		
		rb1=new JRadioButton("2018");
		rb1.setBounds(320,140,90,20);
		rb2=new JRadioButton("2019");
		rb2.setBounds(420,140,90,20);
		rb3=new JRadioButton("2020");
		rb3.setBounds(520,140,90,20);
		bg1=new ButtonGroup();
		bg1.add(rb1);
		bg1.add(rb2);
		bg1.add(rb3);
		
		lp=new JLabel("Price");
		lp.setBounds(150,180,150,20);
		tp=new JTextField();
		tp.setBounds(320,180,150,20);
		
		submit.setBounds(350,220,150,20);
		submit.addActionListener(this);
		
		ta.setBounds(350,260,700,70);
		
		jf.add(heading);
		jf.add(lb);
		jf.add(list1);
		jf.add(lm);
		jf.add(tm);
		jf.add(ly);
		jf.add(rb1);
		jf.add(rb2);
		jf.add(rb3);
		jf.add(lp);
		jf.add(tp);
		jf.add(submit);
		jf.add(ta);
	}
	public void actionPerformed(ActionEvent ae)
	{
		String bname=list1.getSelectedValue();
		String mname=tm.getText();
		String yr="";
		String ps=tp.getText();
		int p=Integer.parseInt(ps);
		boolean flag=true;
		if(rb1.isSelected())
		{
			yr="2018";
		}
		else if(rb2.isSelected())
		{
			yr="2019";
		}
		else if(rb3.isSelected())
		{
			yr="2020";
		}
		if(bname.equals("")||mname.equals("")||yr.equals(""))
		{
			ta.setText("Please enter all Details!!");
			flag=false;
		}
		if(p<10000||p>50000)
		{
			ta.setText("Product not available in this range");
			flag=false;
		}
		JButton temp=(JButton)ae.getSource();
		if(temp==submit && flag==true)
		{
			String details;
			ta.setText("Purchase Details..."+"\n");
			ta.append("Brand \t\t Model \t\t Year \t\t Price \n");
			ta.append(bname+" \t\t "+mname+" \t\t "+yr+" \t\t "+ps+"\n");
			list1.clearSelection();
			lm.setText(null);
			bg1.clearSelection();
			tp.setText(null);
		}
		else
		{
			JOptionPane.showMessageDialog(jf,"Purchase failed!!");
		}
	}
	public static void main(String z[])
	{
		new GUI();
	}
}