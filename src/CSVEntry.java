public class CSVEntry {
    int complexityMeasure;
    double complexityDegree;
    String chemin;
    String name;
    int LOC;
    int CLOC;
    double DC;

    /**
     * Constructeur ...
     * @param chemin
     * @param name
     * @param LOC
     * @param CLOC
     * @param DC
     */
    public CSVEntry(String chemin, String name, int LOC, int CLOC, double DC) {
        this.chemin = chemin;
        this.name = name;
        this.LOC = LOC;
        this.CLOC = CLOC;
    }

    /**
     * Constructeur...
     * @param complexityMeasure
     * @param complexityDegree
     * @param chemin
     * @param name
     * @param LOC
     * @param CLOC
     * @param DC
     */
    public CSVEntry(int complexityMeasure, double complexityDegree, String chemin, String name, int LOC, int CLOC, double DC) {
        this.complexityMeasure = complexityMeasure;
        this.complexityDegree = complexityDegree;
        this.chemin = chemin;
        this.name = name;
        this.LOC = LOC;
        this.CLOC = CLOC;
        this.DC = DC;
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
    }

    /**
     * Getter pour DC
     * @return
     */
    public double getDC() {
        return DC;
    }

    /**
     * Setter pour DC
     * @param DC
     */
    public void setDC(double DC) {
        this.DC = DC;
    }
}
