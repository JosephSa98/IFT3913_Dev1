public class FileManager{
    public static void main(String[] args) {
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

