/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Connector;

public class ControllerLogin {

    public boolean authenticate(String username, String password) {
        // Mendeklarasikan objek Connection dan PreparedStatement
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Membuat koneksi ke database
            connection = Connector.Connect();

            // Menyiapkan query SQL
            String query = "SELECT * FROM login WHERE username = ? AND password = ?";

            // Membuat prepared statement dari query
            statement = connection.prepareStatement(query);

            // Mengatur parameter pada prepared statement
            statement.setString(1, username);
            statement.setString(2, password);

            // Menjalankan query dan mendapatkan hasil
            ResultSet resultSet = statement.executeQuery();

            // Jika hasil query mengembalikan baris, berarti login berhasil
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Menutup koneksi dan statement untuk menghindari kebocoran sumber daya
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Jika tidak ada hasil dari query, maka login gagal
        return false;
    }
}
