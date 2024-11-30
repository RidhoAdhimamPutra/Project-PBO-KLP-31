-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 30 Nov 2024 pada 15.10
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `iq_test`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `soal`
--

CREATE TABLE `soal` (
  `id` int(11) NOT NULL,
  `bunyi_soal` varchar(255) NOT NULL,
  `opsi_a` varchar(255) NOT NULL,
  `opsi_b` varchar(255) NOT NULL,
  `opsi_c` varchar(255) NOT NULL,
  `opsi_d` varchar(255) NOT NULL,
  `opsi_e` varchar(255) NOT NULL,
  `jawaban` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `soal`
--

INSERT INTO `soal` (`id`, `bunyi_soal`, `opsi_a`, `opsi_b`, `opsi_c`, `opsi_d`, `opsi_e`, `jawaban`) VALUES
(1, 'Suatu seri: 100-4-90-7-80, seri selanjutnya adalah…', '8', '9', '10', '11', '12', 'C'),
(2, 'Suatu seri: 50-40-31-24-18, seri selanjutnya adalah…', '16', '15', '14', '16', '12', 'C'),
(3, 'Suatu seri: 9-5-1-2-10-6-2-3-11-7, seri selanjutnya adalah…', '3', '4', '12', '8', '7', 'A'),
(4, 'Suatu seri: 13-14-13-14-11-12-11-12-15-16-15-16-13, seri selanjutnya adalah…', '11-15-13', '12-16-14', '14-13-14', '14-15-13', '13-14-13', 'C'),
(5, '1,5,13,29,61,…,…', '125,253', '148,310', '153,312', '158,352', '158,328', 'A'),
(6, 'Penulisan gabungan kata yang sesuai dengan EYD dalam kalimat di bawah ini adalah…', 'Setiap hari raya Idul Fitri keluarga besar itu selalu mengadakan acara halalbihalal', 'Jika ingin membaca, ayah selalu harus mengenakan kaca mata', 'Adik saya selalu membawa sapu tangan hijau kesayangannya kalua sedang pergi ke luar kota', 'Mereka memberikan bantuan secara suka rela kepada para korban bencana alam itu', '', 'C'),
(7, 'Penulisan bilangan dalam kalimat berikut tepat, kecuali…', 'Penulisan bilangan tiga dua pertiga adalah 3 2/3.', 'Pada halaman 1.024 tertera nomor teleponnya 760130', 'Tamu undangan yang hadir dalam perhelatan itu diperkirakan mencapai 500 orang', 'Ulang tahun yang ke-20 adalah peristiwa penting bagi orang Jepang', '', 'B'),
(8, 'Kalimat yang mencerminkan penalaran yang baik adalah…', 'Karena kampanye yang melewati ruas jalan itu terjebak macet sehingga pertikaian antar simpatisan tidak dapat dihindarkan.', 'Dia yang tertua dari ketiga saudaranya yang kini sukses menjadi pengusaha', 'Harga foto kopi mengalami kenaikan drastis akhir-akhir ini.', 'Kontrak jasa bertujuan meningkatkan produksi ladang-ladang minyak di wilayah Nimar-Karim dengan biaya yang efektif', '', 'D'),
(9, 'Penulisan yang salah terdapat dalam kalimat…', 'Saya pun akan ikut memeriahkan acara itu', 'Siapakah di antara kita yang akan menjadi juara?', 'Walau pun banyak pekerjaan, ia tetap belajar', 'Sang merah putih telah melambai-lambai', '', 'C'),
(10, 'Bahtera = …', 'Pernikahan', 'Sejahtera', 'Bahagia', 'Perahu', 'Pertukaran', 'D'),
(11, 'Timpang = …', 'Kesal', 'Ganjil', 'Aneh', 'Cacat', 'Tak seimbang', 'E'),
(12, 'Homogen = …', 'Udara', 'Harmonis', 'Sepadan', 'Sejenis', 'Keturunan', 'D'),
(13, 'Sapta = …', 'Marga', 'Sumpah', 'Prasetya', 'Bilangan', 'Janji', 'D'),
(14, 'Kreasi = …', 'Kemampuan berpikir', 'Rencana', 'Kepandaian menari', 'Ciptaan', 'Seniman', 'D'),
(15, 'Kontradiksi = …', 'Penandatanganan kontrak', 'Pertentangan', 'Perjanjian', 'Perdebatan', 'Penghalang', 'B');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `soal`
--
ALTER TABLE `soal`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `soal`
--
ALTER TABLE `soal`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
