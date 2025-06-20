package easbasdat;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ruangRapatDAO {
 private Connection conn;

    // Konstruktor untuk inisialisasi koneksi
    public ruangRapatDAO(Connection conn) {
        this.conn = conn;
    }

    // Menambahkan data ruang rapat baru
    public void insertRuang(int id, String nama, int kapasitas, String lokasi) {
        String sql = "INSERT INTO ruang_rapat VALUES (?, ?, ?, ?, 'Kosong')";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setString(2, nama);
            stmt.setInt(3, kapasitas);
            stmt.setString(4, lokasi);
            stmt.executeUpdate();
            System.out.println("Ruang berhasil ditambahkan.");
        } catch (SQLException e) {
            System.out.println("Gagal menambahkan ruang: " + e.getMessage());
        }
    }

    // Menghapus ruang rapat berdasarkan ID
    public void deleteRuang(int id) {
        String sql = "DELETE FROM ruang_rapat WHERE id_ruang = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Ruang berhasil dihapus.");
        } catch (SQLException e) {
            System.out.println("Gagal menghapus ruang: " + e.getMessage());
        }
    }

    // Mengubah data ruang rapat berdasarkan ID
    public void updateRuang(int id, String nama, int kapasitas, String lokasi) {
        String sql = "UPDATE ruang_rapat SET nama_ruang=?, kapasitas=?, lokasi=? WHERE id_ruang=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nama);
            stmt.setInt(2, kapasitas);
            stmt.setString(3, lokasi);
            stmt.setInt(4, id);
            stmt.executeUpdate();
            System.out.println("Ruang berhasil diupdate.");
        } catch (SQLException e) {
            System.out.println("Gagal mengupdate ruang: " + e.getMessage());
        }
    }

    // Mengambil dan menampilkan semua data ruang rapat
    public List<String> getAllRuang() {
        List<String> ruangList = new ArrayList<>();
        String sql = "SELECT * FROM ruang_rapat";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String ruang = String.format("ID: %d | Nama: %s | Kapasitas: %d | Lokasi: %s | Status: %s",
                        rs.getInt("id_ruang"),
                        rs.getString("nama_ruang"),
                        rs.getInt("kapasitas"),
                        rs.getString("lokasi"),
                        rs.getString("status"));
                ruangList.add(ruang);
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data ruang: " + e.getMessage());
        }
        return ruangList;
    }
}