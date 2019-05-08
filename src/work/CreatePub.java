package work;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.Statement;

class CreatePub extends JFrame{
    private JTextField textField;
    private final JPopupMenu menu  ;


    private void writeInSql(int m, String str1, String str2) throws Exception {

        String sql;
        Connection con = DBConnector.getConn();
        Statement stmt = con.createStatement();

        //创建并检查数据库表，若没有"notice"表，则创建表并执行插入操作，若表已存在，则直接执行操作，插入数据
        sql = "create table if not exists publish( suoyin varchar (20),biaoti varchar(20),neirong varchar(20))";

        stmt.executeUpdate(sql);
        System.out.println("创建表成功");

        //插入从文本框中获取的数据；
        sql = "insert into publish(suoyin,biaoti,neirong) values('" + m + "','" + str1 + "','" + str2 + "')";
        int rw = stmt.executeUpdate(sql);

        if (rw <= 0) {                //判断数据是否插入成功
            System.out.println("失败");
        } else {
            System.out.println("成功");
        }
    }
    CreatePub(){
        menu = new JPopupMenu();
        JMenuItem buttonlook = new JMenuItem("往期公告");
        menu.add(buttonlook);
        buttonlook.addActionListener(e -> {
            new Pub();//利用数据库查看往期公告
        });
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e)
            {
                if (e.isPopupTrigger())
                {
                    // 显示弹出式菜单
                    menu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        setTitle("发布公告");
        setBounds(350,200,750, 550);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textField = new JTextField();
        textField.setForeground(Color.BLACK);
        textField.setText("标题:");
        textField.setBounds(52, 25, 641, 42);
        contentPane.add(textField);
        textField.setColumns(10);

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("仿宋", Font.PLAIN, 19));
        textArea.setText("内容:");
        textArea.setLineWrap(true);
        JScrollPane jsp = new JScrollPane(textArea);    //将文本域放入滚动窗口
        jsp.setBounds(52,102,641,283);
        contentPane.add(jsp);

        JButton btn_c = new JButton("发布");
        btn_c.addActionListener(arg0 -> {
            String tx1 = textField.getText();
            String tx2 = textArea.getText();
            if (tx1.equals("") || tx2.equals("")) {
                JOptionPane.showMessageDialog(CreatePub.this,
                        "必填信息未填完整！",
                        "输入有误", JOptionPane.ERROR_MESSAGE);
            } else {
                String t1 = textField.getText();
                String t2 = textArea.getText();
                try {
                    DBConnector.getConn();
                    writeInSql(DBConnector.suoyin(), t1, t2);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                JOptionPane.showMessageDialog(CreatePub.this,
                        "发布成功！",
                        "发布成功", JOptionPane.INFORMATION_MESSAGE);
                CreatePub.this.dispose();

            }
        });

        btn_c.setBounds(150, 400, 123, 30);
        contentPane.add(btn_c);

        JButton btn_q = new JButton("取消");
        btn_q.addActionListener(e -> CreatePub.this.dispose());
        btn_q.setBounds(400, 400, 123, 30);
        contentPane.add(btn_q);
        this.setVisible(true);
    }
}
