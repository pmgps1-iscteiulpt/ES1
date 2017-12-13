package antiSpamFilter;

import java.util.ArrayList;
import java.util.LinkedList;

public class Email {

	private LinkedList<Rule> rulesPerMail; /* lista de regras para cada mail */
	private String id; /*id do e-mail*/

	/*Construtor, recebe a string do email que esta no ficheiro*/
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

	/*obter id do e mail*/
	public String getId() {
		return id;
	}

	/*obter lista de regras do e mail*/
	public LinkedList<Rule> getRulesPerMail() {
		return rulesPerMail;
	}
}
