import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chat extends JFrame {
    String nombre;
    String texto;
    JPanel menu = new JPanel();

    public Chat(String e){
        nombre=e;
        setTitle("Chat "+nombre);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(700,250);
        setSize(400, 520);
        init();
    }
    public void init(){
        menu.setLayout(null);
        add(menu);

        JLabel foto = new JLabel(new ImageIcon("fon.png"));
        foto.setBounds(0, 0, 490, 490);
        foto.setVisible(true);
        menu.add(foto, null);

        JTextField usuario = new JTextField();
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
        plus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
            }
        });

        foto.add(plus, null);
        JButton envio = new JButton(new ImageIcon("flecha.png"));
        envio.setBounds(320, 450, 70, 30);
        envio.setFont(new java.awt.Font("Dialog", 0, 24));
        envio.setForeground(Color.black);
        envio.setBackground(new Color(2, 19, 38));
        envio.setBorderPainted(false);
        envio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("envia");
                texto=usuario.getText();
                System.out.println(texto);
                usuario.setText("");
            }
        });
        foto.add(envio, null);
    }

    public static void main(String[] args) {
        Chat chat = new Chat("Pepe");
        chat.setVisible(true);
    }
}
