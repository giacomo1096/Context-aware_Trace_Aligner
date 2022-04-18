package view;

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
	private JMenuItem importDOTAutomatonMenuItem;
	private JMenu importMenu;
	private JMenuItem exitMenuItem;
	
	protected H_MenuPerspective _handler;
	
	public MenuPerspective(){
		super();
		initComponent();
		initHandler();		
	}

	private void initComponent() {
		
		fileMenu = new JMenu("File");
		
	    newMenuItem = new JMenuItem("New ");	  
	    openXESMenuItem = new JMenuItem("Open XES file ");
	    importDeclareMenuItem  = new JMenuItem("Import Declare model ");
		importDOTAutomatonMenuItem  = new JMenuItem("Import Automaton");
		importMenu = new JMenu("Import ");
	    
	    newMenuItem.setEnabled(true);
	    openXESMenuItem.setEnabled(true);
	    
	    importDeclareMenuItem.setEnabled(false);
	    importDOTAutomatonMenuItem.setEnabled(false);
	    importMenu.setEnabled(false);
	    
	    exitMenuItem = new JMenuItem("Exit ");
	       
	    fileMenu.add(newMenuItem);
	    fileMenu.add(openXESMenuItem);
	    fileMenu.add(importMenu);
	    importMenu.add(importDeclareMenuItem);
	    importMenu.add(importDOTAutomatonMenuItem);		
	    fileMenu.addSeparator();
	    fileMenu.add(exitMenuItem);
	    
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

	public JMenuItem getImportDOTAutomatonMenuItem() {
		return importDOTAutomatonMenuItem;
	}

	public void setImportDOTAutomatonMenuItem(JMenuItem importDOTAutomatonMenuItem) {
		this.importDOTAutomatonMenuItem = importDOTAutomatonMenuItem;
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

	public void setOpenXESMenuItem(JMenuItem openXESMenuItem) {
		this.openXESMenuItem = openXESMenuItem;
	}

}
