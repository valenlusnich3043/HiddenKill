package com.micheliani.game.personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Personaje {

	Sprite sprite;
	int y, x;
	float personajeSpeed = 10.0f;
			
	public Personaje(int x, int y) {
		this.x = x;
		this.y = y;
		sprite = new Sprite(new Texture(Gdx.files.internal("goat.png")), 128, 128);
		
		
	}
	
	public void render(final SpriteBatch batch) {
		batch.draw(sprite,x,y);
	
		 if(Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) {
		      x -= Gdx.graphics.getDeltaTime() * personajeSpeed;
		      System.out.println("Izquierda");
		 }else if(Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) {
		      x += Gdx.graphics.getDeltaTime() * personajeSpeed;			 
		      System.out.println("Derecha");
		 }else if(Gdx.input.isKeyPressed(Keys.DPAD_UP)) {
		      y += Gdx.graphics.getDeltaTime() * personajeSpeed;
		      System.out.println("Arriba");
		 }
		 else if(Gdx.input.isKeyPressed(Keys.DPAD_DOWN)) {
			  y -= Gdx.graphics.getDeltaTime() * personajeSpeed;		
			  System.out.println("Abajo");
		   }

	}

}
