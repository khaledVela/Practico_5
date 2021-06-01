import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Cliente extends JFrame {
    JPanel menu = new JPanel();
    JTextField usuario = new JTextField();

    public Cliente() {
        super("Cliente");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(700, 250);
        setSize(400, 520);
        init();
    }

    public void init() {
        menu.setLayout(null);
        menu.setBackground(new Color(2, 19, 38));
        add(menu);

        JLabel foto = new JLabel(new ImageIcon("menu.png"));
        foto.setBounds(0, 0, 490, 480);
        foto.setVisible(true);
        menu.add(foto, null);

        JLabel persona = new JLabel("User name:");
        persona.setForeground(Color.white);
        persona.setFont(new java.awt.Font("Dialog", 0, 24));
        persona.setBounds(230, 300, 140, 50);
        foto.add(persona, null);

        usuario.setBounds(230, 350, 120, 25);
        usuario.setOpaque(false);
        usuario.setForeground(Color.white);
        foto.add(usuario, null);

        JButton next = new JButton("Aceptar");
        next.setOpaque(false);
        next.setBackground(new Color(0, 0, 0, 0));
        next.setContentAreaFilled(false);
        next.setBounds(230, 380, 120, 25);
        next.setForeground(Color.white);
        foto.add(next, null);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                conectar();
            }
        });

    }
public void conectar(){
    try {
        String id = usuario.getText();

        Socket s = new Socket("localhost", 2089);
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        dout.writeUTF(id);
        String i = new DataInputStream(s.getInputStream()).readUTF();
        if (i.equals("Ya estas registrado")) {
            JOptionPane.showMessageDialog(this, "YA ESTA REGISTRADO\n");
        } else {
            Chat chat = new Chat(id,s);
            this.dispose();
            chat.setVisible(true);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}


    public static void main(String[] args) {
        Cliente client = new Cliente();
        client.setVisible(true);
    }
}
