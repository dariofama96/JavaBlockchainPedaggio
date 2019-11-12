package httpserver;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

public class GestoreGraficiChain implements HttpHandler {
    public Connection cn;
    public Statement st;
    public static ArrayList <String> datigrafico = new ArrayList();
    @Override
    public void handle(HttpExchange he) throws IOException {
        if (he.getRequestMethod().equalsIgnoreCase("GET")) {
            try {
               
                Headers requestHeaders = he.getRequestHeaders();
                InputStream is = he.getRequestBody();
 
                Headers responseHeaders = he.getResponseHeaders();
                Class.forName("com.mysql.jdbc.Driver");     
                cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/detail?zeroDateTimeBehavior=convertToNull", "root", "");
                st = cn.createStatement();
                String sql = "SELECT Price_Paid, hash_blocco FROM tollprices";
                ResultSet rs = st.executeQuery(sql);
                String resultdata = null;
                ResultSetMetaData md = rs.getMetaData(); 
                int colCount = md.getColumnCount();  
                while(rs.next()){
                    for (int i = 1; i <= colCount ; i++){
                        resultdata = rs.getString(i); 
                        datigrafico.add(resultdata);
                        System.out.println(resultdata);  
                        
                    }
                }
                
                
                
                is = he.getRequestBody();
                String risultato = datigrafico.toString();
                byte[] ndata = risultato.getBytes();  
                responseHeaders = he.getResponseHeaders(); 
                he.sendResponseHeaders(HttpURLConnection.HTTP_OK, risultato.length());
                OutputStream os = he.getResponseBody();
                os.write(ndata); //Restituisco il resto al client
                os.flush();
                os.close();
                cn.close();
                
            }catch(Exception e){
                Logger.getLogger(GestoreBanca.class.getName()).log(Level.SEVERE, null, e);
            }
    
        } 
    }
    
}
