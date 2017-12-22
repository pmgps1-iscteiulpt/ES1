package antiSpamFilter;

import java.util.LinkedList;

public class AntiSpamFilterManual {

	private static final int THRESHOLD = 5;
	private LinkedList<Rule> rules = new LinkedList<>(); /* lista de regras */
	private LinkedList<Email> emailHam; /* E mails considerados legitimos */
	private LinkedList<Email> emailSpam; /* E mails considerados spam */
	private int fp; /* Variavel para guardar os falsos positivos */
	private int fn;/* Variavel para guardar os falsos negativos */

	public AntiSpamFilterManual(LinkedList<Rule> rule, String spam, String ham) {
		fp = 0;
		fn = 0;
		Utils utils = new Utils(rule, spam, ham);
		rules = utils.getRules();
		System.out.println(rules.size());
		emailHam = utils.getEmailHam();
		emailSpam = utils.getEmailSpam();
		test();
	}

	/* metodo para verificar os fp e os fn no ham e spam files */
	private void test() {
		for (int i = 0; i < emailSpam.size(); i++) {
			int count = 0;
			for (int j = 0; j < emailSpam.get(i).getRulesPerMail().size(); j++) {
				for (int j2 = 0; j2 < rules.size(); j2++) {
					if (emailSpam.get(i).getRulesPerMail().get(j).toString().equals(rules.get(j2).getName())) {
						count += rules.get(j2).getWeight();
					}
				}
			}
			if (count < THRESHOLD)
				fn++;
		}
		for (int i = 0; i < emailHam.size(); i++) {
			int count = 0;
			for (int j = 0; j < emailHam.get(i).getRulesPerMail().size(); j++) {
				for (int j2 = 0; j2 < rules.size(); j2++) {
					if (emailHam.get(i).getRulesPerMail().get(j).toString().equals(rules.get(j2).getName())) {
						count += rules.get(j2).getWeight();
					}
				}
			}
			if (count > THRESHOLD)
				fp++;
		}

	}

	public LinkedList<Email> getEmailHam() {
		return emailHam;
	}

	public LinkedList<Email> getEmailSpam() {
		return emailSpam;
	}
	
	public int getFN() {
		return fn;
	}
	
	public int getFP() {
		return fp;
	}
	
	public LinkedList<Rule> getRules() {
		return rules;
	}
	

}
