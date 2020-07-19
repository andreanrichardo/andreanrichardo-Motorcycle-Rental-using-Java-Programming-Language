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
public class pengembalian {
    private String IdTransaksi;
    private String IdPenyewa;
    private String TotalBayar;
    
    public String getIdTransaksi() {
        return IdTransaksi;
    }

    public void setIdTransaksi(String IdTransaksi) {
        this.IdTransaksi = IdTransaksi;
    }

    public String getIdPenyewa() {
        return IdPenyewa;
    }

    public void setIdPenyewa(String IdPenyewa) {
        this.IdPenyewa = IdPenyewa;
    }

    public String getTotalBayar() {
        return TotalBayar;
    }

    public void setTotalBayar(String TotalBayar) {
        this.TotalBayar = TotalBayar;
    }
    
}
