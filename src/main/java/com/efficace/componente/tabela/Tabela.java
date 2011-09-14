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

	private static final long serialVersionUID = -3488444737034831984L;
	
	private LineSelectionTableCellRenderer renderer = new LineSelectionTableCellRenderer();
    private Integer[] alinhamentoColunas;
    private Format[] formatoDados;
    private Integer[] tamanhoColunas;

    public Tabela(){
        ajustaTabela();
        new TableFindAction().install(this);
    }
    
    public Tabela(Integer[] alinhamentoColunas, Integer[] tamanhoColunas) {
    	this.alinhamentoColunas = alinhamentoColunas;
    	this.tamanhoColunas = tamanhoColunas;
        ajustaTabela();
        new TableFindAction().install(this);
    }

    public Tabela(Format[] formatoDados, Integer[] alinhamentoColunas, Integer[] tamanhoColunas) {
    	this.formatoDados = formatoDados;
    	this.alinhamentoColunas = alinhamentoColunas;
    	this.tamanhoColunas = tamanhoColunas;
        ajustaTabela();
        new TableFindAction().install(this);
    }

    public Integer[] getAlinhamentoColunas() {
        return alinhamentoColunas;
    }

    public void setAlinhamentoColunas(Integer[] alinhamentoColunas) {
        this.alinhamentoColunas = alinhamentoColunas;
        ajustaTabela();
    }

    public Format[] getFormatoDados() {
        return formatoDados;
    }

    public void setFormatoDados(Format[] formatoDados) {
        this.formatoDados = formatoDados;
        ajustaTabela();
    }

    public Integer[] getTamanhoColunas() {
        return tamanhoColunas;
    }

    public void setTamanhoColunas(Integer[] tamanhoColunas) {
        this.tamanhoColunas = tamanhoColunas;
        ajustaTabela();
    }

    public void ajustaTabela(){
        //Desabilita o auto redimensionamento da tabela
        this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        //Nao permite drag-and-drop e nem resize de colunas
        this.getTableHeader().setReorderingAllowed(false);
        this.getTableHeader().setResizingAllowed(false);

        for (int c = 0; c < this.getColumnCount(); c++) {
            this.setDefaultRenderer(this.getColumnClass(c), renderer);
        }

        if (getAlinhamentoColunas() != null){
            renderer.setAlinhamentoDados(getAlinhamentoColunas());
        }

        if (getFormatoDados() != null){
            renderer.setFormatoDados(getFormatoDados());
        }

        if (getTamanhoColunas() != null){
            renderer.setTamanhoColuna(getTamanhoColunas());
        }

    }

    public void setDados(Object[][] dados){
        DefaultTableModel modelo = (DefaultTableModel) this.getModel();
        modelo.setDataVector(dados, getColunasIdentificadores(modelo));
        this.setModel(modelo);
    }
    
    private Object[] getColunasIdentificadores(TableModel modelo){
        modelo = (DefaultTableModel) this.getModel();
        Object[] colunas = new Object[modelo.getColumnCount()];
        for(int c = 0;c < colunas.length;c++){
            colunas[c] = modelo.getColumnName(c);
        }
        return colunas;
    }

    public boolean isCellEditable(int row, int column) {
        return false;
    }
    
    public void marcaLinhaVerde() {
		renderer.addLinhaPositivada(this.getSelectedRow());
    }
    
    public void desmarcaLinhaVerde(){
		renderer.removeLinhaPositivada(this.getSelectedRow());
    }
    
    public void marcaLinhaVermelha() {
		renderer.addLinhaNegativada(this.getSelectedRow());
    }
    
    public void desmarcaLinhaVermelha(){
		renderer.removeLinhaNegativada(this.getSelectedRow());
    }
    
}