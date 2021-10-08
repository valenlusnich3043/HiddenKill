package com.micheliani.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.micheliani.game.HiddenKill;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Hidden Kill";
		config.width = 850;
		config.height = 550;
		new LwjglApplication(new HiddenKill(), config);
	}
}
