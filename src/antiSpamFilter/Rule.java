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

	/*obter nome da regra*/
	public String getName() {
		return name;
	}

	/*alterar nome da regra*/
	public void setName(String name) {
		this.name = name;
	}

	/* Peso */
	private Double weight;

	/*obter peso da regra*/
	public Double getWeight() {
			return weight;
	}

	/*alterar peso da regra*/
	public void setWeight(Double weight) {
		this.weight = weight;
	}

	/* Vari�vel que indica se a regra tem, ou n�o, peso */
	private boolean hasWeight;

	/*obter peso*/
	public boolean hasWeight() {
		return hasWeight;
	}

	/*alterar peso*/
	public void setHasWeight(boolean hasWeight) {
		this.hasWeight = hasWeight;
	}

	/*
	 * Construtor que s� estabelece o nome para o ficheiro inicial que ainda n�o
	 * tem pesos
	 */
	public Rule(String name) {
		this.name = name;
		this.hasWeight = false;
	}

	/* Construtor para quando o ficheiro j� cont�m pesos */
	public Rule(String name, double weight) {
		this.name = name;
		this.weight = weight;
		this.hasWeight = true;
	}

	/*
	 * L� um ficheiro de regras e devolve uma lista com as regras e respetivos
	 * pesos
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

	/*metodo que converte uma lista de regras para uma string, para posteriormente
	 * essa string ser escrita num ficheiro*/
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

	/*metodo que ajusta o peso se tiver fora dos limites*/
	public static double adjustWeight(double weight) {
		if (weight < -5)
			return -5;
		if (weight > 5)
			return 5;
		return weight;
	}

	/*metodo to string da regra -> disposicao de cada regra no ficheiro*/
	@Override
	public String toString() {
		if (weight == null)
			return name;
		else
			return name + "=" + weight;
	}

}
