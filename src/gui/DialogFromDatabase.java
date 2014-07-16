/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;
import defaults.ConnectDatabase;
import defaults.Coppia;
import defaults.Single;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
import static javax.swing.ListSelectionModel.SINGLE_SELECTION;
import javax.swing.table.TableColumn;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Giorgio
 */
public class DialogFromDatabase extends JDialog{
    
    JTable table;
    ConnectDatabase database;
    public DialogFromDatabase(ConnectDatabase database){
        super();
        this.database=database;
        
        this.setLayout(new MigLayout());
        
        
        ArrayList<Single> singoli=this.database.dumpSingoli();
        
        String[] colonne = {"ID","Nome","Punteggio Medio"};
        final Object[][] data=new Object[singoli.size()][3];
        for(int i=0;i<singoli.size();i++){
            Single temp=singoli.get(i);
            data[i][0]=temp.getId_database();
            data[i][1]=temp.getName();
            data[i][2]=temp.getMedia();
        }
         
        TableFromDatabase model = new TableFromDatabase(data,colonne);

        
        table=new JTable(model);
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               if (e.getClickCount() == 2) {
                  JTable target = (JTable)e.getSource();
                  int row = target.getSelectedRow();
                  // do some action
                  clicked((int)data[row][0]);
                  }
            }
         });
        
        
        TableColumn column = null;
        for (int i = 0; i < 3; i++) {
            column = table.getColumnModel().getColumn(i);
            if (i == 1) {
                column.setPreferredWidth(150); //third column is bigger
            } else if (i == 2) {
                column.setPreferredWidth(100); //third column is bigger
            }else {
                column.setPreferredWidth(50);
            }
        }
        
        //JScrollPane scrollPane = new JScrollPane(table);
        //table.setFillsViewportHeight(true);
        table.setSelectionMode(SINGLE_SELECTION);
        
        this.add(table.getTableHeader(),"wrap,gap 0");
        this.add(table,"gap 0");
        
        this.pack();
        
        this.setVisible(true);
        this.setAlwaysOnTop(true);
        this.setResizable(false);
    }
    
    
    
    private void clicked(int var){
        firePropertyChange("toADD",-1,var);
        this.dispose();

    }
    
}
