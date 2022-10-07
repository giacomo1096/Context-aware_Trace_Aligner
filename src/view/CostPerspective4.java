/*
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
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;



public class CostPerspective3 extends JDialog{

	private static final long serialVersionUID = 1L;

	//protected H_CostPerspective _handler;
	
    private Container content;
	
	private JPanel northPanel;
    private JPanel centralPanel;

	
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
	private JLabel blankLabel;

    //
    private JPanel constraintPanel;
	private JPanel addActivityButtonPanel;
	
	private JLabel firstBoxLabel;
	private JLabel secondBoxLabel;
	private JLabel thirdBoxLabel;
	private JLabel fourthBoxLabel;
	private JLabel fifthBoxLabel;
    private JComboBox<String> patternComboBox;
	private JComboBox<String> firstActivityComboBox;
	private JComboBox<String> secondActivityComboBox;
	private JTextField costField1;
	private JTextField costField2;

    private DefaultListModel<String> constraintsListModel;
	private JList<String> constraintsList;
	private JScrollPane constraintsScrollPane;
    private JButton rightButton;
	private JButton removeButton;
		
	public CostPerspective3(){
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
		
		this.setTitle("STEP 4: Associate Costs to Activities");
		
		northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(690,90));
		northPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		northPanel.setLayout(new FlowLayout());

        centralPanel = new JPanel();
		centralPanel.setPreferredSize(new Dimension(690,225));
		centralPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		centralPanel.setLayout(new FlowLayout());
		
		activitiesComboBox = new JComboBox<String>();
		activitiesComboBox.insertItemAt("-- Name of the Activity --", 0);
		
		fixedCostLabel = new JLabel(" Fixed Costs:");
		fixedCostLabel.setPreferredSize(new Dimension(435,30));
		
		for(int kind=0;kind<Constants.getActivitiesRepository_vector().size();kind++) {
			String activity = Constants.getActivitiesRepository_vector().elementAt(kind);
			activitiesComboBox.insertItemAt(activity, kind+1);
		}		
		activitiesComboBox.setPreferredSize(new Dimension(300,25));
		activitiesComboBox.setSelectedIndex(0);		
		activitiesComboBox.setEnabled(true);
		addingCostField = new JTextField("Adding Cost");
		addingCostField.setPreferredSize(new Dimension(100,25));
		addingCostField.setEnabled(true);
		removalCostField = new JTextField("Removal Cost");
		removalCostField.setPreferredSize(new Dimension(100,25));
		removalCostField.setEnabled(true);

				
		northPanel.add(fixedCostLabel);
		northPanel.add(activitiesComboBox);
		northPanel.add(addingCostField);
		northPanel.add(removalCostField);
		

//
        costModelLabel = new JLabel(" Cost Models:");
		costModelLabel.setPreferredSize(new Dimension(435,30));


        firstBoxLabel = new JLabel("Pattern:");
		firstBoxLabel.setPreferredSize(new Dimension(90,25));
		secondBoxLabel = new JLabel("Activity#1:");
		secondBoxLabel.setPreferredSize(new Dimension(90,25));
		thirdBoxLabel = new JLabel("Activity#2:");
		thirdBoxLabel.setPreferredSize(new Dimension(90,25));


		fourthBoxLabel = new JLabel("Cost#1:");
		fourthBoxLabel.setPreferredSize(new Dimension(90,25));
		fifthBoxLabel = new JLabel("Cost#2:");
		fifthBoxLabel.setPreferredSize(new Dimension(90,25));
		
		patternComboBox = new JComboBox<String>();
		patternComboBox.setPreferredSize(new Dimension(200,25));
		
		patternComboBox.addItem("---");
		
		patternComboBox.addItem("pattern1");	
		patternComboBox.addItem("pattern2");
		patternComboBox.addItem("pattern3");
		patternComboBox.addItem("pattern4");
		
		
		
	
		firstActivityComboBox = new JComboBox<String>();
		firstActivityComboBox.addItem("---");
		firstActivityComboBox.setPreferredSize(new Dimension(200,25));
		firstActivityComboBox.setEnabled(false);
		secondActivityComboBox = new JComboBox<String>();
		secondActivityComboBox.addItem("---");
		secondActivityComboBox.setPreferredSize(new Dimension(200,25));
		secondActivityComboBox.setEnabled(false);
		
		costField1 = new JTextField();
		costField1.setPreferredSize(new Dimension(200,25));
		costField1.setEnabled(false);
		costField2 = new JTextField();
		costField2.setPreferredSize(new Dimension(200,25));
		costField2.setEnabled(false);

		constraintPanel = new JPanel();
		constraintPanel.setLayout(new FlowLayout());
		constraintPanel.setPreferredSize(new Dimension(300,150));
		constraintPanel.add(firstBoxLabel);
		constraintPanel.add(patternComboBox);
		constraintPanel.add(secondBoxLabel);
		constraintPanel.add(firstActivityComboBox);
		constraintPanel.add(thirdBoxLabel);
		constraintPanel.add(secondActivityComboBox);

		constraintPanel.add(fourthBoxLabel);
		constraintPanel.add(costField1);
		constraintPanel.add(fifthBoxLabel);
		constraintPanel.add(costField2);

		
		//
		// JPanel containing the JButtons for adding new activities in the alphabet
		//
		addActivityButtonPanel = new JPanel();
		addActivityButtonPanel.setPreferredSize(new Dimension(100,100)); 
		addActivityButtonPanel.setLayout(new FlowLayout());
		
		rightButton = new JButton("ADD>>");
		rightButton.setPreferredSize(new Dimension(90,25)); 
		removeButton = new JButton("<<DEL");
		removeButton.setPreferredSize(new Dimension(90,25));
		
		addActivityButtonPanel.add(rightButton);
		addActivityButtonPanel.add(removeButton);

		constraintsListModel = new DefaultListModel<String>();
		constraintsList = new JList<String>(constraintsListModel);
		constraintsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		constraintsList.setSelectedIndex(-1);

		constraintsScrollPane = new JScrollPane(constraintsList);
		constraintsScrollPane.setPreferredSize(new Dimension(240,140));
		constraintsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		constraintsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        centralPanel.add(costModelLabel);
        centralPanel.add(constraintPanel);
		centralPanel.add(addActivityButtonPanel);
		centralPanel.add(constraintsScrollPane);

        //
		
		blankLabel = new JLabel();
		blankLabel.setPreferredSize(new Dimension(150,20));
		nextStepButton = new JButton("Next Step >");
		previousStepButton = new JButton("< Previous Step");
	
		southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(640,60));
		southPanel.setLayout(new FlowLayout());
		southPanel.add(previousStepButton);
		southPanel.add(blankLabel);
		southPanel.add(nextStepButton);
			
		this.add(northPanel);
        this.add(centralPanel);
		this.add(southPanel);		

		this.setSize(690,400);
		this.setResizable(false);	
		
        int width = this.getWidth();
        int height = this.getHeight();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds((screenSize.width / 2) - (width / 2), (screenSize.height / 2) - (height / 2), width, height);

	}
	
	private void initHandler() {
		
		//_handler = new H_CostPerspective(this);
		
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
*/
