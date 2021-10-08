package com.micheliani.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.micheliani.game.pantallas.PantallaJuego;
import com.micheliani.game.personajes.Personaje;

public class HiddenKill extends Game {
	OrthographicCamera camera;
	public SpriteBatch batch;
	Texture personajeImagen1;
	Personaje cabra;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PantallaJuego(this));
//		personajeImagen1 = new Texture("hinchaDeBoca.png"); 
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 750, 550);
	    camera.update();
	    asdasdasdad
	    cabra = new Personaje(400,400);
	}

	@Override
	public void render () {
		super.render();
	    camera.update();
	    batch.setProjectionMatrix(camera.combined);
	    
		batch.begin();
		cabra.render(batch);
//		batch.draw(personajeImagen1, 0, 0);
		batch.end();
		
	}
	
	@Override
	public void dispose () {
		batch.dispose();
//		personajeImagen1.dispose();

	}
}
