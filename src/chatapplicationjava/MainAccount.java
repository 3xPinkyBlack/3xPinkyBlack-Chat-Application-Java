/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapplicationjava;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class MainAccount extends javax.swing.JFrame {
    String LoggedInUserName, LoggedInUserFullName, MessReceiverUserName, userName = "", receiverFullName = "";
    
    public void viewUnReadMessage(String UName) {
        String query = "SELECT * FROM "+UName+" WHERE messState='UnRead' and notifState='no'";
        String userNotif = "You Have New Message From";
        int isAvailable = 0;
        
        try {
            Connection conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery(query);
            
            while(rs.next()){
                isAvailable = 1;
                if(!rs.getString("userName").equals(MessReceiverUserName)){
                    userNotif = userNotif + "\n("+ rs.getString("userName") +") - "+ rs.getString("senderFullName");
                }
            }
            
            if(isAvailable == 1) {
                JOptionPane.showMessageDialog(null,userNotif);
                query = "UPDATE "+UName+" SET notifState='yes'";
                stmt.executeUpdate(query);
            }
            
        } catch(SQLException se){
        }
    }
    
    private void EmptyTableFriendName() {
        FriendsName.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "My Friends"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    }
    
    public MainAccount(String LoggedUserFullName, String UserName) {
        this.setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("wu.png")));
        LoggedInUserName = UserName;
        LoggedInUserFullName = LoggedUserFullName;
        initComponents();
        this.setTitle("My Account - "+LoggedUserFullName);
        
        SendMess.setVisible(false);
        MessageForm.setVisible(false);
        FriendsUserName();
               
        Dimension screenSize,frameSize;
        int x,y;
        screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        frameSize=getSize();
        x=(screenSize.width-frameSize.width)/2;
        y=(screenSize.height-frameSize.height)/2;
        setLocation(x, y);
        
    }

    private void FillUserMessage(String UserName) {
        SendMess.setVisible(true);
        MessageForm.setVisible(true);
        
        String query = "SELECT * FROM "+LoggedInUserName+" WHERE userName='"+UserName+"'";
        Connection conn = DBConnect.getConnection();
        
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery(query);
            
            String MyMess = "", ThereMess = "";
            String NewMess;
            
            int a;
            //MyMess = MyMessage.getText();
            //ThereMess = ThereMessage.getText();
            
            while (rs.next()) { 
                a = 0;
                if(rs.getString("whoSend").equals("me")) {
                    MyMess = MyMess + "\n -->> ";
                    ThereMess = ThereMess + "\n";
                    NewMess = rs.getString("userMess");
                    while(a < NewMess.length()) {
                        if(a > 0) {
                            if((a % 48) == 0) {
                                MyMess = MyMess+"\n        ";
                                ThereMess = ThereMess + "\n";
                            }
                        }
                        MyMess = MyMess + NewMess.charAt(a);
                        a++;
                    }
                } else {
                    ThereMess = ThereMess + "\n -->> ";
                    MyMess = MyMess + "\n";
                    NewMess = rs.getString("userMess");
                    while(a < NewMess.length()) {
                        if(a > 0) {
                            if((a % 48) == 0) {
                                ThereMess = ThereMess+"\n        ";
                                MyMess = MyMess + "\n";
                            }
                        }
                        ThereMess = ThereMess + NewMess.charAt(a);
                        a++;
                    }
                }
            }
            
            query = "UPDATE " + LoggedInUserName + " SET messState='Read' WHERE messState='UnRead' and userName='" + UserName + "'";
            stmt.executeUpdate(query);
            
            MyMessage.setText(MyMess);
            ThereMessage.setText(ThereMess);
            MessageForm.setText("");
        } catch (SQLException e ) {
        }
    }
    
    private void FriendsUserName(){
        EmptyTableFriendName();
        String query = "SELECT * FROM "+LoggedInUserName+"_friends WHERE FriendState='Approved'";
        Connection conn = DBConnect.getConnection();
        
        Statement SelectUser;
        try {
            SelectUser = conn.createStatement();
            ResultSet rs = SelectUser.executeQuery(query);
            DefaultTableModel model=(DefaultTableModel)FriendsName.getModel();

            Object[] row;
            while(rs.next()) {
                row = new Object[1];
                row[0] = "(" + rs.getString("FriendUserName") + ") - " + rs.getString("FriendFullName");
                    

                if(!rs.getString("FriendUserName").equals(this.LoggedInUserName)) {
                    model.addRow(row);
                }
            }
        } catch (SQLException ex) {
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MainPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        MyMessage = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        ThereMessage = new javax.swing.JTextArea();
        MessageForm = new javax.swing.JTextField();
        SendMess = new javax.swing.JButton();
        senderName = new javax.swing.JLabel();
        Me = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        FriendsName = new javax.swing.JTable();
        ProfilePanel = new javax.swing.JPanel();
        AccountMenuBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        ViewProfile = new javax.swing.JMenuItem();
        UpdateProfile = new javax.swing.JMenuItem();
        LogoutMenuItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        FindFriends = new javax.swing.JMenuItem();
        ViewFriends = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 153, 153));

        MainPanel.setBackground(new java.awt.Color(255, 153, 153));
        MainPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                MainPanelMouseMoved(evt);
            }
        });

        MyMessage.setEditable(false);
        MyMessage.setBackground(new java.awt.Color(0, 102, 102));
        MyMessage.setColumns(20);
        MyMessage.setFont(new java.awt.Font("Comic Sans MS", 3, 14)); // NOI18N
        MyMessage.setForeground(new java.awt.Color(255, 204, 204));
        MyMessage.setRows(5);
        MyMessage.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        MyMessage.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                MyMessageMouseMoved(evt);
            }
        });
        jScrollPane1.setViewportView(MyMessage);

        ThereMessage.setEditable(false);
        ThereMessage.setBackground(new java.awt.Color(0, 102, 102));
        ThereMessage.setColumns(20);
        ThereMessage.setFont(new java.awt.Font("Comic Sans MS", 3, 14)); // NOI18N
        ThereMessage.setForeground(new java.awt.Color(255, 204, 204));
        ThereMessage.setRows(5);
        ThereMessage.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        ThereMessage.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                ThereMessageMouseMoved(evt);
            }
        });
        jScrollPane2.setViewportView(ThereMessage);

        MessageForm.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        MessageForm.setText("   ");
        MessageForm.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        MessageForm.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                MessageFormMouseMoved(evt);
            }
        });
        MessageForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MessageFormActionPerformed(evt);
            }
        });
        MessageForm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                MessageFormKeyReleased(evt);
            }
        });

        SendMess.setBackground(new java.awt.Color(0, 255, 204));
        SendMess.setFont(new java.awt.Font("Comic Sans MS", 3, 24)); // NOI18N
        SendMess.setText("Send");
        SendMess.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                SendMessMouseMoved(evt);
            }
        });
        SendMess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SendMessActionPerformed(evt);
            }
        });

        senderName.setFont(new java.awt.Font("Comic Sans MS", 3, 28)); // NOI18N
        senderName.setForeground(new java.awt.Color(102, 255, 102));
        senderName.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                senderNameMouseMoved(evt);
            }
        });

        Me.setFont(new java.awt.Font("Comic Sans MS", 3, 28)); // NOI18N
        Me.setForeground(new java.awt.Color(153, 255, 153));
        Me.setText("             My Message");
        Me.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                MeMouseMoved(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));

        FriendsName.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        FriendsName.setFont(new java.awt.Font("Comic Sans MS", 3, 14)); // NOI18N
        FriendsName.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "My Friends"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        FriendsName.setGridColor(new java.awt.Color(255, 255, 255));
        FriendsName.setSelectionBackground(new java.awt.Color(255, 204, 204));
        FriendsName.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                FriendsNameMouseMoved(evt);
            }
        });
        FriendsName.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                FriendsNameMouseWheelMoved(evt);
            }
        });
        FriendsName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FriendsNameMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(FriendsName);
        if (FriendsName.getColumnModel().getColumnCount() > 0) {
            FriendsName.getColumnModel().getColumn(0).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 214, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 7, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addComponent(MessageForm)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SendMess, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                            .addComponent(Me, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                                .addComponent(senderName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(83, 83, 83))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        MainPanelLayout.setVerticalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Me, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(senderName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                            .addComponent(jScrollPane2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(MessageForm)
                            .addComponent(SendMess, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))
                        .addGap(8, 8, 8)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ProfilePanelLayout = new javax.swing.GroupLayout(ProfilePanel);
        ProfilePanel.setLayout(ProfilePanelLayout);
        ProfilePanelLayout.setHorizontalGroup(
            ProfilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1125, Short.MAX_VALUE)
        );
        ProfilePanelLayout.setVerticalGroup(
            ProfilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 511, Short.MAX_VALUE)
        );

        AccountMenuBar.setBackground(new java.awt.Color(153, 204, 0));
        AccountMenuBar.setForeground(new java.awt.Color(153, 153, 255));
        AccountMenuBar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                AccountMenuBarMouseMoved(evt);
            }
        });

        jMenu1.setForeground(new java.awt.Color(255, 102, 204));
        jMenu1.setText("    My Account  ");
        jMenu1.setFont(new java.awt.Font("Comic Sans MS", 3, 14)); // NOI18N

        ViewProfile.setFont(new java.awt.Font("Comic Sans MS", 3, 14)); // NOI18N
        ViewProfile.setForeground(new java.awt.Color(0, 102, 102));
        ViewProfile.setText("View Profile");
        ViewProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewProfileActionPerformed(evt);
            }
        });
        jMenu1.add(ViewProfile);

        UpdateProfile.setFont(new java.awt.Font("Comic Sans MS", 3, 14)); // NOI18N
        UpdateProfile.setForeground(new java.awt.Color(0, 102, 102));
        UpdateProfile.setText("Update Profile");
        UpdateProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateProfileActionPerformed(evt);
            }
        });
        jMenu1.add(UpdateProfile);

        LogoutMenuItem.setFont(new java.awt.Font("Comic Sans MS", 3, 14)); // NOI18N
        LogoutMenuItem.setForeground(new java.awt.Color(255, 0, 0));
        LogoutMenuItem.setText("LogOut");
        LogoutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(LogoutMenuItem);

        AccountMenuBar.add(jMenu1);

        jMenu2.setForeground(new java.awt.Color(153, 102, 255));
        jMenu2.setText("  Friends");
        jMenu2.setFont(new java.awt.Font("Comic Sans MS", 3, 14)); // NOI18N

        FindFriends.setFont(new java.awt.Font("Comic Sans MS", 3, 14)); // NOI18N
        FindFriends.setForeground(new java.awt.Color(0, 102, 102));
        FindFriends.setText("Find Friends");
        FindFriends.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FindFriendsActionPerformed(evt);
            }
        });
        jMenu2.add(FindFriends);

        ViewFriends.setFont(new java.awt.Font("Comic Sans MS", 3, 14)); // NOI18N
        ViewFriends.setForeground(new java.awt.Color(0, 102, 102));
        ViewFriends.setText("View Friends");
        ViewFriends.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewFriendsActionPerformed(evt);
            }
        });
        jMenu2.add(ViewFriends);

        AccountMenuBar.add(jMenu2);

        setJMenuBar(AccountMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(ProfilePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(ProfilePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MessageFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MessageFormActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MessageFormActionPerformed

    private void SendMessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SendMessActionPerformed
        String MyMess = MyMessage.getText() + "\n -->> ";
        String ThereMess = ThereMessage.getText() + "\n";
        String NewMess = MessageForm.getText();
        int a = 0;
        
        try {
            Connection conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs;
            
            String SaveMess = "INSERT INTO "+this.LoggedInUserName+" VALUES('"+this.MessReceiverUserName+"','"+this.receiverFullName+"','me','"+NewMess+"','Read','yes')";
            stmt.executeUpdate(SaveMess);
            
            SaveMess = "INSERT INTO "+this.MessReceiverUserName+" VALUES('"+this.LoggedInUserName+"','"+this.LoggedInUserFullName+"','he','"+NewMess+"','UnRead','no')";
            stmt.executeUpdate(SaveMess);
            
        } catch (SQLException e) {
            
        }
        while(a < NewMess.length()) {
            if(a > 0) {
                if((a % 48) == 0) {
                    MyMess = MyMess+"\n        ";
                    ThereMess = ThereMess + "\n";
                }
            }
            MyMess = MyMess + NewMess.charAt(a);
            a++;
        }
        
        MessageForm.setText("");
        ThereMessage.setText(ThereMess);
        MyMessage.setText(MyMess);
    }//GEN-LAST:event_SendMessActionPerformed

    private void FriendsNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FriendsNameMouseClicked
        MyMessage.setVisible(true);
        ThereMessage.setVisible(true);
        
        int i = FriendsName.getSelectedRow();
        userName = "";
        
        TableModel model = FriendsName.getModel();
        String temp = (String) model.getValueAt(i, 0);
        for(int j=1; j<temp.length(); j++){
            if(temp.charAt(j) == ')') {
            break;
        } else {
            userName = userName + temp.charAt(j);
        }
        }

        MessReceiverUserName = userName;
        FillUserMessage(userName);

        try {
            Connection conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs;

            String SaveMess = "SELECT * FROM userdb WHERE userName='"+userName+"'";
            rs = stmt.executeQuery(SaveMess);
            while(rs.next()) {
                senderName.setText("  " + rs.getString("FName") + " " + rs.getString("MName"));
                receiverFullName = rs.getString("FName") + " " + rs.getString("MName");
            }

        } catch (SQLException e) {

        }

    }//GEN-LAST:event_FriendsNameMouseClicked

    private void LogoutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutMenuItemActionPerformed
        this.setVisible(false);
        new LoginForm().setVisible(true);
    }//GEN-LAST:event_LogoutMenuItemActionPerformed

    private void UpdateProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateProfileActionPerformed
        this.setVisible(false);
        new UpdateProfile(LoggedInUserName,LoggedInUserFullName).setVisible(true);
    }//GEN-LAST:event_UpdateProfileActionPerformed

    private void ViewProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewProfileActionPerformed
        this.setVisible(false);
        new ViewProfile(LoggedInUserName,LoggedInUserFullName).setVisible(true);
    }//GEN-LAST:event_ViewProfileActionPerformed

    private void MeMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MeMouseMoved
        if(!userName.equals("") && MessageForm.getText().equals(""))
            FillUserMessage(userName);
        
        viewUnReadMessage(LoggedInUserName);
        FriendsUserName();
    }//GEN-LAST:event_MeMouseMoved

    private void senderNameMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_senderNameMouseMoved
        if(!userName.equals("") && MessageForm.getText().equals(""))
            FillUserMessage(userName);
        
        viewUnReadMessage(LoggedInUserName);
        FriendsUserName();
    }//GEN-LAST:event_senderNameMouseMoved

    private void MainPanelMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MainPanelMouseMoved
        if(!userName.equals("") && MessageForm.getText().equals(""))
            FillUserMessage(userName);
        
        viewUnReadMessage(LoggedInUserName);
        FriendsUserName();
    }//GEN-LAST:event_MainPanelMouseMoved

    private void MyMessageMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MyMessageMouseMoved
        if(!userName.equals("") && MessageForm.getText().equals(""))
            FillUserMessage(userName);
        
        viewUnReadMessage(LoggedInUserName);
        FriendsUserName();
    }//GEN-LAST:event_MyMessageMouseMoved

    private void ThereMessageMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ThereMessageMouseMoved
        if(!userName.equals("") && MessageForm.getText().equals(""))
            FillUserMessage(userName);
        
        viewUnReadMessage(LoggedInUserName);
        FriendsUserName();
    }//GEN-LAST:event_ThereMessageMouseMoved

    private void SendMessMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SendMessMouseMoved
        if(!userName.equals("") && MessageForm.getText().equals(""))
            FillUserMessage(userName);
        
        viewUnReadMessage(LoggedInUserName);
        FriendsUserName();
    }//GEN-LAST:event_SendMessMouseMoved

    private void MessageFormMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MessageFormMouseMoved
        if(!userName.equals("") && MessageForm.getText().equals(""))
            FillUserMessage(userName);
        
        viewUnReadMessage(LoggedInUserName);
        FriendsUserName();
    }//GEN-LAST:event_MessageFormMouseMoved

    private void FriendsNameMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FriendsNameMouseMoved
        if(!userName.equals("") && MessageForm.getText().equals(""))
            FillUserMessage(userName);
        
        viewUnReadMessage(LoggedInUserName);
        FriendsUserName();
    }//GEN-LAST:event_FriendsNameMouseMoved

    private void AccountMenuBarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AccountMenuBarMouseMoved
        if(!userName.equals("") && MessageForm.getText().equals(""))
            FillUserMessage(userName);
        
        viewUnReadMessage(LoggedInUserName);
        FriendsUserName();
    }//GEN-LAST:event_AccountMenuBarMouseMoved

    private void FindFriendsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FindFriendsActionPerformed
        this.setVisible(false);
        new FindFromFriends(LoggedInUserName, LoggedInUserFullName).setVisible(true);
    }//GEN-LAST:event_FindFriendsActionPerformed

    private void ViewFriendsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewFriendsActionPerformed
        this.setVisible(false);
        new ViewFriends(LoggedInUserName, LoggedInUserFullName).setVisible(true);
    }//GEN-LAST:event_ViewFriendsActionPerformed

    private void FriendsNameMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_FriendsNameMouseWheelMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_FriendsNameMouseWheelMoved

    private void MessageFormKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MessageFormKeyReleased
        
    }//GEN-LAST:event_MessageFormKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

      
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar AccountMenuBar;
    private javax.swing.JMenuItem FindFriends;
    private javax.swing.JTable FriendsName;
    private javax.swing.JMenuItem LogoutMenuItem;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JLabel Me;
    private javax.swing.JTextField MessageForm;
    private javax.swing.JTextArea MyMessage;
    private javax.swing.JPanel ProfilePanel;
    private javax.swing.JButton SendMess;
    private javax.swing.JTextArea ThereMessage;
    private javax.swing.JMenuItem UpdateProfile;
    private javax.swing.JMenuItem ViewFriends;
    private javax.swing.JMenuItem ViewProfile;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel senderName;
    // End of variables declaration//GEN-END:variables
}
