package antiSpamFilter.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import antiSpamFilter.Rule;

class RuleTest {
	
	/**
	 * Esta classe serve para testar a classe Rule
	 * @author agasa-iscteiulpt
	 */
	
	/**
	 * variaveis locais para auxilio no teste da classe
	 */
	
	Rule rule1;
	Rule rule2;
	Rule rule3;
	
	private static final double EPSILON = 0.0;
	
	/**
	 * inicializacao das variaveis locais para teste
	 * @throws Exception
	 */

	@BeforeEach
	void setUp() throws Exception {
		rule1 = new Rule("Rule1");
		rule2 = new Rule("Rule2",3.0);
		rule3 = new Rule("Rule2",3.0);
	}

	
	/**
	 * Testar os metodos getName e getWeight
	 */
	@Test
	void testGetters() {
		assertEquals(rule1.getName(),"Rule1");
		assertEquals(rule2.getWeight(),3.0,EPSILON);
	}
	
	/**
	 * Testar os metodos setName e getName
	 */
	@Test
	void testSetters() {
		rule1.setName("Rule");
		assertEquals(rule1.getName(),"Rule");
		rule1.setWeight(3.2);
		assertEquals(rule1.getWeight(),3.2,EPSILON);
	}
	
	/**
	 * Testar a igualdade das regras
	 */
	@Test
	void testEquality() {
		assertEquals(rule2, rule3);
		assertNotEquals(rule1,rule2);
	}
	
	/**
	 * testar o metodo hasWeight
	 */
	@Test
	void testHasWeight() {
		assertFalse(rule1.hasWeight());
		assertTrue(rule2.hasWeight());
		rule1.setHasWeight(true);
		assertTrue(rule1.hasWeight());
	}
	
	/**
	 * Testa o método readRulesFile/1 e consequentemente o metodo readRule/1
	 */
	
	@Test
	void testReadRulesFile() {
		LinkedList<Rule> rulesList = Rule.readRulesFile("testFiles/rules.cf");
		assertEquals(rulesList.size(), 335);
		assertEquals(rulesList.peek().getName(), "BAYES_00");
	}
	
	/**
	 * Testa o metodo rulesListToString/1
	 */
	
	@Test
	void testRulesListToString() {
		LinkedList<Rule> rulesList = Rule.readRulesFile("testFiles/rules.cf");
		String rules = Rule.rulesListToString(rulesList);
		String[] divideLines = rules.split("\n");
		assertEquals(divideLines.length, 335);
	}
	
	/**
	 * Testa o metodo pickIdealLine/1
	 */
	
	@Test
	void testPickIdealLine() {
		int idealLine = Rule.pickIdealLine("testFiles/AntiSpamFilterProblem.NSGAII.rf");
		assertEquals(idealLine,4);
	}
	
	
	/**
	 * Testa o metodo readAutomaticRules/1
	 */
	
	@Test
	void testReadAutomaticRules() {
		String path = "testFiles/AntiSpamFilterProblem.NSGAII.rs";
		String[] vector = Rule.readAutomaticRules(path, 1);
		assertEquals(vector.length,335);
	}
	
	/**
	 * Testa o metodo adjustWeight/1
	 */
	
	@Test
	void testAdjustWeight() {
		double weight1 = Rule.adjustWeight(6.0);
		double weight2 = Rule.adjustWeight(-9.0);
		double weight3 = Rule.adjustWeight(2.3);
		assertEquals(weight1,5.0,EPSILON);
		assertEquals(weight2,-5.0,EPSILON);
		assertEquals(weight3,2.3,EPSILON);
	}
	
	/**
	 * Testa o metodo toString/0
	 */
	
	@Test
	void testToString() {
		assertEquals(rule1.toString(),"Rule1");
		assertEquals(rule2.toString(),"Rule2=3.0");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
