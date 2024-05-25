/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.mahasiswa;

import model.Connector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Connector;

public class DAOMahasiswa implements InterfaceDAOMahasiswa {

    @Override
    public void insert(ModelMahasiswa mahasiswa) {
       try {
            
            String query = "INSERT INTO students (nama, nim) VALUES (?, ?);";

            PreparedStatement statement;
            statement = Connector.Connect().prepareStatement(query);
            statement.setString(1, mahasiswa.getNama());
            statement.setString(2, mahasiswa.getNim());
            
            statement.executeUpdate();
            
            statement.close();
        } catch (SQLException e) {
            
            System.out.println("Input Failed: " + e.getLocalizedMessage());
        } 
    }

    @Override
    public void update(ModelMahasiswa mahasiswa) {
        try {
            
            String query = "UPDATE students SET nama=?, nim=? WHERE id=?;";

            PreparedStatement statement;
            statement = Connector.Connect().prepareStatement(query);
            statement.setString(1, mahasiswa.getNama());
            statement.setString(2, mahasiswa.getNim());
            statement.setInt(3, mahasiswa.getId());
            
            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {

            System.out.println("update Failed! (" + e.getMessage() + ")");
        }
    }

    @Override
    public void delete(int id) {
        try {
            
            String query = "DELETE FROM students WHERE id=?;";
            
            PreparedStatement statement;
            statement = Connector.Connect().prepareStatement(query);
            statement.setInt(1, id);
                
            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
           
            System.out.println("Delete Failed: " + e.getLocalizedMessage());
        }
    }

    @Override
    public List<ModelMahasiswa> getAll() {
         List<ModelMahasiswa> listMahasiswa = null;

        try {

            listMahasiswa = new ArrayList<>();

            Statement statement = Connector.Connect().createStatement();

            String query = "SELECT * FROM students;";

            ResultSet resultSet = statement.executeQuery(query);
    
            while (resultSet.next()) {
                
                ModelMahasiswa mhs = new ModelMahasiswa();

                mhs.setId(resultSet.getInt("id"));
                mhs.setNama(resultSet.getString("nama"));
                mhs.setNim(resultSet.getString("nim"));

                listMahasiswa.add(mhs);
            }
    
            statement.close();
        } catch (SQLException e) {
            
            System.out.println("Error: " + e.getLocalizedMessage());
        }
        return listMahasiswa;
    }
}