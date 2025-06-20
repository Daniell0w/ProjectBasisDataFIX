package easbasdat;
import java.sql.*;

public class userAdminDAO {
     private Connection conn;

    public userAdminDAO(Connection conn) {
        this.conn = conn;
    }

    // Menampilkan semua user admin
    public void tampilkanSemuaUser() {
        String sql = "SELECT * FROM user_administration";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id_user") +
                                   ", Username: " + rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Menambah user admin baru
    public void tambahUser(int id, String username, int password) {
        String sql = "INSERT INTO user_administration VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setString(2, username);
            stmt.setInt(3, password);
            stmt.executeUpdate();
            System.out.println("User admin berhasil ditambahkan.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Mengubah password user admin
    public void ubahPassword(int id, int newPass) {
        String sql = "UPDATE user_administration SET passcode=? WHERE id_user=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, newPass);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            System.out.println("Password berhasil diubah.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Menghapus user admin
    public void hapusUser(int id) {
        String sql = "DELETE FROM user_administration WHERE id_user=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("User admin berhasil dihapus.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
