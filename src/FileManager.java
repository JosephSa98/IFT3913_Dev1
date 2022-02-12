import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.LinkedList;

public class FileManager{

    public static CSVEntry countSizeClass(File path){
        int linesOfCode = 0;
        int commentLinesOfCode = 0;
        String className = "";
        String packageName = "";

        try {
            RandomAccessFile inputCode = new RandomAccessFile(path, "r");
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
            while((line = inputCode.readLine()) != null){
                if(line.indexOf("public class ") == 0 && !isBlockComment && className.equals("")){
                    className = line.substring(13).split("\\{")[0];
                }else if(line.indexOf("package ") == 0 && !isBlockComment && packageName.equals("")){
                    packageName = line.substring(8).split(";")[0];
                }

                commentLineIncremented = false;

                if(line.isBlank())
                    continue;

                for(int i = 0; i < line.length(); i++){
                    if(line.charAt(i) == '/' && !isLiteral) {
                        switch (state) {
                            case 0:
                                if(!isBlockComment)
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
                    }else if(line.charAt(i) == '*' && !isLiteral){
                        switch (state) {
                            case 0:
                                if(isBlockComment)
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
                    }else if(line.charAt(i) == '\"'){
                        // if a line of code is, for example
                        // System.out.println("///****///")
                        isLiteral = !isLiteral;
                    }else{
                        state = 0;
                    }

                }
                if(isBlockComment && !commentLineIncremented)
                    commentLinesOfCode++;
                linesOfCode++;
                state = 0;
            }
            inputCode.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Échec lors de l'accès au fichier");
        }

        System.out.println("className:" + className + "; LOC" + linesOfCode + "; CLOC:" + commentLinesOfCode);

        return new CSVEntry(path.getAbsolutePath(), className, linesOfCode, commentLinesOfCode, false, packageName);
    }


    public static void main(String[] args){
        String path = "testClass.txt";
        if(args.length > 1){
            path = args[1];
        }

        CSVCreator csv = new CSVCreator();

        File file = new File(path);

        if(file.isDirectory()){
            File[] filesArray = file.listFiles();
            LinkedList<File> folders = new LinkedList<>();
            CSVEntry basePackageEntry = new CSVEntry(true);
            String packageName = "";
            if(filesArray == null) {
                System.out.println("Not a directory : " + path);
            }else {
                int packageLOC = 0;
                int packageCLOC = 0;
                for (File f : filesArray) {
                    if (f.isDirectory()) {
                        folders.add(f);
                    } else {
                        CSVEntry entry = countSizeClass(f);
                        if (packageName.equals("")) {
                            packageName = entry.packageName;
                        }
                        csv.addClassEntry(entry);
                        packageCLOC += entry.CLOC;
                        packageLOC += entry.LOC;
                    }
                }
                basePackageEntry.setLOC(packageLOC);
                basePackageEntry.setCLOC(packageCLOC);
                basePackageEntry.setName(packageName);
                basePackageEntry.setChemin(file.getAbsolutePath());

                //TODO: do something recursively (?) with remaining folders in LinkedList folders
            }
        }else{
            CSVEntry entry = countSizeClass(file);
            csv.addClassEntry(entry);
        }



        String[] linesOfcode = {"/*hi",
                "cava*/" ,
                "bien" ,
                "//et" ,
                "toi" ,
                "",
                "",
                "//merci",
                "",
                "",
                "",
                "",
                "fin"};
        linesOfCommentInClass(linesOfcode);
        nonEmptyLinesOfCode(linesOfcode);
    }

    /*
     * Trouver le CLOC dans la classe
     * */
    public static void linesOfCommentInClass(String[] linesOfcode){
        int comments = 0;

        for (int i = 0; i < linesOfcode.length; i++) {
            if(linesOfcode[i].contains("/*")){
                while (!(linesOfcode[i].contains("*/")) && (i < linesOfcode.length - 1)){
                    comments++;
                    i++;
                }
                if (linesOfcode[i].contains("*/")){
                    comments++;
                }
            }else if (linesOfcode[i].contains("//")){
                comments++;
            }
        }
        System.out.println("Lines of comment: " + comments);
    }

    /*
    * Trouver le nombre de ligne qui ne sont pas vide
    * */
    public static void nonEmptyLinesOfCode (String[] linesOfcode){
        int nonEmptyLines = 0;

        for (int i = 0; i < linesOfcode.length; i++) {
            if (linesOfcode[i].isBlank()){
                continue;
            }else {
                nonEmptyLines++;
            }
        }
        System.out.println("Number of non empty lines: " + nonEmptyLines);
    }

}

