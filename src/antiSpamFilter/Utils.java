package antiSpamFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Utils {
	private final String rule="files/rules.cf";
	private final String spam="files/spam.log.txt";
	private final String ham="files/ham.log.txt";
	LinkedList<Rule> rules; /*lista de regras*/
	LinkedList<Email> emailHam; /*E mails considerados legitimos*/
	LinkedList<Email> emailSpam; /*E mails considerados spam*/
	
	public Utils() {
		emailHam = new LinkedList<>();
		emailSpam = new LinkedList<>();
		rules=readRulesFile(rule);
		readHamFile(ham);
		readSpamFile(spam);
	}
	
	public LinkedList<Email> getEmailHam() {
		return emailHam;
	}
	
	public LinkedList<Email> getEmailSpam() {
		return emailSpam;
	}
	
	public LinkedList<Rule> getRules() {
		return rules;
	}
	
	private void readHamFile(String fileName) {
		Scanner file;
		try {
			file = new Scanner(new File(fileName));

			while (file.hasNext()) {
				String line = file.nextLine();
				Email email = new Email(line);
				emailHam.add(email);
			}
			file.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Path Not Found!");
		}
	}

	/* metodo que le o ficheiro spam */
	private void readSpamFile(String fileName) {
		Scanner file;
		try {
			file = new Scanner(new File(fileName));

			while (file.hasNext()) {
				String line = file.nextLine();
				Email email = new Email(line);
				emailSpam.add(email);
			}
			file.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Path Not Found!");
		}
	}
	
	public static LinkedList<Rule> readRulesFile(String fileName) {
		Scanner file;
		LinkedList<Rule> rulesList = new LinkedList<Rule>();
		try {
			file = new Scanner(new File(fileName));

			while (file.hasNext()) {
				String line = file.nextLine();
				Rule rule = readRule(line);
				rulesList.add(rule);
			}
			file.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Path Not Found!");
		}
		return rulesList;
	}

	/* L� a String de uma linha do ficheiro rules e devolve um objeto Rule */
	private static Rule readRule(String s) {
		if (s.contains("=")) {
			String[] vector = s.split("=");
			// for(String str : vector)
			// str.trim();

			return new Rule(vector[0].trim(), Double.parseDouble(vector[1].trim()));
		} else {
			return new Rule(s.trim());
		}
	}
}
