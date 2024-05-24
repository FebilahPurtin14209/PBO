package password;

import java.security.NoSuchAlgorithmException;


public class PasswordStore {
    public String name, username;
    private String password, hashkey;
    private double score;
    private int category;
    
    public static final int UNCATEGORIZED = 0;
    public static final int CAT_WEBAPP = 1;
    public static final int CAT_MOBILEAPP = 2;
    public static final int CAT_OTHER = 3;
    
    public PasswordStore(String name, String username, String plainPass) throws NoSuchAlgorithmException, Exception{
        hashkey = Encryptor.generateKey();
        this.name = name;
        this.username = username;
        setPassword(plainPass);
        setCategory(UNCATEGORIZED);
    }

    public PasswordStore(String name, String username, String plainPass, int category) throws NoSuchAlgorithmException, Exception{
        hashkey = Encryptor.generateKey();
        this.name = name;
        this.username = username;
        setPassword(plainPass);
        setCategory(category);
    }
    
    public void setPassword(String plainPass) throws Exception{
        this.password = Encryptor.encrypt(plainPass, hashkey);
        calculateScore(password);
    }
    
    public String getHashKey(){
        return this.hashkey;
    }
    
    public String getEncPassword(){
        return this.password;
    }
    
    public double getScore(){
        return this.score;
    }
    
    public String getPassword() throws Exception{
        return Encryptor.decrypt(password, hashkey);
    }

    public void setCategory(int category){
        if (category >= 0 && category <= 3) {
            this.category = category;
        } else {
            this.category = 0;
        }
    }

    public String getCategory(){
        return switch (category) {
            case UNCATEGORIZED -> "Belum terkategori";
            case CAT_WEBAPP -> "Aplikasi web";
            case CAT_MOBILEAPP -> "Aplikasi mobile";
            case CAT_OTHER -> "Akun lainnya";
            default -> "Kategori tidak valid";
        };
    }
    
    public int getCategoryCode() {
        return this.category;
    }
    
    @Override
    public String toString() {
        return "Username: " + username + "\n" +
               "Password (encrypted): " + password + "\n" +
               "Hashkey: " + hashkey + "\n" +
               "Kategori: " + category + "\n" +
               "Score: " + score;
    };
    
    
    private void calculateScore(String plainPass){
        if(plainPass.length()>15){
            score = 10;
        } else {
            score = (plainPass.length() / 15)*10;
        }
    }
}
