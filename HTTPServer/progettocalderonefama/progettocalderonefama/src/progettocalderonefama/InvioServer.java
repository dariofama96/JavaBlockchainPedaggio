package progettocalderonefama;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class InvioServer {
    
    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        InvioServer.address = address;
    }

    public static String getHash() {
        return hash;
    }
    public static String getPort() {
        return port;
    }

    public static void setPort(String port) {
        InvioServer.port = port;
    }

    private static String address ="localhost";
    private static String port="8000";
    private static String hash;
   

    public static boolean sendToServer(String title, String content) throws MalformedURLException, IOException {
        boolean res = false;
        if (res==false) {
            try {
                int PRETTY_PRINT_INDENT_FACTOR = 4;
                String XML_Content = content;
                JSONObject xmlJSONObj = XML.toJSONObject(XML_Content);
                String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
                jsonPrettyPrintString = jsonPrettyPrintString.replace("{", "").replace("}", "").replace(",", "").replace("\"", "").replace("_", "").replaceAll("\r*\n*", "");
                System.out.println(jsonPrettyPrintString);
                JSONObject obj = new JSONObject("{\"title\": \""+title+"\",\"content\": \""+jsonPrettyPrintString+"\"}");
                System.out.println(obj);
                InvioServer.executePost("http://" + address + ":" + port + "/upload/", obj.toString());
                return true;
            } catch (JSONException ex) {
                Logger.getLogger(InvioServer.class.getName()).log(Level.SEVERE, null, ex);
                return false;
                
            }
        } else {
            
        return false;
        }

    }
    
    private static String executePost(String targetURL, String urlParameters) throws MalformedURLException, IOException {
        HttpURLConnection connection = null;

        try {
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
            hash = numbers;
            rd.close();
            JOptionPane.showMessageDialog(null, "Upload Completato");
            Object[] options = { "OK" };
            JOptionPane.showOptionDialog(null, "Si prega di effettuare il pagamento!", "Attenzione",
            JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
            null, options, options[0]);
            return response.toString();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "SERVER OFFLINE");
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
    
}
