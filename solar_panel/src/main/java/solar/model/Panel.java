package solar.model;

public class Panel {


    //Fields
    private int ID;
    private String Section;
    private int row;
    private int col;
    public int year;
    public PanelMateriel materiel;
    public String IsTracking;

    //Constructor for creating the Brand new panel
    public Panel() {

    }

    public Panel(int ID, String section, int row, int col, int year, PanelMateriel materiel, String isTracking) {
        this.ID = ID;
        Section = section;
        this.row = row;
        this.col = col;
        this.year = year;
        this.materiel = materiel;
        this.IsTracking = isTracking;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSection() {
        return Section;
    }

    public void setSection(String section) {
        this.Section = section;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public PanelMateriel getMateriel() {
        return materiel;
    }

    public void setMateriel(PanelMateriel materiel) {
        this.materiel = materiel;
    }

    public String getTracking() {
        return IsTracking;
    }

    public void setTracking(String tracking) {
        IsTracking = tracking;
    }
}


//Constructor for representing an existing panel stored in a file.
