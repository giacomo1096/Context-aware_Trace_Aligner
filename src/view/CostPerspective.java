package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

import control.H_CostPerspective;
import main.Constants;

public class CostPerspective extends JDialog{

	private static final long serialVersionUID = 1L;

	protected H_CostPerspective _handler;
	
    private Container content;
	
	private JPanel northPanel;
	
	private JLabel fixedCostLabel;
	
	private JComboBox<String> activitiesComboBox;
	private JTextField addingCostField;
	private JTextField removalCostField;
	
	private JLabel costModelLabel;
	private JTextArea costModelTextArea;
	private JScrollPane costModelScrollPane;

	private JPanel southPanel;
	
	private JButton nextStepButton;
	private JButton previousStepButton;
	private JButton importDFAButton;
	private JLabel blankLabel;
		
	public CostPerspective(){
		super();
		setModal(true);
		initComponent();
		initHandler();
		setVisible(true);
	}

	private void initComponent() {
		
		content = getContentPane();
		content.setLayout(new FlowLayout());
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
		
		this.setTitle("Associate Costs to Activities");
		
		northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(440,250));
		northPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		northPanel.setLayout(new FlowLayout());
		
		activitiesComboBox = new JComboBox<String>();
		activitiesComboBox.insertItemAt("-- Name of the Activity --", 0);
		
		fixedCostLabel = new JLabel(" Fixed Costs:");
		fixedCostLabel.setPreferredSize(new Dimension(435,30));
		
		for(int kind=0;kind<Constants.getActivitiesRepository_vector().size();kind++) {
			String activity = Constants.getActivitiesRepository_vector().elementAt(kind);
			activitiesComboBox.insertItemAt(activity, kind+1);
		}		
		activitiesComboBox.setPreferredSize(new Dimension(210,25));
		activitiesComboBox.setSelectedIndex(0);		
		activitiesComboBox.setEnabled(true);
		addingCostField = new JTextField("Adding Cost");
		addingCostField.setPreferredSize(new Dimension(90,25));
		addingCostField.setEnabled(true);
		removalCostField = new JTextField("Removal Cost");
		removalCostField.setPreferredSize(new Dimension(90,25));
		removalCostField.setEnabled(true);

		costModelLabel = new JLabel(" Cost Models:");
		costModelLabel.setPreferredSize(new Dimension(435,30));
		costModelTextArea = new JTextArea();
		costModelTextArea.setPreferredSize(new Dimension(800,90));
		
		costModelScrollPane = new JScrollPane(costModelTextArea);
		costModelScrollPane.setPreferredSize(new Dimension(400,100));
		costModelScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		costModelScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		importDFAButton = new JButton("Import Cost Model as a DFA");
		
		northPanel.add(fixedCostLabel);
		northPanel.add(activitiesComboBox);
		northPanel.add(addingCostField);
		northPanel.add(removalCostField);
		
		northPanel.add(costModelLabel);
		northPanel.add(costModelScrollPane);
		northPanel.add(importDFAButton);
		
		blankLabel = new JLabel();
		blankLabel.setPreferredSize(new Dimension(150,20));
		nextStepButton = new JButton("Next Step >");
		previousStepButton = new JButton("< Previous Step");
	
		southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(440,40));
		southPanel.setLayout(new FlowLayout());
		southPanel.add(previousStepButton);
		southPanel.add(blankLabel);
		southPanel.add(nextStepButton);
			
		this.add(northPanel);
		this.add(southPanel);		
		
		this.setSize(450,340);
		this.setResizable(false);	
		
        int width = this.getWidth();
        int height = this.getHeight();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds((screenSize.width / 2) - (width / 2), (screenSize.height / 2) - (height / 2), width, height);

	}
	
	private void initHandler() {
		
		_handler = new H_CostPerspective(this);
		
	}

	public JTextArea getCostModelTextArea() {
		return costModelTextArea;
	}

	public JScrollPane getCostModelScrollPane() {
		return costModelScrollPane;
	}
	
	public JButton getNextStepButton() {
		return nextStepButton;
	}

	public JButton getPreviousStepButton() {
		return previousStepButton;
	}
	
	public JButton getImportDFAButton() {
		return importDFAButton;
	}
	
	public JComboBox<String> getActivitiesComboBox() {
		return activitiesComboBox;
	}

	public void setActivitiesComboBox(JComboBox<String> activitiesComboBox) {
		this.activitiesComboBox = activitiesComboBox;
	}

	public JTextField getAddingCostField() {
		return addingCostField;
	}

	public void setAddingCostField(JTextField addingCostField) {
		this.addingCostField = addingCostField;
	}

	public JTextField getRemovalCostField() {
		return removalCostField;
	}

	public void setRemovalCostField(JTextField removalCostField) {
		this.removalCostField = removalCostField;
	}
}