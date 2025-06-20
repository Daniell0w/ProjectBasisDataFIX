package easbasdat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class userService {
private Connection conn;

    public userService() {
        this.conn = DBConnection.getConnection();
    }    

    public boolean login(String username, int password) {
        String query = "SELECT * FROM user_administration WHERE username = ? AND passcode = ?"; //validasi pw dan username
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username); 
            stmt.setInt(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // true jika ada data yang cocok
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat login: " + e.getMessage());
            return false;
        }
    }
}
