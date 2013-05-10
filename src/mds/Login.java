package mds;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.font.TextAttribute;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 *
 * @author Gabriel Budau
 */
public class Login extends javax.swing.JFrame {

    public Login() {
        initComponents();
        username.setHorizontalAlignment(JTextField.CENTER);
        password.setHorizontalAlignment(JTextField.CENTER);
        this.setTitle("LOGIN");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width - this.getWidth()) / 2, (dim.height - this.getHeight()) / 2);
        loginButton.setFont(new Font("Consolas", 10, 12));
        exitButton.setFont(new Font("Consolas", 10, 12));
        usernameLabel.setFont(new Font("Consolas", 10, 12));
        signUpLabel.setFont(new Font("Consolas", 10, 12));
        forgotMyPasswordLabel.setFont(new Font("Consolas", 10, 12));
        usernameLabel1.setFont(new Font("Consolas", 10, 12));
        passwordLabel.setFont(new Font("Consolas", 10, 12));
        ImageIcon icon = new ImageIcon(".\\chat.png");
        this.setIconImage(icon.getImage());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        username = new javax.swing.JTextField();
        loginButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        usernameLabel = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        signUpLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        usernameLabel1 = new javax.swing.JLabel();
        forgotMyPasswordLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        username.setToolTipText("Enter Username");
        username.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usernameKeyPressed(evt);
            }
        });

        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        usernameLabel.setText("Info");

        password.setToolTipText("Enter Password");
        password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordKeyPressed(evt);
            }
        });

        signUpLabel.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        signUpLabel.setForeground(new java.awt.Color(51, 102, 255));
        signUpLabel.setText("Sign Up");
        signUpLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                signUpLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                signUpLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                signUpLabelMouseExited(evt);
            }
        });

        passwordLabel.setText("Password");

        usernameLabel1.setText("Username");

        forgotMyPasswordLabel.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        forgotMyPasswordLabel.setForeground(new java.awt.Color(51, 102, 255));
        forgotMyPasswordLabel.setText("Forgot my password");
        forgotMyPasswordLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                forgotMyPasswordLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                forgotMyPasswordLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                forgotMyPasswordLabelMouseExited(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(usernameLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(username, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(password, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passwordLabel, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(usernameLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(forgotMyPasswordLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(signUpLabel)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(forgotMyPasswordLabel)
                    .addComponent(signUpLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(usernameLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginButton)
                    .addComponent(exitButton))
                .addGap(10, 10, 10)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void loginCheck(final String user, final String pass) {
        if (Utils.checkUser(user, pass)) {
            this.usernameLabel.setVisible(true);
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new Client(user).setVisible(true);
                }
            });
            this.setVisible(false);
            username.setText("");
        } else {
            this.usernameLabel.setText("<html>Username and/or password<br>are invalid</html>");
            this.usernameLabel.setForeground(Color.red);
            this.usernameLabel.setVisible(true);
        }
    }
    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        final String user = username.getText();
        String pass = new String(password.getPassword());
        if (!user.equals("")) {
            loginCheck(user, pass);
        }
    }//GEN-LAST:event_loginButtonActionPerformed

    private void usernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String user = username.getText();
            String pass = new String(password.getPassword());
            if (!user.equals("")) {
                loginCheck(user, pass);

            }
        }
    }//GEN-LAST:event_usernameKeyPressed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void signUpLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signUpLabelMouseEntered
        signUpLabel.setForeground(Color.RED);
        signUpLabel.setFont(new Font(Utils.setUnderline()));
    }//GEN-LAST:event_signUpLabelMouseEntered

    private void signUpLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signUpLabelMouseExited
        signUpLabel.setForeground(new java.awt.Color(51, 102, 255));
        signUpLabel.setFont(new Font("Consolas", 10, 12));
    }//GEN-LAST:event_signUpLabelMouseExited

    private void signUpLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signUpLabelMouseClicked
        signUpLabel.setForeground(Color.RED);
        signUpLabel.setFont(new Font("Consolas", 1, 12));
        signUpLabel.setFont(new Font(Utils.setUnderlineBold()));
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignUp().setVisible(true);
            }
        });
    }//GEN-LAST:event_signUpLabelMouseClicked

    private void forgotMyPasswordLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forgotMyPasswordLabelMouseClicked
        forgotMyPasswordLabel.setForeground(Color.RED);
        forgotMyPasswordLabel.setFont(new Font("Consolas", 1, 12));
        forgotMyPasswordLabel.setFont(new Font(Utils.setUnderlineBold()));
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ForgotMyPassword().setVisible(true);
            }
        });
    }//GEN-LAST:event_forgotMyPasswordLabelMouseClicked

    private void forgotMyPasswordLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forgotMyPasswordLabelMouseEntered
        forgotMyPasswordLabel.setForeground(Color.RED);
        forgotMyPasswordLabel.setFont(new Font(Utils.setUnderline()));
    }//GEN-LAST:event_forgotMyPasswordLabelMouseEntered

    private void forgotMyPasswordLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forgotMyPasswordLabelMouseExited
        forgotMyPasswordLabel.setForeground(new java.awt.Color(51, 102, 255));
        forgotMyPasswordLabel.setFont(new Font("Consolas", 10, 12));
    }//GEN-LAST:event_forgotMyPasswordLabelMouseExited

    private void passwordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String user = username.getText();
            String pass = new String(password.getPassword());
            if (!user.equals("")) {
                loginCheck(user, pass);

            }
        }
    }//GEN-LAST:event_passwordKeyPressed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel forgotMyPasswordLabel;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton loginButton;
    private javax.swing.JPasswordField password;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel signUpLabel;
    private javax.swing.JTextField username;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JLabel usernameLabel1;
    // End of variables declaration//GEN-END:variables
}
