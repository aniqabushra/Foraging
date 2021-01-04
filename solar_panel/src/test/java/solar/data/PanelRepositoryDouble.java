package solar.data;

import solar.model.Panel;
import solar.model.PanelMateriel;

import java.util.ArrayList;
import java.util.List;

public class PanelRepositoryDouble implements PanelRepository{
    //dummy data
    private ArrayList<Panel> panels =new ArrayList<>();
    public PanelRepositoryDouble(){
        Panel sections=new Panel();
        sections.setID(1);
        sections.setSection("test");
        sections.setRow(1);
        sections.setCol(2);
        sections.setMateriel(PanelMateriel.AMORPHOUS_SILICON);
        sections.setYear(2000);
        sections.setTracking("true");
        panels.add(sections);
        panels.add(new Panel(2,"test2",1,3,1985, PanelMateriel.COPPER_INDIUM,"yes"));



    }

    @Override
    public List<Panel> findAll() throws DataAcessException {
        return new ArrayList<>(panels);
    }

    @Override
    public Panel findById(int panelId) throws DataAcessException {
        for(Panel p: panels){
            if(p.getID()==panelId){
                return p;
            }
        }
        return null;
    }

    @Override
    public Panel add(Panel panel) throws DataAcessException {
        panels.add(panel);
        return panel;
    }

    @Override
    public boolean update(Panel panel) throws DataAcessException {
        return true;
    }

    @Override//TODO DELETE ONE
    public boolean delete(int panelid) throws DataAcessException {

        for(Panel p: panels){
            if(p.getID()==panelid){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Panel> findBySection(Panel panelSec) throws DataAcessException {
        return null;
    }
}
