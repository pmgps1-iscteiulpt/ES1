package antiSpamFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class AntiSpamFilterManual {

	private static final int THRESHOLD = 5;
	LinkedList<Rule> rules;
	LinkedList<Email> emailHam;
	LinkedList<Email> emailSpam;
	private int fp;
	private int fn;

	public AntiSpamFilterManual(LinkedList<Rule> rules, String spam, String ham) {
		fp = 0;
		fn = 0;
		this.rules = rules;
		emailHam = new LinkedList<>();
		emailSpam = new LinkedList<>();
		readSpamFile(spam);
		readHamFile(ham);
		test();
	}
  /* metodo para verificar os pn e os fn no ham e spam files*/
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
			System.out.println(count);
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

	public int getFN() {
		return fn;
	}

	public int getFP() {
		return fp;
	}

}
