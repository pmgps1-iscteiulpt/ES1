package antiSpamFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;

import gui.Gui;

public class AntiSpamFilterProblem extends AbstractDoubleProblem {

	private static final int NUM_OF_RULES = 335; // mudar
	private static final int THRESHOLD = 5;
	LinkedList<Email> emailHam; /* E mails considerados legitimos */
	LinkedList<Email> emailSpam; /* E mails considerados spam */
	LinkedList<Rule> rules;
	private int fp; /* Variavel para guardar os falsos positivos */
	private int fn;/* Variavel para guardar os falsos negativos */

	public AntiSpamFilterProblem() {
		this(NUM_OF_RULES);
		Utils utils=new Utils();
		emailHam=utils.getEmailHam();
		emailSpam=utils.getEmailSpam();
		rules=utils.getRules();
	}

	public AntiSpamFilterProblem(Integer numberOfVariables) {
		setNumberOfVariables(numberOfVariables);
		setNumberOfObjectives(2);
		setName("AntiSpamFilterProblem");

		List<Double> lowerLimit = new ArrayList<>(getNumberOfVariables());
		List<Double> upperLimit = new ArrayList<>(getNumberOfVariables());

		for (int i = 0; i < getNumberOfVariables(); i++) {
			lowerLimit.add(-5.0);
			upperLimit.add(5.0);
		}

		setLowerLimit(lowerLimit);
		setUpperLimit(upperLimit);
	}

	public void evaluate(DoubleSolution solution) {
//		double aux, xi, xj;
		double[] fx = new double[getNumberOfObjectives()];
		fp=0;
		fn=0;
		double[] x = new double[getNumberOfVariables()];
		for (int i = 0; i < solution.getNumberOfVariables(); i++) {
			x[i] = solution.getVariableValue(i);
			rules.get(i).setWeight(x[i]);
		}
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
		solution.setObjective(0, fp);
		solution.setObjective(1, fn);
		
//		fx[0] = 0.0;
//		for (int var = 0; var < solution.getNumberOfVariables() - 1; var++) {
//			fx[0] += Math.abs(x[0]); // Example for testing
//		}
//
//		fx[1] = 0.0;
//		for (int var = 0; var < solution.getNumberOfVariables(); var++) {
//			fx[1] += Math.abs(x[1]); // Example for testing
//		}
//		
//		solution.setObjective(0, fx[0]);
//		solution.setObjective(1, fx[1]);
	}

	public int getFn() {
		return fn;
	}

	public int getFp() {
		return fp;
	}
}
