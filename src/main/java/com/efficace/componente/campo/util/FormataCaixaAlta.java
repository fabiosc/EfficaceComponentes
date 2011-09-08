package com.efficace.componente.campo.util;

import com.efficace.componente.excecoes.DigitoException;
import com.efficace.componente.excecoes.MascaraException;
import com.efficace.componente.util.Util;

public class FormataCaixaAlta extends FormataDados {

	private static final long serialVersionUID = -9163772629200548629L;

	public FormataCaixaAlta(int quantidadeDigitos) throws MascaraException {
		super.quantidadeDigitos = quantidadeDigitos;
	}
	
	@Override
	public String formata(String string, boolean insereDigito) throws DigitoException {
		
        	if (insereDigito) {
        		this.digitos.append(string);
        	}
        	
        	return Util.caixaAlta(this.digitos.toString());
        
	}

}
