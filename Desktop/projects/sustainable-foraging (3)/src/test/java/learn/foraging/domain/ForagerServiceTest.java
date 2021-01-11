package learn.foraging.domain;

import learn.foraging.data.DataException;
import learn.foraging.data.ForagerRepositoryDouble;
import learn.foraging.models.Forager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ForagerServiceTest {
    //TODO Created A new Class for Forager test.
    ForagerService service=new ForagerService((new ForagerRepositoryDouble()));
    @Test
    void shouldNotAddNullForager() throws DataException {
        Result result=service.addForager(null);
        assertFalse(result.isSuccess());

    }
    @Test
    void shouldNotAddEmptyFirstName() throws DataException {
        //"id,firstName,LastName,State"
        Forager forager = new Forager("1","","Bushra","MD");
        Result result=service.addForager(forager);
        assertFalse(result.isSuccess());
        //assertEquals(1, result.getMessages().size());
    }
    @Test
    void shouldAddForager() throws DataException {
        Forager forager = new Forager("1-4-6-7-8","Aniqa","Bushra","MD");
        Result expected = new Result();
        Result result = service.addForager(forager);
        assertTrue(result.isSuccess());
    }
    @Test
    void ShouldNotAddDuplicate_fname_lname_country() throws DataException {
        //
        Forager forager = new Forager("kl000023","Jilly","Sisse","GA");
        Response actual=service.addForager(forager);
        assertFalse(actual.isSuccess());
        assertEquals("The combination of first name, last name, and state cannot be duplicated.",actual.getErrorMessages().get(0));
    }
}
