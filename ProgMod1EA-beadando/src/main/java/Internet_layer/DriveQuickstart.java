package Internet_layer;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/* class to demonstarte use of Drive files list API */
public class DriveQuickstart {
    
    public static String keresettID = "";
    
    
    /** Application name. */
    private static final String APPLICATION_NAME = "Google Drive API Java Quickstart";
    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    /** Directory to store authorization tokens for this application. */
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
    
    /**
     * Creates an authorized Credential object.
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = DriveQuickstart.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        //returns an authorized Credential object.
        return credential;
    }
    
    public static void feltolt() throws IOException, GeneralSecurityException {
        
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();

        // Print the names and IDs for up to 10 files.
        String folderId = "10SveTo5sKFOO9gC0YyULfqvtBxdBu_6Q";
        File fileMetadata = new File();
        fileMetadata.setName("program_database.xml");
        fileMetadata.setParents(Collections.singletonList(folderId));
        java.io.File filePath = new java.io.File("program_database.xml");
        FileContent mediaContent = new FileContent("text/pain", filePath);
        try {
        File file = service.files().create(fileMetadata, mediaContent)
            .setFields("id, parents")
            .execute();
        System.out.println("File uploaded succesfully (" + file.getId() + ")");
        } catch(Exception e){
                LAUNCHER.Main.ablakcím = "Otthoni költségvetési alkalmazás    -   OFFLINE (Nincs internet.)";
            }
    }
    
    public static void keres() throws IOException, GeneralSecurityException {
        
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
        
        String pageToken = null;
        try {
        do {
          FileList result = service.files().list()
              .setQ("name='program_database.xml'")
              .setSpaces("drive")
              .setFields("nextPageToken, files(id, name)")
              .setPageToken(pageToken)
              .execute();
          for (File file : result.getFiles()) {
            System.out.printf("Found file: %s \n",
                file.getName());
                keresettID = file.getId();
          }
          pageToken = result.getNextPageToken();
        } while (pageToken != null);
        } catch(Exception e){
                LAUNCHER.Main.ablakcím = "Otthoni költségvetési alkalmazás    -   OFFLINE (Nincs internet.)";
            }

    }
    
    public static void letolt() throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();

        // Print the names and IDs for up to 10 files.
        try {
        String fileId = DriveQuickstart.keresettID;
        OutputStream outputStream = new ByteArrayOutputStream();
        service.files().get(fileId)
            .executeMediaAndDownloadTo(outputStream);
        //System.out.println(outputStream);
        System.out.println("File downloaded successfully");
        
        try (PrintWriter writer = new PrintWriter("program_database.xml", "UTF-8")) {
            writer.println(outputStream);
        }
        } catch(Exception e){
                LAUNCHER.Main.ablakcím = "Otthoni költségvetési alkalmazás    -   OFFLINE (Nincs internet, de van OFFLINE adatbázis.)";
                try {
                Path source = Paths.get("program_database_OFFLINE.xml");
                Files.move(source, source.resolveSibling("program_database.xml"));
                } catch(Exception ea){
                    LAUNCHER.Main.ablakcím = "Otthoni költségvetési alkalmazás    -   OFFLINE (Nincs internet és OFFLINE adatbázis se.)";
                }
            }
    }
    
    public static void torol() throws IOException, GeneralSecurityException {
        
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
        
        try {

        String fileId = DriveQuickstart.keresettID;
        try {
        service.files().delete(fileId).execute();
        System.out.println("Cloud file deleted succesfully");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e);
        }
        } catch(Exception e){
                LAUNCHER.Main.ablakcím = "Otthoni költségvetési alkalmazás    -   OFFLINE (Nincs internet.)";
            }
    } 
}