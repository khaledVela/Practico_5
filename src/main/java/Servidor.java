import javax.naming.ldap.SortKey;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor extends JFrame implements Runnable {
    private JLabel chat = new JLabel();
    private JLabel participantes = new JLabel();
    private JPanel jPanelChat = new JPanel();
    private JPanel jPanelPaticipa = new JPanel();
    int port = 0;

    public Servidor() {
        setTitle("Servidor");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(700, 250);
        setLayout(null);
        setSize(545, 520);
        init();
    }

    public void init() {
        jPanelPaticipa.setBackground(new java.awt.Color(0, 0, 0));
        jPanelPaticipa.setBounds(370, 50, 150, 400);

        participantes.setFont(new java.awt.Font("Dialog", 0, 24));
        participantes.setText("Participa");
        participantes.setBounds(400, -20, 100, 100);

        jPanelChat.setBackground(new java.awt.Color(0, 0, 0));
        jPanelChat.setBounds(10, 50, 350, 400);

        chat.setFont(new java.awt.Font("Dialog", 0, 24));
        chat.setText("Chat");
        chat.setBounds(170, -20, 100, 100);

        add(jPanelChat, null);
        add(jPanelPaticipa, null);
        add(participantes, null);
        add(chat, null);

        JMenuBar bar = new JMenuBar();
        JMenu mnu = new JMenu("Menu");
        JMenuItem mi = new JMenuItem("Esperar conexión");
        mi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                mnuArchivo_EsperarConexion();
            }
        });
        Thread mihilo = new Thread(this);
        mihilo.start();

        mnu.add(mi);
        bar.add(mnu);
        setJMenuBar(bar);
    }

    protected void mnuArchivo_EsperarConexion() {
        String puertoString = JOptionPane.showInputDialog(this, "Colocar en qué puerto escuchará por favor:");

        try {
            port = Integer.parseInt(puertoString);
            if (port <= 1024 || port > 65000) {
                throw new Exception("Debe colocar un entero");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Debe colocar un número entero positivo mayor a 1024");
            return;
        }


    }

    public static void main(String[] args) {
        Servidor serv = new Servidor();
        serv.setVisible(true);
    }

    @Override
    public void run() {

        }
    }
