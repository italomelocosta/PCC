/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.autooeste.Controller;

import br.com.autooeste.DAO.RequisicaoEstoqueDAO;
import br.com.autooeste.Modelo.Estoque;
import br.com.autooeste.Modelo.Funcionario;
import br.com.autooeste.Modelo.Item;
import br.com.autooeste.Modelo.RequisicaoEstoque;
import br.com.autooeste.Modelo.RequisicaoItem;
import br.com.autooeste.Util.Conexao;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;

/**
 *
 * @author Italo
 */
@ManagedBean
@SessionScoped
public class RequisicaoEstoqueController {

    private EntityManager em;
    private RequisicaoEstoque rE;
    private RequisicaoEstoqueDAO rEDAO;
    private List<Item> lista = null;
    private Item item;
    private RequisicaoItem rI;
    private RequisicaoItemController rIController;
    private Funcionario f;
    private FuncionarioController fController;
    private int codigo;
    private String bosta;
    private String desc;
    public static final String MSSQL = "com.mysql.jdbc.Driver";
    public static final String PATH = "jdbc:mysql://localhost:3306/bdford?zeroDateTimeBehavior=convertToNull";
    public static final String SQL_USER = "root";
    public static final String SQL_PWD = "root";
    private RequisicaoEstoque retorno;
    private EstoqueController ec;

    public RequisicaoEstoqueController() {
        em = Conexao.getEntityManager();
        em.getTransaction().begin();
        rE = new RequisicaoEstoque();
        rEDAO = new RequisicaoEstoqueDAO(em);
        item = new Item();
        rI = new RequisicaoItem();
        rIController = new RequisicaoItemController();
        f = new Funcionario();
        fController = new FuncionarioController();
        ec = new EstoqueController();
    }

    public void gerarRelatorio() throws JRException, IOException {
        //usuario = (Usuario) session.getAttribute("usuarioLogado");
        retorno = rEDAO.procurar();
        int i = retorno.getIdRequisicaoestoque();
        HashMap parameters = new HashMap();

        try {
            ResultSet r = rEDAO.procurar2(i);

            FacesContext facesContext = FacesContext.getCurrentInstance();

            facesContext.responseComplete();

            ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();

            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/WEB-INF/Relatorio/report1.jasper"), parameters, new JRResultSetDataSource(r));

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            JRPdfExporter exporter = new JRPdfExporter();

            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);

            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

            exporter.exportReport();

            byte[] bytes = baos.toByteArray();

            if (bytes != null && bytes.length > 0) {

                HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

                response.setContentType("application/pdf");

                response.setHeader("Content-disposition", "inline; filename=\"relatorioPorData.pdf\"");

                response.setContentLength(bytes.length);

                ServletOutputStream outputStream = response.getOutputStream();

                outputStream.write(bytes, 0, bytes.length);

                outputStream.flush();

                outputStream.close();

            }

        } catch (Exception ex) {
            //Logger.getLogger(ProjetoBean.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    /*public void teste() throws ClassNotFoundException {
     Class.forName(MSSQL);
     String fileName = "C:\\Users\\Italo\\Documents\\GitHub\\PCC\\SistemaFord\\web\\WEB-INF\\imprimiReq.jasper";
     String outFileName = "C:\\Users\\Italo\\Documents\\GitHub\\PCC\\SistemaFord\\web\\WEB-INF\\imprimiReq.pdf";
     HashMap hm = new HashMap();
     String query = "SELECT re.idRequisicao_estoque, re.dt_requisicao, re.mot_solicitacao, f.nome, ri.Item_idItem, i.descricao, ri.quantidade_requerida, i.medida FROM Requisicao_Estoque re INNER JOIN Funcionario f ON re.Funcionario_idFuncionario = f.idFuncionario INNER JOIN Requisicao_item ri ON re.idRequisicao_estoque = ri.Requisicao_estoque_idRequisicao_estoque INNER JOIN Item i ON ri.Item_idItem = i.idItem";
     ResultSet rs = null;
     try {
     rs = getQueryResultSet(query, new LinkedList<String>());
     } catch (SQLException ex) {
     Logger.getLogger(RequisicaoEstoqueController.class.getName()).log(Level.SEVERE, null, ex);
     }
     createPDFReport(fileName, outFileName, hm, rs);
     }
    
     public static void createPDFReport(String fileName, String outFileName, HashMap hm, ResultSet rs) {
     try {
     JasperPrint print = JasperFillManager.fillReport(fileName, hm, new JRResultSetDataSource(rs));
     JRExporter exporter = new net.sf.jasperreports.engine.export.JRPdfExporter();
     exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outFileName);
     exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
     exporter.exportReport();
     System.out.println("Created file: " + outFileName);
     } catch (JRException e) {
     e.printStackTrace();
     System.exit(1);
     } catch (Exception e) {
     e.printStackTrace();
     System.exit(1);
     }
     }
    
     public static ResultSet getQueryResultSet(String query, LinkedList<String> values) throws SQLException {
     Connection conn = DriverManager.getConnection(PATH, SQL_USER, SQL_PWD);
     PreparedStatement ps = conn.prepareStatement(query);
     if (values.size() != 0) {
     for (int i = 0; i < values.size(); i++) {
     ps.setString(i + 1, values.get(i));
     }
     }
     return ps.executeQuery();
     }*/
    public void novoItem(Item itens) {
        if (lista == null) {
            lista = new ArrayList<Item>();
        }
        itens.setQuantida(item.getQuantida());
        lista.add(itens);
    }

    public String salvar() {
        Funcionario buscaFunc = fController.buscaFunc(f);
        rE.setFuncionarioidFuncionario(buscaFunc);
        rE.setDtRequisicao(bosta);
        rE.setMotSolicitacao(desc);
        try {
            rEDAO.salvar(rE);
            confirmarTransacao();
        } catch (Exception e) {
            cancelarTransacao();
        }

        retorno = rEDAO.procurar();
        Estoque est;
        int tamanho = lista.size() - 1;
        for (int j = 0; j <= tamanho; j++) {
            est = new Estoque();
            rI = new RequisicaoItem();
            rI.setRequisicaoestoqueidRequisicaoestoque(retorno);
            rI.setQuantidadeRequerida(lista.get(j).getQuantida());
            rI.setItemidItem(lista.get(j));
            Item codigoItem = lista.get(j);
            est = ec.buscar(codigoItem);
            System.out.println("\n\n\n" + est.getQuantidade());
            float convert = est.getQuantidade() - rI.getQuantidadeRequerida();
            if (convert >= 0) {
                est.setQuantidade(convert);
                System.out.println(est.getQuantidade());
                ec.alterar(est);
                try {
                    rIController.salvar(rI);
                    confirmarTransacao();
                } catch (Exception e) {
                    cancelarTransacao();
                }
            }
        }
        return "cadastra_req";
    }

    private void confirmarTransacao() {
        em.getTransaction().commit();
        em.clear();
        em.getTransaction().begin();
    }

    private void cancelarTransacao() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        em.clear();
        em.getTransaction().begin();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Item> getLista() {
        return lista;
    }

    public void setLista(List<Item> lista) {
        this.lista = lista;
    }

    public String getBosta() {
        return bosta;
    }

    public void setBosta(String bosta) {
        this.bosta = bosta;
    }

    public RequisicaoEstoque getrE() {
        return rE;
    }

    public void setrE(RequisicaoEstoque rE) {
        this.rE = rE;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public RequisicaoItem getrI() {
        return rI;
    }

    public void setrI(RequisicaoItem rI) {
        this.rI = rI;
    }

    public Funcionario getF() {
        return f;
    }

    public void setF(Funcionario f) {
        this.f = f;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
