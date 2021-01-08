package solar.domain;

import solar.data.DataAcessException;
import solar.data.PanelRepository;
import solar.model.Panel;
import solar.model.PanelMateriel;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class PanelService {
    private final PanelRepository repository;


    // public PanelService(){

    //}

    public PanelService(PanelRepository repository) {
        this.repository = repository;
    }

    //add
    //sec can't be null
    //0<row and col <250
    //Materiel is required
    //isTracking is req
    //Year must be in the past
    public PanelResult add(Panel panel) throws DataAcessException {
        //two types of validation input validation or domain validation
        //input validation
        PanelResult result = validateInput(panel);
        if (!result.isSuccess()) {
            return result;
        }
        Panel p = repository.add(panel);
        result.setPayload(p);
        return result;
    }

    private PanelResult validateInput(Panel panel) throws DataAcessException {
        PanelResult result = new PanelResult();
        if (panel == null) {
            result.addErrorMessage("panel cannot be null");
            return result;
        }
        if (panel.getSection() == null || panel.getSection().trim().length() == 0) {
            result.addErrorMessage("Sections is required");
        }
        //Boolean comparingMateriels;
        if (panel.getMateriel() == null || comparingMateriels() == false) {
            result.addErrorMessage("Materiel is required");
        }
        //checking is tracking
        if (panel.getTracking() == null) {
            result.addErrorMessage("isTracking is required");
        }
        //check row and col
        if (panel.getRow() < 0 || panel.getRow() > 250) {
            result.addErrorMessage("Row Should be positive number less than or equal to 250");
        }
        if (panel.getCol() < 0 || panel.getCol() > 250) {
            result.addErrorMessage("Column Should be positive number less than or equal to 250");
        }
        int currentYear = LocalDate.now().getYear();
        if (panel.getYear() > currentYear) {
            result.addErrorMessage("Year Installed must be in the past");
        }
        //Combined value of sec row and col should not be duplicate
        List<Panel> panels = findAll();
        for (Panel p : panels) {
                if (p.getID() != panel.getID() && Objects.equals(panel.getSection(), p.getSection())
                        && Objects.equals(panel.getRow(), p.getRow())
                        && Objects.equals(panel.getCol(), p.getCol())) {
                    result.addErrorMessage("The combined values of Section, Row, and Column may not be duplicated.");
                    return result;
                }


        }


        return result;
    }

    public boolean comparingMateriels() {
        Panel panel = new Panel();
        if ((panel.getMateriel() == null || panel.getMateriel() != PanelMateriel.AMORPHOUS_SILICON) ||
                (panel.getMateriel() == null || panel.getMateriel() != PanelMateriel.COPPER_INDIUM) ||
                (panel.getMateriel() == null || panel.getMateriel() != PanelMateriel.MULTICRYSTALLINE_SILICON) ||
                (panel.getMateriel() == null || panel.getMateriel() != PanelMateriel.MONOCRYSTALLINE_SILICON) ||
                (panel.getMateriel() == null || panel.getMateriel() != PanelMateriel.CADMIUM_TELLURIDE)) {
            return true;
        } else {
            return false;
        }
    }

    public List<Panel> findAll() throws DataAcessException {
        return repository.findAll();
    }

    public PanelResult update(Panel panel) throws DataAcessException {
        PanelResult result = validateInput(panel);
        if (!result.isSuccess()) {
            return result;
        }
        Panel existing = repository.findById(panel.getID());
        if (existing == null) {
            result.addErrorMessage("panel Id" + panel.getID() + "not found");
            return result;
        }
        boolean success = repository.update(panel);
        if (!success) {
            result.addErrorMessage("Could not find panel id" + panel.getID());
        }
        return result;
    }

    public PanelResult delete(int panel) throws DataAcessException {
        PanelResult result = new PanelResult();
        Panel existing = repository.findById(panel);
        if (existing == null) {
            result.addErrorMessage("Could not find panel Id" + panel);
            return result;
        }
        if (!result.isSuccess()) {
            return result;
        }
        boolean success = repository.delete(panel);
        if (!success) {
            result.addErrorMessage("Could not find Id" + panel);
            return result;
        }
        return result;
    }

    //TODO Find BY Section
    public List<Panel> findBySection(Panel panel) throws DataAcessException {
        return repository.findBySection(panel);
    }
}
