package work;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

class CreateVote extends JFrame{
    private JTextField titletext;
    private JTextField Atext;
    private JTextField Btext;
    private JTextField Ctext;

    CreateVote(String scnumber) {

        setTitle("����ͶƱ");
        //setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(500,300,750, 550);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel titlelabel = new JLabel("ͶƱ����");
        titlelabel.setFont(new Font("����", Font.PLAIN, 20));
        titlelabel.setBounds(39, 35, 195, 27);
        contentPane.add(titlelabel);

        titletext = new JTextField();
        titletext.setBounds(39, 75, 472, 27);
        contentPane.add(titletext);
        titletext.setColumns(10);

        JButton btn_qr = new JButton("����");
        btn_qr.setBounds(120, 350, 123, 29);
        contentPane.add(btn_qr);

        JButton btn_cancel = new JButton("ȡ��");
        btn_cancel.setBounds(400, 350, 123, 29);
        contentPane.add(btn_cancel);

        JLabel Alabel = new JLabel("ѡ��1");
        Alabel.setFont(new Font("����", Font.PLAIN, 20));
        Alabel.setBounds(60, 150, 89, 27);
        contentPane.add(Alabel);

        JLabel Blabel = new JLabel("ѡ��2");
        Blabel.setFont(new Font("����", Font.PLAIN, 20));
        Blabel.setBounds(60, 205, 81, 21);
        contentPane.add(Blabel);

        JLabel Clabel = new JLabel("ѡ��3");
        Clabel.setFont(new Font("����", Font.PLAIN, 20));
        Clabel.setBounds(60, 260, 81, 21);
        contentPane.add(Clabel);

        Atext = new JTextField();
        Atext.setBounds(140, 150, 339, 27);
        contentPane.add(Atext);
        Atext.setColumns(10);

        Btext = new JTextField();
        Btext.setColumns(10);
        Btext.setBounds(140, 205, 339, 27);
        contentPane.add(Btext);

        Ctext = new JTextField();
        Ctext.setColumns(10);
        Ctext.setBounds(140, 260, 339, 27);
        contentPane.add(Ctext);

        btn_qr.addActionListener(e -> {
            var str1 = titletext.getText();
            var str2 = Atext.getText();
            var str3 = Btext.getText();
            var str4 = Ctext.getText();

            if (str1.equals("") || str2.equals("") || str3.equals("") || str4.equals("")) {
                JOptionPane.showMessageDialog(CreateVote.this,
                        "������Ϣδ��������",
                        "��������", JOptionPane.ERROR_MESSAGE);
            } else {
                DBConnector.dbConnector.insertVote(scnumber,str1,str2,str3,str4);
                JOptionPane.showMessageDialog(CreateVote.this,
                        "�����ɹ���",
                        "�����ɹ�", JOptionPane.INFORMATION_MESSAGE);
                CreateVote.this.dispose();
            }
        });

        btn_cancel.addActionListener(e -> CreateVote.this.dispose());

        this.setVisible(true);
    }
}
