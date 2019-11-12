/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpserver;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.security.Key;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import sun.misc.BASE64Decoder;

/**
 *
 * @author Dario Famà
 */
public class GestoreGrafici implements HttpHandler {
    
    public Connection conn;
    public Statement stat;
    
    public static String decrypt(String encryptedMessage, String key) throws Exception{
    
        byte[] byteKey = key.getBytes();
        Key secretKey = new SecretKeySpec(byteKey, "AES");
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.DECRYPT_MODE,secretKey);
        
        byte[] byteEncryptedMessage = new BASE64Decoder().decodeBuffer(encryptedMessage);
        byte[] byteMessage = c.doFinal(byteEncryptedMessage);
        
        String message = new String(byteMessage);
        return message;
    
    }
    
    private static ArrayList <String> datipergrafico = new ArrayList();
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

                System.out.println("Dati arrivati dal client: ");
                
                System.out.print(new String(data) + "\n");
                String dataString = new String(data);
                JSONObject obj = new JSONObject(dataString);
                System.out.println(obj.toString());
                String messdecodific = decrypt(obj.getString("messaggio"), obj.getString("chiave"));
                System.out.println("Messaggio:  " + messdecodific);
                JSONObject obj2 = new JSONObject(messdecodific);
                String data_arr_1 = obj2.getString("data_1");
                String data_arr_2 = obj2.getString("data_2");
                System.out.println(data_arr_1);
                System.out.println(data_arr_2);
                    Class.forName("com.mysql.jdbc.Driver");     
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/detail?zeroDateTimeBehavior=convertToNull","root","");
                    stat = conn.createStatement();
                    String sql = "SELECT data_erogazione, COUNT(*) FROM tollprices WHERE data_erogazione BETWEEN '"+data_arr_1+"' AND '"+data_arr_2+"' GROUP BY data_erogazione";
                    ResultSet rs = stat.executeQuery(sql);
                    String resultdata;
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int columnsNumber = rsmd.getColumnCount();
                    System.out.println("ciao");
                        while(rs.next()){
                            System.out.println("ciao1");
                            for (int i = 1; i <= columnsNumber ; i++){
                                System.out.println("ciao2");
                                resultdata = rs.getString(i); 
                                System.out.println("dato " + resultdata); 
                                datipergrafico.add(resultdata);


                            }
                        }

                is = he.getRequestBody();
                String risultato = datipergrafico.toString();
                System.out.println(risultato);
                byte[] ndata = risultato.getBytes();  
                responseHeaders = he.getResponseHeaders(); 
                he.sendResponseHeaders(HttpURLConnection.HTTP_OK, risultato.length());
                OutputStream os = he.getResponseBody();
                os.write(ndata); //Restituisco il resto al client
                os.flush();
                os.close();
                conn.close();
                
            }catch(Exception e){
                Logger.getLogger(GestoreGrafici.class.getName()).log(Level.SEVERE, null, e);
            }
    
        }
    
    }
}
