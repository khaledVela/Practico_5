import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cliente extends JFrame {
    JPanel menu = new JPanel();
    String cliente;

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

        JTextField usuario = new JTextField();
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
                cliente = usuario.getText();
                setVisible(false);
                Chat chat = new Chat(cliente);
                chat.setVisible(true);

            }
        });

    }

    public static void main(String[] args) {
        Cliente client = new Cliente();
        client.setVisible(true);
    }
}
