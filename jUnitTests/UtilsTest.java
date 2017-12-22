package antiSpamFilter.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import antiSpamFilter.Email;
import antiSpamFilter.Utils;

class UtilsTest {
	
	/**
	 * Esta classe serve para fazer testes unitários na classe Utils
	 * @author agasa-iscteiulpt
	 */

	/**
	 * Variaveis com o path dos ficheiros neceesarios ao teste
	 */
	static final String hamPath = "testFiles/ham.log.txt";
	static final String spamPath = "testFiles/spam.log.txt";

	@Test
	void testReadHamFile() {
		LinkedList<Email> ham = Utils.readHamFile(hamPath);
		assertEquals(ham.size(), 695);
	}
	
	@Test
	void testReadSpamFile() {
		LinkedList<Email> spam = Utils.readHamFile(spamPath);
		assertEquals(spam.size(),239);
	}

}
