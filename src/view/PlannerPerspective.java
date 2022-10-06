package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import main.Constants;
import control.H_PlannerPerspective;

public class PlannerPerspective extends JDialog
{

	private static final long serialVersionUID = 1L;
	
	private JTextArea activitiesArea;
	private JScrollPane activitiesScrollPane;	
	
	private JTextArea constraintsArea;	
	private JScrollPane constraintsScrollPane;
	
	private JTextArea traceArea = new JTextArea();
	private JScrollPane traceScrollPane;
		
	//private JCheckBox FDlazyGreedyCheckBox;
	//private JLabel FDlazyGreedyLabel;
	private JCheckBox FDoptimalCheckBox;	
	private JLabel FDoptimalLabel;
	private JCheckBox SymBAoptimalCheckBox;	
	private JLabel SymBAoptimalLabel;
	
	
	//private JCheckBox LPGCheckBox;	
	//private JLabel LPGlabel;	
	
	private JLabel number_of_traces_label;
	private JComboBox<String> number_of_traces_ComboBox_FROM;
	private JComboBox<String> number_of_traces_ComboBox_TO;
	private JCheckBox number_of_traces_checkBox;	
	
	private JLabel lenght_of_traces_label;
	private JComboBox<String> lenght_of_traces_ComboBox_FROM;
	private JComboBox<String> lenght_of_traces_ComboBox_TO;
	private JCheckBox lenght_of_traces_checkBox;
	
	private JLabel trace_duplicated_label;
	private JCheckBox trace_duplicated_checkBox;
	
	
	//private JCheckBox costCheckBox;
	//private JComboBox<String> activitiesComboBox;
	//private JTextField addingCostField;
	//private JTextField removalCostField;
	
	private JLabel activitiesRepoLabel;
	private JLabel traceLabel;
	private JLabel constraintsListLabel;
	private JLabel plannerOptionsLabel;
	//private JLabel actionsCostLabel;
	private JLabel blankLabel;
	private JLabel blankLabel_2;
	private JLabel blankLabel_3;
	private JLabel blankLabel_4;
	private JLabel blankLabel_5;
	private JButton runPlannerButton;
	private JButton previousStepButton;
	private JButton generatePDDLButton;
	
	protected H_PlannerPerspective _handler;
	
	public PlannerPerspective()
	{
		super();
		initComponent();
		initHandler();
	}

	public void initComponent()
	{		
		Container content = this.getContentPane();
	    //content.setBackground(Color.white);
	    
	    content.setLayout(new FlowLayout()); 	

		activitiesRepoLabel = new JLabel("<html><u>Activities repository</u></html>");
		activitiesRepoLabel.setPreferredSize(new Dimension(390,25));
		traceLabel = new JLabel("<html><u>Traces</u></html>");
		traceLabel.setPreferredSize(new Dimension(390,25));
		constraintsListLabel = new JLabel("<html><u>List of DECLARE constraints</u></html>");
		constraintsListLabel.setPreferredSize(new Dimension(390,25));
		plannerOptionsLabel = new JLabel("<html><u>Planner Search Algorithm</u></html>");
		plannerOptionsLabel.setPreferredSize(new Dimension(390,25));
		//actionsCostLabel = new JLabel("<html><u>Cost to add/remove activities in/from the trace</u></html>");
		//actionsCostLabel.setPreferredSize(new Dimension(350,25));
		trace_duplicated_label= new JLabel("<html><u>Discard duplicated traces</u></html>");
		trace_duplicated_label.setPreferredSize(new Dimension(350,25));	
		number_of_traces_label= new JLabel("<html><u>Select an interval of traces to analyze</u></html>");
		number_of_traces_label.setPreferredSize(new Dimension(350,25));
		lenght_of_traces_label = new JLabel("<html><u>Select only the traces having length between</u></html>");
		lenght_of_traces_label.setPreferredSize(new Dimension(350,25));	
		
		activitiesArea = new JTextArea();
		activitiesArea.setEditable(false);
		activitiesScrollPane = new JScrollPane(activitiesArea);
		activitiesScrollPane.setPreferredSize(new Dimension(390,60));
		activitiesScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		activitiesScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		traceArea = new JTextArea();
		traceArea.setEditable(false);
		traceScrollPane = new JScrollPane(traceArea);
		traceScrollPane.setPreferredSize(new Dimension(390,60));
		traceScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		traceScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		constraintsArea = new JTextArea();
		constraintsArea.setEditable(false);
		constraintsScrollPane = new JScrollPane(constraintsArea);
		constraintsScrollPane.setPreferredSize(new Dimension(390,60));
		constraintsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		constraintsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    
		//FDlazyGreedyLabel = new JLabel("Lazy Greedy (Fast-Downward)");
		//FDlazyGreedyLabel.setPreferredSize(new Dimension(350,15));
		//FDlazyGreedyCheckBox = new JCheckBox();
		//FDlazyGreedyCheckBox.setPreferredSize(new Dimension(30,15));
		FDoptimalLabel = new JLabel("Blind A* (Fast-Downward)");
		FDoptimalLabel.setPreferredSize(new Dimension(350,15));
		FDoptimalCheckBox = new JCheckBox();
		FDoptimalCheckBox.setPreferredSize(new Dimension(30,15));
		
		SymBAoptimalLabel = new JLabel("Symbolic Bidirectional Blind A* (SymBA*-2)");
		SymBAoptimalLabel.setPreferredSize(new Dimension(350,15));
		SymBAoptimalCheckBox = new JCheckBox();
		SymBAoptimalCheckBox.setSelected(true);
		SymBAoptimalCheckBox.setPreferredSize(new Dimension(30,15));
		

		if(!Constants.getPDDL_encoding().equalsIgnoreCase("AAAI17")) {
			System.out.println("TEST");
			SymBAoptimalLabel.setEnabled(false);
			SymBAoptimalCheckBox.setEnabled(false);
		}
		//LPGlabel = new JLabel("Local Search (LPG-td)");
		//LPGlabel.setPreferredSize(new Dimension(350,15));
		//LPGCheckBox = new JCheckBox();
		//LPGCheckBox.setPreferredSize(new Dimension(30,15));
				
		trace_duplicated_checkBox = new JCheckBox();
		trace_duplicated_checkBox.setPreferredSize(new Dimension(30,15));
		trace_duplicated_checkBox.setSelected(true);
		
		number_of_traces_checkBox = new JCheckBox();
		number_of_traces_checkBox.setPreferredSize(new Dimension(30,15));
		
		number_of_traces_ComboBox_FROM = new JComboBox<String>();
		number_of_traces_ComboBox_FROM.setPreferredSize(new Dimension(180,25));		
		number_of_traces_ComboBox_TO = new JComboBox<String>();
		number_of_traces_ComboBox_TO.setPreferredSize(new Dimension(180,25));	
		number_of_traces_ComboBox_FROM.insertItemAt("-- FROM trace # --", 0);
		number_of_traces_ComboBox_TO.insertItemAt("-- TO trace # --", 0);
		for(int kd=0;kd<Constants.getAllTraces_vector().size();kd++) {
			int numtr = kd + 1;			
			number_of_traces_ComboBox_FROM.insertItemAt("" + numtr, numtr);
			number_of_traces_ComboBox_TO.insertItemAt("" + numtr, numtr);
		}	
		number_of_traces_ComboBox_FROM.setSelectedIndex(0);
		number_of_traces_ComboBox_TO.setSelectedIndex(0);
		
		number_of_traces_ComboBox_FROM.setEnabled(false);
		number_of_traces_ComboBox_TO.setEnabled(false);
		//
		
		///////////////////////////////////////////////////
				
		lenght_of_traces_checkBox = new JCheckBox();
		lenght_of_traces_checkBox.setPreferredSize(new Dimension(30,15));
		
		lenght_of_traces_ComboBox_FROM = new JComboBox<String>();
		lenght_of_traces_ComboBox_FROM.setPreferredSize(new Dimension(180,25));		
		lenght_of_traces_ComboBox_TO = new JComboBox<String>();
		lenght_of_traces_ComboBox_TO.setPreferredSize(new Dimension(180,25));	
		lenght_of_traces_ComboBox_FROM.insertItemAt("-- min length --", 0);
		lenght_of_traces_ComboBox_TO.insertItemAt("++ max length ++", 0);
		
		lenght_of_traces_ComboBox_FROM.setSelectedIndex(0);
		lenght_of_traces_ComboBox_TO.setSelectedIndex(0);
		
		lenght_of_traces_ComboBox_FROM.setEnabled(false);
		lenght_of_traces_ComboBox_TO.setEnabled(false);
		
		//////////////////////////////////////////////////
		
		/*
		costCheckBox = new JCheckBox();
		costCheckBox.setSelected(true);
		costCheckBox.setPreferredSize(new Dimension(30,15));		
		activitiesComboBox = new JComboBox<String>();
		activitiesComboBox.insertItemAt("-- Name of the Activity --", 0);
		
		for(int kind=0;kind<Constants.getActivitiesRepository_vector().size();kind++) {
			String activity = Constants.getActivitiesRepository_vector().elementAt(kind);
			activitiesComboBox.insertItemAt(activity, kind+1);
		}		

		activitiesComboBox.setPreferredSize(new Dimension(180,25));
		activitiesComboBox.setSelectedIndex(0);		
		activitiesComboBox.setEnabled(true);
		addingCostField = new JTextField("Adding Cost");
		addingCostField.setPreferredSize(new Dimension(90,25));
		addingCostField.setEnabled(true);
		removalCostField = new JTextField("Removal Cost");
		removalCostField.setPreferredSize(new Dimension(90,25));
		removalCostField.setEnabled(true);
		*/
		
		blankLabel = new JLabel();
		blankLabel.setPreferredSize(new Dimension(30,30));
		blankLabel_5 = new JLabel();
		blankLabel_5.setPreferredSize(new Dimension(30,30));
		blankLabel_2 = new JLabel();
		blankLabel_2.setPreferredSize(new Dimension(390,10));
		blankLabel_3 = new JLabel();
		blankLabel_3.setPreferredSize(new Dimension(390,10));
		blankLabel_4 = new JLabel();
		blankLabel_4.setPreferredSize(new Dimension(390,10));
		generatePDDLButton = new JButton("Generate PDDL");
		runPlannerButton = new JButton("Stats >");
		previousStepButton = new JButton("< Back");
		
		this.add(activitiesRepoLabel);	
	    this.add(activitiesScrollPane);	
	    this.add(traceLabel);	
	    this.add(traceScrollPane);
	    this.add(constraintsListLabel);	
	    this.add(constraintsScrollPane);    
	    this.add(plannerOptionsLabel);	    
	    //this.add(FDlazyGreedyLabel);
	    //this.add(FDlazyGreedyCheckBox);	
	    this.add(FDoptimalLabel);
	    this.add(FDoptimalCheckBox);
	    this.add(SymBAoptimalLabel);
	    this.add(SymBAoptimalCheckBox);
	    //this.add(LPGlabel);
	    //this.add(LPGCheckBox);
	    this.add(blankLabel_3); 
	    this.add(number_of_traces_label);
	    this.add(number_of_traces_checkBox);
	    this.add(number_of_traces_ComboBox_FROM);
	    this.add(number_of_traces_ComboBox_TO);
	    this.add(blankLabel_3); 
	    this.add(trace_duplicated_label);
	    this.add(trace_duplicated_checkBox);	    
	    this.add(blankLabel_3); 
	    this.add(lenght_of_traces_label);
	    this.add(lenght_of_traces_checkBox);
	    this.add(lenght_of_traces_ComboBox_FROM);
	    this.add(lenght_of_traces_ComboBox_TO);   
	    this.add(blankLabel_3); 
	    //this.add(actionsCostLabel);	
	    //this.add(costCheckBox);	
	    //this.add(activitiesComboBox);
	    //this.add(addingCostField);
	    //this.add(removalCostField);
	    //this.add(blankLabel_2); 
	    this.add(previousStepButton); 
	    this.add(blankLabel); 
	    this.add(generatePDDLButton); 
	    this.add(blankLabel_5); 
	    //this.add(runPlannerButton); 
		
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	    this.setLocation ((screenSize.width / 2 ) - ( this.getWidth ( ) / 2 ), (screenSize.height / 2 ) - ( this.getHeight ( ) / 2 ) );


		this.setTitle("STEP 4: Launching the Planner");
	    this.setSize(400, 600);
	    this.setResizable(false);
	    	   	   
	}
	
	private void initHandler() {
		_handler = new H_PlannerPerspective(this);
	}

	public JTextArea getActivitiesArea() {
		return activitiesArea;
	}

	public JTextArea getConstraintsArea() {
		return constraintsArea;
	}

	public JTextArea getTraceArea() {
		return traceArea;
	}

	public JButton getRunPlannerButton() {
		return runPlannerButton;
	}

	public JButton getPreviousStepButton() {
		return previousStepButton;
	}
	
	public JButton getGeneratePDDLButton() {
		return generatePDDLButton;
	}

	//public JCheckBox getFDLazyGreedyCheckBox() {
		//return FDlazyGreedyCheckBox;
	//}

	//public void setFDLazyGreedyCheckBox(JCheckBox lazyGreedyCheckBox) {
		//this.FDlazyGreedyCheckBox = lazyGreedyCheckBox;
	//}

	public JCheckBox getFDOptimalCheckBox() {
		return FDoptimalCheckBox;
	}

	public void setFDOptimalCheckBox(JCheckBox optimalCheckBox) {
		this.FDoptimalCheckBox = optimalCheckBox;
	}
	
	public JCheckBox getSymBAoptimalCheckBox() {
		return SymBAoptimalCheckBox;
	}
	
	public void setSymBAoptimalCheckBox(JCheckBox symBAoptimalCheckBox) {
		SymBAoptimalCheckBox = symBAoptimalCheckBox;
	}
	
	
	//public JCheckBox getLPGCheckBox() {
	//	return LPGCheckBox;
	//}

	//public void setLPGCheckBox(JCheckBox lpgCheckBox) {
	//	this.LPGCheckBox = lpgCheckBox;
	//}
	

	/*
	public JCheckBox getCostCheckBox() {
		return costCheckBox;
	}
	*/
	
	public JComboBox<String> getNumber_of_traces_ComboBox_FROM() {
		return number_of_traces_ComboBox_FROM;
	}
	
	public JComboBox<String> getNumber_of_traces_ComboBox_TO() {
		return number_of_traces_ComboBox_TO;
	}

	public JCheckBox getNumber_of_Traces_checkBox() {
		return number_of_traces_checkBox;
	}
	
	/*
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
	*/
	
	public JComboBox<String> getLenght_of_traces_ComboBox_FROM() {
		return lenght_of_traces_ComboBox_FROM;
	}

	public JComboBox<String> getLenght_of_traces_ComboBox_TO() {
		return lenght_of_traces_ComboBox_TO;
	}

	public JCheckBox getLenght_of_traces_checkBox() {
		return lenght_of_traces_checkBox;
	}

	public void setLenght_of_traces_ComboBox_FROM(
			JComboBox<String> lenght_of_traces_ComboBox_FROM) {
		this.lenght_of_traces_ComboBox_FROM = lenght_of_traces_ComboBox_FROM;
	}

	public void setLenght_of_traces_ComboBox_TO(
			JComboBox<String> lenght_of_traces_ComboBox_TO) {
		this.lenght_of_traces_ComboBox_TO = lenght_of_traces_ComboBox_TO;
	}

	public void setLenght_of_traces_checkBox(JCheckBox lenght_of_traces_checkBox) {
		this.lenght_of_traces_checkBox = lenght_of_traces_checkBox;
	}

	public JCheckBox getTrace_duplicated_checkBox() {
		return trace_duplicated_checkBox;
	}

	public void setTrace_duplicated_checkBox(JCheckBox trace_duplicated_checkBox) {
		this.trace_duplicated_checkBox = trace_duplicated_checkBox;
	}
}