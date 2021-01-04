package solar.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import solar.model.Panel;
import solar.model.PanelMateriel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PanelFileRepositoryTest {
    private static String SEED_PATH = "./data/SolarPanel-seed.csv";
    private static String TEST_PATH = "./data/SolarPanel-test.csv";
    private PanelFileRepository repository=new PanelFileRepository(TEST_PATH);

    @BeforeEach

        //happen every single time
    void setup() throws IOException {
        Files.copy(Paths.get(SEED_PATH), Paths.get(TEST_PATH),
                StandardCopyOption.REPLACE_EXISTING);

    }
    @Test
    void ShouldfindAllFivePanels() throws DataAcessException {
        List<Panel> actual=repository.findAll();
        assertNotNull(actual);
        assertEquals(5,actual.size());

    }
    @Test
    void ShouldFindExixtingPanel() throws DataAcessException {
        Panel sec2=repository.findById(4);
        assertNotNull(sec2);
        assertEquals(2018,sec2.year);


    }
    @Test
    void ShouldNotFindNonMissingPanel() throws DataAcessException {
        Panel sec400=repository.findById(10000);
        assertNull(sec400);
    }
    @Test
    void ShouldAddPanel() throws DataAcessException {
        Panel panel=new Panel();
        panel.setMateriel(PanelMateriel.AMORPHOUS_SILICON);
        panel.setRow(2);
        panel.setCol(4);
        panel.setSection("Test section");
        panel.setTracking(("no"));

        Panel actual=repository.add(panel);
        assertNotNull(actual);
        assertEquals(6,actual.getID());

    }
    @Test
    void ShouldUpdateExistingPanel() throws DataAcessException {
        Panel panel=new Panel();
        panel.setID(3);
        panel.setYear(2020);
        panel.setSection("sec5");
        panel.setTracking("yes");
        panel.setMateriel(PanelMateriel.AMORPHOUS_SILICON);
        Boolean success=repository.update(panel);
        assertTrue(success);

        Panel actual=repository.findById(3);
        assertNotNull(actual);

    }
    @Test
    void ShouldNotUpdateMissingPanel() throws DataAcessException {
        Panel panel=new Panel();
        panel.setID(10000);
        boolean success=repository.update(panel);
        assertFalse(success);

    }
    @Test
    void ShouldDeleteExisting() throws DataAcessException {
        Panel panel=new Panel();
        panel.setID(3);
        boolean actual=repository.delete(3);
        assertTrue(actual);
        Panel panelId3=repository.findById(3);
        assertNull(panelId3);
    }
    @Test
    void ShouldNotDeleteMissing() throws DataAcessException {
        Panel panel=new Panel();
        panel.setID(10000);
        boolean actual=repository.delete(1000);
        assertFalse(actual);
    }
}