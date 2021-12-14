package com.micheliani.game.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.micheliani.game.HiddenKill;
import com.micheliani.game.escenas.Hud;
import com.micheliani.game.interfaces.JuegoEventListener;
import com.micheliani.game.interfaces.KeyListener;
import com.micheliani.game.red.HiloCliente;
import com.micheliani.game.sprites.Personaje;
import com.micheliani.game.utiles.Global;
import com.micheliani.game.utiles.Render;
import com.micheliani.game.utiles.Utiles;

public class PantallaJuego implements Screen, JuegoEventListener{

	private HiddenKill hiddenKill;
	private TextureAtlas atlas;
	
	public int nroJugador = 0;
	
	// basic playscreen variables
	private OrthographicCamera camaraJuego;
	private Viewport gamePort;
	private Hud hud;
	
	
	//Tiled map variables
	private TmxMapLoader maploader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
		
	
	private float contInicio = 0; 
	private int jugador;
	private int jugador2 = 0;
	private boolean finJuego = false;

	//Box2d variables
//	private World world;
//	private Box2DDebugRenderer b2dr;
	
	public Personaje player;
	public Personaje player2;

	//Asset Manager
	private Music music;
	
	//Red
	private HiloCliente hc;
	
	//Teclas Keylistener
	private KeyListener teclas;
	
	public PantallaJuego(HiddenKill hiddenKill) { 

		atlas = new TextureAtlas("personaje.pack");
		
		this.hiddenKill = hiddenKill;
		camaraJuego = new OrthographicCamera();
		gamePort = new FitViewport(HiddenKill.ancho / HiddenKill.PPM, 200 / HiddenKill.PPM, camaraJuego);
		
		hud = new Hud(hiddenKill.batch); 
		
		//cargo el mapa
		maploader = new TmxMapLoader();
		map = maploader.load("hiddenKillMap.tmx");
		renderer = new OrthogonalTiledMapRenderer(map, 1 / HiddenKill.PPM);
		
		//centrar la camara correctamente al inicio del juego 
//		camaraJuego.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
		camaraJuego.setToOrtho(false, gamePort.getWorldWidth(), gamePort.getWorldHeight());
		
//		world = new World(new Vector2(0, -10), true);
//		b2dr = new Box2DDebugRenderer();
//		
//		new B2WorldCreator(world, map);

		player = new Personaje(this);
		player2 = new Personaje(this);
		
		music = HiddenKill.manager.get("audio/musica/musica.ogg",  Music.class);
		music.setVolume(0.08f);
		music.setLooping(true);
		music.play();

		Utiles.listener = this;

	    hc = new HiloCliente(this);
	    hc.start();
		teclas = new KeyListener(hc);
	    Gdx.input.setInputProcessor(teclas);
	    
	}
	
	public TextureAtlas getAtlas() {
		return atlas;
	}
	
	@Override
	public void show() {
		
	}
	
	public void handleInput(float dt) {
		
		
		if (teclas.isArriba1()) {
			if(nroJugador==1) {
				player.jump();
				HiddenKill.manager.get("audio/sonidos/salto1.wav",  Sound.class).play();		
			}else {
				player2.jump();
				HiddenKill.manager.get("audio/sonidos/salto1.wav",  Sound.class).play();		
			}
		
			hc.enviarMensaje("ApreteArriba");
		}
		
		if (teclas.isDerecha1()) {
			if(nroJugador==1) {
//				player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(), true);							
			}else {
//				player2.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player2.b2body.getWorldCenter(), true);							
			}
			hc.enviarMensaje("ApreteDerecha");
		}
		
		if (teclas.isIzquierda1()) {
			if(nroJugador==1) {
//				player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);
			}else {
//				player2.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player2.b2body.getWorldCenter(), true);
			}
			hc.enviarMensaje("ApreteIzquierda");
		}
	
	}

	public void update(float dt) {
		//handle user input first
		handleInput(dt);
		
//		world.step(1/60f, 6, 2);
		
		player.update(dt);
		player2.update(dt);

		hud.update(dt);
		
//		camaraJuego.position.x = player.b2body.getPosition().x;
//		camaraJuego.position.x = player2.b2body.getPosition().x;
		//update our gamecam with correct coordinates after changes
		contInicio += dt;
		if(contInicio>10) {
			camaraJuego.position.x += (1.2)*dt;
		}
		
		camaraJuego.update();
		//tell our renderer to draw only what our camera can see in our game world 	
		renderer.setView(camaraJuego);
		
		//Determino los finales del juego
//		if(player.getY() < 0 || player.getX() > 87) {
//			player.currentState = Personaje.State.DEAD;
//		}
//		
//		if(player2.getY() < 0 || player2.getX() > 87) {
//			player2.currentState = Personaje.State.DEAD;
//		}
	}
	

	@Override
	public void render(float delta) {
		Render.limpiarPantalla();

		if (!Global.empieza) {
			Render.begin();
//			espera.dibujar();
			Render.end();
		} else {
			// separate our update logic from render
			
			update(delta);
			
			if(Global.fin) {
				hiddenKill.setScreen(new PantallaGameOver(hiddenKill));
				dispose();
			}
			// limpiar pantalla
			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

			// render del mapa
			renderer.render();

			// renderer our Box2DDebugLines
//			b2dr.render(world, camaraJuego.combined);

			hiddenKill.batch.setProjectionMatrix(camaraJuego.combined);
			hiddenKill.batch.begin();
			
			
			player.draw(hiddenKill.batch);
			player2.draw(hiddenKill.batch);

			hiddenKill.batch.end();

			hiddenKill.batch.setProjectionMatrix(hud.stage.getCamera().combined);
			hud.stage.draw();

			if(finJuego) {
				
				if (jugador == 1) {
					hiddenKill.setScreen(new PantallaGameOver(hiddenKill));
                    dispose();
                }
                if (jugador == 2) {
                	hiddenKill.setScreen(new PantallaGameOver2(hiddenKill));
                    dispose();
                }
				
			}
			
//			if (gameOver()) {
//				hiddenKill.setScreen(new PantallaGameOver(hiddenKill));
//				dispose();
//			}
		}

	}

	public boolean gameOver() {
//		if(player.currentState == Personaje.State.DEAD) {
//			return false;
//		}
//		
//		if(player2.currentState == Personaje.State.DEAD) {
//			return false;
//		}

		return false;
	}
	
	@Override
	public void resize(int width, int height) {
//		gamePort.update(width, height);
		gamePort = new FitViewport(width, height);
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
		map.dispose();
		renderer.dispose();
//		world.dispose();
//		b2dr.dispose();
		hud.dispose();
	}

	@Override
	public void terminoJuego(int nroJugador) {
		
		if(nroJugador==this.jugador2) {
			jugador = 1;
		}else {
			jugador = 2;
		}
		finJuego = true;
		
	}

	@Override
	public void asignarJugador(int jugador2) {
		this.jugador2 = jugador2;
		
	}



}
