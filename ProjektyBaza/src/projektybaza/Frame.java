
package projektybaza;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.RowFilter;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class Frame extends javax.swing.JFrame {
static int row;
int idState = 0;
int nameState = 0;
static private TableRowSorter<TableModel> rowSorter;
static String orderBy = "projekt_id asc";


public Frame() throws SQLException {
        initComponents();
        tabela.getTableHeader().addMouseListener(new java.awt.event.MouseAdapter() {
            //=========Metoda do wykrywania kliknięcia na nagłówek tabeli w celu sortowania===================\\
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    try {
                        headerMouseClicked(evt);
                    } catch (SQLException ex) {
                        Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jOptionPane1 = new javax.swing.JOptionPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        wyszukaj = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(350, 300));

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            ProjektyBaza.table,
            new String [] {
                "ID", "Nazwa", "Adres", "Opis"
            }));
            TableColumnModel columnModel = tabela.getColumnModel();
            columnModel.getColumn(0).setMaxWidth(30);
            columnModel.getColumn(1).setMaxWidth(150);
            columnModel.getColumn(2).setMaxWidth(240);
            columnModel.getColumn(2).setPreferredWidth(200);
            tabela.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
            tabela.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    tabelaMouseClicked(evt);
                }
            });
            jScrollPane1.setViewportView(tabela);

            jButton1.setText("DODAJ");
            jButton1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
                }
            });

            jButton2.setText("ODŚWIEŻ");
            jButton2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton2ActionPerformed(evt);
                }
            });

            jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel1.setText("Wyszukaj w tabeli:");

            wyszukaj.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    wyszukajKeyReleased(evt);
                }
            });

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jButton1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton2)
                    .addGap(36, 36, 36)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(wyszukaj, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(196, Short.MAX_VALUE))
                .addComponent(jScrollPane1)
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                    .addGap(60, 60, 60)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)
                            .addComponent(wyszukaj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
            );

            setBounds(0, 0, 716, 338);
        }// </editor-fold>//GEN-END:initComponents

    
    //===========Działanie po kliknięciu na Przycisk "DODAJ" =======================\\
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        AddFrame n = new AddFrame();
        n.setLocationRelativeTo(this);
        n.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    
    
    //===========Metoda wykrywająca który wiersz tabeli został kliknięty===============\\
    //===========Oraz który przycisk zoztał urzyty jeśli prawy to wywoływane jest menu=\\
    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked
        int roww = tabela.rowAtPoint(evt.getPoint());
        this.row = roww;
        if ((evt.getButton() == 3))
            new RightClickMenu(roww).show(this, evt.getX(), evt.getY());
    }//GEN-LAST:event_tabelaMouseClicked
    
    
    //=========Metoda obsługująca zdarzenia na nagłówkach tabeli - Sortowanie==========================\\
    private void headerMouseClicked(java.awt.event.MouseEvent evt) throws SQLException {
        int coll = tabela.columnAtPoint(evt.getPoint());
        if (coll == 0){
            if(idState == 0){
                orderBy = "projekt_id desc";
                idState = 1;
            }
            else if(idState ==1){
                orderBy = "projekt_id asc";
                idState = 0;
            }
        }
            else if(coll == 1){
                if(nameState == 0){
                    orderBy = "Nazwa desc";
                    nameState = 1;
                }
                else if (nameState == 1){
                    orderBy = "Nazwa asc";
                    nameState = 0;
                }
            }
        ProjektyBaza.updateTable(orderBy);
    }
    

//=========Odświeżanie tabeli ręcznie jeśli zostały zmienione dane w Bazie Danych przez inny program 
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            ProjektyBaza.updateTable(orderBy);
            
        } catch (SQLException ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    
//=========Event słuchający czy coś zostało wpisane do pola tekstowego "WYSZUKAJ"====================\\
//=============================Jeśli tak to tabela jest filtrowana===================================\\
    private void wyszukajKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_wyszukajKeyReleased
                tabela.setRowSorter(rowSorter);
        String text = wyszukaj.getText();
  if (text.trim().length() == 0) {
     rowSorter.setRowFilter(null);
  } else {
     rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
  }
  
    }//GEN-LAST:event_wyszukajKeyReleased

    
    
    
 //======================metoda main w której Wywoływane jest okno główne=============================\\
    public static void main(String args[]) throws SQLException {
    try {
        ProjektyBaza.StartConnect();
        
           
    } catch (IOException ex) {
        Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
    }
        ProjektyBaza.setTable(orderBy);
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Frame fr = new Frame();
                    rowSorter = new TableRowSorter<>(tabela.getModel());
                    tabela.setRowHeight(20);
                    fr.setLocationRelativeTo(null);
                    fr.setVisible(true);
//                        new Frame().setVisible(true);
                    
                } catch (SQLException ex) {
                    Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable tabela;
    private javax.swing.JTextField wyszukaj;
    // End of variables declaration//GEN-END:variables
    
    
   
    
    


}
