package httpserver;
import com.sun.net.httpserver.HttpServer;
import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;


public class HTTPServer {
    public static String pathBlockChain = "C://Users//Dario Famà//Documents//NetBeansProjects//HTTPServer//HTTPServer//blockchain//chain.txt";
    public static void main (String[] args) throws IOException, ClassNotFoundException, SQLException{
        try {
            if(args.length !=0)
            {
                for (String s: args) {
                    System.out.println(s);
                }
                return ;
            }
            
            String param = null;
            String portParam = null;
            String address = null;
            String path = null;
            try {
                /* Tramite la classe ImportTxt.java importo i dati di configurazione del server presenti nel file param.json */
                param = ImportTxt.importJSON("param.json");
                JSONObject obj = new JSONObject(param);
                address = obj.getString("address");
                portParam = obj.getString("port");
                path = obj.getString("path");
                path = path.replace("*", "\\");
                System.out.println(path);
                ImportTxt.directory = path;
                File dir = new File(ImportTxt.directory);
                ImportTxt.addFromDir(dir.toString());
            } catch (JSONException ex) {
                Logger.getLogger(HTTPServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            int port = 0;
            try {
                port = Integer.parseInt(portParam);
            }catch(Exception e) {
                System.out.println("Valore della porta non valido");
            }
            
            
        /* Creo l'istanza del server specificandone numero di porta ed indirizzo HTTP */
            HttpServer server = HttpServer.create(new InetSocketAddress(address,port), 0);
            server.createContext("/blc", new GestoreBlockChain());
            server.createContext("/upload", new GestoreUpload());
            server.createContext("/Con", new GestoreCon());
            server.createContext("/bank", new GestoreBanca());
            server.createContext("/graph", new GestoreGrafici());
            server.createContext("/gchain", new GestoreGraficiChain());
            server.setExecutor(null); // creates a default executor
            server.start(); // Avvio il server
        /* 
        Utilizzo la funzione destroyFile() ad ogni avvio del server per fare in modo 
        che ogni volta che il server viene chiuso e riavviato il file relativo alla blockchain
        si svuoti tenendo conto solo dei blocchi della connessione corrente.
        */
            destroyFile(); 
            System.out.println("Server Avviato");
            HttpServerGUI gui = new HttpServerGUI();
            gui.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(HTTPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void destroyFile(){
        File file = new File(pathBlockChain);
        file.delete();
        if(!file.exists())
            System.out.println("Sucessfully deleted the file");
    }
      
}