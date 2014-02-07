/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Giorgio
 */
public class TableFromDatabase  extends DefaultTableModel {

   public TableFromDatabase(Object[][] tableData, Object[] colNames) {
      super(tableData, colNames);
   }

   public boolean isCellEditable(int row, int column) {
      return false;
   }
}