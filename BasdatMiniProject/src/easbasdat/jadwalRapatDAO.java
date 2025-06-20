package easbasdat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class jadwalRapatDAO {
    private Connection conn;

    
    //Konstruktor untuk inisialisasi koneksi database.
    public jadwalRapatDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Menambahkan jadwal rapat baru ke database.
     * idRapat ID unik rapat.
     * idRuang ID ruang rapat yang digunakan.
     * idPemesan ID admin atau user yang memesan.
     * tanggal Tanggal rapat dalam format YYYY-MM-DD.
     * waktuMulai Waktu mulai dalam format HH:MM:SS.
     * waktuSelesai Waktu selesai dalam format HH:MM:SS.
     */
    public void insertJadwal(int idRapat, int idRuang, int idPemesan, String tanggal, String waktuMulai, String waktuSelesai) {
        String query = "INSERT INTO jadwal_rapat (id_rapat, id_ruang, id_pemesan, tanggal, waktu_mulai, waktu_selesai) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idRapat);
            stmt.setInt(2, idRuang);
            stmt.setInt(3, idPemesan);
            stmt.setString(4, tanggal);
            stmt.setString(5, waktuMulai);
            stmt.setString(6, waktuSelesai);
            stmt.executeUpdate();
            System.out.println("Jadwal rapat berhasil ditambahkan.");
        } catch (SQLException e) {
            System.out.println("Gagal menambahkan jadwal rapat: " + e.getMessage());
        }
    }

    /**
     * Mengupdate jadwal rapat yang sudah ada di database.
     * idRapat ID rapat yang ingin diubah.
     * tanggal Tanggal baru.
     * waktuMulai Waktu mulai baru.
     * waktuSelesai Waktu selesai baru.
     * idRuang ID ruang rapat baru.
     */
    public void updateJadwal(int idRapat, String tanggal, String waktuMulai, String waktuSelesai, int idRuang) {
        String query = "UPDATE jadwal_rapat SET tanggal=?, waktu_mulai=?, waktu_selesai=?, id_ruang=? WHERE id_rapat=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, tanggal);
            stmt.setString(2, waktuMulai);
            stmt.setString(3, waktuSelesai);
            stmt.setInt(4, idRuang);
            stmt.setInt(5, idRapat);
            stmt.executeUpdate();
            System.out.println("Jadwal rapat berhasil diperbarui.");
        } catch (SQLException e) {
            System.out.println("Gagal memperbarui jadwal rapat: " + e.getMessage());
        }
    }

    /**
     * Menghapus jadwal rapat berdasarkan ID rapat.
     * idRapat ID rapat yang ingin dihapus.
     */
    public void deleteJadwal(int idRapat) {
        String query = "DELETE FROM jadwal_rapat WHERE id_rapat=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idRapat);
            stmt.executeUpdate();
            System.out.println("Jadwal rapat berhasil dihapus.");
        } catch (SQLException e) {
            System.out.println("Gagal menghapus jadwal rapat: " + e.getMessage());
        }
    }
}
