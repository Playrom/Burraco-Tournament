/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import defaults.*;
import defaults.ConnectDatabase;
import defaults.MainClass;
import defaults.SingleList;
import defaults.TorneoDatabase;
import defaults.XmlConnectionToServer;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
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
    JDialog addCoupleDialog,listCoppie,selectType,selectAdvanced;
    JPanel start;
    MainClass main;
    
    
    JMenuItem aboutMenuItem, contentsMenuItem, copyMenuItem, cutMenuItem, deleteMenuItem,exitMenuItem,
            openMenuItem,pasteMenuItem,saveAsMenuItem,saveMenuItem,connectServer,saveToServer;
    JMenu editMenu,fileMenu,helpMenu;
    JMenuBar menuBar;
    
    JLabel numeroTurni,numeroSmazzate,nomeTorneo;
    JButton avanti;
    JTextField  nomeTorneoField,numeroTurniField;
    JComboBox numeroSmazzateCombo;
    
    String filenameSave,filenameOpen;
    
    ArrayList<TorneoDatabase> tornei_from_database;
    
    
    
    public MainGui(){
        main=new MainClass();
        ConnectDatabase database=new ConnectDatabase("all","aicon07","193.183.99.188",3306);

        
        this.setLayout(new MigLayout());
        
        
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
        
        nuovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setAdvancedActionPerformed(evt);
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
            java.util.logging.Logger.getLogger(MainGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
        connectServer = new javax.swing.JMenuItem();
        saveToServer = new javax.swing.JMenuItem();
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
        
        
        connectServer.setMnemonic('c');
        connectServer.setText("Open from Server ...");
        connectServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectServerActionPerformed(evt);
            }
        });
        fileMenu.add(connectServer);
        
        saveToServer.setMnemonic('h');
        saveToServer.setText("Save to Server ...");
        saveToServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveToServerActionPerformed(evt);
            }
        });
        fileMenu.add(saveToServer);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        
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

    private void connectServerActionPerformed(java.awt.event.ActionEvent evt) {                                             
            
         ClientMode socket=new ClientMode("127.0.0.1",8777);
         tornei_from_database=socket.firstConnect("temp-list-tornei.xml");
         System.out.println("client created");
         DialogListTournamentFromDatabase temp=new DialogListTournamentFromDatabase(tornei_from_database);
         temp.addPropertyChangeListener("selectTournament", new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    int value=(Integer) evt.getNewValue();
                    
                    sendRequestToServer(value);
                }
            });
    }
    
    private void sendRequestToServer(int id){
        String path=new String();
        for(TorneoDatabase torneo:tornei_from_database){
            if(torneo.getId()==id) path=torneo.getName_file();
        }
        File requestXml=XmlConnectionToServer.sendSelection(path);
        ClientToSendRequest socket=new ClientToSendRequest("127.0.0.1",8778);
        File tournamentFile=socket.send(requestXml);
        filenameOpen=tournamentFile.getAbsolutePath();
        main.loadXml(filenameOpen);
            
        load();
        
    }
    
    private void saveToServerActionPerformed(java.awt.event.ActionEvent evt){
        if(filenameSave==null){
            saveAsMenuItemActionPerformed(evt);
        } else {
            if(main.isStarted()){
                main.writeXml(filenameSave);
            } else {
                main.saveCouplesPreTournament(filenameSave);
            }
        }
        File file=new File(filenameSave);
        SaveToServerMode socket=new SaveToServerMode("127.0.0.1",8780);
        socket.save(file);
    }
            /*if(main.isStarted()){
                main.writeXml(filenameSave);
            } else {
                main.saveCouplesPreTournament(filenameSave);
            }*/
            
     
    
    private void saveAsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                             
            final JFileChooser fc = new JFileChooser("..");            // TODO add your handling code here:
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
            
            final JFileChooser fc = new JFileChooser("..");            // TODO add your handling code here:
            fc.showDialog(panelNotStarted, null);
            File file=fc.getSelectedFile();
            filenameOpen=file.getAbsolutePath();
            main.loadXml(filenameOpen);
            
            
            load();
            
    }   
    
    private void newTorneoActionPerformed(java.awt.event.ActionEvent evt){
        selectType=new JDialog();
        JLabel text=new JLabel("Il Torneo è composto da coppie o singoli?");
        selectType.setLayout(new MigLayout());
        JButton selectSingle,selectCoppie;
        selectSingle=new JButton("Singoli");
        selectCoppie=new JButton("Coppie");
        
        selectType.add(selectSingle);
        selectType.add(selectCoppie);
        
        selectSingle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newTorneoSingleActionPerformed(evt);
            }
        });
        
        selectCoppie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newTorneoCoppieActionPerformed(evt);
            }
        });
        
        selectType.setAlwaysOnTop(true);
        selectType.pack();
        selectType.setVisible(true);
        
    }
    
    private void newTorneoSingleActionPerformed(java.awt.event.ActionEvent evt){
        main=new MainClass();
        main.initSingles();
        main.setStart(nomeTorneoField.getText(),false,true,Integer.valueOf(numeroTurniField.getText()),Integer.valueOf((String)numeroSmazzateCombo.getSelectedItem()));
        main.startTournamentAndCreate();
        selectType.dispose();
        load();
    }

    
    private void newTorneoCoppieActionPerformed(java.awt.event.ActionEvent evt){
        main=new MainClass();
        main.initCoppie();
        main.initSingles();
        main.setStart(nomeTorneoField.getText(),false,false,Integer.valueOf(numeroTurniField.getText()),Integer.valueOf((String)numeroSmazzateCombo.getSelectedItem()));
        main.startTournamentAndCreate();
        selectType.dispose();
        load();
    }
    
    private void setAdvancedActionPerformed(java.awt.event.ActionEvent evt){
        selectAdvanced=new JDialog();
        
        JLabel text=new JLabel("Inserisci ulteriori informazioni");
        selectAdvanced.setLayout(new MigLayout());
        
        
        
        numeroTurni=new JLabel("Numero Turni: ");
        numeroSmazzate=new JLabel("Numero Smazzate: ");
        nomeTorneo=new JLabel("Nome Torneo: ");
        
        numeroTurniField=new JTextField();
        numeroTurniField.setColumns(1);
        
        nomeTorneoField=new JTextField();
        nomeTorneoField.setColumns(15);
        
        numeroSmazzateCombo=new JComboBox();
        numeroSmazzateCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "3", "4" }));
        numeroSmazzateCombo.setSelectedIndex(0);
        
        avanti=new JButton("Avanti");
        
        selectAdvanced.setAlwaysOnTop(true);

        
        selectAdvanced.add(text,"wrap,align center");
        selectAdvanced.add(nomeTorneo);
        selectAdvanced.add(nomeTorneoField,"wrap");
        selectAdvanced.add(numeroTurni);
        selectAdvanced.add(numeroTurniField,"wrap");
        selectAdvanced.add(numeroSmazzate);
        selectAdvanced.add(numeroSmazzateCombo,"wrap");
        selectAdvanced.add(avanti,"skip");
        
        selectAdvanced.pack();
        
        selectAdvanced.setVisible(true);
        
        avanti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newTorneoActionPerformed(evt);
                selectAdvanced.dispose();
            }
        });
        
    }
    
    
    
    public void reload(){
        panelNotStarted.setVisible(false);
    }
    
    public void startPan(){
        repaint();
        this.add(panelStarted);
        panelStarted.setVisible(true);
        this.pack();
    }
    
    public void load(){
        repaint();
        panelStarted=new StartedPanel(main);
        panelNotStarted=new NotStartedPanel(main);
        panelStarted.setVisible(false);
        panelNotStarted.setVisible(false);

        panelStarted.addPropertyChangeListener("termina", new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    
                }
            });
        
        if(main.isStarted()){
            repaint();
            this.add(panelStarted);
            panelStarted.setVisible(true);
            this.pack();
            
        } else {
            repaint();
            panelNotStarted.setVisible(true);
            
            
            this.add(panelNotStarted);
            panelNotStarted.addComponentListener(compListener());
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
