package solar.domain;

import org.junit.jupiter.api.Test;
import solar.data.DataAcessException;
import solar.data.PanelRepositoryDouble;
import solar.model.Panel;
import solar.model.PanelMateriel;

import static org.junit.jupiter.api.Assertions.*;

class PanelServiceTest {
    PanelService service=new PanelService(new PanelRepositoryDouble());

    @Test
    void shouldAddPanel() throws DataAcessException {
        Panel panel = new Panel(0,"test Section",1,2,2000, PanelMateriel.AMORPHOUS_SILICON,"no");
        PanelResult expected = new PanelResult();
        PanelResult result = service.add(panel);
        assertTrue(result.isSuccess());
    }
    @Test
    void shouldNotAddNullPanel() throws DataAcessException {
        PanelResult result=service.add(null);

        assertFalse(result.isSuccess());

    }
    @Test
    void shouldNotAddEmptySections() throws DataAcessException {
        Panel panel = new Panel(0,"" ,1,2,2000, PanelMateriel.AMORPHOUS_SILICON,"yes");
        PanelResult result=service.add(panel);
        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());

    }
    @Test
    void shouldNotAddEmptyMateriel() throws DataAcessException {
        Panel panel = new Panel(3,"test3" ,1,5,2000, null,"yes");
        PanelResult result=service.add(panel);
        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());

    }
    @Test
    void shouldNotAddEmptyIsTracking() throws DataAcessException {
        Panel panel = new Panel(5,"test4" ,1,7,2000, PanelMateriel.AMORPHOUS_SILICON,null);
        PanelResult result=service.add(panel);
        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());

    }
   /* @Test
    void ShouldNotAddDuplicate_row_col_sec() throws DataAcessException {
        //1, sec1,1,1,2010,MULTICRYSTALLINE_SILICON,false
        Panel panel = new Panel(3,"test" ,1,2,2010, SolarMateriel.MULTICRYSTALLINE_SILICON,false);
        PanelResult expected = new PanelResult();
        PanelResult actual = service.add(panel);
        assertEquals(expected.getMessages().size()==1, actual);
    }
    //how to check for duplicate
    private PanelResult makeResult(String message) {
        PanelResult result = new PanelResult();
        result.addErrorMessage(message);
        return result;
    }*/
    @Test
    void ShouldUpdateExixting() throws DataAcessException {
        PanelResult result=service.update(new Panel(1,"Updated sec1" ,2,1,2010, PanelMateriel.MULTICRYSTALLINE_SILICON,"yes"));
        assertTrue(result.isSuccess());


    }
    @Test
    void ShouldNotUpdateExixting() throws DataAcessException {
        PanelResult result=service.update(new Panel(1000,"Updated sec1" ,2,1,2010, PanelMateriel.MULTICRYSTALLINE_SILICON,"no"));
        assertFalse(result.isSuccess());

    }
    @Test
    void ShouldDeleteExixting() throws DataAcessException {
        PanelResult result=service.delete(1);
        assertTrue(result.isSuccess());
    }
    @Test
    void ShouldNotDeleteMissing() throws DataAcessException {
        Panel p =new Panel();
        PanelResult result=service.delete(1000);
        assertFalse(result.isSuccess());
    }


}