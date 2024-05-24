package data;

import password.PasswordStore;
//import password.Encryptor;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class DataPassword {

    public static final ArrayList<PasswordStore> passData = new ArrayList<PasswordStore>();
    private static final String csvPath = "./datapassword.csv";
    private static final String[] headers = {"name", "username", "password",
            "hashkey", "category", "score"};

    public static void saveCSVData() {
        if (passData.isEmpty()) {
            System.out.println("data masih kosong");
        } else {
            try {
                FileWriter writer = new FileWriter("./datapassword.csv");
                CSVFormat formater = CSVFormat.DEFAULT.builder().setHeader(headers).build();
                CSVPrinter printer = new CSVPrinter(writer, formater);
                for (PasswordStore pass : passData) {
                    printer.printRecord(pass.name, pass.username, pass.getEncPassword(),
                            pass.getHashKey(), pass.getCategoryCode(), pass.getScore());
                }
                printer.flush();
            } catch (IOException ex) {
                Logger.getLogger(DataPassword.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static ArrayList<PasswordStore> loadCSVData() throws Exception {
        passData.clear();
        try {
            FileReader reader = new FileReader(csvPath);
            CSVFormat format = CSVFormat.DEFAULT.builder().setHeader(headers)
                    .setSkipHeaderRecord(true).build();
            Iterable<CSVRecord> data = format.parse(reader);
            for (CSVRecord record : data) {
                PasswordStore newPass;
                if (record.get("hashkey") == null) {
                    newPass = new PasswordStore(
                            record.get("name"),
                            record.get("username"),
                            record.get("password"),
                            Integer.parseInt(record.get("category")));
                } else {
                    newPass = new PasswordStore(
                            record.get("name"),
                            record.get("username"),
                            record.get("password"),
                            Integer.parseInt(record.get("category")));
                }
                passData.add(newPass);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Data password kosong ...");
        } catch (IOException ex) {
            Logger.getLogger(DataPassword.class.getName()).log(Level.SEVERE, null, ex);
        }
        return passData;
    }
}