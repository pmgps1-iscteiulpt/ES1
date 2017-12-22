package antiSpamFilter.test;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import antiSpamFilter.AntiSpamFilterManual;
import antiSpamFilter.Email;
import antiSpamFilter.Rule;

class AntiSpamFilterManualTest {
	

	/**
	 * Esta classe JUnit testa a classe AntiSpamFilterManual
	 * @author agasa-iscteiulpt
	 */

	/**
	 * Atributos para testar a classe
	 */
	
	LinkedList<Rule> rulesList;
	LinkedList<Email> emailHam;
	LinkedList<Email> emailSpam;
	
	String hamPath = "/files/ham.log.txt";
	String spamPath = "/files/spam.log.txt";
	
	@Before
	void setUp() throws Exception {
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
