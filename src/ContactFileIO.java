import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactFileIO {
    private HashMap<String, Contact> contacts = new HashMap <> ();
    private String directory = "data";
    private String filename = "contact.csv";
    private Path dataFile = Paths.get(directory, filename);

    public ContactFileIO() {
        readFile();
    }

    public HashMap <String, Contact> getContacts() {

        return this.contacts;
    }

    private  void readFile() {
        try {

            List<String> currentContacts = Files.readAllLines(dataFile);

            for(String contact : currentContacts) {
                List<String> contactArr = Arrays.asList(contact.split(","));
                System.out.println(contactArr);
                String key = contactArr.get(0) + " " + contactArr.get(1);
                this.contacts.put(key, new Contact(contactArr.get(0), contactArr.get(1), contactArr.get(2)));

             }

         } catch(IOException e) {
             System.out.println(e.getMessage());
         }
     }

     private void writeFile() {
         for (Map.Entry<String, Contact> entry : this.contacts.entrySet()) {
//             properties.put(entry.getKey(), entry.getValue());
             System.out.println(entry.getKey());
         }

     }

    public void setContacts(HashMap<String, Contact> contacts) {
        this.contacts = contacts;
        writeFile();
    }
}
