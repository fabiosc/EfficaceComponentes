/*
 * Desenvolvido por Efficace Tecnologia
 * Brasil, 16/08/2011
 */
package com.efficace.componente.tabela.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.Format;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * 
 * @author Fabio
 */
public class LineSelectionTableCellRenderer extends DefaultTableCellRenderer {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2406173258671449959L;
	
	private List<Integer> linhasPositivadas = new ArrayList<Integer>();
    private List<Integer> linhasNegativadas = new ArrayList<Integer>();
    private Format[] formatoDados = null;
    private Integer[] alinhamentoDados = null;
    private Integer[] tamanhoColuna = null;

    /**
     * 
     * @return 
     */
    public Integer[] getTamanhoColuna() {
        return tamanhoColuna;
    }

    /**
     * 
     * @param tamanhoColuna 
     */
    public void setTamanhoColuna(Integer[] tamanhoColuna) {
        this.tamanhoColuna = tamanhoColuna;
    }

    /**
     * 
     * @return 
     */
    /* FORMATO DOS DADOS */
    public Format[] getFormatoDados() {
        return formatoDados;
    }

    /**
     * 
     * @param formatoDados 
     */
    public void setFormatoDados(Format[] formatoDados) {
        this.formatoDados = formatoDados;
    }

    /**
     * 
     * @return 
     */
    /* ALINHAMENTO DOS DADOS */
    public Integer[] getAlinhamentoDados() {
        return alinhamentoDados;
    }

    /**
     * 
     * @param alinhamentoDados 
     */
    public void setAlinhamentoDados(Integer[] alinhamentoDados) {
        this.alinhamentoDados = alinhamentoDados;
    }

    /**
     * 
     * @param indiceLinha 
     */
    /* LINHAS POSITIVADAS */
    public void addLinhaPositivada(Integer indiceLinha){
        if (!isNegativada(indiceLinha)){
            int id = linhasPositivadas.indexOf(indiceLinha);
            if (id < 0){
                linhasPositivadas.add(indiceLinha);
            }
        }
    }

    /**
     * 
     * @param indiceLinha 
     */
    public void removeLinhaPositivada(Integer indiceLinha){
        int id = linhasPositivadas.indexOf(indiceLinha);
        if (id >= 0){
            linhasPositivadas.remove(id);
        }
    }

    /**
     * 
     * @return 
     */
    public List<Integer> getLinhasPositivadas() {
        return linhasPositivadas;
    }

    /**
     * 
     * @param linhasPositivadas 
     */
    public void setLinhasPositivadas(List<Integer> linhasPositivadas) {
        this.linhasPositivadas = linhasPositivadas;
    }

    /**
     * 
     * @param indiceLinha 
     */
    /* LINHAS NEGATIVADAS */
    public void addLinhaNegativada(Integer indiceLinha){
        if (!isPositivada(indiceLinha)){
            int id = linhasNegativadas.indexOf(indiceLinha);
            if (id < 0){
                linhasNegativadas.add(indiceLinha);
            }
        }
    }

    /**
     * 
     * @param indiceLinha 
     */
    public void removeLinhaNegativada(Integer indiceLinha){
        int id = linhasNegativadas.indexOf(indiceLinha);
        if (id >= 0){
            linhasNegativadas.remove(id);
        }
    }

    /**
     * 
     * @return 
     */
    public List<Integer> getLinhasNegativadas() {
        return linhasNegativadas;
    }

    /**
     * 
     * @param linhasNegativadas 
     */
    public void setLinhasNegativadas(List<Integer> linhasNegativadas) {
        this.linhasNegativadas = linhasNegativadas;
    }

    /**
     * 
     * @param indiceLinha
     * @return 
     */
    /* VERIFICA SE LINHA JÁ ESTA POSITIVADA */
    private boolean isPositivada(Integer indiceLinha){
        int id = linhasPositivadas.indexOf(indiceLinha);
        if (id < 0) {
            return false;
        }
        return true;
    }

    /**
     * 
     * @param indiceLinha
     * @return 
     */
    /* VERIFICA SE LINHA JÁ ESTA  NEGATIVADA */
    private boolean isNegativada(Integer indiceLinha){
        int id = linhasNegativadas.indexOf(indiceLinha);
        if (id < 0){
            return false;
        }
        return true;
    }

    /**
     * 
     * @param table
     * @param value
     * @param isSelected
     * @param hasFocus
     * @param row
     * @param column
     * @return 
     */
    @Override
    public Component getTableCellRendererComponent(
            JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column) {

        Component result = super.getTableCellRendererComponent(
                table,
                value,
                isSelected,
                hasFocus,
                row,
                column);

        /* DEFININDO ALINHAMENTO */
        if (alinhamentoDados != null){
            try {
                if (alinhamentoDados[column] != null){
                    this.setHorizontalAlignment(alinhamentoDados[column]);
                } else {
                    this.setHorizontalAlignment(SwingConstants.LEADING);
                }
            } catch (RuntimeException re){
                this.setHorizontalAlignment(SwingConstants.LEADING);
            }
        }

        /* FORMATANDO DADOS DA CELULA */
        if (formatoDados != null){
            try {
                if (formatoDados[column] != null){
                    if (value != null){
                        setText(formatoDados[column].format(value));
                    }
                }
            } catch (RuntimeException re){

            }
        }

        /* DEFININDO TAMANHO DA COLUNA */
        if (tamanhoColuna != null){
            try {
                if (tamanhoColuna[column] != null){
                    table.getColumnModel().getColumn(column).setPreferredWidth(tamanhoColuna[column]);
                }
            } catch (RuntimeException re){
                
            }
        }

        if (isSelected) {
            result.setFont(new Font("arial", Font.BOLD, 12));
            result.setForeground(Color.white);
            result.setBackground(Color.blue);
        } else {
            if (isPositivada(row)){
                result.setFont(new Font("arial", Font.BOLD, 12));
                result.setForeground(Color.black);
                //result.setBackground(new Color(192,	255,	062));
                result.setBackground(new Color(193, 255, 193));
            } else if (isNegativada(row)){
                result.setFont(new Font("arial", Font.BOLD, 12));
                result.setForeground(Color.black);
                result.setBackground(new Color(255, 228, 196));
            } else {
                if ((row % 2) > 0){
                    result.setFont(new Font("arial", Font.PLAIN, 12));
                    result.setForeground(Color.black);
                    result.setBackground(Color.white);
                } else {
                    result.setFont(new Font("arial", Font.PLAIN, 12));
                    result.setForeground(Color.black);
                    result.setBackground(new Color(240,	240,	240	));
                }
            }
        }
        return result;
    }
}
