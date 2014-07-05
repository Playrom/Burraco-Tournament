/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import defaults.ClientMode;
import defaults.ConnectDatabase;
import defaults.Single;
import defaults.SingleList;
import defaults.TorneoDatabase;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JTable;
import static javax.swing.ListSelectionModel.SINGLE_SELECTION;
import javax.swing.table.TableColumn;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author playrom
 */
public class DialogListTournamentFromDatabase extends JDialog{
   
    
    JTable table;
    
    public DialogListTournamentFromDatabase(ArrayList<TorneoDatabase> tornei){
        super();
        
        this.setLayout(new MigLayout());
        
        
        
        String[] colonne = {"ID","Nome"};
        Object[][] data=new Object[tornei.size()][2];
        int k=0;
        for(TorneoDatabase torneo:tornei){
            
            data[k][0]=torneo.getId();
            data[k][1]=torneo.getName();
            k++;
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
                  clicked(row+1);
                  }
            }
         });
        
        
        TableColumn column = null;
        for (int i = 0; i < 2; i++) {
            column = table.getColumnModel().getColumn(i);
            if (i == 1) {
                column.setPreferredWidth(150); //third column is bigger
            } else {
                column.setPreferredWidth(100); //third column is bigger
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
    
    public static void main (String Args[]) 
    {
        ClientMode socket=new ClientMode("127.0.0.1",8777);
        ArrayList<TorneoDatabase> tornei=socket.firstConnect("tornei-dialog-temp.xml");
        DialogListTournamentFromDatabase q=new DialogListTournamentFromDatabase(tornei);
        
    }
    
    private void clicked(int var){
        firePropertyChange("selectTournament",-1,var);
        this.dispose();

    }
    

}
