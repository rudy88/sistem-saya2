/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

/**
 *
 * @author suryono
 */
public class Pejabat {
    //variabel yang ada untuk entitas sama dengan yang di database
    private int no;
    private String jeneng;
    //getter untuk nama
    public String getJeneng() {
        return jeneng;
    }
    //setter untuk nama
    public void setJeneng(String jeneng) {
        this.jeneng = jeneng;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }


}
