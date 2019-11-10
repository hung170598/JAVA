/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Account;
import view.LoginView;

/**
 *
 * @author hung1
 */
public class LoginControler {
    private LoginView loginView;

    public LoginControler(LoginView loginView) {
        this.loginView = loginView;
        this.loginView.addLoginListener(new LoginListener());
    }
    
    
    
    class LoginListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Account account = loginView.getAccount();
            if(account.getUsername().equals("admin"))
                loginView.showMessage("OK!");
            else
                loginView.showMessage("NO!");
        }
    }
    
    public static void main(String[] args) {
        LoginView loginView = new LoginView();
        LoginControler control = new LoginControler(loginView);
        loginView.setVisible(true);
    }
    
}

