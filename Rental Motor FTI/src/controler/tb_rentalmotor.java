/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andre
 */
public class tb_rentalmotor {
    Connection con;

    public tb_rentalmotor(Connection con) {
        this.con = con;
    }
    public int totalPenyewa() {
        int total = 0;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM tb_penyewa");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(tb_rentalmotor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }
      public boolean insertPenyewa(penyewa pyw) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO tb_penyewa (ID_PENYEWA, NO_KTP, NAMA_PENYEWA, NO_TELPON, ALAMAT, JENIS_KELAMIN) "
                    + "VALUES (?, ?, ?, ?, ?,?)");
            ps.setString(1, pyw.getIdPenyewa());
            ps.setString(2, pyw.getNoKtp());
            ps.setString(3, pyw.getNamaPenyewa());
            ps.setString(4, pyw.getNoTelp());
            ps.setString(5, pyw.getAlamat());
            ps.setString(6, pyw.getJekel()+"");
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
      
      public List<penyewa> getAllPenyewa(String cari) {
        List<penyewa> listPenyewa = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tb_penyewa "
                    + "WHERE ID_PENYEWA LIKE '%" + cari + "%' OR NAMA_PENYEWA LIKE '%" + cari + "%'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                penyewa pyw = new penyewa();
                pyw.setIdPenyewa(rs.getString(1));
                pyw.setNoKtp(rs.getString(2));
                pyw.setNamaPenyewa(rs.getString(3));
                pyw.setNoTelp(rs.getString(4));
                pyw.setAlamat(rs.getString(5));
                pyw.setJekel(rs.getString(6).charAt(0));
                listPenyewa.add(pyw);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listPenyewa;
    }
      public boolean deletePenyewa(String IdPenyewa) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM tb_penyewa "
                    + "WHERE NO_KTP='" + IdPenyewa + "'");
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
      public boolean updatePenyewa(penyewa pyw, String NoKtpUpdate) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE tb_penyewa "
                    + "SET ID_PENYEWA=?, NO_KTP=?, NAMA_PENYEWA=?, NO_TELPON=?, ALAMAT=?, JENIS_KELAMIN =? "
                    + "WHERE NO_KTP=?");
            ps.setString(1, pyw.getIdPenyewa());
            ps.setString(2, pyw.getNoKtp());
            ps.setString(3, pyw.getNamaPenyewa());
            ps.setString(4, pyw.getNoTelp());
            ps.setString(5, pyw.getAlamat());
            ps.setString(6, pyw.getJekel() + "");
            ps.setString(7, NoKtpUpdate);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
      public int totalMotor() {
        int total = 0;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM tb_motor");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(tb_rentalmotor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }
      public List<Motor> getAllMotor(String cari) {
        List<Motor> listMotor = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tb_motor "
                    + "WHERE ID_MOTOR LIKE '%" + cari + "%' OR PLAT_MOTOR LIKE '%" + cari + "%'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Motor mt = new Motor();
                mt.setIdMotor(rs.getString(1));
                mt.setPlatMotor(rs.getString(2));
                mt.setNamaMotor(rs.getString(3));
                mt.setHarga(rs.getString(4));
                listMotor.add(mt);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listMotor;
    }
      public int totalPemesanan() {
        int total = 0;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM tb_transaksi");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(tb_rentalmotor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }
      public List<pemesanan> getAllPemesanan(String cari) {
        List<pemesanan> listPemesanan = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tb_transaksi "
                    + "WHERE ID_TRANSAKSI LIKE '%" + cari + "%' OR PLAT_MOTOR LIKE '%" + cari + "%'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pemesanan psn = new pemesanan();
                psn.setIdTransaksi(rs.getString(1));
                psn.setIdPenyewa(rs.getString(2));
                psn.setIdMotor(rs.getString(3));
                psn.setPlatMotor(rs.getString(4));
                psn.setNamaMotor(rs.getString(5));
                psn.setHarga(rs.getString(6));
                psn.setLama(rs.getString(7));
                psn.setTglPakai(rs.getString(8));
                psn.setTglPengembalian(rs.getString(9));
                listPemesanan.add(psn);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listPemesanan;
    }
      public boolean insertPemesanan(pemesanan psn) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO tb_transaksi (ID_TRANSAKSI, ID_PENYEWA, ID_MOTOR, PLAT_MOTOR, NAMA_MOTOR, HARGA, LAMA_SEWA, TGL_PAKAI, TGL_PENGEMBALIAN) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, psn.getIdTransaksi());
            ps.setString(2, psn.getIdPenyewa());
            ps.setString(3, psn.getIdMotor());
            ps.setString(4, psn.getPlatMotor());
            ps.setString(5, psn.getNamaMotor());
            ps.setString(6, psn.getHarga());
            ps.setString(7, psn.getLama());
            ps.setString(8, psn.getTglPakai());
            ps.setString(9, psn.getTglPengembalian());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
      public boolean insertPengembalian(pengembalian pgn) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO tb_riwayat (ID_TRANSAKSI, ID_PENYEWA, TOTAL_BAYAR) "
                    + "VALUES (?, ?, ?)");
            ps.setString(1, pgn.getIdTransaksi());
            ps.setString(2, pgn.getIdPenyewa());
            ps.setString(3, pgn.getTotalBayar());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    public int totalPengembalian() {
        int total = 0;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM tb_riwayat");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(tb_rentalmotor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }
      public List<pengembalian> getAllPengembalian(String cari) {
        List<pengembalian> listPengembalian = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tb_riwayat "
                    + "WHERE ID_TRANSAKSI LIKE '%" + cari + "%' OR ID_PENYEWA LIKE '%" + cari + "%'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pengembalian pgn = new pengembalian();
                pgn.setIdTransaksi(rs.getString(1));
                pgn.setIdPenyewa(rs.getString(2));
                pgn.setTotalBayar(rs.getString(3));
                
                listPengembalian.add(pgn);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listPengembalian;
    }
}
