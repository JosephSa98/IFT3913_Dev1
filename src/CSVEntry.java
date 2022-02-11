public class CSVEntry {
    int complexityMeasure;
    double complexityDegree;
    String chemin;
    String name;
    int LOC;
    int CLOC;
    int DC;

    public CSVEntry(String chemin, String name, int LOC, int CLOC, int DC) {
        this.chemin = chemin;
        this.name = name;
        this.LOC = LOC;
        this.CLOC = CLOC;
        this.DC = DC;
    }

    public CSVEntry(int complexityMeasure, double complexityDegree, String chemin, String name, int LOC, int CLOC, int DC) {
        this.complexityMeasure = complexityMeasure;
        this.complexityDegree = complexityDegree;
        this.chemin = chemin;
        this.name = name;
        this.LOC = LOC;
        this.CLOC = CLOC;
        this.DC = DC;
    }

    public int getComplexityMeasure() {
        return complexityMeasure;
    }

    public void setComplexityMeasure(int complexityMeasure) {
        this.complexityMeasure = complexityMeasure;
    }

    public double getComplexityDegree() {
        return complexityDegree;
    }

    public void setComplexityDegree(double complexityDegree) {
        this.complexityDegree = complexityDegree;
    }

    public String getChemin() {
        return chemin;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLOC() {
        return LOC;
    }

    public void setLOC(int LOC) {
        this.LOC = LOC;
    }

    public int getCLOC() {
        return CLOC;
    }

    public void setCLOC(int CLOC) {
        this.CLOC = CLOC;
    }

    public int getDC() {
        return DC;
    }

    public void setDC(int DC) {
        this.DC = DC;
    }
}
