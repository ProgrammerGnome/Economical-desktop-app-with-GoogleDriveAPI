package LAUNCHER;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 *
 * @author kiralymark
 */
public class Main {
    
    public static String ablakcím = "Otthoni költségvetési alkalmazás    -   Státusz: ONLINE";
    
    public static void main(String[] args) throws IOException, GeneralSecurityException {
        
        FrontEnd_layer.GUI_Main_Masolat.main(args);    
        
    }  
}