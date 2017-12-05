package antiSpamFilter;

import java.util.ArrayList;
import java.util.LinkedList;

public class Email {

	private LinkedList<Rule> rulesPerMail; /* lista de regras para cada mail */
	private String id;

	public Email(String email) {
		rulesPerMail = new LinkedList<>();
		System.out.println(email);
		String[] argumentos = email.split("\\s+");  /* documento usa tabs*/
		System.out.println(argumentos.length);
		id = argumentos[0].trim();

		for (int i = 1; i < argumentos.length; i++) {
			Rule rule = new Rule(argumentos[i].trim());
			System.out.println(rule.getName());
			rulesPerMail.add(rule);
		}
	}

	public String getId() {
		return id;
	}

	public LinkedList<Rule> getRulesPerMail() {
		return rulesPerMail;
	}
}
