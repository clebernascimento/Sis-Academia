/**
* Sistema de cadastro com acesso restrito
*
* Usado para fazer controle de clientes de uma academia 
*
* @author Cleber-Soft <cleber-soft@hotmail.com>
*
* @version 1.0
* @package SiS - Academy System
* 
* TELA DE ALTERAR LOGIN
*/
package tela;

import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
/**
 *
 * @author Cleber Nascimento
 */
public class TelaAlteraLogin extends javax.swing.JDialog {
    //Criando Variaveis para Conexao com o Banco
    //PreparedStatement e ResultSet sao Frameworks do pacote java.sql
    // e servem para preparar e executar as instruções SQL
    Connection conexao = null;// Usando a variavel conexao da classe ConnectionFactory
    PreparedStatement smt = null;
    ResultSet rs = null;
    
    //VERIFICA SE A SENHA ATUAL DO USUARIO MYSQL CONFERE COM A ATUAL
    private boolean validarSenhaAtual(){
       try{
           boolean validade = ConnectionFactory.getPassword().equals(txtSenhaAtual.getText());
           return validade;
       }catch(Exception e){
           return false;
       }
    }
    //VERIFICA SE HÁ CAMPOS VAZIOS
    private boolean verificarCampos(){
                boolean validade = ((txtNovaSenha.getText().isEmpty())
                |(txtRepitaSenha.getText().isEmpty()));
                return (validade);
        
    }
    //VALIDA A SENHA ATUAL E MOSTRA UMA MENSAGEM
    private void validar(){
        if(validarSenhaAtual()){
            labelSenhaIncorreta.setVisible(false);
            txtNovaSenha.setEnabled(true);
            txtRepitaSenha.setEnabled(true);
            txtNovaSenha.requestFocus();
            txtSenhaAtual.setEnabled(false);
            btnProximo.setEnabled(false);
            btnAlterarSenha.setEnabled(true);
        }else{
            JOptionPane.showMessageDialog(this, "Verifique a Senha Atual Preenchida",
                    "Senha Atual", JOptionPane.INFORMATION_MESSAGE);
            labelSenhaIncorreta.setVisible(true);
            txtSenhaAtual.requestFocus();
        }
    }
    //EXECUTA O METODO UPDATE PARA ATUALIZAÇÃO DO BANCO
    private int executarUpdate() throws Exception{
        String sql = "UPDATE login user SET senha=? WHERE User='root';";
            smt = conexao.prepareStatement(sql);
            smt.setString(1,txtNovaSenha.getText());
            int result = smt.executeUpdate();
            conexao.prepareStatement("FLUSH PRIVILEGES;").execute();
            return result;
    }
    //VALIDA SE A ATUALIZAÇÃO FOI BEM SUCEDIDA
    private void validarUpdate() throws Exception{
            if(executarUpdate()>0){
                JOptionPane.showMessageDialog(this, "Senha Alterada com Sucesso");
            }else{
                JOptionPane.showMessageDialog(this, "Não foi possivel Atualizar a Senha","Senha",JOptionPane.INFORMATION_MESSAGE);
            }
    }
    private boolean validarSenhas(){
        boolean validade = txtNovaSenha.getText().equals(txtRepitaSenha.getText());
        return validade;
    }
    
    //RETORNO DE ERRO PARA A VALIDAÇÃO
    private int retornarErro(){
        int erro=0;
        //VERIFICA CAMPOS VAZIOS
        if(verificarCampos()){
            erro=erro+1;
            //VALIDA SENHAS
        }if(!validarSenhas()){
            erro=erro+5;
        }
        return erro;
    }
    //MOSTRA MENSAGEM COM BASE NO RETORNO DE ERRO
    private boolean validarRetorno(){
        switch (retornarErro()){
            case 0:
               return true;

            case 1:
                JOptionPane.showMessageDialog(this, "Preencha Todos os Campos", 
                        "Campo Vazio", JOptionPane.INFORMATION_MESSAGE);
                labelSenhaIncorreta.setVisible(false);labelSenhaNaoConfere.setVisible(true);
                 return false;
            case 5:
                 JOptionPane.showMessageDialog(this, "Confira a Nova Senha Digitada", 
                        "Nova Senha", JOptionPane.INFORMATION_MESSAGE);
                 labelSenhaIncorreta.setVisible(false);labelSenhaNaoConfere.setVisible(true);
                 return false;
            case 6:
                  JOptionPane.showMessageDialog(this, "Preencha Todos os Campos", 
                        "Campo Vazio", JOptionPane.INFORMATION_MESSAGE);
                 labelSenhaIncorreta.setVisible(false);labelSenhaNaoConfere.setVisible(true);
                 return false;
            default:
                return false;
        }
    }
    //EXECUTA O ALTERA SENHA
    private void AlterarSenha(){
            try {
                    if(validarRetorno()){
                        validarUpdate();
                    }                  
                    
                }
                 catch (Exception e) {
                    //EXIBE A EXCESSAO QUE OCORREU EM CASO DE ERRO NA CONEXAO CONEXAO COM O BANCO
                    JOptionPane.showMessageDialog(null, e.getMessage());
                   }
                }
    
     private void consultar(){
            //CONSULTANDO O BANCO
            String sql = "SELECT *FROM login WHERE usuario like ?" ;
            try {
                smt = conexao.prepareStatement(sql);
                smt.setString(1,txtUsuario.getText()+"%"); 
                rs = smt.executeQuery();
                //condicao para setar os valores do banco 
                if(rs.next()){
                    //seta o valor da tabela no campo
                   // txtCodigo.setText(rs.getString("id"));
                    txtUsuario.setText(rs.getString("usuario"));
            }else{
                JOptionPane.showMessageDialog(null, "Nenhum resultado encontrado! ");
             }
             }
                catch (Exception e) {
                     //retorna provávéis erros
                    JOptionPane.showMessageDialog(null, e);
                }
            }

    /*private void AlterarSenha(){
            String sql = "UPDATE login SET usuario=?,senha=? WHERE id=?";
            try {
                smt = conexao.prepareStatement(sql);
                smt.setString(1, txtUsuario.getText());
                smt.setString(2, txtSenhaAtual.getText());
                smt.setString(3, txtCodigo.getText());
                 // VALIDANDO OS CAMPOS PARA NAO RETORNAR VAZIOS
            if ((txtCodigo.getText().isEmpty())||(txtUsuario.getText().isEmpty())||(txtSenhaAtual.getText().isEmpty())||(txtNovaSenha.getText().isEmpty())||(txtRepitaSenha.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Os campos não podem retornar vazios");
                }else {
                //A ESTRUTURA ABAIXO É USADA PARA CONFIRMAR A ALTERÇÃO DA SENHA NA TABELA
                int adicionando = smt.executeUpdate();
                if(adicionando > 0){
                    //System.out.println(adicionado);
                    JOptionPane.showMessageDialog(null,"Login Alterado Com Sucesso !");
                    // A LINHA ABAIXO LIMPA OS CAMPOS DO FORMULARIO POS ALTERAÇÃO
                    txtCodigo.setText("");
                    txtUsuario.setText("");
                    txtSenhaAtual.setText("");
                    txtNovaSenha.setText("");
                    txtRepitaSenha.setText("");
                } else {
                }
                }
            } catch (Exception e) {
                //EXIBE A EXCESSAO QUE OCORREU EM CASO DE ERRO NA CONEXAO CONEXAO COM O BANCO
                JOptionPane.showMessageDialog(null, e);
            }
            }*/

    /**
     * Creates new form TelaAlteraSenha
     */
    public TelaAlteraLogin(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        conexao = ConnectionFactory.getConnection();//Chama o metodo conexao que estar dentro da classe ConnecionFactory
    }
    
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        labelTitulo = new javax.swing.JLabel();
        panelAlterarSenha = new javax.swing.JPanel();
        labelUsuario = new javax.swing.JLabel();
        labelNovaSenha = new javax.swing.JLabel();
        txtNovaSenha = new javax.swing.JPasswordField();
        btnBuscarDados = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtUsuario = new javax.swing.JTextField();
        labelSenha = new javax.swing.JLabel();
        labelRepitaSenha = new javax.swing.JLabel();
        txtRepitaSenha = new javax.swing.JPasswordField();
        labelSenhaIncorreta = new javax.swing.JLabel();
        labelSenhaNaoConfere = new javax.swing.JLabel();
        txtSenhaAtual = new javax.swing.JPasswordField();
        btnAlterarSenha = new javax.swing.JButton();
        btnProximo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SiS - Academy System - Tela Alterar Senha");

        labelTitulo.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        labelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/SENHA.png"))); // NOI18N
        labelTitulo.setText("ALTERAR SENHA");

        panelAlterarSenha.setBorder(javax.swing.BorderFactory.createTitledBorder("Alterar Senha"));
        panelAlterarSenha.setPreferredSize(new java.awt.Dimension(528, 290));
        panelAlterarSenha.setLayout(new java.awt.GridBagLayout());

        labelUsuario.setText("USUARIO");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(14, 16, 0, 0);
        panelAlterarSenha.add(labelUsuario, gridBagConstraints);

        labelNovaSenha.setText("NOVA SENHA");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(16, 16, 0, 0);
        panelAlterarSenha.add(labelNovaSenha, gridBagConstraints);

        txtNovaSenha.setEnabled(false);
        txtNovaSenha.setMaximumSize(new java.awt.Dimension(167, 28));
        txtNovaSenha.setMinimumSize(new java.awt.Dimension(167, 28));
        txtNovaSenha.setPreferredSize(new java.awt.Dimension(167, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(13, 4, 0, 0);
        panelAlterarSenha.add(txtNovaSenha, gridBagConstraints);

        btnBuscarDados.setText("BUSCAR");
        btnBuscarDados.setToolTipText("Alterar Senha");
        btnBuscarDados.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarDados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarDadosActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(32, 20, 23, 0);
        panelAlterarSenha.add(btnBuscarDados, gridBagConstraints);

        btnCancelar.setText("CANCELAR");
        btnCancelar.setToolTipText("Cancelar");
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 14;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(32, 17, 23, 0);
        panelAlterarSenha.add(btnCancelar, gridBagConstraints);

        txtUsuario.setEditable(false);
        txtUsuario.setBackground(new java.awt.Color(234, 235, 236));
        txtUsuario.setForeground(new java.awt.Color(76, 76, 76));
        txtUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUsuario.setMaximumSize(new java.awt.Dimension(10, 28));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 161;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 4, 0, 0);
        panelAlterarSenha.add(txtUsuario, gridBagConstraints);

        labelSenha.setText("SENHA ATUAL");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(21, 16, 0, 0);
        panelAlterarSenha.add(labelSenha, gridBagConstraints);

        labelRepitaSenha.setText("REPITA A SENHA");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 16, 0, 0);
        panelAlterarSenha.add(labelRepitaSenha, gridBagConstraints);

        txtRepitaSenha.setEnabled(false);
        txtRepitaSenha.setMaximumSize(new java.awt.Dimension(167, 28));
        txtRepitaSenha.setMinimumSize(new java.awt.Dimension(167, 28));
        txtRepitaSenha.setPreferredSize(new java.awt.Dimension(167, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 4, 0, 0);
        panelAlterarSenha.add(txtRepitaSenha, gridBagConstraints);

        labelSenhaIncorreta.setForeground(new java.awt.Color(189, 38, 38));
        labelSenhaIncorreta.setText("Senha Incorreta");
        labelSenhaIncorreta.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        labelSenhaIncorreta.setMaximumSize(new java.awt.Dimension(163, 18));
        labelSenhaIncorreta.setMinimumSize(new java.awt.Dimension(163, 18));
        labelSenhaIncorreta.setPreferredSize(new java.awt.Dimension(163, 18));
        labelSenhaIncorreta.setVisible(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(22, 11, 0, 5);
        panelAlterarSenha.add(labelSenhaIncorreta, gridBagConstraints);

        labelSenhaNaoConfere.setForeground(new java.awt.Color(189, 38, 38));
        labelSenhaNaoConfere.setText("Senhas Não Conferem");
        labelSenhaNaoConfere.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        labelSenhaNaoConfere.setMaximumSize(new java.awt.Dimension(163, 18));
        labelSenhaNaoConfere.setMinimumSize(new java.awt.Dimension(163, 18));
        labelSenhaNaoConfere.setPreferredSize(new java.awt.Dimension(163, 18));
        labelSenhaNaoConfere.setVisible(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(19, 11, 0, 5);
        panelAlterarSenha.add(labelSenhaNaoConfere, gridBagConstraints);

        txtSenhaAtual.setEnabled(false);
        txtSenhaAtual.setFocusCycleRoot(true);
        txtSenhaAtual.setMaximumSize(new java.awt.Dimension(167, 20));
        txtSenhaAtual.setMinimumSize(new java.awt.Dimension(167, 20));
        txtSenhaAtual.setPreferredSize(new java.awt.Dimension(167, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 4, 0, 0);
        panelAlterarSenha.add(txtSenhaAtual, gridBagConstraints);

        btnAlterarSenha.setText("ALTERAR SENHA");
        btnAlterarSenha.setEnabled(false);
        btnAlterarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarSenhaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(32, 13, 23, 0);
        panelAlterarSenha.add(btnAlterarSenha, gridBagConstraints);

        btnProximo.setText("PROXIMO");
        btnProximo.setEnabled(false);
        btnProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProximoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(32, 12, 23, 0);
        panelAlterarSenha.add(btnProximo, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 57, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelAlterarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelAlterarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(561, 399));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarDadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarDadosActionPerformed
        consultar();
        //AtivarCampo();
        txtSenhaAtual.setEnabled(true);
        txtSenhaAtual.requestFocus();
        btnBuscarDados.setEnabled(false);
        btnProximo.setEnabled(true);
    }//GEN-LAST:event_btnBuscarDadosActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProximoActionPerformed
        // TODO add your handling code here:
        if(txtSenhaAtual.isEnabled()){
          validar();
      }
    }//GEN-LAST:event_btnProximoActionPerformed

    private void btnAlterarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarSenhaActionPerformed
        // TODO add your handling code here:
        AlterarSenha();
    }//GEN-LAST:event_btnAlterarSenhaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaAlteraLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaAlteraLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaAlteraLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaAlteraLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TelaAlteraLogin dialog = new TelaAlteraLogin(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterarSenha;
    private javax.swing.JButton btnBuscarDados;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnProximo;
    private javax.swing.JLabel labelNovaSenha;
    private javax.swing.JLabel labelRepitaSenha;
    private javax.swing.JLabel labelSenha;
    private javax.swing.JLabel labelSenhaIncorreta;
    private javax.swing.JLabel labelSenhaNaoConfere;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JPanel panelAlterarSenha;
    private javax.swing.JPasswordField txtNovaSenha;
    private javax.swing.JPasswordField txtRepitaSenha;
    private javax.swing.JPasswordField txtSenhaAtual;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables

}
