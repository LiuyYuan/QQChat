package work;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.*;

@SuppressWarnings("serial")
public class FileChooser {
    private JButton Do =new JButton("ִ�в���");
    private JTextField textField =new JTextField();
    private JList jList;
    int filenumber;
    String k [];
    int number22;
    private String ss;
    private static String kname,jname;

    FileChooser(int number){
        JFrame frame = new JFrame("�ļ�ִ��ϵͳ");
        frame.setBounds(500, 200, 500, 380);


        Container c = frame.getContentPane();
        if (number == 0){//0���ϴ���
            c.add(textField);
            c.add(Do);
            c.setLayout(null);
            textField.setBounds(0,70,500,50);
            textField.setEditable(false);
            Do.setBounds(170,200,120,30);
            frame.setResizable(false);
            frame.setVisible(true);
            Do.addActionListener(e -> {
                JFileChooser jfc=new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int i =  jfc.showDialog(new JLabel(), "ѡ��");
                if (i == 0){
                    File file=jfc.getSelectedFile();
                    kname = jfc.getSelectedFile().getName();
                    jname = jfc.getSelectedFile().getPath();
                    System.out.println(jfc.getSelectedFile().getName());
                    new UploadClient().upload(file);
                    DBConnector.dbConnector.sendfile(kname,jname);
                }
                textField.setText("�ļ����ϴ���");
            });
        }


        if (number == 1) {
            //1������
            filenumber = DBConnector.dbConnector.filenu();
            k = new String[filenumber];
            k = DBConnector.dbConnector.getfilenames();
            jList = new JList(k);
            JScrollPane scrollPane = new JScrollPane(jList);
            c.add(scrollPane);
            c.setLayout(null);
            JLabel label1 = new JLabel("��������ļ���ѡ��:");
            c.add(label1);
            label1.setBounds(0,0,500,20);
            scrollPane.setBounds(0,20,500,340);
            System.out.println(filenumber);//������ļ���
            frame.setResizable(true);
            frame.setVisible(true);
            jList.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.getClickCount()==2) {
                        number22 = jList.getSelectedIndex();
                        ss = k[number22];
                        JFileChooser jfc=new JFileChooser();
                        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                        int i =  jfc.showDialog(new JLabel(), "ѡ��");
                        if (i == 0){
                            File file=jfc.getSelectedFile();
                            System.out.println(ss);
                            String ff = file.getPath()+"\\"+ss;
                            System.out.println(ff);
                            new DownLoadClient().download(ss,ff);
                            System.out.println("�ļ��������");
                        }
                        textField.setText("�ļ������أ�");
                    }
                }
            });


            /*for (int i = 0;i < number1;i++){
                JLabel label = new JLabel(DBConnector.dbConnector.file2(i-1));
                scrollPane.setColumnHeaderView(label);
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        label.setForeground(Color.red);
                        ss = label.getText();
                        Do.setEnabled(true);
                        ex.addActionListener(e1 -> {
                            label.setForeground(Color.BLACK);
                            ss = null;
                            Do.setEnabled(false);
                        });
                    }
                });
            }
            Do.addActionListener(e -> {
                System.out.println(1);
                JFileChooser jfc=new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                int i =  jfc.showDialog(new JLabel(), "ѡ��");
                if (i == 0){
                    File file=jfc.getSelectedFile();
                    System.out.println(ss);
                    String ff = file.getPath()+"\\"+ss;
                    System.out.println(ff);
                    new DownLoadClient().download(ss,ff);
                    System.out.println("�ļ��������");
                }
                textField.setText("�ļ������أ�");
            });*/

        }
    }
    public static void main(String []args){
        new FileChooser(1);
    }
}
