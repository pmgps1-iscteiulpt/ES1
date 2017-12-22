package gui.test;

import org.junit.jupiter.api.Test;

import gui.Gui;

class GuiTest {
	/**
	 * esta classe serve para fazer testes unitarios na classe Gui
	 * @author agasa-iscteiulpt
	 */
	
	@SuppressWarnings("static-access")
	@Test
	void test() {
		new Gui().main(null);
	}

}
