package learn.foraging.domain;

import learn.foraging.data.DataException;
import learn.foraging.data.ForagerRepository;
import learn.foraging.models.Forager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
@Service
public class ForagerService {

    private final ForagerRepository repository;

    public ForagerService(ForagerRepository repository) {
        this.repository = repository;
    }

    public List<Forager> findByState(String stateAbbr) {
        return repository.findByState(stateAbbr);
    }

    public List<Forager> findByLastName(String prefix) {
        return repository.findAll().stream()
                .filter(i -> i.getLastName().startsWith(prefix))
                .collect(Collectors.toList());
    }
                //new code
    public Result addForager(Forager forager) throws DataException {
        //two types of validation input validation or domain validation
        //input validation
        Result result = validateInputForager(forager);
        if (!result.isSuccess()) {
            return result;
        }
        Forager forager1 = repository.addForager(forager);
        result.setPayload(forager1);
        return result;
    }

    //Todo new code
    public List<Forager> findAll() throws DataException {
        return repository.findAll();
    }

    //Todo new code
    private Result validateInputForager(Forager forager) throws DataException {
        Result result = new Result();
        if (forager == null) {
            result.addErrorMessage("Forager cannot be null");
            return result;
        }
        if (forager.getLastName() == null || forager.getLastName().trim().length() == 0) {
            result.addErrorMessage("Last Name is required");
        }
        if (forager.getFirstName() == null || forager.getFirstName().trim().length() == 0) {
            result.addErrorMessage("First Name is required");
        }
        if (forager.getState() == null || forager.getState().trim().length() == 0) {
            result.addErrorMessage("State is required");
        }
        List<Forager> foragers = findAll();
        for (Forager f : foragers) {
            if (f.getId() != forager.getId() && Objects.equals(forager.getFirstName(), f.getFirstName())
                    && Objects.equals(forager.getLastName(), f.getLastName())
                    && Objects.equals(forager.getState(), f.getState())) {
                result.addErrorMessage("The combination of first name, last name, and state cannot be duplicated.");
                return result;
            }
        }
        return result;
    }
}
