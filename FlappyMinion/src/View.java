import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.io.InputStream;

public class View extends JPanel {
    // lebar dan tinggi layar
    int width = 360;
    int height = 640;

    private Logic logic;
    Image background;
    private JLabel scoreLabel;

    public View(Logic logic){
        this.logic = logic; //memasukkan ke dalam konstruktor
        setPreferredSize(new Dimension(width, height));

        //tambahkan fokus dan key listener agar bisa menerima input keyboard
        setFocusable(true);
        addKeyListener(logic);

        //membuat gambar untuk background
        background = new ImageIcon(getClass().getResource("assets/yellowbg.jpg")).getImage();

        //membuat tulisan skor dengan font yang sudah disediakan
        try {
            InputStream is = getClass().getResourceAsStream("/assets/PressStart2P-Regular.ttf");
            Font pixelFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(14f);
            scoreLabel = new JLabel("SCORE: 0");
            scoreLabel.setFont(pixelFont);
            scoreLabel.setForeground(Color.black);
        } catch (Exception e) {
            e.printStackTrace();
            //alternatif jika tidak ada font
            scoreLabel = new JLabel("SCORE: 0");
            scoreLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
            scoreLabel.setForeground(Color.black);
        }
        //posisi tulisan skor
        scoreLabel.setBounds(20, 20, 200, 40);
        setLayout(null);
        add(scoreLabel); //menampilkan tulisan skor
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
        //untuk mengupdate skor
        scoreLabel.setText("Score: " + logic.score);
    }

    public void draw(Graphics g){
        //menggunakan gambar background
        if(background != null){
            g.drawImage(background, 0, 0, width, height, null);
        } else {
            //alternatif jika tidak ditemukan
            g.setColor(Color.ORANGE);
        }

        //menggambar karakter player
        Player player = logic.getPlayer();
        if(player != null){
            g.drawImage(player.getImage(), player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight(), null);
        }

        //menggambar pipa atau rintangannya
        ArrayList<Pipe> pipes = logic.getPipes();
        if(pipes != null) {
            for(int i = 0; i < pipes.size(); i++) {
                Pipe pipe = pipes.get(i);
                g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight(), null);
            }
        }

        //kondisi jika sudah game over akan menampilkan tulisan game over dan restart
        if (logic.gameOver) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            //load font pixel
            Font pixelFont;
            try {
                InputStream is = getClass().getResourceAsStream("/assets/PressStart2P-Regular.ttf");
                pixelFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(30f);
            } catch (Exception e) {
                e.printStackTrace();
                //alternatif jika font tidak ditemukan
                pixelFont = new Font("Monospaced", Font.BOLD, 30);
            }
            g2.setFont(pixelFont);

            String text = "GAME OVER";
            //membuat posisi text game over di tengah
            FontMetrics fm = g2.getFontMetrics();
            int textWidth = fm.stringWidth(text);
            int x = (width - textWidth) / 2;
            int y = (height) / 2;

            //untuk efek shadow
            g2.setColor(Color.black);
            g2.drawString(text, x + 2, y + 2);
            //untuk tulisan utamanya
            g2.setColor(Color.red);
            g2.drawString(text, x, y);

            //menampilkan tulisan untuk restart
            g2.setFont(pixelFont.deriveFont(10f));
            String instruction = "Press R to Restart";
            //atur posisi tulisan
            int instrWidth = g2.getFontMetrics().stringWidth(instruction);
            int instrX = (width - instrWidth) / 2;
            int instrY = y + 30;
            g2.setColor(Color.black);
            g2.drawString(instruction, instrX, instrY);
        }
    }
}
