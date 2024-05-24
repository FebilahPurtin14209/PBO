package tampilan_ui.input;

import java.util.Scanner;

public class Input {
    private String label;
    private Scanner input;
    private String value;

    public Input(String label) {
        this.label = label;
        this.input = new Scanner(System.in);
    }

    public void draw() {
        System.out.print("| " + label + " : ");
    }

    public String getValue() {
        value = input.nextLine();
        return value;
    }

    public int getValueInt() {
        value = input.nextLine();
        return Integer.parseInt(value);
    }

    public double getValueDouble() {
        value = input.nextLine();
        return Double.parseDouble(value);
    }
}
