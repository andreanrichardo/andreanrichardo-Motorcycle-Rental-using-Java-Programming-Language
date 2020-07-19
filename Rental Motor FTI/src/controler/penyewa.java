/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

/**
 *
 * @author andre
 */
public class penyewa {

    private String IdPenyewa;
    private String NoKtp;
    private String NamaPenyewa;
    private String NoTelp;
    private String Alamat;
    private char jekel;

    public String getIdPenyewa() {
        return IdPenyewa;
    }

    public void setIdPenyewa(String IdPenyewa) {
        this.IdPenyewa = IdPenyewa;
    }
    public String getNoKtp() {
        return NoKtp;
    }

    public void setNoKtp(String NoKtp) {
        this.NoKtp = NoKtp;
    }

    public String getNamaPenyewa() {
        return NamaPenyewa;
    }

    public void setNamaPenyewa(String NamaPenyewa) {
        this.NamaPenyewa = NamaPenyewa;
    }

    public String getNoTelp() {
        return NoTelp;
    }

    public void setNoTelp(String NoTelp) {
        this.NoTelp = NoTelp;
    }

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String Alamat) {
        this.Alamat = Alamat;
    }

    public char getJekel() {
        return jekel;
    }

    public void setJekel(char jekel) {
        this.jekel = jekel;
    }   
}
