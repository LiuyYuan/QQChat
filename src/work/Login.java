package work;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.ImageIcon;
import java.sql.ResultSet;
import java.sql.SQLException;

public  class Login implements  ActionListener{
    private JFrame frame = new JFrame("ɽ����ѧѧ���������ϵͳ");
    private JTextField JTusername = new JTextField();
    private JPasswordField JPsw = new JPasswordField();
    private JButton button1 = new JButton("��¼");
    private JComboBox<String> JC = new JComboBox<>();
    @SuppressWarnings("deprecation")
    Login() {
        JPanel panel = new JPanel();
        Container c = frame.getContentPane();
        c.add(panel,BorderLayout.CENTER);
        JLabel label = new JLabel();
        panel.add(label,-1);
        frame.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));//�˴����ƴ���Ϊ��frame����this
        ((JPanel)frame.getContentPane()).setOpaque(false);
        JPanel panel1 = new JPanel();
        panel.add(panel1);
        label.setBounds(0,0, panel.getWidth(), panel.getHeight());
        panel.setOpaque(false);
        c.add(button1);
        JButton button2 = new JButton("ȡ��");
        c.add(button2);
        JButton button3 = new JButton("ע��");
        c.add(button3);
        JLabel JLusername = new JLabel("ѧ�ţ� ");
        c.add(JLusername);
        c.add(JTusername);
        c.setLayout(null);
        JLabel JLPaw = new JLabel("���룺");
        c.add(JLPaw);
        c.add(JPsw);
        JLabel JL1 = new JLabel("��ݣ� ");
        c.add(JL1);
        c.add(JC);


        label.setBounds(0,0,500,380);
        frame.setBounds(500, 200, 500, 380);
        //frame.setUndecorated(true);
        frame.setResizable(false);
        JLusername.setBounds(150, 90, 100, 20);
        JTusername.setBounds(250, 90, 80, 20);
        JLPaw.setBounds(150, 150, 60, 20);
        JPsw.setBounds(250, 150, 80, 20);
        JL1.setBounds(150, 200, 60, 20);
        JC.setBounds(250, 200, 80, 20);
        JC.addItem("��ͨѧ��");
        JC.addItem("����Ա");

        JLusername.setForeground(Color.BLUE);
        JLPaw.setForeground(Color.BLUE);
        JL1.setForeground(Color.BLUE);


        button1.setBounds(150, 250, 60, 20);
        button2.setBounds(250, 250, 60, 20);
        button3.setBounds(350, 300, 60, 20);
        button1.setBorderPainted(false);
        button1.setBorder(null);
        button1.setFocusPainted(false);
        button1.setContentAreaFilled(false);//ǰ�ĸ�ʵ���˰�ť͸����

        button2.setBorderPainted(false);
        button2.setBorder(null);
        button2.setFocusPainted(false);
        button2.setContentAreaFilled(false);

        button3.setBorderPainted(false);
        button3.setBorder(null);
        button3.setFocusPainted(false);
        button3.setContentAreaFilled(false);

        button3.setForeground(Color.RED);
        button1.setForeground(Color.RED);
        button2.setForeground(Color.RED);


        frame.setDefaultCloseOperation(JFrame.  EXIT_ON_CLOSE);
        frame.setVisible(true);
        button2.addActionListener(e -> System.exit(0));
        button1.addActionListener(this);
        button3.addActionListener(e -> {
            frame.dispose();//ע������ݷ���database��
            new Registration();
        });

    }
public static void  main (String []args){
	        new Login();

	    }


    public void actionPerformed(ActionEvent e){
        if(e.getSource() == button1) {
            String scnumber = JTusername.getText();
            String password = new String(JPsw.getPassword());
            int index = JC.getSelectedIndex(); //ѡ�ͨ����ȡ˳�����ж�ѡ��Ŀ�� 1�ǹ���Ա 0��ѧ��
            String loginsql ;
            if (index == 0){
                loginsql = "select password from students where shenfen = '0' && scnumber='"+scnumber+"'";//mysql��&�����ң�����&&�����ң�����
                if ( logindb(password,loginsql)){
                    DBConnector.dbConnector.shangxian(scnumber);
                    frame.dispose();
                    new ClientFrame(false,scnumber);

                }
            }
            else if (index == 1){
                loginsql = "select password from students where shenfen = '1' && scnumber='"+scnumber+"'";
                if (logindb(password,loginsql)){
                    DBConnector.dbConnector.shangxian(scnumber);
                    frame.dispose();
                    new ClientFrame(true,scnumber);
                }
            }

        }
    }
    private boolean logindb(String password,String sql) {
        ResultSet rs = null;
        try {
            rs = DBConnector.getConn().createStatement().executeQuery(sql);
        }catch(Exception e){
            e.printStackTrace();
        }
        try {
            assert rs != null;
            while (rs.next()) {
                if (rs.getString("password").equals(password)) {
                    rs.close();
                    return true;
                }
            }
        }catch(SQLException e1) {
            e1.printStackTrace();
        }
        return false;
    }
}
