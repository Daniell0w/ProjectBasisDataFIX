package easbasdat;
import java.sql.*;

public class rekapDAO {
    private Connection conn;

    public rekapDAO(Connection conn) {
        this.conn = conn;
    }

    // Memanggil stored procedure berdasarkan jenis rekap (mingguan/bulanan)
    public void tampilkanRekap(String jenis) {
        String call = "{CALL rekap_penggunaan_ruang(?)}";
        try (CallableStatement stmt = conn.prepareCall(call)) {
            stmt.setString(1, jenis.toLowerCase()); // 'mingguan' atau 'bulanan'
            ResultSet rs = stmt.executeQuery();

            System.out.println("\n=== Rekap Penggunaan Ruang (" + jenis + ") ===");

            while (rs.next()) {
                String namaRuang = rs.getString("nama_ruang");
                int waktu = rs.getInt(2); // Kolom kedua bisa WEEK atau MONTH
                int total = rs.getInt("total_penggunaan");
                String label = jenis.equalsIgnoreCase("mingguan") ? "Minggu Ke-" : "Bulan Ke-";
                System.out.println("Ruang: " + namaRuang + 
                                   " | " + label + waktu +
                                   " | Total Penggunaan: " + total);
            }

        } catch (SQLException e) {
            System.err.println("Gagal mengambil data rekap: " + e.getMessage());
        }
    }
}