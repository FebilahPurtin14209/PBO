package tampilan_ui.output;

public class Space {
    private final int width;

    public Space(int width){
        this.width = width;
    }

    public void draw(){
        System.out.println("|" + " ".repeat(width - 2) + "|");
    }


}

