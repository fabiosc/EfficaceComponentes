package com.efficace.componente.campo.util;

import java.awt.AWTException;
import java.awt.Robot;
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
	
	private Robot robot;
	
	public TratamentoTecla() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_HOME 
        		|| e.getKeyCode() == KeyEvent.VK_LEFT 
        		|| e.getKeyCode() == KeyEvent.VK_RIGHT){
        	robot.keyPress(KeyEvent.VK_END);
        } else if (e.getKeyCode() == KeyEvent.VK_DELETE){
        	//
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER){
            //
        }
    }

    public void keyReleased(KeyEvent e) {
    }

}
