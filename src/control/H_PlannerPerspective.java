package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


import main.Constants;
import main.Trace;
import main.Utilities;
import view.PlannerPerspective;
import view.ResultsPerspective;

public class H_PlannerPerspective {
	
	public PlannerPerspective _view = null;
	
	public H_PlannerPerspective (PlannerPerspective i_view) {
		_view = i_view;
		installListeners();
	}

	private void installListeners() {

		_view.addWindowListener(new WindowListener() {
            
            public void windowOpened(WindowEvent e) {}
            public void windowClosed(WindowEvent e) {}
            public void windowActivated(WindowEvent e) {}
            public void windowDeactivated(WindowEvent e) {}
            public void windowIconified(WindowEvent e) {}
            public void windowDeiconified(WindowEvent e) {}
            
            public void windowClosing(WindowEvent e) {
            	_view.dispose();
                Constants.getConstraintsPerspective().setComponentEnabled(true);
            }
            
        });
		
		_view.getPreviousStepButton().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
            	_view.dispose();
                 Constants.getConstraintsPerspective().setComponentEnabled(true);
            }
        });

		/*
		_view.getCostCheckBox().addActionListener(new ActionListener()
		{
		    public void actionPerformed(ActionEvent ae)
		    {
		    	if(_view.getCostCheckBox().isSelected()) {
		    		_view.getAddingCostField().setEnabled(true);
		    		_view.getRemovalCostField().setEnabled(true);
		    		_view.getActivitiesComboBox().setEnabled(true);
		    	}
		    	else {
		    		_view.getAddingCostField().setEnabled(false);
		    		_view.getRemovalCostField().setEnabled(false);
		    		_view.getActivitiesComboBox().setEnabled(false);
		    	}
		    }
		});
		*/
		
		_view.getNumber_of_Traces_checkBox().addActionListener(new ActionListener()
		{
		    public void actionPerformed(ActionEvent ae)
		    {
		    	if(_view.getNumber_of_Traces_checkBox().isSelected()) {
		    		_view.getNumber_of_traces_ComboBox_FROM().setEnabled(true);
		    		_view.getNumber_of_traces_ComboBox_TO().setEnabled(true);
		    	}
		    	else {
		    		_view.getNumber_of_traces_ComboBox_FROM().setEnabled(false);
		    		_view.getNumber_of_traces_ComboBox_TO().setEnabled(false);
		    	}
		    }
		});
		
		_view.getLenght_of_traces_checkBox().addActionListener(new ActionListener()
		{
		    public void actionPerformed(ActionEvent ae)
		    {
		    	if(_view.getLenght_of_traces_checkBox().isSelected()) {
		    		_view.getLenght_of_traces_ComboBox_FROM().setEnabled(true);
		    		_view.getLenght_of_traces_ComboBox_TO().setEnabled(true);
		    	}
		    	else {
		    		_view.getLenght_of_traces_ComboBox_FROM().setEnabled(false);
		    		_view.getLenght_of_traces_ComboBox_TO().setEnabled(false);
		    	}
		    }
		});
		
		/////// **** AAAI2017 **** ////////////////////////////
		
		_view.getGeneratePDDLButton().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
            	
            	            	
            	if(!_view.getFDOptimalCheckBox().isSelected() && !_view.getSymBAoptimalCheckBox().isSelected())
            		JOptionPane.showMessageDialog(null, "It is required to choose at least a valid \nsearch heuristic to run the planner!", "ATTENTION!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/info_icon.png"));
            	else if(_view.getNumber_of_Traces_checkBox().isSelected() && (_view.getNumber_of_traces_ComboBox_FROM().getSelectedIndex()==0 || _view.getNumber_of_traces_ComboBox_TO().getSelectedIndex()==0 || _view.getNumber_of_traces_ComboBox_FROM().getSelectedIndex() > _view.getNumber_of_traces_ComboBox_TO().getSelectedIndex())) {
            			JOptionPane.showMessageDialog(null, "Please select a valid interval of traces to analyze!", "ATTENTION!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/info_icon.png"));   
            	}
            	else {
            	
	                //
	            	// Decide to discard (or not) duplicate traces, in order to avoid unnecessary alignments
	            	//        	
	                if(_view.getTrace_duplicated_checkBox().isSelected()) {
	    	    		Constants.setDiscard_duplicated_traces(true);
	    	    	}
	    	    	else {
	    	    		Constants.setDiscard_duplicated_traces(false);
	    	    	}
	                
	    	    	//
	            	// Remove the existing old files from the folder containing the generated planning domains and problems
	            	//
	            	if(_view.getFDOptimalCheckBox().isSelected()) {
	            		 Utilities.emptyFolder("fast-downward/src/Conformance_Checking");
	            	}
	            	
	            	if(_view.getSymBAoptimalCheckBox().isSelected()) {
	            		Utilities.emptyFolder("seq-opt-symba-2/Conformance_Checking");
	            		Utilities.emptyFolder("seq-opt-symba-2/results");
	            	}
	    			//////////////////////////////////////////////////////////////////////////

	            	
	            	//
	            	// Case in which we assign a cost to add/remove activities to/from the trace
	            	//   
	            	/*
	            	if(Constants.getPlannerPerspective().getCostCheckBox().isSelected()) {
	            		
	            		//UPDATE the ADDING/REMOVAL cost associated to the last activity updated
	            		String selected_activity_name_for_cost = (String) _view.getActivitiesComboBox().getSelectedItem();
							
	    				if (!(selected_activity_name_for_cost.equalsIgnoreCase("-- Name of the Activity --")) )
	    				 {
	    					for(int ind=0;ind<Constants.getActivitiesCost_vector().size();ind++) {							
	    						Vector<String> v = Constants.getActivitiesCost_vector().elementAt(ind);
	    						if(v.elementAt(0).equalsIgnoreCase(selected_activity_name_for_cost)) {
	    							v.set(1,_view.getAddingCostField().getText());
	    							v.set(2,_view.getRemovalCostField().getText());
	    							break;
	    						}
	    					}
		    			 }	
	    				//System.out.println(Constants.get_activities_cost_vector());
	        		 }
	        		 */
	    			//////////////////////////////////////////////////////////////////////////
	            	
	            	//
	            	// Interval of traces to be checked
	            	//  
					
					int number_of_traces_to_check_from = 0;
					int number_of_traces_to_check_to = 0;
					
	            	if(Constants.getPlannerPerspective().getNumber_of_Traces_checkBox().isSelected()) {
	            		number_of_traces_to_check_from = Constants.getPlannerPerspective().getNumber_of_traces_ComboBox_FROM().getSelectedIndex();
	            		number_of_traces_to_check_to = Constants.getPlannerPerspective().getNumber_of_traces_ComboBox_TO().getSelectedIndex();
	            	}			
	            	else {
	            		number_of_traces_to_check_from = 1;
	            		number_of_traces_to_check_to = Constants.getAllTraces_vector().size();
	            	}
	            	
	            	//////////////////////////////////////////////////////////////////////////
	    			
					int length_of_traces_to_check_from = 0;
					int length_of_traces_to_check_to = 0;
					
	            	if(Constants.getPlannerPerspective().getLenght_of_traces_checkBox().isSelected()) {
	            		length_of_traces_to_check_from = new Integer(Constants.getPlannerPerspective().getLenght_of_traces_ComboBox_FROM().getSelectedItem().toString());
	            		length_of_traces_to_check_to = new Integer(Constants.getPlannerPerspective().getLenght_of_traces_ComboBox_TO().getSelectedItem().toString());
	            	}			
	            	else {
	            		length_of_traces_to_check_from = Constants.getMinimumLengthOfATrace();
	            		length_of_traces_to_check_to = Constants.getMaximumLengthOfATrace();
	            	}
	            	
	            	//////////////////////////////////////////////////////////////////////////
	            	//System.out.println(length_of_traces_to_check_from);
	            	//System.out.println(length_of_traces_to_check_to);
	            	//System.out.println(number_of_traces_to_check_from);
	            	//System.out.println(number_of_traces_to_check_to);
	            	//////////////////////////////////////////////////////////////////////////
	            	            	
	            	for(int k=number_of_traces_to_check_from-1;k<number_of_traces_to_check_to;k++) {
	            		
	            		Trace trace = Constants.getAllTraces_vector().elementAt(k);
	        			
	            		
	            		
	            		/*
	            		System.out.println(trace.getOriginalTraceContent_vector().size());
	            		*/
	                	if(_view.getTrace_duplicated_checkBox().isSelected()) { // Remove duplicated traces
	            			
	                		  if(Constants.getContentOfAnyDifferentTrace_Hashtable().containsValue(trace.getTraceName()))  {
	            				            	
	                  			if(trace.getOriginalTraceContent_vector().size() >= length_of_traces_to_check_from && trace.getOriginalTraceContent_vector().size() <= length_of_traces_to_check_to)  {
	                    			
				 	            	StringBuffer sb_domain = Utilities.createPropositionalDomain(trace);
				  	            	StringBuffer sb_problem = Utilities.createPropositionalProblem(trace);
	      	            	
				      	            int trace_real_number = k + 1;
				      	            	
				      	            if(_view.getFDOptimalCheckBox().isSelected()) {
					      	  	        Utilities.createFile("fast-downward/src/Conformance_Checking/domain" + trace_real_number + ".pddl", sb_domain);
					      	  	        Utilities.createFile("fast-downward/src/Conformance_Checking/problem" + trace_real_number + ".pddl", sb_problem);
				            	    }   
				      	            
				      	           if(_view.getSymBAoptimalCheckBox().isSelected()) {
					      	  	        Utilities.createFile("seq-opt-symba-2/Conformance_Checking/domain" + trace_real_number + ".pddl", sb_domain);
					      	  	        Utilities.createFile("seq-opt-symba-2/Conformance_Checking/problem" + trace_real_number + ".pddl", sb_problem);
				            	   }   
	                  			}
	                		  }
	                	 }
	                	else { // Mantain duplicated traces
	                		
	                		if(trace.getOriginalTraceContent_vector().size() >= length_of_traces_to_check_from && trace.getOriginalTraceContent_vector().size() <= length_of_traces_to_check_to)  {
		                		
	                			//System.out.println("TRACE Name: ");
	    	            		//System.out.println(trace.getTraceName() + " ");
	    	            		
	                			StringBuffer sb_domain = Utilities.createPropositionalDomain(trace);
			  	            	StringBuffer sb_problem = Utilities.createPropositionalProblem(trace);
			            	
			      	            int trace_real_number = k + 1;
			      	            	
			      	            if(_view.getFDOptimalCheckBox().isSelected()) {
				      	  	        Utilities.createFile("fast-downward/src/Conformance_Checking/domain" + trace_real_number + ".pddl", sb_domain);
				      	  	        Utilities.createFile("fast-downward/src/Conformance_Checking/problem" + trace_real_number + ".pddl", sb_problem);
			            	    }   
			      	            
			      	           if(_view.getSymBAoptimalCheckBox().isSelected()) {
				      	  	        Utilities.createFile("seq-opt-symba-2/Conformance_Checking/domain" + trace_real_number + ".pddl", sb_domain);
				      	  	        Utilities.createFile("seq-opt-symba-2/Conformance_Checking/problem" + trace_real_number + ".pddl", sb_problem);
			            	   }   
	                		} 
	                	}
	            	}
            	}
            }
        });
		/////// -- END of AAAI2017 -- ////////////////////////////	
		_view.getRunPlannerButton().addActionListener(new ActionListener()
        {
			
            public void actionPerformed(ActionEvent ae)
            {
            	//new ResultsPerspective();
            	System.out.println("sono dentro");
            	
            	int number_of_traces_to_check_from = 0;
            	int number_of_traces_to_check_to = 0;
            	
            	if(Constants.getPlannerPerspective().getNumber_of_Traces_checkBox().isSelected()) {
            		number_of_traces_to_check_from = Constants.getPlannerPerspective().getNumber_of_traces_ComboBox_FROM().getSelectedIndex();
            		number_of_traces_to_check_to = Constants.getPlannerPerspective().getNumber_of_traces_ComboBox_TO().getSelectedIndex();
            	}			
            	else {
            		number_of_traces_to_check_from = 1;
            		number_of_traces_to_check_to = Constants.getAllTraces_vector().size();
            	}
            	
            	double log_fitness_numerator = 0;
         	    double log_fitness_denominator = 0;
         	    
	         	  File folder = new File("seq-opt-symba-2/results/");
	         	  
	         	 double total_number_of_alignments = 0;
	         	 int total_number_of_traces_analyzed = 0;
	         	 double total_number_of_log_events = 0;
	         	  
	         	  File[] directoryListing = folder.listFiles();
	         	  if (directoryListing != null) {
	         	    for (File child : directoryListing) {
	         	    	//System.out.println(child.getName());

	         	    	total_number_of_traces_analyzed++;
	         	    	
	         	    	String[] split_file_name = child.getName().split("_");
	         	    	String[] split_file_name_1 = split_file_name[1].split(".txt");
	         	    			
	         	    	Trace trace = Utilities.getTraceById("Trace#"+split_file_name_1[0]);
	         	    	
	         	    	Vector<String> plan_vector = new Vector<String>();
            	    	//int trace_number = k + 1;
            		 
            		 BufferedReader reader = null;
             	     try {
             	    	 reader = new BufferedReader(new FileReader(child.getAbsolutePath())); 	       
             	    	 String line = reader.readLine();
             	    	 while(line!=null) {
             	    		 if(line!=null && line.contains("(") && line.contains(")")){
             	    			 plan_vector.addElement(line);
          			     }
          			     line = reader.readLine();
          			 }
             	    	}
                     catch(Exception e)
             	    {e.printStackTrace();
             	    }
            		
             	    StringBuffer logBuffer = new StringBuffer();
             	    logBuffer.append("\n");
             	    logBuffer.append(">>>> TRACE ID: " + trace.getTraceID() + " " + trace.getTraceName() + " " + trace.getTraceNumber() + "\n");
             	    logBuffer.append(">>>> TRACE CONTENT: " + trace.getOriginalTraceContent_vector()+"\n");
            	    logBuffer.append(">>>> ORIGINAL TRACE LENGTH: " + trace.getOriginalTraceContent_vector().size()+"\n");
            	    total_number_of_log_events += trace.getOriginalTraceContent_vector().size();
            	    
            	    float cost_of_alignments = 0;
            	    float number_of_alignments = 0;
            	    float length_of_trace_aligned = 0;
            	    
            	   
            	    
            	    for (int index=0;index<plan_vector.size();index++) {
            	    	String planning_action = (String) plan_vector.elementAt(index);
            			
            			String[] split = planning_action.split("\\(");
            			String[] split1 = split[1].split("\\)");
            			String[] complete_action = split1[0].split(" ");
            			String action = complete_action[0];					
            			
			           if(action.substring(0,3).equalsIgnoreCase("del")) {
            				number_of_alignments++;
            				cost_of_alignments++;
            			}
            			else if(action.substring(0,3).equalsIgnoreCase("add")) {
            				number_of_alignments++;
            				length_of_trace_aligned++; 
            				cost_of_alignments++;
            			}
            			else if(action.substring(0,4).equalsIgnoreCase("sync")) {
            				length_of_trace_aligned++; 
            			}
            				            			
            	    }
            	    logBuffer.append(">>>> ALIGNED TRACE LENGTH: " + length_of_trace_aligned+"\n");
            	    logBuffer.append(">>>> COST OF ALIGNMENT: " + cost_of_alignments+"\n");
            	    logBuffer.append(">>>> NUMBER OF ALIGNMENTS: " + number_of_alignments+"\n");
            	               	    
            	    double fitness = 1 - (cost_of_alignments/(length_of_trace_aligned + trace.getOriginalTraceContent_vector().size()));
            	    logBuffer.append(">>>> FITNESS: " + fitness+"\n");
            	    
            	    log_fitness_numerator += cost_of_alignments;
            	    log_fitness_denominator += length_of_trace_aligned + trace.getOriginalTraceContent_vector().size();
            	    
            	    total_number_of_alignments += number_of_alignments;

            	    try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("seq-opt-symba-2/results/out_" + split_file_name_1[0] + ".txt", true)))) {
        	    	    out.println(logBuffer);
            	    }catch (IOException e) {System.out.println("Il file non esiste");}
            	
            	
            	  }
	         	  }
            	double log_fitness = 1 - (log_fitness_numerator/log_fitness_denominator);
            	
            	
            	System.out.println("LOG FITNESS : " + log_fitness);
            	
            	System.out.println("TRACES ANALYZED : " + total_number_of_traces_analyzed);
            	System.out.println("TOTAL NUMBER OF ALIGNMENTS : " + total_number_of_alignments);
            	System.out.println("AVERAGE NUMBER OF ALIGNMENTS : " + (total_number_of_alignments/total_number_of_traces_analyzed));
            	System.out.println("AVERAGE NUMBER OF LOG EVENTS : " + (total_number_of_log_events/total_number_of_traces_analyzed));
            	
            	
            	try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("seq-opt-symba-2/results/OUTCOMES.txt", true)))) {
    	    	    out.println("TRACES ANALYZED : " + total_number_of_traces_analyzed);
    	    	    out.println("AVERAGE NUMBER OF LOG EVENTS PER TRACE: " + (total_number_of_log_events/total_number_of_traces_analyzed));
    	    	    out.println("TOTAL NUMBER OF ALIGNMENTS : " + total_number_of_alignments);
                	out.println("AVERAGE NUMBER OF ALIGNMENTS PER TRACE: " + (total_number_of_alignments/total_number_of_traces_analyzed));
        	    }catch (IOException e) {System.out.println("Il file non esiste");}
            	
            	
            }
        });
		/*
		_view.getRunPlannerButton().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
            	if(!_view.getFDOptimalCheckBox().isSelected())
            		JOptionPane.showMessageDialog(null, "It is required to choose at least a valid \nsearch heuristic to run the planner!", "ATTENTION!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/info_icon.png"));
            	else if(_view.getNumber_of_Traces_checkBox().isSelected() && (_view.getNumber_of_traces_ComboBox_FROM().getSelectedIndex()==0 || _view.getNumber_of_traces_ComboBox_TO().getSelectedIndex()==0 || _view.getNumber_of_traces_ComboBox_FROM().getSelectedIndex() > _view.getNumber_of_traces_ComboBox_TO().getSelectedIndex())) {
            			JOptionPane.showMessageDialog(null, "Please select a valid interval of traces to analyze!", "ATTENTION!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/info_icon.png"));   
            	}
            	else {
            		
            		if(Constants.getPlannerPerspective().getCostCheckBox().isSelected()) {
	            		//UPDATE the ADDING/REMOVAL cost associated to the last activity updated
	            		String selected_activity_name_for_cost = (String) _view.getActivitiesComboBox().getSelectedItem();
						
	    				if (!(selected_activity_name_for_cost.equalsIgnoreCase("-- Name of the Activity --")) )
	    				 {
	    					for(int index=0;index<Constants.getActivitiesCost_vector().size();index++) {							
	    						Vector<String> v = Constants.getActivitiesCost_vector().elementAt(index);
	    						if(v.elementAt(0).equalsIgnoreCase(selected_activity_name_for_cost)) {
	    							v.set(1,_view.getAddingCostField().getText());
	    							v.set(2,_view.getRemovalCostField().getText());
	    							break;
	    						}
	    					}
	    				 }				
            		 }
            		
					new ResultsPerspective();
            }
            }
        });
		*/
		/*
		_view.getActivitiesComboBox().addItemListener(new ItemListener()
		{
			 public void itemStateChanged(ItemEvent event)
			{
				if(event.getStateChange() == ItemEvent.DESELECTED) 
				   {
				    
					String previous_selected_activity = (String) event.getItem();
					
					if(!previous_selected_activity.equalsIgnoreCase("-- Name of the Activity --")) {

						for(int ix=0;ix<Constants.getActivitiesCost_vector().size();ix++) {							
							Vector<String> v = Constants.getActivitiesCost_vector().elementAt(ix);
							if(v.elementAt(0).equalsIgnoreCase(previous_selected_activity)) {		
								v.set(1, _view.getAddingCostField().getText());
								v.set(2, _view.getRemovalCostField().getText());
								Constants.getActivitiesCost_vector().set(ix,v);
								break;
							}
						}
					}					
				   }
				
				String selected_activity_name = (String) _view.getActivitiesComboBox().getSelectedItem();
							
				if (event.getStateChange() == ItemEvent.SELECTED && !(selected_activity_name.equalsIgnoreCase("-- Name of the Activity --")) )
				 {
					for(int index=0;index<Constants.getActivitiesCost_vector().size();index++) {							
						Vector<String> v = Constants.getActivitiesCost_vector().elementAt(index);
						if(v.elementAt(0).equalsIgnoreCase(selected_activity_name)) {
							_view.getAddingCostField().setText(v.elementAt(1));
							_view.getRemovalCostField().setText(v.elementAt(2));		
							break;
						}
					}
				 }				
				else if(event.getStateChange() == ItemEvent.SELECTED && (selected_activity_name.equalsIgnoreCase("-- Name of the Activity --"))) {
					_view.getAddingCostField().setText("Adding Cost");
					_view.getRemovalCostField().setText("Removal Cost");
				}

			}
		});
		*/
	}
	
}
