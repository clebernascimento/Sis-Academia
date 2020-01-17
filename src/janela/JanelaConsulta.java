/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package janela;

import java.awt.Dimension;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 *
 * @author Cleber Nascimento
 */
public class JanelaConsulta {
    
private static JDesktopPane jdeskTopPane;

public JanelaConsulta(JDesktopPane jdeskTopPane){
JanelaConsulta.jdeskTopPane = jdeskTopPane;
}
public  static void abrirJanela(JInternalFrame jInternalFrame){
    if (jInternalFrame.isVisible()) {
        jInternalFrame.toFront();
        jInternalFrame.requestFocus();
    }else{
        jdeskTopPane.add(jInternalFrame);
        jInternalFrame.setVisible(true); // abre a tela cadastro (jInternalFrame)
        //centraliza o jInternalFrame dentro do jDesktopPane1
        Dimension d = jInternalFrame.getDesktopPane().getSize();
        jInternalFrame.setLocation((d.width - jInternalFrame.getSize().width) / 2, 
                                   (d.height - jInternalFrame.getSize().height) / 2);
        }
    }
}
