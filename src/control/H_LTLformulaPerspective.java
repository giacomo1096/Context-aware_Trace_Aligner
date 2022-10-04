package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import main.Constants;

import view.LTLformulaPerspective;

import org.processmining.ltl2automaton.plugins.automaton.Automaton;
import org.processmining.ltl2automaton.plugins.ltl.SyntaxParserException;

import main.LTLFormula;

public class H_LTLformulaPerspective {
	
	public LTLformulaPerspective _view = null;
	
	public H_LTLformulaPerspective (LTLformulaPerspective i_view){
		_view = i_view;
		installListeners();
	}

	private void installListeners() {
		
		_view.getCancelButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_view.dispose();
			}
		});
		_view.addWindowListener(new WindowListener() {            
            public void windowOpened(WindowEvent e) {}
            public void windowClosed(WindowEvent e) {}
            public void windowActivated(WindowEvent e) {}
            public void windowDeactivated(WindowEvent e) {}
            public void windowIconified(WindowEvent e) {}
            public void windowDeiconified(WindowEvent e) {}
            public void windowClosing(WindowEvent e) {
				_view.dispose();
            }
        });

		
		_view.getOkButton().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {

            	String ltl_formula = _view.getFormulaTextArea().getText();


				//
				Automaton automaton = null;
				try {
					automaton = LTLFormula.generateAutomatonByLTLFormula(ltl_formula);
					if(Constants.getConstraintsPerspective().getConstraintsListModel().contains("LTL{" + ltl_formula + "}"))
    					JOptionPane.showMessageDialog(_view, new JLabel("<html>The LTL formula '" + ltl_formula + "' <font color=\"red\">already exists</font>!</html>"), "Attention", JOptionPane.ERROR_MESSAGE, new ImageIcon("images/alert_icon.png"));
    				else {
    			 		Constants.getConstraintsPerspective().getConstraintsListModel().addElement("LTL{" + ltl_formula + "}");

						//

							String temporal_constraint  = new String(ltl_formula);
							temporal_constraint = temporal_constraint.replace("LTL{", "");
	            			temporal_constraint = temporal_constraint.replace("}", "");            			
   	   	         			ltl_formula = temporal_constraint;
   	   	         			
   	   	         			String activities_of_ltl_formula = new String(temporal_constraint);
   	   	         				
   	   	         			if(activities_of_ltl_formula.contains("V"))
   	   	         				activities_of_ltl_formula = activities_of_ltl_formula.replaceAll("V", "");
   	   	         		
	   	   	         		if(activities_of_ltl_formula.contains("U"))
								activities_of_ltl_formula = activities_of_ltl_formula.replaceAll("U", "");
							
							if(activities_of_ltl_formula.contains("W"))
								activities_of_ltl_formula = activities_of_ltl_formula.replaceAll("W", "");
							
							if(activities_of_ltl_formula.contains("X"))
								activities_of_ltl_formula = activities_of_ltl_formula.replaceAll("X", "");
						
							activities_of_ltl_formula = activities_of_ltl_formula.toLowerCase();
							
							if(activities_of_ltl_formula.contains("/"))
								activities_of_ltl_formula = activities_of_ltl_formula.replaceAll("\\/", "");
							
							if(activities_of_ltl_formula.contains("\\"))
								activities_of_ltl_formula = activities_of_ltl_formula.replaceAll("\\\\", "");
							
							if(activities_of_ltl_formula.contains("!"))
								activities_of_ltl_formula = activities_of_ltl_formula.replaceAll("!", "");
							
							if(activities_of_ltl_formula.contains("("))
								activities_of_ltl_formula = activities_of_ltl_formula.replaceAll("\\(", "");
							
							if(activities_of_ltl_formula.contains(")"))
								activities_of_ltl_formula = activities_of_ltl_formula.replaceAll("\\)", "");
							
							if(activities_of_ltl_formula.contains("<"))
								activities_of_ltl_formula = activities_of_ltl_formula.replaceAll("\\<", "");
							
							if(activities_of_ltl_formula.contains(">"))
								activities_of_ltl_formula = activities_of_ltl_formula.replaceAll("\\>", "");
							
							if(activities_of_ltl_formula.contains("."))
								activities_of_ltl_formula = activities_of_ltl_formula.replaceAll("\\.", "");
							
							if(activities_of_ltl_formula.contains("true"))
								activities_of_ltl_formula = activities_of_ltl_formula.replaceAll("true", "");
							
							if(activities_of_ltl_formula.contains("false"))
								activities_of_ltl_formula = activities_of_ltl_formula.replaceAll("false", "");
							
							if(activities_of_ltl_formula.contains(","))
								activities_of_ltl_formula = activities_of_ltl_formula.replaceAll("\\,", "_");
							
							if(activities_of_ltl_formula.contains("+"))
								activities_of_ltl_formula = activities_of_ltl_formula.replaceAll("\\+", "_");
							
							if(activities_of_ltl_formula.contains("-"))
								activities_of_ltl_formula = activities_of_ltl_formula.replaceAll("\\-", "_");
	   	   	         			
							String[] activities_of_ltl_formula_array = activities_of_ltl_formula.split("\\s+");
							//System.out.println("\nActivities LTL formula: "+activities_of_ltl_formula.toString());

							for(String specific_activity : activities_of_ltl_formula_array){
								if(!Constants.getActivitiesRepository_vector().contains(specific_activity)) {
									
									Constants.getActivitiesRepository_vector().addElement(specific_activity);											
									Constants.getAlphabetPerspective().getAlphabetListModel().addElement(specific_activity);
							  		Constants.getTracesPerspective().getAlphabetListModel().addElement(specific_activity);
								}
							}


						//
            			_view.dispose();
    			}
				} catch (SyntaxParserException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(_view, new JLabel("<html>The LTL formula '" + ltl_formula + "' <font color=\"red\">is not valid</font>!</html>"), "Attention", JOptionPane.ERROR_MESSAGE, new ImageIcon("images/alert_icon.png"));

				}


				//
            	
				
            }
        });


		/*
		 _view.getOkButton().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {

            	String ltl_formula = _view.getFormulaTextArea().getText();
            	
				//if(Constants.getConstraintsPerspective().getConstraintsListModel().contains(ltl_formula))
            	if(Constants.getConstraintsPerspective().getConstraintsListModel().contains("LTL{" + ltl_formula + "}"))
    				JOptionPane.showMessageDialog(_view, new JLabel("<html>The LTL formula '" + ltl_formula + "' <font color=\"red\">already exists</font>!</html>"), "Attention", JOptionPane.ERROR_MESSAGE, new ImageIcon("images/alert_icon.png"));
    			else {
    			 	Constants.getConstraintsPerspective().getConstraintsListModel().addElement("LTL{" + ltl_formula + "}");
            		_view.dispose();
    			}
            }
        });
		 */
		
	}

}
