package work;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;



public class Registration implements  ActionListener {
	    private JFrame frame = new JFrame("��Ϣע��");
	    private JTextField Tusername = new JTextField();
	    private JTextField JTusername = new JTextField();
	    private JPasswordField JPsw = new JPasswordField();
	    private JComboBox<String> JC = new JComboBox<>();

	@SuppressWarnings("deprecation")
		Registration() {
			JPanel panel = new JPanel();
		Container c = frame.getContentPane();
		c.add(panel,BorderLayout.CENTER);
			JLabel label = new JLabel();
			panel.add(label,-1);
		        frame.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));
		        ((JPanel)frame.getContentPane()).setOpaque(false);
			JPanel panel1 = new JPanel();
			panel.add(panel1);
		        label.setBounds(0,0, panel.getWidth(), panel.getHeight());
	            panel.setOpaque(false);
			JButton button1 = new JButton("ע��");
			c.add(button1);
			JButton button2 = new JButton("ȡ��");
			c.add(button2);
			JLabel lusername = new JLabel("�û����� ");
			c.add(lusername);
			JLabel JLusername = new JLabel("ѧ�ţ� ");
			c.add(JLusername);
		        c.add(JTusername);
		        c.setLayout(null);
		        c.add(Tusername);
			JLabel JLPaw = new JLabel("���룺");
			c.add(JLPaw);
		        c.add(JPsw);
			JLabel JL1 = new JLabel("��ݣ� ");
			c.add(JL1);
		        c.add(JC);


		        label.setBounds(0,0,500,380);
		        frame.setBounds(500, 200, 500, 380);
		        frame.setResizable(false);

		        lusername.setBounds(150,30,60,20);
		        Tusername.setBounds(250,30,80,20);

		        JLusername.setBounds(150, 90, 60, 20);
		        JTusername.setBounds(250, 90, 80, 20);

		        JLPaw.setBounds(150, 150, 60, 20);
		        JPsw.setBounds(250, 150, 80, 20);

		        JL1.setBounds(150, 200, 60, 20);
		        JC.setBounds(250, 200, 80, 20);
		        JC.addItem("������Ա");
		        JC.addItem("��ͨѧ��");
		        lusername.setForeground(Color.BLUE);
		        JLusername.setForeground(Color.BLUE);
		        JLPaw.setForeground(Color.BLUE);
		        JL1.setForeground(Color.BLUE);


		        button1.setBounds(150, 250, 60, 20);
		        button2.setBounds(250, 250, 60, 20);
		        button1.setBorderPainted(false);
		        button1.setBorder(null);
		        button1.setFocusPainted(false);
		        button1.setContentAreaFilled(false);//ǰ�ĸ�ʵ���˰�ť͸����

		        button2.setBorderPainted(false);
		        button2.setBorder(null);
		        button2.setFocusPainted(false);
		        button2.setContentAreaFilled(false);

		        button1.setForeground(Color.RED);
		        button2.setForeground(Color.RED);
		        
		        
		        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        frame.setVisible(true);
		        button2.addActionListener(e -> System.exit(0));
		        button1.addActionListener(this);
		        }		        
	    
	    @SuppressWarnings("unused")
		public void actionPerformed(ActionEvent e){
		        String scnumber = JTusername.getText();
		        String password = new String(JPsw.getPassword());
		        String name = Tusername.getText();
		        int index = JC.getSelectedIndex(); //ѡ�ͨ����ȡ˳�����ж�ѡ��Ŀ�� 0�ǹ���Ա 1��ѧ��
		        String loginsql ;
		        if (index == 1) {
		        	loginsql = "insert into students (scnumber,name,password,shenfen,class1) values('" + scnumber + "','"+name+"','" + password + "','0','1')";
		        try {
					boolean rs = DBConnector.getConn().createStatement().execute(loginsql);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		        }
		        else if (index == 0) {
					loginsql = "insert into students (scnumber,name,password,shenfen,class1) values('" + scnumber + "','"+name+"','" + password + "','1','1')";
					try {
						boolean rs = DBConnector.getConn().createStatement().execute(loginsql);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
		       frame.dispose();
		       new Login();

}}
