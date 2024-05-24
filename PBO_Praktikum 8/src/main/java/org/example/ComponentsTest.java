package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import tampilan_ui.output.HLine;
import tampilan_ui.output.Label;
import tampilan_ui.output.Space;
import tampilan_ui.input.Input;
import tampilan_ui.input.SelectInput;


public class ComponentsTest {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

        new HLine(50).draw();
        new Space(50).draw();
        new Label("Uji coba komponen yang dibuat", 50).draw();
        new Space(50).draw();
        new HLine(50).draw();
        new Space(50).draw();

        Input input = new Input("Masukkan nama");
        Input input2 = new Input("Usia anda");
        input.draw();
        String nama = input.getValue();
        input2.draw();
        int usia = input2.getValueInt();

        new Space(50).draw();
        String [] pilihan = {"Pilihan 1", "Pilihan 2", "Pilihan 3", "Pilihan 4"};
        SelectInput pilSelect = new SelectInput("Inputkan pilihan anda:", pilihan, 50);
        pilSelect.draw();
        int value = pilSelect.getValue();

        new Space(50).draw();
        new HLine(50).draw();
        new Space(50).draw();

        new Label("Nama anda: "+ nama, 50).draw();
        new Label("Usia anda: "+ usia, 50).draw();
        new Label("Anda memilih menu: "+ value, 50).draw();

        new Space(50).draw();
        new HLine(50).draw();
    }
}