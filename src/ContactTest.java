public class ContactTest {

    public static void main(String[] args) {

    ContactFileIO test = new ContactFileIO ();
        System.out.println (test.getContacts ());

        test.setContacts(test.getContacts());

    }

}
