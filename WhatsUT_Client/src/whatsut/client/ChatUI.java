package whatsut.client;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
import java.rmi.Naming;
import java.util.*;

/**
 * @author davi on 5/25/21
 * @project WhatsUT_Client
 */

public class ChatUI {
    JTextArea tx;
    JTextField tf, ip, name;
    JButton connect;
    JList lst;
    JFrame frame;
    private ChatClient client;
    private ChatServerInt server;

    public ChatUI() {
        frame = new JFrame("WhatsUT");
        JPanel main = new JPanel();
        JPanel top = new JPanel();
        JPanel cn = new JPanel();
        JPanel bottom = new JPanel();
        ip = new JTextField();
        tf = new JTextField();
        name = new JTextField();
        tx = new JTextArea();
        connect = new JButton("Conectar");
        JButton bt = new JButton("Enviar");
        lst = new JList();
        main.setLayout(new BorderLayout(5, 5));
        top.setLayout(new GridLayout(1, 0, 5, 5));
        cn.setLayout(new BorderLayout(5, 5));
        bottom.setLayout(new BorderLayout(5, 5));
        top.add(new JLabel("Seu nome: "));
        top.add(name);
        top.add(new JLabel("Endereço do Servidor: "));
        top.add(ip);
        top.add(connect);
        cn.add(new JScrollPane(tx), BorderLayout.CENTER);
        cn.add(lst, BorderLayout.EAST);
        bottom.add(tf, BorderLayout.CENTER);
        bottom.add(bt, BorderLayout.EAST);
        main.add(top, BorderLayout.NORTH);
        main.add(cn, BorderLayout.CENTER);
        main.add(bottom, BorderLayout.SOUTH);
        main.setBorder(new EmptyBorder(10, 10, 10, 10));

        connect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doConnect();
            }
        });
        bt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendText();
            }
        });
        tf.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendText();
            }
        });

        frame.setContentPane(main);
        frame.setSize(600, 600);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        System.out.println("Teste ChatUI!");
        ChatUI c = new ChatUI();
    }

    public void doConnect() {
        if (connect.getText().equals("Conectar")) {
            if (name.getText().length() < 2) {
                JOptionPane.showMessageDialog(frame, "Você precisa digitar um nome.");
                return;
            }
            if (ip.getText().length() < 2) {
                JOptionPane.showMessageDialog(frame, "Você precisa digitar um IP.");
                return;
            }
            try {
                client = new ChatClient(name.getText());
                client.setGUI(this);
                server = (ChatServerInt) Naming.lookup("rmi://" + ip.getText() + "/admin");
                server.login(client);
                updateUsers(server.getConnected());
                connect.setText("Disconectar");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "ERRO, não foi possível conectar...");
            }
        } else {
            updateUsers(null);
            connect.setText("Conectar");
            //Better to implement Logout ....
        }
    }

    public void sendText() {
        if (connect.getText().equals("Conectar")) {
            JOptionPane.showMessageDialog(frame, "Você precisa se conectar primeiro.");
            return;
        }
        String st = tf.getText();
        st = "[" + name.getText() + "] " + st;
        tf.setText("");
        try {
            server.publish(st);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeMsg(String st) {
        tx.setText(tx.getText() + "\n" + st);
    }

    public void updateUsers(Vector v) {
        DefaultListModel listModel = new DefaultListModel();
        if (v != null) for (int i = 0; i < v.size(); i++) {
            try {
                String tmp = ((ChatClientInt) v.get(i)).getName();
                listModel.addElement(tmp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        lst.setModel(listModel);
    }
}