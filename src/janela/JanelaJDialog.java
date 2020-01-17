/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package janela;

import com.sun.javafx.tk.Toolkit;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;

/**
 *
 * @author Cleber Nascimento
 */
public class JanelaJDialog {
   private static JDesktopPane jdeskTopPane;

public JanelaJDialog(JDesktopPane jdeskTopPane){
JanelaJDialog.jdeskTopPane = jdeskTopPane;
}
public  static void abrirJanela(JDialog jDialog){
    if (jDialog.isVisible()) {
        jDialog.toFront();
        jDialog.requestFocus();
    }else{
        jdeskTopPane.add(jDialog);
        jDialog.setVisible(true); // abre a tela cadastro (jInternalFrame)
        //centraliza o jInternalFrame dentro do jDesktopPane1
        Dimension d = jDialog.getContentPane().getSize();
        jDialog.setLocation((d.width - jDialog.getSize().width) / 2, 
                                   (d.height - jDialog.getSize().height) / 2);
        }
    }
}
