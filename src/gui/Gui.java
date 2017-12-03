package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import antiSpamFilter.AntiSpamFilterManual;
import antiSpamFilter.Rule;

public class Gui {

	private JFrame frame;
	private JTextField rulesTextField;
	private JTextField spamTextField;
	private JTextField hamTextField;
	private JTextField fpTextField;
	private JTextField fnTextField;
	private JTable table;

	private boolean editable; /* Serve para gerir a editabilidade da tabela */

	private LinkedList<Rule> rulesList; /* Lista de regras */

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public Gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Anti-Spam Mestre");
		frame.setBounds(100, 100, 900, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		/******* Painel de caminhos para os ficheiros ***************/
		JPanel pathsPanel = new JPanel();
		frame.getContentPane().add(pathsPanel, BorderLayout.NORTH);
		pathsPanel.setLayout(new GridLayout(4, 1, 0, -5));

		/****** Painel Ham ********/
		JPanel hamPanel = new JPanel();
		pathsPanel.add(hamPanel);

		JLabel hamLabel = new JLabel("Ham:");
		hamPanel.add(hamLabel);

		hamTextField = new JTextField();
		hamPanel.add(hamTextField);
		hamTextField.setColumns(50);
		/****************************************/

		/****** Painel Spam ***********/
		JPanel spamPanel = new JPanel();
		pathsPanel.add(spamPanel);

		JLabel spamLabel = new JLabel("Spam:");
		spamPanel.add(spamLabel);

		spamTextField = new JTextField();
		spamPanel.add(spamTextField);
		spamTextField.setColumns(50);
		/******************************************/

		/********* Painel Rules *********/
		JPanel rulesPanel = new JPanel();
		pathsPanel.add(rulesPanel);
		rulesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel rulesLabel = new JLabel("Rules:");
		rulesPanel.add(rulesLabel);

		rulesTextField = new JTextField();
		rulesPanel.add(rulesTextField);
		rulesTextField.setColumns(50);

		JButton rulesUploadButton = new JButton("Upload");

		rulesPanel.add(rulesUploadButton);
		/******************************************/

		/****** Painel config checkbox ********/
		JPanel panelConfigChcbx = new JPanel();
		pathsPanel.add(panelConfigChcbx);

		JLabel labelConfig = new JLabel("Config:");
		panelConfigChcbx.add(labelConfig);

		JCheckBox chckbxManual = new JCheckBox("Manual");
		panelConfigChcbx.add(chckbxManual);

		JCheckBox chckbxAutomatica = new JCheckBox("Autom\u00E1tica");
		panelConfigChcbx.add(chckbxAutomatica);
		
/****************Para impedir que as 2 checkbx estejam selecionadas ao msm tempo********/
		chckbxManual.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chckbxAutomatica.isSelected()) {
					chckbxAutomatica.setSelected(false);
				}
			}
		});
		;
		
		chckbxAutomatica.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chckbxManual.isSelected()) {
					chckbxManual.setSelected(false);
				}
			}
		});
		;
		
		/* Falsos Positivos */
		JLabel labelFP = new JLabel("FP");
		panelConfigChcbx.add(labelFP);

		fpTextField = new JTextField();
		panelConfigChcbx.add(fpTextField);
		fpTextField.setColumns(10);
		fpTextField.setEditable(false);
		/**/
		/* Falsos Negativos */
		JLabel labelFN = new JLabel("FN");
		panelConfigChcbx.add(labelFN);

		fnTextField = new JTextField();
		panelConfigChcbx.add(fnTextField);
		fnTextField.setColumns(10);
		fnTextField.setEditable(false);
		/**/
		/************************************/

		/******** Painel Inferior Avaliar+Gerar+Obter Grafico *************/
		JPanel panelSouth = new JPanel();
		frame.getContentPane().add(panelSouth, BorderLayout.SOUTH);
		panelSouth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton buttonAvalConfig = new JButton("Avaliar Configura\u00E7\u00E3o Manual");
		buttonAvalConfig.setHorizontalAlignment(SwingConstants.LEFT);
		buttonAvalConfig.setVerticalAlignment(SwingConstants.BOTTOM);
		panelSouth.add(buttonAvalConfig);

		JButton buttonGerarConfig = new JButton("Gerar Configura\u00E7\u00E3o Autom\u00E1tica");
		buttonGerarConfig.setHorizontalAlignment(SwingConstants.RIGHT);
		panelSouth.add(buttonGerarConfig);

		JButton buttonObterGrafico = new JButton("Obter Gr\u00E1fico");
		panelSouth.add(buttonObterGrafico);

		JButton buttonGravar = new JButton("Gravar");
		panelSouth.add(buttonGravar);
		buttonGravar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				try {
					writeRules();
//				} catch (NullPointerException exception) {
//					System.out.println("");
//				}

			}
		});
		/***********************************************/

		/* Tabela para regras */
		table = new JTable();

		/* A��o para carregar no bot�o e apresentar regras na tabela */
		rulesUploadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chckbxManual.isSelected()) {
					editable = true;
					uploadRules(rulesTextField.getText());
				}
			}
		});
		;
		/*botao Avaliar a config manual e inserir pf e fn*/
		buttonAvalConfig.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AntiSpamFilterManual avalManual= 
						new AntiSpamFilterManual(rulesList, spamTextField.getText(), hamTextField.getText());
				fpTextField.setText(""+avalManual.getFP());
				fnTextField.setText(""+avalManual.getFN());
				
			}
		});
		;

		JScrollPane scrollbar = new JScrollPane(table);
		frame.getContentPane().add(scrollbar, BorderLayout.CENTER);
		
	}

	/*
	 * ap�s pressionar o bot�o de upload com path na text box as regras e
	 * respetivos pesos s�o apresentados numa tabela
	 */
	private void uploadRules(String rulesPath) {
		this.rulesList = Rule.readRulesFile(rulesPath);
		DefaultTableModel model = new DefaultTableModel() {

			/* Vetor nomes das colunas */
			String[] colNames = { "Rule", "Weight" };

			/* Nomes das Colunas */
			@Override
			public String getColumnName(int column) {
				// TODO Auto-generated method stub
				return colNames[column];
			}

			/* Numero de Linhas */
			@Override
			public int getRowCount() {
				return rulesList.size();
			}

			/* Numero de Colunas */
			@Override
			public int getColumnCount() {
				return 2;
			}
			

			@Override
			public boolean isCellEditable(int row, int column) {
				return editable;
			}
		};

		for (int i = 0; i < rulesList.size(); i++) {
			Rule rule = rulesList.get(i);
			String name = rule.getName();
			model.setValueAt(name, i, 0);

			if (rule.hasWeight()) {
				double weight = rule.getWeight();
				String strWeight = "" + weight;
				model.setValueAt(strWeight, i, 1);
			}

		}
		table.setModel(model);
	}

	/* faz update na lista de regras */
	private void updateList() {
		for (int i = 0; i < rulesList.size(); i++) {
			Rule rule = rulesList.get(i);
			TableModel model = table.getModel();
			String name = (String) (model.getValueAt(i, 0));
			rule.setName(name);
			if (model.getValueAt(i, 1) != null) {
				String strWeight = (String) (model.getValueAt(i, 1));
				double weight = Double.parseDouble(strWeight.trim());
//				System.out.println(weight);
				rule.setWeight(Rule.adjustWeight(weight));
			} else {
				rule.setWeight(0.0);
			}

		}
	}
	
	
	/* Escreve as regras e respetivos pesos */
	private void writeRules() {
		updateList();
		try {
			Writer writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("./files/rules_v1.cf")));
			String rules = Rule.rulesListToString(rulesList);
			writer.write(rules);
			writer.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("DONE");
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
