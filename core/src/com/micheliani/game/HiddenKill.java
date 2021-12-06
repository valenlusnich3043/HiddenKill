package com.micheliani.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.micheliani.game.pantallas.PantallaJuego;

public class HiddenKill extends Game {
	OrthographicCamera camera;
	public SpriteBatch batch;
	public static final int ancho = 400;
	public static final int alto = 208;
	public static final float PPM = 45; 
	
	Texture personajeImagen1;
//	Personaje cabra;

	public static AssetManager manager;
	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		manager = new AssetManager();
		
		manager.load("audio/musica/musica.ogg",  Music.class);
		manager.load("audio/sonidos/salto1.wav",  Sound.class);
		manager.finishLoading();
		
		setScreen(new PantallaJuego(this));
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 750, 550);
	    camera.update();
	    
//	    cabra = new Personaje(500,500);    
	}
 
	@Override
	public void render () {
		super.render();
//	    camera.update();
//	    batch.setProjectionMatrix(camera.combined); REVISAR
	    
//		batch.begin();
////		cabra.render(batch);
////		batch.draw(personajeImagen1, 0, 0);
//		batch.end();
		
	}
	
	@Override
	public void dispose () {
		batch.dispose();
//		personajeImagen1.dispose();

	}
}
