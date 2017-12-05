import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class ContactFileIO {
     private HashMap<String, Contact> contacts;

    {
        contacts = new HashMap <> ();
    }

   private String directory = "data";
   private String filename = "contact.csv";
   private Path dataDirectory = Paths.get(directory);
   private Path dataFile = Paths.get(directory, filename);

    public ContactFileIO() {
        readFile ();
    }

    public HashMap <String, Contact> getContacts() {
        return this.contacts;
    }
     private  void readFile (){


         try {
             List<String> currentContacts;
             currentContacts = Files.readAllLines(dataFile);

             for(String contact : currentContacts) {
                 System.out.println (contact);
                 String[]  contactArray = contact.split(",");

                 String key = contactArray[0] + " " + contactArray[1];
                 System.out.println (contactArray);
                 this.contacts.put(key, new Contact(contactArray[0], contactArray[1], contactArray[2]));



             }
         } catch(IOException e) {
             System.out.println(e.getMessage());
         }
     }

}
