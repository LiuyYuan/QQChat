package work;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;



public class Registration implements  ActionListener {
	    private JFrame frame = new JFrame("信息注册");
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
			JButton button1 = new JButton("注册");
			c.add(button1);
			JButton button2 = new JButton("取消");
			c.add(button2);
			JLabel lusername = new JLabel("用户名： ");
			c.add(lusername);
			JLabel JLusername = new JLabel("学号： ");
			c.add(JLusername);
		        c.add(JTusername);
		        c.setLayout(null);
		        c.add(Tusername);
			JLabel JLPaw = new JLabel("密码：");
			c.add(JLPaw);
		        c.add(JPsw);
			JLabel JL1 = new JLabel("身份： ");
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
		        JC.addItem("管理人员");
		        JC.addItem("普通学生");
		        lusername.setForeground(Color.BLUE);
		        JLusername.setForeground(Color.BLUE);
		        JLPaw.setForeground(Color.BLUE);
		        JL1.setForeground(Color.BLUE);


		        button1.setBounds(150, 250, 60, 20);
		        button2.setBounds(250, 250, 60, 20);
		        button1.setBorderPainted(false);
		        button1.setBorder(null);
		        button1.setFocusPainted(false);
		        button1.setContentAreaFilled(false);//前四个实现了按钮透明化

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
		        int index = JC.getSelectedIndex(); //选项卡通过读取顺序来判断选择目标 0是管理员 1是学生
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
