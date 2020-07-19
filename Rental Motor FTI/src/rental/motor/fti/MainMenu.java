/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rental.motor.fti;

import controler.koneksi;
import controler.pengembalian;
import controler.penyewa;
import controler.tb_rentalmotor;
import controler.Motor;
import controler.pemesanan;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author andre
 */

    class Proses extends Thread {

    MainMenu m;

    public Proses(MainMenu m) {
        this.m = m;
    }

    @Override
    public void run() {
        while (true) {
            int totalTemp = new tb_rentalmotor(m.con).totalPenyewa();
            if (m.total != totalTemp) {
                m.total = totalTemp;
                
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Proses.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
public class MainMenu extends javax.swing.JFrame {
    
    Connection con;
    List<penyewa> listPenyewa = new ArrayList<>();
    List<Motor> listMotor = new ArrayList<>();
    List<pemesanan> listPemesanan = new ArrayList<>();
    List<pengembalian> listPengembalian = new ArrayList<>();
    String NoKtpPilih;
    String IdMotorPilih;
    int total;
    
    public MainMenu() {
        initComponents();
        con = new koneksi().getConnection();
        bindingTbPenyewa(null);
        bindingTbMotor(null);
        bindingTbPemesanan(null);
        bindingTbPengembalian(null);
        Thread t = new Thread(new Proses(this));
        t.start();
        new Timer().scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                Calendar c = Calendar.getInstance();
                lblWaktu.setText(c.get(Calendar.HOUR_OF_DAY) + " : " + c.get(Calendar.MINUTE) + " : " + c.get(Calendar.SECOND));
            }
        }, 0, 1000);
        
    }
    
    public void bindingTbPenyewa(String cari) {
        if (cari == null) {
            cari = "";
        }
        total = new tb_rentalmotor(con).totalPenyewa();
        listPenyewa = new tb_rentalmotor(con).getAllPenyewa(cari);
        Object[][] o = new Object[listPenyewa.size()][7];
        for (int i = 0; i < listPenyewa.size(); i++) {
            o[i][0] = (i + 1) + ".";
            o[i][1] = listPenyewa.get(i).getIdPenyewa();
            o[i][2] = listPenyewa.get(i).getNoKtp();
            o[i][3] = listPenyewa.get(i).getNamaPenyewa();
            o[i][4] = listPenyewa.get(i).getNoTelp();
            o[i][5] = listPenyewa.get(i).getAlamat();
            if (listPenyewa.get(i).getJekel() == 'L') {
                o[i][6] = "Laki-laki";
            } else {
                o[i][6] = "Perempuan";
            }
        }
        tbPenyewa.setModel(new javax.swing.table.DefaultTableModel(
                o,
                new String[]{
                    "No.", "ID Penyewa", "No KTP", "Nama Penyewa", "No Telpon", "Alamat", "Jenis Kelamin"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
    }
    public void bindingTbMotor(String cari) {
        if (cari == null) {
            cari = "";
        }
        total = new tb_rentalmotor(con).totalMotor();
        listMotor = new tb_rentalmotor(con).getAllMotor(cari);
        Object[][] o = new Object[listMotor.size()][5];
        for (int i = 0; i < listMotor.size(); i++) {
            o[i][0] = (i + 1) + ".";
            o[i][1] = listMotor.get(i).getIdMotor();
            o[i][2] = listMotor.get(i).getPlatMotor();
            o[i][3] = listMotor.get(i).getNamaMotor();
            o[i][4] = listMotor.get(i).getHarga();
        }
        tbMotor.setModel(new javax.swing.table.DefaultTableModel(
                o,
                new String[]{
                    "No.", "ID Motor", "Plat Motor", "Nama Motor", "Harga Per Hari"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
    }
    public void bindingTbPemesanan(String cari) {
        if (cari == null) {
            cari = "";
        }
        total = new tb_rentalmotor(con).totalPemesanan();
        listPemesanan = new tb_rentalmotor(con).getAllPemesanan(cari);
        Object[][] o = new Object[listPemesanan.size()][10];
        for (int i = 0; i < listPemesanan.size(); i++) {
            o[i][0] = (i + 1) + ".";
            o[i][1] = listPemesanan.get(i).getIdTransaksi();
            o[i][2] = listPemesanan.get(i).getIdPenyewa();
            o[i][3] = listPemesanan.get(i).getIdMotor();
            o[i][4] = listPemesanan.get(i).getPlatMotor();
            o[i][5] = listPemesanan.get(i).getNamaMotor();
            o[i][6] = listPemesanan.get(i).getHarga();
            o[i][7] = listPemesanan.get(i).getLama();
            o[i][8] = listPemesanan.get(i).getTglPakai();
            o[i][9] = listPemesanan.get(i).getTglPengembalian();
        }
        tbPemesanan.setModel(new javax.swing.table.DefaultTableModel(
                o,
                new String[]{
                    "No.", "ID Transaksi", "ID Penyewa", "ID Motor", "Plat Motor", "Nama Motor", "Harga Per Hari", "Lama Sewa", "Tanggal Pakai", "Tanggal Pengembalian"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
    }
    public void bindingTbPengembalian(String cari) {
        if (cari == null) {
            cari = "";
        }
        total = new tb_rentalmotor(con).totalPengembalian();
        listPengembalian = new tb_rentalmotor(con).getAllPengembalian(cari);
        Object[][] o = new Object[listPengembalian.size()][4];
        for (int i = 0; i < listPengembalian.size(); i++) {
            o[i][0] = (i + 1) + ".";
            o[i][1] = listPengembalian.get(i).getIdTransaksi();
            o[i][2] = listPengembalian.get(i).getIdPenyewa();
            o[i][3] = listPengembalian.get(i).getTotalBayar();
        }
        tbPengembalian.setModel(new javax.swing.table.DefaultTableModel(
                o,
                new String[]{
                    "No.", "ID Transaksi", "ID Penyewa", "Total Bayar"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
    }
    
        public void resetForm() {
        txtIdPenyewa.setText("");
        txtNoKtp.setText("");
        txtNama.setText("");
        txtAlamat.setText("");
        txtNoTelp.setText("");
        rbLaki.setSelected(true);
        btnInsert.setEnabled(true);
        
        }
        public void resetForm1() {
        txtIdTrans.setText("");
        txtIdPenyewa1.setText("");
        txtIdMotor.setText("");
        txtPlatMotor.setText("");
        txtNamaMotor.setText("");
        txtHarga.setText("");
        txtLama.setText("");
        txtTglPinjam.setText("");
        txtTglPengembalian.setText("");
        
        btnFinish.setEnabled(true);
        }
        public void resetForm2() {
        txtIdTrans1.setText("");
        txtIdPenyewaa.setText("");
        txtTotal.setText("");
        
        btnBayar.setEnabled(true);
        }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel25 = new javax.swing.JLabel();
        Jekel = new javax.swing.ButtonGroup();
        jPanel4 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAlamat = new javax.swing.JTextArea();
        jLabel18 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        rbLaki = new javax.swing.JRadioButton();
        txtNoTelp = new javax.swing.JTextField();
        rbPerempuan = new javax.swing.JRadioButton();
        btnInsert = new javax.swing.JButton();
        txtNama = new javax.swing.JTextField();
        txtNoKtp = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtIdPenyewa = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbPenyewa = new javax.swing.JTable();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtLama = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtIdMotor = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnFinish = new javax.swing.JButton();
        txtPlatMotor = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtNamaMotor = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbMotor = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtHarga = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtTglPinjam = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtIdPenyewa1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtIdTrans = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTglPengembalian = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tbPemesanan = new javax.swing.JTable();
        jLabel35 = new javax.swing.JLabel();
        txtIdTrans1 = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        txtIdPenyewaa = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        btnBayar = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPengembalian = new javax.swing.JTable();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblWaktu = new javax.swing.JLabel();

        jLabel25.setText("jLabel25");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(255, 255, 51));

        txtAlamat.setColumns(20);
        txtAlamat.setRows(5);
        jScrollPane3.setViewportView(txtAlamat);

        jLabel18.setFont(new java.awt.Font("Kozuka Gothic Pr6N B", 1, 18)); // NOI18N
        jLabel18.setText("RENTAL MOTOR");

        jLabel8.setText("NOMOR TELEPON");

        jLabel2.setText("NAMA");

        jLabel3.setText("ALAMAT");

        jLabel4.setText("JENIS KELAMIN");

        Jekel.add(rbLaki);
        rbLaki.setText("LAKI-LAKI");
        rbLaki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbLakiActionPerformed(evt);
            }
        });

        txtNoTelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNoTelpActionPerformed(evt);
            }
        });

        Jekel.add(rbPerempuan);
        rbPerempuan.setText("PEREMPUAN");

        btnInsert.setText("INSERT");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        txtNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaActionPerformed(evt);
            }
        });

        jLabel5.setText("NO KTP");

        jLabel11.setText("ID PENYEWA");

        tbPenyewa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "ID Penyewa", "No KTP", "Nama Penyewa", "No Telpon", "Alamat", "Jenis Kelamin"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbPenyewa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPenyewaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbPenyewa);

        btnUpdate.setText("UPDATE");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("DELETE");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 883, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnDelete)
                                .addGap(18, 18, 18)
                                .addComponent(btnUpdate)
                                .addGap(18, 18, 18)
                                .addComponent(btnInsert)
                                .addGap(106, 106, 106))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel11))
                                    .addGap(71, 71, 71)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtNoTelp)
                                        .addComponent(txtNoKtp)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(rbLaki)
                                            .addGap(18, 18, 18)
                                            .addComponent(rbPerempuan))
                                        .addComponent(txtIdPenyewa, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                                        .addComponent(txtNama)
                                        .addComponent(jScrollPane3)))))
                        .addGap(0, 227, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNoKtp, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbLaki)
                    .addComponent(rbPerempuan)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNoTelp, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtIdPenyewa, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInsert)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete))
                .addContainerGap())
        );

        jTabbedPane1.addTab("PEMINJAM", jPanel3);

        jPanel2.setBackground(new java.awt.Color(255, 255, 51));

        jLabel19.setText("PLAT MOTOR");

        jLabel6.setText("NAMA MOTOR");

        jLabel7.setText("HARGA PER HARI");

        btnFinish.setText("FINISH");
        btnFinish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinishActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Kozuka Gothic Pr6N B", 1, 18)); // NOI18N
        jLabel21.setText("RENTAL MOTOR");

        tbMotor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "ID Motor", "Plat Motor", "Nama Motor", "Harga Per Hari"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbMotor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMotorMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbMotor);

        jLabel22.setText("LAMA PINJAM");

        jLabel9.setText("TANGGAL PAKAI");

        jLabel23.setText("MOTOR YANG TERSEDIA");

        txtHarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHargaActionPerformed(evt);
            }
        });

        jLabel24.setText("ID MOTOR");

        jLabel13.setText("ID PENYEWA");

        jLabel12.setText("ID TRANSAKSI");

        jLabel10.setText("TANGGAL PENGEMBALIAN");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel12))
                                .addGap(65, 65, 65)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtPlatMotor, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtNamaMotor, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtIdMotor, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                                        .addComponent(txtTglPengembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(txtIdTrans, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel22))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(txtIdPenyewa1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel9)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtTglPinjam, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtLama, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(btnFinish))
                        .addGap(8, 8, 8)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel23)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdTrans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel22)
                    .addComponent(txtLama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtIdPenyewa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTglPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtIdMotor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txtTglPengembalian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtPlatMotor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNamaMotor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnFinish)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("PEMESANAN", jPanel2);

        jPanel7.setBackground(new java.awt.Color(255, 255, 51));

        tbPemesanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "ID Transaksi", "ID Penyewa", "ID Motor", "Plat Motor", "Nama Motor", "Harga Per Hari", "Lama Sewa", "Tanggal Pakai", "Tanggal Pengembalian"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbPemesanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPemesananMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tbPemesanan);

        jLabel35.setText("ID Transaksi");

        jLabel36.setText("ID Penyewa");

        jLabel37.setText("Total Bayar");

        btnBayar.setText("BAYAR");
        btnBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBayarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 883, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnBayar)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel35)
                                    .addComponent(jLabel36)
                                    .addComponent(jLabel37))
                                .addGap(73, 73, 73)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtIdTrans1)
                                    .addComponent(txtIdPenyewaa)
                                    .addComponent(txtTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(txtIdTrans1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(txtIdPenyewaa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addGap(18, 18, 18)
                .addComponent(btnBayar)
                .addGap(223, 223, 223))
        );

        jTabbedPane1.addTab("PENGEMBALIAN", jPanel7);

        jPanel8.setBackground(new java.awt.Color(255, 255, 51));

        tbPengembalian.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "ID Transaksi", "ID Penyewa", "Total Bayar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbPengembalian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPengembalianMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbPengembalian);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 883, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(298, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("RIWAYAT", jPanel8);
        jTabbedPane1.addTab("About Us", jTabbedPane4);

        jPanel1.setBackground(new java.awt.Color(0, 255, 255));

        jLabel1.setFont(new java.awt.Font("Kozuka Gothic Pr6N H", 0, 48)); // NOI18N
        jLabel1.setText("RENTAL MOTOR");

        lblWaktu.setText("jLabel11");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblWaktu)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(285, 285, 285)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lblWaktu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jTabbedPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtHargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHargaActionPerformed

    private void tbMotorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMotorMouseClicked
        int row = tbMotor.getSelectedRow();
        IdMotorPilih = listMotor.get(row).getIdMotor();
        if (row >= 0) {
            txtIdMotor.setText(listMotor.get(row).getIdMotor());
            txtPlatMotor.setText(listMotor.get(row).getPlatMotor());
            txtNamaMotor.setText(listMotor.get(row).getNamaMotor());
            txtHarga.setText(listMotor.get(row).getHarga());
        }                        // TODO add your handling code here:
    }//GEN-LAST:event_tbMotorMouseClicked

    private void btnFinishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinishActionPerformed
        pemesanan psn = new pemesanan();
        psn.setIdTransaksi(txtIdTrans.getText());
        psn.setIdPenyewa(txtIdPenyewa1.getText());
        psn.setIdMotor(txtIdMotor.getText());
        psn.setPlatMotor(txtPlatMotor.getText());
        psn.setNamaMotor(txtNamaMotor.getText());
        psn.setHarga(txtHarga.getText());
        psn.setLama(txtLama.getText());
        psn.setTglPakai(txtTglPinjam.getText());
        psn.setTglPengembalian(txtTglPengembalian.getText());

        if (new tb_rentalmotor(con).insertPemesanan(psn)) {
            JOptionPane.showMessageDialog(this, "Data pemesanan berhasil disimpan");
            resetForm1();
            bindingTbPemesanan(null);

        } else {
            JOptionPane.showMessageDialog(this, "Data pemesanan gagal disimpan !");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnFinishActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Apakah Anda akan delete penyewa ini ?", "Delete Penyewa", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (new tb_rentalmotor(con).deletePenyewa(NoKtpPilih)) {
                JOptionPane.showMessageDialog(this, "Data penyewa berhasil didelete");
                resetForm();
                bindingTbPenyewa(null);
            } else {
                JOptionPane.showMessageDialog(this, "Data penyewa gagal didelete");
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Apakah Anda akan update penyewa ini ?", "Update Penyewa", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            penyewa pyw = new penyewa();
            pyw.setIdPenyewa(txtIdPenyewa.getText());
            pyw.setNoKtp(txtNoKtp.getText());
            pyw.setNamaPenyewa(txtNama.getText());
            pyw.setNoTelp(txtNoTelp.getText());
            pyw.setAlamat(txtAlamat.getText());
            if (rbLaki.isSelected()) {
                pyw.setJekel('L');
            } else {
                pyw.setJekel('P');
            }
            if (new tb_rentalmotor(con).updatePenyewa(pyw, NoKtpPilih)) {
                JOptionPane.showMessageDialog(this, "Data penyewa berhasil diupdate");
                resetForm();
                bindingTbPenyewa(null);
            } else {
                JOptionPane.showMessageDialog(this, "Data penyewa gagal disimpan !");
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void tbPenyewaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPenyewaMouseClicked
        int row = tbPenyewa.getSelectedRow();
        NoKtpPilih = listPenyewa.get(row).getNoKtp();
        if (row >= 0) {
            txtIdPenyewa.setText(listPenyewa.get(row).getIdPenyewa());
            txtNoKtp.setText(listPenyewa.get(row).getNoKtp());
            txtNama.setText(listPenyewa.get(row).getNamaPenyewa());
            txtNoTelp.setText(listPenyewa.get(row).getNoTelp());
            txtAlamat.setText(listPenyewa.get(row).getAlamat());
            if (listPenyewa.get(row).getJekel() == 'L') {
                rbLaki.setSelected(true);
            } else {
                rbPerempuan.setSelected(true);
            }
            btnInsert.setEnabled(false);
            btnUpdate.setEnabled(true);
            btnDelete.setEnabled(true);
        }                        // TODO add your handling code here:
    }//GEN-LAST:event_tbPenyewaMouseClicked

    private void txtNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaActionPerformed

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        penyewa pyw = new penyewa();
        pyw.setIdPenyewa(txtIdPenyewa.getText());
        pyw.setNoKtp(txtNoKtp.getText());
        pyw.setNamaPenyewa(txtNama.getText());
        pyw.setNoTelp(txtNoTelp.getText());
        pyw.setAlamat(txtAlamat.getText());
        if (rbLaki.isSelected()) {
            pyw.setJekel('L');
        } else {
            pyw.setJekel('P');
        }
        if (new tb_rentalmotor(con).insertPenyewa(pyw)) {
            JOptionPane.showMessageDialog(this, "Data mahasiswa berhasil disimpan");
            resetForm();
            bindingTbPenyewa(null);

        } else {
            JOptionPane.showMessageDialog(this, "Data mahasiswa gagal disimpan !");
        }
    }//GEN-LAST:event_btnInsertActionPerformed

    private void txtNoTelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNoTelpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoTelpActionPerformed

    private void rbLakiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbLakiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbLakiActionPerformed

    private void tbPemesananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPemesananMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbPemesananMouseClicked

    private void btnBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBayarActionPerformed
        pengembalian pgn = new pengembalian();
        pgn.setIdTransaksi(txtIdTrans1.getText());
        pgn.setIdPenyewa(txtIdPenyewaa.getText());
        pgn.setTotalBayar(txtTotal.getText());

        if (new tb_rentalmotor(con).insertPengembalian(pgn)) {
            JOptionPane.showMessageDialog(this, "Berhasil Dibayar!");
            resetForm2();
            bindingTbPengembalian(null);

        } else {
            JOptionPane.showMessageDialog(this, "Tidak Berhasil Dibayar!");
        }              // TODO add your handling code here:
    }//GEN-LAST:event_btnBayarActionPerformed

    private void tbPengembalianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPengembalianMouseClicked

    }//GEN-LAST:event_tbPengembalianMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup Jekel;
    private javax.swing.JButton btnBayar;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDelete1;
    private javax.swing.JButton btnDelete2;
    private javax.swing.JButton btnFinish;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnInsert1;
    private javax.swing.JButton btnInsert2;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUpdate1;
    private javax.swing.JButton btnUpdate2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JLabel lblWaktu;
    private javax.swing.JRadioButton rbLaki;
    private javax.swing.JRadioButton rbLaki1;
    private javax.swing.JRadioButton rbLaki2;
    private javax.swing.JRadioButton rbPerempuan;
    private javax.swing.JRadioButton rbPerempuan1;
    private javax.swing.JRadioButton rbPerempuan2;
    private javax.swing.JTable tbMotor;
    private javax.swing.JTable tbPemesanan;
    private javax.swing.JTable tbPengembalian;
    private javax.swing.JTable tbPenyewa;
    private javax.swing.JTable tbPenyewa1;
    private javax.swing.JTable tbPenyewa2;
    private javax.swing.JTextArea txtAlamat;
    private javax.swing.JTextArea txtAlamat1;
    private javax.swing.JTextArea txtAlamat2;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtIdMotor;
    private javax.swing.JTextField txtIdPenyewa;
    private javax.swing.JTextField txtIdPenyewa1;
    private javax.swing.JTextField txtIdPenyewa2;
    private javax.swing.JTextField txtIdPenyewa3;
    private javax.swing.JTextField txtIdPenyewaa;
    private javax.swing.JTextField txtIdTrans;
    private javax.swing.JTextField txtIdTrans1;
    private javax.swing.JTextField txtLama;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNama1;
    private javax.swing.JTextField txtNama2;
    private javax.swing.JTextField txtNamaMotor;
    private javax.swing.JTextField txtNoKtp;
    private javax.swing.JTextField txtNoKtp1;
    private javax.swing.JTextField txtNoKtp2;
    private javax.swing.JTextField txtNoTelp;
    private javax.swing.JTextField txtNoTelp1;
    private javax.swing.JTextField txtNoTelp2;
    private javax.swing.JTextField txtPlatMotor;
    private javax.swing.JTextField txtTglPengembalian;
    private javax.swing.JTextField txtTglPinjam;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
