/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
/**
 *
 * @author Cleber Nascimento
 */
public class TelaAlteraSenha extends javax.swing.JDialog {
    //Criando Variaveis para Conexao com o Banco
    //PreparedStatement e ResultSet sao Frameworks do pacote java.sql
    // e servem para preparar e executar as instruções SQL
    Connection conexao = null;// Usando a variavel conexao da classe ConnectionFactory
    PreparedStatement smt = null;
    
    public TelaAlteraSenha(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        conexao = ConnectionFactory.getConnection();//Chama o metodo conexao que estar dentro da classe ConnecionFactory
    }
   //VERIFICA SE AS SENHAS DO CAMPO NOVA SENHA E REPITA SENHA SÃO IGUAIS
   
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
                btnAlterarSenha.setText("Alterar");
        }else{
            JOptionPane.showMessageDialog(this, "Verifique a Senha Atual Preenchida",
                    "Senha Atual", JOptionPane.INFORMATION_MESSAGE);
            labelSenhaIncorreta.setVisible(true);
            txtSenhaAtual.requestFocus();
        }
    }
    //EXECUTA O METODO UPDATE PARA ATUALIZAÇÃO DO BANCO
    private int executarUpdate() throws Exception{
        String sql = "UPDATE mysql.user SET authentication_string=PASSWORD(?) WHERE User='administrador';";
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
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        labelTitulo = new javax.swing.JLabel();
        panelAlterarSenha = new javax.swing.JPanel();
        labelUsuario = new javax.swing.JLabel();
        labelNovaSenha = new javax.swing.JLabel();
        txtNovaSenha = new javax.swing.JPasswordField();
        btnAlterarSenha = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtUsuario = new javax.swing.JTextField();
        labelSenha = new javax.swing.JLabel();
        labelRepitaSenha = new javax.swing.JLabel();
        txtRepitaSenha = new javax.swing.JPasswordField();
        labelSenhaIncorreta = new javax.swing.JLabel();
        labelSenhaNaoConfere = new javax.swing.JLabel();
        txtSenhaAtual = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SiS - Academy System - Tela Alterar Senha");
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        labelTitulo.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        labelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/SENHA.png"))); // NOI18N
        labelTitulo.setText("ALTERAR SENHA");

        panelAlterarSenha.setBorder(javax.swing.BorderFactory.createTitledBorder("Alterar Senha"));
        panelAlterarSenha.setPreferredSize(new java.awt.Dimension(528, 290));

        labelUsuario.setText("USUARIO");

        labelNovaSenha.setText("NOVA SENHA");

        txtNovaSenha.setEnabled(false);
        txtNovaSenha.setMaximumSize(new java.awt.Dimension(167, 28));
        txtNovaSenha.setMinimumSize(new java.awt.Dimension(167, 28));
        txtNovaSenha.setPreferredSize(new java.awt.Dimension(167, 20));

        btnAlterarSenha.setText("PROXIMO");
        btnAlterarSenha.setToolTipText("Alterar Senha");
        btnAlterarSenha.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAlterarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarSenhaActionPerformed(evt);
            }
        });

        btnCancelar.setText("CANCELAR");
        btnCancelar.setToolTipText("Cancelar");
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        txtUsuario.setEditable(false);
        txtUsuario.setBackground(new java.awt.Color(234, 235, 236));
        txtUsuario.setForeground(new java.awt.Color(76, 76, 76));
        txtUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUsuario.setText("ADMINISTRADOR");
        txtUsuario.setMaximumSize(new java.awt.Dimension(10, 28));

        labelSenha.setText("SENHA ATUAL");

        labelRepitaSenha.setText("REPITA A SENHA");

        txtRepitaSenha.setEnabled(false);
        txtRepitaSenha.setMaximumSize(new java.awt.Dimension(167, 28));
        txtRepitaSenha.setMinimumSize(new java.awt.Dimension(167, 28));
        txtRepitaSenha.setPreferredSize(new java.awt.Dimension(167, 20));

        labelSenhaIncorreta.setForeground(new java.awt.Color(189, 38, 38));
        labelSenhaIncorreta.setText("senha incorreta");
        labelSenhaIncorreta.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        labelSenhaIncorreta.setMaximumSize(new java.awt.Dimension(163, 18));
        labelSenhaIncorreta.setMinimumSize(new java.awt.Dimension(163, 18));
        labelSenhaIncorreta.setPreferredSize(new java.awt.Dimension(163, 18));
        labelSenhaIncorreta.setVisible(false);

        labelSenhaNaoConfere.setForeground(new java.awt.Color(189, 38, 38));
        labelSenhaNaoConfere.setText("senhas não conferem");
        labelSenhaNaoConfere.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        labelSenhaNaoConfere.setMaximumSize(new java.awt.Dimension(163, 18));
        labelSenhaNaoConfere.setMinimumSize(new java.awt.Dimension(163, 18));
        labelSenhaNaoConfere.setPreferredSize(new java.awt.Dimension(163, 18));
        labelSenhaNaoConfere.setVisible(false);

        txtSenhaAtual.setFocusCycleRoot(true);
        txtSenhaAtual.setMaximumSize(new java.awt.Dimension(167, 20));
        txtSenhaAtual.setMinimumSize(new java.awt.Dimension(167, 20));
        txtSenhaAtual.setPreferredSize(new java.awt.Dimension(167, 20));

        javax.swing.GroupLayout panelAlterarSenhaLayout = new javax.swing.GroupLayout(panelAlterarSenha);
        panelAlterarSenha.setLayout(panelAlterarSenhaLayout);
        panelAlterarSenhaLayout.setHorizontalGroup(
            panelAlterarSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAlterarSenhaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAlterarSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelNovaSenha)
                    .addComponent(labelUsuario)
                    .addComponent(labelSenha)
                    .addComponent(labelRepitaSenha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelAlterarSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtRepitaSenha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSenhaAtual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNovaSenha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 50, Short.MAX_VALUE)
                .addGroup(panelAlterarSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelSenhaNaoConfere, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelSenhaIncorreta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(panelAlterarSenhaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAlterarSenha)
                .addGap(43, 43, 43)
                .addComponent(btnCancelar)
                .addGap(150, 150, 150))
        );
        panelAlterarSenhaLayout.setVerticalGroup(
            panelAlterarSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAlterarSenhaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAlterarSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelUsuario)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelAlterarSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSenha)
                    .addComponent(labelSenhaIncorreta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSenhaAtual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(panelAlterarSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNovaSenha)
                    .addComponent(txtNovaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSenhaNaoConfere, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(panelAlterarSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelRepitaSenha)
                    .addComponent(txtRepitaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(panelAlterarSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnAlterarSenha))
                .addContainerGap(73, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelAlterarSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                    .addComponent(labelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelAlterarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new java.awt.GridBagConstraints());

        setSize(new java.awt.Dimension(505, 404));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAlterarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarSenhaActionPerformed
      //SE O FOCU ESTAVA EM SENHA ATUAL EXECUTE VALIDAR, SENÃO EXECUTE ALTERAR SENHA
        if(txtSenhaAtual.isEnabled()){
          validar();
      }else{
          AlterarSenha();
      }
    }//GEN-LAST:event_btnAlterarSenhaActionPerformed
   
public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows Classic".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaAlteraSenha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaAlteraSenha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaAlteraSenha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaAlteraSenha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TelaAlteraSenha dialog = new TelaAlteraSenha(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnCancelar;
    private javax.swing.JPanel jPanel1;
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
