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

        //������������ݿ����û��"notice"���򴴽���ִ�в�������������Ѵ��ڣ���ֱ��ִ�в�������������
        sql = "create table if not exists publish( suoyin varchar (20),biaoti varchar(20),neirong varchar(20))";

        stmt.executeUpdate(sql);
        System.out.println("������ɹ�");

        //������ı����л�ȡ�����ݣ�
        sql = "insert into publish(suoyin,biaoti,neirong) values('" + m + "','" + str1 + "','" + str2 + "')";
        int rw = stmt.executeUpdate(sql);

        if (rw <= 0) {                //�ж������Ƿ����ɹ�
            System.out.println("ʧ��");
        } else {
            System.out.println("�ɹ�");
        }
    }
    CreatePub(){
        menu = new JPopupMenu();
        JMenuItem buttonlook = new JMenuItem("���ڹ���");
        menu.add(buttonlook);
        buttonlook.addActionListener(e -> {
            new Pub();//�������ݿ�鿴���ڹ���
        });
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e)
            {
                if (e.isPopupTrigger())
                {
                    // ��ʾ����ʽ�˵�
                    menu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        setTitle("��������");
        setBounds(350,200,750, 550);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textField = new JTextField();
        textField.setForeground(Color.BLACK);
        textField.setText("����:");
        textField.setBounds(52, 25, 641, 42);
        contentPane.add(textField);
        textField.setColumns(10);

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("����", Font.PLAIN, 19));
        textArea.setText("����:");
        textArea.setLineWrap(true);
        JScrollPane jsp = new JScrollPane(textArea);    //���ı�������������
        jsp.setBounds(52,102,641,283);
        contentPane.add(jsp);

        JButton btn_c = new JButton("����");
        btn_c.addActionListener(arg0 -> {
            String tx1 = textField.getText();
            String tx2 = textArea.getText();
            if (tx1.equals("") || tx2.equals("")) {
                JOptionPane.showMessageDialog(CreatePub.this,
                        "������Ϣδ��������",
                        "��������", JOptionPane.ERROR_MESSAGE);
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
                        "�����ɹ���",
                        "�����ɹ�", JOptionPane.INFORMATION_MESSAGE);
                CreatePub.this.dispose();

            }
        });

        btn_c.setBounds(150, 400, 123, 30);
        contentPane.add(btn_c);

        JButton btn_q = new JButton("ȡ��");
        btn_q.addActionListener(e -> CreatePub.this.dispose());
        btn_q.setBounds(400, 400, 123, 30);
        contentPane.add(btn_q);
        this.setVisible(true);
    }
}
