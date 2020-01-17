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
* TELA DE CADASTRO
*/
package tela;

import Modelo.ApenasLetras;
import Modelo.ApenasLetrasCaracteres;
import Modelo.SomenteNumeros;
import factory.ConnectionFactory;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Cleber Nascimento
 */
public class TelaCadastro extends javax.swing.JDialog {
 //Criando Variaveis para Conexao com o Banco
    //PreparedStatement e ResultSet sao Frameworks do pacote java.sql
    // e servem para preparar e executar as instruções SQL
    Connection conexao = null;// Usando a variavel conexao da classe ConnectionFactory
    java.sql.PreparedStatement smt = null;
    ResultSet rs = null;
   
      //METODO PARA HABITAR OS CAMPOS
    private void AtivarCampo(){
       //Ação do botao novo para habilitar os campos desabilitados
        txtId.setEnabled(false); // ao clicar no botao NOVO desativa o campo ID
        txtNome.setEnabled(true);
        txtNascimento.setEnabled(true);
        txtIdade.setEnabled(true);
        jComboBoxSexo.setEnabled(true);
        txtRG.setEnabled(true);
        txtFixo.setEnabled(true);
        txtTelefone.setEnabled(true);
        txtEmail.setEnabled(true);
        txtEndereco.setEnabled(true);
        txtBairro.setEnabled(true);
        txtNumero.setEnabled(true);
        txtComplemento.setEnabled(true);
        txtCep.setEnabled(true);
        txtObservacao.setEnabled(true);
        txtAltura.setEnabled(true);
        txtPeso.setEnabled(true);
        txtGluteo.setEnabled(true);
        txtPeito.setEnabled(true);
        txtCintura.setEnabled(true);
        txtBracoDireito.setEnabled(true);
        txtBracoEsquerdo.setEnabled(true);
        txtPernaDireita.setEnabled(true);
        txtPernaEsquerda.setEnabled(true);
        btnCadastrar.setEnabled(true);
        btnLimpar.setEnabled(true);
        btnNovo.setEnabled(false);//DESABILITA O BOTAO NOVO ATE QUE SEJA EFETUADO UM NOVO CADASTRO
    }
    //METODO PARA DESABILITAR OS CAMPOS
    private void DesativarCampo(){
        // quando clicar no botao cadastrar ele desativa os campos para um novo cadastro
        txtId.setEnabled(true); // ao clicar no botao cadastrar ativa o campo ID
        txtNome.setEnabled(false);
        txtNascimento.setEnabled(false);
        txtIdade.setEnabled(false);
        jComboBoxSexo.setEnabled(false);
        txtRG.setEnabled(false);
        txtFixo.setEnabled(false);
        txtTelefone.setEnabled(false);
        txtEmail.setEnabled(false);
        txtEndereco.setEnabled(false);
        txtBairro.setEnabled(false);
        txtNumero.setEnabled(false);
        txtComplemento.setEnabled(false);
        txtCep.setEnabled(false);
        txtObservacao.setEnabled(false);
        txtAltura.setEnabled(false);
        txtPeso.setEnabled(false);
        txtGluteo.setEnabled(false);
        txtPeito.setEnabled(false);
        txtCintura.setEnabled(false);
        txtBracoDireito.setEnabled(false);
        txtBracoEsquerdo.setEnabled(false);
        txtPernaDireita.setEnabled(false);
        txtPernaEsquerda.setEnabled(false);
        btnCadastrar.setEnabled(false);
        btnLimpar.setEnabled(false);
        btnNovo.setEnabled(true);//HABILITA O BOTAO NOVO PARA NOVO CADASTRO
    }
    //METODO PARA FORMATAR A DATA DO JCALENDAR NO TXTCAMPO E INSERIR 
    //AUTOMATICAMENTE COM A DATA DO SISTEMA
    private void data(){
        //DateFormat df = DateFormat.getInstance();
        SimpleDateFormat formatador = new SimpleDateFormat("yyyy.MM.dd");
        Date dataSistema = new Date();
        jDate.setDate(dataSistema);
        txtData.setText(formatador.format(dataSistema));
    }
    //METODO PARA LIMPAR CAMPOS
    private void LimparCampos(){
         //LIMPA TODOS OS CAMPOS APOS CLICAR NO BOTAO LIMPAR
        for(int i = 0; i < jPanel1.getComponentCount(); i++)
            if(jPanel1.getComponent(i) instanceof JTextField)((JTextField) jPanel1.getComponent(i)).setText(""); 
        for(int i = 0; i < jPanel2.getComponentCount(); i++)
            if(jPanel2.getComponent(i) instanceof JTextField)((JTextField) jPanel2.getComponent(i)).setText(""); 
        jComboBoxSexo.setSelectedItem("Selecionar");// ao clcar no botao limpar o item volta para o primeiro item
        txtObservacao.setText("");
    }
    
    //método para adicionar clientes
    private void adicionar() {
     
        //INSERINDO NO BANCO
        String sql = "INSERT INTO cliente(nome,nascimento,idade,sexo,rg,fixo,telefone,email,endereco,bairro,numero,complemento,cep,inicio,observacao,altura,peso,gluteo,peito,cintura,braco_direito,braco_esquerdo,perna_direita,perna_esquerda) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            smt = conexao.prepareStatement(sql);
            smt.setString(1, txtNome.getText());
            smt.setString(2, txtNascimento.getText());
            smt.setString(3, txtIdade.getText());
            smt.setString(4, (String) jComboBoxSexo.getSelectedItem());
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
           
            //VALIDANDO OS CAMPOS PARA NAO RETORNAR VAZIOS
            if ((txtNome.getText().isEmpty()) || (txtNascimento.getText().isEmpty()) || (txtIdade.getText().isEmpty())
                    || (jComboBoxSexo.getItemCount() == 0) || (txtRG.getText().isEmpty())
                    || (txtFixo.getText().isEmpty()) || (txtTelefone.getText().isEmpty())
                    || (txtEmail.getText().isEmpty()) || (txtEndereco.getText().isEmpty()) 
                    || (txtBairro.getText().isEmpty()) || (txtNumero.getText().isEmpty())
                    || (txtComplemento.getText().isEmpty()) || (txtCep.getText().isEmpty())
                    || (txtNome.getText().isEmpty()) || (txtData.getText().isEmpty())
                    || (txtObservacao.getText().isEmpty()) || (txtAltura.getText().isEmpty())
                    || (txtPeso.getText().isEmpty()) || (txtGluteo.getText().isEmpty()) || (txtPeito.getText().isEmpty())
                    || (txtCintura.getText().isEmpty()) || (txtBracoDireito.getText().isEmpty())
                    || (txtBracoEsquerdo.getText().isEmpty()) || (txtPernaDireita.getText().isEmpty())
                    || (txtPernaEsquerda.getText().isEmpty())) {
            JOptionPane.showMessageDialog(null, "Os campos não podem retornar vazios");
            } else {
                //a linha abaixo atualiza a tabela com os dados do formulário
                //a etrutura abaixo é usada para confirmar a inserção dos dados na tabela
                int adicionado = smt.executeUpdate();
                
                //a linha abaixo serve de apoio ao entendimento da lógica
                if (adicionado > 0) {
                    //System.out.println(adicionado);
                    JOptionPane.showMessageDialog(null, "Cliente Cadastrado com Sucesso");//MENSAGEM DE CADASTRO COM SUCESSO
                    //LIMPA OS CAMPOS APOS CADASTRO REALIZADO
                   LimparCampos();
                }
            }
        } catch (Exception e) {
            //EXIBE A EXCESSAO QUE OCORREU EM CASO DE ERRO NA CONEXAO CONEXAO COM O BANCO
            JOptionPane.showMessageDialog(null, e);
        }
    }
    /**
     * Creates new form TelaCadastro1
     */
    public TelaCadastro(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        conexao = ConnectionFactory.getConnection();//Chama o metodo conexao que estar dentro da classe ConnecionFactory
        
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
        txtObservacao.setDocument(new ApenasLetras());
        
        ////METODO PARA DIGITAR APENAS LETRAS E CARACTERES(-, _ , . , @) NO CAMPO
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
        jLabelIdade = new javax.swing.JLabel();
        txtIdade = new javax.swing.JTextField();
        jLabelCelular = new javax.swing.JLabel();
        txtTelefone = new javax.swing.JFormattedTextField();
        jLabelNome = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabelEndereco = new javax.swing.JLabel();
        txtEndereco = new javax.swing.JTextField();
        jLabelCodigo = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabelInicio = new javax.swing.JLabel();
        jDate = new com.toedter.calendar.JDateChooser();
        txtData = new javax.swing.JTextField();
        jLabelBairro = new javax.swing.JLabel();
        txtBairro = new javax.swing.JTextField();
        jLabelNumero = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        jLabelComplemento = new javax.swing.JLabel();
        txtComplemento = new javax.swing.JTextField();
        jLabelCep = new javax.swing.JLabel();
        txtCep = new javax.swing.JFormattedTextField();
        jLabelEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabelNascimento = new javax.swing.JLabel();
        txtNascimento = new javax.swing.JFormattedTextField();
        jLabelSexo = new javax.swing.JLabel();
        jComboBoxSexo = new javax.swing.JComboBox<>();
        jLabelRg = new javax.swing.JLabel();
        txtRG = new javax.swing.JFormattedTextField();
        jLabelFixo = new javax.swing.JLabel();
        txtFixo = new javax.swing.JFormattedTextField();
        jLabelObeservacao = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservacao = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabelAltura = new javax.swing.JLabel();
        jLabelPeso = new javax.swing.JLabel();
        jLabelGluteo = new javax.swing.JLabel();
        jLabelPeito = new javax.swing.JLabel();
        jLabelCintura = new javax.swing.JLabel();
        txtAltura = new javax.swing.JTextField();
        txtPeso = new javax.swing.JTextField();
        txtGluteo = new javax.swing.JTextField();
        txtPeito = new javax.swing.JTextField();
        txtCintura = new javax.swing.JTextField();
        jLabelBracoDireito = new javax.swing.JLabel();
        jLabelBracoEsquerdo = new javax.swing.JLabel();
        jLabelPernaEsquerda = new javax.swing.JLabel();
        jLabelPernaDireita = new javax.swing.JLabel();
        txtBracoDireito = new javax.swing.JTextField();
        txtBracoEsquerdo = new javax.swing.JTextField();
        txtPernaEsquerda = new javax.swing.JTextField();
        txtPernaDireita = new javax.swing.JTextField();
        btnCadastrar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SiS - Academy System - Tela Cadastro");

        jLabel1.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        jLabel1.setForeground(java.awt.Color.darkGray);
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/cadastro3.png"))); // NOI18N
        jLabel1.setText("CADASTRO DE CLIENTE");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Informções do Cliente"));
        jPanel1.setFocusable(false);

        jLabelIdade.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelIdade.setText("IDADE");

        txtIdade.setEnabled(false);

        jLabelCelular.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelCelular.setText("TELEFONE CELULAR");

        try {
            txtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtTelefone.setEnabled(false);

        jLabelNome.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelNome.setText("NOME");

        txtNome.setEnabled(false);

        jLabelEndereco.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelEndereco.setText("ENDEREÇO");

        txtEndereco.setEnabled(false);

        jLabelCodigo.setText("CODIGO");

        jLabelInicio.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelInicio.setText("DATA/INICIO");

        jDate.setEnabled(false);

        txtData.setEnabled(false);

        jLabelBairro.setText("BAIRRO");

        txtBairro.setEnabled(false);

        jLabelNumero.setText("NUMERO");

        txtNumero.setEnabled(false);

        jLabelComplemento.setText("COMPLEMENTO");

        txtComplemento.setEnabled(false);

        jLabelCep.setText("CEP");

        try {
            txtCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCep.setEnabled(false);

        jLabelEmail.setText("E-MAIL");

        txtEmail.setEnabled(false);

        jLabelNascimento.setText("DATA / NASCIMENTO");

        try {
            txtNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtNascimento.setEnabled(false);

        jLabelSexo.setText("SEXO");

        jComboBoxSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecionar", "MASCULINO", "FEMININO" }));
        jComboBoxSexo.setEnabled(false);

        jLabelRg.setText("RG (Identidade)");

        try {
            txtRG.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#######-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtRG.setEnabled(false);

        jLabelFixo.setText("TELEFONE FIXO");

        try {
            txtFixo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFixo.setEnabled(false);

        jLabelObeservacao.setText("OBSERVAÇÃO");

        txtObservacao.setTabSize(0);
        txtObservacao.setEnabled(false);
        jScrollPane2.setViewportView(txtObservacao);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("FOTO");
        jLabel2.setToolTipText("FOTO");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton1.setText("IMAGEM");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabelNome)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelNascimento)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelIdade)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdade, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabelSexo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelRg)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtRG, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelFixo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFixo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelCelular)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabelCodigo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelEmail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelEndereco)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelBairro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabelNumero)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelComplemento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelCep)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelInicio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(48, 48, 48))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabelObeservacao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelCodigo)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelNome)
                            .addComponent(jLabelNascimento)
                            .addComponent(txtNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelIdade)
                            .addComponent(txtIdade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelSexo)
                            .addComponent(jComboBoxSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelRg)
                            .addComponent(txtRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelFixo)
                            .addComponent(txtFixo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelCelular)
                            .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelEmail)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelEndereco)
                            .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelBairro)
                            .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelNumero)
                                .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelComplemento)
                                .addComponent(txtComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelCep)
                                .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelInicio)
                                .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelObeservacao)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Definição"));

        jLabelAltura.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelAltura.setText("ALTURA");

        jLabelPeso.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelPeso.setText("PESO(Kg)");

        jLabelGluteo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelGluteo.setText("GLUTEO");

        jLabelPeito.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelPeito.setText("PEITO");

        jLabelCintura.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelCintura.setText("CINTURA");

        txtAltura.setEnabled(false);

        txtPeso.setEnabled(false);

        txtGluteo.setEnabled(false);

        txtPeito.setEnabled(false);

        txtCintura.setEnabled(false);

        jLabelBracoDireito.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelBracoDireito.setText("BRAÇO - DIREITO");

        jLabelBracoEsquerdo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelBracoEsquerdo.setText("BRAÇO - ESQUERDO");

        jLabelPernaEsquerda.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelPernaEsquerda.setText("PERNA - ESQUERDA");

        jLabelPernaDireita.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelPernaDireita.setText("PERNA - DIREITA");

        txtBracoDireito.setEnabled(false);

        txtBracoEsquerdo.setEnabled(false);

        txtPernaEsquerda.setEnabled(false);

        txtPernaDireita.setEnabled(false);

        btnCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/salvar.png"))); // NOI18N
        btnCadastrar.setToolTipText("Cadastrar");
        btnCadastrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCadastrar.setEnabled(false);
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/limpar.png"))); // NOI18N
        btnLimpar.setToolTipText("Limpar Campos");
        btnLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpar.setEnabled(false);
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

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/novo.png"))); // NOI18N
        btnNovo.setToolTipText("Novo Cadatsro");
        btnNovo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabelAltura)
                .addGap(4, 4, 4)
                .addComponent(txtAltura, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabelPeso)
                .addGap(4, 4, 4)
                .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabelGluteo)
                .addGap(4, 4, 4)
                .addComponent(txtGluteo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabelPeito)
                .addGap(4, 4, 4)
                .addComponent(txtPeito, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabelCintura)
                .addGap(4, 4, 4)
                .addComponent(txtCintura, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabelBracoDireito)
                .addGap(4, 4, 4)
                .addComponent(txtBracoDireito, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabelBracoEsquerdo)
                .addGap(4, 4, 4)
                .addComponent(txtBracoEsquerdo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabelPernaDireita)
                .addGap(10, 10, 10)
                .addComponent(txtPernaDireita, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabelPernaEsquerda)
                .addGap(4, 4, 4)
                .addComponent(txtPernaEsquerda, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(259, 259, 259)
                .addComponent(btnNovo)
                .addGap(2, 2, 2)
                .addComponent(btnCadastrar)
                .addGap(6, 6, 6)
                .addComponent(btnLimpar)
                .addGap(6, 6, 6)
                .addComponent(btnSair))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAltura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGluteo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPeito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCintura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelAltura)
                            .addComponent(jLabelPeso)
                            .addComponent(jLabelGluteo)
                            .addComponent(jLabelPeito)
                            .addComponent(jLabelCintura))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBracoDireito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBracoEsquerdo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPernaDireita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPernaEsquerda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelBracoDireito)
                            .addComponent(jLabelBracoEsquerdo)
                            .addComponent(jLabelPernaDireita)
                            .addComponent(jLabelPernaEsquerda))))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNovo)
                    .addComponent(btnCadastrar)
                    .addComponent(btnLimpar)
                    .addComponent(btnSair)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(314, 314, 314)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        //AÇÃO DO BOTÃO CADASTRAR
        DesativarCampo();//METODO PARA DESABILITAR OS CAMPOS APOS UM NOVO CADASTRO
        adicionar();// método para adicionar clientes
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        // AÇÃO DO BOTÃO LIMPAR
        //LIMPA TODOS OS CAMPOS APOS CLICAR NO BOTAO LIMPAR
        LimparCampos();
    }//GEN-LAST:event_btnLimparActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        // AÇÃO DO BOTÃO SAIR
        this.dispose();//FECHA APENAS A TELA CADASTRO
        DesativarCampo();//DESATIVA OS CAMPOS AO SAIR
        //LIMPA TODOS OS CAMPOS APOS CLICAR NO BOTAO SAIR
        LimparCampos();
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        //AÇÃO DO BOTAO NOVO
        AtivarCampo(); //CHAMA O METODO NOVO() PARA HABILITAR OS CAMPOS
        data();//CHAMANDO O METODO DATA() PARA INSERIR DATA CONFORME A DO SISTEMA AUTOMATICO
        txtNome.requestFocus(); //DEIXA O CURSO PISCANDO NO CAMPO NOME
    }//GEN-LAST:event_btnNovoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File file = chooser.getSelectedFile();
        String imagem = file.getAbsolutePath();
        //jLabel1.setText(filename);
        jLabel2.setIcon(new ImageIcon(imagem));
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(TelaCadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TelaCadastro dialog = new TelaCadastro(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBoxSexo;
    private com.toedter.calendar.JDateChooser jDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelAltura;
    private javax.swing.JLabel jLabelBairro;
    private javax.swing.JLabel jLabelBracoDireito;
    private javax.swing.JLabel jLabelBracoEsquerdo;
    private javax.swing.JLabel jLabelCelular;
    private javax.swing.JLabel jLabelCep;
    private javax.swing.JLabel jLabelCintura;
    private javax.swing.JLabel jLabelCodigo;
    private javax.swing.JLabel jLabelComplemento;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelEndereco;
    private javax.swing.JLabel jLabelFixo;
    private javax.swing.JLabel jLabelGluteo;
    private javax.swing.JLabel jLabelIdade;
    private javax.swing.JLabel jLabelInicio;
    private javax.swing.JLabel jLabelNascimento;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JLabel jLabelNumero;
    private javax.swing.JLabel jLabelObeservacao;
    private javax.swing.JLabel jLabelPeito;
    private javax.swing.JLabel jLabelPernaDireita;
    private javax.swing.JLabel jLabelPernaEsquerda;
    private javax.swing.JLabel jLabelPeso;
    private javax.swing.JLabel jLabelRg;
    private javax.swing.JLabel jLabelSexo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtAltura;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtBracoDireito;
    private javax.swing.JTextField txtBracoEsquerdo;
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
    private javax.swing.JFormattedTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}