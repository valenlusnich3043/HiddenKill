package com.micheliani.game.interfaces;

import java.util.EventListener;

public interface JuegoEventListener extends EventListener{

	void terminoJuego(int nroJugador);

	void asignarJugador(int jugador2);

}
