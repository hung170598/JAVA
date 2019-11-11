/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;
import model.Account;
import view.LoginView;

/**
 *
 * @author hung1
 */
public class LoginControler {
    private LoginView loginView;
    private String serverHost = "localhost";
    private int serverPort = 1111;

    public LoginControler(LoginView loginView) {
        this.loginView = loginView;
        this.loginView.addLoginListener(new LoginListener());
    }

    class LoginListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Account account = loginView.getAccount();
            if(account.getUsername().equals("")){
                JOptionPane.showMessageDialog(loginView, 
                        "Điền username!");
                return;
            }
            if(account.getPassword().equals("")){
                JOptionPane.showMessageDialog(loginView, 
                        "Điền password!");
                return;
            }
            try{
                Socket mySocket = new Socket(serverHost, serverPort);
                ObjectOutputStream oos = new 
                    ObjectOutputStream(mySocket.getOutputStream());
                DataInputStream dis = new
                    DataInputStream(mySocket.getInputStream());
                oos.writeObject(account);
                int x = dis.readInt();
                switch(x){
                    case -1:{
                        JOptionPane.showMessageDialog(loginView, 
                                "Connect Eror!");
                        break;
                    }
                    case 0:{
                        JOptionPane.showMessageDialog(loginView, 
                                "Username không tồn tại!");
                        break;
                    }
                    case 2:{
                        JOptionPane.showMessageDialog(loginView, 
                                "Password sai, hãy nhập lại!");
                        break;
                    }
                    case 1:{
                        JOptionPane.showMessageDialog(loginView, 
                                "OK!");
                        break;
                    }
                }
            }
            catch(IOException ex){
                JOptionPane.showMessageDialog(loginView, 
                        ex);
            }
        }
    }
    
    public static void run() {
        LoginView loginView = new LoginView();
        LoginControler control = new LoginControler(loginView);
        loginView.setVisible(true);
    }
    
}

