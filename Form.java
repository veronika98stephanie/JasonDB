/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FormPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class Form extends javax.swing.JFrame {
    
    DefaultListModel dlm = new DefaultListModel();
    Connection con;
    Statement stmt;
    ResultSet rs;
    Float gpa;
    String name;
    String idNumber;
    String query = "SELECT * FROM studentsform";
    String newID;
    int curRow = 1;
    int choice = 0;
    int counter = 0;
    boolean rightInput = false;
    boolean rightGpa = false;

    /**
     * Creates new form Form
     */
    public Form() {
        initComponents();
        createConnection();
        display();
        displayJlist();
        init();
    }
    
    private void init(){
        txtName.setText("");
        txtID.setText("");
        txtGpa.setText("");
        txtName.setEnabled(false);
        txtID.setEnabled(false);
        txtGpa.setEnabled(false);
        btnSave.setEnabled(false);
        btnCancel.setEnabled(false);
    }
    
    private void addData() throws Exception {
        try{           
            createConnection();
            name = txtName.getText();
            
            if(Float.parseFloat(txtGpa.getText()) < 4.0 && 
                    Float.parseFloat(txtGpa.getText())>0){
                gpa = Float.parseFloat(txtGpa.getText());
                rightGpa = true;
            }
            else{
                rightGpa = false;
                Exception Exception = null;
                throw Exception;
            }
            
            if (txtID.getText().matches("[0-9]{10}")){
                idNumber = txtID.getText();
                rightInput = true;
            }
            else{
                rightInput = false;
                Exception Exception = null;
                throw Exception;
            }
            
            String query1 = "INSERT INTO studentsform VALUES ('" + idNumber + 
                    "','" + name + "'," +  gpa+ ")";
            stmt.executeUpdate(query1);
            rs = stmt.executeQuery(query);
            rs.last();
            
            displayJlist();
            System.out.println(txtGpa.getText());
            //dlm.addElement(rs.getString("name"));
            //listName.setModel(dlm);
            con.close();
        }catch(SQLException err){
            JOptionPane.showMessageDialog(this, err.getMessage());
        }
    }
    private void updateData() throws Exception{
        try{   
            createConnection();
            name = txtName.getText();
            
            if(Float.parseFloat(txtGpa.getText()) < 4.0 && 
                    Float.parseFloat(txtGpa.getText())>0){
                gpa = Float.parseFloat(txtGpa.getText());
                rightGpa = true;
            }
            else{
                rightGpa = false;
                Exception Exception = null;
                throw Exception;
            }
            
            if (txtID.getText().matches("[0-9]{10}")){
                idNumber = txtID.getText();
                rightInput = true;
            }
            else{
                rightInput = false;
                Exception Exception = null;
                throw Exception;
            }
            //UPDATE studentsform SET name = 'name', studentId = 'id', GPA = gpa WHERE studentId = newID;
            String query1 = "UPDATE studentsForm SET name = '" + name + 
                    "',studentsId ='" + idNumber + "', GPA =" + gpa + 
                    " WHERE studentsId='" + newID + "'";
            stmt.executeUpdate(query1);
            rs = stmt.executeQuery(query);
            rs.last();
            displayJlist();
            System.out.println(txtGpa.getText());
            //dlm.addElement(rs.getString("name"));
            //listName.setModel(dlm);
            con.close();
            rightInput = true;
           
        }catch(SQLException err){
            JOptionPane.showMessageDialog(this, err.getMessage());
        }
    }
    private void deleteData(){
        try{
            createConnection();
            name = txtName.getText();
            gpa = Float.parseFloat(txtGpa.getText());
            idNumber = txtID.getText();
            String query1 = "DELETE FROM studentsForm " + "WHERE studentsId = '" + newID + " ' ";
            System.out.println(query1);
            stmt.executeUpdate(query1);
            rs = stmt.executeQuery(query);
            rs.last();
            displayJlist();
            con.close();
            rightInput = true;
            rightGpa = true;
        } catch(SQLException err){
            JOptionPane.showMessageDialog(this, err.getMessage());
        }
    }
    
    //create connection
    private void createConnection(){
        try{
            String user = "root";
            String connectionString = "jdbc:mysql://localhost:3306/formdb?zeroDateTimeBehavior=convertToNull";
            String password = "";
            
            con = DriverManager.getConnection(connectionString,user,password);
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(query);
            
            //display();
        }catch(SQLException err){
            JOptionPane.showMessageDialog(this, err.getMessage());
        }
        
    }

    public final void displayJlist(){
        try{
            
            rs.absolute(0);
            counter = 0;
            dlm.clear();
            while(rs.next()){
                dlm.addElement(rs.getString("name"));
                listName.setModel(dlm);
                counter++;
            }
           
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    public void display(){
        try{
            
            rs.absolute(curRow);
            
            name = rs.getString("name");
            idNumber = rs.getString("StudentsID");
            gpa = rs.getString("gpa");

            newID = idNumber;
            
            txtName.setText(name);
            txtGpa.setText(gpa);
            txtID.setText(idNumber);
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        lblStudentInfo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listName = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        lblPhoto = new javax.swing.JLabel();
        lblStudentID = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblGPA = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtGpa = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnFirst = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblStudentInfo.setFont(new java.awt.Font("Yu Gothic Medium", 1, 36)); // NOI18N
        lblStudentInfo.setText("STUDENTS' INFO");

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setToolTipText("");
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        listName.setFont(new java.awt.Font("Tw Cen MT", 0, 24)); // NOI18N
        listName.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listNameMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(listName);

        jPanel1.setBorder(new javax.swing.border.MatteBorder(null));

        lblPhoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPhoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FormPackage/9 Dolls.jpg"))); // NOI18N
        lblPhoto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblPhoto.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                lblPhotoComponentResized(evt);
            }
        });

        lblStudentID.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblStudentID.setText("Student ID: ");

        lblName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblName.setText("Name       :");

        lblGPA.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblGPA.setText("GPA         :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblStudentID))
                    .addComponent(lblName)
                    .addComponent(lblGPA))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtGpa)
                    .addComponent(txtName)
                    .addComponent(txtID))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblStudentID)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblName))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtGpa, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblGPA))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnFirst.setText("First");
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnLast.setText("Last");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLast, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnFirst, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblStudentInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblStudentInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
        );

        pack();
    }// </editor-fold>              
    
    private void setButton1(){
        btnSave.setEnabled(true);
        btnCancel.setEnabled(true);
        listName.setEnabled(false);
        txtName.setEnabled(true);
        txtID.setEnabled(true);
        txtGpa.setEnabled(true);
        btnAdd.setEnabled(false);
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
        btnFirst.setEnabled(false);
        btnLast.setEnabled(false);
    }
    
    private void setButton2(){
        btnSave.setEnabled(false);
        btnCancel.setEnabled(false);
        listName.setEnabled(true);
        txtName.setEnabled(false);
        txtID.setEnabled(false);
        txtGpa.setEnabled(false);
        btnAdd.setEnabled(true);
        btnUpdate.setEnabled(true);
        btnDelete.setEnabled(true);
        btnFirst.setEnabled(true);
        btnLast.setEnabled(true);
    }
    
    private void setButton3(){
        btnSave.setEnabled(true);
        btnCancel.setEnabled(true);
        listName.setEnabled(true);
        txtName.setEnabled(true);
        txtID.setEnabled(true);
        txtGpa.setEnabled(true);
        btnAdd.setEnabled(false);
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
        btnFirst.setEnabled(false);
        btnLast.setEnabled(false);
    }
    
    
    
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        choice = 1;
        txtName.setText("");
        txtID.setText("");
        txtGpa.setText("");
        setButton1();
        
    }//GEN-LAST:event_btnAddActionPerformed

    private void listNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listNameMouseClicked
        // TODO add your handling code here:
        createConnection();
        curRow = listName.getSelectedIndex()+ 1;
        display();        
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }//GEN-LAST:event_listNameMouseClicked

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        try{
            createConnection();
            rs.first();
            curRow = rs.getRow();
            listName.setSelectedIndex(0);
            
            display();
            con.close();
        }catch(SQLException err){
            JOptionPane.showMessageDialog(this, err.getMessage());
        }
        
    }//GEN-LAST:event_btnFirstActionPerformed

    private void lblPhotoComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_lblPhotoComponentResized
        // TODO add your handling code here:
        
    }//GEN-LAST:event_lblPhotoComponentResized

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
        try{
            createConnection();
            rs.last();
            curRow = rs.getRow();
            listName.setSelectedIndex(counter-1);
            display();
            con.close();
        }catch(SQLException err){
            JOptionPane.showMessageDialog(this, err.getMessage());
        }
    }  
    
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
        while(choice == 1){
            try {
                addData();
                break;
            } catch (SQLException ex) {
                Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Please input the right one"
                 + "\nStudentID: 10 digits number\n Name : less than 30 char\n "
                        + "GPA : less than 4 more than 0, format ex: 3.33");
                break;
            }               
        }
        while(choice == 2){
            try {
                updateData();
                break;
            } catch (SQLException ex) {
                Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Please input the right one"
                 + "\nStudentID: 10 digits number\n Name : less than 30 char\n "
                        + "GPA : less than 4 more than 0, format ex: 3.33");
                break;
            }      
        }
        while(choice == 3){
            try {
                deleteData();
                txtName.setText("");
                txtID.setText("");
                txtGpa.setText("");
                break;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "There is nothing to be deleted");
                break;
            }      
        }
        
        if(rightInput && rightGpa){
            setButton2();
            rightInput = false;
            rightGpa = false;
        }else{
            setButton1();
        }
    }
    
    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
        txtName.setText("");
        txtID.setText("");
        txtGpa.setText("");
        setButton2();
        createConnection();
        display();
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
        choice = 2;
        setButton3();
    }                                         

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
        choice = 3;
        setButton3();
    }     
    
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
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblGPA;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPhoto;
    private javax.swing.JLabel lblStudentID;
    private javax.swing.JLabel lblStudentInfo;
    private javax.swing.JList<String> listName;
    private javax.swing.JTextField txtGpa;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
