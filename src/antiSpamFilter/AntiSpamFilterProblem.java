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

	private static final int NUM_OF_RULES = 335; /* # de regras de file config */
	private static final int THRESHOLD = 5;
	private LinkedList<Email> emailHam; /* E mails considerados legitimos */
	private LinkedList<Email> emailSpam; /* E mails considerados spam */
	private LinkedList<Rule> rules;

	public AntiSpamFilterProblem() {
		this(NUM_OF_RULES);
		Utils utils = new Utils();
		emailHam = utils.getEmailHam();
		emailSpam = utils.getEmailSpam();
		rules = utils.getRules();
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
		int fp = 0;
		int fn = 0;
		double[] x = new double[getNumberOfVariables()];
		for (int i = 0; i < solution.getNumberOfVariables(); i++) {
			x[i] = solution.getVariableValue(i);
			rules.get(i).setWeight(x[i]);
		}
		for (int i = 0; i < emailSpam.size(); i++) {
			int count = emailSpam.get(i).fpfn(rules,x);
			if (count < THRESHOLD) {
				fn++;
			}
		}
		for (int i = 0; i < emailHam.size(); i++) {
			int count = emailHam.get(i).fpfn(rules,x);
			if (count > THRESHOLD) {
				fp++;
			}
		}
		solution.setObjective(0, fp);
		solution.setObjective(1, fn);
	}
}
