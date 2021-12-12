package com.micheliani.game.interfaces;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.micheliani.game.red.HiloCliente;

public class KeyListener implements InputProcessor{
	
	private boolean arriba1 = false, derecha1 = false, izquierda1 = false, arriba2 = false, derecha2 = false, izquierda2 = false; 
	private HiloCliente hc;
	
	public KeyListener(HiloCliente hc) {
		this.hc = hc;
	}

	@Override
	public boolean keyDown(int keycode) {
		if(keycode==Keys.UP) {
			arriba1 = true;
		}
		if(keycode==Keys.RIGHT) {
			derecha1 = true;
		}
		if(keycode==Keys.LEFT) {
			izquierda1 = true;	
		}
		
		if(keycode==Keys.W) {
			arriba2 = true;
		}
		if(keycode==Keys.D) {
			derecha2 = true;
		}
		if(keycode==Keys.A) {
			izquierda2 = true;	
		}
		
		return false;
	}


	@Override
	public boolean keyUp(int keycode) {
		if(keycode==Keys.UP) {
			arriba1 = false;
			hc.enviarMensaje("DejeApretarArriba");
		}
		if(keycode==Keys.RIGHT) {
			derecha1 = false;
			hc.enviarMensaje("DejeApretarDerecha");
		}
		if(keycode==Keys.LEFT) {
			izquierda1 = false;	
			hc.enviarMensaje("DejeApretarIzquierda");
		}
		
//		if(keycode==Keys.W) arriba2 = false;
//		if(keycode==Keys.D) derecha2 = false;
//		if(keycode==Keys.A) izquierda2 = false;	
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean isArriba1() {
		return arriba1;
	}

	public boolean isDerecha1() {
		return derecha1;
	}

	public boolean isIzquierda1() {
		return izquierda1;
	}

	public boolean isArriba2() {
		return arriba2;
	}

	public boolean isDerecha2() {
		return derecha2;
	}

	public boolean isIzquierda2() {
		return izquierda2;
	}



	

}
