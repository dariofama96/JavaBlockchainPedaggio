package httpserver;
import java.io.IOException;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.WriteMode;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;

public class DropBoxMain {

    public static void uploadFile(DbxClientV2 dbxClient, File localFile, String dropboxPath) throws DbxException {
        try (InputStream in = new FileInputStream(localFile)) {
            FileMetadata metadata = dbxClient.files().uploadBuilder(dropboxPath)
                .withMode(WriteMode.ADD)
                .withClientModified(new Date(localFile.lastModified()))
                .uploadAndFinish(in);

            System.out.println("Riepilogo dati inviati: \n" + metadata.toStringMultiline());
            System.out.println("Upload sul Cloud DROPBOX completato.");
        } catch (IOException ex) {
            System.err.println("Error reading from file \"" + localFile + "\": " + ex.getMessage());
            System.exit(1);
        }
    }
}


