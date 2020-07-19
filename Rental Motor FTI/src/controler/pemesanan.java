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
public class pemesanan {
    private String IdTransaksi;
    private String IdPenyewa;
    private String IdMotor;
    private String PlatMotor;
    private String NamaMotor;
    private String Harga; 
    private String Lama;
    private String TglPakai;
    private String TglPengembalian;
    
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

    public String getIdMotor() {
        return IdMotor;
    }

    public void setIdMotor(String IdMotor) {
        this.IdMotor = IdMotor;
    }

    public String getPlatMotor() {
        return PlatMotor;
    }

    public void setPlatMotor(String PlatMotor) {
        this.PlatMotor = PlatMotor;
    }

    public String getNamaMotor() {
        return NamaMotor;
    }

    public void setNamaMotor(String NamaMotor) {
        this.NamaMotor = NamaMotor;
    }

    public String getHarga() {
        return Harga;
    }

    public void setHarga(String Harga) {
        this.Harga = Harga;
    }

    public String getLama() {
        return Lama;
    }

    public void setLama(String Lama) {
        this.Lama = Lama;
    }

    public String getTglPakai() {
        return TglPakai;
    }

    public void setTglPakai(String TglPakai) {
        this.TglPakai = TglPakai;
    }

    public String getTglPengembalian() {
        return TglPengembalian;
    }

    public void setTglPengembalian(String TglPengembalian) {
        this.TglPengembalian = TglPengembalian;
    }
    
}
