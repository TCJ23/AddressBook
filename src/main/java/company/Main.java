package company;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        ConsoleInput consoleInput = new ConsoleInput();
        IAddressBookManager addressBookManager = new AddressBookManager();
//        ConsoleInput consoleInput = (ConsoleInput) context.getBean("consoleInput");
//        IAddressBookManager addressBookManager = (AddressBookManager) context.getBean("addressBookManager");
        boolean eof = true;
        while (eof) {
            String s = consoleInput.getMenuItemFromConsole();

            switch (s) {
                case "1":
                    System.out.println("You chose option to Find record ");
                    String textToSearch = consoleInput.getTextToSearch();
                    List<AddressBookEntry> adr = addressBookManager.findAddrBookEntry(textToSearch);
                    if (adr.isEmpty() == false) {
                        for (AddressBookEntry i : adr) {
                            System.out.println("Found following record " + i.toString());
                        }
                    } else if (adr.isEmpty()) {
                        System.out.println("Not sure what you looking for ?");
                    }
                    break;
                case "2":
                    System.out.println("You chose option to add");
                    String strFirst = consoleInput.getFirstNameFromConsole();
                    String strLast = consoleInput.getLastNameFromConsole();
                    String strPhone = makePhoneAmericanStd(consoleInput);
                    addressBookManager.addAddrBookEntry(strFirst, strLast, strPhone);
                    break;
                case "3":
                    System.out.println("You chose option to remove");
                    Integer idToRemove = consoleInput.getFindAddrBookFromConsole();
                    boolean success = addressBookManager.removeAddrBookEntry(idToRemove);
                    if (success) {
                        System.out.println("Record of id " + idToRemove + " was deleted");
                    } else {
                        System.out.println("sorry we couldn't find your record");
                    }
                    break;
                case "4":
                    System.out.println("You chose option to list all");
                    for (AddressBookEntry addressBookEntry : addressBookManager.listAllAddrBookEntries()) {
                        System.out.println(addressBookEntry.toString());
                    }
                    break;
                case "5":
                    System.out.println("You chose option to edit your phone number");
                    Integer idToEdit = consoleInput.getFindAddrBookFromConsole();
                    String phoneToEdit = makePhoneAmericanStd(consoleInput);
                    addressBookManager.editAddrBookEntry(idToEdit, phoneToEdit);
                    break;
                case "6":
                    System.out.println("You want to sort all records :");
                    for (AddressBookEntry addressBookEntry : addressBookManager.listAllSortedBookEntires()) {
                        System.out.println(addressBookEntry.toString());
                    }
                    break;
                case "7":
                    System.out.println("You want to sort records");
                    System.out.println("By which field you would like to search for ?\n" +
                            " You can chose \n" +
                            " 1 -> ID, \n" +
                            " 2 -> Firstname, \n" +
                            " 3 -> Lastname, \n" +
                            " 4 -> TelephoneNumber, \n");
                    String textToSort = consoleInput.getTextToSort();
                    for (AddressBookEntry addressBookEntry : addressBookManager.listAllSortedBookEntires(textToSort)) {
                        System.out.println(addressBookEntry.toString());
                    }
                    break;
                case "8":
                    System.out.println("You chose option to exit");
                    eof = false;
                    break;
                default:
                    System.out.println("You chose incorrect option, please type number only.");
            }
        }
    }

    private static String makePhoneAmericanStd(ConsoleInput consoleInput) {
        String phoneToEdit = consoleInput.getPhoneNumberFromConsole().replaceAll("[^0-9]", "");
        while (phoneToEdit.length() != 10) {
            System.out.println("Please provide exactly 10 gigits number");
            phoneToEdit = consoleInput.getPhoneNumberFromConsole().replaceAll("[^0-9]", "");
        }
        phoneToEdit = String.format("(%s) %s-%s", phoneToEdit.substring(0, 3), phoneToEdit.substring(3, 6), phoneToEdit.substring(6, 10));
        return phoneToEdit;
    }
}
