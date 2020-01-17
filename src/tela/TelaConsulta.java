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
* TELA DE CONSULTA
*/
package tela;

import Modelo.ApenasLetras;
import Modelo.ApenasLetrasCaracteres;
import Modelo.SomenteNumeros;
import factory.ConnectionFactory;
import Modelo.TabelaAlinhada;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Cleber Nascimento
 */
public class TelaConsulta extends javax.swing.JDialog {
    //Criando Variaveis para Conexao com o Banco
    //PreparedStatement e ResultSet sao Frameworks do pacote java.sql
    // e servem para preparar e executar as instruções SQL
    Connection conexao = null;// Usando a variavel conexao da classe ConnectionFactory
    PreparedStatement smt = null;
    ResultSet rs = null;
    
     //METODO PARA LIMPAR OS DADOS DA TABELA
     private void limpaTabela(){
        while (jTabela.getModel().getRowCount() > 0) {  
           ((DefaultTableModel) jTabela.getModel()).removeRow(0);  
       } 
     }
     private void LimparCampos(){
         //LIMPA TODOS OS CAMPOS APOS CLICAR NO BOTAO LIMPAR
        for(int i = 0; i < jPanel2.getComponentCount(); i++)
            if(jPanel2.getComponent(i) instanceof JTextField)((JTextField) jPanel2.getComponent(i)).setText(""); 
             txtCampoPesquisar.setText("");// LIMPA O CAMPO PESQUISAR
             txtObservacao.setText("");//LIMPA O CAMPO OBSERVAÇÃO
     }
         //METODO PARA FAZER A CONSULTA NO BANCO
        private void consultar(){
            //CONSULTANDO O BANCO
            String sql = "SELECT * FROM Cliente WHERE nome like ?";
            try {
                smt = conexao.prepareStatement(sql);
                smt.setString(1,txtCampoPesquisar.getText() + "%");
                rs = smt.executeQuery();
                //jTabela.setModel(DbUtils.resultSetToTableModel(rs));//USANDO A BIBLIOTECA RS2XML PARA PREENCHER A TABELA
               DefaultTableModel modelo =(DefaultTableModel) jTabela.getModel();
                modelo.setNumRows(0);
                 while(rs.next()){
                    modelo.addRow(new Object[]{rs.getString("id"),rs.getString("nome")});
                }
            } catch (Exception e) {
                //EXIBE A EXCESSAO QUE OCORREU EM CASO DE ERRO NA CONEXAO CONEXAO COM O BANCO
                JOptionPane.showMessageDialog(null, "Não à conexao com o banco de dados");
            }
            }
        //METODO PARA PREENCHER OS CAMPOS DO FORMULARIOS COM O CONTEUDO DO CAMPO
        private void preencherCampo() throws SQLException{
            int preencher = jTabela.getSelectedRow();
            String id = String.valueOf(jTabela.getModel().getValueAt(preencher, 0)).trim();
            String sql = "SELECT * FROM Cliente WHERE id=?";
            smt = conexao.prepareStatement(sql);
            smt.setString(1,id);
            rs = smt.executeQuery();
            rs.next();
            //SETA OS CAMPOS COM OS VALORES DO BANCO
            txtId.setText(rs.getString("id"));
            txtNome.setText(rs.getString("nome"));
            txtNascimento.setText(rs.getString("nascimento"));
            txtIdade.setText(rs.getString("idade"));
            txtSexo.setText(rs.getString("sexo"));
            txtRG.setText(rs.getString("rg"));
            txtFixo.setText(rs.getString("fixo"));
            txtTelefone.setText(rs.getString("telefone"));
            txtEmail.setText(rs.getString("email"));
            txtEndereco.setText(rs.getString("endereco"));
            txtBairro.setText(rs.getString("bairro"));
            txtNumero.setText(rs.getString("numero"));
            txtComplemento.setText(rs.getString("complemento"));
            txtCep.setText(rs.getString("cep"));
            txtIdade.setText(rs.getString("idade"));
            txtData.setText(rs.getString("inicio"));
            txtObservacao.setText(rs.getString("observacao"));
            txtAltura.setText(rs.getString("altura"));
            txtPeso.setText(rs.getString("peso"));
            txtGluteo.setText(rs.getString("gluteo"));
            txtPeito.setText(rs.getString("peito"));
            txtCintura.setText(rs.getString("cintura"));
            txtBracoDireito.setText(rs.getString("braco_direito"));
            txtBracoEsquerdo.setText(rs.getString("braco_esquerdo"));
            txtPernaDireita.setText(rs.getString("perna_direita"));
            txtPernaEsquerda.setText(rs.getString("perna_esquerda"));
        }
         //METODO PARA ALTERAR CLIENTE NO BANCO
        private void alterar(){
            if(jTabela.getSelectedRow() != -1){
                int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja alterar este cliente ?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "UPDATE Cliente SET nome=?,nascimento=?,idade=?,sexo=?,rg=?,fixo=?,telefone=?,email=?,endereco=?,bairro=?,numero=?,complemento=?,cep=?,inicio=?,observacao=?,altura=?,peso=?,gluteo=?,peito=?,cintura=?,braco_direito=?,braco_esquerdo=?,perna_direita=?,perna_esquerda=? WHERE id=?";
            try {
                smt = conexao.prepareStatement(sql);
                smt.setString(1, txtNome.getText());
                smt.setString(2, txtNascimento.getText());
                smt.setString(3, txtIdade.getText());
                smt.setString(4, txtSexo.getText());
                smt.setString(5, txtRG.getText());
                smt.setString(6, txtFixo.getText());
                smt.setString(7, txtTelefone.getText());
                smt.setString(8, txtEmail.getText());
                smt.setString(9, txtEndereco.getText());
                smt.setString(10, txtBairro.getText());
                smt.setString(11, txtNumero.getText());
                smt.setString(12, txtComplemento.getText());
                smt.setString(13, txtCep.getText());
                smt.setString(14, txtData.getText());
                //smt.setString(14, jDate.getDateFormatString());
                smt.setString(15, txtObservacao.getText());
                smt.setString(16, txtAltura.getText());
                smt.setString(17, txtPeso.getText());
                smt.setString(18, txtGluteo.getText());
                smt.setString(19, txtPeito.getText());
                smt.setString(20, txtCintura.getText());
                smt.setString(21, txtBracoDireito.getText());
                smt.setString(22, txtBracoEsquerdo.getText());
                smt.setString(23, txtPernaDireita.getText());
                smt.setString(24, txtPernaEsquerda.getText());
                smt.setString(25, txtId.getText());
            
                 // VALIDANDO OS CAMPOS PARA NAO RETORNAR VAZIOS
                if ((txtNome.getText().isEmpty()) || (txtNascimento.getText().isEmpty()) 
                    || (txtIdade.getText().isEmpty())
                    /*|| (jComboBoxSexo.getSelectedItem().isEmpty())*/ || (txtRG.getText().isEmpty())
                    || (txtFixo.getText().isEmpty()) || (txtTelefone.getText().isEmpty())
                    || (txtEmail.getText().isEmpty()) || (txtEndereco.getText().isEmpty()) 
                    || (txtBairro.getText().isEmpty()) || (txtNumero.getText().isEmpty())
                    || (txtComplemento.getText().isEmpty()) || (txtCep.getText().isEmpty()) 
                    || (txtData.getText().isEmpty())
                    || (txtObservacao.getText().isEmpty()) || (txtAltura.getText().isEmpty())
                    || (txtPeso.getText().isEmpty()) || (txtGluteo.getText().isEmpty()) || (txtPeito.getText().isEmpty())
                    || (txtCintura.getText().isEmpty()) || (txtBracoDireito.getText().isEmpty())
                    || (txtBracoEsquerdo.getText().isEmpty()) || (txtPernaDireita.getText().isEmpty())
                    || (txtPernaEsquerda.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Os campos não podem retornar vazios");
                }else {
                //A LINHA ABAIXO ATULIZA A TABELA USUARIO COM OS DADOS DO FORMULARIO
                //A ESTRUTURA ABAIXO É USADA PARA CONFIRMAR A ALTERÇÃO DOS DADOS NA TABELA
                int adicionando = smt.executeUpdate();
                if(adicionando > 0){
                    //System.out.println(adicionado);
                    JOptionPane.showMessageDialog(null,"Dados do Cliente Atualizado com Sucesso !");
                    // A LINHA ABAIXO LIMPA OS CAMPOS DO FORMULARIO POS ALTERAÇÃO
                    LimparCampos();
                } else {
                }
                }
            } catch (Exception e) {
                //EXIBE A EXCESSAO QUE OCORREU EM CASO DE ERRO NA CONEXAO CONEXAO COM O BANCO
                JOptionPane.showMessageDialog(null, e);
            }
            }
            }else{
                JOptionPane.showMessageDialog(null,"Selecione um Cliente !!","Cliente não selecionado",
                  JOptionPane.INFORMATION_MESSAGE);
             }
        }
         //METODO PARA REMOVER DADOS DO CLIENTE
        private void remover() {
        //A ESTRUTURA A BAIXO CONFIRMA A REMOÇÃO DO CLIENTE
        if(jTabela.getSelectedRow() != -1){   
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este cliente ?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM Cliente WHERE id=?";
            try {
                smt = conexao.prepareStatement(sql);
                smt.setString(1, txtId.getText());
                //A LINHA ABAIXO ATULIZA A TABELA USUARIO COM OS DADOS DO FORMULARIO
                //A ESTRUTURA ABAIXO É USADA PARA CONFIRMAR A ALTERÇÃO DOS DADOS NA TABELA
                int apagado = smt.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Cliente Removido com sucesso");
                    // A LINHA ABAIXO LIMPA OS CAMPOS DO FORMULARIO POS ALTERAÇÃO
                     LimparCampos();
                }
            } catch (Exception e) {
                //EXIBE A EXCESSAO QUE OCORREU EM CASO DE ERRO NA CONEXAO CONEXAO COM O BANCO
                JOptionPane.showMessageDialog(null, e);

            }
        }
        }else{
             JOptionPane.showMessageDialog(null,"Selecione um Cliente !!","Cliente não selecionado",
                  JOptionPane.INFORMATION_MESSAGE);
        }
    }
           
    /**
     * Creates new form TelaConsulta
     */
    public TelaConsulta(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        conexao = ConnectionFactory.getConnection();//Chama o metodo conexao que estar dentro da classe ConnecionFactory
        //METODO PARA SELECIONAR TODAS AS LINHAS DA TABELA
        jTabela.setCellSelectionEnabled(false); //Precisa estar false! 
        jTabela.setRowSelectionAllowed(true);
        //METODO PARA ALINHAR A TABELA (COLUNAS E LINHAS)
        jTabela.setDefaultRenderer(Object.class, new TabelaAlinhada());
        //METODO PARA DIGITAR SOMENTES NUMEROS NOS CAMPOS
        txtAltura.setDocument(new SomenteNumeros(4));
        txtPeso.setDocument(new SomenteNumeros(5));
        txtGluteo.setDocument(new SomenteNumeros(5));
        txtPeito.setDocument(new SomenteNumeros(5));
        txtCintura.setDocument(new SomenteNumeros(5));
        txtBracoDireito.setDocument(new SomenteNumeros(5));
        txtBracoEsquerdo.setDocument(new SomenteNumeros(5));
        txtPernaDireita.setDocument(new SomenteNumeros(5));
        txtPernaEsquerda.setDocument(new SomenteNumeros(5));
        txtIdade.setDocument(new SomenteNumeros(2));
        txtNumero.setDocument(new SomenteNumeros(4));
        //METODO PARA DIGITAR SOMENTES LETRAS NOS CAMPOS
        txtNome.setDocument(new ApenasLetras());
        txtEndereco.setDocument(new ApenasLetras());
        txtBairro.setDocument(new ApenasLetras());
        txtComplemento.setDocument(new ApenasLetras());
        //txtObservacao.setDocument(new ApenasLetras());
        //METODO PARA DIGITAR APENAS LETRAS E CARACTERES(-, _ , . , @) NO CAMPO
        txtEmail.setDocument(new ApenasLetrasCaracteres());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtCampoPesquisar = new javax.swing.JTextField();
        btnConsultar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabela = new javax.swing.JTable();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtData = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtIdade = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtEndereco = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtAltura = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtPeso = new javax.swing.JTextField();
        jDate = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtGluteo = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtPeito = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtCintura = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtBracoDireito = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtBracoEsquerdo = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtPernaDireita = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtPernaEsquerda = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtSexo = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtBairro = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtComplemento = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservacao = new javax.swing.JTextArea();
        txtNascimento = new javax.swing.JFormattedTextField();
        txtFixo = new javax.swing.JFormattedTextField();
        txtTelefone = new javax.swing.JFormattedTextField();
        txtCep = new javax.swing.JFormattedTextField();
        txtRG = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SiS - Academy System - Tela Consulta");

        jLabel1.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/consulta.png"))); // NOI18N
        jLabel1.setText("CONSULTAR/LISTAR CLIENTE");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Listar Cliente"));

        jLabel7.setText("CONSULTAR POR NOME");

        txtCampoPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCampoPesquisarKeyReleased(evt);
            }
        });

        btnConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/listar.png"))); // NOI18N
        btnConsultar.setToolTipText("Listar Cliente");
        btnConsultar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/limpar.png"))); // NOI18N
        btnLimpar.setToolTipText("Limpar Campos");
        btnLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/sair2.png"))); // NOI18N
        btnSair.setToolTipText("Sair");
        btnSair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        jTabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "NOME"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTabela.setColumnSelectionAllowed(true);
        jTabela.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTabela.getTableHeader().setReorderingAllowed(false);
        jTabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabelaMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTabelaMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTabela);
        jTabela.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTabela.getColumnModel().getColumnCount() > 0) {
            jTabela.getColumnModel().getColumn(0).setResizable(false);
            jTabela.getColumnModel().getColumn(1).setResizable(false);
        }

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/editar.png"))); // NOI18N
        btnEditar.setToolTipText("Editar Cadastro");
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/excluir.png"))); // NOI18N
        btnExcluir.setToolTipText("Excluir Cadastro");
        btnExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtCampoPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(btnConsultar)
                .addGap(6, 6, 6)
                .addComponent(btnEditar)
                .addGap(6, 6, 6)
                .addComponent(btnExcluir)
                .addGap(6, 6, 6)
                .addComponent(btnLimpar)
                .addGap(6, 6, 6)
                .addComponent(btnSair)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel7)
                        .addGap(6, 6, 6)
                        .addComponent(txtCampoPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnConsultar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações do Cliente"));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("NOME");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("DATA/INICIO");

        txtData.setEditable(false);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("IDADE");

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("ENDEREÇO");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("TEL/CELULAR");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("ALTURA");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("PESO");

        jDate.setEnabled(false);

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("CODIGO");

        txtId.setEditable(false);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("GLUTEO");

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("PEITO");

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("CINTURA");

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("BRAÇO-DIREITO");

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("BRAÇO-ESQUERDO");
        jLabel13.setToolTipText("Braço-Esquerdo");

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("PERNA-DIREITA");

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("PERNA-ESQUERDA");
        jLabel15.setToolTipText("Perna-Esquerda");

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("NASCIMENTO");

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("SEXO");

        txtSexo.setEditable(false);

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("RG");

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("TEL/FIXO");

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText("E-MAIL");

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("BAIRRO");

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setText("NUMERO");

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel25.setText("COMPLEMENTO");
        jLabel25.setToolTipText("Complemento");

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel26.setText("CEP");

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel29.setText("OBSERVAÇÃO");
        jLabel29.setToolTipText("Observação");

        txtObservacao.setColumns(20);
        txtObservacao.setRows(5);
        jScrollPane2.setViewportView(txtObservacao);

        try {
            txtNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txtFixo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txtCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txtRG.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#######-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNome)
                            .addComponent(txtNascimento)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtSexo)
                                .addComponent(txtIdade)
                                .addComponent(txtRG, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
                            .addComponent(txtFixo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefone, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtBairro)
                                    .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDate, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(103, 103, 103)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPernaDireita, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGluteo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAltura, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPeito, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCintura, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBracoDireito, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBracoEsquerdo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPernaEsquerda, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel2)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(txtNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel4))
                            .addComponent(txtIdade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addComponent(jLabel19)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel20))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(txtSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(txtRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(txtFixo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel24)
                                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel25)
                                    .addComponent(txtComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel26)
                                    .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(9, 9, 9))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(txtAltura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtGluteo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtPeito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtCintura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBracoDireito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBracoEsquerdo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPernaDireita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPernaEsquerda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(314, 314, 314)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCampoPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCampoPesquisarKeyReleased
        //EVENTO PARA PESQUISAR EM TEMPO REAL O NOME DO CLIENTE AO DIGITAR NO CAMPO DE TEXTO (txtCampoPesquisar)
        consultar();//CHAMANDO O METODO CONSULTAR
    }//GEN-LAST:event_txtCampoPesquisarKeyReleased

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        //EVENTO PARA CONSTULTAR CLIENTE PREENCHENDO A TABELA AO CLICAR NO BOTAO CONSULTA
        consultar();//CHAMANDO O METODO CONSULTAR()
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        // AÇÃO DO BOTÃO LIMPAR
        limpaTabela();//CHAMANDO METODO PARA LIMPAR A TABELA AO CLICAR NO BOTAO LIMPAR
        //LIMPA TODOS OS CAMPOS APOS CLICAR NO BOTAO LIMPAR
        LimparCampos();
    }//GEN-LAST:event_btnLimparActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        // AÇÃO DO BOTÃO SAIR
        this.dispose();//FECHA APENAS A JANELA DE CONSULTA
        limpaTabela();//CHAMANDO METODO PARA LIMPAR A TABELA AO CLICAR NO BOTAO LIMPAR
         //LIMPA TODOS OS CAMPOS APOS CLICAR NO BOTAO LIMPAR
        LimparCampos();
    }//GEN-LAST:event_btnSairActionPerformed

    private void jTabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabelaMouseClicked
        // EVENTO PARA PREECHER OS CAMPOS COM O CONTEUDO DA TABELA CLICANDO COM O MOUSE
        try {
            preencherCampo();// CHAMANDO O METODO PARA PREENCHER OS CAMPOS
        }catch(SQLException e){
           JOptionPane.showMessageDialog(null,e.getMessage(),"SQLException",
                   JOptionPane.INFORMATION_MESSAGE);
       }
    }//GEN-LAST:event_jTabelaMouseClicked

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        //EVENTO PARA ALTERAR CLIENTE
        alterar();//CHAMANDO METODO ALTERAR
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        //EVENTO PARA REMOVER DADOS DO CLEINTE
        remover();//CHAMANDO METODO REMOVER
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void jTabelaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabelaMousePressed
        // EVENTO PARA NAO EDITAR A TABELA
        jTabela.editingCanceled(null);// DEIXA A TABELA NAO EDITAVEL
    }//GEN-LAST:event_jTabelaMousePressed

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
            java.util.logging.Logger.getLogger(TelaConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TelaConsulta dialog = new TelaConsulta(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnSair;
    private com.toedter.calendar.JDateChooser jDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTabela;
    private javax.swing.JTextField txtAltura;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtBracoDireito;
    private javax.swing.JTextField txtBracoEsquerdo;
    private javax.swing.JTextField txtCampoPesquisar;
    private javax.swing.JFormattedTextField txtCep;
    private javax.swing.JTextField txtCintura;
    private javax.swing.JTextField txtComplemento;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JFormattedTextField txtFixo;
    private javax.swing.JTextField txtGluteo;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdade;
    private javax.swing.JFormattedTextField txtNascimento;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextArea txtObservacao;
    private javax.swing.JTextField txtPeito;
    private javax.swing.JTextField txtPernaDireita;
    private javax.swing.JTextField txtPernaEsquerda;
    private javax.swing.JTextField txtPeso;
    private javax.swing.JFormattedTextField txtRG;
    private javax.swing.JTextField txtSexo;
    private javax.swing.JFormattedTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}