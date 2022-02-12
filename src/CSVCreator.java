import java.io.*;
import java.util.LinkedList;

public class CSVCreator {
    private LinkedList<CSVEntry> classEntries;
    private LinkedList<CSVEntry> packageEntries;


    /**
     * Constructeur
     */
    public CSVCreator() {
        classEntries = new LinkedList<>();
        packageEntries = new LinkedList<>();
    }

    /**
     * Getter pour classEntries
     * @return
     */
    public LinkedList<CSVEntry> getClassEntries() {
        return classEntries;
    }

    /**
     * Ajoute un classEntry à classEntries
     * @param classEntry
     */
    public void addClassEntry(CSVEntry classEntry) {
        this.classEntries.add(classEntry);
    }

    /**
     * Getter pour packageEntries
     * @return
     */
    public LinkedList<CSVEntry> getPackageEntries() {
        return packageEntries;
    }

    /**
     * Ajoute un packageEntry à packageEntries
     * @param packageEntry
     */
    public void addPackageEntry(CSVEntry packageEntry) {
        this.packageEntries.add(packageEntry);
    }

    /**
     * Transforme le tableau des métriques de classe en String
     * @return
     */
    public String classEntriesToString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("WMC,class_BC,chemin,class,classe_LOC,classe_CLOC,classe_DC\n");
        for(CSVEntry entry : classEntries){
            stringBuilder.append(entry.toString()).append("\n");
        }

        return stringBuilder.toString();
    }

    /**
     * Transforme le tableau des métriques de package en String
     * @return
     */
    public String packageEntriesToString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("WCP,paquet_BC,chemin,paquet,paquet_LOC,paquet_CLOC,paquet_DC\n");
        for(CSVEntry entry : packageEntries){
            stringBuilder.append(entry.toString()).append("\n");
        }

        return stringBuilder.toString();
    }
    
    /**
     * Écrit les fichiers CSV
     */
    public void writeCSVFile(){
        try {

            File csvClasses = new File("CSV/classes.csv");
            csvClasses.createNewFile();
            File csvPackages = new File("CSV/paquets.csv");
            csvPackages.createNewFile();

            FileWriter classCsvWriter = new FileWriter(csvClasses);
            classCsvWriter.write(this.classEntriesToString());


            FileWriter packageCsvWriter = new FileWriter(csvClasses);
            packageCsvWriter.write(this.packageEntriesToString());

        }catch(Exception e){
            System.out.println("Error creating files");
        }

    }

}
