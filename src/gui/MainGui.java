/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import defaults.MainClass;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.FileFilter;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author playrom
 */
public class MainGui extends javax.swing.JFrame {
    
    JButton addCoppieButtonLoaded,showCouplesLoaded;
    NotStartedPanel panelNotStarted;
    StartedPanel panelStarted;
    JDialog addCoupleDialog,listCoppie;
    JPanel start;
    MainClass main;
    
    JMenuItem aboutMenuItem, contentsMenuItem, copyMenuItem, cutMenuItem, deleteMenuItem,exitMenuItem,openMenuItem,pasteMenuItem,saveAsMenuItem,saveMenuItem;
    JMenu editMenu,fileMenu,helpMenu;
    JMenuBar menuBar;
    
    String filenameSave,filenameOpen;
    
    
    
    public MainGui(){
        main=new MainClass();
        
        this.setLayout(new MigLayout());
        
        main.loadXml("src/defaults/Coppia.xml");
        
        initStart();
        
        this.add(start);
        
        
        initComponents();
        
        
        
        
    }
    
    
    public void initComponents(){
        
        
        
        
        /*panelNotStarted=new NotStartedPanel(main);
        panelStarted=new StartedPanel(main);
        
        panelStarted.setVisible(false);

        panelNotStarted.addComponentListener(compListener());
        
        this.add(panelNotStarted);*/
        
        initMenu();
        
        
        
        
        
        
        
        this.pack();
        
    }
    
    public void initStart(){
        start=new JPanel();
        start.setLayout(new MigLayout());
        JButton carica,nuovo;
        carica=new JButton("Carica Torneo");
        nuovo=new JButton("Nuovo Torneo");
        
        start.add(carica);
        start.add(nuovo,"wrap");
        
        carica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
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
    
    public void initMenu(){
        
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        copyMenuItem = new javax.swing.JMenuItem();
        pasteMenuItem = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentsMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();
        
        
        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        openMenuItem.setMnemonic('o');
        openMenuItem.setText("Open");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(openMenuItem);

        saveMenuItem.setMnemonic('s');
        saveMenuItem.setText("Save");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setMnemonic('a');
        saveAsMenuItem.setText("Save As ...");
        saveAsMenuItem.setDisplayedMnemonicIndex(5);
        saveAsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveAsMenuItem);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Edit");

        cutMenuItem.setMnemonic('t');
        cutMenuItem.setText("Cut");
        editMenu.add(cutMenuItem);

        copyMenuItem.setMnemonic('y');
        copyMenuItem.setText("Copy");
        editMenu.add(copyMenuItem);

        pasteMenuItem.setMnemonic('p');
        pasteMenuItem.setText("Paste");
        editMenu.add(pasteMenuItem);

        deleteMenuItem.setMnemonic('d');
        deleteMenuItem.setText("Delete");
        editMenu.add(deleteMenuItem);

        menuBar.add(editMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Help");

        contentsMenuItem.setMnemonic('c');
        contentsMenuItem.setText("Contents");
        helpMenu.add(contentsMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);
    }
    
    
    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                             
        System.exit(0);
    }  
    
    
    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                             
        if(filenameSave==null){
            saveAsMenuItemActionPerformed(evt);
        } else {
            if(main.isStarted()){
                main.writeXml(filenameSave);
            } else {
                main.saveCouplesPreTournament(filenameSave);
            }
        }
    }                              

    private void saveAsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                             
            final JFileChooser fc = new JFileChooser("src");            // TODO add your handling code here:
            fc.showSaveDialog(panelNotStarted);
            
            
            File file=fc.getSelectedFile();
            filenameSave=file.getAbsolutePath();   
            
            if(main.isStarted()){
                main.writeXml(filenameSave);
            } else {
                main.saveCouplesPreTournament(filenameSave);
            }
            
    }                                            

    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                             
            
            final JFileChooser fc = new JFileChooser("src");            // TODO add your handling code here:
            fc.showDialog(panelNotStarted, null);
            File file=fc.getSelectedFile();
            filenameOpen=file.getAbsolutePath();
            main.loadXml(filenameOpen);
            
            
            load();
            
    }    
    
    public void reload(){
        panelNotStarted.setVisible(false);
    }
    
    public void startPan(){
        this.add(panelStarted);
        panelStarted.setVisible(true);
        this.pack();
    }
    
    public void load(){
        if(main.isStarted()){
            repaint();
            panelStarted=new StartedPanel(main);
            this.add(panelStarted);
            panelStarted.setVisible(true);
            this.pack();
            
        } else {
            repaint();
            panelNotStarted=new NotStartedPanel(main);
            panelNotStarted.setVisible(true);
            this.add(panelNotStarted);
            this.pack();
        }
    }
    
    @Override
    public void repaint(){
        if(panelNotStarted!=null) this.remove(panelNotStarted);
        
        if(panelStarted!=null) this.remove(panelStarted);
        
        if(start!=null) this.remove(start);
        
    }
   
    
    
    ComponentAdapter compListener() {
        
        ComponentAdapter temp=new ComponentAdapter(){
            
            public void componentHidden(ComponentEvent e) {
                startPan();
                System.out.print("ciaoprovaosds");
                
            }
            
            public void componentShown(ComponentEvent e) {
                
            }
        };
        
        return temp;
    }
    
}
