package br.grupointegrado.SpaceInvaders.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import br.grupointegrado.SpaceInvaders.MainGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 640; //altura
		config.width = 480; //largura
		new LwjglApplication(new MainGame(), config);
	}
}
