package company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvImporter {

    public static List<AddressBookEntry> importFromCSV(String records) {
        List<String> splitter = Arrays.asList(records.split("\n"));
        List<String> getAllLines = splitter.subList(1, splitter.size());
        List<AddressBookEntry> addressBookEntriesCSV = new ArrayList<>();
        for (String line : getAllLines) {
            String[] pole = line.split(",");
            Integer idOfRecord = Integer.parseInt(pole[0]);
            String firstname = pole[1];
            String lastname = pole[2];
            String phoneNumber = pole[3];
            AddressBookEntry addBookEntry = new AddressBookEntry(idOfRecord, firstname, lastname, phoneNumber);
            addressBookEntriesCSV.add(addBookEntry);
        }
        return addressBookEntriesCSV;
    }
}