package work;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

class Pub extends JFrame {
    private JTextArea text;
    private JTextField title;
    private static int tpti;
    private int tpi;


    Pub(){
        setTitle("公告系统");
        setBounds(400,200,750, 550);
        text = new JTextArea();
        title = new JTextField();
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        title = new JTextField();
        title.setForeground(Color.black);
        title.setBounds(52,20,400,40);
        title.setFont(new Font("宋体", Font.PLAIN, 15));
        contentPane.add(title);

        text = new JTextArea();
        text.setForeground(Color.BLACK);
        text.setLineWrap(true);
        JScrollPane jsp = new JScrollPane(text);
        jsp.setBounds(52,90,641,320);
        text.setFont(new Font("宋体", Font.PLAIN, 20));
        contentPane.add(jsp);
        text.setColumns(10);

        JButton lb = new JButton("上一篇");
        lb.setBounds(91, 430, 123, 29);
        contentPane.add(lb);

        JButton ub = new JButton("下一篇");
        ub.setBounds(250, 430, 123, 29);
        contentPane.add(ub);

        JButton exit = new JButton("退出");
        exit.setBounds(500,430,123,29);
        contentPane.add(exit);

        this.setVisible(true);
        text.setEditable(false);
        title.setEditable(false);

        tpi = 1;
        tpti = DBConnector.dbConnector.allPub();
        if (tpti >= 1){
            title.setText(DBConnector.dbConnector.selectbt(tpti));
            text.setText(DBConnector.dbConnector.selectnr(tpti));
            title.setEnabled(false);
            text.setEnabled(false);
        }
        lb.setEnabled(false);
        if (tpti <= 1)
            ub.setEnabled(false);
        lb.addActionListener(e -> {
            tpi--;
            if (tpi == 1)
                lb.setEnabled(false);
            ub.setEnabled(true);
            title.setText(DBConnector.dbConnector.selectbt(tpi));
            text.setText(DBConnector.dbConnector.selectnr(tpi));
            title.setEnabled(false);
            text.setEnabled(false);
        });

        ub.addActionListener(e -> {
            tpi++;
            if (tpi == tpti) ub.setEnabled(false);
            lb.setEnabled(true);
            title.setText(DBConnector.dbConnector.selectbt(tpi));
            text.setText(DBConnector.dbConnector.selectnr(tpi));
            title.setEnabled(false);
            text.setEnabled(false);
        });

        exit.addActionListener(e -> Pub.this.dispose());
    }
}