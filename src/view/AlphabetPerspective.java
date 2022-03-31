package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

import control.H_AlphabetPerspective;

public class AlphabetPerspective extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JPanel activitiesPanel;
	private JPanel addActivityButtonPanel;
	
	private JLabel firstBoxLabel;
	private JLabel secondBoxLabel;
	private JLabel blankLabel;
	
	private JTextField activityField;
		
	private DefaultListModel<String> alphabetListModel;
	private JList<String> alphabetList;
	private JScrollPane alphabetScrollPane;
	
	private JButton rightButton;
	private JButton removeButton;	
	
	private JButton nextStepButton;

	protected H_AlphabetPerspective _handler;
	
	public AlphabetPerspective(){
		super();
		initComponent();
		initHandler();		
	}

	private void initComponent() {
		
		this.setLayout(new FlowLayout());
		this.setBorder(new TitledBorder(null, "STEP 1: Create a repository of activities", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		//
		// JLabels on top of the JFrame
		//
		firstBoxLabel = new JLabel("Create a new activity");
		firstBoxLabel.setPreferredSize(new Dimension(385,15));		

		secondBoxLabel = new JLabel("Repository of activities");
		secondBoxLabel.setPreferredSize(new Dimension(280,15));
		
		//
		// JPanel containing the JTextField for adding the name of the activities
		//
		activitiesPanel = new JPanel();
		activitiesPanel.setPreferredSize(new Dimension(260,100)); 
		activitiesPanel.setLayout(new FlowLayout());
		
		activityField = new JTextField();
		activityField.setPreferredSize(new Dimension(260,30));
		
		activitiesPanel.add(activityField);
		
		//
		// JPanel containing the JButtons for adding new activities in the alphabet
		//
		addActivityButtonPanel = new JPanel();
		addActivityButtonPanel.setPreferredSize(new Dimension(120,100)); 
		addActivityButtonPanel.setLayout(new FlowLayout());
		
		rightButton = new JButton("ADD>>");
		rightButton.setPreferredSize(new Dimension(110,25)); 
		removeButton = new JButton("<<DEL");
		removeButton.setPreferredSize(new Dimension(110,25));
		
		addActivityButtonPanel.add(rightButton);
		addActivityButtonPanel.add(removeButton);
		
		//
		// Alphabet of the activities
		//
		alphabetListModel = new DefaultListModel<String>();
		alphabetList = new JList<String>(alphabetListModel);
		alphabetList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		alphabetList.setSelectedIndex(-1);

		alphabetScrollPane = new JScrollPane(alphabetList);
		alphabetScrollPane.setPreferredSize(new Dimension(280,90));
		alphabetScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		alphabetScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		blankLabel = new JLabel();
		blankLabel.setPreferredSize(new Dimension(550,6));
		nextStepButton = new JButton("Next Step >");
		
		this.add(firstBoxLabel);
		this.add(secondBoxLabel);
		this.add(activitiesPanel);
		this.add(addActivityButtonPanel);
		this.add(alphabetScrollPane);
		this.add(blankLabel);
		this.add(nextStepButton);
		this.setPreferredSize(new Dimension(690,190));
		this.setVisible(true);
	}
	
	public void setComponentEnabled(boolean enabled) {
		firstBoxLabel.setEnabled(enabled);
		secondBoxLabel.setEnabled(enabled);
		activityField.setEnabled(enabled);
		rightButton.setEnabled(enabled);
		removeButton.setEnabled(enabled);
		alphabetList.setEnabled(enabled);
		alphabetScrollPane.setEnabled(enabled);
		nextStepButton.setEnabled(enabled);
		this.setEnabled(enabled);
	}
	
	public void resetComponent() {
		activityField.setText("");
		alphabetListModel.removeAllElements();
	}
	
	private void initHandler() {		
		_handler = new H_AlphabetPerspective(this);
	}

	public DefaultListModel<String> getAlphabetListModel() {
		return alphabetListModel;
	}

	public JList<String> getAlphabetList() {
		return alphabetList;
	}

	public JTextField getActivityField() {
		return activityField;
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
	
}