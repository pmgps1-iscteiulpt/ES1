package antiSpamFilter.test;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import antiSpamFilter.AntiSpamFilterManual;
import antiSpamFilter.Rule;

class AntiSpamFilterManualTest {
	
	/**
	 * Esta classe serve para realizar testes unitarios da classe AntiSpamFilterManual
	 */

	/**
	 * Inicializacao de variaveis para usar nos testes
	 */
	LinkedList<Rule> rulesList = Rule.readRulesFile("testFiles/rules.cf");
	AntiSpamFilterManual manual;
	
	@Test
	void test() throws Exception {
		manual = new AntiSpamFilterManual(rulesList, 
				"testFiles/spam.log.txt", 
				"testFiles/ham.log.txt");
		assertEquals(manual.getEmailHam().size(),695);
		assertEquals(manual.getEmailSpam().size(),239);
		assertEquals(manual.getRules().size(), 335);
	}

}
