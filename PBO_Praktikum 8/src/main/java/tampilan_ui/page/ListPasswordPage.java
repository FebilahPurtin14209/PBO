package tampilan_ui.page;

//import java.util.ArrayList;
import tampilan_ui.output.Space;
import tampilan_ui.output.HLine;
import tampilan_ui.output.Label;
import password.PasswordStore;
//import password.Encryptor;
import data.DataPassword;

public class ListPasswordPage {
    String text;
    int width;
    
    public ListPasswordPage(String text, int width){
        this.text = text;
        this.width = width;
    }
    
    public void draw() throws Exception{
        drawHeader();
        drawContent();
        drawFooter();
    }
    
    public void drawHeader() throws Exception{
        new HLine(width).draw();
        new Space(width).draw();
        new Label(text, width).draw();
        new Space(width).draw();
        new HLine(width).draw();
    }
    
    public void drawContent() throws Exception{
        new Space(width).draw();
        
        new DataPassword();
        String word = "Terdapat " + DataPassword.passData.size() + " tersimpan";
        new Label(word, width).draw();
        
        new Label("----- ----- -----", this.width).draw();
        
        for(PasswordStore ps : DataPassword.passData){
            new Label(String.format("| %-15s | %-15s | %-15s |", ps.name, ps.username, ps.getCategory()), this.width).draw();
        }
    }
    
    public void drawFooter() throws Exception{
        new Space(this.width).draw();
        new HLine(this.width).draw();
        
        new MainPage("Password Vault Application", this.width).draw(); 
    }
}
