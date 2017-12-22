package antiSpamFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Utils {
	private String rule = "";
	@SuppressWarnings("unused")
	private String spam = "";
	@SuppressWarnings("unused")
	private String ham = "";
	private LinkedList<Rule> rules; /** lista de regras */
	private LinkedList<Email> emailHam; /** E mails considerados legitimos */
	private LinkedList<Email> emailSpam; /** E mails considerados spam */

	/**
	 * construtor para config automatica
	 */
	public Utils() {
		rule = "files/rules.cf";
		String spam = "files/spam.log.txt";
		String ham = "files/ham.log.txt";
		rules = Rule.readRulesFile(rule);
		emailHam = readHamFile(ham);
		emailSpam = readSpamFile(spam);
	}

	/**
	 * construtor para config manual
	 * 
	 * @param rules2-lista
	 *            de regras
	 * @param spam-nome
	 *            ficheiro spam
	 * @param ham-nome
	 *            ficheiro ham
	 */
	public Utils(LinkedList<Rule> rules2, String spam, String ham) {
		this.rules = rules2;
		this.spam = spam;
		this.ham = ham;
		emailHam = readHamFile(ham);
		emailSpam = readSpamFile(spam);
	}

	/**
	 * 
	 * @return devolve lista de email Ham
	 */
	public LinkedList<Email> getEmailHam() {
		return emailHam;
	}

	/**
	 * 
	 * @return devolve lista com email spam
	 */
	public LinkedList<Email> getEmailSpam() {
		return emailSpam;
	}
/**
 * 
 * @return devolve lista com as regras
 */
	public LinkedList<Rule> getRules() {
		return rules;
	}

	/**
	 * 
	 * @param fileName
	 * @return devolve lista dos emails ham
	 */
	public static LinkedList<Email> readHamFile(String fileName) {
		LinkedList<Email> list = new LinkedList<Email>();
		Scanner file;
		try {
			file = new Scanner(new File(fileName));

			while (file.hasNext()) {
				String line = file.nextLine();
				Email email = new Email(line);
				list.add(email);
			}
			file.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Path Not Found!");
		}
		return list;
	}

	/**
	 * @return metodo que le o ficheiro spam 
	 * 
	 */
	public static LinkedList<Email> readSpamFile(String fileName) {
		LinkedList<Email> list = new LinkedList<Email>();
		Scanner file;
		try {
			file = new Scanner(new File(fileName));

			while (file.hasNext()) {
				String line = file.nextLine();
				Email email = new Email(line);
				list.add(email);
			}
			file.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Path Not Found!");
		}
		return list;
	}
}
