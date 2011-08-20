package com.efficace.componente.campo.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Posiciona o cursor para o final do campo ao pressionar
 * as teclas: Home, seta direita, seta esquerda
 *
 * Simula o pressionamento da tecla backspace ao pressionar
 * a tecla delete
 *
 */
public class TratamentoTecla implements KeyListener {

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 36 || e.getKeyCode() == 37 || e.getKeyCode() == 39){
            e.setKeyCode(35);
        } else if (e.getKeyCode() == 127){
            e.setKeyCode(8);
        } else if (e.getKeyCode() == 13){
            //
        }
    }

    public void keyReleased(KeyEvent e) {
    }

}
