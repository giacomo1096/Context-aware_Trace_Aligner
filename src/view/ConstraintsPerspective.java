package view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

import control.H_ConstraintsPerspective;

public class ConstraintsPerspective extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JComboBox<String> constraintComboBox;
	private JComboBox<String> firstActivityComboBox;
	private JComboBox<String> secondActivityComboBox;
	
	private JPanel constraintPanel;
	private JPanel addActivityButtonPanel;
	
	private JLabel firstBoxLabel;
	private JLabel secondBoxLabel;
	private JLabel thirdBoxLabel;
	private JLabel LTLconstraintLabel;
	private JLabel blankLabel;
	private JLabel blankLabel_2;
		
	private DefaultListModel<String> constraintsListModel;
	private JList<String> constraintsList;
	private JScrollPane constraintsScrollPane;
	
	private JButton rightButton;
	private JButton removeButton;
	
	private JLabel encodingLabel;
    private JRadioButton ICAPS16_encoding_radio_button = new JRadioButton("ICAPS-16");
    private JRadioButton AAAI17_encoding_radio_button = new JRadioButton("AAAI-17", true);
    //private ButtonGroup radio_button_group = new ButtonGroup();
	
	private JButton nextStepButton;
	private JButton previousStepButton;
	
	protected H_ConstraintsPerspective _handler;
	
	public ConstraintsPerspective(){
		super();
		initComponent();
		initHandler();		
	}

	private void initComponent() {
		
		this.setLayout(new FlowLayout());
		this.setBorder(new TitledBorder(null, "STEP 3: Define LTL/DECLARE constraints", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		firstBoxLabel = new JLabel("Constraint:");
		firstBoxLabel.setPreferredSize(new Dimension(90,25));
		secondBoxLabel = new JLabel("Activity#1:");
		secondBoxLabel.setPreferredSize(new Dimension(90,25));
		thirdBoxLabel = new JLabel("Activity#2:");
		thirdBoxLabel.setPreferredSize(new Dimension(90,25));
		
		constraintComboBox = new JComboBox<String>();
		constraintComboBox.setPreferredSize(new Dimension(200,25));
		
		constraintComboBox.addItem("---");
		
		//RULES having one argument
		constraintComboBox.addItem("existence");
		constraintComboBox.addItem("absence");
		constraintComboBox.addItem("init");
		//constraintComboBox.addItem("last");		
		
		//RULES having two arguments
		
		//choice and exclusive choice
		constraintComboBox.addItem("choice");
		constraintComboBox.addItem("exclusive choice");
		
		//responded existence and co-existence
		constraintComboBox.addItem("responded existence");	
		constraintComboBox.addItem("not responded existence");
		constraintComboBox.addItem("co-existence");
		constraintComboBox.addItem("not co-existence");
		
		//response, precedence and succession
		constraintComboBox.addItem("response");
		constraintComboBox.addItem("precedence");		
		constraintComboBox.addItem("succession");
		
		//chain response, chain precedence and chain succession
		constraintComboBox.addItem("chain response");
		constraintComboBox.addItem("chain precedence");
		constraintComboBox.addItem("chain succession");
		
		//alternate response, alternate precedence and alternate succession
		constraintComboBox.addItem("alternate response");
		constraintComboBox.addItem("alternate precedence");
		constraintComboBox.addItem("alternate succession");
		
		//not response, not precedence and not succession
		constraintComboBox.addItem("not response");
		constraintComboBox.addItem("not precedence");
		constraintComboBox.addItem("not succession");
		
		
		//not chain response, not chain precedence and not chain succession
		constraintComboBox.addItem("not chain response");
		constraintComboBox.addItem("not chain precedence");
		constraintComboBox.addItem("not chain succession");
		
		//general LTL Constraint
		constraintComboBox.addItem("** LTL constraint **");

		firstActivityComboBox = new JComboBox<String>();
		firstActivityComboBox.addItem("---");
		firstActivityComboBox.setPreferredSize(new Dimension(200,25));
		firstActivityComboBox.setEnabled(false);
		secondActivityComboBox = new JComboBox<String>();
		secondActivityComboBox.addItem("---");
		secondActivityComboBox.setPreferredSize(new Dimension(200,25));
		secondActivityComboBox.setEnabled(false);
		
		
		constraintPanel = new JPanel();
		constraintPanel.setLayout(new FlowLayout());
		constraintPanel.setPreferredSize(new Dimension(300,100));
		constraintPanel.add(firstBoxLabel);
		constraintPanel.add(constraintComboBox);
		constraintPanel.add(secondBoxLabel);
		constraintPanel.add(firstActivityComboBox);
		constraintPanel.add(thirdBoxLabel);
		constraintPanel.add(secondActivityComboBox);
		
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
		constraintsScrollPane.setPreferredSize(new Dimension(240,90));
		constraintsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		constraintsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		LTLconstraintLabel = new JLabel("Corresponding LTL constraint: ");
		LTLconstraintLabel.setPreferredSize(new Dimension(670,25));
		
		encodingLabel = new JLabel("PDDL encoding: ");
		encodingLabel.setPreferredSize(new Dimension(150,40));
		//radio_button_group.add(ICAPS16_encoding_radio_button);
		//radio_button_group.add(AAAI17_encoding_radio_button);
		blankLabel_2 = new JLabel();
		blankLabel_2.setPreferredSize(new Dimension(335,40));
		
		blankLabel = new JLabel();
		blankLabel.setPreferredSize(new Dimension(400,20));
		nextStepButton = new JButton("Next Step >");
		previousStepButton = new JButton("< Previous Step");
		
		this.add(constraintPanel);
		this.add(addActivityButtonPanel);
		this.add(constraintsScrollPane);
		this.add(LTLconstraintLabel);
	    //this.add(encodingLabel);
	    //this.add(ICAPS16_encoding_radio_button);
	    //this.add(AAAI17_encoding_radio_button);
	    //this.add(blankLabel_2);
		this.add(previousStepButton);
		this.add(blankLabel);
		this.add(nextStepButton);
		this.setPreferredSize(new Dimension(690,200));
		this.setVisible(true);
		
	}
	
	public void setComponentEnabled(boolean enabled) {

		firstBoxLabel.setEnabled(enabled);
		secondBoxLabel.setEnabled(enabled);
		thirdBoxLabel.setEnabled(enabled);
		constraintComboBox.setEnabled(enabled);
		constraintComboBox.setSelectedIndex(0);
		rightButton.setEnabled(enabled);
		removeButton.setEnabled(enabled);
		constraintsList.setEnabled(enabled);
		LTLconstraintLabel.setText("Corresponding LTL constraint : ");
		LTLconstraintLabel.setEnabled(enabled);
		encodingLabel.setEnabled(enabled);
		ICAPS16_encoding_radio_button.setEnabled(enabled);
		AAAI17_encoding_radio_button.setEnabled(enabled);
		nextStepButton.setEnabled(enabled);
		previousStepButton.setEnabled(enabled);
		this.setEnabled(enabled);
	}
	
	public void resetComponent() {

		constraintsListModel.removeAllElements();

	}
	
	private void initHandler() {
		
		_handler = new H_ConstraintsPerspective(this);
		
	}

	public JComboBox<String> getConstraintComboBox() {
		return constraintComboBox;
	}

	public JComboBox<String> getFirstActivityComboBox() {
		return firstActivityComboBox;
	}

	public JComboBox<String> getSecondActivityComboBox() {
		return secondActivityComboBox;
	}

	public JLabel getSecondBoxLabel() {
		return secondBoxLabel;
	}

	public DefaultListModel<String> getConstraintsListModel() {
		return constraintsListModel;
	}

	public JList<String> getConstraintsList() {
		return constraintsList;
	}

	public JButton getRightButton() {
		return rightButton;
	}

	public JButton getRemoveButton() {
		return removeButton;
	}

	public JButton getNextStepButton() {
		return nextStepButton;
	}

	public JButton getPreviousStepButton() {
		return previousStepButton;
	}

	public void setConstraintComboBox(JComboBox<String> constraintComboBox) {
		this.constraintComboBox = constraintComboBox;
	}

	public void setFirstActivityComboBox(JComboBox<String> firstActivityComboBox) {
		this.firstActivityComboBox = firstActivityComboBox;
	}

	public void setSecondActivityComboBox(JComboBox<String> secondActivityComboBox) {
		this.secondActivityComboBox = secondActivityComboBox;
	}

	public void setSecondBoxLabel(JLabel secondBoxLabel) {
		this.secondBoxLabel = secondBoxLabel;
	}

	public void setConstraintsListModel(DefaultListModel<String> constraintsListModel) {
		this.constraintsListModel = constraintsListModel;
	}

	public void setConstraintsList(JList<String> constraintsList) {
		this.constraintsList = constraintsList;
	}

	public void setRightButton(JButton rightButton) {
		this.rightButton = rightButton;
	}

	public void setRemoveButton(JButton removeButton) {
		this.removeButton = removeButton;
	}

	public void setNextStepButton(JButton nextStepButton) {
		this.nextStepButton = nextStepButton;
	}

	public void setPreviousStepButton(JButton previousStepButton) {
		this.previousStepButton = previousStepButton;
	}

	public JLabel getLTLconstraintLabel() {
		return LTLconstraintLabel;
	}

	public JRadioButton getICAPS16_encoding_radio_button() {
		return ICAPS16_encoding_radio_button;
	}

	public JRadioButton getAAAI17_encoding_radio_button() {
		return AAAI17_encoding_radio_button;
	}

	public void setICAPS16_encoding_radio_button(
			JRadioButton iCAPS16_encoding_radio_button) {
		ICAPS16_encoding_radio_button = iCAPS16_encoding_radio_button;
	}

	public void setAAAI17_encoding_radio_button(
			JRadioButton aAAI17_encoding_radio_button) {
		AAAI17_encoding_radio_button = aAAI17_encoding_radio_button;
	}

}
