/*
 * Desenvolvido por Efficace Tecnologia
 * Brasil, 07-09-2011
 */
package com.efficace.componente.campo.util;

import com.efficace.componente.excecoes.DigitoException;
import com.efficace.componente.excecoes.MascaraException;
import com.efficace.componente.util.Util;

/**
 * FormataCaixaAlta.java
 * @author Fabio Sicupira Cavalcante - Brasil, 07/09/2011
 * @version 0.1-SNAPTSHOT 07/09/2011
 */
public class FormataCaixaAlta extends FormataDados {

	private static final long serialVersionUID = -9163772629200548629L;

	public FormataCaixaAlta(int quantidadeDigitos) throws MascaraException {
		super.quantidadeDigitos = quantidadeDigitos;
	}
	
	public String formata(String string, boolean insereDigito) throws DigitoException {
		
        	if (insereDigito) {
        		this.digitos.append(string);
        	}
        	
        	return Util.caixaAlta(this.digitos.toString());
        
	}

}