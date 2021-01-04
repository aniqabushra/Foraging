package solar.data;

import solar.model.Panel;

import java.util.List;

public interface PanelRepository {
    /*
            o	List<Panel> findAll (?).   x
            o	 Panel add (Panel panel).   x
            o	String Serialize (Panel panel)  x
            o	Panel Deserialize (String panel)
            o	removeBySectionRowColumn`or ID ()
            o	update ()

         */
    List<Panel> findAll() throws DataAcessException;

    Panel findById(int panelId) throws DataAcessException;
    List<Panel> findBySection(Panel panelSec) throws DataAcessException;

    //for add the repository is going to take a panel and its going to generate the next id
    Panel add(Panel panel) throws DataAcessException;

    boolean update(Panel panel) throws DataAcessException;

    boolean delete(int panel) throws DataAcessException;

    default int getNextId(List<Panel> allEncounters) {
        int nextId = 0;
        for (Panel e : allEncounters) {
            nextId = Math.max(nextId, e.getID());
        }
        return nextId + 1;
    }
}
