package tampilan_ui.page;

//import java.util.Scanner;
import tampilan_ui.input.Input;
import tampilan_ui.input.SelectInput;
import tampilan_ui.output.HLine;
import tampilan_ui.output.Space;
import tampilan_ui.output.Label;
import password.PasswordStore;
//import password.Encryptor;
import data.DataPassword;

public class InputPage {
    String judul, username, password = new String();
    int width, kategori;
    
    private final HLine hline;
    private final Space space;
    private final Label label;
    
    public InputPage(String label, int width){
        this.width = width;
        
        this.hline = new HLine(width);
        this.space = new Space(width);
        this.label = new Label(label, width);
    }
    
    public void draw() throws Exception{
        drawHeader();
        drawContent();
        drawFooter();
    }
    
    public void drawHeader() throws Exception{
        this.hline.draw();
        this.space.draw();
        this.label.draw();
        this.space.draw();
        this.hline.draw();
    }
    
    public void drawContent() throws Exception{
        String[] pilihan = {"Belum terkategori", "Aplikasi web", "Aplikasi mobile", "Akun lainnya"};
        SelectInput pilInput = new SelectInput("Kategori: ", pilihan, width);
        
        Input inputusername = new Input("Username");
        Input inputpassword = new Input("Password");
        Input judul_login = new Input("Judul Password");
        
        this.space.draw();
        new Label("Input password baru", width).draw();
        
        judul_login.draw();
        judul = judul_login.getValue();
        
        inputusername.draw();
        username = inputusername.getValue();
        
        inputpassword.draw();
        password = inputpassword.getValue();
        
        pilInput.draw();
        kategori = pilInput.getValue()-1;
        
        System.out.println("----- -----");
        
        PasswordStore save = new PasswordStore(judul, username, password, kategori);
        
        new DataPassword().passData.add(save);
        
        DataPassword.saveCSVData();
        
        System.out.println("Password berhasil dibuat!!!");
    }
    
    public void drawFooter() throws Exception{
        new Space(width).draw();
        new HLine(width).draw();
    }
}
