/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import defaults.MainClass;
import javax.swing.*;

/**
 *
 * @author playrom
 */
public class MainGui extends javax.swing.JFrame {
    
    JMenuBar menu;
    JButton addCoppieButtonLoaded,showCouplesLoaded;
    NotStartedPanel panelNotStarted;
    JDialog addCoupleDialog,listCoppie;
    MainClass main;
    
    public MainGui(){
        main=new MainClass();
        main.loadXml("src/defaults/Coppia.xml");
        initComponents();
        
        
    }
    
    
    public void initComponents(){
        panelNotStarted=new NotStartedPanel(main.getCoppie());
        
        this.add(panelNotStarted);
        
        this.pack();
        
    }
    
    
    
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
             System.setProperty("apple.laf.useScreenMenuBar", "true");
                System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Test");
           
            UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GuiMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GuiMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GuiMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GuiMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainGui().setVisible(true);
                
            }
        });
    }
    
    
    
    
}
