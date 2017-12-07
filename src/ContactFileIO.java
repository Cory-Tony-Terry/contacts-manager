import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ContactFileIO {
    private HashMap<String, Contact> contacts = new HashMap <> ();
    private String directory = "data";
    private String filename = "contact.csv";
    private Path dataFile = Paths.get(directory, filename);

    public ContactFileIO() {
        readFile();
    }

    private  void readFile() {
        try {

            List<String> currentContacts = Files.readAllLines(dataFile);

            for(String contact : currentContacts) {

                List<String> contactArr = Arrays.asList(contact.split(","));
                String key = contactArr.get(0).toLowerCase() + " " + contactArr.get(1).toLowerCase();
                System.out.println();
                this.contacts.put(key, new Contact(contactArr.get(0), contactArr.get(1), contactArr.get(2)));

             }

         } catch(IOException e) {
             System.out.println(e.getMessage());
         }
     }

     private void writeFile() {
        try{
         List<String> combined = new ArrayList <> ();

         for (Map.Entry<String, Contact> entry : this.contacts.entrySet()) {

             String current = String.format ("%s,%s,%s" , entry.getValue ().getFirstName (), entry.getValue ().getLastName (), entry.getValue ().getPhoneNumber ());

             combined.add(current);

         }

         Files.write(dataFile, combined);

          }catch(IOException e) {
              System.out.println(e.getMessage());
            }
    }

    public HashMap<String, Contact> getContacts() {
        return this.contacts;
    }
    public void setContacts(HashMap<String, Contact> contacts) {
        this.contacts = contacts;
        writeFile();
    }
}
