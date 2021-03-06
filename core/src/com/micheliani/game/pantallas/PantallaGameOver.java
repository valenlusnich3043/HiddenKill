package com.micheliani.game.pantallas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.micheliani.game.HiddenKill;

public class PantallaGameOver implements Screen{

	private Viewport viewport;
	private Stage stage;
	
	private Game game; 

	public PantallaGameOver(Game game) {
		this.game = game;
		viewport = new FitViewport(HiddenKill.ancho, HiddenKill.alto, new OrthographicCamera());
		stage = new Stage(viewport, ((HiddenKill) game).batch);
		
		Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
		
		Table table = new Table();
		table.center();
		table.setFillParent(true);
		
		Label gameOverLabel = new Label("FIN DEL JUEGO", font);
		Label perdiste = new Label("PERDISTE", font);
		Label juegarDeNuevoLabel = new Label("Haz click en cualquier parte de la pantalla para iniciar de vuelta", font);
		
		table.add(gameOverLabel).expandX();
		table.row();
		table.row();
		table.add(perdiste).expandX();
		table.row();
		table.row();
		table.add(juegarDeNuevoLabel).expandX().padTop(10f);
		
		stage.addActor(table);
	}
	
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		
//		if(Gdx.input.justTouched()) {
//			game.setScreen(new PantallaJuego((HiddenKill) game));
//			dispose();
//		}
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
		
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
