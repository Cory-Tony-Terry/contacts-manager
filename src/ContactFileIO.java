import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ContactFileIO {
    private HashMap<String, Contact> contacts = new HashMap <> ();
    private String directory = "data";
    private String filename = "contact.csv";
    private Path dataFile = Paths.get(directory, filename);

    public ContactFileIO() {
        readFile ();
    }

    public HashMap <String, Contact> getContacts() {
        return this.contacts;
    }

    private  void readFile (){
        try {

            List<String> currentContacts = Files.readAllLines(dataFile);

            for(String contact : currentContacts) {
                List<String> contactArr = Arrays.asList(contact.split(","));

                String key = contactArr.get(0) + " " + contactArr.get(1);
                this.contacts.put(key, new Contact(contactArr.get(0), contactArr.get(1), contactArr.get(2)));

             }

         } catch(IOException e) {
             System.out.println(e.getMessage());
         }
     }

}
