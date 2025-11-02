import javax.swing.*;

public class App {
    public static void main(String[] args) {
        //membuat frame utama
        JFrame frame = new JFrame("Flappy Minion");
        //menghentikan program saat diclose
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //mengatur ukuran frame
        frame.setSize(360, 640) ;
        //mengatur posisi frame
        frame.setLocationRelativeTo(null);
        //membuat ukuran jendela tetap
        frame.setResizable(false);
        // frame.setVisible(true); (dipindahkan ke bawah)

        Logic logika = new Logic(); //instansiasi logic
        //instansiasi sehingga view bisa berkomunikas dengan logic
        View tampilan = new View(logika);
        //begitu pula kebalikannya
        logika.setview(tampilan);

        //membuat tampilan awal (main menu)
        Menu menu = new Menu(frame, tampilan);
        //menambahkan tampilan main menu ke frame
        frame.add(menu);
        //menampilkan frame
        frame.setVisible(true);
    }
}
