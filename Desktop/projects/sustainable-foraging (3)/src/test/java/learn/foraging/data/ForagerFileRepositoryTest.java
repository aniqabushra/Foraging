package learn.foraging.data;

import learn.foraging.models.Forager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ForagerFileRepositoryTest {
    //TODO New Code
    static final String SEED_FILE_PATH = "./data/foragers.csv";
    static final String TEST_FILE_PATH = "./data/forager-test.csv";

    ForagerFileRepository repository = new ForagerFileRepository(TEST_FILE_PATH);

    @BeforeEach
    void setupTest() throws IOException {
        Path seedPath = Paths.get(SEED_FILE_PATH);
        Path testPath = Paths.get(TEST_FILE_PATH);

        Files.copy(seedPath, testPath, StandardCopyOption.REPLACE_EXISTING);
    }
            //----//
    @Test
    void shouldFindAll() {
        ForagerFileRepository repo = new ForagerFileRepository(SEED_FILE_PATH);
        List<Forager> all = repo.findAll();
        assertEquals(1001, all.size());
    }
    //TODO New test
    @Test
    void shouldAddForager() throws DataException {
        Forager forager=new Forager();
        forager.setFirstName("testFirstName");
        forager.setLastName("testLastName");
        forager.setState("WI");
        Forager actual=repository.addForager(forager);
        assertNotNull(actual);
    }
}