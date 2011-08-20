/*
 * Desenvolvido por Efficace Tecnologia
 * Brasil, 21-04-2011
 */
package com.efficace.componente.campo.util;

import javax.swing.text.BadLocationException;

/**
 * FabricaFornatoDados.java
 * @author Fabio Sicupira Cavalcante - Brasil, 21/04/2011
 * @version 0.1-SNAPTSHOT 21/04/2011
 */
public class FabricaFormatoDados {
    /**
     * Construtor da classe
     * @param mascara - String - Mascara do campo
     * @return PlainDocument - Retorna o padrao de edicao do campo
     * @throws BadLocationException 
     */
    public FormatoDados criar(String mascara) throws BadLocationException{
        if (mascara.equals("#")){
            FormatoNumeroSimples fns = new FormatoNumeroSimples(mascara);
            fns.setPossuiDecimal(false);
            return fns;
        } else if (mascara.equals(",#") || mascara.equals(".#")){
            FormatoQuantidade qtd = new FormatoQuantidade(mascara);
            return qtd;
        } else {
            FormatoDecimal dec = new FormatoDecimal(mascara);
            return dec;
        }
    }
    
    /**
     * 
     * @param mascara - String - Mascara do campo
     * @param padraoBancario - Boolean - Informa se o campo deve seguir o padrao de digitacao de bancos
     * @return PlainDocument - Retorna o padrao de edicao do campo
     * @throws BadLocationException 
     */
    public FormatoDados criar(String mascara, Boolean padraoBancario) throws BadLocationException{
        if (mascara.equals("#")){
            FormatoNumeroSimples fns = new FormatoNumeroSimples(mascara);
            fns.setPadraoBancario(padraoBancario);
            fns.setPossuiDecimal(false);
            return fns;
        } else if (mascara.equals(",#") || mascara.equals(".#")){
            FormatoQuantidade qtd = new FormatoQuantidade(mascara);
            qtd.setPadraoBancario(padraoBancario);
            return qtd;
        } else {
            FormatoDecimal dec = new FormatoDecimal(mascara);
            dec.setPadraoBancario(padraoBancario);
            return dec;
        }
    }
    
}
