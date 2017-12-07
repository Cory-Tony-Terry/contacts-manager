import util.Input;

import javax.swing.text.MaskFormatter;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.HashMap;

public class ContactTest {
    private static ContactFileIO contactFileIO = new ContactFileIO ();
    private static HashMap<String, Contact> contactHashMap = new HashMap<>(contactFileIO.getContacts());
    private static Input input = new Input();

    public static void main(String[] args) throws ParseException {
        boolean begin = true;

        do {


            System.out.println ("What would you like to do?\n");
            System.out.println ("1. View contacts.");
            System.out.println ("2. Add a new contact.");
            System.out.println ("3. Search a contact by name.");
            System.out.println ("4. Delete an existing contact.");
            System.out.println ("5. Exit.");

            int userChoice = input.getInt (1, 5);

            System.out.println ("\n");

            String header = "Name            | Phone number |";
            String hr = "-------------------------------";


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
                addNameToArray (currentContact[0], currentContact[1], currentContact[2]);

            } else if (userChoice == 3) {

                input.getString ();
                String[] currentContact = getContactInfo();
                String fullName = currentContact[0].toLowerCase () + " " + currentContact[1].toLowerCase ();
                outputFormat (fullName);

            } else if (userChoice == 4) {

                input.getString ();
                String[] currentContact = getContactInfo();
                String fullName = currentContact[0].toLowerCase () + " " + currentContact[1].toLowerCase ();
                removeNamefromArray (fullName);

            } else if (userChoice == 5) {
              begin = false;
            }
        }while(begin);

        contactFileIO.setContacts (contactHashMap);

    }

    public static void outputFormat(String name) throws ParseException {

        try {
            String phoneMaskSevenDigit = "###-####";
            String phoneMaskNineDigit = "###-###-####";

            MaskFormatter maskFormatter= new MaskFormatter(phoneMaskNineDigit);
            maskFormatter.setValidCharacters("0123456789");
            maskFormatter.setValueContainsLiteralCharacters(false);

            if (contactHashMap.containsKey(name)) {
                String fullName = contactHashMap.get(name).getFirstName() + " " + contactHashMap.get(name).getLastName();
                String phoneNumber = contactHashMap.get(name).getPhoneNumber();
                String formattedPhoneNumber = maskFormatter.valueToString(phoneNumber);

                System.out.printf("%-15s | %-12s |", fullName, formattedPhoneNumber);
                System.out.println ();
            } else {
                System.out.println("name does not exist");
            }
        } catch(java.text.ParseException e) {
            System.out.println(e.getMessage());
        }


    }

    public static void addNameToArray(String firstName, String lastName, String phoneNumber) throws ParseException {
        String key = firstName.toLowerCase() + " " + lastName.toLowerCase();
        contactHashMap.putIfAbsent (key,  new Contact(firstName, lastName, phoneNumber));
        outputFormat (key);

    }
    public static void removeNamefromArray(String key) {
        contactHashMap.remove (key);
    }

    private static String[] getContactInfo(){
        String[] contactInfo;
        contactInfo = new String[2];
        input.getString ();
        System.out.print ("Please input the contacts First Name: ");
        contactInfo[0] = input.getString ();
        System.out.print ("Please input the contacts Last Name: ");
        contactInfo[1] = input.getString ();
        return contactInfo;
    }

    private static String[] getContactInfo(String full){
            String[] contactInfo;
            contactInfo = new String[3];
            input.getString();
            System.out.print("Please input the contacts First Name: ");
            contactInfo[0] = input.getString();
            System.out.print("Please input the contacts Last Name: ");
            contactInfo[1] = input.getString();
            System.out.print("Please input the contacts  Phone Number: ");
            contactInfo[2] = input.getString();
            return contactInfo;
    }



}
