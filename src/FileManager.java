import java.io.*;
import java.util.*;


public class FileManager{

    /**
     * Compte le nombre d'occurences de subString dans fullString
     * Note: if we count "ifi" in "ififi", the result would be 1
     * @param subString
     * @param fullString
     * @return
     */
    public static int countMatches(String subString, String fullString){
        int count = 0;
        int matchCounter = 0;

        for(int i = 0; i < fullString.length(); i++){
            if(fullString.charAt(i) == subString.charAt(matchCounter)){
                matchCounter++;
            }

            if(matchCounter == subString.length()){
                matchCounter = 0;
                count++;
            }

        }

        return count;
    }

    /**
     * Parcourt une classe et mesure les métriques
     * @param path
     * @return
     */
    public static CSVEntry countSizeClass(File path){
        int linesOfCode = 0;
        int commentLinesOfCode = 0;
        String className = "";
        String packageName = "";
        int WMC = 0;
        int depth = 0; // depth of "{", when we pass from 1 to 2, we assume we're declaring a new function

        System.out.println("Analysing file " + path);

        try {
            if(path.exists()) {
                BufferedReader inputCode = new BufferedReader(new FileReader(path));
                String line;

                // State 0 : nothing particular
                // State 1 : just read '/' while not isBlockComment, should look for /* or //
                // State 2 : just read '*' while isBlockComment, should look for */
                int state = 0;
                boolean isBlockComment = false;
                boolean isLiteral = false;

                // To handle lines with, for example
                // *//*
                boolean commentLineIncremented;


                linesLoop:
                while ((line = inputCode.readLine()) != null) {
                    if (line.indexOf("public class ") == 0 && !isBlockComment && className.equals("")) {
                        className = line.substring("public class ".length()).split("\\{")[0]
                                .split(" implements ")[0];
                    } else if (line.indexOf("public interface ") == 0 && !isBlockComment && className.equals("")) {
                        className = line.substring("public interface ".length()).split("\\{")[0]
                                .split(" implements ")[0];
                    } else if (line.indexOf("public abstract class ") == 0 && !isBlockComment && className.equals("")) {
                        className = line.substring("public abstract class ".length()).split("\\{")[0]
                                .split(" implements ")[0];
                    } else if (line.indexOf("public final class ") == 0 && !isBlockComment && className.equals("")) {
                        className = line.substring("public final class ".length()).split("\\{")[0]
                                .split(" implements ")[0];
                    } else if (line.indexOf("public enum ") == 0 && !isBlockComment && className.equals("")) {
                        className = line.substring("public enum ".length()).split("\\{")[0]
                                .split(" implements ")[0];
                    } else if (line.indexOf("package ") == 0 && !isBlockComment && packageName.equals("")) {
                        packageName = line.substring("package ".length()).split(";")[0];
                    }


                    // Doesn't take into account, for example
                    // String elseelseelse = "if(while(for(case case case"
                    WMC += countMatches("if(", line);
                    WMC += countMatches("else", line);
                    WMC += countMatches("while(", line);
                    WMC += countMatches("for(", line);
                    WMC += countMatches("case ", line);


                    commentLineIncremented = false;

                    if (line.isBlank())
                        continue;

                    for (int i = 0; i < line.length(); i++) {
                        if (line.charAt(i) == '/' && !isLiteral) {
                            switch (state) {
                                case 0:
                                    if (!isBlockComment)
                                        state = 1;
                                    break;
                                case 1:
                                    state = 0;
                                    commentLinesOfCode++;
                                    linesOfCode++;
                                    continue linesLoop;
                                case 2:
                                    commentLinesOfCode++;
                                    commentLineIncremented = true;
                                    isBlockComment = false;
                                default:
                                    state = 0;
                                    break;
                            }
                        } else if (line.charAt(i) == '*' && !isLiteral) {
                            switch (state) {
                                case 0:
                                    if (isBlockComment)
                                        state = 2;
                                    break;
                                case 1:
                                    state = 0;
                                    isBlockComment = true;
                                case 2:
                                    break;
                                default:
                                    state = 0;
                                    break;
                            }
                        } else if (line.charAt(i) == '\"') {
                            // if a line of code is, for example
                            // System.out.println("///****///")
                            isLiteral = !isLiteral;
                        } else {
                            state = 0;
                            if (!isBlockComment && !isLiteral) {
                                if (line.charAt(i) == '{') {
                                    if (depth == 1)
                                        WMC++;
                                    depth++;
                                } else if (line.charAt(i) == '}') {
                                    depth--;
                                }
                            }
                        }
                    }
                    if (isBlockComment && !commentLineIncremented)
                        commentLinesOfCode++;
                    linesOfCode++;
                    state = 0;
                }
                inputCode.close();
            }else{
                System.out.println("Fichier " + path + " non trouvé");
                return new CSVEntry(path.getAbsolutePath(), "", -1, -1, false, "", -1);
            }


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Fichier " + path + " non trouvé");
            return new CSVEntry(path.getAbsolutePath(), "", -1, -1, false, "", -1);
        }

        // System.out.println("className:" + className + "; LOC" + linesOfCode + "; CLOC:" + commentLinesOfCode);

        return new CSVEntry(path.getAbsolutePath(), className, linesOfCode, commentLinesOfCode, false,
                packageName, WMC);
    }

    /**
     * Parcourt un dossier (package) et mesure les métriques
     * @param path
     * @param csv
     * @return
     */
    public static CSVEntry countSizePackage(File path, CSVCreator csv, String extension){
        File[] filesArray = path.listFiles();

        LinkedList<File> folders = new LinkedList<>();

        String packageName = "";

        int packageLOC = 0;
        int packageCLOC = 0;
        int WCP = 0;

        assert filesArray != null;
        for (File f : filesArray) {
            if (f.isDirectory()) {
                folders.add(f);
            } else if (f.getName().contains("." + extension)){
                CSVEntry entry = countSizeClass(f);
                if (packageName.equals("")) {
                    packageName = entry.getPackageName();
                }
                csv.addClassEntry(entry);
                packageCLOC += entry.getCLOC();
                packageLOC += entry.getLOC();
                WCP += entry.getComplexityMeasure();
            }
        }

        for(File folder : folders){
            CSVEntry folderEntry = countSizePackage(folder, csv, extension);
            csv.addPackageEntry(folderEntry);
            packageLOC += folderEntry.getLOC();
            packageCLOC += folderEntry.getCLOC();
            WCP += folderEntry.getComplexityMeasure();
        }

        return new CSVEntry(path.getAbsolutePath(), packageName, packageLOC, packageCLOC, true, "", WCP);
    }


    public static void main(String[] args) throws IOException, InterruptedException {

        String path = "testClass.txt";
        String extension = "";

        String folderName = "";
        // If present, indicates that a CSV already exists and we only want to analyse changed files
        String commitVer = "";

        if(args.length > 0){
            path = args[0];
        }
        if(args.length > 1){
            extension = args[1];
        }
        if(args.length > 3){
            folderName = args[2];
            commitVer = args[3];
        }

        if(commitVer.equals("")) {
            CSVCreator csv = new CSVCreator();

            File file = new File(path);

            if (file.isDirectory()) {
                CSVEntry entry = countSizePackage(file, csv, extension);
                csv.addPackageEntry(entry);
            } else if (file.getName().contains("." + extension)) {
                CSVEntry entry = countSizeClass(file);
                csv.addClassEntry(entry);
            }

            csv.writeCSVFile();
        }else{
            boolean isWindows = System.getProperty("os.name").toLowerCase().contains("win");
            String command;

            // List changed files
            command = (isWindows ? "cmd /c " : "") + "cd " + folderName +
                    " && git diff-tree --no-commit-id --name-only -r " + commitVer;
            System.out.println("\n" + command);
            final Process processList = Runtime.getRuntime().exec(command);

            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(processList.getInputStream()));

            // Fichiers à analyser
            Stack<String> changed = new Stack<>();
            ArrayList<CSVEntry> changedEntries = new ArrayList<>();

            String line;
            while ( (line = stdInput.readLine()) != null){
                if(line.contains("." + extension))
                    changed.add(folderName + "/" + line);
                System.out.println(line);
            }

            processList.waitFor();

            while(!changed.isEmpty()){
                File file = new File(changed.pop());
                // Debug
                //System.out.println("Identified entry " + file.getName());
                changedEntries.add(countSizeClass(file));
            }

            File oldCSV = new File("CSV/classes.csv");
            RandomAccessFile inputCode = new RandomAccessFile(oldCSV, "r");

            File newCSV = new File("CSV/classes_new.csv");
            newCSV.createNewFile();
            FileWriter newCsvWriter = new FileWriter(newCSV);

            while((line = inputCode.readLine()) != null){
                String filePath = line.split(",")[2];

                int hit = -1;
                for (int i = 0; i < changedEntries.size(); i++){
                    if(filePath.equals(changedEntries.get(i).getChemin())){
                        hit = i;
                        break;
                    }
                }

                if(hit >= 0){
                    CSVEntry hitEntry = changedEntries.remove(hit);
                    // Check if is valid
                    // if LOC < 0, then file was removed
                    if(hitEntry.getLOC() >= 0) {
                        // Debug
                        //System.out.println("Verified that " + hitEntry.getLOC()
                        //        + " is greater than 0 for " + hitEntry.getName() + " " + (hitEntry.getLOC() >= 0));
                        newCsvWriter.write(hitEntry.toString() + "\n");
                        // Debug
                        //System.out.println("Writing : " + hitEntry.toString());
                    }
                }else{
                    newCsvWriter.write(line + "\n");

                    // Debug
                    //System.out.println("Writing2 : " + line);
                }
                // Debug
                //System.out.println(changedEntries.size());
            }

            // Current commit contains newly created files
            // Debug
            //System.out.println("Final size: " + changedEntries.size() + "; empty:" + changedEntries.isEmpty());
            while(!changedEntries.isEmpty()){
                CSVEntry addEntry = changedEntries.remove(0);
                newCsvWriter.write(addEntry.toString() + "\n");

                // Debug
                //System.out.println("Writing3 : " + addEntry.toString() + "\n");
            }

            inputCode.close();
            newCsvWriter.close();

            // Replace old with new
            oldCSV.delete();
            newCSV.renameTo(oldCSV);

        }
    }
}

