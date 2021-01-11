package learn.foraging.data;

import learn.foraging.models.Forager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Repository
public class ForagerFileRepository implements ForagerRepository {

    private final String filePath;
    //TODO New Code
    private static final String HeaderForager = "id,firstName,LastName,State";

    public ForagerFileRepository(@Value("./data/foragers.csv")String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Forager> findAll() {
        ArrayList<Forager> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            reader.readLine(); // read header

            for (String line = reader.readLine(); line != null; line = reader.readLine()) {

                String[] fields = line.split(",", -1);
                if (fields.length == 4) {
                    result.add(deserialize(fields));
                }
            }
        } catch (IOException ex) {
            // don't throw on read
        }
        return result;
    }

    @Override
    public Forager findById(String id) {
        return findAll().stream()
                .filter(i -> i.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Forager> findByState(String stateAbbr) {
        return findAll().stream()
                .filter(i -> i.getState().equalsIgnoreCase(stateAbbr))
                .collect(Collectors.toList());
    }
   //    TODO New Code
    @Override
    public Forager addForager(Forager forager) throws DataException {
        if (forager == null) {
            return null;
        }
        List<Forager> all = findAll();
        forager.setId(java.util.UUID.randomUUID().toString());
        all.add(forager);
        writeAll(all);
        return forager;
    }

    private Forager deserialize(String[] fields) {
        Forager result = new Forager();
        result.setId(fields[0]);
        result.setFirstName(fields[1]);
        result.setLastName(fields[2]);
        result.setState(fields[3]);
        return result;
    }
    //TODO New Code
    protected void writeAll(List<Forager> foragers) throws DataException {
        try (PrintWriter writer = new PrintWriter(filePath)) {

            writer.println(HeaderForager);

            if (foragers == null) {
                return;
            }

            for (Forager forager : foragers) {
                writer.println(serialize(forager));
            }

        } catch (FileNotFoundException ex) {
            throw new DataException(ex);
        }
    }
    //TODO New Code
    private String serialize(Forager forager) {
        return String.format("%s,%s,%s,%s",
                forager.getId(),
                forager.getFirstName(),
                forager.getLastName(),
                forager.getState());
    }
}