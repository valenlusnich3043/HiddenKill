package com.micheliani.game.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.micheliani.game.HiddenKill;
import com.micheliani.game.escenas.Hud;
import com.micheliani.game.sprites.Personaje;

public class PantallaJuego implements Screen{

	private HiddenKill hiddenKill;
	private OrthographicCamera camaraJuego;
	private Viewport gamePort;
	private Hud hud;
	
	
	//Tiled map variables
	private TmxMapLoader maploader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
		
	//Box2d variables
	private World world;
	private Box2DDebugRenderer b2dr;
	
	private Personaje player;
	
	public PantallaJuego(HiddenKill hiddenKill) { 
		this.hiddenKill = hiddenKill;
		camaraJuego = new OrthographicCamera();
		gamePort = new FitViewport(hiddenKill.ancho / hiddenKill.PPM, hiddenKill.alto / hiddenKill.PPM, camaraJuego);
		
		hud = new Hud(hiddenKill.batch);
		
		maploader = new TmxMapLoader();
		map = maploader.load("hiddenKillMap.tmx");
		renderer = new OrthogonalTiledMapRenderer(map, 1 / hiddenKill.PPM);
		
		//centrar la camara correctamente al inicio del juego 
		camaraJuego.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

		world = new World(new Vector2(0, -10), true);
		b2dr = new Box2DDebugRenderer();
		
		BodyDef bdef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		Body body;
			
		
		//create suelo pasto bodies/fixture
		for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
			bdef.type = BodyDef.BodyType.StaticBody;
			bdef.position.set((rect.getX() + rect.getWidth() / 2) / hiddenKill.PPM, (rect.getY() + rect.getHeight() / 2) / hiddenKill.PPM);
			
			body = world.createBody(bdef);
			
			shape.setAsBox((rect.getWidth() / 2)/ hiddenKill.PPM, (rect.getHeight() / 2)/ hiddenKill.PPM);
			fdef.shape = shape;
			body.createFixture(fdef);
		}
		
		//create piedra bodies/fixture

		for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
			bdef.type = BodyDef.BodyType.StaticBody;
			bdef.position.set((rect.getX() + rect.getWidth() / 2) / hiddenKill.PPM, (rect.getY() + rect.getHeight() / 2)/ hiddenKill.PPM );
			
			body = world.createBody(bdef);
			
			shape.setAsBox((rect.getWidth() / 2)/ hiddenKill.PPM,(rect.getHeight() / 2)/ hiddenKill.PPM);
			fdef.shape = shape;
			body.createFixture(fdef);
		}
		
		player = new Personaje(world);

	}

	@Override
	public void show() {
		
	}
	
	public void handleInput(float dt) {
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) 
			player.b2body.applyLinearImpulse(new Vector2(0, 4f), player.b2body.getWorldCenter(), true);
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2)
			player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(), true);
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2)
			player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);
		
		//	if(Gdx.input.isTouched()) {
//		camaraJuego.position.x += 100 * dt;
//	}

//	if(Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT)) {
//		camaraJuego.position.x += 100 * dt;
//	}else if(Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)) {
//		camaraJuego.position.x -= 100 * dt;
//	}else if(Gdx.input.isKeyPressed(Input.Keys.DPAD_UP)) {
//		camaraJuego.position.y += 100 * dt; 
//	}else if(Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN)) {
//		camaraJuego.position.y -= 100 * dt; 
//		
//	}
	 
	}
	
	public void update(float dt) {
		//handle user input first
		handleInput(dt);
		
		world.step(1/60f, 6, 2);
		
		camaraJuego.position.x = player.b2body.getPosition().x;
		
		//update our gamecam with correct coordinates after changes
		camaraJuego.update();
		//tell our renderer to draw only what our camera can see in our game world 	
		renderer.setView(camaraJuego);
	}

	@Override
	public void render(float delta) {
		//separate our update logic from render
		update(delta);		
		
		//limpiar pantalla
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//render del mapa
		renderer.render();
		
		//renderer our Box2DDebugLines
		b2dr.render(world, camaraJuego.combined);
		
		hiddenKill.batch.setProjectionMatrix(hud.stage.getCamera().combined);
		hud.stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		gamePort.update(width, height);
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
