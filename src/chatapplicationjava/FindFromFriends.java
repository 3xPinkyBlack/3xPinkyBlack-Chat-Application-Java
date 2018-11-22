/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapplicationjava;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author u
 */
public class FindFromFriends extends javax.swing.JFrame {
    String userName, userFullName;
    
    private void EmptyTable() {
        ViewAllFriendsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Friend UserName", "Friends Name", "Friend State"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ViewAllFriendsTable.setAlignmentX(1.0F);
        ViewAllFriendsTable.setAlignmentY(1.0F);
    }
    
    public final ImageIcon ResizeImage(byte[] pic) {
        ImageIcon MyImage = new ImageIcon(pic);
        Image img = MyImage.getImage();
        Image NewImg = img.getScaledInstance(userProfileImageViewer.getWidth(), userProfileImageViewer.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(NewImg);
        return image;
    }
    
    
    private void FriendsUserName(String LoggedInUserName, String SearchValue){
        String[][] FriendsList = new String[10000][3];
        String query;
        String query2;
        EmptyTable();
        if(!SearchValue.equals("")) {
            query = "SELECT * FROM userdb WHERE CONCAT(userName,FName,MName,LName) LIKE '%"+SearchValue+"%' ORDER BY FName,MName,LName ASC";
            query2 = "SELECT * FROM "+LoggedInUserName+"_friends ORDER BY FriendFullName ASC";
        } else {
            query = "SELECT * FROM userdb ORDER BY FName, MName, LName ASC";
            query2 = "SELECT * FROM "+LoggedInUserName+"_friends ORDER BY FriendFullName ASC";
        }
        int NumFriends = 0;
        
        try {
            Connection conn = DBConnect.getConnection();
            ResultSet rs1;
            Statement stmt = conn.createStatement();
            rs1 = stmt.executeQuery(query2);
            
            while(rs1.next()) {
                FriendsList[NumFriends][0] = rs1.getString("FriendUserName");
                FriendsList[NumFriends][1] = rs1.getString("FriendFullName");
                FriendsList[NumFriends][2] = rs1.getString("FriendState");
                NumFriends++;
            }
            
            PreparedStatement SelectUser;
           
            SelectUser = conn.prepareStatement(query);
            ResultSet rs = SelectUser.executeQuery();
            DefaultTableModel model=(DefaultTableModel)ViewAllFriendsTable.getModel();
            
            Object[] row;
            int CountIndex = 0;
            while(rs.next()) {
                int isAvail = 0;
                row = new Object[3];
                for(int i=0; i< NumFriends; i++) {
                    if(FriendsList[i][0].equals(rs.getString("userName"))) { 
                        isAvail = 1;
                    }
                }
                
                if(isAvail == 0) {
                    row[0] = rs.getString("userName");
                    row[1] = rs.getString("FName") + " " + rs.getString("MName");
                    row[2] = "Send Friend Request";
                } else {
                    row[0] = FriendsList[CountIndex][0];
                    row[1] = FriendsList[CountIndex][1];
                    row[2] = FriendsList[CountIndex][2];
                    CountIndex++;
                }
                if(!rs.getString("userName").equals(userName)){ 
                    model.addRow(row);
                }
                
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        };
    }
    public FindFromFriends(String LoggedInUserName, String LoggedInUserFullName) {
        this.setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("wu.png")));
        initComponents();
        this.setTitle("Search Friends - "+LoggedInUserFullName);
        userName = LoggedInUserName;
        userFullName = LoggedInUserFullName;
        FriendsUserName(LoggedInUserName,"");
        
        
        Dimension screenSize,frameSize;
        int x,y;
        screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        frameSize=getSize();
        x=(screenSize.width-frameSize.width)/2;
        y=(screenSize.height-frameSize.height)/2;
        setLocation(x, y);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ViewAllFriendsTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        SearchBox = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        userProfileImageViewer = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ViewAllFriendsTable.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        ViewAllFriendsTable.setFont(new java.awt.Font("Comic Sans MS", 3, 18)); // NOI18N
        ViewAllFriendsTable.setForeground(new java.awt.Color(255, 204, 204));
        ViewAllFriendsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Friend UserName", "Friends Name", "Friend State"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ViewAllFriendsTable.setAlignmentX(1.0F);
        ViewAllFriendsTable.setAlignmentY(1.0F);
        ViewAllFriendsTable.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ViewAllFriendsTableFocusGained(evt);
            }
        });
        ViewAllFriendsTable.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                ViewAllFriendsTableMouseWheelMoved(evt);
            }
        });
        ViewAllFriendsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ViewAllFriendsTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(ViewAllFriendsTable);

        jLabel1.setFont(new java.awt.Font("NFS font", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 153));
        jLabel1.setText("     Search For Friends");

        SearchBox.setFont(new java.awt.Font("Comic Sans MS", 3, 14)); // NOI18N
        SearchBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchBoxKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 3, 24)); // NOI18N
        jLabel2.setText("Enter Value To Search");

        jButton1.setFont(new java.awt.Font("Comic Sans MS", 3, 18)); // NOI18N
        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                .addComponent(SearchBox, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userProfileImageViewer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(SearchBox)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                    .addComponent(userProfileImageViewer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SearchBoxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchBoxKeyReleased
       FriendsUserName(userName,SearchBox.getText());
    }//GEN-LAST:event_SearchBoxKeyReleased

    private void ViewAllFriendsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ViewAllFriendsTableMouseClicked
        int i = ViewAllFriendsTable.getSelectedRow();
        
        TableModel model = ViewAllFriendsTable.getModel();
        String ClickedUserName = (String) model.getValueAt(i, 0);
        String ClickedFullName = (String) model.getValueAt(i, 1);
        String ClickedUserStatus = (String) model.getValueAt(i, 2);
        
        try { 
            Connection conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs;

            String query = "SELECT * FROM userdb WHERE userName='"+ClickedUserName+"'";
            rs = stmt.executeQuery(query);
            while(rs.next()) {
                if(rs.getBytes("userImage") != null)
                    userProfileImageViewer.setIcon(ResizeImage(rs.getBytes("userImage")));
                else 
                    userProfileImageViewer.setIcon(null);
            }
        } catch(SQLException se) {}
            
        if(ClickedUserStatus.equals("Send Friend Request")) {
            if(JOptionPane.showConfirmDialog(null,"Do You Want To Send Friend Request To "+ClickedFullName) == 0) {
                try {
                    Connection conn = DBConnect.getConnection();
                    Statement stmt = conn.createStatement();
                    ResultSet rs;
                    String AddUserToUserDB = "INSERT INTO "+userName+"_friends VALUES('"+ClickedUserName+"','"+ClickedFullName+"','Friend Request Sent')";
                    stmt.executeUpdate(AddUserToUserDB);
                    
                    AddUserToUserDB = "INSERT INTO "+ClickedUserName+"_friends VALUES('"+userName+"','"+userFullName+"','New Friend Request')";
                    stmt.executeUpdate(AddUserToUserDB);
                } catch(SQLException se) {

                }
            }
        }
        FriendsUserName(userName,SearchBox.getText());
    }//GEN-LAST:event_ViewAllFriendsTableMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        new MainAccount(userFullName,userName).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void ViewAllFriendsTableMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_ViewAllFriendsTableMouseWheelMoved

    }//GEN-LAST:event_ViewAllFriendsTableMouseWheelMoved

    private void ViewAllFriendsTableFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ViewAllFriendsTableFocusGained

    }//GEN-LAST:event_ViewAllFriendsTableFocusGained

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
            java.util.logging.Logger.getLogger(FindFromFriends.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FindFromFriends.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FindFromFriends.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FindFromFriends.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField SearchBox;
    private javax.swing.JTable ViewAllFriendsTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel userProfileImageViewer;
    // End of variables declaration//GEN-END:variables
}
