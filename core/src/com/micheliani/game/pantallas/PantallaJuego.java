package com.micheliani.game.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.micheliani.game.HiddenKill;

public class PantallaJuego implements Screen{

	private HiddenKill hiddenKill;
	Texture texture;
	
	public PantallaJuego(HiddenKill hiddenKill) {
		this.hiddenKill = hiddenKill;
		texture = new Texture("badlogic.jpg");
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		hiddenKill.batch.begin();
		hiddenKill.batch.draw(texture, 0, 0);
		hiddenKill.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
