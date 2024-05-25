/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.dosen.EditDataDosen;
import View.dosen.InputDataDosen;
import View.dosen.ViewDataDosen;
import java.util.List;
import javax.swing.JOptionPane;
import model.dosen.DAODosen;
import model.dosen.InterfaceDAODosen;
import model.dosen.ModelDosen;
import model.dosen.ModelTableDosen;


public class ControllerDosen {
    
    ViewDataDosen halamanTable;
    InputDataDosen halamanInput;
    EditDataDosen halamanEdit;

    InterfaceDAODosen daoDosen;

    List<ModelDosen> daftarDosen;
    
    public ControllerDosen(ViewDataDosen halamanTable) {
        this.halamanTable = halamanTable;
        this.daoDosen = new DAODosen();
    }
    
    public ControllerDosen(InputDataDosen halamanInput) {
        this.halamanInput = halamanInput;
        this.daoDosen = new DAODosen();
    }
//    
    public ControllerDosen(EditDataDosen halamanEdit) {
        this.halamanEdit = halamanEdit;
        this.daoDosen = new DAODosen();
    }

    public void showAllDosen() {
 
        daftarDosen = daoDosen.getAll();

        ModelTableDosen table = new ModelTableDosen(daftarDosen);

        halamanTable.getTableDosen().setModel(table);
    }

public void insertDosen() throws Exception {
        try {

            ModelDosen dosenBaru = new ModelDosen();

            String nama = halamanInput.getInputNama();
            String nidn = halamanInput.getInputNIDN();
            
            if ("".equals(nama) || "".equals(nidn)) {
                throw new Exception("Nama atau NIDN tidak boleh kosong!");
           }

            dosenBaru.setNama(nama);
            dosenBaru.setNidn(nidn);
            
            daoDosen.insert(dosenBaru);

            JOptionPane.showMessageDialog(null, "Dosen baru berhasil ditambahkan.");
            
            halamanInput.dispose();
            new ViewDataDosen();
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public void editDosen(int id){
        try {

            ModelDosen dosenYangMauDiedit = new ModelDosen();

            String nama = halamanEdit.getInputNama();
            String nidn = halamanEdit.getInputNIDN();
            
            if ("".equals(nama) || "".equals(nidn)) {
                throw new Exception("Nama atau NIDN tidak boleh kosong!");
            }
            
            dosenYangMauDiedit.setId(id);
            dosenYangMauDiedit.setNama(nama);
            dosenYangMauDiedit.setNidn(nidn);

            daoDosen.update(dosenYangMauDiedit);

            JOptionPane.showMessageDialog(null, "Data Dosen berhasil diubah.");

            halamanEdit.dispose();
            new ViewDataDosen();
            } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public void deleteDosen(Integer baris) {

        Integer id = (int) halamanTable.getTableDosen().getValueAt(baris, 0);
        String nama = halamanTable.getTableDosen().getValueAt(baris, 1).toString();

        int input = JOptionPane.showConfirmDialog(
                null,
                "Hapus " + nama + "?",
                "Hapus Dosen",
                JOptionPane.YES_NO_OPTION
        );
        
    if (input == 0) {

            daoDosen.delete(id);

            JOptionPane.showMessageDialog(null, "Berhasil menghapus data.");
            
            showAllDosen();
        }
    }
}