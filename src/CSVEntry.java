public class CSVEntry {
    int complexityMeasure;
    double complexityDegree;
    String chemin;
    String name;
    int LOC;
    int CLOC;
    double DC;
    boolean isPackage;
    String packageName;    //not used if isPackage since already contained in name


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
     */
    public CSVEntry(String chemin, String name, int LOC, int CLOC, boolean isPackage, String packageName) {
        this.chemin = chemin;
        this.name = name;
        this.LOC = LOC;
        this.CLOC = CLOC;
        this.isPackage = isPackage;
        this.packageName = packageName;
    }

    /**
     * Constructeur
     * @param complexityMeasure
     * @param complexityDegree
     * @param chemin
     * @param name
     * @param LOC
     * @param CLOC
     * @param DC
     * @param isPackage
     */
    public CSVEntry(int complexityMeasure, double complexityDegree, String chemin, String name, int LOC, int CLOC,
                    double DC, boolean isPackage) {
        this.complexityMeasure = complexityMeasure;
        this.complexityDegree = complexityDegree;
        this.chemin = chemin;
        this.name = name;
        this.LOC = LOC;
        this.CLOC = CLOC;
        this.DC = DC;
        this.isPackage = isPackage;
        this.packageName = "";
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
    }

    /**
     * Getter pour le complexity degree
     * @return
     */
    public double getComplexityDegree() {
        return complexityDegree;
    }

    /**
     * Setter pour le complexity degree
     * @param complexityDegree
     */
    public void setComplexityDegree(double complexityDegree) {
        this.complexityDegree = complexityDegree;
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

    public boolean isPackage() {
        return isPackage;
    }

    public void setPackage(boolean aPackage) {
        isPackage = aPackage;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
