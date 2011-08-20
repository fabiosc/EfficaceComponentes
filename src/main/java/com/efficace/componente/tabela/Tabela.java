/*
 * Desenvolvido por Efficace Tecnologia
 * Brasil, 16/08/2011
 */
package com.efficace.componente.tabela;

import com.efficace.componente.tabela.util.LineSelectionTableCellRenderer;
import com.efficace.componente.tabela.util.TableFindAction;
import java.text.Format;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * Tabela.java
 * @author Fabio Sicupira Cavalcante
 * @version 0.1-SNAPTSHOT 16/08/2011
*/
public class Tabela extends JTable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3488444737034831984L;
	
	private LineSelectionTableCellRenderer renderer = new LineSelectionTableCellRenderer();
    private Integer[] tabelaAlinhamentosDados;
    private Format[] tabelaFormatosDados;
    private Integer[] tabelaTamanhosColuna;

    /**
     * Construtor da classe Tabela
     */
    public Tabela(){
        ajustaTabela();
        new TableFindAction().install(this);
    }

    /**
     * Retorna um array de inteiros que definem
     * o alinhamento das colunas da tabela
     * @return Integer[] 
     */
    public Integer[] getTabelaAlinhamentosDados() {
        return tabelaAlinhamentosDados;
    }

    /**
     * Recebe um array de inteiros que definem 
     * o alinhamento das colunas da tabela
     * @param tabelaAlinhamentosDados - Integer[]
     */
    public void setTabelaAlinhamentosDados(Integer[] tabelaAlinhamentosDados) {
        this.tabelaAlinhamentosDados = tabelaAlinhamentosDados;
        ajustaTabela();
    }

    /**
     * Retorna um array de formatos  que definem
     * o formato dos dados nas colunas da tabela
     * @return Format[]
     */
    public Format[] getTabelaFormatosDados() {
        return tabelaFormatosDados;
    }

    /**
     * Recebe um array de formatos  que  definem
     * o formato dos dados nas colunas da tabela
     * @param tabelaFormatosDados - Format[]
     */
    public void setTabelaFormatosDados(Format[] tabelaFormatosDados) {
        this.tabelaFormatosDados = tabelaFormatosDados;
        ajustaTabela();
    }

    /**
     * Retorna um array de inteiros que definem
     * o tamanho das colunas da tabela
     * @return Integer[]
     */
    public Integer[] getTabelaTamanhosColuna() {
        return tabelaTamanhosColuna;
    }

    /**
     * Recebe um array de inteiros que definem
     * o tamanho das colunas da tabela
     * @param tabelaTamanhosColuna - Integer[]
     */
    public void setTabelaTamanhosColuna(Integer[] tabelaTamanhosColuna) {
        this.tabelaTamanhosColuna = tabelaTamanhosColuna;
        ajustaTabela();
    }

    /**
     * Realiza o ajuste das colunas da tabela
     */
    private void ajustaTabela(){
        //Desabilita o auto redimensionamento da tabela
        this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        //Nao permite drag-and-drop e nem resize de colunas
        this.getTableHeader().setReorderingAllowed(false);
        this.getTableHeader().setResizingAllowed(false);

        for (int c = 0; c < this.getColumnCount(); c++) {
            this.setDefaultRenderer(this.getColumnClass(c), renderer);
        }

        if (getTabelaAlinhamentosDados() != null){
            renderer.setAlinhamentoDados(getTabelaAlinhamentosDados());
        }

        if (getTabelaFormatosDados() != null){
            renderer.setFormatoDados(getTabelaFormatosDados());
        }

        if (getTabelaTamanhosColuna() != null){
            renderer.setTamanhoColuna(getTabelaTamanhosColuna());
        }

    }

    /**
     * Recebe um array de objetos contendo os dados
     * que preencherao a tabela
     * @param dados - Object[][]
     */
    public void setDados(Object[][] dados){
        DefaultTableModel modelo = (DefaultTableModel) this.getModel();
        modelo.setDataVector(dados, getColunasIdentificadores(modelo));
        this.setModel(modelo);
    }
    
    /**
     * Retorna um array de objetos a partir de
     * um   modelo  de  tabela,  e  auxilia  o 
     * preenchimento da tabela
     * @param modelo - TableModel
     * @return Object[][] 
     */
    private Object[] getColunasIdentificadores(TableModel modelo){
        modelo = (DefaultTableModel) this.getModel();
        Object[] colunas = new Object[modelo.getColumnCount()];
        for(int c = 0;c < colunas.length;c++){
            colunas[c] = modelo.getColumnName(c);
        }
        return colunas;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
    
}
