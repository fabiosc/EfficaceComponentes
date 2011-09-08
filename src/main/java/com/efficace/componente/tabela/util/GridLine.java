package com.efficace.componente.tabela.util;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

public class GridLine {


    public static void main(String[] args) {

        for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(GridLine.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(GridLine.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(GridLine.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(GridLine.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
        }

        JButton b1 = new JButton("Positivar");
        JButton b2 = new JButton("Remover Positivação");
        JButton b3 = new JButton("Negativar");
        JButton b4 = new JButton("Remover Negativação");

        Integer[] tamanhoColuna = new Integer[]{
          50, 50, 50, 80, 100, 200
        };

        Integer[] alinhamento = new Integer[]{
            SwingConstants.TRAILING,
            SwingConstants.CENTER,
            SwingConstants.CENTER,
            SwingConstants.TRAILING,
            SwingConstants.TRAILING,
            null
        };

        Format[] formato = new Format[]{
            null,
            null,
            null, 
            new SimpleDateFormat("dd/MM/yyyy"),
            NumberFormat.getNumberInstance(),
            null
        };

        Object[] columns = new Object[]{"Num", "BR", "EN", "DATA", "VALOR", "CLIENTE"};
        Object[][] dados = new Object[][]{
            {0, "Zero", "Zero", new Date(), new Double(1000.01), "JOAQUIM JOSÉ DA SILVA XAVIER"},
            {1, "Um", "One", new Date(), new Double(1.02), null},
            {2, "Dois", "Two", new Date(), new Double(2.03), null},
            {3, "Três", "Three", new Date(), new Double(3.04), null},
            {4, "Quatro", "Four", new Date(), new Double(4.05), null},
            {5, "Cinco", "Five", new Date(), new Double(5.06), null},
            {6, "Seis", "Six", new Date(), new Double(6.07), null},
            {7, "Sete", "Seven", new Date(), new Double(7.08), null},
            {8, "Oito", "Eight", new Date(), new Double(8.09), null},
            {9, "Nove", "Nine", new Date(), new Double(9.10), null},
            {10, "Dez", "Ten", new Date(), new Double(10.11), null},
            {11, "Onze", "Eleven", new Date(), new Double(11.12), null},
            {12, "Doze", "Twoelve", new Date(), new Double(12.13), null},
            {13, "Treze", "Thirteen", new Date(), new Double(13.14), null},
            {14, "Quatorze", "Fourteen", new Date(), new Double(14.15), null},
            {15, "Quinze", "Fifteen", new Date(), new Double(15.01), null}
        };

        final JTable tbl = new JTable(dados, columns);
        //OBS
        // Fazer auto-resizing se o conteúdo do jtable
        // for menor que a largura da jtable, se não
        // desligar auto-resizing

        tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        //Não permite drag-and-drop e nem resize de colunas
        tbl.getTableHeader().setReorderingAllowed(false);
        tbl.getTableHeader().setResizingAllowed(false);

        JScrollPane pane = new JScrollPane(tbl);
        //pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        //pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        final LineSelectionTableCellRenderer renderer = new LineSelectionTableCellRenderer();
        for (int c = 0; c < tbl.getColumnCount(); c++) {
            tbl.setDefaultRenderer(tbl.getColumnClass(c), renderer);
        }

        renderer.setAlinhamentoDados(alinhamento);
        renderer.setFormatoDados(formato);
        renderer.setTamanhoColuna(tamanhoColuna);

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                renderer.addLinhaPositivada(tbl.getSelectedRow());
                ajustaTabela(tbl);
            }
        });
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                renderer.removeLinhaPositivada(tbl.getSelectedRow());
                ajustaTabela(tbl);
            }
        });
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                renderer.addLinhaNegativada(tbl.getSelectedRow());
                ajustaTabela(tbl);
            }
        });

        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                renderer.removeLinhaNegativada(tbl.getSelectedRow());
                ajustaTabela(tbl);
            }
        });

        JPanel bPanel = new JPanel();
        bPanel.setLayout(new GridLayout(3, 1));
        bPanel.add(b1);
        bPanel.add(b2);
        bPanel.add(b3);
        bPanel.add(b4);

        JFrame dlg = new JFrame("Teste");
        Container c = dlg.getContentPane();
        c.add(pane, BorderLayout.CENTER);
        c.add(bPanel, BorderLayout.SOUTH);
        dlg.setSize(500, 300);
        dlg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dlg.setVisible(true);

    }

    private static void ajustaTabela(JTable tbl){
            tbl.setSelectionMode(0);
            tbl.repaint();
    }

}
