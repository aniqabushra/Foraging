package solar.ui;

import solar.data.DataAcessException;
import solar.data.PanelFileRepository;
import solar.data.PanelRepository;
import solar.domain.PanelService;

public class App {
    public static void main(String[] args) throws DataAcessException {
        View view=new View();
        PanelFileRepository repository=new PanelFileRepository("./data/SolarPanel.csv");
        PanelService service=new PanelService(repository);
        Controller controller=new Controller(service,view);
        controller.run();
    }

}
