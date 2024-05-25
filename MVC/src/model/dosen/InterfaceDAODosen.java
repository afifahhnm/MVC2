/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.dosen;

import java.util.List;
import model.dosen.ModelDosen;

public interface InterfaceDAODosen {

    public void insert(ModelDosen dosen);
    
    public void update(ModelDosen dosen);
      
    public void delete(int id);
   
    public List<ModelDosen> getAll();
}

