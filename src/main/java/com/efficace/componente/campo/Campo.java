/*
 * Desenvolvido por Efficace Tecnologia
 * Brasil, 21-04-2011
 */
package com.efficace.componente.campo;

import com.efficace.componente.campo.util.FabricaFormatoDados;
import com.efficace.componente.campo.util.FormatoDados;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

/**
 * Campo.java
 * @author Fabio Sicupira Cavalcante - Brasil, 21/04/2011
 * @version 0.1-SNAPTSHOT 21/04/2011
 */
public class Campo extends JTextField{
    private String mascara;
    private Integer quantidadeDigitos = 0;
    private Boolean padraoBancario = true;
    private FabricaFormatoDados fabrica = new FabricaFormatoDados();
    private FormatoDados fd;

    /**
     * Construtor da classe Campo
     */
    public Campo() {
        alinhaDireita();
        posicionaCursor();
        addKeyListener(new TratamentoTecla());
        addFocusListener(new TratamentoFoco());
        addMouseListener(new TratamentoMouse());
    }

    /**
     * Retorna a máscara do campo
     * @return String - Máscara do campo
     */
    public String getMascara() {
        return mascara;
    }

    /**
     * Atribui uma máscara ao campo
     * Máscara: #.#, #.## ou #.### - Números decimais no padrão americano
     *        : #,#, #,## ou #,### - Números decimais no padrão brasileiro
     *        : ,#                 - Números inteiros no padrão americano
     *        : .#                 - Números inteiros no padrão brasileiro
     *        : #                  - Números inteiros sem formato
     * 
     * @param mascara String - Máscara do campo
     * @throws BadLocationException 
     */
    public void setMascara(String mascara) throws BadLocationException {
        fd = fabrica.criar(mascara);
        setCaretPosition(getText().length());
        setDocument(fd);
        this.mascara = mascara;
    }

    /**
     * Retorna a quantidade de caracteres que podem ser digitados no campo
     * @return Inteiro - Quantidade de caracteres
     */
    public Integer getQuantidadeDigitos() {
        return quantidadeDigitos;
    }

    /**
     * Atribui a quantidade de caracteres que podem ser digitados no campo
     * @param tamanhoMaximo Inteiro - Quantidade de caracteres
     */
    public void setQuantidadeDigitos(Integer tamanhoMaximo) {
        this.quantidadeDigitos = tamanhoMaximo;
        if (this.quantidadeDigitos != 0 && fd != null){
            fd.setTamanhoMaximo(this.quantidadeDigitos);
        }
    }

    /**
     * Retorna true se a digitação segue o padrão bancário
     * ou false se não segue.
     * @return Boleano - Padrão bancário
     */
    public Boolean getPadraoBancario() {
        return padraoBancario;
    }

    /**
     * Atribui o padrão bancário a um campo
     * @param padraoBancario Boleano - Padrão bancário
     */
    public void setPadraoBancario(Boolean padraoBancario) {
        this.padraoBancario = padraoBancario;
        if (fd != null){
            fd.setPadraoBancario(this.padraoBancario);
        }
    }

    /**
     * Alinha os caracteres do campo a direita
     */
    private void alinhaDireita(){
        setHorizontalAlignment(JTextField.TRAILING);
    }

    /**
     * Posiciona o cursos no campo
     */
    private void posicionaCursor(){
        setCaretPosition(getText().length());
    }
    
    /**
     * Posiciona o cursor para o final do campo ao pressionar
     * as teclas: Home, seta direita, seta esquerda
     *
     * Simula o pressionamento da tecla backspace ao pressionar
     * a tecla delete
     *
     */
    private class TratamentoTecla implements KeyListener{
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == 36 || e.getKeyCode() == 37 || e.getKeyCode() == 39){
                e.setKeyCode(35);
            } else if (e.getKeyCode() == 127){
                e.setKeyCode(8);
            } else if (e.getKeyCode() == 13){
                //
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

    /**
     * Posiciona o cursor no final do campo
     * a qualquer ação do mouse no campo
     */
    private class TratamentoMouse implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent e) {
            setCaretPosition(getText().length());
        }

        @Override
        public void mousePressed(MouseEvent e) {
            setCaretPosition(getText().length());
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            setCaretPosition(getText().length());
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            setCaretPosition(getText().length());
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setCaretPosition(getText().length());
        }
    }

    /**
     * Posiciona o cursor no final do campo
     * ao ganhar o foco
     */
    private class TratamentoFoco implements FocusListener{
        @Override
        public void focusGained(FocusEvent e) {
            setCaretPosition(getText().length());
        }

        @Override
        public void focusLost(FocusEvent e) {
        }
    }
    
    /**
     * Retorna o valor como número duplo
     * @return Duplo -  Valor do campo
     */
    public Double getValor(){
        return fd.getValorCampo();
    }
    
    /**
     * Retorna o valor em uma string com o formato no padrão americano
     * @return String - Valor do campo
     */
    public String getValorString(){
        return fd.getValorCampoString();
    }
    
}
