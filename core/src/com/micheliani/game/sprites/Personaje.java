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
	private TextureRegion punkQuieto; 
	
	public Personaje(World world, PantallaJuego screen) {
		super(screen.getAtlas().findRegion("Punk"));
		this.world = world;
		definePersonaje();
		punkQuieto = new TextureRegion(getTexture(), 0, 0, 48, 48);
		setBounds(0, 0, 48 / HiddenKill.PPM, 48 / HiddenKill.PPM);
		setRegion(punkQuieto);
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
