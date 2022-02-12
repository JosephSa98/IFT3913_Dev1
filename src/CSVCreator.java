import javax.swing.*;
import java.io.*;
import java.util.LinkedList;

public class CSVCreator {
    private LinkedList<CSVEntry> classEntries;
    private LinkedList<CSVEntry> packageEntries;


    public CSVCreator() {
        classEntries = new LinkedList<>();
        packageEntries = new LinkedList<>();
    }

    public LinkedList<CSVEntry> getClassEntries() {
        return classEntries;
    }


    public void addClassEntry(CSVEntry classEntry) {
        this.classEntries.add(classEntry);
    }

    public LinkedList<CSVEntry> getPackageEntries() {
        return packageEntries;
    }

    public void addPackageEntry(CSVEntry packageEntry) {
        this.packageEntries.add(packageEntry);
    }

    /*APPEL LA FONCTION REPORTCLASSESINPUT() POUR GENERER UN ARRAY CONTENANT LES INFOS*/
/*ENSUITE FAIT APPEL A WRITECSVFILE QUI VA ECRIRE DANS LE FICHIER CSV LES INFOS ET LEURS NOMS DE COLOMNES*/

//    public void produceData() {
//
//        String[] results = this.reportClassesInput();
//        this.writeCSVFile(data,true);
//    }


/*CHERCHE L'INFORMATION DES CLASSES CALUCULANT LOC, CLOC ETC... PAR NOTRE AUTOMATE :) ET LES INSERE DANS LE ARRAY "DATA"*/

//    public String[] reportClassesInput(){
//        String[] data = new String[this.classes.length+1];
//        data[0] = "chemin ," + "class ," + "classe_LOC ," + "classe_CLOC ," + "classe_DC \n";
//        for (var i=0; i < this.classes.length; i++) {
//            data[i+1] = this.absolutePaths[i] + "," + this.classNames[i] + "," +
//                    classe_LOC(this.classes[i]) + "," + classe_CLOC(this.classes[i]) +
//                    "," + classe_DC(this.classes[i])+ "\n";
//        }
//        return data;
//    }


    /**
     * Ecrit dans le fichier CSV les infos recu par la fonction produceData()
     * @param data
     * @param append
     */
    public static void writeCSVFile(String[] data, boolean append){

        String csvClassesPath = "CSV/classes.csv";

        try {
            FileWriter csvWriter = new FileWriter(csvClassesPath, append);
            BufferedWriter bw = new BufferedWriter(csvWriter);
            PrintWriter pw = new PrintWriter(bw);

            //data[0] = "chemin ,"+"class ,"+"classe_LOC ,"+"classe_CLOC ,"+"classe_DC"+"\n";


            int i = 0;
            while (i < data.length) {

                if(i < data.length-1){
                    pw.print(data[i] + ", ");
                }
                else{
                    pw.println(data[i] + "\n");
                }
                pw.flush();
                i++;
            }
            pw.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Record NOT saved!");
        }
    }

}
