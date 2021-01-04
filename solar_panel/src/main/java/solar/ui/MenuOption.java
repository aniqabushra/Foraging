package solar.ui;

public enum MenuOption {
    EXIT("Exit"),
    FIND_PANEL_BY_SECTION("Find panel by section"),
    ADD_A_PANEL("Add a panel"),
    UPDATE_A_PANEL("Update a panel"),
    REMOVE_A_PANEL("Remove a panel");
    private final String title;

    MenuOption(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
