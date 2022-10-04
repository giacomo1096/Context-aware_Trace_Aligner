package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.processmining.ltl2automaton.plugins.automaton.Automaton;
import org.processmining.ltl2automaton.plugins.automaton.State;
import org.processmining.ltl2automaton.plugins.automaton.Transition;
import org.processmining.ltl2automaton.plugins.ltl.SyntaxParserException;
import java.util.Iterator;


import main.Constants;
import main.Trace;
import main.Utilities;
import view.AlphabetPerspective;

public class H_AlphabetPerspective {
	
	private AlphabetPerspective _view = null;
	
	public H_AlphabetPerspective (AlphabetPerspective i_view){
		_view = i_view;
		installListeners();
	}

	private void installListeners() {
	
		_view.getNextStepButton().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
            	if(_view.getAlphabetListModel().getSize()>0) {
        			 Constants.getAlphabetPerspective().setComponentEnabled(false);
        			 Constants.getTracesPerspective().setComponentEnabled(true);
        			
        			// 
        			// Reset and update the global vector containing the repository of all activities that can be used in traces and in the constraints.
         			//
        			Constants.setActivitiesRepository_vector(new Vector<String>()); 
         			
             		for(int i=0;i<_view.getAlphabetListModel().getSize();i++) {
            			String string = (String) _view.getAlphabetListModel().getElementAt(i);
            			
            			Constants.getActivitiesRepository_vector().addElement(string); 
            			
            			//
            			// Update the alphabet of activities in the second panel that manages traces creation (TracesPerspective panel).
            			//
            			if(!Constants.getTracesPerspective().getAlphabetListModel().contains(string))
            				Constants.getTracesPerspective().getAlphabetListModel().addElement(string);            			           			
             		}
        		}
        		else {
        			JOptionPane.showMessageDialog(null, "The repository of activities can not be empty!", "ATTENTION!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/info_icon.png"));
        		}
            }
        });
		
		_view.getRightButton().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
            	if(_view.getActivityField().getText().equalsIgnoreCase("") || 
            			_view.getActivityField().getText().equalsIgnoreCase(" ") ||	_view.getActivityField().getText().contains(" ")) {            		
            			JOptionPane.showMessageDialog(null, "The name of the activity can not be empty and can not contain blank spaces!", "ATTENTION!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/info_icon.png")); 		
            	}
            	else if(Utilities.isUpperCase(_view.getActivityField().getText()))
            		JOptionPane.showMessageDialog(null, "Please use only lower space characters for the activity name!", "ATTENTION!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/info_icon.png"));            	
            	else if(_view.getActivityField().getText().charAt(0) >= '0' && _view.getActivityField().getText().charAt(0) <= '9')           	
            		JOptionPane.showMessageDialog(null, "It is not allowed to use an Integer as the first character of the activity name!", "ATTENTION!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/info_icon.png"));
            	else if(!_view.getActivityField().getText().matches("[a-z0-9_]*"))
                		JOptionPane.showMessageDialog(null, "Only special symbols underscore \"_\" sign are allowed but no other special characters!", "ATTENTION!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/info_icon.png"));
            	else {
            		
            		boolean activity_name_already_exists = false;
            		
            		for(int i=0;i<_view.getAlphabetListModel().getSize();i++) {
            			String string = (String) _view.getAlphabetListModel().getElementAt(i);
            			if(string.equalsIgnoreCase(_view.getActivityField().getText())) {
            				JOptionPane.showMessageDialog(null, "The activity '" + _view.getActivityField().getText() + "' already exists. Please choose a different name for the activity!", "ATTENTION!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/info_icon.png"));
            				activity_name_already_exists = true;
            				break;
            			}
            			}
            	
            		if(!activity_name_already_exists){
	            		_view.getAlphabetListModel().addElement(_view.getActivityField().getText());
	            		_view.getActivityField().setText("");
            		}
            	}
            }
        }); 
		
		_view.getRemoveButton().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {           	
            	if(_view.getAlphabetList().getSelectedIndex() == -1) { // No activity selected
            		JOptionPane.showMessageDialog(null, "Please select an activity to be removed from the repository!", "ATTENTION!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/info_icon.png"));
            	} 
               	else {
                    	int index = _view.getAlphabetList().getSelectedIndex();
                    	
                    	// Activity selected for the deletion
                    	String elem = (String) _view.getAlphabetList().getSelectedValue();
                		
                    	// Remove the activity selected for the deletion from the AlphabetPerspective panel.
                    	_view.getAlphabetListModel().removeElementAt(index);
                
                		// Remove the activity selected for the deletion from the alphabet of activities in the second panel that manages traces creation (TracesPerspective panel).
                		if(Constants.getTracesPerspective().getAlphabetListModel().contains(elem))
                			Constants.getTracesPerspective().getAlphabetListModel().removeElement(elem);                		              		
             		
                		// Remove the selected activity from any of the already specified traces
                		for(int k=0;k<Constants.getAllTraces_vector().size();k++) {
                			
                			Trace trace = Constants.getAllTraces_vector().elementAt(k);
                			
                			if(trace.getTraceAlphabet_vector().contains(elem))
                				trace.getTraceAlphabet_vector().removeElement(elem);
                			               			
                			for(int g=0;g<trace.getOriginalTraceContent_vector().size();g++) {
                				
                				String item = trace.getOriginalTraceContent_vector().elementAt(g);
                				
                				if(item.equalsIgnoreCase(elem)) {
                					
                					trace.getOriginalTraceContent_vector().removeElement(elem);
                					trace.getNumberOfActivityInstances_Hashtable().remove(elem);
                					
                					String item_instance_to_be_removed = trace.getTraceContentWithActivitiesInstances_vector().elementAt(g);
                					              					
                					trace.getTraceContentWithActivitiesInstances_vector().removeElement(item_instance_to_be_removed);
                					trace.getAssociationsToActivityInstances_Hashtable().remove(item_instance_to_be_removed);
                					
                					g--;
                				}                				

                			}

                		}
                		
                		// Remove the selected activity from the list of activities visualized for the actually selected trace in the second panel (TracePerspective)
                		while(Constants.getTracesPerspective().getTraceListModel().contains(elem))
                			Constants.getTracesPerspective().getTraceListModel().removeElement(elem);
                     		
                		
                		//TODO gestire anche i casi LTL e DFA
                		// Remove the selected activity from the list of activities visualized for the actually selected trace in the second panel (TracePerspective)            		
                		for(int k=0;k<Constants.getConstraintsPerspective().getConstraintsListModel().getSize();k++) {
                			String string = (String) Constants.getConstraintsPerspective().getConstraintsListModel().getElementAt(k);

							//
							if(string.startsWith("LTL{")) { 
								String ltl_formula = new String();	    
	            			
								string = string.replace("LTL{", "");
								string = string.replace("}", "");            			
								ltl_formula = string;
									   
								String activities_of_ltl_formula = new String(string);
										   
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
								//System.out.println(activities_of_ltl_formula);
								for(int i = 0; i<activities_of_ltl_formula_array.length;i++) {
									if(!activities_of_ltl_formula_array[i].equalsIgnoreCase("")) {
										//System.out.println(activities_of_ltl_formula_array[i]);
										if(activities_of_ltl_formula_array[i].equalsIgnoreCase(elem)){
											Constants.getConstraintsPerspective().getConstraintsListModel().removeElementAt(k);
                							k--;
										}
									}
								}
							}
							
	       	         		else if(string.startsWith("DFA{")) {
								boolean is_valid = true;
								Automaton automaton = null;
		       	         		String DFA_file_path = string.replace("DFA{", "");
		       	         		DFA_file_path = DFA_file_path.replace("}", "");            			
		       	         					       	         			
		       	         		try {
		       	         			automaton = Utilities.getAutomatonForModelLearning(DFA_file_path);
								} catch (FileNotFoundException e) {
									e.printStackTrace();
								}
	       	         		
			       	         	 State s = automaton.getInit();
			       	         	 Iterator<State> it = automaton.iterator();
			       	         	 while (it.hasNext()) {
				       	              State ss = it.next();
				       	              Iterator<Transition> transitions = ss.getOutput().iterator();
				       	              while (transitions.hasNext()) {
				       	                  Transition t = transitions.next();

										  //System.out.println(t.getSource().toString() +" "+ t.toString()+" "+t.getTarget().toString());



				       	                  if(t.getPositiveLabel().equalsIgnoreCase(elem)) {
											is_valid = false;
											
				       	                  }
				       	                  }
			       	         	}
								if(!is_valid){
									Constants.getConstraintsPerspective().getConstraintsListModel().removeElementAt(k);
                					k--;
								}
	       	         		}	
							//

						else{
                			String[] split = string.split("\\(");
                			String[] split_1 = split[1].split("\\)");
                			
                			if(!split_1[0].contains(",")) {
                				if(split_1[0].equalsIgnoreCase(elem)) {
                					Constants.getConstraintsPerspective().getConstraintsListModel().removeElementAt(k);
                					k--;
                				}
                			}		
                			else {
                				String[] split_2 = split_1[0].split(",");
                				if(split_2[0].equalsIgnoreCase(elem) || split_2[1].equalsIgnoreCase(elem)) {
                					Constants.getConstraintsPerspective().getConstraintsListModel().removeElementAt(k);
                					k--;
                				}
                			}
						}
                		}
               	}
        	}
        });
		
/*
 		_view.getRemoveButton().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {           	
            	if(_view.getAlphabetList().getSelectedIndex() == -1) { // No activity selected
            		JOptionPane.showMessageDialog(null, "Please select an activity to be removed from the repository!", "ATTENTION!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/info_icon.png"));
            	} 
               	else {
                    	int index = _view.getAlphabetList().getSelectedIndex();
                    	
                    	// Activity selected for the deletion
                    	String elem = (String) _view.getAlphabetList().getSelectedValue();
                		
                    	// Remove the activity selected for the deletion from the AlphabetPerspective panel.
                    	_view.getAlphabetListModel().removeElementAt(index);
                
                		// Remove the activity selected for the deletion from the alphabet of activities in the second panel that manages traces creation (TracesPerspective panel).
                		if(Constants.getTracesPerspective().getAlphabetListModel().contains(elem))
                			Constants.getTracesPerspective().getAlphabetListModel().removeElement(elem);                		              		
             		
                		// Remove the selected activity from any of the already specified traces
                		for(int k=0;k<Constants.getAllTraces_vector().size();k++) {
                			
                			Trace trace = Constants.getAllTraces_vector().elementAt(k);
                			
                			if(trace.getTraceAlphabet_vector().contains(elem))
                				trace.getTraceAlphabet_vector().removeElement(elem);
                			               			
                			for(int g=0;g<trace.getOriginalTraceContent_vector().size();g++) {
                				
                				String item = trace.getOriginalTraceContent_vector().elementAt(g);
                				
                				if(item.equalsIgnoreCase(elem)) {
                					
                					trace.getOriginalTraceContent_vector().removeElement(elem);
                					trace.getNumberOfActivityInstances_Hashtable().remove(elem);
                					
                					String item_instance_to_be_removed = trace.getTraceContentWithActivitiesInstances_vector().elementAt(g);
                					              					
                					trace.getTraceContentWithActivitiesInstances_vector().removeElement(item_instance_to_be_removed);
                					trace.getAssociationsToActivityInstances_Hashtable().remove(item_instance_to_be_removed);
                					
                					g--;
                				}                				

                			}

                		}
                		
                		// Remove the selected activity from the list of activities visualized for the actually selected trace in the second panel (TracePerspective)
                		while(Constants.getTracesPerspective().getTraceListModel().contains(elem))
                			Constants.getTracesPerspective().getTraceListModel().removeElement(elem);
                     		
                		
                		//TODO gestire anche i casi LTL e DFA
                		// Remove the selected activity from the list of activities visualized for the actually selected trace in the second panel (TracePerspective)            		
                		for(int k=0;k<Constants.getConstraintsPerspective().getConstraintsListModel().getSize();k++) {
                			String string = (String) Constants.getConstraintsPerspective().getConstraintsListModel().getElementAt(k);
                			String[] split = string.split("\\(");
                			String[] split_1 = split[1].split("\\)");
                			
                			if(!split_1[0].contains(",")) {
                				if(split_1[0].equalsIgnoreCase(elem)) {
                					Constants.getConstraintsPerspective().getConstraintsListModel().removeElementAt(k);
                					k--;
                				}
                			}		
                			else {
                				String[] split_2 = split_1[0].split(",");
                				if(split_2[0].equalsIgnoreCase(elem) || split_2[1].equalsIgnoreCase(elem)) {
                					Constants.getConstraintsPerspective().getConstraintsListModel().removeElementAt(k);
                					k--;
                				}
                			}
                		}
               	}
        	}
        });
		
 */
	}
	


}
