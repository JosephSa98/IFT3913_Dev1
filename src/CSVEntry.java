import java.util.Objects;

public class CSVEntry {
    private int complexityMeasure;
    private double complexityDegree;
    private String chemin;
    private String name;
    private int LOC;
    private int CLOC;
    private double DC;
    private boolean isPackage;
    private String packageName;    //not used if isPackage since already contained in name


    /**
     * Constructeur
     * @param isPackage
     */
    public CSVEntry(boolean isPackage) {
        this.isPackage = isPackage;
        packageName = "";
    }

    /**
     * Constructeur
     * @param chemin
     * @param name
     * @param LOC
     * @param CLOC
     * @param isPackage
     * @param packageName
     * @param complexityMeasure
     */
    public CSVEntry(String chemin, String name, int LOC, int CLOC, boolean isPackage, String packageName, int complexityMeasure) {
        this.chemin = chemin;
        this.name = name;
        setLOC(LOC);
        setCLOC(CLOC);
        this.isPackage = isPackage;
        this.packageName = packageName;
        setComplexityMeasure(complexityMeasure);
    }

    /**
     * Getter pour le complexity measure
     * @return
     */
    public int getComplexityMeasure() {
        return complexityMeasure;
    }

    /**
     * Setter pour le complexity measure
     * @param complexityMeasure
     */
    public void setComplexityMeasure(int complexityMeasure) {
        this.complexityMeasure = complexityMeasure;
        this.complexityDegree = DC/complexityMeasure;
    }

    /**
     * Getter pour le complexity degree
     * @return
     */
    public double getComplexityDegree() {
        return complexityDegree;
    }

    /**
     * Getter pour le path
     * @return
     */
    public String getChemin() {
        return chemin;
    }

    /**
     * Setter pour le path
     * @param chemin
     */
    public void setChemin(String chemin) {
        this.chemin = chemin;
    }

    /**
     * Getter pour name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Setter pour name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter pour LOC
     * @return
     */
    public int getLOC() {
        return LOC;
    }

    /**
     * Setter pour LOC
     * @param LOC
     */
    public void setLOC(int LOC) {
        this.LOC = LOC;
        DC = ((double) CLOC)/LOC;
    }

    /**
     * Getter pour CLOC
     * @return
     */
    public int getCLOC() {
        return CLOC;
    }

    /**
     * Setter pour CLOC
     * @param CLOC
     */
    public void setCLOC(int CLOC) {
        this.CLOC = CLOC;
        if(LOC > 0)
            DC = ((double) CLOC)/LOC;
    }

    /**
     * Getter pour DC
     * @return
     */
    public double getDC() {
        return DC;
    }

    /**
     * Getter pour isPackage
     * @return
     */
    public boolean isPackage() {
        return isPackage;
    }

    /**
     * Setter pour isPackage
     * @param aPackage
     */
    public void setPackage(boolean aPackage) {
        isPackage = aPackage;
    }

    /**
     * Getter pour packageName
     * @return
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * Setter pour packageName
     * @param packageName
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    /**
     * Retourne la String correspondant à l'entrée CSV
     * @return
     */
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(complexityMeasure).append(",")
                .append(complexityDegree).append(",")
                .append(chemin).append(",")
                .append(name).append(",")
                .append(LOC).append(",")
                .append(CLOC).append(",")
                .append(DC);

        return stringBuilder.toString();
    }
}
