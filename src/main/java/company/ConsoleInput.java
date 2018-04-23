package company;

import java.util.Scanner;

public class ConsoleInput {

    private Scanner scan = new Scanner(System.in).useDelimiter("\\n");


    public String getMenuItemFromConsole() {
        System.out.println(" ");
        System.out.println("Please chose one of actions you would like to perform");
        System.out.println("1. Find"); //done
        System.out.println("2. Add"); //done
        System.out.println("3. Remove");//done
        System.out.println("4. List all"); //done
        System.out.println("5. Edit your phone number");//done
        System.out.println("6. Sort");
        System.out.println("7. Sort by variable");
        System.out.println("8. Exit");
        System.out.println(" ");
        return scan.next();
    }

    public Integer getFindAddrBookFromConsole() {
        System.out.println("Please provide ID of a person: ");
        return scan.nextInt();
    }

    public String getFirstNameFromConsole() {
        System.out.println("Please give me your firstname");
        return scan.next();
    }

    public String getLastNameFromConsole() {
        System.out.println("Please give me your lastame");
        return scan.next();
    }

    public String getPhoneNumberFromConsole() {
        System.out.println("Please provide me your new phone number...\n" +
        "It must be in american format so we accept 10 digits only :");
        return scan.next();
    }

    public String getTextToSearch() {
        System.out.println("Please provide text to search for :");
        return scan.next();
    }
    public String getTextToSort() {
        System.out.println("Please provide text to search for :");
        return scan.next();
    }
}
