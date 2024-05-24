package tampilan_ui.output;

public class HLine {
    private int width;   //(-)

    public HLine(int width){
        this.width = width;
    }

    public void draw(){
        System.out.println("+" + "=".repeat(width - 2) + "+");
    }

}

