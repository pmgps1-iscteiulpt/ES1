package antiSpamFilter.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import antiSpamFilter.Email;
import antiSpamFilter.Rule;

class EmailTest {
	
	/**
	 * Esta classe JUnit testa a classe Email
	 * @author agasa-iscteuilpt
	 * 
	 */
	
	/**
	 * Atributos para instanciar Email's para teste	
	 */
	
	Email email1;
	Email email2;
	Email email3;
	Email email4;
	
	/*
	 * Atributos do tipo String para fins de teste (nomear os emails)
	 */
	
	String name1 = "xval_initial/9/_ham_/00035.a0e0e8cdca0b8352a9e9c2c81e5d5cd7"
			+ "	BAYES_00	HTML_FONT_SIZE_LARGE	HTML_MESSAGE	"
			+ "MIME_HTML_ONLY	SPF_FAIL";
	
	String name2 = "xval_initial/9/_ham_/00353.ec01b62420323fffe253643fe0439c86	"
			+ "BAYES_00	RDNS_NONE";
	
	/**
	 * Listas de regras para cada email
	 */

	/**
	 * Inicializacao das variáveis
	 */
	@BeforeEach
	void setUp() throws Exception {
		email1 = new Email(name1);
		email3 = new Email(name2);
		email4 = new Email(name1);
	}
	
	/**
	 * Verificacao da inicializacao das variaveis
	 */

	@Test
	void testInicializacao() {
		assertNotNull(email1);
		assertNotNull(email3);
		assertNotNull(email4);
		assertNull(email2);
	}
	
	/**
	 * Verificacao das listas de regras
	 */
	
	@Test
	void testRulesList() {
		assertSame(email1.getRulesPerMail().size(), 5);
		assertSame(email3.getRulesPerMail().size(), 2);
		assertNotEquals(email1.getRulesPerMail(), email3.getRulesPerMail());
		assertEquals(email1.getRulesPerMail(), email4.getRulesPerMail());
	}
	
	/**
	 * Testar o metodo getId() 
	 */
	
	@Test void testGetId() {
		assertEquals(email1.getId(),email4.getId());
		assertNotEquals(email1.getId(),email3.getId());
	}
	
	

}
