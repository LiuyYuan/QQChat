package work;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.awt.Event.ENTER;

public class ClientFrame {
    private JTextField msgText;
    static JTextArea showArea;
    private TCPClient a;
    private final JPopupMenu menu ;
    static int clientnum;
    private String  l[];
    private JList names;
    private JScrollPane namer;

    ClientFrame(boolean aa, String scnumber){
        JFrame mainJFrame = new JFrame("山东大学学生教务管理系统");
        menu = new JPopupMenu();
        showArea = new JTextArea();

        String path = "1.jpg";
        ImageIcon icon = new ImageIcon(path);
        JLabel label = new JLabel(icon);
        showArea.add(label);
        mainJFrame.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));

        JMenuItem buttonvote = new JMenuItem("发起投票");
        JMenuItem buttonvotec = new JMenuItem("查看投票");
        JMenuItem buttonpub = new JMenuItem("发起公告");
        JMenuItem buttonpubc = new JMenuItem("查看公告");
        JMenuItem buttonup = new JMenuItem("上传文件");
        JMenuItem buttondown = new JMenuItem("下载文件");
        menu.add(buttonup);
        menu.add(buttondown);

        int scnum = DBConnector.dbConnector.getscnum();
        l = new String[scnum];
        l = DBConnector.dbConnector.getscname();
        names = new JList(l);
        namer = new JScrollPane(names);

        buttonup.addActionListener(e -> new FileChooser(0));
        buttondown.addActionListener(e -> new FileChooser(1));

        if (aa){
            menu.add(buttonvote);
            menu.add(buttonvotec);
            menu.add(buttonpub);
            menu.add(buttonpubc);
        buttonvote.addActionListener(e -> new CreateVote(scnumber));
        buttonvotec.addActionListener(e -> new Vote(scnumber));
        buttonpub.addActionListener(e -> new CreatePub());
        buttonpubc.addActionListener(e -> new Pub());

        }else {
            menu.add(buttonpubc);
            menu.add(buttonvotec);
            buttonvotec.addActionListener(e -> new Vote(scnumber));
            buttonpubc.addActionListener(e -> new Pub());
        }
        JPanel mpanel = new JPanel();
        JButton emotion = new JButton("表情");
        mpanel.setLayout(new FlowLayout());
        mpanel.add(emotion);
        Container con = mainJFrame.getContentPane();
        con.setLayout(null);

        showArea.setEditable(false);
        showArea.setLineWrap(true);
        JScrollPane JSPanel = new JScrollPane(showArea);
        msgText = new JTextField();
        msgText.setColumns(30);
        JButton sentBtn = new JButton("发送");
        JPanel pane = new JPanel();
        pane.setLayout(new FlowLayout());
        pane.add(msgText);
        pane.add(sentBtn);
        con.add(JSPanel);
        con.add(mpanel);
        con.add(pane);
        con.add(namer);
        namer.setBounds(350,0,150,320);
        JSPanel.setBounds(0,0,350,320);
        mpanel.setBounds(0,320,500,40);
        pane.setBounds(0,360,500,40);
        mainJFrame.setBounds(650,200,500,430);
        mainJFrame.setResizable(false);
        mainJFrame.setVisible(true);
        mainJFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        mainJFrame.addWindowListener(new WindowAdapter() {//增加关闭事件来完成数据库在线状态的改变
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println(1);
                int exi = JOptionPane.showConfirmDialog (null, "确定退出客户端吗？", "友情提示", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (exi == JOptionPane.YES_OPTION) {
                    DBConnector.dbConnector.rezaixian(scnumber);
                    System.exit (0);
                }
            }
        });
        a = new TCPClient(showArea,scnumber);
        a.start();

        emotion.addActionListener(e -> {
            //表情
        });

        msgText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == ENTER){
                    String s =msgText.getText();
                    if (s.length() > 0) {
                        try {
                            a.send(s);
                            msgText.setText(null);
                            msgText.requestFocus();
                        } catch (Exception f) {
                            showArea.append("你的消息：" + msgText.getText() + "未能发送出去\n");
                        }
                    }
                }
            }
        });
        showArea.addMouseListener(new MouseAdapter() {
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
        sentBtn.addActionListener(e -> {
            String s = msgText.getText();
            if (s.length() > 0) {
                try {
                    a.send(s);
                    msgText.setText(null);
                    msgText.requestFocus();
                } catch (Exception f) {
                    showArea.append("你的消息：" + msgText.getText() + "未能发送出去\n");
                }
            }
        });
    }
    public static void main(String []args){
        new ClientFrame(true,"9");
    }
}