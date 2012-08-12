/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Pejabat;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author suryono
 */
//class untuk koneksi ke database
public class DaoPejabat {
    //url ke database

    private String url = "jdbc:mysql://localhost:3306/pejabat";
    //username database
    private String username = "root";
    //password database
    private String password = "";
    //variabel untuk membuat koneksi
    private Connection con;
    //untuk mendapatkan array dari pejabat
    private List<Pejabat> list;

    public DaoPejabat() {
        try {
            try {
                //mengenalkan driver
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            } catch (InstantiationException ex) {
                JOptionPane.showMessageDialog(null, "kesalahan " + ex);
            } catch (IllegalAccessException ex) {
                JOptionPane.showMessageDialog(null, "kesalahan : " + ex);
            }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "kesalahan : " + ex);
        }
    }

    public DaoPejabat(Connection con) {
        this.con = con;
    }
    //method untuk koneksi database

    public void connect() throws SQLException {
        this.con = DriverManager.getConnection(url, username, password);
    }
    //method untuk menutup koneksi ke database

    public void disconnect() throws SQLException {
        this.con.close();
    }
    //method mendapatkan data dari database pejabat dalam bentuk array

    public List<Pejabat> read() {
        try {
            //membuat statement
            Statement st = con.createStatement();
            String sql = "SELECT * FROM data_pejabat";
            //mendapatkan data dari tabel dalam bentuk result set
            ResultSet rs = st.executeQuery(sql);
            list = new ArrayList<Pejabat>();
            while (rs.next()) {
                Pejabat pj = new Pejabat();
                pj.setNo(rs.getInt("no"));
                pj.setJeneng(rs.getString("nama"));
                list.add(pj);
            }


        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "kesalahan pada dao : " + ex);
        }
        return list;

    }
//method untuk insert ke database

    public void insert(Pejabat pj) {
        try {
            String sql = "INSERT INTO data_pejabat VALUES(?,?)";
            PreparedStatement ps = this.con.prepareStatement(sql);
            ps.setInt(1, pj.getNo());
            ps.setString(2, pj.getJeneng());
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "kesalahan pada dao : " + ex);
        }
    }
//method untuk update ke database

    public void update(int no, Pejabat pj) {
        try {
            String sql = "UPDATE data_pejabat set no=?, nama=? WHERE no=?";
            PreparedStatement ps = this.con.prepareStatement(sql);
            ps.setInt(1, pj.getNo());
            ps.setString(2, pj.getJeneng());
            ps.setInt(3, no);
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "kesalahan pada dao : " + ex);
        }

    }
//method untuk delete database

    public void delete(int no) {
        try {
            String sql = "DELETE from data_pejabat WHERE no=?";
            PreparedStatement ps = this.con.prepareStatement(sql);
            ps.setInt(1, no);
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "kesalahan pada dao : " + ex);
        }


    }
//method untuk pencarian

    public List<Pejabat> read(int no ) {
        try {
            Statement st = con.createStatement();
            String sql = "SELECT * FROM data_pejabat WHERE no like?";
            list = new ArrayList<Pejabat>();
            PreparedStatement ps = this.con.prepareStatement(sql);
            ps.setString(1, "%" + no + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pejabat pj = new Pejabat();
                pj.setNo(rs.getInt("no"));
                pj.setJeneng(rs.getString("nama"));
                list.add(pj);

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "kesalahan pada dao : " + ex);
        }
        return list;
    }
}
