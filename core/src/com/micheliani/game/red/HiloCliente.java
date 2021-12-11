package com.micheliani.game.red;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.micheliani.game.utiles.Global;

public class HiloCliente extends Thread{
	
	private DatagramSocket conexion;
	private InetAddress ipServer;
	private int puerto = 9990;
	private boolean fin = false;
	
	public HiloCliente(){
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
			// TODO Auto-generated catch block
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
		}while(!fin);
	}

	private void procesarMensaje(DatagramPacket dp) {
		String msg = dp.getData().toString().trim();
		if(msg.equals("OK")) {
			ipServer = dp.getAddress(); 
		}else if(msg.equals("Empieza")) {
			Global.empieza = true;
		}
	}
	

}
