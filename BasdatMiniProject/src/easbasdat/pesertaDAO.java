package easbasdat;
import java.sql.*;

public class pesertaDAO {
private Connection conn;

    public pesertaDAO(Connection conn) {
        this.conn = conn;
    }

    // Menambahkan peserta ke rapat
    public void insertPeserta(int id, int idRapat, String nama) {
        String sql = "INSERT INTO peserta_rapat VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setInt(2, idRapat);
            stmt.setString(3, nama);
            stmt.executeUpdate();
            System.out.println("Peserta berhasil ditambahkan.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    
}
