package solar.domain;

import solar.model.Panel;

import java.util.ArrayList;
import java.util.List;

public class PanelResult {
    //it has a status information and the things we have to return
    private ArrayList<String> messages = new ArrayList<>();
    //if operation is successfull then we wanna return orbiter
    private Panel payload;

    public void addErrorMessage(String message) {
        messages.add(message);
    }

    public boolean isSuccess() {
        return messages.size() == 0;
    }

    public Panel getPayload() {
        return payload;
    }

    public void setPayload(Panel payload) {
        this.payload = payload;
    }

    public List<String> getMessages() {
        return new ArrayList<>(messages);
    }
}
