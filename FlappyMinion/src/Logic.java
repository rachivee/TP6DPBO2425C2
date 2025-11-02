import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Logic implements ActionListener, KeyListener {
    //mengatur ukuran frame
    int frameWidth = 360;
    int frameHeight = 640;

    //mengatur posisi dan ukuran player
    int playerStartPosX = frameWidth/4;
    int playerStartPosY = frameHeight/2;
    int playerWidth = 55;
    int playerHeight = 80;

    //mengatur posisi dan ukuran pipa
    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;

    //objek dalam game
    View view; //tampilan
    Image birdImage; //gambar karakter
    Player player; //objek player

    //tambahkan list pipa dan gambarnya
    Image lowerPipeImage;
    Image upperPipeImage;
    ArrayList<Pipe> pipes;

    //timer untuk update game dan memunculkan pipa
    Timer gameLoop;
    Timer pipesCooldown;

    int gravity = 1;
    int pipeVelocityX = -2;

    boolean gameStarted = false;
    boolean gameOver = false;

    public int score = 0;

    public Logic(){
        //ambil gambar karakter yang sudah disediakan
        birdImage = new ImageIcon(getClass().getResource("assets/karnion.png")).getImage();
        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);

        //ambil gambar pipa yang sudah disediakan
        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();
        //inisialisasi list pipa
        pipes = new ArrayList<Pipe>();

        //timer untuk menampilkan pipa
        pipesCooldown = new Timer(2000, new ActionListener() { //setiap 2 detik
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Pipa");
                placePipes(); //tambahkan pipa baru
            }
        });
        gameLoop = new Timer(1000/60, this);
    }

    public void setview(View view){
        this.view = view;
    }

    public Player getPlayer(){
        return player;
    }

    public ArrayList<Pipe> getPipes() {
        return pipes;
    }

    public void placePipes(){
        //menentukan posisi vertikal untuk pipa di atas secara acak
        int randomPosY = (int) (pipeStartPosY - pipeHeight/3 - Math.random() * (pipeHeight/2));
        //atur jarak antar pipa
        int openingSpace = frameHeight/3;

        //untuk pipa bagian atas
        Pipe upperPipe = new Pipe(pipeStartPosX, randomPosY, pipeWidth, pipeHeight, upperPipeImage, true);
        pipes.add(upperPipe);

        //untuk pipa bagian bawah
        Pipe lowerPipe = new Pipe(pipeStartPosX, (randomPosY + openingSpace + pipeHeight), pipeWidth, pipeHeight, lowerPipeImage, false);
        pipes.add(lowerPipe);
    }


    public void move() {
        // gerak player
        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY(player.getPosY() + player.getVelocityY());
        player.setPosY(Math.max(player.getPosY(), 0));
        // gerak pipa
        for (int i = 0; i < pipes.size(); i++){
            Pipe pipe = pipes.get(i);
            pipe.setPosX(pipe.getPosX() + pipeVelocityX);

            //cek apakah player menyentuh pipa
            if(checkCollision(player, pipe)){
                gameOver();
            }

            //kondisi jika player melewati pipa
            if (!pipe.isPassed() && pipe.getPosX() + pipe.getWidth() < player.getPosX() && pipe.isUpperPipe()) {
                pipe.setPassed(true);
                //skor bertambah satu
                score++;
            }
        }

        //game over juga jika player jatuh ke bawah
        if(player.getPosY() > frameHeight - player.getHeight()) {
            gameOver();
        }
    }

    //untuk mengecek apakah player menyentuh pipa
    public boolean checkCollision(Player player, Pipe pipe) {
        Rectangle playerRect = new Rectangle(player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight());
        Rectangle pipeRect = new Rectangle(pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight());
        //true jika dua objek bersinggungan
        return playerRect.intersects(pipeRect);
    }

    //kondisi game over
    public void gameOver() {
        gameLoop.stop(); //game berhenti
        pipesCooldown.stop(); //pipa berhenti muncul
        gameOver = true; //tandai game sudah selesai
        view.repaint();
    }

    //untuk restart game setelah game over
    public void restartGame() {
        //reset skor
        score = 0;
        //set kembali posisi player
        player.setPosX(playerStartPosX);
        player.setPosY(playerStartPosY);
        player.setVelocityY(0);
        //hapus pipa
        pipes.clear();
        //set status game
        gameOver = false;
        gameStarted = false;

        if(view != null) {
            //tampilkan ulang layar
            view.repaint();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(gameStarted && !gameOver){
            move(); //update posisi objek
            if (view != null) {
                view.repaint();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    //kondisi saat tombol ditekan
    public void keyPressed(KeyEvent e) {
        //jika spasi ditekan
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            //game dimulai saat space pertama kali ditekan
            if(!gameStarted) {
                gameStarted = true; //status mulai game menjadi true
                pipesCooldown.start(); //mulai munculkan pipa
                gameLoop.start();
            }
            //saat game sedang berjalan
            else if (!gameOver) {
                //posisi player akan ke atas (terbang)
                player.setVelocityY(-10);
            }
        }

        //jika sudah game over dan menekan tombol R
        if (gameOver && e.getKeyCode() == KeyEvent.VK_R) {
            //jalankan restart game
            restartGame();
        }
    }

    public void keyReleased(KeyEvent e) {}
}
