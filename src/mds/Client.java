package mds;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.text.*;

/**
 *
 * @author Gabriel Budau
 */
class Client extends javax.swing.JFrame {
    // Comm

    private InetAddress host = null;
    private final int PORT = 1234;
    private Socket link = null;
    private PrintWriter out = null;
    //-------------
    private ArrayList<Tuplu<String, ClientPanel>> tabs = new ArrayList<>();
    private final String username;

    public Client(String _username) {
        initComponents();
        try {
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException uhEx) {
            System.exit(1);
        }
        username = _username;
        Utils.setStatus(username, "online");
        start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        friendsList = new javax.swing.JList();
        chatButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        tabsPane = new javax.swing.JTabbedPane();

        jScrollPane2.setViewportView(jTextPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        friendsList.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        friendsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        friendsList.setAlignmentX(0.3F);
        friendsList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                friendsListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(friendsList);

        chatButton.setText("CHAT");
        chatButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chatButtonActionPerformed(evt);
            }
        });

        refreshButton.setText("Refresh");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(chatButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(refreshButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabsPane, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tabsPane)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chatButton)
                            .addComponent(refreshButton))))
                .addGap(0, 14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newTab(String _title) {
        if (tabs.size() < 8 && searchByTitle(_title) == -1) {
            ClientPanel cpl = new ClientPanel(_title);
            tabsPane.add(_title, cpl);
            tabs.add(new Tuplu(username + " chats with:" + _title, cpl));
        }
    }
    
    private void startChat() {
        if (!friendsList.isSelectionEmpty()) {
            String selItem = (String) friendsList.getSelectedValue();
            selItem = selItem.substring(selItem.indexOf(">") + 1);
            selItem = selItem.substring(selItem.indexOf(">") + 1);
            selItem = selItem.substring(selItem.indexOf(">") + 1);
            selItem = selItem.substring(0, selItem.indexOf("<"));
            if (!selItem.equals(username)) {
                newTab(selItem);
            }
        }
    }
    private void closeForm(){
        try {
            Utils.setStatus(username, "offline");
            out.println("***QUIT***");
            link.close();
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        Utils.getList(friendsList);
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void chatButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chatButtonActionPerformed
        startChat();
    }//GEN-LAST:event_chatButtonActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        closeForm();
    }//GEN-LAST:event_formWindowClosing

    private void friendsListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_friendsListMouseClicked
        if (evt.getClickCount() == 2) {
            startChat();
        }
    }//GEN-LAST:event_friendsListMouseClicked

    private int searchByTitle(String _title) {
        int i = 0;
        for (Tuplu elem : tabs) {
            String temp = (String) elem.getX();
            temp = new StringBuffer(temp).reverse().toString();
            String tmp = temp.substring(0, temp.indexOf(":"));
            tmp = new StringBuffer(tmp).reverse().toString();
            if (tmp.equals(_title)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    private int searchByTitle_v0_2(String _title) {
        int i = 0;
        for (Tuplu elem : tabs) {
            if (_title.equals((String) elem.getX())) {
                return i;
            }
            i++;
        }
        return -1;
    }

    private void start() {
        try {
            Utils.getList(friendsList);
            this.setTitle(this.username);

            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation((dim.width - this.getWidth()) / 2, (dim.height - this.getHeight()) / 2);

            friendsList.setFont(new java.awt.Font("Oswald", 10, 12));

            link = new Socket(host, PORT);
            out = new PrintWriter(link.getOutputStream(), true);
            out.println(username);
        } catch (IOException ex) {
            closeForm();
        }

        Thread getMsg = new Thread(new Runnable() {
            @Override
            public void run() {
                String res = "";
                Scanner in;
                try {
                    in = new Scanner(link.getInputStream());
                    while (in.hasNextLine()) {
                        res = in.nextLine();//mesaj primit de la server 

                        String temp = res.substring(0, res.indexOf(":")); //de la cine am primit mesajul

                        int index = searchByTitle(temp);

                        if (index != -1) {
                            //ClientForm frm = (ClientForm) tabs.get(index);
                            ClientPanel cpl = (ClientPanel) tabs.get(index).getY();
                            //--------Decriptare mesaj-----
                            //String dec = new String(AES.decrypt(res.substring(res.indexOf(":") + 2).getBytes(), cpl.password.getBytes()));
                            //-------------------------
                            StyledDocument doc = (StyledDocument) cpl.messagesTextPane.getDocument();
                            // Create a style object and then set the style attributes
                            Style style = doc.addStyle("StyleName", null);
                            StyleConstants.setFontSize(style, 10);
                            StyleConstants.setForeground(style, Color.red);
                            StyleConstants.setFontFamily(style, "Consolas");
                            StyleConstants.setBold(style, true);

                            // Append to document
                            doc.insertString(doc.getLength(), "  " + res.substring(0, res.indexOf(":")), style);
                            StyleConstants.setForeground(style, Color.DARK_GRAY);
                            StyleConstants.setBold(style, false);
                            doc.insertString(doc.getLength(), res.substring(res.indexOf(":")) + "\n", style);
                            cpl.messagesTextPane.select(doc.getLength(), doc.getLength());

                        } else {
                            newTab(temp);
                            ClientPanel cpl = tabs.get(tabs.size() - 1).getY();
                            //--------Decriptare mesaj-----
                            //String dec = new String(AES.decrypt(res.substring(res.indexOf(":") + 2).getBytes(), cpl.password.getBytes()));
                            //-------------------------
                            StyledDocument doc = (StyledDocument) cpl.messagesTextPane.getDocument();
                            // Create a style object and then set the style attributes
                            Style style = doc.addStyle("StyleName", null);
                            StyleConstants.setFontSize(style, 10);
                            StyleConstants.setForeground(style, Color.red);
                            StyleConstants.setFontFamily(style, "Consolas");
                            StyleConstants.setBold(style, true);
                            // Append to document
                            doc.insertString(doc.getLength(), "  " + res.substring(0, res.indexOf(":")), style);
                            StyleConstants.setForeground(style, Color.DARK_GRAY);
                            StyleConstants.setBold(style, false);
                            doc.insertString(doc.getLength(), res.substring(res.indexOf(":")) + "\n", style);
                            cpl.messagesTextPane.select(doc.getLength(), doc.getLength());
                            //TODO: Sa-i apara fereastra


                        }
                    }
                } catch (IOException ex) {
                } catch (BadLocationException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        getMsg.start();

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton chatButton;
    private javax.swing.JList friendsList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JButton refreshButton;
    private javax.swing.JTabbedPane tabsPane;
    // End of variables declaration//GEN-END:variables

    class ClientPanel extends javax.swing.JPanel {

        private String chatWith = null;
        private PrintWriter out = null;
        private String password = "aaaaaaaaaaaaaaa";

        public ClientPanel(String _chatWith) {
            try {
                initComponents();
                this.chatWith = _chatWith;
                //this.setTitle(username + " chats with:" + _chatWith);
                out = new PrintWriter(link.getOutputStream(), true);

            } catch (IOException ex) {
            }
        }

        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
        private void initComponents() {

            jScrollPane3 = new javax.swing.JScrollPane();
            messagesTextPane = new javax.swing.JTextPane();
            sendMessageTextField = new javax.swing.JTextField();
            sendButton = new javax.swing.JButton();
            exitLabel = new javax.swing.JLabel();

            setPreferredSize(new java.awt.Dimension(380, 343));

            jScrollPane3.setAutoscrolls(true);

            messagesTextPane.setEditable(false);
            jScrollPane3.setViewportView(messagesTextPane);

            sendMessageTextField.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    sendMessageTextFieldKeyPressed(evt);
                }
            });

            sendButton.setText("SEND");
            sendButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    sendButtonActionPerformed(evt);
                }
            });

            exitLabel.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
            exitLabel.setText("exit");
            exitLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    exitLabelMouseEntered(evt);
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    exitLabelMouseExited(evt);
                }

                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    exitLabelMouseClicked(evt);
                }
            });

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                    .addGap(14, 14, 14)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addComponent(sendMessageTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(sendButton))
                    .addComponent(jScrollPane3)
                    .addComponent(exitLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(exitLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sendMessageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sendButton))
                    .addContainerGap()));
        }// </editor-fold>                        

        private void encdecTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
            // TODO add your handling code here:
        }

        private void sendMessage() {
            try {
                String mes = this.sendMessageTextField.getText();


                if (!mes.equals("")) {
                    this.sendMessageTextField.setText("");


                    StyledDocument doc = (StyledDocument) messagesTextPane.getDocument();
                    // Create a style object and then set the style attributes
                    Style style = doc.addStyle("StyleName", null);
                    StyleConstants.setFontSize(style, 10);
                    StyleConstants.setForeground(style, Color.BLUE);
                    StyleConstants.setFontFamily(style, "Consolas");
                    StyleConstants.setBold(style, true);
                    // Append to document
                    doc.insertString(doc.getLength(), "  " + username, style);
                    StyleConstants.setForeground(style, Color.DARK_GRAY);
                    StyleConstants.setBold(style, false);
                    doc.insertString(doc.getLength(), "::" + mes + "\n", style);
                    messagesTextPane.select(doc.getLength(), doc.getLength());
                    /*
                     mes = new String(AES.encrypt(mes.getBytes("US-ASCII"), password.getBytes("US-ASCII")));*/
                    String send = username + "***" + chatWith + "%%%" + mes;
                    out.println(send);
                }
            } catch (BadLocationException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {
            sendMessage();
        }

        private void exitLabelMouseClicked(java.awt.event.MouseEvent evt) {
            exitLabel.setForeground(Color.RED);
            exitLabel.setFont(new Font("Consolas", 1, 12));
            exitLabel.setFont(new Font(Utils.setUnderlineBold()));

            //TODO sa inchida tab-ul

            int index = searchByTitle_v0_2(username + " chats with:" + chatWith);
            Tuplu tup = tabs.get(index);
            ClientPanel cpl = (ClientPanel) tup.getY();
            cpl.setVisible(false);
            tabsPane.remove(index);
            tabs.remove(index);
            Thread.currentThread().interrupt();

            //DID

        }

        private void exitLabelMouseEntered(java.awt.event.MouseEvent evt) {
            exitLabel.setForeground(Color.RED);
            exitLabel.setFont(new Font(Utils.setUnderline()));
        }

        private void exitLabelMouseExited(java.awt.event.MouseEvent evt) {
            exitLabel.setForeground(new java.awt.Color(51, 102, 255));
            exitLabel.setFont(new Font("Consolas", 10, 12));
        }

        private void sendMessageTextFieldKeyPressed(java.awt.event.KeyEvent evt) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                sendMessage();
            }
        }
        // Variables declaration - do not modify                     
        private javax.swing.JButton encdecButton;
        private javax.swing.JTextField encdecTextField;
        private javax.swing.JLabel exitLabel;
        private javax.swing.JScrollPane jScrollPane3;
        private javax.swing.JTextPane messagesTextPane;
        private javax.swing.JButton sendButton;
        private javax.swing.JTextField sendMessageTextField;
        // End of variables declaration                   
    }
}
