package antiSpamFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Rule {

	/* Nome da Regra */
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/* Peso */
	private Integer weight;

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	/* Variável que indica se a regra tem, ou não, peso */
	private boolean hasWeight;

	public boolean hasWeight() {
		return hasWeight;
	}

	public void setHasWeight(boolean hasWeight) {
		this.hasWeight = hasWeight;
	}

	/*
	 * Construtor que só estabelece o nome para o ficheiro inicial que ainda não tem
	 * pesos
	 */
	public Rule(String name) {
		this.name = name;
		this.hasWeight = false;
	}

	/* Construtor para quando o ficheiro já contêm pesos */
	public Rule(String name, int weight) {
		this.name = name;
		this.weight = weight;
		this.hasWeight = true;
	}

	/*
	 * Lê um ficheiro de regras e devolve uma lista com as regras e respetivos pesos
	 */
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

	/* Lê a String de uma linha do ficheiro rules e devolve um objeto Rule */
	private static Rule readRule(String s) {
		if (s.contains("»")) {
			String[] vector = s.split("»");
			System.out.println(vector[1]);
			// for(String str : vector)
			// str.trim();

			return new Rule(vector[0].trim(), Integer.parseInt(vector[1].trim()));
		} else {
			return new Rule(s.trim());
		}
	}

	public static String rulesListToString(List<Rule> list) {
		String str = "";
		for (Rule rule : list) {
			if (list.indexOf(rule) == list.size() - 1)
				str += rule.toString();
			else
				str += rule.toString() + "\n";
		}
		return str;
	}

	@Override
	public String toString() {
		if (weight == null)
			return name;
		else
			return name + "»" + weight;
	}

}
