package httpserver;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class GestoreUpload implements HttpHandler {
    public static String title;
    public static Date databiglietto;
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public static String targaVeicolo;
    public static int numberOfRequest;
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static float price;
  
    
    public String getNameCar()
    {
        return targaVeicolo;
    }
    public float getPrice()
    {
        return price;
    }
    public String getTitle()
    {
        return title;
    }
    
    public String getDate() throws ParseException
    {
        String strDate = sdf.format(databiglietto);
        return strDate;
    }
    
    
    @Override
    public void handle(HttpExchange he) throws IOException {
        if (he.getRequestMethod().equalsIgnoreCase("POST")) {
            try {
               
                Headers requestHeaders = he.getRequestHeaders();
                int contentLength = Integer.parseInt(requestHeaders.getFirst("Content-length"));

                InputStream is = he.getRequestBody();

                byte[] data = new byte[contentLength];
                int length = is.read(data);  
                   
                Headers responseHeaders = he.getResponseHeaders();

                System.out.println("Dati arrivati dal client:");
 
                System.out.print(new String(data) + "\n");
                String dataString = new String(data);
                JSONObject obj = new JSONObject(dataString);
                System.out.println("Calcolo Pedaggio....");
                String path = "C://Users//Dario Famà//Documents//NetBeansProjects//HTTPServer//HTTPServer//blockchain/chain.txt";
                FileWriter res;
                title = obj.getString("title");
                databiglietto = new Date();
                
                if(obj.getString("content").contains("CRIA")){ //Controllo se il veicolo è un veicolo di soccorso dalla targa.
                    targaVeicolo = obj.getString("content").substring(29,37);
                    System.out.println(targaVeicolo);
                    float fl = CalcoloPedaggio.calcoloPedaggio(obj.getString("content")); // Calcolo il pedaggio passandogli la targa del veicolo di soccorso.
                    System.out.println("Pedaggio: " + fl);
                    price = fl;

                    /* Memorizzo il file nella cartella files lato server */
                    String pathfile = "C://Users//Dario Famà//Documents//NetBeansProjects//HTTPServer//HTTPServer//files"+"/"+ obj.getString("title");
                    try{
                        BufferedWriter ris = null;
                        ris = new BufferedWriter(new FileWriter(pathfile));
                        ris.write(obj.getString("content"));
                        ris.close();
                    }catch(IOException e){

                    }

                    is = he.getRequestBody();
                    String s = Float.toString(fl);
                    byte[] ndata = s.getBytes();     
                    responseHeaders = he.getResponseHeaders();
                    he.sendResponseHeaders(HttpURLConnection.HTTP_OK, s.length());
                    OutputStream ost = he.getResponseBody();
                    ost.write(ndata);
                    ost.flush();
                    ost.close();
                    
                        
                }else { //Controllo se il veicolo è un veicolo Stradale.
                    targaVeicolo = obj.getString("content").substring(29,36);
                    
                    float fl=CalcoloPedaggio.calcoloPedaggio(obj.getString("content"));// Calcolo il pedaggio passandogli la targa del veicolo Stradale.
                    System.out.println("Pedaggio: " + fl);
                    price=fl;
                    
                    /* -------- Memorizziamo la transazione in un file --------*/
                    String pathfile = "C://Users//Dario Famà//Documents//NetBeansProjects//HTTPServer//HTTPServer//files"+"/"+ obj.getString("title");
                    try{
                        BufferedWriter ris = null;
                        ris = new BufferedWriter(new FileWriter(pathfile));
                        ris.write(obj.getString("content"));
                        ris.close();
                        File rs = new File(pathfile);
                  
                    }catch(IOException e){

                    }  
 
                    is = he.getRequestBody();
                    String s = Float.toString(fl);
                    byte[] ndata = s.getBytes();     
                    responseHeaders = he.getResponseHeaders();
                    he.sendResponseHeaders(HttpURLConnection.HTTP_OK, s.length());
                    OutputStream ost = he.getResponseBody();
                    ost.write(ndata);
                    ost.flush();
                    ost.close();
                    
                
                }
               
            }catch (NumberFormatException | IOException e) {
            
            }
            catch (JSONException ex) {
                Logger.getLogger(GestoreUpload.class.getName()).log(Level.SEVERE, null, ex);
            } 
    }
}
}
