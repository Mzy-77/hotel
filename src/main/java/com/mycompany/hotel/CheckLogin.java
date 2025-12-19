package com.mycompany.hotel;
import java.io.*;
import java.util.ArrayList;

public class CheckLogin {
    private ArrayList<Login> userPassList;
    // Use project-relative path that matches actual layout: src/main/data/...
    private final String path = "src/main/java/data/loginemp.txt";
    private final String AdminUserName = "admin";
    private final String AdminPassword = "admin";
    private String userName;
    private String password;
    public CheckLogin() {
    userPassList = new ArrayList<>();
    LoadUsers();
}

    public String getAdminPassword() {
        return AdminPassword;
    }
    public String getUsername() {
        return AdminUserName;
    }
    public String getPassword() {
        return password;
    }
    public String getUserName() {
        return userName;
    }

    public ArrayList<Login> getUserPassList() {
        return userPassList;

    }
    public void LoadUsers(){
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line ;
            while ((line = reader.readLine()) != null) {
                String[] part = line.split(";");
                if (part.length == 2) {
                    userPassList.add(new Login(part[0], part[1]));
                }
            }
        }
        catch (IOException e) {
        throw new RuntimeException(e);
        }
    }

    public void SaveUsers(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (Login login : userPassList) {
                writer.write(login.toString());
                writer.newLine();
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String LoginCheck(String username, String password){

        if(username.equals(AdminUserName)&&password.equals(AdminPassword)){
            return "admin";
        }
        if (userPassList==null){
            return "wrong";
        }
        else {
            for (Login login : userPassList ) {

                if(login.getUsername().equals(username) && login.getPassword().equals(password)){
                    return "user";
                }
            }
        }
        return "wrong";

    }
    public boolean addUser(String username, String password){
        for (Login login : userPassList) {
            if(login.getUsername().equals(username) && login.getPassword().equals(password)){
                return false;
            }
        }
        userPassList.add(new Login(username,password));
        SaveUsers();
        return true;
    }

}