

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;

/**
 *
 * @author Gabriel Budau, Alina Midoschi Livia, Malaiasi Laura Georgiana, Turbatu Elena, Horia Constantin
 */
public class ServerFrame extends javax.swing.JFrame {

    private final int PORT = 1234;
    private ServerSocket ss;
    private ArrayList<Tuplu<String, Socket>> Links = new ArrayList<>();
    private ArrayList<Link> conexiuni = new ArrayList<>();
    private ArrayList<Thread> threads = new ArrayList<>();
    private long getLinksThreadId; //Id threadului ce accepta conexiuni, avem nevoie pentru a-l putea inchide
    private boolean serverStatus = false;
    public ServerFrame() {
        initComponents();
        serverStatusLabel.setForeground(Color.BLUE);
        clearBtn.setFont(new Font("Consolas", 10, 10));
        startBtn.setFont(new Font("Consolas", 10, 10));
        stopBtn.setFont(new Font("Consolas", 10, 10));
        startServer();
        serverStatus = true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        serverStatusLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        serverMessagesTextArea = new javax.swing.JTextArea();
        clearBtn = new javax.swing.JButton();
        startBtn = new javax.swing.JButton();
        stopBtn = new javax.swing.JButton();
        command = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        serverStatusLabel.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        serverStatusLabel.setText("offline");

        serverMessagesTextArea.setEditable(false);
        serverMessagesTextArea.setColumns(20);
        serverMessagesTextArea.setRows(5);
        jScrollPane1.setViewportView(serverMessagesTextArea);

        clearBtn.setText("clear output");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        startBtn.setText("start");
        startBtn.setEnabled(false);
        startBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startBtnActionPerformed(evt);
            }
        });

        stopBtn.setText("stop");
        stopBtn.setEnabled(false);
        stopBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopBtnActionPerformed(evt);
            }
        });

        command.setEnabled(false);
        command.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                commandKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(clearBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(startBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stopBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                        .addComponent(serverStatusLabel))
                    .addComponent(command))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(serverStatusLabel)
                        .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(startBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(stopBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(command, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        serverMessagesTextArea.setText("");
    }//GEN-LAST:event_clearBtnActionPerformed

    private void stopBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopBtnActionPerformed
        if (serverStatus) {
            try {
                
                for (Link elem : conexiuni) {
                    elem.input.close();
                    elem.output.close();
                    elem.link.close();
                }
                for (Thread elem : threads) {
                    elem.interrupt();
                }
                Links.clear();
                serverStatusLabel.setText("OFFLINE");
                serverStatusLabel.setForeground(new java.awt.Color(255, 0, 0));
                serverMessagesTextArea.append("Server Closed \n");
                serverStatus = false;
                ss.close();
            } catch (Exception ex) {
                serverMessagesTextArea.append(ex.getMessage());
            }
        }
    }//GEN-LAST:event_stopBtnActionPerformed

    private void startBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startBtnActionPerformed
        if (!serverStatus) {
            startServer();
            serverStatus = true;
        }
    }//GEN-LAST:event_startBtnActionPerformed

    private void commandKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_commandKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String com = this.command.getText();
            if(!com.equals("")){
                String[] commandSplit = com.split(" ");
                String printMessage = null;
                if(commandSplit[0].equals("info") && ((printMessage = ServerUtils.userInfo(commandSplit[1])) != null)){
                    serverMessagesTextArea.append("\n");
                    serverMessagesTextArea.append(printMessage);
                }else{
                    serverMessagesTextArea.append("\nUser does not exist! \n");
                }
            }
        }
    }//GEN-LAST:event_commandKeyPressed

    private Tuplu findLink(String _username) {
        for (Tuplu elem : Links) {
            if (elem.x.equals(_username)) {
                return elem;
            }
        }
        return null;
    }

    private int findLinkBySocket(Socket _link) {
        for (int i = 0; i < Links.size(); i++) {
            if (Links.get(i).y == _link) {
                return i;
            }
        }
        return -1;
    }

    private void startServer() {
        serverMessagesTextArea.setFont(new Font("Consolas", 10, 12));
        serverMessagesTextArea.setForeground(Color.WHITE);
        serverMessagesTextArea.setBackground(Color.BLACK);
        
        command.setFont(new Font("Consolas", 10, 12));
        command.setForeground(Color.WHITE);
        command.setBackground(Color.BLACK);
        
        serverMessagesTextArea.append("Opening PORT=" + PORT + " ...\n");
        try {
            ss = new ServerSocket(PORT);
            serverStatusLabel.setText("ONLINE");
            serverStatusLabel.setForeground(new java.awt.Color(0, 102, 0));
            serverStatusLabel.setFont(new Font("Consolas", 10, 15));
        } catch (IOException ioEx) {
            serverMessagesTextArea.append("Unable to open PORT = " + PORT + " \n");
            serverStatusLabel.setText("OFFLINE");
            serverStatusLabel.setForeground(new java.awt.Color(255, 0, 0));
        }
        serverMessagesTextArea.append("PORT=" + PORT + " opened \n");
        serverMessagesTextArea.append("Waiting connections... \n");
        
        Thread T = new Thread(new Runnable() {
            @Override
            public void run() {
  
                Socket link = null;
                while (serverStatus) {

                    try {
                        link = ss.accept();
                        Link lk = new Link(new Tuplu("", link));
                        Thread Tr = new Thread(lk);
                        conexiuni.add(lk);
                        threads.add(Tr);
                        Tr.start();

                    } catch (IOException ioEx) {
                        serverMessagesTextArea.append(ioEx.toString());
                    }
                }
                
                Thread.currentThread().interrupt();

            }
        });
        this.getLinksThreadId = T.getId();
        T.start();
    }

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearBtn;
    private javax.swing.JTextField command;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea serverMessagesTextArea;
    private javax.swing.JLabel serverStatusLabel;
    private javax.swing.JButton startBtn;
    private javax.swing.JButton stopBtn;
    // End of variables declaration//GEN-END:variables

    private void printLinks() {
        for (Tuplu elem : Links) {
            serverMessagesTextArea.append((String) elem.getX() + " ");
        }
        serverMessagesTextArea.append("\n");
    }

    class Link implements Runnable {

        Socket link;
        Scanner input;
        PrintWriter output;
        private boolean keepLoop = true;
        private boolean firstMessage = true;

        Link(Tuplu _t) {
            this.link = (Socket) _t.getY();
            Links.add(_t);
            try {
                input = new Scanner(link.getInputStream());
            } catch (IOException ioEx) {
                serverMessagesTextArea.append(ioEx.toString());
            }
        }

        private void execAlias(String msg) {

            Tuplu tup;
            tup = findLink(msg);
            int Index = findLinkBySocket(link);
            String tmp = (String) Links.get(Index).getX();
            if (tmp.equals("") && Index != -1) {
                if (findLink(msg) != null) {
                    try {
                        output = new PrintWriter(link.getOutputStream(), true);
                        output.println("Numele deja exista.");
                    } catch (IOException ex) {
                        Logger.getLogger(ServerFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    Links.set(Index, new Tuplu(msg, Links.get(Index).getY()));
                    serverMessagesTextArea.append("\"" + msg + "\" logged in  \n");
                }
            }
        }

        private void execMsg(String msg) {
            String username = msg.substring(0, msg.indexOf("*"));
            String chatWith = msg.substring(msg.indexOf("*") + 3, msg.indexOf("%"));
            String message = msg.substring(msg.indexOf("%") + 3);

            try {
                boolean found = false;
                String out = "", outUser = "";
                Tuplu tup = findLink(chatWith);
                if (tup != null) {
                    Socket temp = (Socket) tup.getY();

                    output = new PrintWriter(temp.getOutputStream(), true);

                    outUser = (String) tup.getX();

                    out = username;

                    output.println(out + "::" + message);

                    found = true;
                }
                if (!found) {/*
                     * TODO----------
                     output = new PrintWriter(link.getOutputStream(), true);
                     output.println(username + " nu este conectat.");*/

                }
            } catch (IOException e) {
                e.getMessage();
            }
        }

        private void execQuit() {
            try {
                int index = findLinkBySocket(link);
                serverMessagesTextArea.append("\"" + Links.get(index).getX() + "\" logged out  \n");
                Links.remove(index);
                link.close();
                keepLoop = false;

                Thread.currentThread().interrupt();
            } catch (IOException ex) {
                Logger.getLogger(ServerFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public void run() {
            String message = "";
            if (input.hasNextLine()) {
                message = input.nextLine();
            }
            while (keepLoop) {
                if (firstMessage) {
                    execAlias(message);
                    firstMessage = false;
                } else if (message.equals("***QUIT***")) {
                    execQuit();
                } else {
                    execMsg(message);
                }
                if (input.hasNextLine()) {
                    message = input.nextLine();
                }
            }

        }
    }
}
