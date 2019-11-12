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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.JSONObject;


public class GestoreBanca implements HttpHandler {
    public Connection cn;
    public Statement st;
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
                String numeroarrivato = obj.getString("numbercard");
                String cvvarrivato = obj.getString("cvv");
                String nomearrivato = obj.getString("name");
                String prezzoarrivato = obj.getString("prezzo");
                String cognomearrivato = obj.getString("surname");
                System.out.println(numeroarrivato);
                System.out.println(cvvarrivato);
                System.out.println(nomearrivato);
                System.out.println(prezzoarrivato);
                System.out.println(cognomearrivato);
                Class.forName("com.mysql.jdbc.Driver");     
                cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?zeroDateTimeBehavior=convertToNull","root","");
                st = cn.createStatement();
                String sql = "SELECT * FROM bankdata";
                ResultSet rs = st.executeQuery(sql);
                String number = null;
                String codice = null;
                String nomeprop = null;
                String cognomeprop = null;
                float prezzodaconfronto = Float.parseFloat(prezzoarrivato);
                float saldo = 0;
                while(rs.next()){
                    number = rs.getString("number_of_card");
                    codice = rs.getString("cvv");
                    nomeprop = rs.getString("name");
                    cognomeprop = rs.getString("surname");
                    saldo = rs.getFloat("balance");
                    System.out.println(number);
                    System.out.println(codice);
                    System.out.println(cognomeprop);
                    System.out.println(nomeprop);
                    System.out.println(saldo);
                }
                float diff = 0;
                if(numeroarrivato.equals(number) && cvvarrivato.equals(codice) && nomearrivato.equals(nomeprop) && cognomearrivato.equals(cognomeprop)){
                    if(prezzodaconfronto <= saldo){
                        diff = saldo - prezzodaconfronto;
                        System.out.println(diff);
                        String query = "update bankdata set balance = ?";
                        PreparedStatement preparedStmt = cn.prepareStatement(query);
                        preparedStmt.setFloat(1, diff);
                        preparedStmt.executeUpdate();
                    }
                }
                else
                    JOptionPane.showMessageDialog(null, "Errore. Dati non aggiornati");
                
                is = he.getRequestBody();
                String s = Float.toString(diff);
                byte[] ndata = s.getBytes();  
                responseHeaders = he.getResponseHeaders(); 
                he.sendResponseHeaders(HttpURLConnection.HTTP_OK, s.length());
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
