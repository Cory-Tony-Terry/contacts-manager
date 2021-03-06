import util.Input;
import javax.swing.text.MaskFormatter;
import java.util.HashMap;

public class ContactApplication {
    private static ContactFileIO contactFileIO = new ContactFileIO ();
    private static HashMap<String, Contact> contactHashMap = new HashMap<>(contactFileIO.getContacts());
    private static Input input = new Input();

    public static void main(String[] args){
        boolean begin = true;
        String header = "Name            | Phone number |";
        String hr = "-------------------------------";

        do {

            System.out.println ("What would you like to do?\n");
            System.out.println ("1. View contacts.");
            System.out.println ("2. Add a new contact.");
            System.out.println ("3. Search a contact by name.");
            System.out.println ("4. Delete an existing contact.");
            System.out.println ("5. Exit.");

            int userChoice = input.getInt (1, 5);

            System.out.println ("\n");

            if (userChoice == 1) {
                System.out.println (header);
                System.out.println (hr);

                for (String key : contactHashMap.keySet ()) {
                    outputFormat (key);
                }

                System.out.println ();

            } else if (userChoice == 2) {

                System.out.println ();
                input.getString ();
                String[] currentContact = getContactInfo("full");
                System.out.println();
                addNameToArray (currentContact[0], currentContact[1], currentContact[2]);
                System.out.println();
                contactFileIO.setContacts (contactHashMap);

            } else if (userChoice == 3) {

                input.getString ();
                String[] currentContact = getContactInfo();
                String fullName = currentContact[0].toLowerCase () + " " + currentContact[1].toLowerCase ();
                System.out.println();
                outputFormat (fullName);
                System.out.println();

            } else if (userChoice == 4) {

                input.getString ();
                String[] currentContact = getContactInfo();
                String fullName = currentContact[0].toLowerCase () + " " + currentContact[1].toLowerCase ();
                removeNamefromArray (fullName);
                System.out.println();
                contactFileIO.setContacts (contactHashMap);


            } else if (userChoice == 5) {
              begin = false;
            }
        }while(begin);

        contactFileIO.setContacts (contactHashMap);

    }

    private static void outputFormat(String name){
        try {
            String phoneMaskSevenDigit = "###-####";
            String phoneMaskNineDigit = "###-###-####";

            MaskFormatter maskFormatter= new MaskFormatter(phoneMaskNineDigit);
            maskFormatter.setValidCharacters("0123456789");
            maskFormatter.setValueContainsLiteralCharacters(false);

            MaskFormatter maskFormatter7= new MaskFormatter(phoneMaskSevenDigit);
            maskFormatter7.setValidCharacters("0123456789");
            maskFormatter7.setValueContainsLiteralCharacters(false);

            if (contactHashMap.containsKey(name)) {

                if (contactHashMap.get(name).getPhoneNumber().length() > 7){
                    String fullName = contactHashMap.get(name).getFirstName() + " " + contactHashMap.get(name).getLastName();
                    String phoneNumber = contactHashMap.get(name).getPhoneNumber();
                    String formattedPhoneNumber = maskFormatter.valueToString(phoneNumber);

                    System.out.printf("%-15s | %-12s |", fullName, formattedPhoneNumber);
                    System.out.println ();
                } else {
                    String fullName = contactHashMap.get(name).getFirstName() + " " + contactHashMap.get(name).getLastName();
                    String phoneNumber = contactHashMap.get(name).getPhoneNumber();
                    String formattedPhoneNumber = maskFormatter7.valueToString(phoneNumber);

                    System.out.printf("%-15s | %-12s |", fullName, formattedPhoneNumber);
                    System.out.println ();
                }


            } else {
                System.out.println("name does not exist");
            }
        } catch(java.text.ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addNameToArray(String firstName, String lastName, String phoneNumber){
        String key = firstName.toLowerCase() + " " + lastName.toLowerCase();
        contactHashMap.put (key,  new Contact(firstName, lastName, phoneNumber));
        outputFormat (key);
    }

    private static void removeNamefromArray(String key) {
        contactHashMap.remove (key);
    }

    private static String[] getContactInfo(){
        String[] contactInfo;
        contactInfo = new String[2];
        System.out.print ("Please input the contacts First Name: ");
        contactInfo[0] = input.getString ();
        System.out.print ("Please input the contacts Last Name: ");
        contactInfo[1] = input.getString ();
        return contactInfo;
    }

    private static String[] getContactInfo(String full){
            String[] contactInfo;
            contactInfo = new String[3];
            System.out.print("Please input the contacts First Name: ");
            contactInfo[0] = input.getString();
            System.out.print("Please input the contacts Last Name: ");
            contactInfo[1] = input.getString();
            System.out.print("Please input the contacts  Phone Number: ");
            contactInfo[2] = input.getString();
            return contactInfo;
    }
}
