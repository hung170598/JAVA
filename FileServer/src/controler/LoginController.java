/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Account;
import view.ServerView;

/**
 *
 * @author hung1
 */
public class LoginController {
    private ServerView view;
    private Connection con;
    private ServerSocket myServer;
    private Socket clientSocket;
    private int serverPort = 1111;


    public LoginController(ServerView view) {
        this.view = view;
        openServer(serverPort);
        view.showLog("TCP server is running ...");
        while(true){
            try{
                clientSocket = myServer.accept();
                ObjectInputStream ois = new 
                    ObjectInputStream(clientSocket.getInputStream());
                DataOutputStream dos = new
                        DataOutputStream(clientSocket.getOutputStream());
                Account account = (Account)ois.readObject();
                int check = checkLogin(account);
                dos.writeInt(check);
            }
            catch(ClassNotFoundException| IOException e){
                view.showLog(e.toString());
            }
        }
        
    }
    public void openServer(int port){
        try{
            this.myServer = new ServerSocket(port);
        }
        catch(IOException e){
            view.showLog(e.toString());
        }
    }
    public int checkLogin(Account account){
        try{
            con = ConnectDB.getConnectionDB();
            String query = "select password from account where "
                    + "username = '" + account.getUsername() + "'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            if(!rs.next()) return 0;
            else{
                String password = rs.getString(1);
                if(password.equals(account.getPassword())) return 1;
                return 2;
            }
        }
        catch(ClassNotFoundException | SQLException e){
            view.showLog(e.toString());
        }
        return -1;
    }
    
    public static void main(String[] args) {
        ServerView serverView = new ServerView();
        LoginController control = new LoginController(serverView);
        serverView.setVisible(true);
    }
    
}

