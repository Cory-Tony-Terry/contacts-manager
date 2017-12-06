import util.Input;

public class ContactTest {

    public static void main(String[] args) {

        Input input = new Input();

        System.out.println("What would you like to do?\n");
        System.out.println("1. View contacts.");
        System.out.println("2. Add a new contact.");
        System.out.println("3. Search a contact by name.");
        System.out.println("4. Delete an existing contact.");
        System.out.println("5. Exit.");
        System.out.println("Enter an option (1, 2, 3, 4 or 5):");





    ContactFileIO test = new ContactFileIO ();
        System.out.println (test.getContacts ());

        test.setContacts(test.getContacts());

    }

}
