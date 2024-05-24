import tampilan_ui.page.MainPage;
import data.DataPassword;

public class MainApp {
    public static void main(String[] args) throws Exception {
        DataPassword.loadCSVData();
        new MainPage("Password Vault Application", 50).draw();
    }
}
