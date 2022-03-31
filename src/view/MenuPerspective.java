package view;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import control.H_MenuPerspective;

public class MenuPerspective extends JMenuBar{

	private static final long serialVersionUID = 1L;
	
	private JMenu fileMenu;
	
	private JMenuItem newMenuItem;
	private JMenuItem openXESMenuItem;
	private JMenuItem importDeclareMenuItem;
	private JMenuItem importLTLFormulaMenuItem;
	private JMenu importMenu;
	
	/////////////////// ModelLearning ////////////////////
	//////////////////////////////////////////////////////
	private JMenuItem importModelLearningAutomatonMenuItem;
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	
	
	private JMenuItem exitMenuItem;
	
	private JMenu AAAI17EncodingMenu;
	private JCheckBoxMenuItem sinkStatesMenuItem;
	private JCheckBoxMenuItem disjunctiveGoalMenuItem;
	private JCheckBoxMenuItem productAutomatonMenuItem;

	protected H_MenuPerspective _handler;
	
	public MenuPerspective(){
		super();
		initComponent();
		initHandler();		
	}

	private void initComponent() {
		
		fileMenu = new JMenu("File");
		AAAI17EncodingMenu = new JMenu("AAAI-17 Encoding");
		
	    newMenuItem = new JMenuItem("New ");	  
	    openXESMenuItem = new JMenuItem("Open XES file ");
	    importDeclareMenuItem  = new JMenuItem("Import Declare model ");
	    importLTLFormulaMenuItem  = new JMenuItem("Import LTL formula ");
	    //saveMenuItem = new JMenuItem("Save ");
		importMenu = new JMenu("Import ");
	    
	    /////////////////// ModelLearning ////////////////////
		//////////////////////////////////////////////////////
		importModelLearningAutomatonMenuItem  = new JMenuItem("Import Automaton");
		//////////////////////////////////////////////////////
		//////////////////////////////////////////////////////
		
	    
	    newMenuItem.setEnabled(true);
	    openXESMenuItem.setEnabled(true);
	    importDeclareMenuItem.setEnabled(false);
	    //saveMenuItem.setEnabled(false);
	    importMenu.setEnabled(false);
	    
		/////////////////// ModelLearning ////////////////////
		//////////////////////////////////////////////////////
		importModelLearningAutomatonMenuItem.setEnabled(false);
		//////////////////////////////////////////////////////
		//////////////////////////////////////////////////////	    
		
	    
	    exitMenuItem = new JMenuItem("Exit ");
	       
	    fileMenu.add(newMenuItem);
	    fileMenu.add(openXESMenuItem);
	    fileMenu.add(importMenu);
	    importMenu.add(importDeclareMenuItem);
	    
		/////////////////// ModelLearning ////////////////////
		//////////////////////////////////////////////////////
	    importMenu.add(importModelLearningAutomatonMenuItem);
		//////////////////////////////////////////////////////
		//////////////////////////////////////////////////////	 
	    
	    importMenu.add(importLTLFormulaMenuItem);
	    
		
	    fileMenu.addSeparator();
	    fileMenu.add(exitMenuItem);
	    
	    ///////////////////////////////////////////////////////
	    
	    sinkStatesMenuItem = new JCheckBoxMenuItem("Remove transitions to a target sink state");
	    disjunctiveGoalMenuItem = new JCheckBoxMenuItem("Allow disjunctive goals in the planning problem");
	    productAutomatonMenuItem = new JCheckBoxMenuItem("Use a product automaton to represent Declare/LTL constraints");
	    
	    disjunctiveGoalMenuItem.setSelected(false);
	    sinkStatesMenuItem.setSelected(true);
	    
	    AAAI17EncodingMenu.add(sinkStatesMenuItem);
	    AAAI17EncodingMenu.add(disjunctiveGoalMenuItem);
	    
	    
	    ///////////////////////////////////////////////////////
	    
	    this.add(fileMenu);    
	}
	
	private void initHandler() {
		_handler = new H_MenuPerspective(this);
	}

	public JMenuItem getNewMenuItem() {
		return newMenuItem;
	}

	public void setNewMenuItem(JMenuItem newMenuItem) {
		this.newMenuItem = newMenuItem;
	}

	public JMenuItem getOpenMenuItem() {
		return openXESMenuItem;
	}

	public void setOpenMenuItem(JMenuItem openMenuItem) {
		this.openXESMenuItem = openMenuItem;
	}

	public JMenuItem getImportDeclareMenuItem() {
		return importDeclareMenuItem;
	}

	public void setImportDeclareMenuItem(JMenuItem importDeclareMenuItem) {
		this.importDeclareMenuItem = importDeclareMenuItem;
	}

	/////////////////// ModelLearning ////////////////////
	//////////////////////////////////////////////////////   
	public JMenuItem getImportModelLearningAutomatonMenuItem() {
		return importModelLearningAutomatonMenuItem;
	}

	public void setImportModelLearningAutomatonMenuItem(JMenuItem importModelLearningAutomatonMenuItem) {
		this.importModelLearningAutomatonMenuItem = importModelLearningAutomatonMenuItem;
	}
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////	
    
	public JMenuItem getImportLTLFormulaMenuItem() {
		return importLTLFormulaMenuItem;
	}

	public void setImportLTLFormulaMenuItem(JMenuItem importLTLFormulaMenuItem) {
		this.importLTLFormulaMenuItem = importLTLFormulaMenuItem;
	}
	

	
	public JMenuItem getExitMenuItem() {
		return exitMenuItem;
	}

	public void setExitMenuItem(JMenuItem saveMenuItem) {
		this.exitMenuItem = saveMenuItem;
	}

	public JMenu getImportMenu() {
		return importMenu;
	}

	public void setExitMenu(JMenu importMenu) {
		this.importMenu = importMenu;
	}

	
	public JMenuItem getOpenXESMenuItem() {
		return openXESMenuItem;
	}

	public JCheckBoxMenuItem getSinkStatesMenuItem() {
		return sinkStatesMenuItem;
	}

	public JCheckBoxMenuItem getProductAutomatonMenuItem() {
		return productAutomatonMenuItem;
	}

	public void setOpenXESMenuItem(JMenuItem openXESMenuItem) {
		this.openXESMenuItem = openXESMenuItem;
	}

	public void setSinkStatesMenuItem(JCheckBoxMenuItem sinkStatesMenuItem) {
		this.sinkStatesMenuItem = sinkStatesMenuItem;
	}

	public void setProductAutomatonMenuItem(JCheckBoxMenuItem productAutomatonMenuItem) {
		this.productAutomatonMenuItem = productAutomatonMenuItem;
	}

	public JCheckBoxMenuItem getDisjunctiveGoalMenuItem() {
		return disjunctiveGoalMenuItem;
	}

	public void setDisjunctiveGoalMenuItem(JCheckBoxMenuItem disjunctiveGoalMenuItem) {
		this.disjunctiveGoalMenuItem = disjunctiveGoalMenuItem;
	}

	
}
