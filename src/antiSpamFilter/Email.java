package antiSpamFilter;

import java.util.LinkedList;

public class Email {

	private LinkedList<Rule> rulesPerMail; /** lista de regras para cada mail */
	private String id; /** id do e-mail */

	/**
	 * Construtor, recebe a string do email que esta no ficheiro 
	 * @param email
	 */
	public Email(String email) {
		rulesPerMail = new LinkedList<>();
		String[] argumentos = email.split("\\s+"); /** documento usa tabs */
		id = argumentos[0].trim(); 

		for (int i = 1; i < argumentos.length; i++) {
			Rule rule = new Rule(argumentos[i].trim());
			rulesPerMail.add(rule);
		}
	}

	/**
	 * 
	 * @return devolve id do e mail
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return devolve a lista de regras do e mail 
	 * */
	public LinkedList<Rule> getRulesPerMail() {
		return rulesPerMail;
	}

	/**
	 * 
	 * @param rules lista com as regras
	 * @param x lista com os pesos provisórios
	 * @return devolve o somatorio dos pesos das regras
	 */
	public int fpfn(LinkedList<Rule> rules, double[] x) {
		int count = 0;
			for (int i = 0; i < rulesPerMail.size(); i++) {
				for (int j = 0; j < rules.size(); j++) {
				if (rules.get(j).getName().equals(rulesPerMail.get(i).getName())) {
					count += x[j];
				}
			}
		}
		return count;
	}
}
