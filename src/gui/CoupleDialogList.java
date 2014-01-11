/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.awt.FlowLayout;
import java.awt.*;
import javax.swing.*;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import defaults.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Giorgio
 */
public class CoupleDialogList extends JDialog {
   
    ArrayList data,ordinate,singles;
    JButton arrays[];
    int init;
    DialogEdit editDialog;
    boolean classifica;
            
            
    public CoupleDialogList(ArrayList data,boolean classifica){ //Se torneo di singoli
        this.data=data;
        this.classifica=classifica;
        
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); 
        setLayout(new MigLayout("fillx"));
        
        
        init();
        
    }
    
    public CoupleDialogList(ArrayList data,ArrayList singles,boolean classifica){ //Se TOrneo è di coppie
        this.data=data;
        this.singles=singles;
        this.classifica=classifica;
        
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); 
        setLayout(new MigLayout("fillx"));
        
        init();
        
    }
    
    public void init(){
        
      
       int i=0;
       JLabel names,ids,masters,victorys,types;
       names=new JLabel("Giocatori");
       if(!classifica)ids=new JLabel("ID"); else ids=new JLabel("Pos");
       masters=new JLabel("MP");
       victorys=new JLabel("VP");
       types=new JLabel("Tipo");
       
       this.add(ids);
       this.add(types);
       this.add(names);
       this.add(masters);
       this.add(victorys,"wrap");
       
       if(classifica){
           ordinate=new ArrayList(data);
           Collections.sort(ordinate);
           
            for (Object temp1 : ordinate) {
                
                JLabel label,id,master,victory,type;
                boolean tipo;
                
                if(temp1 instanceof Coppia){
           
                    Coppia temp=(Coppia) temp1;
                    label=new JLabel(temp.toString());
                    id=new JLabel(String.valueOf(i+1));
                    master=new JLabel(String.valueOf(temp.getMastPoints()));
                    victory=new JLabel(String.valueOf(temp.getVictPoints()));
                    tipo=temp.getType();
                    
                    if(tipo==true) { type=new JLabel("Mob"); } else { type=new JLabel("Fis"); }

                } else {
                    
                    Single temp=(Single) temp1;
                    label=new JLabel(temp.toString());
                    id=new JLabel(String.valueOf(i+1));
                    master=new JLabel(String.valueOf(temp.getMaster()));
                    victory=new JLabel(String.valueOf(temp.getVictory()));
                    
                    type=new JLabel("Sin");

                    
                }

                this.add(id);
                this.add(type);
                this.add(label);
                this.add(victory);
                this.add(master,"wrap");

                i++;
            }
           
           
           
       } else {
       
            for (Object temp1 : data) {

                JLabel label,id,master,victory,type;
                JButton edit;
                boolean tipo;
                
                if(temp1 instanceof Coppia){
           
                    Coppia temp=(Coppia) temp1;
                    label=new JLabel(temp.toString());
                    id=new JLabel(String.valueOf(i+1));
                    master=new JLabel(String.valueOf(temp.getMastPoints()));
                    victory=new JLabel(String.valueOf(temp.getVictPoints()));
                    tipo=temp.getType();
                    
                    if(tipo==true) { type=new JLabel("Mob"); } else { type=new JLabel("Fis"); }
                    
                    edit=new JButton("Modifica " + temp.getId());
                    
                    
                    
                    final CoupleDialogList questo=this;
                    final int tempF=temp.getId();

                    final ArrayList tempC=data;

                    final int intID=i;

                    ActionListener listener=new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                           JButton text=(JButton) e.getSource();
                           String tempString=text.getText();
                           tempString=tempString.replaceAll("Modifica ", "");

                            closeAndReloadCoppie(Integer.valueOf(tempString));
                        }
                    };

                    edit.addActionListener(listener);

                } else {
                    
                    Single temp=(Single) temp1;
                    label=new JLabel(temp.toString());
                    id=new JLabel(String.valueOf(i+1));
                    master=new JLabel(String.valueOf(temp.getMaster()));
                    victory=new JLabel(String.valueOf(temp.getVictory()));
                    
                    type=new JLabel("Sin");
                    
                    edit=new JButton("Modifica " + temp.getId());

                    
                    
                    
                    final CoupleDialogList questo=this;
                    final int tempF=temp.getId();

                    final ArrayList tempC=data;

                    final int intID=i;

                    ActionListener listener=new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                           JButton text=(JButton) e.getSource();
                           String tempString=text.getText();
                           tempString=tempString.replaceAll("Modifica ", "");

                            closeAndReloadSingle(Integer.valueOf(tempString));
                        }
                    };

                    edit.addActionListener(listener);

                }
                



                this.add(id);
                this.add(type);
                this.add(label);
                this.add(victory);
                this.add(master);
                this.add(edit, "wrap");

                






                i++;
               }

       }
       
       ActionListener terminaListener=new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    end();
                }
            };
       
       JButton termina=new JButton("Termina");
       termina.addActionListener(terminaListener);
       this.add(termina,"span 3");
       
       this.setResizable(false);
       
       this.pack();
       
       this.setVisible(true);
    }
    
    public void closeAndReloadSingle(int id){
        editDialog=new SingleDialogEdit((SingleList) data,id);
        editDialog.setVisible(true);

        editDialog.addPropertyChangeListener("modificato", new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    reload();
                    new CoupleDialogList(data,false);
                    end();
                }
            });
    }
    
    
    public void closeAndReloadCoppie(int id){
        editDialog=new CoupleDialogEdit((SingleList) singles,data,id);
        editDialog.setVisible(true);

        editDialog.addPropertyChangeListener("modificato", new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    reload();
                    new CoupleDialogList(data,singles,false);
                    end();

                }
            });
        
    }
    
    public void end(){
        this.dispose();
    }
    
    public void reload(){
        
    }
    
    
    
    
    
    
    
    
}
