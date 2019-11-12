
package facebook_stats;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
 import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.DefaultIntervalCategoryDataset;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.json.JSONObject;
import sun.misc.BASE64Encoder;


public class Statistichepedaggio extends javax.swing.JFrame {
    public static String address = "localhost";
    public static String port = "8000";
    ArrayList<String> ascisse = new ArrayList<String>();
    ArrayList<String> ordinate = new ArrayList<String>();

    private void initUI() {
        JPanel content = new JPanel();
        content.setPreferredSize(new Dimension(1280, 900));
        DefaultCategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        chartPanel.setPreferredSize( new java.awt.Dimension( 600 , 300 ) );
        content.add(chartPanel);

        DefaultCategoryDataset dataset3 = createDatasetLine();
        JFreeChart chart3 = createLineChart(dataset3);
        ChartPanel chartPanel3 = new ChartPanel(chart3);
        chartPanel3.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel3.setBackground(Color.white);
        chartPanel3.setPreferredSize( new java.awt.Dimension( 600 , 300 ) );
        content.add(chartPanel3);
        
        DefaultPieDataset dataset2 = createPieDataset();
        JFreeChart chart2 = createPieChart(dataset2);
        ChartPanel chartPanel2 = new ChartPanel(chart2);
        chartPanel2.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel2.setBackground(Color.white);
        chartPanel2.setPreferredSize( new java.awt.Dimension( 600 , 300 ));
        content.add(chartPanel2);
        setContentPane(content);
        pack();
    }
    
    public String encrypt (String message, String key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
        byte[] byteMessage = message.getBytes();
        byte[] byteKey = key.getBytes();
        Key secretKey = new SecretKeySpec(byteKey, "AES");
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] cipher = c.doFinal(byteMessage);
        String encryptedMessage = new BASE64Encoder().encode(cipher);
        return encryptedMessage;
    }
    
    public static String generateKey(){
        String key="";
        Random rand = new Random();
        for (int i =0; i<16; i++){
            int c = rand.nextInt(122-48)+48;
            if((c>=58 && c<=64) | (c>=91 && c <=96)){
            i--;
            continue;
            }
            key+=((char)c);
        }
        return key;    
    }
    
    private JFreeChart createChart(DefaultCategoryDataset dataset) {

        JFreeChart chart = ChartFactory.createBarChart(
                "Grafico a barre", 
                "Legenda", 
                "Numero Veicoli", 
                createDataset(), 
                PlotOrientation.VERTICAL,
                true, 
                true, 
                false 
        );
        return chart;

    }
    
    private JFreeChart createLineChart(DefaultCategoryDataset dataset) {

        JFreeChart chart = ChartFactory.createLineChart(
                "Grafico a linee", 
                "Legenda", 
                "Numero Veicoli", 
                createDatasetLine(), 
                PlotOrientation.VERTICAL,
                true, 
                true, 
                false 
        );
        return chart;

    }
    
    private JFreeChart createPieChart(DefaultPieDataset dataset) {

        JFreeChart chart = ChartFactory.createPieChart(
                "Grafico a Torta", 
                createPieDataset(), 
                true, 
                true, 
                false 
        );
        return chart;

    }
    
    private DefaultPieDataset createPieDataset() {
            
      DefaultPieDataset dataset2 = new DefaultPieDataset();
      
      for(int i = 0; i < ascisse.size(); i++){
        dataset2.setValue(ascisse.get(i), Integer.parseInt(ordinate.get(i)));
      }

        return dataset2;
    }
    
    private DefaultCategoryDataset createDatasetLine() {
            
     DefaultCategoryDataset dataset3 = new DefaultCategoryDataset();
      
      for(int i = 0; i < ascisse.size(); i++){
        dataset3.addValue(Integer.parseInt(ordinate.get(i)),"Numero Veicoli", ascisse.get(i));
      }

        return dataset3;
    }
    
    private DefaultCategoryDataset createDataset() {
            
      final DefaultCategoryDataset dataset = 
      new DefaultCategoryDataset();
      
      for(int i = 0; i < ascisse.size(); i++){
        dataset.addValue(Integer.parseInt(ordinate.get(i)), ascisse.get(i),"Data");
      }

        return dataset;
    }
    
 
   public Statistichepedaggio(){
       initComponents(); 
   }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        primo = new com.toedter.calendar.JDateChooser();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        secondo = new com.toedter.calendar.JDateChooser();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        printplot = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(255, 204, 204));
        jTextField1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("Data Inizio");

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(255, 255, 204));
        jTextField2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText("Data Fine");

        jTextField3.setEditable(false);
        jTextField3.setBackground(new java.awt.Color(153, 153, 255));
        jTextField3.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setText("INSERIRE LE DATE PER DISEGNARE IL GRAFICO");

        jButton1.setBackground(new java.awt.Color(102, 255, 102));
        jButton1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jButton1.setText("Invio Dati");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        printplot.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        printplot.setText("Grafico");
        printplot.setEnabled(false);
        printplot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printplotActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(primo, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(101, 101, 101)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(secondo, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(printplot, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(primo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(secondo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(printplot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
       Date data1 = primo.getDate();
       Date data2 = secondo.getDate();
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       String prova1 = sdf.format(data1);
       String prova2 = sdf.format(data2);
       
       HttpURLConnection connection = null;
       
        try {
            String chiave = generateKey();
            String Url_Target = "http://" + address + ":" + port + "/graph/";
            JSONObject ogg1 = new JSONObject("{\"data_1\": \""+prova1+"\",\"data_2\": \""+prova2+"\"}");
            System.out.println("Chiave Invio " + chiave);
            String messaggioCriptato = encrypt(ogg1.toString(), chiave);
            System.out.println(messaggioCriptato);
            JSONObject ogg2 = new JSONObject("{\"chiave\": \""+chiave+"\",\"messaggio\": \""+messaggioCriptato+"\"}");
            //Create connection
            URL url = new URL(Url_Target);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            connection.setRequestProperty("Content-Length",
                    Integer.toString(ogg2.toString().getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches(false);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream(
            connection.getOutputStream());
            wr.writeBytes(ogg2.toString());
            wr.close();

            //Get Response  
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); 
            String line;
            
            while ((line = rd.readLine()) != null) {
                response.append(line);
                //response.append('\r');
            }
            String dati = response.toString();
            System.out.println(dati);
            List <String> grafico = Arrays.asList(dati.substring(1, dati.length() - 1).replaceAll("\\s", "").split(","));
            System.out.println(grafico);
            /*for(String s: grafico){
                System.out.println(s);
            }*/
            for(int i = 0; i < grafico.size(); i+=2){
                System.out.println(grafico.get(i));
                ascisse.add(grafico.get(i));
            }
            for(int j = 1; j< grafico.size(); j+=2){
                System.out.println(grafico.get(j));
                ordinate.add(grafico.get(j));
            }
            printplot.setEnabled(true);
            rd.close();
            JOptionPane.showMessageDialog(null, "Upload Completato");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "SERVER OFFLINE");
            e.printStackTrace();
            
        } 
    }//GEN-LAST:event_jButton1ActionPerformed

    private void printplotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printplotActionPerformed
       initUI();
    }//GEN-LAST:event_printplotActionPerformed

   
    public static void main(final String[] args) {
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
            java.util.logging.Logger.getLogger(StatisticheBlockchain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StatisticheBlockchain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StatisticheBlockchain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StatisticheBlockchain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Statistichepedaggio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private com.toedter.calendar.JDateChooser primo;
    private javax.swing.JButton printplot;
    private com.toedter.calendar.JDateChooser secondo;
    // End of variables declaration//GEN-END:variables
}
