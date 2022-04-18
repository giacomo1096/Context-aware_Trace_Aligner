package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import main.Constants;

public class Desktop extends JFrame
{

	private static final long serialVersionUID = 1L;
	
	private MenuPerspective menuBar;
	private AlphabetPerspective alphabetPanel;
	private TracesPerspective tracesPanel;
	private ConstraintsPerspective constraintsPanel;
	
	public Desktop()
	{
		super();
		initComponent();
	}

	public void initComponent()
	{		
		Container content = this.getContentPane();
	    
	    content.setLayout(new FlowLayout()); 	
		
	    menuBar = new MenuPerspective();	    
	    alphabetPanel = new AlphabetPerspective();
	    tracesPanel = new TracesPerspective();
	    constraintsPanel = new ConstraintsPerspective();
	    
	    Constants.setMenuPerspective(menuBar);
	    Constants.setAlphabetPerspective(alphabetPanel);
	    Constants.setTracesPerspective(tracesPanel);
	    Constants.setConstraintsPerspective(constraintsPanel);
	    
	    Constants.getTracesPerspective().setComponentEnabled(false);
	    Constants.getConstraintsPerspective().setComponentEnabled(false);

	    this.setJMenuBar(menuBar);
	    this.add(alphabetPanel);	
	    this.add(tracesPanel);	
	    this.add(constraintsPanel);	

	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	    this.setLocation ( ( screenSize.width / 3 ) - ( this.getWidth ( ) / 3 ), (
	    screenSize.height / 3 ) - ( this.getHeight ( ) / 3 ) );

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);		
		this.setTitle("Planning-based Trace Alignment for Declarative Processes");
		
		Constants.setDesktop(this);
		
	    this.setSize(700, 670);
	    this.setResizable(true);
	    this.setVisible(true);
	}
}