package com.micheliani.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.micheliani.game.elementos.Recursos;
import com.micheliani.game.elementos.Texto;
import com.micheliani.game.pantallas.PantallaJuego;

public class HiddenKill extends Game{
	OrthographicCamera camera;
	public SpriteBatch batch;
	public static final int ancho = 400;
	public static final int alto = 300;
	public static final float PPM = 45; 
	private Texto espera;
	
	Texture personajeImagen1;

	public static AssetManager manager; 
	
	
	@Override
	public void create () {

		espera = new Texto(Recursos.FUENTE, 100, Color.WHITE, false);
		System.out.println("Esperando rival");
//		espera.setTexto("Esperando rival");
		
		batch = new SpriteBatch();
		manager = new AssetManager();
		
		manager.load("audio/musica/musica.ogg",  Music.class);
		manager.load("audio/sonidos/salto1.wav",  Sound.class);
		manager.finishLoading();
		
		setScreen(new PantallaJuego(this));
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 750, 550);
	    camera.update();

	}
 
	@Override
	public void render() {

//		Render.limpiarPantalla();
//		
//		if(!Global.empieza) {
//			Render.begin();
//			espera.dibujar();
//			Render.end();
//		} else {

		super.render();

//		}
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
