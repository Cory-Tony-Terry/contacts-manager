import util.Input;

import java.util.HashMap;

public class ContactTest {

        private static ContactFileIO contactFileIO = new ContactFileIO ();
        private static HashMap<String, Contact> contactHashMap = new HashMap<>(contactFileIO.getContacts());

    public static void outputFormat(String name) {
        if (contactHashMap.containsKey(name)) {
            String fullName = contactHashMap.get(name).getFirstName() + " " + contactHashMap.get(name).getLastName();
            String phoneNumber = contactHashMap.get(name).getPhoneNumber();
            System.out.printf("%-15s | %-12s |", fullName, phoneNumber);
        } else {
            System.out.println("name does not exist");
        }
    }

    public static void main(String[] args) {

        Input input = new Input();

        System.out.println("What would you like to do?\n");
        System.out.println("1. View contacts.");
        System.out.println("2. Add a new contact.");
        System.out.println("3. Search a contact by name.");
        System.out.println("4. Delete an existing contact.");
        System.out.println("5. Exit.");

        int userChoice = input.getInt(1,5);

        System.out.println("\n");

        String header = "Name            | Phone number |";
        String hr = "-------------------------------";
        System.out.println(header);
        System.out.println(hr);

        if (userChoice == 1){
            for (String key : contactHashMap.keySet()) {
                outputFormat(key);
            }
            System.out.println();
        } else if (userChoice == 2) {

        } else if (userChoice == 3) {

        } else if (userChoice == 4) {

        } else if (userChoice == 5) {

        }


    }


}
