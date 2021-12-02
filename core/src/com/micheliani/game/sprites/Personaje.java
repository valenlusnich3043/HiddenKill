package com.micheliani.game.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.micheliani.game.HiddenKill;
import com.micheliani.game.pantallas.PantallaJuego;

public class Personaje extends Sprite{
	public World world;
	public Body b2body;
	private TextureRegion BikerQuieto; 
	
	public Personaje(World world, PantallaJuego screen) {
		super(screen.getAtlas().findRegion("little_mario"));
		this.world = world;
		definePersonaje();
		BikerQuieto = new TextureRegion(getTexture(), 0, 2, 18, 18);
		setBounds(0, 0, 16 / HiddenKill.PPM, 16 / HiddenKill.PPM);
		setRegion(BikerQuieto);
	}
	
	public void update(float dt) {
		setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
	}
	
	public void definePersonaje() {
		BodyDef bdef = new BodyDef();
		bdef.position.set(32 / HiddenKill.PPM, 32 / HiddenKill.PPM);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);
	
		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(6 / HiddenKill.PPM);
		
		fdef.shape = shape;
		b2body.createFixture(fdef);
	}
	
	
	

}
