package easbasdat;
import java.sql.*;

public class notifikasiDAO {
private Connection conn;

    public notifikasiDAO(Connection conn) {
        this.conn = conn;
    }

    // Menampilkan semua notifikasi dari database
    public void tampilkanNotifikasi() {
        String sql = "SELECT * FROM notifikasi";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("Notifikasi: " + rs.getString("pesan"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    
}
