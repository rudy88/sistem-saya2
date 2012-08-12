/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import entity.Pejabat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author suryono
 */
//berguna untuk menampung data dari database terus ditampilkan ke tabel
public class TabelModelPejabat extends AbstractTableModel {
    
    List<Pejabat>list;

    public TabelModelPejabat(List<Pejabat> list) {
        this.list = list;
    }
    
    public Pejabat get(int row){
        return list.get(row);
    }
    //mendapatkan jumlah baris
    public int getRowCount() {
        return list.size();
    }
    //mendapatkan jumlah kolom
    public int getColumnCount() {
        return 2;
    }
    //mendapatkan no dan nama
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex) {
            case 0: return list.get(rowIndex).getNo();
            case 1: return list.get(rowIndex).getJeneng();
            default: return null;

        }
    }
    //untuk memberi nama kolom di tabel
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "Nomor";
            case 1: return "Nama";
              default : return null;

        }

    }

}
