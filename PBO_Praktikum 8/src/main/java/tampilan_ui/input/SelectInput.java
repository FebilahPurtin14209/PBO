package tampilan_ui.input;

import tampilan_ui.output.Label;

public class SelectInput {
    private String label;
    private String[] selection;
    private int width;
    private int value;
    private Input input;

    // Constructor Object SelectInput
    public SelectInput(String label, String[] selection, int width) {
        this.label = label;
        this.selection = selection;
        this.width = width;
        this.input = new Input("");
    }

    // Method to draw selectInput
    public void draw() {
        Label labelComponent = new Label(label, width); // "|"" di akhir label
        labelComponent.draw();

        for (int i = 0; i < selection.length; i++) {
            String formattedSelection = String.format("%-" + (width - 6) + "s", selection[i]);
            System.out.println("| [" + (i + 1) + "] " + formattedSelection + " |"); // "|" di akhir 
        }
        //input.draw();
        printChoice();
    }
    
    public void printChoice() {
        System.out.print("| Pilihan : ");
        value = input.getValueInt();
    }

    public int getValue() {
        return value;
    }
}
