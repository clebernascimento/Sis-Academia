/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.Component;
import javax.swing.JTable;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Cleber Nascimento
 */
public class TabelaAlinhada extends DefaultTableCellRenderer {
	public TabelaAlinhada() {
		super();
	}
        @Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		this.setHorizontalAlignment(CENTER);
		return super.getTableCellRendererComponent(table, value, isSelected,
				hasFocus, row, column);
	}
}