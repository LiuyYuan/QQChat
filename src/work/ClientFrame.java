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
        JFrame mainJFrame = new JFrame("ɽ����ѧѧ���������ϵͳ");
        menu = new JPopupMenu();
        showArea = new JTextArea();

        String path = "1.jpg";
        ImageIcon icon = new ImageIcon(path);
        JLabel label = new JLabel(icon);
        showArea.add(label);
        mainJFrame.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));

        JMenuItem buttonvote = new JMenuItem("����ͶƱ");
        JMenuItem buttonvotec = new JMenuItem("�鿴ͶƱ");
        JMenuItem buttonpub = new JMenuItem("���𹫸�");
        JMenuItem buttonpubc = new JMenuItem("�鿴����");
        JMenuItem buttonup = new JMenuItem("�ϴ��ļ�");
        JMenuItem buttondown = new JMenuItem("�����ļ�");
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
        JButton emotion = new JButton("����");
        mpanel.setLayout(new FlowLayout());
        mpanel.add(emotion);
        Container con = mainJFrame.getContentPane();
        con.setLayout(null);

        showArea.setEditable(false);
        showArea.setLineWrap(true);
        JScrollPane JSPanel = new JScrollPane(showArea);
        msgText = new JTextField();
        msgText.setColumns(30);
        JButton sentBtn = new JButton("����");
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
        mainJFrame.addWindowListener(new WindowAdapter() {//���ӹر��¼���������ݿ�����״̬�ĸı�
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println(1);
                int exi = JOptionPane.showConfirmDialog (null, "ȷ���˳��ͻ�����", "������ʾ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (exi == JOptionPane.YES_OPTION) {
                    DBConnector.dbConnector.rezaixian(scnumber);
                    System.exit (0);
                }
            }
        });
        a = new TCPClient(showArea,scnumber);
        a.start();

        emotion.addActionListener(e -> {
            //����
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
                            showArea.append("�����Ϣ��" + msgText.getText() + "δ�ܷ��ͳ�ȥ\n");
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
                    // ��ʾ����ʽ�˵�
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
                    showArea.append("�����Ϣ��" + msgText.getText() + "δ�ܷ��ͳ�ȥ\n");
                }
            }
        });
    }
    public static void main(String []args){
        new ClientFrame(true,"9");
    }
}