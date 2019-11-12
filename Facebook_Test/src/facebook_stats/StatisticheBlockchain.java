package facebook_stats;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.BorderFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class StatisticheBlockchain extends javax.swing.JFrame {
    public static String address = "localhost";
    public static String port = "8000";
    ArrayList<String> ascisse = new ArrayList<String>();
    ArrayList<String> ordinate = new ArrayList<String>();
    
    public StatisticheBlockchain() {
        initComponents();
        //initUI();
    }
    
    private void initUI() {
        CategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        chartPanel.setPreferredSize( new java.awt.Dimension( 800 , 600 ) );
        setContentPane(chartPanel);
        pack();
    }
    
    private JFreeChart createChart(CategoryDataset dataset) {

        JFreeChart chart = ChartFactory.createBarChart(
                "Blocchi con pedaggio", 
                "Legenda", 
                "Pedaggio", 
                createDataset(), 
                PlotOrientation.VERTICAL,
                true, 
                true, 
                false 
        );
        return chart;

    }
    
    private CategoryDataset createDataset() {
            
      final DefaultCategoryDataset dataset = 
      new DefaultCategoryDataset();
      
      for(int i = 0; i < ascisse.size(); i++){
        dataset.addValue(Float.parseFloat(ordinate.get(i)), ascisse.get(i),"Hash Blocco");
      }

        return dataset;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mostra = new javax.swing.JButton();
        mostra1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mostra.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        mostra.setText("Ottieni Dati");
        mostra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostraActionPerformed(evt);
            }
        });

        mostra1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        mostra1.setText("Grafico");
        mostra1.setEnabled(false);
        mostra1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostra1ActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/facebook_stats/block.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(mostra1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(160, 160, 160))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(mostra, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(135, 135, 135))))
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 91, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mostra, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(mostra1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mostraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostraActionPerformed
        try {
            /*Effettuo una richiesta di HTTP GET per ottenere i dati presenti nel file chain.txt
              che mi saranno restituiti dal server.
            */
                String targetURL = "http://" + address + ":" + port + "/gchain/";
		URL obj = new URL(targetURL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		int responseCode = con.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
                                response.append('\n');
			}
			in.close();

			// Stampo l'http response
                        String dati = response.toString();
                        System.out.println(dati);
                        List <String> grafico = Arrays.asList(dati.substring(1, dati.length() - 1).replaceAll("\\s", "").split(","));
                        for(int i = 0; i < grafico.size(); i+=2){
                            System.out.println(grafico.get(i));
                            ordinate.add(grafico.get(i));
                        }
                        for(int j = 1; j< grafico.size(); j+=2){
                            System.out.println(grafico.get(j));
                            ascisse.add(grafico.get(j));
                        }
                        mostra1.setEnabled(true);
                        
		} else {
			System.out.println("GET request not worked");
		}
        }catch(Exception e){}
    }//GEN-LAST:event_mostraActionPerformed

    private void mostra1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostra1ActionPerformed
       initUI();
    }//GEN-LAST:event_mostra1ActionPerformed

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
                new StatisticheBlockchain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton mostra;
    private javax.swing.JButton mostra1;
    // End of variables declaration//GEN-END:variables
}
