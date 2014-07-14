/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import exception.ErroreGiaCalcolato;
import javax.swing.*;
import defaults.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.NumberFormat;
import java.util.Properties;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import net.miginfocom.swing.MigLayout;
/**
 *
 * @author playrom
 */
public class PanelTurno extends JPanel {
    
    MainClass main;
    Turno turno;
    JButton modifica,annulla,cancella;
    final JFormattedTextField pun1[],pun2[];
    int idTurno;
    boolean applied=false;
    
    public PanelTurno(MainClass main2,int idTurno2){
        main=main2;
        idTurno=idTurno2;
        setLayout(new MigLayout("fillx"));
        
        JLabel idTavoli,vicpoint1,vicpoint2,vsLabel,punti;
        idTavoli=new JLabel("#");
        vsLabel=new JLabel("Coppie del Tavolo");
        vicpoint1=new JLabel("Vct1");
        vicpoint2=new JLabel("Vct2");
        punti=new JLabel("Punteggi");
        
        modifica=new JButton("Modifica");
        annulla=new JButton("Annulla");
        cancella=new JButton("Cancella Calcolo");
        
        this.add(idTavoli,"align center");
        this.add(vsLabel,"span 3,align center");
        this.add(vicpoint1,"align center");
        this.add(punti,"span 2,align center");
        this.add(vicpoint2,"wrap,align center");
        
        
        turno=(Turno) main.getTorneo().getTurni().get(idTurno);
        
        pun1=new JFormattedTextField[turno.getNumTavoli()];
        pun2=new JFormattedTextField[turno.getNumTavoli()];
        
        
        for(Object temp2 : turno.getTavoli()){
            final Tavolo temp=(Tavolo) temp2;
            
            int uno,due;
            final JLabel tavolo,coppia1,coppia2,vic1,vic2,vs;
            String nomeUno,nomeDue,victory1,victory2;
            
            
            Properties props = new Properties();
            
            props.setProperty("applied", "false");
            

            
            nomeUno=temp.toStringCop1();
            nomeDue=temp.toStringCop2();
            
            final int i=temp.getId();
            
            coppia1=new JLabel(nomeUno);
            coppia2=new JLabel(nomeDue);
            vs=new JLabel("v.s.");
            tavolo=new JLabel(String.valueOf(temp.getId() + 1));
            
            NumberFormat format=NumberFormat.getInstance();
            format.setGroupingUsed(false);
            
            pun1[i]=new JFormattedTextField(format);
            pun2[i]=new JFormattedTextField(format);
            
            //errore la virgola delle migliaia
            
            if(temp.getPun1()==0) pun1[i].setText("0"); else pun1[i].setText(String.valueOf(temp.getPun1()));
            if(temp.getPun2()==0) pun2[i].setText("0"); else pun2[i].setText(String.valueOf(temp.getPun2()));
            
            
            vic1=new JLabel("0");
            vic2=new JLabel("0");
            
            int punte1=Integer.valueOf(pun1[i].getText());
            int punte2=Integer.valueOf(pun2[i].getText());

            int diff=temp.differenza(punte1,punte2);

            if(punte1>punte2){
                vic1.setText(String.valueOf(diff));
                vic2.setText(String.valueOf(20-diff));
            } else {
                vic1.setText(String.valueOf(20-diff));
                vic2.setText(String.valueOf(diff));
            }
            
            if(turno.getCalcolato()) {
                pun1[i].setEditable(false);
                pun2[i].setEditable(false);
            }

            
            pun1[i].setColumns(4);
            pun2[i].setColumns(4);
            
            this.add(tavolo);
            this.add(coppia1);
            this.add(vs);
            this.add(coppia2);
            this.add(vic1);
            this.add(pun1[i]);
            this.add(pun2[i]);
            this.add(vic2,"wrap");
            
            pun1[i].addPropertyChangeListener(new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent e) {
                    
                    update();
                    
                    }
                
                public void update(){
                    
                    //pun1[i].setValue(Integer.valueOf(toFive(Integer.valueOf(pun1[i].getText()))));
                    //pun2[i].setValue(Integer.valueOf(toFive(Integer.valueOf(pun2[i].getText()))));
                    
                    int punteggi1,punteggi2;
                    String temp1,temp2;
                    
                    try{
                        temp1=pun1[i].getText();
                        temp2=pun2[i].getText();
                        punteggi1=Integer.valueOf(pun1[i].getText());
                        punteggi2=Integer.valueOf(pun2[i].getText());
                    }catch(NumberFormatException e){
                        
                        
                    }finally{
                        temp1=pun1[i].getText();
                        temp2=pun2[i].getText();
                        temp1.replaceAll("[^\\d.]", "");
                        temp2.replaceAll("[^\\d.]", "");
                        punteggi1=Integer.valueOf(temp1);
                        punteggi2=Integer.valueOf(temp2);
                        pun1[i].setText(String.valueOf(punteggi1));
                        pun2[i].setText(String.valueOf(punteggi2));
                    }
                    
                    int differenza=temp.differenza(punteggi1,punteggi2);

                    if(punteggi1>punteggi2){
                        vic1.setText(String.valueOf(differenza));
                        vic2.setText(String.valueOf(20-differenza));
                    } else {
                        vic1.setText(String.valueOf(20-differenza));
                        vic2.setText(String.valueOf(differenza));
                    }
                }
                
                public int toFive(int numero){
                    return (int) (Math.round(numero / 5.0) * 5);
                }

            });
            
            pun2[i].addPropertyChangeListener(new PropertyChangeListener(){
                public void propertyChange(PropertyChangeEvent e) {
                    
                    update();
                    
                    }
                
                public void update(){
                    
                    //pun1[i].setValue(Integer.valueOf(toFive(Integer.valueOf(pun1[i].getText()))));
                    //pun2[i].setValue(Integer.valueOf(toFive(Integer.valueOf(pun2[i].getText()))));
                    int punteggi1,punteggi2;
                    String temp1,temp2;
                    
                    try{
                        temp1=pun1[i].getText();
                        temp2=pun2[i].getText();
                        punteggi1=Integer.valueOf(pun1[i].getText());
                        punteggi2=Integer.valueOf(pun2[i].getText());
                    }catch(NumberFormatException e){
                        
                        
                    }finally{
                        temp1=pun1[i].getText();
                        temp2=pun2[i].getText();
                        temp1.replaceAll("[^\\d]", "" );
                        temp2.replaceAll("[^\\d]", "" );
                        punteggi1=Integer.valueOf(temp1);
                        punteggi2=Integer.valueOf(temp2);
                        pun1[i].setText(String.valueOf(punteggi1));
                        pun2[i].setText(String.valueOf(punteggi2));
                    }
                    
                    int differenza=temp.differenza(punteggi1,punteggi2);

                    if(punteggi1>punteggi2){
                        vic1.setText(String.valueOf(differenza));
                        vic2.setText(String.valueOf(20-differenza));
                    } else {
                        vic1.setText(String.valueOf(20-differenza));
                        vic2.setText(String.valueOf(differenza));
                    }
                }
                
                public int toFive(int numero){
                    return (int) (Math.round(numero / 5.0) * 5);
                }

            });
            
            /*pun1[i].getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                    update();
                }
                public void removeUpdate(DocumentEvent e) {
                    
                }
                
                public void insertUpdate(DocumentEvent e) {
                    update();
                }   
                
                
                
        });
            
            pun2[i].getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                    update();
                }
                public void removeUpdate(DocumentEvent e) {
                    
                }
                public void insertUpdate(DocumentEvent e) {
                    update();
                }   
                
                
                public void update(){
                    int punteggi1=Integer.valueOf(pun1[i].getText().replaceAll(",", ""));
                    int punteggi2=Integer.valueOf(pun2[i].getText().replaceAll(",", ""));

                    int differenza=temp.differenza(punteggi1,punteggi2);

                    if(punteggi1>punteggi2){
                        vic1.setText(String.valueOf(differenza));
                        vic2.setText(String.valueOf(20-differenza));
                    } else {
                        vic1.setText(String.valueOf(20-differenza));
                        vic2.setText(String.valueOf(differenza));
                    }
                }
        });*/
            
            
            
        }
        
        annulla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annullaActionPerformed(evt);
            }
        });
        
        modifica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificaActionPerformed(evt);
            }
        });
        
        cancella.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancellaActionPerformed(evt);
            }
        });
        
        this.add(annulla,"skip 5");
        this.add(cancella);
        this.add(modifica);
        
    }
    
    
    public void annullaActionPerformed(ActionEvent e){
            firePropertyChange("annulla",false,true);
    }
    
    public void cancellaActionPerformed(ActionEvent e){
        for(int k=0;k<turno.getTavoli().length;k++){
                turno.getTavolo(k).annullaPunteggi();
                //this.dispose();
                pun1[k].setEditable(true);
                pun2[k].setEditable(true);
            }
            turno.setCalcolato(false);
            firePropertyChange("calcolato",true,false);
    }
    
    public void modificaActionPerformed(ActionEvent e){
        try{
            turno.checkCalcolato();
            for(int k=0;k<turno.getTavoli().length;k++){
                turno.getTavolo(k).assegnaPunteggi(Integer.valueOf(pun1[k].getText()), Integer.valueOf(pun2[k].getText()));
                //this.dispose();
                pun1[k].setEditable(false);
                pun2[k].setEditable(false);
            }
            turno.setCalcolato(true);
            firePropertyChange("calcolato",false,true);
           
        }catch(ErroreGiaCalcolato t){
            for(int k=0;k<turno.getTavoli().length;k++){
                turno.getTavolo(k).annullaPunteggi();
                turno.getTavolo(k).assegnaPunteggi(Integer.valueOf(pun1[k].getText()), Integer.valueOf(pun2[k].getText()));
                //this.dispose();
            }
            turno.setCalcolato(true);
            firePropertyChange("calcolato",false,true);
        }
    }
    
    

}
