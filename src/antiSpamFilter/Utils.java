package antiSpamFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Utils {
	private String rule="";
	private String spam=""; 
	private String ham="";
	private LinkedList<Rule> rules; /*lista de regras*/
	private LinkedList<Email> emailHam; /*E mails considerados legitimos*/
	private LinkedList<Email> emailSpam; /*E mails considerados spam*/
	/**
	 * construtor para config automatica
	 */
	public Utils() {
		rule="files/rules.cf";
		String spam="files/spam.log.txt";
		String ham="files/ham.log.txt";
		emailHam = new LinkedList<>();
		emailSpam = new LinkedList<>();
		rules=readRulesFile(rule);
		readHamFile(ham);
		readSpamFile(spam);
	}
	/**
	 * construtor para config manual
	 * @param rules2-lista de regras
	 * @param spam-nome ficheiro spam
	 * @param ham-nome ficheiro ham
	 */
	public Utils(LinkedList<Rule> rules2,String spam, String ham) {
		emailHam = new LinkedList<>();
		emailSpam = new LinkedList<>();
		this.rules=rules2;
		this.spam=spam;
		this.ham=ham;
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
			return new Rule(vector[0].trim(), Double.parseDouble(vector[1].trim()));
		} else {
			return new Rule(s.trim());
		}
	}
}
