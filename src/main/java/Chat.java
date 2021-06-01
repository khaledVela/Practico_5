import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.StringTokenizer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class Chat extends JFrame {
    JPanel menu = new JPanel();
    private final static Logger logger = (Logger) LogManager.getRootLogger();
    JTextField usuario = new JTextField();
    JLabel foto = new JLabel(new ImageIcon("fon.png"));
    JTextArea txt_mensajes = new JTextArea();
    JScrollPane scrollPane1 = new JScrollPane();

    String iD;
    DataInputStream din;
    DataOutputStream dout;
    DefaultListModel dlm;

    public Chat(String a, Socket s) {
        iD = a;
        setTitle("Chat " + iD);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(700, 250);
        setSize(400, 520);
        init();
        try {
            dlm = new DefaultListModel();
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            new Read().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init() {
        menu.setLayout(null);
        add(menu);

        foto.setBounds(0, 0, 490, 490);
        foto.setVisible(true);
        menu.add(foto, null);

        txt_mensajes.setEditable(false);
        txt_mensajes.setBounds(0, 0, 385, 450);
        txt_mensajes.setForeground(Color.white);
        txt_mensajes.setOpaque(false);
        foto.add(txt_mensajes, null);

       /* txt_mensajes.setEditable(false);
        txt_mensajes.setForeground(Color.black);
        scrollPane1.setBounds(0, 0, 385, 450);
        scrollPane1.setViewportView(txt_mensajes);
        scrollPane1.setVerticalScrollBarPolicy(scrollPane1.VERTICAL_SCROLLBAR_ALWAYS);
        foto.add(scrollPane1, null);
        */
        usuario.setBounds(50, 450, 270, 30);
        usuario.setForeground(Color.white);
        usuario.setBackground(new Color(2, 19, 38));
        foto.add(usuario, null);

        JButton plus = new JButton("+");
        plus.setBounds(0, 451, 50, 30);
        plus.setFont(new java.awt.Font("Dialog", 0, 24));
        plus.setForeground(Color.WHITE);
        plus.setBackground(new Color(2, 19, 38));
        plus.setBorderPainted(false);
        foto.add(plus, null);

        JButton envio = new JButton(new ImageIcon("flecha.png"));
        envio.setBounds(320, 450, 70, 30);
        envio.setFont(new java.awt.Font("Dialog", 0, 24));
        envio.setForeground(Color.black);
        envio.setBackground(new Color(2, 19, 38));
        envio.setBorderPainted(false);
        envio.addActionListener(new ActionListener() {
            // //
            @Override
            public void actionPerformed(ActionEvent arg0) {
                logger.debug("envia");
                try {
                    String m = usuario.getText(), mm = m;
                    dout.writeUTF(m);
                    usuario.setText("");
                    int esp =  (90 - mm.length() * 2);
                    String espacio = "";
                    for (int a = 0; a < esp; a++) {
                        espacio += " ";
                    }
                    txt_mensajes.append(espacio + "<Tu a todos> " + mm + "\n");

                } catch (Exception e) {
                }
            }
        });
        foto.add(envio, null);
    }

    class Read extends Thread {
        public void run() {
            while (true) {
                try {
                    String m = din.readUTF();
                    if (m.contains(":;.,/=")) {
                        m = m.substring(6);
                        dlm.clear();
                        StringTokenizer st = new StringTokenizer(m, ",");
                        while (st.hasMoreTokens()) {
                            String u = st.nextToken();
                            if (!iD.equals(u)) {
                                dlm.addElement(u);
                            }
                        }
                    } else {
                        txt_mensajes.append("" + m + "\n");

                    }
                } catch (Exception e) {
                    break;
                }
            }

        }
    }
}
