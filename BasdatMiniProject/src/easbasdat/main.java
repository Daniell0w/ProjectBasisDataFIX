package easbasdat;
import java.util.*;
import java.sql.*;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection conn = DBConnection.getConnection();
        userService userService = new userService();
        ruangRapatDAO ruangDAO = new ruangRapatDAO(conn);
        jadwalRapatDAO jadwalDAO = new jadwalRapatDAO(conn);
        pesertaDAO pesertaDAO = new pesertaDAO(conn);
        rekapDAO rekapDAO = new rekapDAO(conn);
        notifikasiDAO notifikasiDAO = new notifikasiDAO(conn);
        userAdminDAO userAdminDAO = new userAdminDAO(conn);
        boolean appRunning = true;
        while (appRunning) {
            System.out.println("=== Aplikasi Manajemen Ruang Rapat ===");
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            int password = scanner.nextInt();
            scanner.nextLine();

            if (userService.login(username, password)) {
                System.out.println("\nLogin berhasil!");
                boolean running = true;
                // Main Menu
                while (running) {
                    System.out.println("\n=== Main Menu ===");
                    System.out.println("1. Manajemen Jadwal Rapat");
                    System.out.println("2. Manajemen Ruang Rapat");
                    System.out.println("3. Rekap Ruang");
                    System.out.println("4. Manajemen Admin");
                    System.out.println("5. Logout");
                    System.out.print("Pilih menu: ");
                    String pilihan = scanner.nextLine();

                    switch (pilihan.toUpperCase()) {
                        case "1":
                            while (true) {
                                System.out.println("\n=== Manajemen Jadwal Rapat ===");
                                System.out.println("1. Buat Jadwal Rapat");
                                System.out.println("2. Cek Ruang Yang Tersedia");
                                System.out.println("3. Update Jadwal Rapat");
                                System.out.println("4. Batalkan Jadwal Rapat");
                                System.out.println("5. Kembali ke Menu Utama");
                                System.out.print("Pilih: ");
                                String aj = scanner.nextLine();

                                if (aj.equals("5")) break;

                                switch (aj) {
                                    case "1":
                                        System.out.print("ID Rapat: ");
                                        int idRapat = Integer.parseInt(scanner.nextLine());
                                        System.out.print("ID Ruang: ");
                                        int idRuang = Integer.parseInt(scanner.nextLine());
                                        System.out.print("ID Pemesan: ");
                                        int idPemesan = Integer.parseInt(scanner.nextLine());
                                        System.out.print("Tanggal (YYYY-MM-DD): ");
                                        String tanggal = scanner.nextLine();
                                        System.out.print("Waktu Mulai (HH:MM:SS): ");
                                        String mulai = scanner.nextLine();
                                        System.out.print("Waktu Selesai (HH:MM:SS): ");
                                        String selesai = scanner.nextLine();
                                        jadwalDAO.insertJadwal(idRapat, idRuang, idPemesan, tanggal, mulai, selesai);

                                        System.out.print("ID Peserta: ");
                                        int idPeserta = Integer.parseInt(scanner.nextLine());
                                        System.out.print("Nama Peserta: ");
                                        String namaPeserta = scanner.nextLine();
                                        pesertaDAO.insertPeserta(idPeserta, idRapat, namaPeserta);
                                        break;
                                    case "2":
                                        ruangDAO.getAllRuang().forEach(System.out::println);
                                        break;
                                    case "3":
                                        System.out.print("ID Rapat yang ingin diupdate: ");
                                        int idUpdate = Integer.parseInt(scanner.nextLine());
                                        System.out.print("Tanggal baru (YYYY-MM-DD): ");
                                        String tglBaru = scanner.nextLine();
                                        System.out.print("Waktu Mulai baru (HH:MM:SS): ");
                                        String mulaiBaru = scanner.nextLine();
                                        System.out.print("Waktu Selesai baru (HH:MM:SS): ");
                                        String selesaiBaru = scanner.nextLine();
                                        System.out.print("ID Ruang baru: ");
                                        int ruangBaru = Integer.parseInt(scanner.nextLine());
                                        jadwalDAO.updateJadwal(idUpdate, tglBaru, mulaiBaru, selesaiBaru, ruangBaru);
                                        break;
                                    case "4":
                                        System.out.print("ID Rapat yang ingin dihapus: ");
                                        int idHapus = Integer.parseInt(scanner.nextLine());
                                        jadwalDAO.deleteJadwal(idHapus);
                                        break;
                                }
                            }
                            break;
                        case "2":
                            while (true) {
                                System.out.println("\n=== Manajemen Ruang Rapat ===");
                                System.out.println("1. Tambah Ruang Rapat");
                                System.out.println("2. Hapus Ruang Rapat");
                                System.out.println("3. Lihat Semua Ruang Rapat");
                                System.out.println("4. Ubah Spesifikasi Ruang Rapat");
                                System.out.println("5. Kembali ke Menu Utama");
                                System.out.print("Pilih: ");
                                String rb = scanner.nextLine();

                                if (rb.equals("5")) break;

                                switch (rb) {
                                    case "1":
                                        System.out.print("ID Ruang: ");
                                        int id = Integer.parseInt(scanner.nextLine());
                                        System.out.print("Nama Ruang: ");
                                        String nama = scanner.nextLine();
                                        System.out.print("Kapasitas: ");
                                        int kapasitas = Integer.parseInt(scanner.nextLine());
                                        System.out.print("Lokasi: ");
                                        String lokasi = scanner.nextLine();
                                        ruangDAO.insertRuang(id, nama, kapasitas, lokasi);
                                        break;
                                    case "2":
                                        System.out.print("ID Ruang yang ingin dihapus: ");
                                        int idDel = Integer.parseInt(scanner.nextLine());
                                        ruangDAO.deleteRuang(idDel);
                                        break;
                                    case "3":
                                        ruangDAO.getAllRuang().forEach(System.out::println);
                                        break;
                                    case "4":
                                        System.out.print("ID Ruang: ");
                                        int idUbah = Integer.parseInt(scanner.nextLine());
                                        System.out.print("Nama Baru: ");
                                        String namaBaru = scanner.nextLine();
                                        System.out.print("Kapasitas Baru: ");
                                        int kapasitasBaru = Integer.parseInt(scanner.nextLine());
                                        System.out.print("Lokasi Baru: ");
                                        String lokasiBaru = scanner.nextLine();
                                        ruangDAO.updateRuang(idUbah, namaBaru, kapasitasBaru, lokasiBaru);
                                        break;
                                }
                            }
                            break;
                        case "3":
                            System.out.println("\n=== Rekap Ruang ===");
                            System.out.println("1. Mingguan");
                            System.out.println("2. Bulanan");
                            System.out.print("Pilih: ");
                            String jenis = scanner.nextLine();
                            rekapDAO.tampilkanRekap(jenis.equals("1") ? "mingguan" : "bulanan");
                            break;
                        case "4":
                            while (true) {
                                System.out.println("\n=== Manajemen Admin ===");
                                System.out.println("1. Lihat daftar user admin");
                                System.out.println("2. Ubah Password user admin");
                                System.out.println("3. Tambah User admin");
                                System.out.println("4. Delete User admin");
                                System.out.println("0. Kembali ke Menu Utama");
                                System.out.print("Pilih: ");
                                String ad = scanner.nextLine();

                                if (ad.equals("0")) break;

                                switch (ad) {
                                    case "1":
                                        userAdminDAO.tampilkanSemuaUser();
                                        break;
                                    case "2":
                                        System.out.print("ID User: ");
                                        int idUbahPw = Integer.parseInt(scanner.nextLine());
                                        System.out.print("Password baru: ");
                                        int newPass = Integer.parseInt(scanner.nextLine());
                                        userAdminDAO.ubahPassword(idUbahPw, newPass);
                                        break;
                                    case "3":
                                        System.out.print("ID User: ");
                                        int idNew = Integer.parseInt(scanner.nextLine());
                                        System.out.print("Username: ");
                                        String unameNew = scanner.nextLine();
                                        System.out.print("Password: ");
                                        int passNew = Integer.parseInt(scanner.nextLine());
                                        userAdminDAO.tambahUser(idNew, unameNew, passNew);
                                        break;
                                    case "4":
                                        System.out.print("ID User: ");
                                        int idDelUser = Integer.parseInt(scanner.nextLine());
                                        userAdminDAO.hapusUser(idDelUser);
                                        break;
                                }
                            }
                            break;
                        case "5":
                            running = false; // Logout
                            appRunning = false;
                            break;
                        default:
                            System.out.println("Pilihan tidak valid.");
                    }
                }
            } else {
                System.out.println("Login gagal. Username atau password salah.\n");
            }
        }
    }
}
