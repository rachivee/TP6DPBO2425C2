# TP6DPBO2425C2
Saya Farah Maulida dengan NIM 2410024 mengerjakan Tugas Praktikum 6 dalam mata kuliah Desain dan Pemrograman Berbasis Objek untuk keberkahan-Nya maka saya tidak akan melakukan kecurangan seperti yang telah di spesifikasikan Aamiin.

# Alur Program
Program ini merupakan implementasi permainan Flappy Minion, sebuah game 2D sederhana yang terinspirasi dari Flappy Bird, tetapi dengan karakter utama berupa minion. Pemain mengendalikan karakter yang harus terbang melewati celah di antara pipa-pipa yang bergerak dari kanan ke kiri tanpa menabrak salah satunya. Program terdiri atas beberapa kelas utama, yaitu App sebagai titik awal program, Menu sebagai tampilan awal (main menu), View sebagai panel utama tempat permainan berlangsung, Logic sebagai pengendali logika permainan (game logic), lalu Player dan Pipe sebagai representasi objek di dalam permainan.

Berikut penjelasan untuk setiap kelasnya:

1. Kelas App
   Kelas App berfungsi sebagai awal eksekusi program Flappy Minion. Pada kelas ini dibuat sebuah panel yang menampilkan tampilan awal permainan. Terdapat dua tampilan, yaitu Menu sebagai tampilan awal seperti main menu dan View sebagai tampilan permainan. Ketika program dijalankan, panel Menu akan tampil terlebih dahulu menampilkan judul game serta tombol “Start Game” dan “Exit”. Jika pemain menekan tombol “Start Game”, tampilan akan berganti ke panel View, dan permainan dimulai.
   
2. Kelas Menu
   Kelas Menu.java berfungsi sebagai tampilan awal atau main menu dari permainan Flappy Minion. Kelas ini menampilkan elemen-elemen utama seperti judul game, gambar karakter, serta dua tombol interaktif yaitu “Start Game” dan “Exit”. Ketika pengguna menekan tombol “Start Game”, kelas ini akan mengganti konten pada frame utama dengan panel permainan (View) dan memulai proses permainan. Sebaliknya, tombol “Exit” digunakan untuk menutup aplikasi. 
   
3. Kelas View
   Kelas View.java berfungsi sebagai panel tempat jalannya permainan Flappy Minion. Kelas ini akan menampilkan seluruh elemen visual permainan, seperti karakter player, pipa-pipa sebagai rintangan, latar belakang, serta skor yang diperoleh. Di dalam kelas ini juga akan memperbarui tampilan setiap kali ada perubahan posisi saat permainan berjalan. Selain itu, kelas ini menerima input dari keyboard, seperti tombol spasi untuk membuat karakter melompat, yang diteruskan ke kelas Logic sebagai pengendali utama permainan.
  
4. Kelas Logic
   Kelas Logic merupakan “otak” dari permainan Flappy Minion. Kelas ini mengatur seluruh mekanisme dan aturan permainan, termasuk pergerakan karakter, efek gravitasi, pergerakan pipa, collision detection, sistem skor, serta kondisi ketika permainan berakhir. Di dalamnya digunakan dua buah timer, yaitu gameLoop yang memperbarui posisi objek secara berkala, dan pipesCooldown yang mengatur kemunculan pipa baru setiap beberapa detik. Ketika pemain menekan tombol spasi, Logic akan mengubah kecepatan vertikal karakter agar tampak seperti melompat. Jika karakter menyentuh pipa atau tanah, permainan dihentikan, dan status game over diaktifkan. Selain itu, kelas ini juga menyediakan fungsi untuk memulai ulang permainan (restart game).
  
5. Kelas Player
   Kelas Player.java berfungsi untuk merepresentasikan karakter utama dalam permainan Flappy Minion, yaitu Minion yang dapat terbang melewati pipa-pipa. Kelas ini menyimpan informasi seperti posisi koordinat (posX, posY), ukuran karakter (width, height), serta kecepatan gerak vertikal (velocityY) yang dipengaruhi oleh gravitasi. Selain itu, kelas ini juga memuat gambar karakter yang akan ditampilkan di layar, serta menyediakan getter dan setter untuk mengatur dan mengambil nilai-nilai atribut tersebut.
    
6. Kelas Pipe
   Kelas Pipe.java berfungsi sebagai representasi dari rintangan utama dalam permainan Flappy Minion. Setiap objek Pipe terdiri dari dua bagian, yaitu pipa atas dan pipa bawah, dengan jarak di antaranya yang menjadi celah bagi pemain untuk lewat. Kelas ini menyimpan atribut seperti posisi horizontal, tinggi pipa, lebar, jarak antar pipa, dan lain-lain.

Program Flappy Minion ini memiliki beberapa fitur utama, yaitu:

Fitur pertama adalah panel Main Menu, yaitu tampilan awal permainan yang berfungsi sebagai antarmuka utama sebelum permainan dimulai. Pada panel ini terdapat judul Flappy Minion, gambar karakter, serta dua tombol utama yaitu Start Game untuk memulai permainan dan Exit untuk keluar dari aplikasi.

Fitur kedua adalah tampilan skor yang bertambah secara otomatis. Saat permainan berlangsung, skor akan meningkat setiap kali pemain berhasil melewati satu pasang pipa tanpa menabrak. Skor ini ditampilkan di layar secara real time.

Fitur ketiga restart setelah game over. Ketika karakter menabrak pipa atau jatuh ke tanah, permainan akan berhenti dan menampilkan kondisi game over. Pemain kemudian dapat memulai kembali permainan tanpa harus menutup aplikasi.
   
# Dokumentasi

![TP6_GIF](https://github.com/user-attachments/assets/f4a1bbd7-5cc8-4d44-b361-a573bf894259)
