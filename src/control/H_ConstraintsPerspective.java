package control;

import main.DeclareTemplate;
import main.LTLFormula;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.processmining.ltl2automaton.plugins.automaton.Automaton;
import org.processmining.ltl2automaton.plugins.automaton.State;
import org.processmining.ltl2automaton.plugins.automaton.Transition;
import org.processmining.ltl2automaton.plugins.ltl.SyntaxParserException;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import main.CombinationOfAcceptingStates;
import main.CombinationOfRelevantTransitions;
import main.Constants;
import main.RelevantTransition;
import main.Trace;
import main.Utilities;
import view.LTLformulaPerspective;
import view.PlannerPerspective;
import view.ConstraintsPerspective;
import view.CostPerspective;
import view.CostPerspective3;

public class H_ConstraintsPerspective {
	
	public ConstraintsPerspective _view = null;
	
	public H_ConstraintsPerspective (ConstraintsPerspective i_view){
		_view = i_view;
		installListeners();
	}


	private void installListeners() {
		
		_view.getConstraintComboBox().addItemListener(new ItemListener()
		{

			 public void itemStateChanged(ItemEvent event)
			{				
				 String constraint = (String) _view.getConstraintComboBox().getSelectedItem();
				 
				 if (event.getStateChange() == ItemEvent.SELECTED && constraint.equalsIgnoreCase("---") )
				 {
					 _view.getFirstActivityComboBox().setSelectedIndex(0);
					 _view.getSecondActivityComboBox().setSelectedIndex(0);
					 _view.getFirstActivityComboBox().setEnabled(false);
					 _view.getSecondActivityComboBox().setEnabled(false);
				 }
				 else if (event.getStateChange() == ItemEvent.SELECTED && constraint.equalsIgnoreCase("** LTL Constraint **") )
				 {
					 _view.getFirstActivityComboBox().setSelectedIndex(0);
					 _view.getSecondActivityComboBox().setSelectedIndex(0);
					 _view.getFirstActivityComboBox().setEnabled(false);
					 _view.getSecondActivityComboBox().setEnabled(false);
				 }
				 else if (event.getStateChange() == ItemEvent.SELECTED && (constraint.equalsIgnoreCase("existence") || constraint.equalsIgnoreCase("absence") || constraint.equalsIgnoreCase("init") || constraint.equalsIgnoreCase("last"))) 
				 {
					 _view.getFirstActivityComboBox().setSelectedIndex(0);
					 _view.getSecondActivityComboBox().setSelectedIndex(0);
					 _view.getFirstActivityComboBox().setEnabled(true);
					 _view.getSecondActivityComboBox().setEnabled(false);
				 }
				 else 
				 {
					 _view.getFirstActivityComboBox().setSelectedIndex(0);
					 _view.getSecondActivityComboBox().setSelectedIndex(0);
					 _view.getFirstActivityComboBox().setEnabled(true);
					 _view.getSecondActivityComboBox().setEnabled(true);
				 }
				 
			}
		});
		
		_view.getConstraintsList().addListSelectionListener(new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent e) {
		    	  
		    	  if(e.getValueIsAdjusting()) { // -- Used to avoid that the ListSelectionListener is invoked twice.
		    		 
		    		  String activity_selected = (String) _view.getConstraintsList().getSelectedValue();
		    	  		    		  
		    		  String LTLconstraint = new String();
		    		  		    	  
			    	  if(activity_selected.startsWith("LTL{")) {
			    		  LTLconstraint = activity_selected;
			    		  LTLconstraint = LTLconstraint.replace("LTL{","");
			    		  LTLconstraint = LTLconstraint.replace("}","");
			    		  _view.getLTLconstraintLabel().setText("Corresponding LTL constraint : " + LTLconstraint);
			    	  }
			    	  else if(activity_selected.startsWith("DFA{")) {
						_view.getLTLconstraintLabel().setText("DFA constraint ");
					  }

			    	  else {
		    	  
		    	  String[] split = activity_selected.split("\\(");
		    	 
		    	  String constraint = split[0];
		    	  
		    	  String[] split1 = split[1].split("\\)");
		    	  		    	  
		    	  if(constraint.equalsIgnoreCase("existence")) {
		    		  LTLconstraint = Constants.LTL_eventually + split1[0];
		    		  _view.getLTLconstraintLabel().setText("Corresponding LTL constraint : " + LTLconstraint);
		    	  }
		    	  else if(constraint.equalsIgnoreCase("absence")) {		    		  
		    		  LTLconstraint = Constants.LTL_NOT + Constants.LTL_eventually + split1[0];
		    		  _view.getLTLconstraintLabel().setText("Corresponding LTL constraint : " + LTLconstraint);
		    	  }
		    	  if(constraint.equalsIgnoreCase("init")) {
		    		  LTLconstraint = split1[0];
		    		  _view.getLTLconstraintLabel().setText("Corresponding LTL constraint : " + LTLconstraint);
		    	  }	    	  
		    	  else if(constraint.equalsIgnoreCase("choice")) {	
		    		  String[] split2 = split1[0].split("\\,");
		    		  LTLconstraint = Constants.LTL_eventually + split2[0] + " " + Constants.LTL_OR + " " + Constants.LTL_eventually + split2[1];
		    		  _view.getLTLconstraintLabel().setText("Corresponding LTL constraint : " + LTLconstraint);
		    	  }
		    	  else if(constraint.equalsIgnoreCase("exclusive choice")) {	
		    		  String[] split2 = split1[0].split("\\,");
		    		  LTLconstraint = "(" + Constants.LTL_eventually + split2[0] + " " + Constants.LTL_OR + " " + Constants.LTL_eventually + split2[1] + ")" + " " + Constants.LTL_AND + " " + Constants.LTL_NOT + "(" + Constants.LTL_eventually + split2[0] + " " + Constants.LTL_AND + " " + Constants.LTL_eventually + split2[1] + ")";
		    		  _view.getLTLconstraintLabel().setText("Corresponding LTL constraint : " + LTLconstraint);
		    	  }
		    	  else if(constraint.equalsIgnoreCase("responded existence")) {	
		    		  String[] split2 = split1[0].split("\\,");
		    		  LTLconstraint = Constants.LTL_eventually + split2[0] + " " + Constants.LTL_IMPLIES + " " + Constants.LTL_eventually + split2[1];
		    		  _view.getLTLconstraintLabel().setText("Corresponding LTL constraint : " + LTLconstraint);
		    	  }
		    	  else if(constraint.equalsIgnoreCase("not responded existence")) {	
		    		  String[] split2 = split1[0].split("\\,");
		    		  LTLconstraint = Constants.LTL_eventually + split2[0] + " " + Constants.LTL_IMPLIES + " " + Constants.LTL_NOT + Constants.LTL_eventually + split2[1];
		    		  _view.getLTLconstraintLabel().setText("Corresponding LTL constraint : " + LTLconstraint);
		    	  }
		    	  else if(constraint.equalsIgnoreCase("co-existence")) {	
		    		  String[] split2 = split1[0].split("\\,");
		    		  LTLconstraint = "(" + Constants.LTL_eventually + split2[0] + " " + Constants.LTL_IMPLIES + " " + Constants.LTL_eventually + split2[1] + ")" + " " + Constants.LTL_AND + " " + "(" + Constants.LTL_eventually + split2[1] + " " + Constants.LTL_IMPLIES + " " + Constants.LTL_eventually + split2[0] + ")";
		    		  _view.getLTLconstraintLabel().setText("Corresponding LTL constraint : " + LTLconstraint);
		    	  }
		    	  else if(constraint.equalsIgnoreCase("not co-existence")) {	
		    		  String[] split2 = split1[0].split("\\,");
		    		  LTLconstraint = Constants.LTL_eventually + split2[0] + " " + Constants.LTL_IMPLIES + " " + Constants.LTL_NOT + Constants.LTL_eventually + split2[1];
		    		  _view.getLTLconstraintLabel().setText("Corresponding LTL constraint : " + LTLconstraint);
		    	  }
		    	  else if(constraint.equalsIgnoreCase("response")) {	
		    		  String[] split2 = split1[0].split("\\,");
		    		  LTLconstraint = Constants.LTL_globally + "(" + split2[0] + " " + Constants.LTL_IMPLIES + " " + Constants.LTL_eventually + split2[1] + ")";
		    		  _view.getLTLconstraintLabel().setText("Corresponding LTL constraint : " + LTLconstraint);
		    	  }
		    	  else if(constraint.equalsIgnoreCase("precedence")) {	
		    		  String[] split2 = split1[0].split("\\,");
		    		  LTLconstraint = Constants.LTL_NOT + split2[1] + " " + Constants.LTL_weak_until + " " + split2[0];
		    		  _view.getLTLconstraintLabel().setText("Corresponding LTL constraint : " + LTLconstraint);
		    	  }		    	  
		    	  else if(constraint.equalsIgnoreCase("succession")) {	
		    		  String[] split2 = split1[0].split("\\,");
		    		  LTLconstraint = Constants.LTL_globally + "(" + split2[0] + " " + Constants.LTL_IMPLIES + " " + Constants.LTL_eventually + split2[1] + ")" + " " + Constants.LTL_AND + " " + "(" + Constants.LTL_NOT + split2[1] + " " + Constants.LTL_weak_until + " " + split2[0] + " " + ")";
		    		  _view.getLTLconstraintLabel().setText("Corresponding LTL constraint : " + LTLconstraint);
		    	  }			    	  
		    	  else if(constraint.equalsIgnoreCase("alternate response")) {	
		    		  String[] split2 = split1[0].split("\\,");
		    		  LTLconstraint = Constants.LTL_globally + "(" + split2[0] + " " + Constants.LTL_IMPLIES + " " + Constants.LTL_next + "(" + Constants.LTL_NOT + split2[0] + " " + Constants.LTL_until + " " + split2[1] + "))";
		    		  _view.getLTLconstraintLabel().setText("Corresponding LTL constraint : " + LTLconstraint);
		    	  }	
		    	  else if(constraint.equalsIgnoreCase("alternate precedence")) {	
		    		  String[] split2 = split1[0].split("\\,");
		    		  LTLconstraint = "(" + Constants.LTL_NOT + split2[1] + " " + Constants.LTL_weak_until + " " + split2[0] + ")" + " " + Constants.LTL_AND + " " + Constants.LTL_globally + "(" + split2[1] + " " + Constants.LTL_IMPLIES + " " + Constants.LTL_next + "(" + Constants.LTL_NOT + split2[1] + " " + Constants.LTL_weak_until + " " + split2[0] + "))";
		    		  _view.getLTLconstraintLabel().setText("Corresponding LTL constraint : " + LTLconstraint);
		    	  }	
		    	  else if(constraint.equalsIgnoreCase("alternate succession")) {	
		    		  String[] split2 = split1[0].split("\\,");
		    		  LTLconstraint = Constants.LTL_globally + "(" + split2[0] + " " + Constants.LTL_IMPLIES + " " + Constants.LTL_next + "(" + Constants.LTL_NOT + split2[0] + " " + Constants.LTL_until + " " + split2[1] + "))" + " " + Constants.LTL_AND + " " + "(" + Constants.LTL_NOT + split2[1] + " " + Constants.LTL_weak_until + " " + split2[0] + ")" + " " + Constants.LTL_AND + " " + Constants.LTL_globally + "(" + split2[1] + " " + Constants.LTL_IMPLIES + " " + Constants.LTL_next + "(" + Constants.LTL_NOT + split2[1] + " " + Constants.LTL_weak_until + " " + split2[0] + "))";
		    		  _view.getLTLconstraintLabel().setText("Corresponding LTL constraint : " + LTLconstraint);
		    	  }	
		    	  else if(constraint.equalsIgnoreCase("chain response")) {	
		    		  String[] split2 = split1[0].split("\\,");
		    		  LTLconstraint = Constants.LTL_globally + "(" + split2[0] + " " + Constants.LTL_IMPLIES + " " + Constants.LTL_next + split2[1] + ")";
		    		  _view.getLTLconstraintLabel().setText("Corresponding LTL constraint : " + LTLconstraint);
		    	  }		
		    	  else if(constraint.equalsIgnoreCase("chain precedence")) {	
		    		  String[] split2 = split1[0].split("\\,");
		    		  LTLconstraint = Constants.LTL_globally + "(" + Constants.LTL_next + split2[1] + " " + Constants.LTL_IMPLIES + " " + split2[0] + ")";
		    		  _view.getLTLconstraintLabel().setText("Corresponding LTL constraint : " + LTLconstraint);
		    	  }		
		    	  else if(constraint.equalsIgnoreCase("chain succession")) {	
		    		  String[] split2 = split1[0].split("\\,");
		    		  LTLconstraint = Constants.LTL_globally + "(" + split2[0] + " " + Constants.LTL_IMPLIES + " " + Constants.LTL_next + split2[1] + ")" + " " + Constants.LTL_AND + " " + Constants.LTL_globally + "(" + Constants.LTL_next + split2[1] + " " + Constants.LTL_IMPLIES + " " + split2[0] + ")";
		    		  _view.getLTLconstraintLabel().setText("Corresponding LTL constraint : " + LTLconstraint);
		    	  }
		    	  else if(constraint.equalsIgnoreCase("not response")) {	
		    		  String[] split2 = split1[0].split("\\,");
		    		  LTLconstraint = Constants.LTL_globally + "(" + split2[0] + " " + Constants.LTL_IMPLIES + " " + Constants.LTL_NOT + Constants.LTL_eventually + split2[1] + ")";
		    		  _view.getLTLconstraintLabel().setText("Corresponding LTL constraint : " + LTLconstraint);
		    	  }		
		    	  else if(constraint.equalsIgnoreCase("not precedence")) {	
		    		  String[] split2 = split1[0].split("\\,");
		    		  LTLconstraint = Constants.LTL_globally + "(" + split2[0] + " " + Constants.LTL_IMPLIES + " " + Constants.LTL_NOT + Constants.LTL_eventually + split2[1] + ")";
		    		  _view.getLTLconstraintLabel().setText("Corresponding LTL constraint : " + LTLconstraint);
		    	  }	
		    	  else if(constraint.equalsIgnoreCase("not succession")) {	
		    		  String[] split2 = split1[0].split("\\,");
		    		  LTLconstraint = Constants.LTL_globally + "(" + split2[0] + " " + Constants.LTL_IMPLIES + " " + Constants.LTL_NOT + Constants.LTL_eventually + split2[1] + ")";
		    		  _view.getLTLconstraintLabel().setText("Corresponding LTL constraint : " + LTLconstraint);
		    	  }	
		    	  else if(constraint.equalsIgnoreCase("not chain response")) {	
		    		  String[] split2 = split1[0].split("\\,");
		    		  LTLconstraint = Constants.LTL_globally + "(" + split2[0] + " " + Constants.LTL_IMPLIES + " " + Constants.LTL_NOT + Constants.LTL_next + split2[1] + ")";
		    		  _view.getLTLconstraintLabel().setText("Corresponding LTL constraint : " + LTLconstraint);
		    	  }	
		    	  else if(constraint.equalsIgnoreCase("not chain precedence")) {	
		    		  String[] split2 = split1[0].split("\\,");
		    		  LTLconstraint = Constants.LTL_globally + "(" + Constants.LTL_next + split2[1] + " " + Constants.LTL_IMPLIES + " " + Constants.LTL_NOT + split2[0] + ")";
		    		  _view.getLTLconstraintLabel().setText("Corresponding LTL constraint : " + LTLconstraint);
		    	  }	
		    	  else if(constraint.equalsIgnoreCase("not chain succession")) {	
		    		  String[] split2 = split1[0].split("\\,");
		    		  LTLconstraint = Constants.LTL_globally + "(" + split2[0] + " " + Constants.LTL_IMPLIES + " " + Constants.LTL_NOT + Constants.LTL_next + split2[1] + ")" + " " + Constants.LTL_AND + " " + Constants.LTL_globally + "(" + Constants.LTL_next + split2[1] + " " + Constants.LTL_IMPLIES + " " + Constants.LTL_NOT + split2[0] + ")";
		    		  _view.getLTLconstraintLabel().setText("Corresponding LTL constraint : " + LTLconstraint);
		    	  }	

			    	  }
		    	  _view.getLTLconstraintLabel().setToolTipText(LTLconstraint);
		    	  
		    	  }
	            }
	        });
		
		
	
		_view.getRightButton().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
            	if(((String)_view.getConstraintComboBox().getSelectedItem()).equalsIgnoreCase("---")) {            		
            		JOptionPane.showMessageDialog(null, "Please select a valid DECLARE constraint!", "ATTENTION!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/info_icon.png"));
            	}         
            	else if(((String)_view.getConstraintComboBox().getSelectedItem()).equalsIgnoreCase("** LTL constraint **")) {
        			new LTLformulaPerspective();	
        		}
            	else if(((String)_view.getConstraintComboBox().getSelectedItem()).equalsIgnoreCase("existence") || ((String)_view.getConstraintComboBox().getSelectedItem()).equalsIgnoreCase("absence") || ((String)_view.getConstraintComboBox().getSelectedItem()).equalsIgnoreCase("init") || ((String)_view.getConstraintComboBox().getSelectedItem()).equalsIgnoreCase("last")) {
            			
            		if(((String)_view.getFirstActivityComboBox().getSelectedItem()).equalsIgnoreCase("---")) {
            	       		JOptionPane.showMessageDialog(null, "Please associate a valid activity to the DECLARE constraint!", "ATTENTION!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/info_icon.png"));
            			}
            		else {
            			String constraint = (String)_view.getConstraintComboBox().getSelectedItem();
            			String activity = (String)_view.getFirstActivityComboBox().getSelectedItem();
            			String declare_constraint = constraint + "(" + activity + ")";
            			if(_view.getConstraintsListModel().contains(declare_constraint))
            				JOptionPane.showMessageDialog(null, "The DECLARE constraint '" + declare_constraint + "' already exists!", "ATTENTION!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/info_icon.png"));
            			else
            				_view.getConstraintsListModel().addElement(declare_constraint);
            		}
            	}
            	else if(
            			(((String)_view.getFirstActivityComboBox().getSelectedItem()).equalsIgnoreCase("---")) || ((String)_view.getSecondActivityComboBox().getSelectedItem()).equalsIgnoreCase("---"))
            			{       		
            	       		JOptionPane.showMessageDialog(null, "Please associate a valid activity to the DECLARE constraint!", "ATTENTION!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/info_icon.png"));
            			}
            	else {
        			String constraint = (String)_view.getConstraintComboBox().getSelectedItem();
        			String activity1 = (String)_view.getFirstActivityComboBox().getSelectedItem();
        			String activity2 = (String)_view.getSecondActivityComboBox().getSelectedItem();
        			String declare_constraint = constraint + "(" + activity1 + "," + activity2 + ")";
        			if(_view.getConstraintsListModel().contains(declare_constraint))
        				JOptionPane.showMessageDialog(null, "The DECLARE constraint '" + declare_constraint + "' already exists!", "ATTENTION!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/info_icon.png"));
        			else if(activity1.equalsIgnoreCase(activity2))
        				JOptionPane.showMessageDialog(null, "The activities used as arguments of the DECLARE constraint must be different!", "ATTENTION!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/info_icon.png"));
        			else
        				_view.getConstraintsListModel().addElement(declare_constraint);
        		}
            	           	
            }
        });
		
		_view.getRemoveButton().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
            	
            	if(_view.getConstraintsList().getSelectedIndex() == -1) { //no constraint selected
            		JOptionPane.showMessageDialog(null, "Please select a constraint to remove!", "ATTENTION!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/info_icon.png"));
            	} 
               	else {
                    	int index = _view.getConstraintsList().getSelectedIndex();   	
                		_view.getConstraintsListModel().removeElementAt(index);
                		
                	}
            	
            }
        });
		
	      _view.getAAAI17_encoding_radio_button().addItemListener(new ItemListener() {
	          public void itemStateChanged(ItemEvent e) {      
	        	 if(e.getStateChange()==1) { 
		             Constants.setPDDL_encoding("AAAI17");
	        	  } 
	          }           
	       });

	      _view.getICAPS16_encoding_radio_button().addItemListener(new ItemListener() {
	          public void itemStateChanged(ItemEvent e) { 
		         if(e.getStateChange()==1) { 
		             Constants.setPDDL_encoding("ICAPS16");
		         }
	          }           
	       });
	      
		
		_view.getPreviousStepButton().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
        			 Constants.getTracesPerspective().setComponentEnabled(true);
        			 Constants.getConstraintsPerspective().setComponentEnabled(false);
                 	 Constants.getMenuPerspective().getImportDeclareMenuItem().setEnabled(false);
                 	Constants.getMenuPerspective().getImportMenu().setEnabled(false);
                 	Constants.getMenuPerspective().getImportDOTAutomatonMenuItem().setEnabled(false);

            }
        });
		
				
		_view.getNextStepButton().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {    
				Constants.setBack_from_cost(false);       	          	
            	//
            	// The tool works properly only if the set of Declare/LTL constraints is not empty. Otherwise, it throws an exception.
	        	//
            	if(_view.getConstraintsListModel().getSize()>0) {
	        			 
        			//
        			// An instance of kind PlannerPerspective is created. Basically, it is a JDialog that allows the user to choose between 
            		// several options for the creation of customized planning domains/problems.
        			//
        			PlannerPerspective ple = new PlannerPerspective();
        			Constants.setPlannerPerspective(ple);        			
	        			
        			//
					// Reset and reinitialize the global vector with the costs of adding/removing activities in/from the trace.
					//
        			Constants.setActivitiesCost_vector(new Vector<Vector<String>>());    	
        			
        			// Reset the vector containing the minimum and maximum length of the traces.
    	         	Constants.setMinimumLengthOfATrace(0);
    	         	Constants.setMaximumLengthOfATrace(0);
        			
	             	for(int i=0;i<Constants.getAlphabetPerspective().getAlphabetListModel().size();i++) {
	            		String string = (String) Constants.getAlphabetPerspective().getAlphabetListModel().getElementAt(i);
	            		
	            		//
	            		// Update the GUI to show the complete alphabet of activities of the constraints and of the log.
	            		//
	            		ple.getActivitiesArea().append(string + "\n");
	            		
	            		//
	            	    // Update the global vector containing the cost of adding/removing activities in/from the trace (the default cost is equal to 1).
	            		//
	            		Vector<String> v = new Vector<String>();
	            		v.addElement(string);
		            	v.addElement("1");
		            	v.addElement("1");	
	            		Constants.getActivitiesCost_vector().addElement(v);
	            		
	             		}
	             		
            		ple.getActivitiesArea().setCaretPosition(0);
            		////////////////////////////////////////////////
            		
            		//
	        		// Reset the global vector containing the list of Declare/LTL constraints.
	         		//	
            		Constants.setAllConstraints_vector(new Vector<String>()); 
	         			
            		//
	         		// Reset the vector containing the alphabet of activities involved ONLY in the Declare/LTL constraints.
	         		//
            		Constants.setAlphabetOfTheConstraints_vector(new Vector<String>());
	         			
            		
            		/////// #### ICAPS2016 #### ////////////////////////////
		         			
	         		// LPSolver Object required to solve the system of inequalities represented by the DECLARE constraints (to get the number of instances to possibly align a trace)
		         	//LPsolver lpsolver = new LPsolver();
	         			
		         	/////// #### END of ICAPS2016 #### ////////////////////////////
		         	
		         	
		         	/////// **** AAAI2017 **** ////////////////////////////       			
	         			         		
		         	//
		         	// LTL formula that records the conjunction of the single LTL formulas of any automaton (in order to build a product automaton).
		         	//
		         	//String ltl_formula_for_product_automaton = new String();
		         			
		         	//
		         	// Create a local vector containing an automaton for any Declare/LTL constraint.
	         		//	
		         	Vector<Automaton> automata_vector = new Vector<Automaton>();
	         			
	         		//
		         	// Create a local vector containing the relevant transitions (a transition is said to be "relevant" if the source and the target state 
		         	// are different) of any automaton representing a Declare/LTL constraint.
		         	//	
		         	Vector<RelevantTransition> relevant_transitions_vector = new Vector<RelevantTransition>();
		         	
		         	//
		         	// Create a local vector containing the relevant transitions (a transition is said to be "relevant" if the source and the target state 
		         	// are different or if it is a add/del transition with cost different from 0) of the cost automaton.
		         	//
		         	Vector<RelevantTransition> cost_automaton_relevant_transitions_vector = new Vector<RelevantTransition>();
	         	
		         	//
         			// Reset the global vectors used to record all the states/the accepting states/the initial states 
	         		// of the automata associated to the Declare/LTL constraints.
		         	//
	         		Constants.setAutomataInitialStates_vector(new Vector<String>());
	         		Constants.setAutomataAcceptingStates_vector(new Vector<String>());
	         		Constants.setAutomataAllStates_vector(new Vector<String>());
	         		
		         	//
         			// Reset the global auxiliar stringbuffers used to record all the states/the accepting states/the initial states 
	         		// of the automata associated to the Declare/LTL constraints in the PDDL format.
		         	//
	         		Constants.setPDDLAutomataInitialStates_sb(new StringBuffer());
	         		Constants.setPDDLAutomataAcceptingStates_sb(new StringBuffer());
	         		Constants.setPDDLAutomataAllStates_sb(new StringBuffer());
	         		
		         	//
         			// Reset the global vector used to record the abstract accepting states of the automata associated to the Declare/LTL constraints.
		         	//
	         		Constants.setAutomataAbstractAcceptingStates_vector(new Vector<String>());
	         		
		         	//
         			// Reset the global auxiliar StringBuffer used to record all the PDDL actions required to connect the regular accepting states 
	         		// of one automaton to the abstract states stored in the vector "Constant.automata_abstract_accepting_states".
	         		//
	         		//Constants.setPDDLActionsForAbstractAcceptingStates_sb(new StringBuffer());
	         		         		
		         	//
         			// Reset the global vector used to record the non-accepting sink states of the automata associated to the Declare/LTL constraints.
		         	//
	         		Constants.setAutomataSinkNonAcceptingStates_vector(new Vector<String>());
	         		         	
	         		//
	         		// Define the prefix and the index of the states of the automata and of their relevant transitions.
	         		// For example, if the first automaton (i.e., with "automaton_index" equal to 0) has two states and three relevant transitions, 
	         		// we would have: s_0_0, s_0_1 (states) and tr_0_1, tr_0_2, tr_0_3 (relevant transitions).
	         		// A second automaton will "automaton_index" equal to 1, a third automaton will have "automaton_index" equal to 2, and so on.
	         		//
	         		String st_prefix = "s";
	         		String tr_prefix = "tr";
	         		int automaton_index = 0;
	         		int single_tr_index = 0;	
	         		
	         		//
	         		// Reset the local Multimap "transitions_map", which will contain the list of relevant transitions taken from 
	         		// any automaton with the associations to their specific label (e.g., a=[tr_0_0,tr_1_0], b=[tr_1_2], etc.).
	         		//
	         		Multimap<String, String> transitions_map = HashMultimap.create();
	         		
	         		//
	         		// Reset the local Multimap "transitions_map", which will contain the list of relevant transitions taken from 
	         		// the cost automaton with the associations to their specific label (e.g., a=[tr_0_0,tr_1_0], b=[tr_1_2], etc.).
	         		//
	         		Multimap<String, String> cost_automaton_transitions_map = HashMultimap.create();
	         				         				
	         	    /////// **** END OF AAAI2017 **** ////////////////////////////

	         		//
	         		// For any Declare/LTL constraint, generate the supporting structures required to synthexize correct planning domains and problems.
	         		//	         		
	         		for(int k=0;k<Constants.getConstraintsPerspective().getConstraintsListModel().size();k++) { 
	         				
	         			
	         			/////// **** AAAI2017 **** ////////////////////////////       	
	         			
	         			//
    		         	// Reset the local LTL formula that records the Declare/LTL constraint under consideration.
    		         	//
    	         		String ltl_formula = new String();
    	         		
	         			//
	         			// Reset the local vector used to record the accepting states of an automaton.
	         			//
	         			Vector<String> automaton_accepting_states_vector = new Vector<String>();
	         			
	         			//
	         			// Reset for any Declare/LTL constraint - i.e., for any corresponding automaton - the index of its relevant transitions.
	         			//	
	         			single_tr_index = 0;
	         				
	         			/////// **** END OF AAAI2017 **** ////////////////////////////
	            		
	         			//
	         			// For any Declare/LTL constraint, update the instance of kind PlannerPerspective in order to show in the associated GUI 
	         			// (when the FOR cycle completes) the complete list of Declare/LTL constraints defined by the user. 
	         			//
	         			String temporal_constraint = (String) Constants.getConstraintsPerspective().getConstraintsListModel().getElementAt(k);   			
	            		ple.getConstraintsArea().append(temporal_constraint + "\n");
	             		
	            		//
	         			// Update the global vector containing the Declare/LTL constraints with the actual constraint under consideration.
	         			//	
	            		Constants.getAllConstraints_vector().addElement(temporal_constraint); 			
	            		
	            		String constraint_name = "";
	            		
	            		if(temporal_constraint.startsWith("LTL{")) { 	    
	            			
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
							//System.out.println(activities_of_ltl_formula);
							for(int i = 0; i<activities_of_ltl_formula_array.length;i++) {
								if(!activities_of_ltl_formula_array[i].equalsIgnoreCase("")) {
									//System.out.println(activities_of_ltl_formula_array[i]);
									if(!Constants.getAlphabetOfTheConstraints_vector().contains(activities_of_ltl_formula_array[i])) {
		            					Constants.getAlphabetOfTheConstraints_vector().addElement(activities_of_ltl_formula_array[i]);
								
											//
				    	            	    // Update the global vector containing the cost of adding/removing activities in/from the trace (the default cost is equal to 1).
				    	            		//
				    	            		Vector<String> v = new Vector<String>();
				    	            		v.addElement(activities_of_ltl_formula_array[i]);			    	          		
				    		            	v.addElement("1");
				    		            	v.addElement("1");
				    	            		Constants.getActivitiesCost_vector().addElement(v);

											//ple.getActivitiesArea().append(activities_of_ltl_formula_array[i] + "\n");
									}
								
								
								}
							}
	            		}	
	            		else if(!temporal_constraint.startsWith("DFA{")) {
		             		//
		            		// Extract the activities involved in the constraint under consideration.
		            		//
		            		String[] constraint_splitted = temporal_constraint.split("\\(");
	            			
		            		//
		            		// Extract the name of the constraint (existence, response, etc.).
		            		//
		            		constraint_name = constraint_splitted[0];
	            			
		            		String[] constraint_splitted_2 = constraint_splitted[1].split("\\)");
	            			
		            		//
		            		// FIRST CASE: the constraint involves two activities (e.g., response(A,B)).
		            		//	            		
		            		if(constraint_splitted_2[0].contains(",")) {
	            			
		            			String[] constraint_splitted_3 = constraint_splitted_2[0].split(",");	    
	            				
			            		//
			            		// Extract the name of the first activity (e.g., if the constraint is response(A,B), the first activity is "A").
			            		//
		            			String activity1 = constraint_splitted_3[0];
	            			
			            		//
			            		// Extract the name of the second activity (e.g., if the constraint is response(A,B), the second activity is "B").
			            		//
		            			String activity2 = constraint_splitted_3[1];
            						            				
		            			//
		            			// Update the global vector containing the alphabet of activities involved in the Declare/LTL constraints
		            			//	
		            			if(!Constants.getAlphabetOfTheConstraints_vector().contains(activity1))
		            				Constants.getAlphabetOfTheConstraints_vector().addElement(activity1);
		            				
		            			if(!Constants.getAlphabetOfTheConstraints_vector().contains(activity2))
		            				Constants.getAlphabetOfTheConstraints_vector().addElement(activity2);
	            			
		            			/////// **** AAAI2017 **** ////////////////////////////
		            				
		            			if(Constants.getPDDL_encoding().equalsIgnoreCase("AAAI17")) {
		            				        	         			
		        	         		//
		        	         		// Infer the LTL constraint associated to any Declare template.
		        	         		//	            				
		        	         		if(constraint_name.equalsIgnoreCase("choice"))
		        	         			ltl_formula = LTLFormula.getFormulaByTemplate(DeclareTemplate.Choice,activity1,activity2);	
		        	         		else if(constraint_name.equalsIgnoreCase("exclusive choice"))
		        	         			ltl_formula = LTLFormula.getFormulaByTemplate(DeclareTemplate.Exclusive_Choice,activity1,activity2);
		        	         		else if(constraint_name.equalsIgnoreCase("responded existence"))
		        	         			ltl_formula = LTLFormula.getFormulaByTemplate(DeclareTemplate.Responded_Existence,activity1,activity2);
		        	         		else if(constraint_name.equalsIgnoreCase("not responded existence")) 
		        	         			ltl_formula = LTLFormula.getFormulaByTemplate(DeclareTemplate.Not_Responded_Existence,activity1,activity2);	        	         			
		        	         		else if(constraint_name.equalsIgnoreCase("co-existence"))	        	         			
		        	         			ltl_formula = LTLFormula.getFormulaByTemplate(DeclareTemplate.CoExistence,activity1,activity2);	 
		        	         		else if(constraint_name.equalsIgnoreCase("not co-existence"))	        	         		        	         			
		        	         			ltl_formula = LTLFormula.getFormulaByTemplate(DeclareTemplate.Not_CoExistence,activity1,activity2);	 	        	         			
		        	         		else if(constraint_name.equalsIgnoreCase("response"))	        	         		        	         			
		        	         			ltl_formula = LTLFormula.getFormulaByTemplate(DeclareTemplate.Response,activity1,activity2);	 	 	        	         			
		        	         		else if(constraint_name.equalsIgnoreCase("precedence"))	        	         		        	         			
		        	         			ltl_formula = LTLFormula.getFormulaByTemplate(DeclareTemplate.Precedence,activity1,activity2);		   
		        	         		else if(constraint_name.equalsIgnoreCase("succession"))	        	         		        	         			
		        	         			ltl_formula = LTLFormula.getFormulaByTemplate(DeclareTemplate.Succession,activity1,activity2);	
		        	         		else if(constraint_name.equalsIgnoreCase("chain response"))	        	         		        	         			
		        	         			ltl_formula = LTLFormula.getFormulaByTemplate(DeclareTemplate.Chain_Response,activity1,activity2);		
		        	         		else if(constraint_name.equalsIgnoreCase("chain precedence"))	        	         		        	         			
		        	         			ltl_formula = LTLFormula.getFormulaByTemplate(DeclareTemplate.Chain_Precedence,activity1,activity2);	
		        	         		else if(constraint_name.equalsIgnoreCase("chain succession"))	        	         		        	         			
		        	         			ltl_formula = LTLFormula.getFormulaByTemplate(DeclareTemplate.Chain_Succession,activity1,activity2);
		        	         		else if(constraint_name.equalsIgnoreCase("alternate response"))	        	         		        	         			
		        	         			ltl_formula = LTLFormula.getFormulaByTemplate(DeclareTemplate.Alternate_Response,activity1,activity2);
		        	         		else if(constraint_name.equalsIgnoreCase("alternate precedence"))	        	         		        	         			
		        	         			ltl_formula = LTLFormula.getFormulaByTemplate(DeclareTemplate.Alternate_Precedence,activity1,activity2);	        	         			
		        	         		else if(constraint_name.equalsIgnoreCase("alternate succession"))	
		        	         			ltl_formula = LTLFormula.getFormulaByTemplate(DeclareTemplate.Alternate_Succession,activity1,activity2);	  
		        	         		else if(constraint_name.equalsIgnoreCase("not response"))	        	         		        	         			
		        	         			ltl_formula = LTLFormula.getFormulaByTemplate(DeclareTemplate.Not_Response,activity1,activity2);	        	         			
		        	         		else if(constraint_name.equalsIgnoreCase("not precedence"))	        	         		        	         			
		        	         			ltl_formula = LTLFormula.getFormulaByTemplate(DeclareTemplate.Not_Precedence,activity1,activity2);		
		        	         		else if(constraint_name.equalsIgnoreCase("not succession"))	        	         		        	         			
		        	         			ltl_formula = LTLFormula.getFormulaByTemplate(DeclareTemplate.Not_Succession,activity1,activity2);	
		        	         		else if(constraint_name.equalsIgnoreCase("not chain response"))	        	         		        	         			
		        	         			ltl_formula = LTLFormula.getFormulaByTemplate(DeclareTemplate.Not_Chain_Response,activity1,activity2);	 	        	         			
		        	         		else if(constraint_name.equalsIgnoreCase("not chain precedence"))	        	         		        	         			
		        	         			ltl_formula = LTLFormula.getFormulaByTemplate(DeclareTemplate.Not_Chain_Precedence,activity1,activity2);	 	  
		        	         		else if(constraint_name.equalsIgnoreCase("not chain succession"))	        	         		        	         			
		        	         			ltl_formula = LTLFormula.getFormulaByTemplate(DeclareTemplate.Not_Chain_Succession,activity1,activity2);	 	  
	        	      
		            				}
		            				/////// **** END of AAAI2017 **** ////////////////////////////	
	            			}
		            		//
		            		// SECOND CASE: the constraint involves one activity (e.g., existence(A))
		            		//
		            		else {
	            				
	            				String activity = constraint_splitted_2[0]; 
	            				
	            				if(!Constants.getAlphabetOfTheConstraints_vector().contains(activity))
	            					Constants.getAlphabetOfTheConstraints_vector().addElement(activity);
	            				
								/////// **** AAAI2017 **** ////////////////////////////
								
	            				if(Constants.getPDDL_encoding().equalsIgnoreCase("AAAI17")) {
		        	         			
		        	         		//
		        	         		// Infer the LTL constraint associated to any Declare template.
		        	         		//
					       			if(constraint_name.equalsIgnoreCase("existence"))
					       				ltl_formula = LTLFormula.getFormulaByTemplate(DeclareTemplate.Existence,activity,null);
					       			else if(constraint_name.equalsIgnoreCase("absence"))
					       				ltl_formula = LTLFormula.getFormulaByTemplate(DeclareTemplate.Absence,activity,null);	  
					       			else if(constraint_name.equalsIgnoreCase("init"))
					       				ltl_formula = LTLFormula.getFormulaByTemplate(DeclareTemplate.Init,activity,null);	    
					       			else if(constraint_name.equalsIgnoreCase("absence2"))
					       				ltl_formula = LTLFormula.getFormulaByTemplate(DeclareTemplate.Absence2,activity,null);	     
	            				}
	            				/////// **** END of AAAI2017 **** ////////////////////////////
		            		}
	            		}
        				/////// **** AAAI2017 **** ////////////////////////////
						if(Constants.getPDDL_encoding().equalsIgnoreCase("AAAI17")) {
		            		//
	    	         		// Update the LTL formula that will be used to generate the product automaton.
	    	         		//	
							/*
	       	         		if(Constants.getMenuPerspective().getProductAutomatonMenuItem().isSelected())  {
			            		if(k+1 < Constants.getConstraintsPerspective().getConstraintsListModel().size())
		    	         			ltl_formula_for_product_automaton += ltl_formula + " /\\ "; 
		    	         		else
		    	         			ltl_formula_for_product_automaton += ltl_formula; 
		       	         	}
       	         		    */
	    	         		//
	    	         		// Infer the automaton associated to the LTL formula under consideration.
	    	         		//	
	       	         		
		       	     		/////////////////////////////////////////////////////////////////////////////
		       	     		/////////////////////////////////////////////////////////////////////////////
		       	     		/////////////////////////////////////////////////////////////////////////////
	       	         		Automaton automaton = null;
	       	         		if(temporal_constraint.startsWith("DFA{")) {
	       	         			
		       	         		String DFA_file_path = temporal_constraint.replace("DFA{", "");
		       	         		DFA_file_path = DFA_file_path.replace("}", "");            			
	   	   	         			//ltl_formula = temporal_constraint;
		       	         			
		       	         			
		       	         		try {
		       	         			automaton = Utilities.getAutomatonForModelLearning(DFA_file_path);
		       	         			//automaton = Utilities.getAutomatonForModelLearningDot(DFA_file_path);
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

										System.out.println(t.getSource().toString() +" "+ t.toString()+" "+t.getTarget().toString());



				       	                  if(!Constants.getAlphabetOfTheConstraints_vector().contains(t.getPositiveLabel())) {
				       	      				Constants.getAlphabetOfTheConstraints_vector().addElement(t.getPositiveLabel());
				       	      				//
				    	            		// Update the GUI to show the complete alphabet of activities of the constraints and of the log.
				    	            		//
											if(!Constants.getActivitiesRepository_vector().contains(t.getPositiveLabel())) {
				    	            		ple.getActivitiesArea().append(t.getPositiveLabel() + "\n");
											}
				    	            		
				    	            		//
				    	            	    // Update the global vector containing the cost of adding/removing activities in/from the trace (the default cost is equal to 1).
				    	            		//
				    	            		Vector<String> v = new Vector<String>();
				    	            		v.addElement(t.getPositiveLabel());
				    		            	v.addElement("1");
				    		            	v.addElement("1");
				    	            		Constants.getActivitiesCost_vector().addElement(v);
				    	            		
				       	                  }
				       	                  }
			       	         	}
	       	         		}
		       	     		/////////////////////////////////////////////////////////////////////////////
		       	     		/////////////////////////////////////////////////////////////////////////////
		       	     		/////////////////////////////////////////////////////////////////////////////
       	         		
       	         		else {
       	         			try {
								automaton = LTLFormula.generateAutomatonByLTLFormula(ltl_formula);
							} catch (SyntaxParserException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
       	         		}
    	         		     		
    	         		
    	         		State initial_state_of_the_automaton = automaton.getInit();
    	      
	            		//
	            		// 1A. Add to the global vector of sink states each non accepting state of the automaton (we are still not sure that it is a sink).
	            		//
	    	         		Iterator<State> it_states =  automaton.iterator();
	    	         		
	    	         		while(it_states.hasNext()) {	        	            			 
		            			 State s = (State) it_states.next();
	    	            		 if(!s.isAccepting())
	    	            			 Constants.getAutomataSinkNonAcceptingStates_vector().addElement(st_prefix + "_" + automaton_index + "_" + s.getId());
	    	         		}
    	         		
    	         		///////////////////////////////////////////////////////////////
    	         		
    	         		//
    	         		// Identify the initial state of the specific automaton under consideration and records it in the global vector/stringbuffer of the initial states.
    	         		//
    	         		if(!Constants.getAutomataInitialStates_vector().contains(st_prefix + "_" + automaton_index + "_" + initial_state_of_the_automaton.getId())) {
    	         			Constants.getAutomataInitialStates_vector().addElement(st_prefix + "_" + automaton_index + "_" + initial_state_of_the_automaton.getId());
    	         			Constants.getPDDLAutomataInitialStates_sb().append("(currstate " + st_prefix + "_" + automaton_index + "_" + initial_state_of_the_automaton.getId() + ")\n");
    	         		}
    	               			
    	         		//
    	         		// For any transition of the automaton under consideration, we check if such transition is relevant 
    	         		// (i.e., if it connects a target state different from the source state).
    	         		//
	               		Iterator<Transition> it = automaton.transitions().iterator();
	               		
		           		System.out.println("Relevant Transitions from the Constraints automata: ");
	            		while(it.hasNext()) {
	            			 
	            			 Transition transition = (Transition) it.next();
	            			 int tr_source_state_id = transition.getSource().getId();
	            			 int tr_target_state_id = transition.getTarget().getId();
	            			 
	            			 if(tr_source_state_id != tr_target_state_id) {
	            				         		        	            				 
        	            		 //
        	            		 // 2A. If the target state and the source state are different, we are sure that the source state is not a sink.
    	            			 // Therefore, we can remove it from the global vector of sink states.
        	            		 //
	    	            			 if(Constants.getAutomataSinkNonAcceptingStates_vector().contains(st_prefix + "_" + automaton_index + "_" + tr_source_state_id))
	    	            				 Constants.getAutomataSinkNonAcceptingStates_vector().removeElement(st_prefix + "_" + automaton_index + "_" + tr_source_state_id);
		         	         	
	         	         		 
	            				 //	        	            	  			
    	            			 // If the transition is relevant, we identify its source state, its target state and its label.
    	            			 //
	            				 String tr_source_state = st_prefix + "_" + automaton_index + "_" + tr_source_state_id;
	            				 String tr_target_state = st_prefix + "_" + automaton_index + "_" + tr_target_state_id;
	            				 String tr_id = null;
	            				 String tr_label = null;
	            				 
    	            			 //
    	            			 // Simple case: the label is positive (e.g., A).
    	            			 //
	            				 if(!transition.isNegative())  {
	            					 tr_id = tr_prefix + "_" + automaton_index + "_" + single_tr_index;
	            					 tr_label = transition.getPositiveLabel();
    	            				
        	            			 //
        	            			 // Create a new RelevantTransition object and records it in the global vector of relevant transitions.
        	            			 //	
    				 				 System.out.println(tr_id +" "+tr_source_state+" "+tr_target_state+" "+tr_label+" original trans: "+transition.getPositiveLabel()+ " cost: 0");
	            					 RelevantTransition relevant_transition = new RelevantTransition(tr_id, tr_source_state, tr_target_state, tr_label, transition.getPositiveLabel(), 0);
    	            				 relevant_transitions_vector.addElement(relevant_transition);
    	            				
        	            			 //
        	            			 // Associate in the "transition_map" object the label of the transition just created to its ID. Remember that "transitions_map" 
        	            			 // will contain the list of relevant transitions taken from any automaton with the associations to their specific label 
        	            			 // (e.g., a=[tr_0_0,tr_1_0], b=[tr_1_2], etc.).
        	            			 //	
    	            				 transitions_map.put(tr_label, tr_id);
    	            				 
    	            				 single_tr_index++;
	            				 }
	            				 else { // If the label is negative (e.g., !A) there are several possible concrete positive labels (...B,C,D,E,...etc.), 
	            					    // i.e., several possible valid relevant transitions to be recorded. Starting from a negative label, the positive 
	            					    // ones are inferred from the repository of activities involved in the log and in the Declare constraints.
	            					 
	            					 Collection<String> coll = transition.getNegativeLabels();

	            					 System.out.println("transizione negativa rilevata!\n");
									 System.out.println(coll.toString());


	            					 for(int ix=0;ix<Constants.getActivitiesRepository_vector().size();ix++) {
	            						 tr_id = tr_prefix + "_" + automaton_index + "_" + single_tr_index;
	            						 String symbol = Constants.getActivitiesRepository_vector().elementAt(ix);
	            						 if(!coll.contains(symbol)) {
	            							 tr_label = symbol;
	        				 				 System.out.println(tr_id +" "+tr_source_state+" "+tr_target_state+" "+tr_label+" original trans: "+transition.getPositiveLabel()+ " cost: 0");
	        	            				 RelevantTransition relevant_transition = new RelevantTransition(tr_id, tr_source_state, tr_target_state, tr_label, transition.getPositiveLabel(), 0);
	        	            				 relevant_transitions_vector.addElement(relevant_transition);
	        	            				 
	        	            				 //
	        	            				 // Associate in the "transition_map" object the label of the transition just created to its ID
	        	            				 //
	        	            				 transitions_map.put(tr_label, tr_id);
	        	            				 
	        	            				 single_tr_index++;
	            						 }
	            					 }
	            				 }
    	            			 
    	            			 //
    	            			 // Keep track of all the states of the automaton under consideration and records it in the corresponding global stringbuffer/vector.
    	            			 //
    	            			 if(!Constants.getAutomataAllStates_vector().contains(tr_source_state))  {
    	            				 Constants.getAutomataAllStates_vector().addElement(tr_source_state);
    	            				 Constants.getPDDLAutomataAllStates_sb().append(tr_source_state + " - state\n");  
    	            				// System.out.println("source_state: "+tr_source_state + " - state\n");
    	            			 } 
    	            			 if(!Constants.getAutomataAllStates_vector().contains(tr_target_state))  {
    	            				 Constants.getAutomataAllStates_vector().addElement(tr_target_state);
    	            				 Constants.getPDDLAutomataAllStates_sb().append(tr_target_state + " - state\n");    
    	            				// System.out.println("target_state: "+tr_target_state + " - state\n");
    	            			 }
            				 
    	            			 //
    	            			 // Keep track of all the accepting states of the automaton under consideration and records it in a local vector.
    	            			 //
    	            			 if(transition.getSource().isAccepting() && !automaton_accepting_states_vector.contains(tr_source_state))  {
    	            				automaton_accepting_states_vector.addElement(tr_source_state);
    	            			 }
    	            			 if(transition.getTarget().isAccepting() && !automaton_accepting_states_vector.contains(tr_target_state))  {
    	            				automaton_accepting_states_vector.addElement(tr_target_state);
    	            			 }	        	         
	            				 
	            			 }
	            			 
	            			// System.out.println(t.getSource().getId());
	            			// System.out.println(t.getTarget().getId());
	            			// System.out.println(t.getPositiveLabel());
	            			// System.out.println(t.getNegativeLabels());
	            			
	            		}
	              		
		            // Record the accepting states of the automaton under consideration in the corresponding global vector and in the 
		            // global StringBuffer used to take trace of the goal condition. 
	         		//
	            	// FIRST CASE: The automaton has several accepting states.
	            	//
	            	// If an automaton has more than one accepting state, such accepting states must be nested in an OR.
	            	// However, if disjunctive conditions are not allowed, an abstract state for the automaton must be generated, 
	            	// together with as many planning actions as are the regular accepting states. Such actions represent the transitions 
	            	// between the regular accepting states and the abstract accepting state generated.
	         		//
	         		if(automaton_accepting_states_vector.size() > 1) {
	            			
	         			//
	         			// If the planner used to synhesize the alignment IS NOT ABLE to manage disjunctive goal conditions, 
	         			// we need to generate a single ABSTRACT accepting state for the automaton, used as target for any regular accepting state.
	         			//
	         		
	         				String aut_abstract_state = st_prefix + "_" + automaton_index + "_" + "abstract";
	         				
	         				Constants.getAutomataAbstractAcceptingStates_vector().addElement(aut_abstract_state);
	         				
	         				Constants.getPDDLAutomataAcceptingStates_sb().append("(currstate " + aut_abstract_state + ")\n");  
	         				         				
	         				Constants.getPDDLAutomataAllStates_sb().append(aut_abstract_state + " - state\n"); 
    	            		
	         				for(int yu=0;yu<automaton_accepting_states_vector.size();yu++) {	         					
	         					if(!Constants.getAutomataAcceptingStates_vector().contains(automaton_accepting_states_vector.elementAt(yu)))
	         						Constants.getAutomataAcceptingStates_vector().addElement(automaton_accepting_states_vector.elementAt(yu));	         					
	         					if(!Constants.getAutomataAllStates_vector().contains(automaton_accepting_states_vector.elementAt(yu)))
	         						Constants.getAutomataAllStates_vector().addElement(automaton_accepting_states_vector.elementAt(yu));	         					
    	            		}
	         			
	         		}
	         		//
	         		// SECOND CASE: The automaton has just one accepting state.
	         		//
	            	else {
	            		Constants.getAutomataAcceptingStates_vector().addElement(automaton_accepting_states_vector.elementAt(0));
	            		Constants.getPDDLAutomataAcceptingStates_sb().append("(currstate " + automaton_accepting_states_vector.elementAt(0) + ")\n"); 
	            	}
	            		
    		         	//
    		         	// Update the local vector containing an automaton for any Declare/LTL constraint.
    	         		//	
	         			automata_vector.addElement(automaton);	        	         			
	            		
	            		//
	            		// The index is increased after having analyzed any automaton, in order to have unique IDs identifying uniquely the automata.
	            		//
	            		automaton_index++;
						}
            			/////// **** END of AAAI2017 **** ////////////////////////////
	         			
	         
	         		} // END of the FOR-cycle to navigate the the list of Declare/LTL constraints.
	         		
	         		
	         		/////// **** COST AUTOMATA **** //////////////////////////// 
	         		
	         		//
        			// An instance of kind CostPerspective is created. Basically, it is a JDialog that allows the user to assign 
            		// a fixed (deviation) cost to each activity, or to create specific cost models.
	         		//


	         		//CostPerspective cst = new CostPerspective();        // version with Cost DFA import
             		//Constants.setCostPerspective(cst); 
             		CostPerspective3 cst3 = new CostPerspective3();      // version with patterns only

             		
	         		Utilities.emptyFolder("cost_automata");             		
             		Vector<Automaton> cost_automata_vector = new Vector<Automaton>();

					for(int k=0; k < cst3.getPatternsListModel().size(); k++){
						String cost_model = (String) cst3.getPatternsListModel().getElementAt(k); 	
             		//for (String cost_model : cst.getCostModelTextArea().getText().split("\\n")) {
						
             			Automaton automaton = null;
       	         		if(cost_model.startsWith("DFA{")) {
       	         			
	       	         		String DFA_file_path = cost_model.replace("DFA{", "");
	       	         		DFA_file_path = DFA_file_path.replace("}", "");            				       	         			
	       	         			
	       	         		try {
	       	         			automaton = Utilities.getAutomatonForModelLearning(DFA_file_path);
	       	         			//automaton = Utilities.getAutomatonForModelLearningDot(DFA_file_path);
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}
       	         		}
       	         		
       	         		else if(cost_model.startsWith("pattern")) {
       	         			
       	         		StringBuffer cost_automaton_buffer = new StringBuffer();
       	         		
       	         		String arguments = cost_model.replaceAll(" ", "");
       	         		arguments = arguments.substring(9);
       	         		arguments = arguments.replace(")", "");
       	         		String[] arguments_splitted = arguments.split(",");
       	         		
             			String activity_1 = arguments_splitted[0];
    	         		String activity_2 =  arguments_splitted[1];
    	         		String cost_1 =  arguments_splitted[2];
    	         		String cost_2 =  arguments_splitted[3];
    	         		
    	         		System.out.println("Arguments: "+ activity_1+" "+activity_2+" "+cost_1+" "+cost_2);
       	         		
       	         		if(cost_model.startsWith("pattern1")) {
       	         			cost_automaton_buffer.append("digraph {\n");
       	         			cost_automaton_buffer.append("	fake0 [style=invisible]\n");
       	         			cost_automaton_buffer.append("	0 [root=true shape=doublecircle]\n");
       	         			cost_automaton_buffer.append("	1 [shape=doublecircle]\n");
       	         			cost_automaton_buffer.append("	2 [shape=doublecircle]\n");
       	         			cost_automaton_buffer.append("	fake0 -> 0 [style=bold]\n");
	         			
       	         			for(int i=0; i<Constants.getActivitiesRepository_vector().size(); i++) {
	         					String activity = Constants.getActivitiesRepository_vector().get(i);
	         					cost_automaton_buffer.append("	0 -> 0 [label="+activity+"]\n");
	         					cost_automaton_buffer.append("	0 -> 0 [label=del_"+activity+"_0]\n");	
       	         			}
       	         			cost_automaton_buffer.append("	0 -> 0 [label=!add_"+activity_1+"]\n");
       	         			cost_automaton_buffer.append("	0 -> 1 [label=add_" +activity_1+"_0]\n");
	         			
       	         			for(int i=0; i<Constants.getActivitiesRepository_vector().size(); i++) {
       	         				String activity = Constants.getActivitiesRepository_vector().get(i);
       	         				cost_automaton_buffer.append("	1 -> 0 [label="+activity+"]\n");
       	         				cost_automaton_buffer.append("	1 -> 0 [label=del_"+activity+"_0]\n");	
       	         			}
       	         			cost_automaton_buffer.append("	1 -> 0 [label=!add_"+activity_1+"]\n");
       	         			cost_automaton_buffer.append("	1 -> 2 [label=add_" +activity_1+"_0]\n");
	         			
       	         			for(int i=0; i<Constants.getActivitiesRepository_vector().size(); i++) {
       	         				String activity = Constants.getActivitiesRepository_vector().get(i);
	         					cost_automaton_buffer.append("	2 -> 2 [label="+activity+"]\n");
	         					cost_automaton_buffer.append("	2 -> 2 [label=del_"+activity+"_0]\n");	
       	         			}
       	         			cost_automaton_buffer.append("	2 -> 2 [label=!add_"+activity_1+"]\n");
       	         			cost_automaton_buffer.append("	2 -> 2 [label=add_" +activity_1+"_"+cost_1+"]\n");
	         				
       	         			cost_automaton_buffer.append("}");
	         			
       	         			Utilities.createFile("cost_automata/cost_automata_1.dot", cost_automaton_buffer);
       	         			
       	         		try {
       	         			automaton = Utilities.getAutomatonForModelLearning("cost_automata/cost_automata_1.dot");
       	         			//automaton = Utilities.getAutomatonForModelLearningDot(DFA_file_path);
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
       	         		
       	         		}
       	         		
       	         		if(cost_model.startsWith("pattern2")) {
       	         			cost_automaton_buffer.append("digraph {\n");
       	         			cost_automaton_buffer.append("	fake0 [style=invisible]\n");
       	         			cost_automaton_buffer.append("	0 [root=true shape=doublecircle]\n");
       	         			cost_automaton_buffer.append("	1 [shape=doublecircle]\n");
       	         			cost_automaton_buffer.append("	2 [shape=doublecircle]\n");
       	         			cost_automaton_buffer.append("	3 [shape=doublecircle]\n");
       	         			cost_automaton_buffer.append("	fake0 -> 0 [style=bold]\n");
	         			
       	         			for(int i=0; i<Constants.getActivitiesRepository_vector().size(); i++) {
	         					String activity = Constants.getActivitiesRepository_vector().get(i);
	         					cost_automaton_buffer.append("	0 -> 0 [label="+activity+"]\n");
	         					cost_automaton_buffer.append("	0 -> 0 [label=add_"+activity+"_0]\n");	
       	         			}
       	         			cost_automaton_buffer.append("	0 -> 0 [label=!del_"+activity_1+"]\n");
	         				cost_automaton_buffer.append("	0 -> 1 [label=del_" +activity_1+"_0]\n");
	         				cost_automaton_buffer.append("	1 -> 0 [label=" +activity_2+"]\n");
	         			
	         				for(int i=0; i<Constants.getActivitiesRepository_vector().size(); i++) {
	         					String activity = Constants.getActivitiesRepository_vector().get(i);
	         					cost_automaton_buffer.append("	1 -> 1 [label=add_"+activity+"_0]\n");	
	         				}
	         				cost_automaton_buffer.append("	1 -> 1 [label=!del_"+activity_1+"]\n");
	         				cost_automaton_buffer.append("	1 -> 1 [label=!"+activity_2+"]\n");
	         				cost_automaton_buffer.append("	1 -> 2 [label=del_" +activity_1+"_"+cost_1+"]\n");
	         				cost_automaton_buffer.append("	2 -> 0 [label=" +activity_2+"]\n");
	         			
	         				for(int i=0; i<Constants.getActivitiesRepository_vector().size(); i++) {
	         					String activity = Constants.getActivitiesRepository_vector().get(i);
	         					cost_automaton_buffer.append("	2 -> 2 [label=add_"+activity+"_0]\n");	
	         				}
	         				cost_automaton_buffer.append("	2 -> 2 [label=!del_"+activity_1+"]\n");
	         				cost_automaton_buffer.append("	2 -> 2 [label=!"+activity_2+"]\n");
	         				cost_automaton_buffer.append("	2 -> 3 [label=del_" +activity_1+"_"+cost_2+"]\n");
	         				cost_automaton_buffer.append("	3 -> 0 [label=" +activity_2+"]\n");
	         			
	         				for(int i=0; i<Constants.getActivitiesRepository_vector().size(); i++) {
	         					String activity = Constants.getActivitiesRepository_vector().get(i);
	         					cost_automaton_buffer.append("	3 -> 3 [label=add_"+activity+"_0]\n");	
	         				}
	         				cost_automaton_buffer.append("	3 -> 3 [label=!del_"+activity_1+"]\n");
	         				cost_automaton_buffer.append("	3 -> 3 [label=!"+activity_2+"]\n");
	         				cost_automaton_buffer.append("	3 -> 3 [label=del_" +activity_1+"_"+cost_2+"]\n");
	         			
	         				cost_automaton_buffer.append("}");
	         			
	         				Utilities.createFile("cost_automata/cost_automata_2.dot", cost_automaton_buffer);
	         				
	         				try {
	       	         			automaton = Utilities.getAutomatonForModelLearning("cost_automata/cost_automata_2.dot");
	       	         			//automaton = Utilities.getAutomatonForModelLearningDot(DFA_file_path);
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}
   	         			
       	         		}
       	         		
       	         		if(cost_model.startsWith("pattern3")) {
       	         			cost_automaton_buffer.append("digraph {\n");
       	         			cost_automaton_buffer.append("	fake0 [style=invisible]\n");
       	         			cost_automaton_buffer.append("	0 [root=true shape=doublecircle]\n");
       	         			cost_automaton_buffer.append("	1 [shape=doublecircle]\n");
       	         			cost_automaton_buffer.append("	fake0 -> 0 [style=bold]\n");
	         			
       	         			for(int i=0; i<Constants.getActivitiesRepository_vector().size(); i++) {
       	         				String activity = Constants.getActivitiesRepository_vector().get(i);
       	         				cost_automaton_buffer.append("	0 -> 0 [label=del_"+activity+"_0]\n");	
       	         			}
       	         			cost_automaton_buffer.append("	0 -> 0 [label=!add_"+activity_1+"]\n");
       	         			cost_automaton_buffer.append("	0 -> 0 [label=add_" +activity_1+"_"+cost_1+"]\n");
       	         			cost_automaton_buffer.append("	0 -> 0 [label=!" +activity_2+"]\n");
       	         			cost_automaton_buffer.append("	0 -> 1 [label=" +activity_2+"]\n");
	         			
       	         			for(int i=0; i<Constants.getActivitiesRepository_vector().size(); i++) {
       	         				String activity = Constants.getActivitiesRepository_vector().get(i);
       	         				cost_automaton_buffer.append("	1 -> 1 [label=del_"+activity+"_0]\n");	
       	         				cost_automaton_buffer.append("	1 -> 1 [label="+activity+"]\n");	
       	         			}
       	         			cost_automaton_buffer.append("	1 -> 1 [label=!add_"+activity_1+"]\n");
       	         			cost_automaton_buffer.append("	1 -> 1 [label=add_" +activity_1+"_"+cost_2+"]\n");
	         			
       	         			cost_automaton_buffer.append("}");
	         			
       	         			Utilities.createFile("cost_automata/cost_automata_3.dot", cost_automaton_buffer);
       	         			
       	         		try {
       	         			automaton = Utilities.getAutomatonForModelLearning("cost_automata/cost_automata_3.dot");
       	         			//automaton = Utilities.getAutomatonForModelLearningDot(DFA_file_path);
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
   	         			
       	         			}
       	         		
       	         		if(cost_model.startsWith("pattern4")) {
       	         			cost_automaton_buffer.append("digraph {\n");
       	         			cost_automaton_buffer.append("	fake0 [style=invisible]\n");
       	         			cost_automaton_buffer.append("	0 [root=true shape=doublecircle]\n");
       	         			cost_automaton_buffer.append("	1 [shape=doublecircle]\n");
       	         			cost_automaton_buffer.append("	fake0 -> 0 [style=bold]\n");
	         			
       	         			for(int i=0; i<Constants.getActivitiesRepository_vector().size(); i++) {
       	         				String activity = Constants.getActivitiesRepository_vector().get(i);
       	         				cost_automaton_buffer.append("	0 -> 0 [label="+activity+"]\n");
       	         				cost_automaton_buffer.append("	0 -> 0 [label=add_"+activity+"_0]\n");
       	         			}
       	         			cost_automaton_buffer.append("	0 -> 0 [label=!del_"+activity_1+"]\n");
       	         			cost_automaton_buffer.append("	0 -> 1 [label=del_"+activity_1+"_0]\n");
	         			
       	         			for(int i=0; i<Constants.getActivitiesRepository_vector().size(); i++) {
       	         				String activity = Constants.getActivitiesRepository_vector().get(i);
       	         				cost_automaton_buffer.append("	1 -> 1 [label="+activity+"]\n");	
       	         				cost_automaton_buffer.append("	1 -> 1 [label=add_"+activity+"_0]\n");	
       	         			}
       	         			cost_automaton_buffer.append("	1 -> 1 [label=!del_"+activity_1+"]\n");
	         				cost_automaton_buffer.append("	1 -> 1 [label=del_"+activity_1+"_"+cost_1+"]\n");
	         			
	         				cost_automaton_buffer.append("}");
	         			
	         				Utilities.createFile("cost_automata/cost_automata_4.dot", cost_automaton_buffer);
	         				
	         				try {
	       	         			automaton = Utilities.getAutomatonForModelLearning("cost_automata/cost_automata_4.dot");
	       	         			//automaton = Utilities.getAutomatonForModelLearningDot(DFA_file_path);
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}
   	         			
       	         		}
       	         		
       	         		}
       	         		
       	         		cost_automata_vector.add(automaton);

             		}
             		/*
             		
             		//
             		//
             		//
             		
             		//Import cost automaton
	         		Automaton cost_automaton_1 = null;
	         		Automaton cost_automaton_2 = null;
	         		Automaton cost_automaton_3 = null;
	         		Automaton cost_automaton_4 = null;
	         		String cost_automaton_file_path_1 = "/Users/giacomo/Desktop/cost_automata/cost_automaton_1.dot";
	         		String cost_automaton_file_path_2 = "/Users/giacomo/Desktop/cost_automata/cost_automaton_2.dot";
	         		String cost_automaton_file_path_3 = "/Users/giacomo/Desktop/cost_automata/cost_automaton_3.dot";
	         		String cost_automaton_file_path_4 = "/Users/giacomo/Desktop/cost_automata/cost_automaton_4.dot";
	         		
	         		try {
   	         			cost_automaton_1 = Utilities.getAutomatonForModelLearning(cost_automaton_file_path_1);
   	         			cost_automaton_2 = Utilities.getAutomatonForModelLearning(cost_automaton_file_path_2);
   	         			cost_automaton_3 = Utilities.getAutomatonForModelLearning(cost_automaton_file_path_3);
   	         			cost_automaton_4 = Utilities.getAutomatonForModelLearning(cost_automaton_file_path_4);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
	         		System.out.println("\nCost Automata imported \n");
	         		
	         		cost_automata_vector.add(cost_automaton_1);
	         		cost_automata_vector.add(cost_automaton_2);
	         		cost_automata_vector.add(cost_automaton_3);
	         		cost_automata_vector.add(cost_automaton_4);
             		
             		
             		//
             		//
	         		//
	         		
	         		*/
	         		
	         		int ii = 0;
	         		Iterator<Automaton> it_cost_automata = cost_automata_vector.iterator();
	         		while(it_cost_automata.hasNext()) {
	         			
	
	         			Automaton cost_automaton = it_cost_automata.next();

						if(cost_automaton != null){
						
						ii++;
	         			System.out.println("\nCOST AUTOMATON N. " + ii);
	         		
	         			State initial_state_of_cost_automaton = cost_automaton.getInit();
	         			Iterator<State> it1 = cost_automaton.iterator();
	         		
	         			while (it1.hasNext()) {
	       	              	State ss = it1.next();
	       	              	//System.out.println("State: " + ss.toString());

	       	              	Iterator<Transition> transitions = ss.getOutput().iterator();
	       	              	while (transitions.hasNext()) {
	       	              		Transition t = transitions.next();
	       	              		String label = t.getPositiveLabel();
	       	                  
	       	              		if(!label.startsWith("!") && !label.startsWith("del_") && !label.startsWith("add_")) {
	       	                	
	       	              			if(!Constants.getAlphabetOfTheConstraints_vector().contains(label)) {
	       	              				//
	       	              				// Update the global vector of the alphabet of activities.
	       	              				//
	       	              				Constants.getAlphabetOfTheConstraints_vector().addElement(label);
		       	      				
	       	              				//
	       	              				// Update the GUI to show the complete alphabet of activities of the constraints and of the log.
	       	              				//
	       	              				//ple.getActivitiesArea().append(label + "\n");
	       	              			}
	       	                	
	       	              		}
	       	              	}
	         			}
	         		
	         			//
	         			// Identify the initial state of the cost automaton and records it in the global vector and in the PDDL StringBuffer of the initial states.
	         			//
	         			String initial_state = st_prefix + "_" + automaton_index + "_" + initial_state_of_cost_automaton.getId();
	         	
	         			Constants.getAutomataInitialStates_vector().addElement(initial_state);
	         			Constants.getPDDLAutomataInitialStates_sb().append("(currstate " + initial_state + ")\n");
         			
	         			//System.out.println("Initial state: " + initial_state);
	         		
         			
	         			//
	         			// For any transition of the cost automaton, we check if such transition is relevant 
	         			//
	         			System.out.println("For any transition of the cost automaton, check if such transition is relevant \n ");
	         			Iterator<Transition> it3 = cost_automaton.transitions().iterator();
	         			single_tr_index = 0;
	         			while(it3.hasNext()) {
           			 
	         				Transition transition = (Transition) it3.next();
	         				int tr_source_state_id = transition.getSource().getId();
	         				int tr_target_state_id = transition.getTarget().getId();
	         				String cost = "0";
	         				int cost_number = 0;
	         				String label = transition.toString();
           			 
	         				System.out.println("\nlabel :  " + label + " source:  " + tr_source_state_id + " target:  " + tr_target_state_id + " ");
           			 
	         				boolean isRelevant = false;
         			
	         				if(tr_source_state_id != tr_target_state_id  ) { isRelevant = true; }
         			
	         				if(label.startsWith("add_") || label.startsWith("del_")) {
	         					String[] label_splitted = label.split("_");
         				
	         					label = label_splitted[0]+"_"+label_splitted[1];
	         					cost = label_splitted[2];
	         					//System.out.println( "label splitted:  " + label_splitted[0] + "  " + label_splitted [1] + "  " + label_splitted[2] + "\n");

	         					if(!cost.equals("0")) {
	         						isRelevant = true;
	         						cost_number = Integer.parseInt(cost);
	         					}
         				
	         				}
	         				//System.out.println("label :  " + label + " cost:  " + cost + "\n");
         			
	         				if(isRelevant) {
	         					
	         					//
	         					// For each transition, add negative labels
	         					//
	         					
	         					Transition transition_1 = transition;
    	         				
    	         				String label_1 = transition_1.toString();
         						int source_state_1 = transition_1.getSource().getId();
    	         				int target_state_1 = transition_1.getTarget().getId();
    	         				
    	         				if(label_1.startsWith("!")) {
    	         					String negative_label_1 = label_1.substring(1);
    	         					transition_1.getNegativeLabels_2().add(negative_label_1);
    	         						    	         				
    	         					Iterator<Transition> iterator_2 = cost_automaton.transitions().iterator();
    	         					while(iterator_2.hasNext()) {
    	         						Transition transition_2 = (Transition) iterator_2.next();
	         						
    	         						String label_2 = transition_2.toString();
    	         						int source_state_2 = transition_2.getSource().getId();
    	         						int target_state_2 = transition_2.getTarget().getId();
	    	         				
    	         						if(source_state_1 == source_state_2 && target_state_1 == target_state_2) {
    	         							if(label_2.startsWith("!add_") && label_1.startsWith("!add_")) {	
    	         								String negative_label_2 = label_2.substring(1);
    	         								if(!transition_1.getNegativeLabels_2().contains(negative_label_2)) {
    	         									transition_1.getNegativeLabels_2().add(negative_label_2);
    	         								}
    	         							}
    	         							else if(label_2.startsWith("!del_") && label_1.startsWith("!del_")) {
    	         								String negative_label_2 = label_2.substring(1);
    	         								if(!transition_1.getNegativeLabels_2().contains(negative_label_2)) {
    	         									transition_1.getNegativeLabels_2().add(negative_label_2);
    	         								}
	    	         						
    	         							}
    	         							else if(!label_2.startsWith("!add") && !label_2.startsWith("!del") && !label_1.startsWith("!add") && !label_1.startsWith("!del") && label_2.startsWith("!")) {
    	         								String negative_label_2 = label_2.substring(1);
    	         								if(!transition_1.getNegativeLabels_2().contains(negative_label_2)) {
    	         									transition_1.getNegativeLabels_2().add(negative_label_2);
    	         								}
    	         							}
    	         						}
    	         					}
    	         					
    	         				}
    	         				
    	         				//Collection<String> coll3 = transition_1.getNegativeLabels_2();
    	         				//System.out.println("\ntransitions: "+label_1+" negative labels: " + coll3.toString());
	         							
	         					//
	         					// END negative labels
	         					//
	         					
	         					System.out.println("Is Relevant! Add to the relevant transitions vector: ");
	         					String tr_source_state = st_prefix + "_" + automaton_index + "_" + tr_source_state_id;
	         					String tr_target_state = st_prefix + "_" + automaton_index + "_" + tr_target_state_id;
	         					String tr_id = null;
       				 	
	         					// If is positive
	         					if(!label.startsWith("!")) {
	         						tr_id = tr_prefix + "_" + automaton_index + "_" + single_tr_index;
	         						System.out.println(tr_id +" "+tr_source_state+" "+tr_target_state+" "+label+" cost: "+cost);
	         						RelevantTransition relevant_transition = new RelevantTransition(tr_id, tr_source_state, tr_target_state, label, transition.getPositiveLabel(), cost_number);
       				 		
	         						cost_automaton_relevant_transitions_vector.addElement(relevant_transition);
	         						cost_automaton_transitions_map.put(label, tr_id);
       				 
	         						single_tr_index++;

	         					}
	         					else { //If is negative
	         							         						
	         						Collection<String> coll2 = transition.getNegativeLabels_2();
	         						
	         						if(!label.startsWith("!add_") && !label.startsWith("!del_")) { 
	         							//System.out.println(label + "\n");
	         							for(int ix=0;ix< Constants.getAlphabetOfTheConstraints_vector().size();ix++) {
	         								String symbol = Constants.getAlphabetOfTheConstraints_vector().elementAt(ix);
	         								if(!coll2.contains(symbol)) {
	         									tr_id = tr_prefix + "_" + automaton_index + "_" + single_tr_index;
	         									System.out.println(tr_id +" "+tr_source_state+" "+tr_target_state+" "+symbol+" original trans: "+label+ " cost: "+cost);
	         									RelevantTransition relevant_transition = new RelevantTransition(tr_id, tr_source_state, tr_target_state, symbol, label, 0);
       				 				
	         									cost_automaton_relevant_transitions_vector.addElement(relevant_transition);
	         									cost_automaton_transitions_map.put(symbol, tr_id);
       				 				
	         									single_tr_index++;
	         								}
	         							} 
	         						}
	         						else if(label.startsWith("!add_") || label.startsWith("!del_")) { 
	         							String prefix = label.substring(1, 5);
	         							for(int ix=0;ix< Constants.getAlphabetOfTheConstraints_vector().size();ix++) {
	         								String symbol = Constants.getAlphabetOfTheConstraints_vector().elementAt(ix);
	         								if(!coll2.contains(prefix+symbol)) {
	         									tr_id = tr_prefix + "_" + automaton_index + "_" + single_tr_index;
	         									String tr_label = prefix+symbol;
	         									System.out.println(tr_id +" "+tr_source_state+" "+tr_target_state+" "+tr_label+" original trans: "+label+ " cost: "+cost);
	         									RelevantTransition relevant_transition = new RelevantTransition(tr_id, tr_source_state, tr_target_state, tr_label, label, 0);
       				 					
	         									cost_automaton_relevant_transitions_vector.addElement(relevant_transition);
	         									cost_automaton_transitions_map.put(tr_label, tr_id);
   				 				
	         									single_tr_index++;
       				 					
	         								}
	         							}
	         						}
	         					}
       				 	
	         					//
	         					// Keep track of all the states of the automaton under consideration and records it in the corresponding global StringBuffer/vector.
	         					//
	         					if(!Constants.getAutomataAllStates_vector().contains(tr_source_state))  {
	         						Constants.getAutomataAllStates_vector().addElement(tr_source_state);
	         						Constants.getPDDLAutomataAllStates_sb().append(tr_source_state + " - state\n"); 
	         					} 
	         					if(!Constants.getAutomataAllStates_vector().contains(tr_target_state))  {
	         						Constants.getAutomataAllStates_vector().addElement(tr_target_state);
	         						Constants.getPDDLAutomataAllStates_sb().append(tr_target_state + " - state\n"); 
       				 		
	         					}
       				 	
	         				}
	         				else {
	         					System.out.println("Is not relevant! \n");
	         				}
	         			}
               		
	         			
	         			automata_vector.add(cost_automaton);
	         			automaton_index++;
					}
	         		}
	         		//
         			// Update the global vectors containing the relevant transitions of the cost automaton.
         			//
         			Constants.setCostAutomatonRelevantTransitions_vector(cost_automaton_relevant_transitions_vector);
         			Constants.setCostAutomatonRelevantTransitions_map(cost_automaton_transitions_map);
         			/* */
               		/////// **** END COST AUTOMATA **** //////////////////////////// 
               		
	         		

	         		/////// **** AAAI2017 **** ////////////////////////////  	
	         			
						if(Constants.getPDDL_encoding().equalsIgnoreCase("AAAI17")) {

					        /*
							if(Constants.getMenuPerspective().getProductAutomatonMenuItem().isSelected())  {
								System.out.println("DENTRO prduct_automaton");
								Automaton product_automaton = LTLFormula.generateAutomatonByLTLFormula(ltl_formula_for_product_automaton);
								Iterator<Transition> it2 = product_automaton.transitions().iterator();

	    	            		while(it2.hasNext()) {
	    	            			// Transition t2 = (Transition) it2.next();
	    	            			 
	    	            			
	    	            			 System.out.print(t2.getSource());
	    	            			 System.out.print(" --> ");
	    	            			 System.out.print(t2.getPositiveLabel());
	    	            			 System.out.print(" ### ");
	    	            			 System.out.print(t2.getNegativeLabels());
	    	            			 System.out.print(" --> ");
	    	            			 System.out.print(t2.getTarget());
	    	            			 System.out.print(" ... INITIAL: ");
	    	            			 System.out.print(product_automaton.getInit().getId());
	    	            			 System.out.print(" ... FINALS: ");
	    	            			 if(t2.getSource().isAccepting()) System.out.print(t2.getSource() + " %% ");
	    	            			 if(t2.getTarget().isAccepting()) System.out.print(t2.getTarget());
	    	            			 System.out.println();
	    	            			 
	    	            		}
							}
							*/
							
    	            		//
							// Update the global vectors containing the automata and the relevant transitions.
							//
    	            		Constants.setAutomata_vector(automata_vector);
							Constants.setRelevantTransitions_vector(relevant_transitions_vector);
												
							//
							// Reset the global vector containing the combinations of relevant transitions.
							//
							Constants.setCombinationOfRelevantTransitions_vector(new Vector<CombinationOfRelevantTransitions>());
							
							// Reset the global vector containing the combinations of relevant transitions
							Constants.setRelevantTransitions_map(transitions_map);
							
							/*
							
							//
							// Remove the sink non-accepting states, if the option has been selected by the user
							//
								for(int as=0;as<Constants.getAutomataSinkNonAcceptingStates_vector().size();as++) {
									Constants.getAutomataAllStates_vector().removeElement(Constants.getAutomataSinkNonAcceptingStates_vector().elementAt(as));
									String all_states_string = Constants.getPDDLAutomataAllStates_sb().toString().replaceAll(Constants.getAutomataSinkNonAcceptingStates_vector().elementAt(as) + " - state\n", "");
									StringBuffer sb = new StringBuffer(all_states_string);
									Constants.setPDDLAutomataAllStates_sb(sb);
								}
							*/
													
							/*
							System.out.println(" -- ALL STATES -- " + Constants.getAutomataAllStates_vector());
							System.out.println(" -- ALL STATES IN PDDL -- " + Constants.getPDDLAutomataAllStates_sb());
							
							System.out.println(" -- INITIAL STATES -- " + Constants.getAutomataInitialStates_vector());
							System.out.println(" -- INITIAL STATES IN PDDL -- \n" + Constants.getPDDLAutomataInitialStates_sb());
							
							System.out.println(" -- ACCEPTING STATES -- " + Constants.getAutomataAcceptingStates_vector());
							System.out.println(" -- ACCEPTING STATES IN PDDL -- \n" + Constants.getPDDLAutomataAcceptingStates_sb());
							
							System.out.println(" -- ABSTRACT ACCEPTING STATES -- " + Constants.getAutomataAbstractAcceptingStates_vector());
							System.out.println(" -- SINK NON ACCEPTING STATES -- " + Constants.getAutomataSinkNonAcceptingStates_vector());
							*/


								Constants.setCombinationOfAcceptingStates_vector(new Vector<CombinationOfAcceptingStates>());
							
								Vector<String> automata_id_of_accepting_states_vector = new Vector<String>();
								for(int q=0;q<Constants.getAutomataAcceptingStates_vector().size();q++) {
									String state_id = Constants.getAutomataAcceptingStates_vector().elementAt(q);
									//System.out.println(state_id);
									int first_underscore = state_id.indexOf("_");
									int last_underscore = state_id.lastIndexOf("_");
									String automaton_id = state_id.substring(first_underscore+1, last_underscore);
									//System.out.println(automata_id);
									if(!automata_id_of_accepting_states_vector.contains(automaton_id))
										automata_id_of_accepting_states_vector.addElement(automaton_id);
								}
								int k_value = automata_id_of_accepting_states_vector.size();
								Object[] arr = Constants.getAutomataAcceptingStates_vector().toArray();
								
								Utilities.findCombinationsOfAcceptingStates(arr, k_value, 0, new String[k_value]);
							
							
							////////////////////////////////////////////////////////////////////
							
														
							//
							// For any key, i.e., for any label, identify the relevant transitions associated 
							// to that label.
							//
							
							System.out.println("\n For any key identify the relevant transitions \n");
							
							Iterator<String> it = Constants.getAlphabetOfTheConstraints_vector().iterator();
							while(it.hasNext())  {
								
								String key = (String) it.next();
								
								Collection<String> values = new ArrayList<String>();
								Collection<String> values_sync_move =  new ArrayList<String>();
								
								Collection<String> cost_automaton_values = new ArrayList<String>();
								Collection<String> cost_automaton_values_sync_move = new ArrayList<String>();
								Collection<String> cost_automaton_values_del_move = new ArrayList<String>();
								
								Iterator<String> it_1 = Constants.getRelevantTransitions_map().get(key).iterator();
								while(it_1.hasNext()) {
									String value = new String((String) it_1.next());
									values.add(value);
								}
								
								Iterator<String> it_2 = Constants.getCostAutomatonRelevantTransitions_map().get("add_"+key).iterator();
								while(it_2.hasNext()) {
									String value = new String((String) it_2.next());
									cost_automaton_values.add(value);
								}
								
								Iterator<String> it_3 = Constants.getRelevantTransitions_map().get(key).iterator();
								while(it_3.hasNext()) {
									String value = new String((String) it_3.next());
									values_sync_move.add(value);
								}
								
								Iterator<String> it_4 = Constants.getCostAutomatonRelevantTransitions_map().get(key).iterator();
								while(it_4.hasNext()) {
									String value = new String((String) it_4.next());
									cost_automaton_values_sync_move.add(value);
								}
								
								Iterator<String> it_5 = Constants.getCostAutomatonRelevantTransitions_map().get("del_"+key).iterator();
								while(it_5.hasNext()) {
									String value = new String((String) it_5.next());
									cost_automaton_values_del_move.add(value);
								}
								
								
								System.out.println("key: " + key+"/add_"+key);
								System.out.println("Relevant trans from the constaints auotmata: " + values.toString());
								System.out.println("Relevant trans from the cost auotmaton for add moves : " + cost_automaton_values.toString());
								System.out.println("Relevant trans from the cost auotmaton for sync moves: : " + cost_automaton_values_sync_move.toString());
								System.out.println("Relevant trans from the cost auotmaton for del moves: : " + cost_automaton_values_del_move.toString());

								
								values.addAll(cost_automaton_values);
								values_sync_move.addAll(cost_automaton_values_sync_move);
								
								System.out.println("Total relevant trans for add moves: " + values.toString());
								System.out.println("Total relevant trans for sync moves: " + values_sync_move.toString());

							
								
								Object[] values_array = values.toArray();
								Object[] values_array_sync_move = values_sync_move.toArray();
								Object[] values_array_del_move = cost_automaton_values_del_move.toArray();
								
								System.out.println("\nSize values_array: " + values_array.length);
								System.out.println("Size values_array_sync_move: " + values_array_sync_move.length);
								System.out.println("Size values_array_del_move: " + values_array_del_move.length +"\n");
								
								
								
								// 
								// Given a specific label (e.g., A), which groups several transitions of different automata 
								// (e.g., tr_0_0, tr_1_1, tr_1_2), it is important to discard those combinations that contain 
								// transitions of the same automaton (for example, any combination that includes at the same time 
								// tr_1_1 and tr_1_2 must be discarded).
								//
								// FIRST OF ALL, we identify the underlying automata of the relevant transitions associated to the 
								// specific label. In the above example, two different automata having ID "0" and "1" are considered. 
								// 
								Vector<String> automata_id_of_relevant_transitions_vector = new Vector<String>();
								for(int l=0;l<values_array.length;l++) {
									String transition_id = values_array[l].toString();
									//System.out.println(transition_id);
									int first_underscore = transition_id.indexOf("_");
									int last_underscore = transition_id.lastIndexOf("_");
									String automaton_id = transition_id.substring(first_underscore+1, last_underscore);
									//System.out.println(automaton_id);
									if(!automata_id_of_relevant_transitions_vector.contains(automaton_id))
										automata_id_of_relevant_transitions_vector.addElement(automaton_id);
								}
								
								Vector<String> automata_id_of_relevant_transitions_vector_sync_move = new Vector<String>();
								for(int l=0;l<values_array_sync_move.length;l++) {
									String transition_id = values_array_sync_move[l].toString();
									//System.out.println(transition_id);
									int first_underscore = transition_id.indexOf("_");
									int last_underscore = transition_id.lastIndexOf("_");
									String automaton_id = transition_id.substring(first_underscore+1, last_underscore);
									//System.out.println(automaton_id);
									if(!automata_id_of_relevant_transitions_vector_sync_move.contains(automaton_id))
										automata_id_of_relevant_transitions_vector_sync_move.addElement(automaton_id);
								}
								
								Vector<String> automata_id_of_relevant_transitions_vector_del_move = new Vector<String>();
								for(int l=0;l<values_array_del_move.length;l++) {
									String transition_id = values_array_del_move[l].toString();
									//System.out.println("del move trans: " +transition_id);
									int first_underscore = transition_id.indexOf("_");
									int last_underscore = transition_id.lastIndexOf("_");
									String automaton_id = transition_id.substring(first_underscore+1, last_underscore);
									//System.out.println(automaton_id);
									if(!automata_id_of_relevant_transitions_vector_del_move.contains(automaton_id))
										automata_id_of_relevant_transitions_vector_del_move.addElement(automaton_id);
								}
								System.out.println("Size automaton id vector add moves : " + automata_id_of_relevant_transitions_vector.size()); 
								System.out.println("Size automaton id vector sync moves : " + automata_id_of_relevant_transitions_vector_sync_move.size());
								System.out.println("Size automaton id vector del moves : " + automata_id_of_relevant_transitions_vector_del_move.size()+"\n"); 

								//
								// To identify the number of different automata involved in the relevant transitions helps to set the 
								// maximum "k" value to calculate the combination of relevant transitions (e.g., in our example, we 
								// calculate combinations with k=1 and k=2 at maximum).
								// The method invoked removes automatically any combination that contains two transitions of the same automaton.
								//
								for(int kl=1;kl<=automata_id_of_relevant_transitions_vector.size();kl++) {
									Utilities.findCombinationsOfTransitions(values_array, key, kl, kl, 0, new String[kl]);
								}
								for(int kl=1;kl<=automata_id_of_relevant_transitions_vector_sync_move.size();kl++) {
									Utilities.findCombinationsOfTransitions_sync_move(values_array_sync_move, key, kl, kl, 0, new String[kl]);
								}
								for(int kl=1;kl<=automata_id_of_relevant_transitions_vector_del_move.size();kl++) {
									Utilities.findCombinationsOfTransitions_del_move(values_array_del_move, key, kl, kl, 0, new String[kl]);
								}
								
							}							
							
							System.out.println("\n Combination of Relevant Transition Vector: ");
							for(int i=0;i<Constants.getCombinationOfRelevantTransitions_vector().size();i++) {
								CombinationOfRelevantTransitions cot = Constants.getCombinationOfRelevantTransitions_vector().elementAt(i);
								System.out.println("combinazione n: "+ i + " label: " + cot.getLabel() + "  transitions: " + cot.getCombination_of_transitions_vector().toString()+ " total cost: "+ cot.getCost());
							}
							System.out.println("\n Combination of Relevant Transition Vector of SYNC: ");
							for(int i=0;i<Constants.getCombinationOfRelevantTransitions_vector_sync_move().size();i++) {
								CombinationOfRelevantTransitions cot = Constants.getCombinationOfRelevantTransitions_vector_sync_move().elementAt(i);
								System.out.println("combinazione n: "+ i + " label: " + cot.getLabel() + "  transitions: " + cot.getCombination_of_transitions_vector().toString()+ " total cost: "+ cot.getCost());
							}
							System.out.println("\n Combination of Relevant Transition Vector of DEL: ");
							for(int i=0;i<Constants.getCombinationOfRelevantTransitions_vector_del_move().size();i++) {
								CombinationOfRelevantTransitions cot = Constants.getCombinationOfRelevantTransitions_vector_del_move().elementAt(i);
								System.out.println("combinazione n: "+ i + " label: " + cot.getLabel() + "  transitions: " + cot.getCombination_of_transitions_vector().toString()+ " total cost: "+ cot.getCost());
							}
							
						}
						/////// **** END of AAAI2017 **** ////////////////////////////

							
						
	             		ple.getConstraintsArea().setCaretPosition(0);
            			
	             		//System.out.println("***************** Relevant TASKS for the DECLARE Constraints : " + relevant_activities_for_the_constraints_vector);
	             	  		
	             		//
	             		// Reset the global Hashtable used to record the content of all the different traces of the log (in the String format). 
	             		//
	             		Constants.setContentOfAnyDifferentTrace_Hashtable(new Hashtable<String,String>());
	             		
	             		
	             		for(int j=0;j<Constants.getAllTraces_vector().size();j++) {
	             			
	             			Trace trace = Constants.getAllTraces_vector().elementAt(j);
	             			
	             			//////////////////////////////////////////////////////////////////////////////////7
	             			
	             			//////////////////////////////////////////////////////////////////////////////////
	             			
	             			//ple.getTraceArea().append("****************\n");
	             			//ple.getTraceArea().append(trace.getTraceID() + "\n");
	             			//ple.getTraceArea().append("****************\n");
	             			ple.getTraceArea().append("* " + trace.getTraceID() + "={");
	             			
	             			for(int jind=0;jind<trace.getOriginalTraceContent_vector().size();jind++) {
	             				ple.getTraceArea().append(trace.getOriginalTraceContent_vector().elementAt(jind));
	             				if(trace.getOriginalTraceContent_vector().size() > jind+1)
	             					ple.getTraceArea().append(",");
	             			}
	             			ple.getTraceArea().append("}\n");
	             			
	             			//
	             			// Update the global Hashtable used to record the content of all the different traces of the log (in the String format). 
	             			//
	         				if(!Constants.getContentOfAnyDifferentTrace_Hashtable().containsKey(trace.getOriginalTraceContent_string()))  {
	         					//System.out.println(trace.getTraceName());
	         					Constants.getContentOfAnyDifferentTrace_Hashtable().put(trace.getOriginalTraceContent_string().toString(),trace.getTraceName());
	         				}
	             			
	             			//
	             			// For any analyzed trace, update the variables recording the minimum and maximum length of a log trace.
	             			//
	             			/////////////////////////////////////////
	             			if(j==0)  {
	             				Constants.setMinimumLengthOfATrace(trace.getOriginalTraceContent_vector().size());
	             			}         		
	             			if(Constants.getMinimumLengthOfATrace() > trace.getOriginalTraceContent_vector().size()) {
	             				Constants.setMinimumLengthOfATrace(trace.getOriginalTraceContent_vector().size());
	             			}
	             			if(Constants.getMaximumLengthOfATrace() < trace.getOriginalTraceContent_vector().size()) {
	             				Constants.setMaximumLengthOfATrace(trace.getOriginalTraceContent_vector().size());
	             			}
	             			/////////////////////////////////////////
	             			
	             			trace.setTraceMissingActivities_vector(new Vector<String>());
	             			trace.setTraceAlphabetWithMissingActivitiesOfTheConstraints_vector(trace.getTraceAlphabet_vector());
	             			
	             			//Update the missing activities for the specific trace
	             			for(int kj=0;kj<Constants.getActivitiesRepository_vector().size();kj++)  {
	             				String activity = Constants.getActivitiesRepository_vector().elementAt(kj);
	             				trace.getTraceMissingActivities_vector().addElement(activity);
	             			}
	             			             			
	             			// A -- Remove from the vector of the missing activities of the trace all the activities that already appear in the trace
	    					for(int f=0;f<trace.getOriginalTraceContent_vector().size();f++) {
	    						String string = trace.getOriginalTraceContent_vector().elementAt(f);    						
	                 			trace.getTraceMissingActivities_vector().removeElement(string);
	                 			 
	                 			/////
	                 			if(!trace.getTraceAlphabetWithMissingActivitiesOfTheConstraints_vector().contains(string)) {
	                 				trace.getTraceAlphabetWithMissingActivitiesOfTheConstraints_vector().addElement(string);
	                 			}
	                 			//////
	    					}
	                 		    					
	             			// B -- Remove from the vector of the missing activities of the trace all the activities that do not appear in any of the DECLARE constraints
	    					Vector<String> final_missing_activities_vector = new Vector<String>(trace.getTraceMissingActivities_vector());
	    					
	                 		for(int hj=0;hj<trace.getTraceMissingActivities_vector().size();hj++) {
	                 				String missing_activity = trace.getTraceMissingActivities_vector().elementAt(hj);
	                 				if(!Constants.getAlphabetOfTheConstraints_vector().contains(missing_activity)) {
	                 					final_missing_activities_vector.removeElement(missing_activity);
	                 				}	
	                 			}

	             			// C -- Create possible instances for the missing activities
	                 		trace.setTraceMissingActivities_vector(final_missing_activities_vector);

	                 		/*
	    					System.out.println("************************");
	    					System.out.println("TRACE name : " + trace.getTraceName());
	    					System.out.println("TRACE content : " + trace.get_Original_Trace_content_vector());
	    					System.out.println("TRACE content for PDDL : " + trace.get_PDDL_Trace_content_vector());
	    					System.out.println("TRACE hashtable with number of instances in the trace: " + trace.get_Number_of_Task_Instances_Hashtable());
	    					System.out.println("TRACE missing activities : " + trace.get_missing_activities_vector());
	    					System.out.println("TRACE alphabet : " + trace.getTrace_alphabet());
	    					System.out.println("TRACE hashtable : " + trace.get_Trace_Hashtable());
	    					*/
	    					////////////////////////////////
	             		}
						
    					//System.out.println("alphabet of the CONSTRAINTS: " + Constants.getAlphabet_of_the_constraints());
	             		
	             		ple.getTraceArea().setCaretPosition(0);
	        			
	             		//
             			// Update the PlannerPerspective panel to visualize the correct minimum and maximum length of a log trace.
             			//
	            		int kix = 1;
	            		for(int lngtr=Constants.getMinimumLengthOfATrace();lngtr<=Constants.getMaximumLengthOfATrace();lngtr++) {	
	            			ple.getLenght_of_traces_ComboBox_FROM().insertItemAt("" + lngtr, kix);
	            			ple.getLenght_of_traces_ComboBox_TO().insertItemAt("" + lngtr, kix);
	            			kix++;
	            		}
	            		ple.getLenght_of_traces_ComboBox_FROM().setSelectedIndex(0);
	            		ple.getLenght_of_traces_ComboBox_TO().setSelectedIndex(0);
	            		///////////////////////////////////
	            		
	             		_view.getConstraintComboBox().setSelectedIndex(0);

						if(!Constants.isBack_from_cost()){
	             		_view.setComponentEnabled(false);        		
	             		
	             	 	ple.setModal(true);
	             		ple.setVisible(true);
						}
	             		
	        		}	        		
	        		else {
	        			JOptionPane.showMessageDialog(null, "The list of Declare constraints can not be empty!", "ATTENTION!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/info_icon.png"));
	        		}
            }
        });		
	}

}
