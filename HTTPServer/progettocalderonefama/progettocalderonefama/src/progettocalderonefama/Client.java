package progettocalderonefama;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.json.JSONException;
import org.json.JSONObject;


public class Client extends javax.swing.JFrame {

    public static boolean flag;
    public Client() {
        flag=false;
        initComponents();
    }
    
    static public void ShowData(String p)
    {
        pricefield.setText(p);
        flag=true;
    }
    
    public void conditionPrice()
    {
        if(flag == true)
        {
            flag=false;
            Client.ShowData(InvioServer.getHash());
        }
      
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        stazione2 = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        stazione1 = new javax.swing.JComboBox();
        jTextField2 = new javax.swing.JTextField();
        targa = new javax.swing.JTextField();
        salva = new javax.swing.JButton();
        senddata = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        file_name = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        pricefield = new javax.swing.JTextField();
        paymentfield = new javax.swing.JTextField();
        bottonepay = new javax.swing.JButton();
        restofield = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        blockchain = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Client Veicoli");

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(255, 153, 0));
        jTextField1.setFont(new java.awt.Font("SansSerif", 0, 36)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("PEDAGGIO AUTOSTRADALE VEICOLI");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 153, 153));
        jButton1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jButton1.setText("Stazione di Arrivo");

        stazione2.setBackground(new java.awt.Color(240, 240, 240));
        stazione2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        stazione2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Messina", "Rometta", "Milazzo", "Barcellona P.G.", "Falcone", "Patti", "Brolo", "Rocca di Capri Leone", "S.Agata di Militello" }));

        jButton2.setBackground(new java.awt.Color(255, 153, 153));
        jButton2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jButton2.setText("Stazione di Partenza");

        stazione1.setBackground(new java.awt.Color(240, 240, 240));
        stazione1.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        stazione1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Messina", "Rometta", "Milazzo", "Barcellona P.G.", "Falcone", "Patti", "Brolo", "Rocca di Capri Leone", "S.Agata di Militello" }));

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(255, 153, 153));
        jTextField2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText("Targa del Veicolo");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        targa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        salva.setBackground(new java.awt.Color(255, 153, 153));
        salva.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        salva.setText("Salva");
        salva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvaActionPerformed(evt);
            }
        });

        senddata.setBackground(new java.awt.Color(204, 204, 0));
        senddata.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        senddata.setText("Invio");
        senddata.setEnabled(false);
        senddata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                senddataActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel1.setText("Nome File");

        file_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                file_nameActionPerformed(evt);
            }
        });

        jTextField5.setEditable(false);
        jTextField5.setBackground(new java.awt.Color(255, 153, 153));
        jTextField5.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField5.setText("Pagamento:");

        jTextField6.setEditable(false);
        jTextField6.setBackground(new java.awt.Color(255, 153, 153));
        jTextField6.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField6.setText("Importo da Pagare");

        pricefield.setEditable(false);
        pricefield.setBackground(new java.awt.Color(255, 255, 255));
        pricefield.setForeground(new java.awt.Color(153, 0, 153));
        pricefield.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pricefield.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        paymentfield.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        paymentfield.setForeground(new java.awt.Color(153, 0, 153));
        paymentfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        paymentfield.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        bottonepay.setBackground(new java.awt.Color(255, 204, 153));
        bottonepay.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        bottonepay.setText("Paga");
        bottonepay.setEnabled(false);
        bottonepay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bottonepayActionPerformed(evt);
            }
        });

        restofield.setEditable(false);
        restofield.setBackground(new java.awt.Color(51, 255, 255));

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        blockchain.setBackground(new java.awt.Color(255, 255, 0));
        blockchain.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        blockchain.setText("BlockChain");
        blockchain.setEnabled(false);
        blockchain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blockchainActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel2.setText("Codice Transazione:");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/progettocalderonefama/auto.png"))); // NOI18N

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/progettocalderonefama/moto.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 771, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(stazione1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                    .addComponent(stazione2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(file_name, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(targa, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(blockchain)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(salva, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68)
                                .addComponent(senddata, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(bottonepay, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(130, 130, 130))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap()))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(restofield, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pricefield, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(paymentfield, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(114, 114, 114))))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(598, Short.MAX_VALUE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(114, 114, 114)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(stazione2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pricefield, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stazione1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(paymentfield, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(targa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                                .addComponent(blockchain))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(salva, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(senddata, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bottonepay, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(file_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(restofield, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(136, 136, 136)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(352, Short.MAX_VALUE)))
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

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void file_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_file_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_file_nameActionPerformed

    private void salvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvaActionPerformed
        FileWriter out = null;
        try {
            
            String txt = file_name.getText();
            if(file_name.getText().equals("") || targa.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Compila tutti i campi");
            }
            else {
                Date data = new Date ();
                SimpleDateFormat adf = new SimpleDateFormat ("dd-MM-yyyy");
                boolean verificastazione = controlloStazioni(stazione1.getSelectedItem().toString(), 
                        stazione2.getSelectedItem().toString()); //Controllo se le stazioni coincidono
                boolean verifica = controlloTarga(targa.getText().toString());
                if(!verifica)
                    JOptionPane.showMessageDialog(null, "Formato targa errato la targa deve essere del formato AA000ZZ");
                    else if(!verificastazione)  { 
                    JOptionPane.showMessageDialog(null, "Errore! Le stazioni coincidono");
                }else {
                    //Scrivo i dati inseriri in un file di testo
                    //Elemento radice
                    Element root = new Element("Biglietto");
                    //Documento
                    Document document = new Document(root);
                    //Creazione di tre elementi figli 
                    //e viene aggiunto un elemento figlio contenente
                    //il valore di quel campo
                    Element item1 = new Element("stazione1");
                    Element descr1 = new Element("VALUE");
                    descr1.setText(stazione1.getSelectedItem().toString());
                    item1.addContent(descr1);
                    root.addContent(item1);
                    Element item2 = new Element("STAZIONE_ARRIVO");
                    Element descr2 = new Element("VALUE");
                    descr2.setText(stazione2.getSelectedItem().toString());
                    item2.addContent(descr2);
                    root.addContent(item2);
                    Element item3 = new Element("TARGA");
                    Element descr3 = new Element("VALUE");
                    descr3.setText(targa.getText());
                    item3.addContent(descr3);
                    root.addContent(item3);
                    Element item4 = new Element("DATA");
                    Element descr4 = new Element("VALUE");
                    descr4.setText(adf.format(data));
                    item4.addContent(descr4);
                    root.addContent(item4);
                    //Creazione dell'oggetto XMLOutputter
                    XMLOutputter outputter = new XMLOutputter();
                    //Imposto il formato dell'outputter come "bel formato"
                    outputter.setFormat(Format.getPrettyFormat());
                    //Produco l'output sul file xml.foo
                    String name = file_name.getText();
                    outputter.output(document, new FileOutputStream( name + ".xml"));
                    System.out.println("File creato:");
                    //Stampo l'output anche sullo standard output
                    outputter.output(document, System.out);
                    JOptionPane.showMessageDialog(null, "Dati memorizzati. Avvio interlocuzione con il server!");
                    senddata.setEnabled(true);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }//GEN-LAST:event_salvaActionPerformed

    private void senddataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_senddataActionPerformed
        File controllo = new File ("param.json");
        String title;
        String text;
        if (controllo.exists()) {
        try {
            String param = null;
            String portParam = null;
            String address = null;
            param = ImportTextFile.importJSON("param.json");
            JSONObject obj = new JSONObject (param);
            address = obj.getString("address");
            portParam = obj.getString("port");
            ImportTextFile importFile = new ImportTextFile();
            importFile.selectFile();
            /* Importo il file di testo da inviare al server estraendone successivamente
            il titolo del file ed il suo contenuto che verrà memorizzato nelle variabili title,text.
            */
            title = importFile.getFileName();
            text = importFile.getTextFile();
            try {
                
                JOptionPane.showMessageDialog(null, "Upload in progress...");
                //Invio dati al server.
                boolean res = InvioServer.sendToServer(title, text);
                if(res) {
                    flag=true;
                    // Mostriamo il pedaggio calcolato al client
                    conditionPrice();
                    senddata.setEnabled(false);
                    blockchain.setEnabled(true);
                    bottonepay.setEnabled(true);
                    
                    
                } else {
                    JOptionPane.showMessageDialog(null, "File già presente sul server");
                }
                
            } catch (IOException ex) {
                String mess = ex.getMessage();
                if (mess.equals("Connection refused: connect") || mess.equals("Connection refused (Connection refused)")) {
                    JOptionPane.showMessageDialog(null, "Server Offline!");
                }
                
            }
        } catch (JSONException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
           
        }
    }else {
        JOptionPane.showMessageDialog(null, "File di configurazione non presente");
        new CreateJson().setVisible(true);
        }
        
    }//GEN-LAST:event_senddataActionPerformed

    private void blockchainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blockchainActionPerformed
        /*Effettuo una richiesta di HTTP GET per ottenere i dati presenti nel file chain.txt
          che mi saranno restituiti dal server.
        */
        try {
                String address = "localhost"; 
                String port="8000";
                String targetURL = "http://" + address + ":" + port + "/blc/";
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

			// print result
			System.out.println(response.toString() + "\n");
		} else {
			System.out.println("GET request not worked");
		}
        }catch(Exception e){}
    }//GEN-LAST:event_blockchainActionPerformed

    private void bottonepayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bottonepayActionPerformed
        /* 
        Per il pagamento del pedaggio al tocco del bottone viene effettuata
        una richiesta di HTTP POST dove viene inviato al server il valore del pedaggio
        da pagare da parte del client che dev'essere maggiore o uguale al pedaggio richiesto
        dal server. Come HTTP Response il server restituirà il resto, ovvero la differenza
        di prezzo tra il prezzo inserito ed il pedaggio da pagare.
        */
        if(flag == true)
        { 
            String val= paymentfield.getText();
            String result= pricefield.getText();
            float priceToPay = Float.parseFloat(val);
            float priceBePaid = Float.parseFloat(result);
            if(priceToPay >= priceBePaid){
                    flag=false;
                    try {
                        String address = "localhost"; 
                        String port = "8000";
                        String targetURL = "http://" + address + ":" + port + "/Con/";
                        String  urlParameters=val;
                        HttpURLConnection connection = null;

                        //Create connection
                        URL url = new URL(targetURL);
                        connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("POST");
                        connection.setRequestProperty("Content-Type",
                                "application/x-www-form-urlencoded");

                        connection.setRequestProperty("Content-Length",
                                Integer.toString(urlParameters.getBytes().length));
                        connection.setRequestProperty("Content-Language", "en-US");

                        connection.setUseCaches(false);
                        connection.setDoOutput(true);

                        //Send request
                        DataOutputStream wr = new DataOutputStream(
                        connection.getOutputStream());
                        wr.writeBytes(urlParameters);
                        wr.close();

                        //Get Response  
                        InputStream is = connection.getInputStream();
                        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                        StringBuilder response = new StringBuilder(); 
                        String line;

                        while ((line = rd.readLine()) != null) {
                            response.append(line);
                           
                        }
                        String numbers = response.toString();

                        restofield.setText(numbers);
                        JOptionPane.showMessageDialog(null, "Pagamento Effettuato!");
                        
                } catch (Exception e) {
                    e.printStackTrace();
                } 
            }else {
                JOptionPane.showMessageDialog(null, "Si prega di inserire un importo >= del pedaggio");
                flag = true;
            }
        }
    }//GEN-LAST:event_bottonepayActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    public boolean controlloTarga (String targa) {
        
        String primidue = targa.substring(0, 2);
        String cifretarga = targa.substring(2,5);
        String ultimidue = targa.substring(5,7);
        if (targa.length()!= 7){
            JOptionPane.showMessageDialog(null,"Targa non valida.");
        }else if(primidue.matches(".*[A-Z]") && cifretarga.matches(".*[0-9]") && ultimidue.matches(".*[A-Z]")){
            System.out.println("FORMATO TARGA CORRETTO");
            return true;  
        }else {
            JOptionPane.showMessageDialog(null,"Targa non valida. Si Prega di utilizzare lettere maiuscole");
           
        }
        return false;
      
     }
    
     private boolean controlloStazioni(String stazione1, String stazione2) {
        if (stazione1.equals(stazione2)){
            JOptionPane.showMessageDialog(null,"Errore! Le stazioni risultano essere uguali.");
            return false;
            }else {
            System.out.println("LE STAZIONI SONO CORRETTE");
            return true;
        }
              
     }
           
           
  
    public static void main(String args[]) throws IOException {
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
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Client().setVisible(true);
            }
        });
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton blockchain;
    public static javax.swing.JButton bottonepay;
    private javax.swing.JTextField file_name;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField paymentfield;
    private static javax.swing.JTextField pricefield;
    private javax.swing.JTextField restofield;
    private javax.swing.JButton salva;
    private javax.swing.JButton senddata;
    private javax.swing.JComboBox stazione1;
    private javax.swing.JComboBox stazione2;
    private javax.swing.JTextField targa;
    // End of variables declaration//GEN-END:variables
   
}
