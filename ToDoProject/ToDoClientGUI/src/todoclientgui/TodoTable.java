/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todoclientgui;

import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import java.awt.Color;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.core.MediaType;
import todoclientgui.model.Todos;


/**
 *
 * @author pawel
 */
public class TodoTable extends javax.swing.JFrame {
    
    int mousepX;
    int mousepY;
    
    private void getAll() {
        String uri_1;
        if (jCheckBox1.isSelected()) {
            uri_1 = "http://localhost:8080/ToDoApp/webresources/com.home.todos/findbysort/";
        } else {
            uri_1 = "http://localhost:8080/ToDoApp/webresources/com.home.todos/findby/";
        }
        String uri_2 = String.valueOf(Login.user.getId());
        String uri = uri_1 + uri_2;
        
        try {
            Client client = Client.create();
            WebResource webResource = client
                    .resource(uri);
            
            ClientResponse response = webResource
                    .accept(MediaType.APPLICATION_XML)
                .get(ClientResponse.class);
            
            if(response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " 
                        + response.getStatus());
            }
            
            List<Todos> output = response.getEntity(new GenericType<List<Todos>>(){});
            
            DefaultTableModel dm = listToTableModel(output);
            jTable1.setModel(dm); 
            
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(27);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(27);
        
            clearTxt();
                
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } 
                         
    }
    
    private void post(Todos todos) {
        String uri = "http://localhost:8080/ToDoApp/webresources/com.home.todos";
        try {
            Client client = Client.create();
            WebResource webResource = client
                    .resource(uri);

            ClientResponse response = webResource
                .type(MediaType.APPLICATION_XML)
                .post(ClientResponse.class, todos);
            
            if(response.getStatus() < 200 || response.getStatus() > 299) {
                throw new RuntimeException("Failed : HTTP error code : " 
                        + response.getStatus());
            }
            clearTxt();
            getAll();
                
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } 
    }
    
    private void delete(Long id) {
        String uri = "http://localhost:8080/ToDoApp/webresources/com.home.todos/";
        try {
            Client client = Client.create();
            WebResource webResource = client
                    .resource(uri + id);

            ClientResponse response = webResource
                .type(MediaType.APPLICATION_XML)
                .delete(ClientResponse.class);
            
            if(response.getStatus() < 200 || response.getStatus() > 299) {
                throw new RuntimeException("Failed : HTTP error code : " 
                        + response.getStatus());
            }
            clearTxt();
            getAll();
                
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } 
    }
    
    private void update(Todos todos) {
        String uri = "http://localhost:8080/ToDoApp/webresources/com.home.todos/";
        try {
            Client client = Client.create();
            WebResource webResource = client
                    .resource(uri + todos.getId());

            ClientResponse response = webResource
                .type(MediaType.APPLICATION_XML)
                .put(ClientResponse.class, todos);
            
            if(response.getStatus() < 200 || response.getStatus() > 299) {
                throw new RuntimeException("Failed : HTTP error code : " 
                        + response.getStatus());
            }
            clearTxt();
            getAll();
                
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } 
    }
    
    private DefaultTableModel listToTableModel(List<Todos> todosList){
        DefaultTableModel dm = new DefaultTableModel();
        dm.addColumn("Id");
        dm.addColumn("Description");
        dm.addColumn("Date");
        dm.addColumn("Done");
         
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm"); 
        
        todosList.forEach((t) -> {
            String id = String.valueOf(t.getId());
            String description = t.getDescription();
            String date = dateFormat.format(t.getTargetDate());
            String is_done = String.valueOf(t.getIsDone());
            
            dm.addRow(new String[]{id,description,date,is_done});
        });
        
        return dm;
    }
    
    private Todos todosFromControls(){
        Todos todos = new Todos();
        
        todos.setDescription(jTextDescription.getText());
        try {
            todos.setTargetDate(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(jTextDate.getText()) );
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Error format of date");
        }
        todos.setIsDone(Boolean.valueOf(jTextIsDone.getText()));
        todos.setUser(Login.user);
        
        return todos;
    }
    
    private void clearTxt(){
        jTextDescription.setText("");
        jTextDate.setText("");
        jTextIsDone.setText("");
    }
    
    /**
     * Creates new form signup
     */
    public TodoTable() {
        initComponents();
        jLabel11.setBackground(new Color(0,0,0,0));
        jLabel4.setText(Login.user.getUsername());
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(27);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(27);
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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextDescription = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextDate = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jButtonViewAll = new javax.swing.JButton();
        jTextIsDone = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButtonLogout = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonAdd = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ToDo");
        setUndecorated(true);
        setResizable(false);

        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(0, 102, 204));

        jTable1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Description", "Date", "Done"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 393, 538);

        jPanel3.setBackground(new java.awt.Color(0, 102, 204));

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("X");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3);
        jPanel3.setBounds(752, 0, 33, 36);

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("User");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(450, 100, 190, 60);

        jTextDescription.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jTextDescription.setForeground(new java.awt.Color(51, 51, 51));
        jTextDescription.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        jPanel1.add(jTextDescription);
        jTextDescription.setBounds(450, 210, 270, 40);

        jLabel6.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Description");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(450, 190, 90, 16);

        jTextDate.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jTextDate.setForeground(new java.awt.Color(51, 51, 51));
        jTextDate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        jPanel1.add(jTextDate);
        jTextDate.setBounds(450, 290, 270, 40);

        jLabel8.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Date");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(450, 270, 90, 16);

        jButtonViewAll.setBackground(new java.awt.Color(0, 102, 204));
        jButtonViewAll.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jButtonViewAll.setForeground(new java.awt.Color(255, 255, 255));
        jButtonViewAll.setText("View All");
        jButtonViewAll.setBorder(null);
        jButtonViewAll.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonViewAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonViewAllActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonViewAll);
        jButtonViewAll.setBounds(680, 440, 80, 30);

        jTextIsDone.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jTextIsDone.setForeground(new java.awt.Color(51, 51, 51));
        jTextIsDone.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        jPanel1.add(jTextIsDone);
        jTextIsDone.setBounds(450, 370, 270, 40);

        jLabel9.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Is Done");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(450, 350, 90, 16);

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("ToDo");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(480, 30, 210, 60);

        jLabel11.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel11MouseDragged(evt);
            }
        });
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel11MousePressed(evt);
            }
        });
        jPanel1.add(jLabel11);
        jLabel11.setBounds(4, 4, 780, 30);

        jButtonLogout.setBackground(new java.awt.Color(0, 102, 204));
        jButtonLogout.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jButtonLogout.setForeground(new java.awt.Color(255, 255, 255));
        jButtonLogout.setText("Logout");
        jButtonLogout.setBorder(null);
        jButtonLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLogoutActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonLogout);
        jButtonLogout.setBounds(640, 110, 80, 30);

        jButtonUpdate.setBackground(new java.awt.Color(0, 102, 204));
        jButtonUpdate.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jButtonUpdate.setForeground(new java.awt.Color(255, 255, 255));
        jButtonUpdate.setText("Update");
        jButtonUpdate.setBorder(null);
        jButtonUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonUpdate);
        jButtonUpdate.setBounds(500, 440, 80, 30);

        jButtonDelete.setBackground(new java.awt.Color(0, 102, 204));
        jButtonDelete.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jButtonDelete.setForeground(new java.awt.Color(255, 255, 255));
        jButtonDelete.setText("Delete");
        jButtonDelete.setBorder(null);
        jButtonDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonDelete);
        jButtonDelete.setBounds(590, 440, 80, 30);

        jButtonAdd.setBackground(new java.awt.Color(0, 102, 204));
        jButtonAdd.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jButtonAdd.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAdd.setText("Add");
        jButtonAdd.setBorder(null);
        jButtonAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonAdd);
        jButtonAdd.setBounds(410, 440, 80, 30);

        jCheckBox1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(51, 51, 51));
        jCheckBox1.setText("Sort by Date");
        jPanel1.add(jCheckBox1);
        jCheckBox1.setBounds(450, 490, 270, 25);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(785, 538));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel11MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseDragged
        int coordinatesX = evt.getXOnScreen();
        int coordinatesY = evt.getYOnScreen();
        this.setLocation(coordinatesX-mousepX, coordinatesY-mousepY);
    }//GEN-LAST:event_jLabel11MouseDragged

    private void jLabel11MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MousePressed
        mousepX = evt.getX();
        mousepY = evt.getY();
    }//GEN-LAST:event_jLabel11MousePressed

    private void jButtonViewAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonViewAllActionPerformed
        try {
            getAll();
        } catch (Exception e) {
            Logger.getLogger(TodoTable.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_jButtonViewAllActionPerformed

    private void jButtonLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLogoutActionPerformed
        Login form = new Login();
        form.setVisible(true);
        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jButtonLogoutActionPerformed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        Todos todos = todosFromControls();
        try {
            post(todos);
        } catch (Exception e) {
            Logger.getLogger(TodoTable.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        try {
            String description = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString();
            String date = jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString();
            String isDone = jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString();
            
            jTextDescription.setText(description);
            jTextDate.setText(date);
            jTextIsDone.setText(isDone);
        } catch (Exception e) {
            Logger.getLogger(TodoTable.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        String[] options = {"Yes", "No"};
        int answ = JOptionPane.showOptionDialog(null, "Sure To Delete??", "Delete Confirm", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

        if (answ == 0) {
            try {
                int index = jTable1.getSelectedRow();
                Long id = Long.valueOf(jTable1.getValueAt(index, 0).toString());
                delete(id);
                
            } catch (Exception e) {
                Logger.getLogger(TodoTable.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        
        Todos todos = todosFromControls();
        try {
            int index = jTable1.getSelectedRow();
            Long id = Long.valueOf(jTable1.getValueAt(index, 0).toString());
            todos.setId(id);
            update(todos);
            
        } catch (Exception e) {
            Logger.getLogger(TodoTable.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_jButtonUpdateActionPerformed

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
            java.util.logging.Logger.getLogger(TodoTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TodoTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TodoTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TodoTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TodoTable().setVisible(true);
            }
        });
                
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonLogout;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JButton jButtonViewAll;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextDate;
    private javax.swing.JTextField jTextDescription;
    private javax.swing.JTextField jTextIsDone;
    // End of variables declaration//GEN-END:variables
}
