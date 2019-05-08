package work;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Vote extends JFrame {
    private JTextField Atext;
    private JTextField Btext;
    private JTextField Ctext;
    private JTextField tittext;
    private JButton Cbtn;
    private JButton Bbtn;
    private JButton Abtn;
    private static int tpti;
    private int tpi = 1;
    private int sum;


    Vote(String scnumber) {
        setTitle("投票区");
        setBounds(500,300,750, 550);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel titlelabel = new JLabel("标题");
        titlelabel.setFont(new Font("宋体", Font.PLAIN, 20));
        titlelabel.setBounds(39, 50, 195, 27);
        contentPane.add(titlelabel);

        JButton lb = new JButton("上一篇");
        lb.setBounds(91, 350, 123, 29);
        contentPane.add(lb);

        JButton ub = new JButton("下一篇");
        ub.setBounds(250, 350, 123, 29);
        contentPane.add(ub);

        JButton exit = new JButton("退出");
        exit.setBounds(500,350,123,29);
        contentPane.add(exit);

        JLabel Alabel = new JLabel("选项1");
        Alabel.setFont(new Font("宋体", Font.PLAIN, 20));
        Alabel.setBounds(39, 150, 89, 27);
        contentPane.add(Alabel);

        JLabel Blabel = new JLabel("选项2");
        Blabel.setFont(new Font("宋体", Font.PLAIN, 20));
        Blabel.setBounds(39, 200, 81, 21);
        contentPane.add(Blabel);

        JLabel Clabel = new JLabel("选项3");
        Clabel.setFont(new Font("宋体", Font.PLAIN, 20));
        Clabel.setBounds(39, 250, 81, 21);
        contentPane.add(Clabel);

        Atext = new JTextField();
        Atext.setBounds(118, 150, 350, 27);
        contentPane.add(Atext);
        Atext.setColumns(10);

        Btext = new JTextField();
        Btext.setColumns(10);
        Btext.setBounds(118, 200, 350, 27);
        contentPane.add(Btext);

        Ctext = new JTextField();
        Ctext.setColumns(10);
        Ctext.setBounds(118, 250, 350, 27);
        contentPane.add(Ctext);

        tittext = new JTextField();
        tittext.setBounds(39, 90, 500, 27);
        contentPane.add(tittext);
        tittext.setColumns(10);

        Abtn = new JButton("选择");
        Abtn.setBounds(500, 150, 89, 29);
        contentPane.add(Abtn);

        Bbtn = new JButton("选择");
        Bbtn.setBounds(500, 200, 89, 29);
        contentPane.add(Bbtn);

        Cbtn = new JButton("选择");
        Cbtn.setBounds(500, 250, 89, 29);
        contentPane.add(Cbtn);



        tpti = DBConnector.dbConnector.allVote();//获取投票项总数
        sum =DBConnector.dbConnector.getsumna(tpi) + DBConnector.dbConnector.getsumnb(tpi) + DBConnector.dbConnector.getsumnc(tpi);//获得票数总和

        if (tpti > 1) {
            lb.setEnabled(false);
            ub.setEnabled(true);
            tittext.setText(DBConnector.dbConnector.selectTitle(tpi));
            Atext.setText(DBConnector.dbConnector.selectta(tpi));
            Btext.setText(DBConnector.dbConnector.selecttb(tpi));
            Ctext.setText(DBConnector.dbConnector.selecttc(tpi));
        if (sum >= DBConnector.dbConnector.getnumsc()){
            tittext.setText(tittext.getText() + " (投票已结束)");
            Atext.setText(Atext.getText() + "(共" + DBConnector.dbConnector.selectna(tpi) + "票)");
            Btext.setText(Btext.getText() + "(共" + DBConnector.dbConnector.selectnb(tpi) + "票)");
            Ctext.setText(Ctext.getText() + "(共" + DBConnector.dbConnector.selectnc(tpi) + "票)");
            Abtn.setEnabled(false);
            Bbtn.setEnabled(false);
            Cbtn.setEnabled(false);
        }else {
            tittext.setText(tittext.getText() + " (投票ing)");
            Atext.setText(Atext.getText() + "(目前共" + DBConnector.dbConnector.selectna(tpi) + "票)");
            Btext.setText(Btext.getText() + "(目前共" + DBConnector.dbConnector.selectnb(tpi) + "票)");
            Ctext.setText(Ctext.getText() + "(目前共" + DBConnector.dbConnector.selectnc(tpi) + "票)");
            Abtn.setEnabled(true);
            Bbtn.setEnabled(true);
            Cbtn.setEnabled(true);
        } }
        if (tpti <= 1){
            ub.setEnabled(false);
            lb.setEnabled(false);
            tittext.setText(DBConnector.dbConnector.selectTitle(tpi));
            Atext.setText(DBConnector.dbConnector.selectta(tpi));
            Btext.setText(DBConnector.dbConnector.selecttb(tpi));
            Ctext.setText(DBConnector.dbConnector.selecttc(tpi));
            if (sum >= DBConnector.dbConnector.getnumsc()){
                tittext.setText(tittext.getText() + " (投票已结束)");
                Atext.setText(Atext.getText() + "(共" + DBConnector.dbConnector.selectna(tpi) + "票)");
                Btext.setText(Btext.getText() + "(共" + DBConnector.dbConnector.selectnb(tpi) + "票)");
                Ctext.setText(Ctext.getText() + "(共" + DBConnector.dbConnector.selectnc(tpi) + "票)");
                Abtn.setEnabled(false);
                Bbtn.setEnabled(false);
                Cbtn.setEnabled(false);
            }else {
                tittext.setText(tittext.getText() + " (投票ing)");
                Atext.setText(Atext.getText() + "(目前共" + DBConnector.dbConnector.selectna(tpi) + "票)");
                Btext.setText(Btext.getText() + "(目前共" + DBConnector.dbConnector.selectnb(tpi) + "票)");
                Ctext.setText(Ctext.getText() + "(目前共" + DBConnector.dbConnector.selectnc(tpi) + "票)");
                Abtn.setEnabled(true);
                Bbtn.setEnabled(true);
                Cbtn.setEnabled(true);
            }}


        lb.addActionListener(e -> {//上一项
            --tpi;
            sum =DBConnector.dbConnector.getsumna(tpi) + DBConnector.dbConnector.getsumnb(tpi) + DBConnector.dbConnector.getsumnc(tpi);
            if (tpi <= 1){
                lb.setEnabled(false);
                ub.setEnabled(true);
                tittext.setText(DBConnector.dbConnector.selectTitle(tpi));
                Atext.setText(DBConnector.dbConnector.selectta(tpi));
                Btext.setText(DBConnector.dbConnector.selecttb(tpi));
                Ctext.setText(DBConnector.dbConnector.selecttc(tpi));
                if (sum >= DBConnector.dbConnector.getnumsc()){
                    System.out.println(DBConnector.dbConnector.getnumsc());
                    tittext.setText(tittext.getText() + " (投票已结束)");
                    Atext.setText(Atext.getText() + "(共" + DBConnector.dbConnector.selectna(tpi) + "票)");
                    Btext.setText(Btext.getText() + "(共" + DBConnector.dbConnector.selectnb(tpi) + "票)");
                    Ctext.setText(Ctext.getText() + "(共" + DBConnector.dbConnector.selectnc(tpi) + "票)");
                    Abtn.setEnabled(false);
                    Bbtn.setEnabled(false);
                    Cbtn.setEnabled(false);
                }else {
                    tittext.setText(tittext.getText() + " (投票ing)");
                    Atext.setText(Atext.getText() + "(目前共" + DBConnector.dbConnector.selectna(tpi) + "票)");
                    Btext.setText(Btext.getText() + "(目前共" + DBConnector.dbConnector.selectnb(tpi) + "票)");
                    Ctext.setText(Ctext.getText() + "(目前共" + DBConnector.dbConnector.selectnc(tpi) + "票)");
                    Abtn.setEnabled(true);
                    Bbtn.setEnabled(true);
                    Cbtn.setEnabled(true);
                }}
                else{
                    lb.setEnabled(true);
                    ub.setEnabled(true);
                    tittext.setText(DBConnector.dbConnector.selectTitle(tpi));
                    Atext.setText(DBConnector.dbConnector.selectta(tpi));
                    Btext.setText(DBConnector.dbConnector.selecttb(tpi));
                    Ctext.setText(DBConnector.dbConnector.selecttc(tpi));
                    if (sum >= DBConnector.dbConnector.getnumsc()){
                        tittext.setText(tittext.getText() + " (投票已结束)");
                        Atext.setText(Atext.getText() + "(共" + DBConnector.dbConnector.selectna(tpi) + "票)");
                        Btext.setText(Btext.getText() + "(共" + DBConnector.dbConnector.selectnb(tpi) + "票)");
                        Ctext.setText(Ctext.getText() + "(共" + DBConnector.dbConnector.selectnc(tpi) + "票)");
                        Abtn.setEnabled(false);
                        Bbtn.setEnabled(false);
                        Cbtn.setEnabled(false);
                    }else {
                        tittext.setText(tittext.getText() + " (投票ing)");
                        Atext.setText(Atext.getText() + "(目前共" + DBConnector.dbConnector.selectna(tpi) + "票)");
                        Btext.setText(Btext.getText() + "(目前共" + DBConnector.dbConnector.selectnb(tpi) + "票)");
                        Ctext.setText(Ctext.getText() + "(目前共" + DBConnector.dbConnector.selectnc(tpi) + "票)");
                        Abtn.setEnabled(true);
                        Bbtn.setEnabled(true);
                        Cbtn.setEnabled(true);
                    }}
        });
        ub.addActionListener(e -> {
                    ++tpi;
                    sum =DBConnector.dbConnector.getsumna(tpi) + DBConnector.dbConnector.getsumnb(tpi) + DBConnector.dbConnector.getsumnc(tpi);
                    if (tpi >= tpti) {
                        ub.setEnabled(false);
                        lb.setEnabled(true);
                        tittext.setText(DBConnector.dbConnector.selectTitle(tpi));
                        Atext.setText(DBConnector.dbConnector.selectta(tpi));
                        Btext.setText(DBConnector.dbConnector.selecttb(tpi));
                        Ctext.setText(DBConnector.dbConnector.selecttc(tpi));
                        if (sum >= DBConnector.dbConnector.getnumsc()){
                            tittext.setText(tittext.getText() + " (投票已结束)");
                            Atext.setText(Atext.getText() + "(共" + DBConnector.dbConnector.selectna(tpi) + "票)");
                            Btext.setText(Btext.getText() + "(共" + DBConnector.dbConnector.selectnb(tpi) + "票)");
                            Ctext.setText(Ctext.getText() + "(共" + DBConnector.dbConnector.selectnc(tpi) + "票)");
                            Abtn.setEnabled(false);
                            Bbtn.setEnabled(false);
                            Cbtn.setEnabled(false);
                        }else {
                            tittext.setText(tittext.getText() + " (投票ing)");
                            Atext.setText(Atext.getText() + "(目前共" + DBConnector.dbConnector.selectna(tpi) + "票)");
                            Btext.setText(Btext.getText() + "(目前共" + DBConnector.dbConnector.selectnb(tpi) + "票)");
                            Ctext.setText(Ctext.getText() + "(目前共" + DBConnector.dbConnector.selectnc(tpi) + "票)");
                            Abtn.setEnabled(true);
                            Bbtn.setEnabled(true);
                            Cbtn.setEnabled(true);
                        }}
                        else {
                        ub.setEnabled(true);
                        lb.setEnabled(true);
                        tittext.setText(DBConnector.dbConnector.selectTitle(tpi));
                        Atext.setText(DBConnector.dbConnector.selectta(tpi));
                        Btext.setText(DBConnector.dbConnector.selecttb(tpi));
                        Ctext.setText(DBConnector.dbConnector.selecttc(tpi));
                        if (sum >= DBConnector.dbConnector.getnumsc()){
                            tittext.setText(tittext.getText() + " (投票已结束)");
                            Atext.setText(Atext.getText() + "(共" + DBConnector.dbConnector.selectna(tpi) + "票)");
                            Btext.setText(Btext.getText() + "(共" + DBConnector.dbConnector.selectnb(tpi) + "票)");
                            Ctext.setText(Ctext.getText() + "(共" + DBConnector.dbConnector.selectnc(tpi) + "票)");
                            Abtn.setEnabled(false);
                            Bbtn.setEnabled(false);
                            Cbtn.setEnabled(false);
                        }else {
                            tittext.setText(tittext.getText() + " (投票ing)");
                            Atext.setText(Atext.getText() + "(目前共" + DBConnector.dbConnector.selectna(tpi) + "票)");
                            Btext.setText(Btext.getText() + "(目前共" + DBConnector.dbConnector.selectnb(tpi) + "票)");
                            Ctext.setText(Ctext.getText() + "(目前共" + DBConnector.dbConnector.selectnc(tpi) + "票)");
                            Abtn.setEnabled(true);
                            Bbtn.setEnabled(true);
                            Cbtn.setEnabled(true);
                        }
                    }
                    });
        exit.addActionListener(e -> Vote.this.dispose());

        Abtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(Vote.this,
                    "投票成功!",
                    "投票成功", JOptionPane.INFORMATION_MESSAGE);
            Abtn.setEnabled(false);
            Bbtn.setEnabled(false);
            Cbtn.setEnabled(false);
            DBConnector.dbConnector.nextperson(tpi, 1);
            ttemp(tpi);
        });

        Bbtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(Vote.this,
                    "投票成功!",
                    "投票成功", JOptionPane.INFORMATION_MESSAGE);
            Abtn.setEnabled(false);
            Bbtn.setEnabled(false);
            Cbtn.setEnabled(false);
            DBConnector.dbConnector.nextperson( tpi, 2);
            ttemp(tpi);
        });

        Cbtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(Vote.this,
                    "投票成功!",
                    "投票成功", JOptionPane.INFORMATION_MESSAGE);
            Abtn.setEnabled(false);
            Bbtn.setEnabled(false);
            Cbtn.setEnabled(false);
            DBConnector.dbConnector.nextperson( tpi, 3);
            ttemp(tpi);
        });

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    private void ttemp(int tpi) {
        int sum = DBConnector.dbConnector.getsumna(tpi)+ DBConnector.dbConnector.getsumnb(tpi)+ DBConnector.dbConnector.getsumnc(tpi);
        tittext.setText(DBConnector.dbConnector.selectTitle(tpi));
        Atext.setText(DBConnector.dbConnector.selectta(tpi));
        Btext.setText(DBConnector.dbConnector.selecttb(tpi));
        Ctext.setText(DBConnector.dbConnector.selecttc(tpi));
        if (sum >= DBConnector.dbConnector.getnumsc()){
            tittext.setText(tittext.getText() + " (投票已结束)");
            Atext.setText(Atext.getText() + "(共" + DBConnector.dbConnector.selectna(tpi) + "票)");
            Btext.setText(Btext.getText() + "(共" + DBConnector.dbConnector.selectnb(tpi) + "票)");
            Ctext.setText(Ctext.getText() + "(共" + DBConnector.dbConnector.selectnc(tpi) + "票)");
            Abtn.setEnabled(false);
            Bbtn.setEnabled(false);
            Cbtn.setEnabled(false);
        }else {
            tittext.setText(tittext.getText() + " (投票ing)");
            Atext.setText(Atext.getText() + "(共" + DBConnector.dbConnector.selectna(tpi) + "票)");
            Btext.setText(Btext.getText() + "(共" + DBConnector.dbConnector.selectnb(tpi) + "票)");
            Ctext.setText(Ctext.getText() + "(共" + DBConnector.dbConnector.selectnc(tpi) + "票)");
            Abtn.setEnabled(true);
            Bbtn.setEnabled(true);
            Cbtn.setEnabled(true);
        }
    }
    public static void main(String []args){
        new Vote("22");
    }
}
