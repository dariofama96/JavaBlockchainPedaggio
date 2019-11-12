package httpserver;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;


public class HttpServerGUI extends javax.swing.JFrame {

    HTTPServer http = new HTTPServer();
    private static String address = "localhost";
    private static String port = "8000";
    private static String pathFile = "C://Users//Dario Famà//Documents//NetBeansProjects//HTTPServer//HTTPServer//files";
    /*
    Inserisco nel costruttore, la proceduta GetDatabaseFiles() in modo tale che non appena viene avviato il server, 
    vengono caricati dal database i record relaivi a numero di targa e pedaggio da pagare in relazione ad ogni veicolo.
    */
    public HttpServerGUI() throws ClassNotFoundException, SQLException {
        initComponents();
        this.uploadTextArea.setText(this.getNameOfFiles().toString()); //stampo nella textArea tutti i nomi dei file presenti nella cartella files del server.
        getDatabaseFiles();
    }
    
    public static void getDatabaseFiles() throws ClassNotFoundException, SQLException {
        Connection cn;
        Statement st;
        Class.forName("com.mysql.jdbc.Driver");
                        cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/detail?zeroDateTimeBehavior=convertToNull","root","");
                        st = cn.createStatement();
                        String sql = "SELECT * FROM tollprices";
                        ResultSet rs = st.executeQuery(sql);
                        ResultSetMetaData rsmd = rs.getMetaData();
                        int columnsNumber = rsmd.getColumnCount();
        while(rs.next()){
            Object[] row = new Object[columnsNumber];
            for (int i = 1; i <= columnsNumber; i++)
            {  
                row[i - 1] = rs.getObject(i);
            }
            ((DefaultTableModel) database.getModel()).insertRow(rs.getRow()-1,row);
        }
        cn.close();

    }
    
    public static ArrayList<File> getFileContent(int index) {
        File f = new File(pathFile);
        ArrayList<File> files = new ArrayList<File>(Arrays.asList(f.listFiles()));
        return files;
    }
    public static String getElem(int index) {
        File f = new File(pathFile);
        ArrayList<File> files = new ArrayList<File>(Arrays.asList(f.listFiles()));
        return files.get(index).toString();
    }
    public static String getNameOfFiles() {
        File folder = new File(pathFile);
        String[] fileNames = null;
        int indexOfFile = 0;
        ArrayList<String> List = new ArrayList<>();
        if (folder.isDirectory())
        {
           fileNames = folder.list();
           Collections.addAll(List, fileNames);
        }
        
        return List.toString().replace(",", "\n").replace("[", " ").replace("]", " ");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        uploadTextArea = new javax.swing.JTextArea();
        bottone_aggiorna = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        bottone_aggiorna1 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        database = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HTTP Server");
        setBackground(new java.awt.Color(255, 204, 102));

        jPanel1.setBackground(new java.awt.Color(255, 204, 102));

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("File caricati sul server:");

        uploadTextArea.setEditable(false);
        uploadTextArea.setColumns(20);
        uploadTextArea.setRows(5);
        jScrollPane1.setViewportView(uploadTextArea);

        bottone_aggiorna.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        bottone_aggiorna.setText("Aggiorna");
        bottone_aggiorna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bottone_aggiornaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel2.setText("File caricati sul database:");

        bottone_aggiorna1.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        bottone_aggiorna1.setText("Aggiorna");
        bottone_aggiorna1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bottone_aggiorna1ActionPerformed(evt);
            }
        });

        database.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Targa", "Pedaggio"
            }
        ));
        jScrollPane4.setViewportView(database);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1)))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bottone_aggiorna1)
                            .addComponent(bottone_aggiorna))
                        .addGap(55, 55, 55))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(bottone_aggiorna))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(bottone_aggiorna1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bottone_aggiornaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bottone_aggiornaActionPerformed
        this.uploadTextArea.setText(this.getNameOfFiles().toString());
    }//GEN-LAST:event_bottone_aggiornaActionPerformed

    private void bottone_aggiorna1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bottone_aggiorna1ActionPerformed
        try {
            DefaultTableModel newModel = (DefaultTableModel) database.getModel();
            while (newModel.getRowCount() > 0) {
                newModel.removeRow(0);
            }
            getDatabaseFiles();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HttpServerGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HttpServerGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bottone_aggiorna1ActionPerformed
    
   
private static final String API_ENDPOINT_ACCOUNT_INFO = "/v2/user";
        
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bottone_aggiorna;
    private javax.swing.JButton bottone_aggiorna1;
    private static javax.swing.JTable database;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea uploadTextArea;
    // End of variables declaration//GEN-END:variables
}
