import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.InputStream;

public class Menu extends JPanel {
    private Font pixelFont;
    public Menu(JFrame frame, View viewPanel) {
        //untuk membaca font dari folder assets
        try {
            InputStream is = getClass().getResourceAsStream("/assets/PressStart2P-Regular.ttf");
            pixelFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(14f);
        } catch (Exception e) {
            //jika tidak ada font yang sesuai, maka gunakan default font
            e.printStackTrace();
            pixelFont = new Font("Monospaced", Font.BOLD, 14);
        }

        //set tampilan awal
        setLayout(new BorderLayout());
        //set background berwarna hitam
        setBackground(Color.BLACK);

        //set judul
        JLabel title = new JLabel("FLAPPY MINION", SwingConstants.CENTER);
        //warna teks kuning
        title.setForeground(Color.YELLOW);
        //set ukuran font
        title.setFont(pixelFont.deriveFont(20f));
        //set jarak dari tepi atas
        title.setBorder(BorderFactory.createEmptyBorder(60, 10, 10, 10));

        //set gambar di bawah judul
        JLabel imageLabel = new JLabel();
        //ambil gambar seperti player
        ImageIcon icon = new ImageIcon(getClass().getResource("/assets/karnion.png"));
        //set ukuran gambar
        Image img = icon.getImage().getScaledInstance(80, 120, Image.SCALE_SMOOTH);
        //set posisi gambar
        imageLabel.setIcon(new ImageIcon(img));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //buat tombol untuk start dan exit
        JButton startButton = createRetroButton("START GAME");
        JButton exitButton = createRetroButton("EXIT");

        //mengatur ukuran tombol
        Dimension btnSize = new Dimension(180, 35);
        startButton.setPreferredSize(btnSize);
        exitButton.setPreferredSize(btnSize);

        //tampilan untuk tombol
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10));
        buttonPanel.add(startButton);
        buttonPanel.add(exitButton);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 60, 60, 60));

        //untuk mengatur panel gabungan gambar dan tombol
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);
        centerPanel.add(imageLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10))); // spasi antar gambar & tombol
        centerPanel.add(buttonPanel);

        //menambahkan judul di bagian paling atas, lalu gabungan gambar + tombol di tengah
        add(title, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        //saat tombol start diklik
        startButton.addActionListener(e -> {
            //menampilkan tampilan awal game
            frame.getContentPane().removeAll();
            frame.add(viewPanel);
            frame.revalidate();
            frame.repaint();
            viewPanel.requestFocusInWindow();
        });

        //saat tombol exit diklik, maka program berhenti
        exitButton.addActionListener(e -> System.exit(0));
    }

    //untuk membuat tampilan tombol
    private JButton createRetroButton(String text) {
        JButton button = new JButton(text);
        button.setFont(pixelFont.deriveFont(10f));
        button.setFocusPainted(false);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //menambahkan efek hover
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            //saat kursor berada di tombol
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setForeground(Color.BLACK); //teks menjadi hitam
                button.setBackground(Color.YELLOW); //latar menjadi kuning
            }
            //saat kursor tidak berada di tombol
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setForeground(Color.WHITE); //teks kembali menjadi putih
                button.setBackground(Color.BLACK); //latar kembali menjadi hitam
            }
        });
        return button;
    }
}
