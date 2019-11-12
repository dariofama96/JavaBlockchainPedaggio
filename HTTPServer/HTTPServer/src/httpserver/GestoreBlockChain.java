package httpserver;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

public class GestoreBlockChain implements HttpHandler {
    @Override
    public void handle(HttpExchange he) throws IOException {
        if (he.getRequestMethod().equalsIgnoreCase("GET")) {
            try {
                Headers requestHeaders = he.getRequestHeaders();
                InputStream is = he.getRequestBody();
               
                Headers responseHeaders = he.getResponseHeaders();
                String path = "C://Users//Dario Famà//Documents//NetBeansProjects//HTTPServer//HTTPServer//blockchain/chain.txt";
                String lines = FileUtils.readFileToString(new File(path));
                System.out.println(lines);
                is = he.getRequestBody();
                byte[] ndata = lines.getBytes();
                responseHeaders = he.getResponseHeaders();
                he.sendResponseHeaders(HttpURLConnection.HTTP_OK, lines.length());
                OutputStream ost = he.getResponseBody();
                ost.write(ndata);
                ost.flush();
                ost.close();
            }catch (Exception e) {
                Logger.getLogger(GestoreBlockChain.class.getName()).log(Level.SEVERE, null, e);
                
            } 
        }
    }
}

    

