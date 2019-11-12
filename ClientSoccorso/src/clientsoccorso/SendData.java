package clientsoccorso;

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

public class SendData {
    
    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        SendData.address = address;
    }
    public static String getPrice(){
        return price;
    }

    public static String getPort() {
        return port;
    }

    public static void setPort(String port) {
        SendData.port = port;
    }
    

    public static boolean sendToServer(String title, String content) throws MalformedURLException, IOException {
        boolean res = false;
        if (res==false) {
            try {
                //Creo l'oggetto json con due parametri "title" e "content"
                int PRETTY_PRINT_INDENT_FACTOR = 4;
                String XML_Content = content;
                JSONObject xmlJSONObj = XML.toJSONObject(XML_Content);
                String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
                jsonPrettyPrintString = jsonPrettyPrintString.replace("{", "").replace("}", "").replace(",", "").replace("\"", "").replace("_", "").replaceAll("\r*\n*", "");
                System.out.println(jsonPrettyPrintString);
                JSONObject obj = new JSONObject("{\"title\": \""+title+"\",\"content\": \""+jsonPrettyPrintString+"\"}");
                System.out.println(obj);
                //Eseguo il post 
                SendData.executePost("http://" + address + ":" + port + "/upload/", obj.toString());
                return true;
            } catch (JSONException ex) {
                Logger.getLogger(SendData.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return false;
        }
        return false;
    }
    
    public static String executePost(String targetURL, String urlParameters) throws MalformedURLException, IOException {
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
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            
            while ((line = rd.readLine()) != null) {
                response.append(line);
                //response.append('\r');
            }
            String numbers = response.toString();
            price = numbers;
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
        } 
    }
    /*
    Procedura che effettua una richiesta di Http Post con la differenza che deve stamparmi il resto non appena lo riceve dal server
    */
    public static String executePostforPay(String targetURL, String urlParameters) throws MalformedURLException, IOException {
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
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            
            while ((line = rd.readLine()) != null) {
                response.append(line);
                
            }
            String numbers = response.toString();
            rd.close();
            
            return response.toString();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "SERVER OFFLINE");
            e.printStackTrace();
            return null;
        } 
    }
    
    private static String address ="localhost";
    private static String port="8000";
    private static String price;
    
}
