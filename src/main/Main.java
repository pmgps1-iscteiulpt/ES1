package main;

import java.awt.EventQueue;

import gui.Gui;

public class Main {

	/* MAIN */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
