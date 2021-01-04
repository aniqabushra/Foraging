package solar.ui;

import solar.domain.PanelResult;
import solar.model.Panel;
import solar.model.PanelMateriel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class View {
    private final Scanner console = new Scanner(System.in);

    public MenuOption displayMenuOptionsAndSelect() {
        printHeader("Main Menu");
        MenuOption[] values = MenuOption.values();
        for (int index = 0; index < values.length; index++) {
            System.out.printf("%s. %s", index, values[index].getTitle());
            System.out.println();
        }
        int selection = readInt("Select[0-4]: ", 0, 4);
        return values[selection];
    }

    //Todo print a header
    public void printHeader(String message) {
        printMessage(message);
        System.out.println("=".repeat(message.length()));
    }

    public void printMessage(String message) {
        System.out.println();
        System.out.println(message);
    }

    //Todo READ METHODS
    private String readString(String prompt) {
        System.out.print(prompt);
        return console.nextLine();
    }

    private String readRequiredString(String prompt) {
        String result = null;
        do {
            result = readString(prompt).trim();
            if (result.length() == 0) {
                System.out.println("value is required");
            }
        } while (result.length() == 0);
        return result;
    }

    private int readInt(String prompt) {
        int result = 0;
        boolean isValid = false;
        do {
            String value = readRequiredString(prompt);
            //it can throw NumberFormatException
            try {
                result = Integer.parseInt(value);
                isValid = true;
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a number");
            }

        } while (!isValid);
        return result;
    }

    private int readInt(String prompt, int min, int max) {
        int result = 0;
        do {
            result = readInt(prompt);
            if (result < min || result > max) {
                System.out.printf("Please Select from %s-%s%n", min, max);
            }
        } while (result < min || result > max);
        return result;
    }

    public Panel readPanelSection(List<Panel> panels) {
        printPanels(panels);
        boolean inputValidation = false;
        do {
            System.out.print("Section Name: ");
            String secInput = console.nextLine();
            System.out.println();
            System.out.printf("Panels in %s%n", secInput);

            if (panels.size() == 0) {
                System.out.println("No Panel Found");

            } else {
                System.out.println("Row,Col,Year,Materiel,IsTracking");
                for (Panel p : panels) {
                    if (p.getSection().equalsIgnoreCase(secInput)) {
                        System.out.printf("%s, %s, %s, %s, %s %n", p.getRow(), p.getCol(), p.getYear(), p.getMateriel(), p.getTracking());
                        inputValidation = true;
                    } /*else if (!p.getSection().equalsIgnoreCase(secInput)) {
                        System.out.println("No section is found");
                        break;
                    }*/
                }
                System.out.println("No section is found");

            }
        } while (!inputValidation);


        return null;
    }

    public PanelMateriel readMateriel(String prompt) {
        int index = 1;
        for (PanelMateriel materiel : PanelMateriel.values()) {
            System.out.printf("%s. %s%n", index++, materiel);
        }
        index--;
        String message = String.format("Select Panel Materiel[1-%s]: ", index);
        //String message1=readRequiredString(message);
        return PanelMateriel.values()[readInt(message, 1, index) - 1];

    }

    public int readYear(String prompt) {
        int year = 0;
        Panel panel = new Panel();
        int currentYear = LocalDate.now().getYear();
        if (panel.getYear() > currentYear) {
            System.out.println("Year Installed must be in the past");
        }
        return readInt("year", 0, currentYear);
    }

    public String readTracking() {
        String result;
        boolean isValid = false;
        do {
            result = readRequiredString("Tracking.yes/no");
            if (result.equalsIgnoreCase("yes") || result.equalsIgnoreCase("no")) {
                break;
            } else {
                System.out.println("Tracking Should be yes or no");
            }
        } while (!isValid);
        return result;
    }

    public Panel AddPanel() {
        Panel panel = new Panel();
        String name = readRequiredString("Section Name");
        panel.setSection(name);
        panel.setRow(readInt("Row: ", 0, 250));
        panel.setCol(readInt("Col: ", 0, 250));
        panel.setYear(readYear("Year: "));
        panel.setMateriel(readMateriel("Materiel: "));
        panel.setTracking(readTracking());
        return panel;
    }

    public void printResult(PanelResult result, String messageTemplate) {
        if (result.isSuccess()) {
            if (result.getPayload() != null) {
                System.out.println("[Success]");
                System.out.printf(messageTemplate, result.getPayload().getSection(), result.getPayload().getRow(), result.getPayload().getCol());
            }
        } else {
            printHeader("Errors");
            for (String msg : result.getMessages()) {
                System.out.printf("- %s%n", msg);
            }
        }
    }

    //TODO will see if I wanna keep it.
    public void printPanels(List<Panel> panels) {
        System.out.println();
        if (panels == null || panels.size() == 0) {
            System.out.println();
            System.out.println("No Panels found.");
        } else {
            for (Panel p : panels) {
                printPanel(p);
            }
        }
    }

    private void printPanel(Panel p) {
        System.out.printf("%s.Section:%s, Row: %s, Col: %s, Year: %s, Materiel: %s, IsTracking: %s%n",
                p.getID(),
                p.getSection(),
                p.getRow(),
                p.getCol(),
                p.getYear(),
                p.getMateriel(),
                p.getTracking());
    }

    //Todo refactor this
    public Panel updatePanel(Panel panel) {
        printPanel(panel);
        updateSection(panel);
        updateCol(panel);
        updaterow(panel);
        updateMateriel(panel);
        updateTracking(panel);
        return panel;
    }

    private void updaterow(Panel panel) {
        int row = readInt("Row (" + panel.getRow() + "):");
        if (row > 0 || row <= 250) {
            panel.setRow(row);
        }
    }

    private void updateCol(Panel panel) {
        int col = readInt("Col ( " + panel.getCol() + "):");
        if (col > 0 || col <= 250) {
            panel.setCol(col);
        }
    }

    private void updateSection(Panel panel) {
        String section = readString("Section ( " + panel.getSection() + "):");
        if (section.trim().length() > 0) {
            panel.setSection(section);
        } else if (section.length() == 0) {
            panel.setSection(panel.getSection());
        }
    }

    private void updateMateriel(Panel panel) {
        PanelMateriel materiel = readMateriel("Materiel ( " + panel.getMateriel() + "):");
        if (materiel != null) {
            panel.setMateriel(materiel);
        }
    }

    private void updateTracking(Panel panel) {
        String tracking = readString("Tracking ( " + panel.getTracking() + "):");
        if (tracking.trim().length() > 0) {
            panel.setSection(tracking);
        }

    }

    public Panel deletePanel(Panel panel) {
        return null;
    }

    public Panel selectId(List<Panel> panels) {
        printPanels(panels);
        //int id=readInt("enterId");
        String sec = readString("Enter section: ");
        int row = readInt("Enter row: ", 0, 250);
        int col = readInt("Enter col: ", 0, 250);
        Panel selectPanel = null;
        for (Panel panel : panels) {
            if (panel.getCol() == col && panel.getRow() == row && panel.getSection().equalsIgnoreCase(sec)) {
                selectPanel = panel;
            }
        }
        if (selectPanel == null) {
            printMessage("Panel not found");
        }
        return selectPanel; //return null if we didn't find a panel

    }
}
