

import java.awt.Color;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;

/**
 *
 * @author Gabriel Budau
 */
public class ServerFrame extends javax.swing.JFrame {

    private final int PORT = 1234;
    private ServerSocket ss;
    private ArrayList<Tuplu<String, Socket>> Links = new ArrayList<Tuplu<String, Socket>>();

    public ServerFrame() {
        initComponents();
        serverStatusLabel.setForeground(Color.BLUE);
        startServer();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        startServerButton = new javax.swing.JButton();
        serverStatusLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        serverMessagesTextArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        startServerButton.setText("START");
        startServerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startServerButtonActionPerformed(evt);
            }
        });

        serverStatusLabel.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        serverStatusLabel.setText("offline");

        serverMessagesTextArea.setEditable(false);
        serverMessagesTextArea.setColumns(20);
        serverMessagesTextArea.setRows(5);
        jScrollPane1.setViewportView(serverMessagesTextArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(startServerButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(serverStatusLabel)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startServerButton)
                    .addComponent(serverStatusLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        serverMessagesTextArea.append("Opening PORT...\n");
        try {
            ss = new ServerSocket(PORT);
            serverStatusLabel.setText("online");
            serverStatusLabel.setForeground(Color.green);
        } catch (IOException ioEx) {
            serverMessagesTextArea.append("Unable to open port \n");
            serverStatusLabel.setText("offline");
            serverStatusLabel.setForeground(Color.black);
            System.exit(1);
        }

        Thread T = new Thread(new Runnable() {
            @Override
            public void run() {
                Socket link = null;
                while (true) {

                    try {
                        link = ss.accept();
                        Thread Tr = new Thread(new Link(new Tuplu("", link)));
                        Tr.start();

                    } catch (IOException ioEx) {
                        serverMessagesTextArea.append(ioEx.toString());
                    }

                }
            }
        });
        T.start();
    }
    private void startServerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startServerButtonActionPerformed
        startServer();
    }//GEN-LAST:event_startServerButtonActionPerformed

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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea serverMessagesTextArea;
    private javax.swing.JLabel serverStatusLabel;
    private javax.swing.JButton startServerButton;
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
                Links.remove(index);
                link.close();
                keepLoop = false;
                //System.out.println(Links + "\n");
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
