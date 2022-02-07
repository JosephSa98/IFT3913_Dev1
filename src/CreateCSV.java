import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class CreateCSV {
    public static void main (String[] args){
        String record = "this is a test";
        String record2 = "WORKING!";
        String filePath = "csvTester.txt";

        saveRecord(record,record2,filePath);

    }
    public static void saveRecord(String record,String record2,String filePath){
        try {
            FileWriter fw = new FileWriter(filePath,false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(record+", "+record2);
            pw.flush();
            pw.close();
        }
        catch (Exception E){
            JOptionPane.showMessageDialog(null,"Record NOT saved!");
        }
    }
}
