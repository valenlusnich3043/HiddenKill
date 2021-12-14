package com.micheliani.game.red;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.micheliani.game.pantallas.PantallaJuego;
import com.micheliani.game.utiles.Global;
import com.micheliani.game.utiles.Utiles;

public class HiloCliente extends Thread {

	private DatagramSocket conexion;
	private InetAddress ipServer;
	private int puerto = 9990;
	private boolean fin = false;
	private PantallaJuego app;

	public HiloCliente(PantallaJuego app) {
		this.app = app;
		try {
			ipServer = InetAddress.getByName("255.255.255.255");
			conexion = new DatagramSocket();
		} catch (SocketException | UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		enviarMensaje("Conexion");
	}

	public void enviarMensaje(String msg) {

		byte[] data = msg.getBytes();
		DatagramPacket dp = new DatagramPacket(data, data.length, ipServer, puerto);
		try {
			conexion.send(dp);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void run() {
		do {
			byte[] data = new byte[1024];
			DatagramPacket dp = new DatagramPacket(data, data.length);
			try {
				conexion.receive(dp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			procesarMensaje(dp);
		} while (!fin);
	}

	private void procesarMensaje(DatagramPacket dp) {
		String msg = (new String(dp.getData())).trim();

		String[] mensajeParametrizado = msg.split("!");

		if (mensajeParametrizado.length < 2) {
			if (msg.equals("Empieza")) {
				Global.empieza = true;
			}

		} else {
			if (mensajeParametrizado[0].equals("OK")) {
				ipServer = dp.getAddress();
//				app.nroJugador = Integer.parseInt(mensajeParametrizado[1]);
                Utiles.listener.asignarJugador(Integer.valueOf(mensajeParametrizado[1]));
			} else if (mensajeParametrizado[0].equals("Actualizar")) {
				if (mensajeParametrizado[1].equals("P1")) {
					float posX = Float.parseFloat(mensajeParametrizado[2]);
					float posY = Float.parseFloat(mensajeParametrizado[3]);
					app.player.setX(posX);
					app.player.setY(posY);
				} else if (mensajeParametrizado[1].equals("P2")) {
					float posX = Float.parseFloat(mensajeParametrizado[2]);
					float posY = Float.parseFloat(mensajeParametrizado[3]);
					app.player2.setX(posX);
					app.player2.setY(posY);
				}
			} else if (mensajeParametrizado[0].equals("ActualizarM")) {
				if (mensajeParametrizado[1].equals("P1")) {
					String state = (mensajeParametrizado[2]);
					app.player.setCurrentState(state);
				} else if (mensajeParametrizado[1].equals("P2")) {
					String state = (mensajeParametrizado[2]);
					app.player2.setCurrentState(state);
				}
			} else if (mensajeParametrizado[0].equals("Fin")) {
				if (mensajeParametrizado[1].equals("P1")) {
					Global.fin = true;
				} else if (mensajeParametrizado[1].equals("P2")) {
					Global.fin = true;
				}
			}else if (mensajeParametrizado[0].equals("Termino")) {
				Utiles.listener.terminoJuego(Integer.parseInt(mensajeParametrizado[1]));
				
			}

		}

	}
}
