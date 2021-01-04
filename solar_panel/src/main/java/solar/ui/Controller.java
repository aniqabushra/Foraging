package solar.ui;

import solar.data.DataAcessException;
import solar.domain.PanelResult;
import solar.domain.PanelService;
import solar.model.Panel;

import java.util.List;

public class Controller {
    private final PanelService service;

    private final View view;

    public Controller(PanelService service, View view) {
        this.service = service;
        this.view = view;
    }

    public void run() throws DataAcessException {
        //TODO loop through menu options;
        MenuOption option;
        do {
            option = view.displayMenuOptionsAndSelect();
            System.out.println(option.getTitle());
            switch (option) {
                case EXIT:
                    System.out.println("Good Bye :)");
                    break;
                case FIND_PANEL_BY_SECTION:
                    displayBySections();
                    break;
                case ADD_A_PANEL:
                    addPanel();
                    break;
                case UPDATE_A_PANEL:
                    updatePanel();
                    break;
                case REMOVE_A_PANEL:
                    removePanel();
                    break;
            }


        } while (option != MenuOption.EXIT);


    }

    private void displayBySections() throws DataAcessException {
        view.printHeader(MenuOption.FIND_PANEL_BY_SECTION.getTitle());
        List<Panel> panel = service.findAll();
        Panel secName = view.readPanelSection(panel);
    }

    private void addPanel() throws DataAcessException {
        view.printHeader(MenuOption.ADD_A_PANEL.getTitle());
        Panel panel = view.AddPanel();
        PanelResult result = service.add(panel);
        view.printResult(result, "Panel %s-%s-%s is added.%n");


    }

    private void updatePanel() throws DataAcessException {
        view.printHeader(MenuOption.UPDATE_A_PANEL.getTitle());
        List<Panel> panels = service.findAll();
        Panel panel = view.selectId(panels);
        //defensing code
        if (panel != null) {
            Panel updatedPanel = view.updatePanel(panel);
            PanelResult result = service.update(updatedPanel);
            result.setPayload(updatedPanel);
            view.printResult(result, "Panel %s-%s-%s updated.%n");
        }

    }

    private void removePanel() throws DataAcessException {
        view.printHeader(MenuOption.REMOVE_A_PANEL.getTitle());
        List<Panel> panels = service.findAll();
        Panel panel = view.selectId(panels);
        if (panel != null) {
            PanelResult result = service.delete(panel.getID());
            result.setPayload(panel);
            view.printResult(result, "Panel %s-%s-%s is removed.%n");
        }
    }


}
