package solar.data;

import solar.model.Panel;
import solar.model.PanelMateriel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PanelFileRepository implements PanelRepository {
    private String filePath;
    private String Delimeter = ",";

    //its gonna take a path to a file repository.
    public PanelFileRepository(String filePath) {
        this.filePath = filePath;
    }

    /*
        o	List<Panel> findAll (?).   x
        o	 Panel add (Panel panel).   x
        o	String Serialize (Panel panel)  x
        o	Panel Deserialize (String panel)
        o	removeBySectionRowColumn`or ID ()
        o	update ()

     */
    @Override
    public List<Panel> findAll() throws DataAcessException {
        //List of panel is an interface of Arraylist of panels(created container)
        ArrayList<Panel> result = new ArrayList<>();
        //we need some io classes to read the file
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            //get rid of header in file
            reader.readLine();
            //loop to check each line
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] fields = line.split(Delimeter);
                //to check if it splits correctly
                if (fields.length == 7) {
                    Panel panel = new Panel();
                    panel.setID(Integer.parseInt(fields[0]));
                    panel.setSection(fields[1]);
                    panel.setRow(Integer.parseInt(fields[2]));
                    panel.setCol(Integer.parseInt(fields[3]));
                    panel.setYear(Integer.parseInt(fields[4]));
                    panel.setMateriel(PanelMateriel.valueOf(fields[5]));
                    panel.setTracking((fields[6]));
                    result.add(panel);
                }
            }

        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            throw new DataAcessException(e.getMessage(), e);
        }
        return result;

    }

    @Override
    public Panel findById(int panelId) throws DataAcessException {
        for (Panel panel : findAll()) {
            if (panel.getID() == panelId) {
                return panel;
            }
        }
        return null; //if don't find an id return null
    }

    @Override
    public List<Panel> findBySection(Panel panelSec) throws DataAcessException {
        ArrayList<Panel> result = new ArrayList<>();
        for (Panel panel : findAll()) {
            if (panel.getCol() == panelSec.getCol() || panel.getRow() == panelSec.getRow() || panel.getSection().equalsIgnoreCase(panelSec.getSection().trim())) {
                result.add(panel);
            }
        }
        return result;
    }

    //for add the repository is going to take a panel and its going to generate the next id
    @Override
    public Panel add(Panel panel) throws DataAcessException {
        //grab all panels
        List<Panel> all = findAll();
        //create next id
        panel.setID(getNextId(all));
        //add new panel to all
        all.add(panel);
        //save
        writeAll(all); //write it to the file
        return panel;

    }

    @Override
    public boolean update(Panel panel) throws DataAcessException {
        //looking for existing panel if find it update it and save it
        List<Panel> all = findAll();
        for (int index = 0; index < all.size(); index++) {
            //if this panel is
            if (all.get(index).getID() == panel.getID()) {
                all.set(index, panel);
                writeAll(all);
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean delete(int panelid) throws DataAcessException {
        //works similar way as an update we gonna just remove it
        List<Panel> all = findAll();
        for (int index = 0; index < all.size(); index++) {
            //if this panel is
            if (all.get(index).getID() == panelid) {
                all.remove(index); // if find the id remove it
                writeAll(all);
                return true;
            }
        }
        return false;

    }

    //Todo will see if i need this?
    public boolean deletebySection(Panel panel) throws DataAcessException {
        List<Panel> all = findAll();
        for (Panel p : all) {
            //if(p.getSection())
        }
        return true;
    }

    private void writeAll(List<Panel> panels) throws DataAcessException {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.println("Id,section,row,col,year,materiel,IsTracking."); //print header
            for (Panel p : panels) {
                writer.println(Serialize(p));
            }
        } catch (IOException ex) {
            //throw new DataAccessException(ex.getMessage(), ex);
            throw new DataAcessException(ex.getMessage(), ex);
        }


    }

    private String Serialize(Panel panel) {
        return String.format("%s, %s,%s,%s,%s,%s,%s",
                panel.getID(),
                panel.getSection(),
                panel.getRow(),
                panel.getCol(),
                panel.getYear(),
                panel.getMateriel(),
                panel.getTracking());

    }
}
