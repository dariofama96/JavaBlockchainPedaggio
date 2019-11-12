package httpserver;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import static httpserver.GestoreUpload.blockchain;
import static httpserver.GestoreUpload.numberOfRequest;
import static httpserver.GestoreUpload.targaVeicolo;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.net.HttpURLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;

public class GestoreCon implements HttpHandler {
    public String hashblocco;
    public static boolean isChainValid() {
	Block currentBlock; 
	Block previousBlock;
	
	//Controllo ciclicamente gli hash
	for(int i=1; i < blockchain.size(); i++) {
		currentBlock = blockchain.get(i);
		previousBlock = blockchain.get(i-1);
		
		if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
			System.out.println("Current Hashes not equal");			
			return false;
		}
		//confronto l'hash del blocco corrente con quello del blocco precedente
		if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
			System.out.println("Previous Hashes not equal");
			return false;
		}
	}
	return true;
    }
    
    public String getHash(){
        
        return hashblocco;
    }

    public Connection cn;
    public Statement st;
    
    @Override
    public void handle(HttpExchange he) throws IOException {
        if (he.getRequestMethod().equalsIgnoreCase("POST")) {
            try {
                GestoreUpload obj = new GestoreUpload();
                Headers requestHeaders = he.getRequestHeaders();
                int contentLength = Integer.parseInt(requestHeaders.getFirst("Content-length"));
                InputStream is = he.getRequestBody();
                byte[] data = new byte[contentLength];
                int length = is.read(data);  
                Headers responseHeaders = he.getResponseHeaders();
                System.out.println("Dati arrivati dal client:");
                System.out.print(new String(data) + "\n");
                String dataString = new String(data);
                float prezzoPagato = Float.valueOf(dataString);
                System.out.println("Importo inserito dal client:" + prezzoPagato);
                String path = "C://Users//Dario Famà//Documents//NetBeansProjects//HTTPServer//HTTPServer//blockchain/chain.txt";
                FileWriter res;
                if(obj.getNameCar().contains("CRIA")){ //Controllo se il veicolo è un veicolo di soccorso dalla targa.
                    String targa1 = obj.getNameCar();
                    System.out.println(targa1);
                    float priceToPay = obj.getPrice();
                    String blockinfo = targa1 + " " + "€"+ priceToPay + "€"+ " " +prezzoPagato; //Costruisco il blocco della chain: targa + €pedaggio
                    numberOfRequest++; //incremento il numero di richiesta.
                    System.out.println("Richiesta Numero: " + numberOfRequest);
                    
                    if(numberOfRequest == 1){ //Se è la prima richiesta allora come hash del blocco precedente considero 0
                        Block block = new Block(blockinfo, "0");
                        System.out.println("Hash blocco 1 "+ block.hash);
                        hashblocco = block.hash;
                        blockchain.add(block);
                        /* Memorizzo la BlockChain in un file */
                        try{
                            res = new FileWriter(path, true);
                            res.write("Blocco numero 1:" + " " + block.hash + "\r\n");
                            res.close();    

                        }catch(IOException e){

                        }  
                    } else{
                        //Se è già stata effettuata una richiesta considero l'hash del blocco della richiesta precedente.
                        Block blockN = new Block(blockinfo, blockchain.get(blockchain.size()-1).hash); //Costruisco un nuovo blocco
                        hashblocco = blockN.hash;
                        blockchain.add(blockN); 
                        System.out.println("Hash blocco" + " " + numberOfRequest + " " + blockN.hash);
                        /* Memorizzo la BlockChain in un file */
                        try{
                            res = new FileWriter(path, true);
                            res.write("Blocco numero" + " " + numberOfRequest + ": " + blockN.hash + "\r\n");
                            res.close();    

                        }catch(IOException e){

                        }  
                    }
                    String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain); //Costruisco la blockchain in formato json.
                    System.out.println("\nThe block chain: ");
                    System.out.println(blockchainJson); //Stampo la blockchain lato server.
                    
                    System.out.println("Validità Block-Chain: " + isChainValid()); //Controllo la validità true - false
                    
                    /***************************************/
                    
                    
                    String pathfile = "C://Users//Dario Famà//Documents//NetBeansProjects//HTTPServer//HTTPServer//files" +"//" + obj.getTitle();
                      
                        /* Upload sul Cloud Dropbox */
                        File rs = new File(pathfile);
                        String ACCESS_TOKEN = "TYEiOw2nJ5AAAAAAAAAACSpuNuOP0hGtL0GGc1UnxuoNdbrZH4Bnpw18jIWynHLJ";
                        DbxRequestConfig config = DbxRequestConfig.newBuilder("DarioCarmen").build();
                        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
                        try {
                            DropBoxMain.uploadFile(client, rs,"/" + obj.getTitle());
                            
                        } catch (DbxException ex) {
                            Logger.getLogger(GestoreUpload.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        /* Fine Upload Dropbox */
                        
                        /* Upload Cloud Google Drive */
                        try{
                            GoogleDrive.uploadTextFile(pathfile, obj.getTitle());
                        }catch(Exception e){}
                        /* Fine Upload Drive */
                  
                    
                    is = he.getRequestBody();
                    String s = hashblocco;
                    byte[] ndata = s.getBytes();     
                    responseHeaders = he.getResponseHeaders();
                    he.sendResponseHeaders(HttpURLConnection.HTTP_OK, s.length());
                    OutputStream ost = he.getResponseBody();
                    ost.write(ndata);
                    ost.flush();
                    ost.close();      
                } else { //Controllo se il veicolo è un veicolo Stradale.
                    String targa2 = obj.getNameCar();
                    System.out.println("Targa Veicolo" + obj.getNameCar());
                    float priceToPay = obj.getPrice();
                    String blockinfo = targa2 + " " + "€"+ priceToPay + "€"+ " " + prezzoPagato;
                    numberOfRequest++; //incremento il numero di richiesta.
                    System.out.println("Richiesta Numero: " + numberOfRequest);
                    if(numberOfRequest == 1){//Se è la prima richiesta allora come hash del blocco precedente considero 0
                        Block block = new Block(blockinfo, "0");
                        System.out.println("Hash blocco 1 " + block.hash);
                        hashblocco = block.hash;
                        blockchain.add(block);
                        try{
                            res = new FileWriter(path, true);
                            res.write("Blocco numero 1" + " " + block.hash + "\n");
                            res.close();    
                        }catch(IOException e){

                        }
                   } else{//Se è già stata effettuata una richiesta considero l'hash del blocco della richiesta precedente.
                        Block blockN = new Block(blockinfo, blockchain.get(blockchain.size()-1).hash);//Costruisco un nuovo blocco
                        blockchain.add(blockN); 
                        System.out.println("Hash blocco" + " " + numberOfRequest + " " + blockN.hash);
                        hashblocco = blockN.hash;
                        /* -------- Memorizzo la BlockChain in un file --------*/
                        try{
                            res = new FileWriter(path, true);
                            res.write("Blocco numero" + " " + numberOfRequest + ": " + blockN.hash + "\n");
                            res.close();    

                        }catch(IOException e){

                        }  
                    }
                    
                    String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);//Costruisco la blockchain in formato json.
                    System.out.println("\nThe block chain: ");
                    System.out.println(blockchainJson);//Stampo la blockchain lato server.
                    
                    System.out.println("Validità Block-Chain: " + isChainValid());//Controllo la validità true - false
                    /************************************************************/
                    
                    is = he.getRequestBody();
                    String s = hashblocco;
                    byte[] ndata = s.getBytes();     
                    responseHeaders = he.getResponseHeaders();
                    he.sendResponseHeaders(HttpURLConnection.HTTP_OK, s.length());
                    OutputStream ost = he.getResponseBody();
                    ost.write(ndata);
                    ost.flush();
                    ost.close();
                    /* -------- Memorizziamo la transazione in un file --------*/
                    String pathfile = "C://Users//Dario Famà//Documents//NetBeansProjects//HTTPServer//HTTPServer//files"+"/"+ obj.getTitle();
                    /*try{
                        BufferedWriter ris = null;
                        ris = new BufferedWriter(new FileWriter(pathfile));
                        ris.write(obj.getString("content"));
                        ris.close();
                        */File rs = new File(pathfile);
                    /********************************************************************************/    
                        /* Upload sul Cloud Dropbox */
                        String ACCESS_TOKEN = "TYEiOw2nJ5AAAAAAAAAACSpuNuOP0hGtL0GGc1UnxuoNdbrZH4Bnpw18jIWynHLJ";
                        DbxRequestConfig config = DbxRequestConfig.newBuilder("DarioCarmen").build();
                        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
                        try {
                            DropBoxMain.uploadFile(client, rs,"/" + obj.getTitle());
                            
                        } catch (DbxException ex) {
                            Logger.getLogger(GestoreUpload.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        /* Upload Cloud Google Drive */
                        try{
                            GoogleDrive.uploadTextFile(pathfile, obj.getTitle());
                        }catch(Exception e){}
                        /* Fine Upload Drive */
                }
                /*
                Avvio la connessione con il database per eseguire un query sql di INSERT 
                per aggiornare il database con i nuovi dati inviati dal client
                */
                try{
                        
                        String plate = obj.getNameCar(); //Otteng0o il numero di targa
                        Class.forName("com.mysql.jdbc.Driver");
                        String nuovo = obj.getDate();
                        float nfl = obj.getPrice(); //Ottengo il prezzo da pagare
                        System.out.println(nfl);
                        float fprice = 0;
                        fprice = prezzoPagato-nfl; //Calcolo del resto
                        cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/detail?zeroDateTimeBehavior=convertToNull","root","");
                        st=cn.createStatement();
                        String sql="INSERT INTO tollprices(License_plate,Price_paid,data_erogazione,hash_blocco) VALUES ('"+plate+"','"+nfl+"','"+nuovo+"','"+hashblocco+"')";
                        st.executeUpdate(sql);
                        System.out.println("Dati memorizzati nel database"); 
                        
                        is = he.getRequestBody();
                        String s = hashblocco;
                        byte[] ndata = s.getBytes();  
                        responseHeaders = he.getResponseHeaders(); 
                        he.sendResponseHeaders(HttpURLConnection.HTTP_OK, s.length());
                        OutputStream os = he.getResponseBody();
                        os.write(ndata); //Restituisco il resto al client
                        os.flush();
                        os.close();
                        cn.close();
                        
                        
                }catch(Exception e){
                        System.out.println(e);
                }
                    
                
            }catch(Exception e){
                Logger.getLogger(GestoreBlockChain.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
}
