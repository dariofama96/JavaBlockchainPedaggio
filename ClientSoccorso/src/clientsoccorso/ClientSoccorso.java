package clientsoccorso;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.JSONException;
import org.json.JSONObject;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class ClientSoccorso extends javax.swing.JFrame {

   
    public static boolean flag;
    public static float prezzoinserito;
    public static String address = "localhost";
    public static String port = "8000";
    public ClientSoccorso() {
        initComponents();
    }
    
    static public void ShowData(String p)
    {
        price.setText(p);
        flag=true;
    }
    
    public void conditionPrice()
    {
        if(flag == true)
        {
            flag=false;
            ClientSoccorso.ShowData(SendData.getPrice());
        }
      
    }

    public float getPriceToPay(){
        return prezzoinserito;
    }
    
    /* La procedura controlloStazioni va ad effettuare un controllo in relazione alla possibilità che 
    le stazioni inserite siano uguali. */
    public boolean controlloStazioni(String stazione_partenza, String stazione_arrivo){
    boolean risultato;
    if(stazione_partenza.equals(stazione_arrivo))
        risultato = false;
    else 
        risultato = true;
    return risultato;
    }
 
    /* La procedura controlloTarga effettua il controllo sul formato del numero di targa del veicolo di soccorso
    che deve essere lunga 8 caratteri e scritta nel formato CRIA000Z
    */
    public boolean controlloTarga (String targa) {
        String primitre = targa.substring(0, 3);
        String quarta = targa.substring(3,4);
        String cifretarga = targa.substring(4,7);
        String ultima = targa.substring(7);
        if (targa.length()!= 8){
            JOptionPane.showMessageDialog(null,"Lunghezza targa del veicolo di soccorso non valida.");
            return false;
        }else if(primitre.matches("(.*)CRI(.*)") && quarta.matches("(.*)A(.*)") && cifretarga.matches(".*[0-9]") && ultima.matches(".*[A-Z]")){
            System.out.println("FORMATO TARGA CORRETTO");
            return true;  
        }else {
            JOptionPane.showMessageDialog(null,"Targa non valida. Si Prega di utilizzare lettere maiuscole");
            return false;
        } 
     }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        stazpartenza = new javax.swing.JComboBox<String>();
        jTextField3 = new javax.swing.JTextField();
        stazarrivo = new javax.swing.JComboBox<String>();
        jTextField4 = new javax.swing.JTextField();
        targa = new javax.swing.JTextField();
        inviodati = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jTextField8 = new javax.swing.JTextField();
        price = new javax.swing.JTextField();
        bottone_paga = new javax.swing.JButton();
        file_name = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        inviodati1 = new javax.swing.JButton();
        BC = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Client Soccorso");
        setBackground(new java.awt.Color(102, 255, 102));

        jPanel1.setBackground(new java.awt.Color(255, 102, 102));
        jPanel1.setForeground(new java.awt.Color(102, 255, 102));

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(255, 255, 51));
        jTextField1.setFont(new java.awt.Font("Rod", 0, 36)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(0, 0, 255));
        jTextField1.setText("PEDAGGIO AUTOSTRADALE PRIMO SOCCORSO");

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(255, 204, 0));
        jTextField2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jTextField2.setText("Stazione di Partenza:");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        stazpartenza.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Messina", "Rometta", "Milazzo", "Barcellona P.G.", "Falcone", "Patti", "Brolo", "Rocca di Capri Leone", "S.Agata di Militello" }));

        jTextField3.setEditable(false);
        jTextField3.setBackground(new java.awt.Color(255, 204, 0));
        jTextField3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jTextField3.setText("Stazione di Arrivo:");

        stazarrivo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Messina", "Rometta", "Milazzo", "Barcellona P.G.", "Falcone", "Patti", "Brolo", "Rocca di Capri Leone", "S.Agata di Militello" }));

        jTextField4.setEditable(false);
        jTextField4.setBackground(new java.awt.Color(0, 0, 0));
        jTextField4.setFont(new java.awt.Font("Sylfaen", 1, 14)); // NOI18N
        jTextField4.setForeground(new java.awt.Color(255, 255, 255));
        jTextField4.setText("Targa Veicolo di Soccorso:");

        targa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        inviodati.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        inviodati.setText("SALVA");
        inviodati.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));
        inviodati.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inviodatiActionPerformed(evt);
            }
        });

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        jTextField8.setEditable(false);
        jTextField8.setBackground(new java.awt.Color(255, 204, 0));
        jTextField8.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jTextField8.setText("Importo Da Pagare:");
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });

        price.setEditable(false);
        price.setBackground(new java.awt.Color(255, 255, 255));
        price.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        bottone_paga.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        bottone_paga.setText("PAGA");
        bottone_paga.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)));
        bottone_paga.setEnabled(false);
        bottone_paga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bottone_pagaActionPerformed(evt);
            }
        });

        file_name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nome File");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        inviodati1.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        inviodati1.setText("INVIO");
        inviodati1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0)));
        inviodati1.setEnabled(false);
        inviodati1.setMaximumSize(new java.awt.Dimension(57, 27));
        inviodati1.setMinimumSize(new java.awt.Dimension(57, 27));
        inviodati1.setPreferredSize(new java.awt.Dimension(57, 27));
        inviodati1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inviodati1ActionPerformed(evt);
            }
        });

        BC.setBackground(new java.awt.Color(255, 204, 0));
        BC.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        BC.setText("BlockChain");
        BC.setEnabled(false);
        BC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/clientsoccorso/download.png"))); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/clientsoccorso/ambulanza.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTextField1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(58, 58, 58)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(stazpartenza, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jTextField2)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(36, 36, 36)
                                        .addComponent(inviodati, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(66, 66, 66)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextField3)
                                            .addComponent(stazarrivo, 0, 1, Short.MAX_VALUE))
                                        .addGap(29, 29, 29))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(47, 47, 47)
                                        .addComponent(inviodati1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(57, 57, 57))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(11, 11, 11)
                                        .addComponent(file_name, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(BC)
                                        .addGap(18, 18, 18))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(targa, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(117, 117, 117)))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jSeparator2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(145, 145, 145))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(148, 148, 148))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(133, 133, 133)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(bottone_paga, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(stazpartenza, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stazarrivo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(targa, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(inviodati, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inviodati1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(file_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3))
                            .addComponent(BC, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 21, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(bottone_paga, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(104, 104, 104))))))
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

    private void inviodatiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inviodatiActionPerformed
        FileWriter out = null;
        try { 
            String txt = file_name.getText();
            if(file_name.getText().equals("") || targa.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Compila tutti i campi");
            }
            else {
                //Ottengo la data di oggi
                Date data = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                boolean verifica = controlloTarga(targa.getText());//Controllo la targa
                boolean verificastazione = controlloStazioni(stazpartenza.getSelectedItem().toString(), 
                        stazarrivo.getSelectedItem().toString()); //Controllo se le stazioni coincidono
                if(!verificastazione)
                    JOptionPane.showMessageDialog(null, "La stazione di partenza coincide con quella di arrivo.");
                else if(!verifica){
                    JOptionPane.showMessageDialog(null, "Formato targa errato la targa deve essere del formato CRIA000Z");
                }else {
                    //Scrivo i dati inseriri in un file di testo
                    //Elemento radice
                    Element root = new Element("Biglietto");
                    //Documento
                    Document document = new Document(root);
                    //Creazione di tre elementi figli 
                    //e viene aggiunto un elemento figlio contenente
                    //il valore di quel campo
                    Element item1 = new Element("STAZIONE_PARTENZA");
                    Element descr1 = new Element("VALUE");
                    descr1.setText(stazpartenza.getSelectedItem().toString());
                    item1.addContent(descr1);
                    root.addContent(item1);
                    Element item2 = new Element("STAZIONE_ARRIVO");
                    Element descr2 = new Element("VALUE");
                    descr2.setText(stazarrivo.getSelectedItem().toString());
                    item2.addContent(descr2);
                    root.addContent(item2);
                    Element item3 = new Element("TARGA");
                    Element descr3 = new Element("VALUE");
                    descr3.setText(targa.getText());
                    item3.addContent(descr3);
                    root.addContent(item3);
                    Element item4 = new Element("DATA");
                    Element descr4 = new Element("VALUE");
                    descr4.setText(sdf.format(data));
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
                    JOptionPane.showMessageDialog(null, "Dati memorizzati. Avvio interlocuzione con il server! ");
                    inviodati1.setEnabled(true); //Sblocco il bottone per l'invio dei dati al server.
                    
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientSoccorso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_inviodatiActionPerformed

    private void inviodati1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inviodati1ActionPerformed
        try {       
            File controllo = new File("param.json");
            String title;
            String text;
            /* Importo il file di testo da inviare al server estraendone successivamente
            il titolo del file ed il suo contenuto che verrà memorizzato nelle variabili title,text.
            */
            if(controllo.exists()){
            String param = null;
            String portParam = null;
            String address = null;
            param = ImportTxtFile.importJSON("param.json");
            JSONObject obj = new JSONObject(param);
            address = obj.getString("address");
            portParam = obj.getString("port");
            ImportTxtFile importFile = new ImportTxtFile();
            importFile.selectFile();
            title = importFile.getFileName();
            text = importFile.getTextFile();
            System.out.println(title);
            try {
                JOptionPane.showMessageDialog(null, "Upload in progress...");
                //Invio dati al server.
                boolean res = SendData.sendToServer(title, text);
                if(res) {
                    flag=true;
                    conditionPrice();
                    inviodati1.setEnabled(false);
                    bottone_paga.setEnabled(true); //Sblocco il bottone per il pagamento del pedaggio
                } else {
                    JOptionPane.showMessageDialog(null, "Operazione non andata a buon fine");
                }
                
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Server Offline!");
                
                //Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            }else{
                JOptionPane.showMessageDialog(null,"File di configurazione per la connessione non presente. Avvio intefaccia per la creazione.");
                new CreateJSON().setVisible(true);
            }
            
        } catch (JSONException ex) {
            Logger.getLogger(ClientSoccorso.class.getName()).log(Level.SEVERE,null, ex);
            
        }
    }//GEN-LAST:event_inviodati1ActionPerformed

    private void BCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCActionPerformed
         try {
            /*Effettuo una richiesta di HTTP GET per ottenere i dati presenti nel file chain.txt
              che mi saranno restituiti dal server.
            */
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

			// Stampo l'http response
			System.out.println(response.toString() + "\n");
		} else {
			System.out.println("GET request not worked");
		}
        }catch(Exception e){}
    }//GEN-LAST:event_BCActionPerformed

    private void bottone_pagaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bottone_pagaActionPerformed
        if(flag == true)
        {
            prezzoinserito = Float.parseFloat(SendData.getPrice());
            System.out.println(prezzoinserito);
            new CreditCard().setVisible(true);
            BC.setEnabled(true); //Sblocco bottone che permette al client di visionare la BlockChain
        
        } else {
            System.out.println("ERRORE");
        }
    }//GEN-LAST:event_bottone_pagaActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

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
            java.util.logging.Logger.getLogger(ClientSoccorso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientSoccorso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientSoccorso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientSoccorso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientSoccorso().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton BC;
    private javax.swing.JButton bottone_paga;
    private javax.swing.JTextField file_name;
    private javax.swing.JButton inviodati;
    private javax.swing.JButton inviodati1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField8;
    private static javax.swing.JTextField price;
    private javax.swing.JComboBox<String> stazarrivo;
    private javax.swing.JComboBox<String> stazpartenza;
    private javax.swing.JTextField targa;
    // End of variables declaration//GEN-END:variables
}
