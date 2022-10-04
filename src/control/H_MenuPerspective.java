package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.deckfour.xes.extension.std.XConceptExtension;
import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;

import org.processmining.ltl2automaton.plugins.automaton.Automaton;
import org.processmining.ltl2automaton.plugins.automaton.State;
import org.processmining.ltl2automaton.plugins.automaton.Transition;
import org.processmining.ltl2automaton.plugins.ltl.SyntaxParserException;
import java.util.Iterator;

import org.processmining.plugins.declare.visualizing.AssignmentModel;
import org.processmining.plugins.declare.visualizing.AssignmentViewBroker;
import org.processmining.plugins.declare.visualizing.ConstraintDefinition;
import org.processmining.plugins.declare.visualizing.Parameter;
import org.processmining.plugins.declare.visualizing.XMLBrokerFactory;

import main.Constants;
import main.Trace;
import main.Utilities;
import main.XLogReader;

import view.MenuPerspective;

public class H_MenuPerspective {
	
	public MenuPerspective _view = null;
	
	public H_MenuPerspective (MenuPerspective i_view){
		_view = i_view;
		installListeners();
	}

	private void installListeners() {
		
		_view.getExitMenuItem().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Constants.getDesktop().dispose();
				
			}
		});
		
		_view.getNewMenuItem().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String[] options = new String[] {"Yes", "No"};
        	    int response = JOptionPane.showOptionDialog(null, "Lose changes?", "Create new log traces and LTL/Declare constraints",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null, options, options[0]);

        	    if(response==0) {
				
        	    	// RESET the AlphabetPerspective panel
					Constants.getAlphabetPerspective().resetComponent();
					Constants.setActivitiesRepository_vector(new Vector<String>());
					
					// RESET the TracesPerspective panel
					Constants.getTracesPerspective().resetComponent();
					Constants.setAllTraces_vector(new Vector<Trace>());
					
					// RESET the ConstraintsPerspective panel
					Constants.getConstraintsPerspective().resetComponent();					

					Constants.getAlphabetPerspective().setComponentEnabled(true);
					Constants.getTracesPerspective().setComponentEnabled(false);
					Constants.getConstraintsPerspective().setComponentEnabled(false);
					
					Constants.getMenuPerspective().getImportMenu().setEnabled(false);
					Constants.getMenuPerspective().getImportDeclareMenuItem().setEnabled(false);
                 	Constants.getMenuPerspective().getImportDOTAutomatonMenuItem().setEnabled(false);

        	    }
			}
		});
		
		_view.getOpenMenuItem().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
		
				 JFileChooser fileChooser = new JFileChooser();
				 
				 FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter("Log file (*.xes)", "xes");

				 	fileChooser.setDialogTitle("Open log file");
				 	fileChooser.setAcceptAllFileFilterUsed(false);
				 	fileChooser.setFileFilter(xmlfilter);
				 
				 	String workingDirectoryName = System.getProperty("user.dir");
				 	File workingDirectory = new File(workingDirectoryName + File.separator + "resources" + File.separator + "log files");
				 	fileChooser.setCurrentDirectory(workingDirectory);
				 	
				 
			        int returnValue = fileChooser.showOpenDialog(Constants.getDesktop());
			        if (returnValue == JFileChooser.APPROVE_OPTION) {
			          File selectedFile = fileChooser.getSelectedFile();
			         // System.out.println(selectedFile.getName());
			        
			  		try {
			  			
			  			////////////////////////////////////////////////////////////////
			  			// RESET the AlphabetPerspective panel
			  			Constants.getAlphabetPerspective().resetComponent();
						Constants.setActivitiesRepository_vector(new Vector<String>());						
						
						// RESET the TracesPerspective panel
						Constants.getTracesPerspective().resetComponent();
						Constants.setAllTraces_vector(new Vector<Trace>());
						
						// RESET the ConstraintsPerspective panel
						Constants.getConstraintsPerspective().resetComponent();
						
						Constants.getAlphabetPerspective().setComponentEnabled(false);
						Constants.getTracesPerspective().setComponentEnabled(true);
						Constants.getConstraintsPerspective().setComponentEnabled(false);
			  			////////////////////////////////////////////////////////////////
			  									
			  			XLog log = XLogReader.openLog(selectedFile.getAbsolutePath());
									  			
			  			int trace_int_id = 0;
			  			
			  			// Vector used to record the complete alphabet of activities used in the log traces.
			  			Vector<String> loaded_alphabet_vector = new Vector<String>();
			  			
			  			// Vector used to record the activities (in their original order) of a specific trace of the log.
			  			Vector<String> loaded_trace_activities_vector = new Vector<String>();
			  			
						for(XTrace trace:log){
							
							trace_int_id++;
							
							String traceName = XConceptExtension.instance().extractName(trace);
							//System.out.println("Trace Name : " + traceName);
							traceName = "trace" + trace_int_id;
														
							Trace t = new Trace("Trace#" + trace_int_id,traceName);
							
							//UPDATE the JComboBox of the GUI with the loaded traces 
							Constants.getTracesPerspective().getTracesComboBox().addItem(t.getTraceID());
							//////////////////////////////////////////////
							
							//XAttributeMap caseAttributes = trace.getAttributes();
							loaded_trace_activities_vector = new Vector<String>();
							
							for(XEvent event : trace){
								String activityName = XConceptExtension.instance().extractName(event).toLowerCase();
							
								if(activityName.contains(" "))
									activityName = activityName.replaceAll(" ", "");
								
								if(activityName.contains("/"))
									activityName = activityName.replaceAll("\\/", "");
								
								if(activityName.contains("("))
									activityName = activityName.replaceAll("\\(", "");
								
								if(activityName.contains(")"))
									activityName = activityName.replaceAll("\\)", "");
								
								if(activityName.contains("<"))
									activityName = activityName.replaceAll("\\<", "");
								
								if(activityName.contains(">"))
									activityName = activityName.replaceAll("\\>", "");
								
								if(activityName.contains("."))
									activityName = activityName.replaceAll("\\.", "");
								
								if(activityName.contains(","))
									activityName = activityName.replaceAll("\\,", "_");
								
								if(activityName.contains("+"))
									activityName = activityName.replaceAll("\\+", "_");
								
								if(activityName.contains("-"))
									activityName = activityName.replaceAll("\\-", "_");
								
								//System.out.println("Activity Name : " + activityName);
								
								//Add to the activity name the type of event that it covers in the life cycle
								//String eventType = XLifecycleExtension.instance().extractTransition(event).toLowerCase();
								//String eventType = "";
								
								//if(eventType == "")
									loaded_trace_activities_vector.addElement(activityName);
								//else
									//loaded_trace_activities_vector.addElement(activityName + "_" + eventType);
								
								if(!loaded_alphabet_vector.contains(activityName))
									//loaded_alphabet_vector.addElement(activityName + "_" + eventType);
									loaded_alphabet_vector.addElement(activityName);
								
								//loaded_trace_activities_vector.addElement(activityName);
								
								//if(!loaded_alphabet_vector.contains(activityName))
									//loaded_alphabet_vector.addElement(activityName);
								
								/*
								Date timestamp = XTimeExtension.instance().extractTimestamp(event);
								System.out.println("TimeStamp : " + timestamp);
								
								String eventType = XLifecycleExtension.instance().extractTransition(event);
								XAttributeMap eventAttributes = event.getAttributes();
								for(String key :eventAttributes.keySet()){
									String value = eventAttributes.get(key).toString();
									System.out.println("Value : " + value);
								}
								for(String key :caseAttributes.keySet()){
									String value = caseAttributes.get(key).toString();
									System.out.println("Value : " + value);
								}
								*/
								
							}
							
							//
							// Update the single trace of the log
							//
							String trace_content = new String();
							
							for(int j=0;j<loaded_trace_activities_vector.size();j++) {
					  			String string = (String) loaded_trace_activities_vector.elementAt(j);
					  			
					  			if(!t.getTraceAlphabet_vector().contains(string)) {
									t.getTraceAlphabet_vector().addElement(string);
								}
					  			
				  				// Hashtable used to set the number of activity instances of a trace
								if(t.getNumberOfActivityInstances_Hashtable().containsKey(string)) {
				  					int number_of_instances = t.getNumberOfActivityInstances_Hashtable().get(string);
				  					number_of_instances = number_of_instances + 1;
				  					t.getNumberOfActivityInstances_Hashtable().put(string, number_of_instances);
				  				}
				  				else {
				  					t.getNumberOfActivityInstances_Hashtable().put(string, 1);
				  				}
					  										
					  			for(int p=0;p<loaded_trace_activities_vector.size();p++) {
					  			
					  				String string_key = string + p;
					  				
					  				if(!t.getAssociationsToActivityInstances_Hashtable().containsKey(string_key)) {
					  					t.getAssociationsToActivityInstances_Hashtable().put(string_key,string);
					  					t.getTraceContentWithActivitiesInstances_vector().addElement(string_key);
					  					t.getOriginalTraceContent_vector().addElement(string);
					  					trace_content += string + ",";
					  					break;
					  				}
					  			}					  			
					   		 }
							t.setOriginalTraceContentString(trace_content.substring(0, trace_content.length()-1));
							
							Constants.getAllTraces_vector().addElement(t);
							/////////////////////////////////////////////////////////////
						}
						
						//Update the GUI component with the loaded LOG
						Constants.setActivitiesRepository_vector(loaded_alphabet_vector);
						for(int kix=0;kix<loaded_alphabet_vector.size();kix++){
							Constants.getAlphabetPerspective().getAlphabetListModel().addElement(loaded_alphabet_vector.elementAt(kix));
							Constants.getTracesPerspective().getAlphabetListModel().addElement(loaded_alphabet_vector.elementAt(kix));
						}
						
						Constants.getTracesPerspective().getTracesComboBox().setSelectedIndex(1);
						
						Constants.getMenuPerspective().getImportMenu().setEnabled(false);
		            	Constants.getMenuPerspective().getImportDeclareMenuItem().setEnabled(false);
	                 	Constants.getMenuPerspective().getImportDOTAutomatonMenuItem().setEnabled(false);         		             		
						} 
			  		catch (Exception exception) {
						exception.printStackTrace();
			        }
			        }	
			}
		});
		
		
		_view.getImportDeclareMenuItem().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = new JFileChooser();
				 
				 FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter("Declare file (*.xml)", "xml");

				 	fileChooser.setDialogTitle("Import Declare Constraints");
				 	fileChooser.setAcceptAllFileFilterUsed(false);
				 	fileChooser.setFileFilter(xmlfilter);
				 	
				 	String workingDirectoryName = System.getProperty("user.dir");
				 	File workingDirectory = new File(workingDirectoryName + File.separator + "resources" + File.separator + "declarative models");
				 	fileChooser.setCurrentDirectory(workingDirectory);
				 				 
			        int returnValue = fileChooser.showOpenDialog(Constants.getDesktop());
			        
			        if (returnValue == JFileChooser.APPROVE_OPTION) {
				         
			        	String[] options = new String[] {"Yes", "No"};
		        	    int response = JOptionPane.showOptionDialog(null, "Lose the previously defined Declare constraints?", "Import Declare constraints from a XML file",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

		        	    if(response==0) {
			        	
						// RESET the ConstraintsPerspective view
						Constants.getConstraintsPerspective().resetComponent();
								        	    	
			        	File selectedFile = fileChooser.getSelectedFile();
				         
					  	try {
					  			 
					  			 AssignmentViewBroker broker = XMLBrokerFactory.newAssignmentBroker(selectedFile.getAbsolutePath());
	
					  			 AssignmentModel assmod = broker.readAssignment();

								 boolean selected = false;
					  			 
					  			 for(ConstraintDefinition cd : assmod.getConstraintDefinitions()){

					  			  boolean is_valid_constraint = true;  
					  			  Vector<String> activities_not_in_the_repo_vector = new Vector<String>();
					  			  
					  			  String constraint = cd.getName() + "(";	
					  			  
					  			  int index = 0;
					  		    			  		    	  
					  		      for(Parameter p : cd.getParameters()){
					  		    	  
					  		       if(cd.getBranches(p).iterator().hasNext()){
					  		    	   				  		    
					  		    	   String activityName = cd.getBranches(p).iterator().next().toString().toLowerCase();
					  		    	   			  		    	 					  		    	   
										if(activityName.contains(" "))
											activityName = activityName.replaceAll(" ", "");
										
										if(activityName.contains("/"))
											activityName = activityName.replaceAll("\\/", "");
										
										if(activityName.contains("("))
											activityName = activityName.replaceAll("\\(", "");
										
										if(activityName.contains(")"))
											activityName = activityName.replaceAll("\\)", "");
										
										if(activityName.contains("<"))
											activityName = activityName.replaceAll("\\<", "");
										
										if(activityName.contains(">"))
											activityName = activityName.replaceAll("\\>", "");
										
										if(activityName.contains("."))
											activityName = activityName.replaceAll("\\.", "");
										
										if(activityName.contains(","))
											activityName = activityName.replaceAll("\\,", "_");
										
										if(activityName.contains("+"))
											activityName = activityName.replaceAll("\\+", "_");
										
										if(activityName.contains("-"))
											activityName = activityName.replaceAll("\\-", "_");
	
										if(!Constants.getActivitiesRepository_vector().contains(activityName)) {
											activities_not_in_the_repo_vector.addElement(activityName);
											is_valid_constraint = false;
										}
																				
					  		         cd.getBranches(p).iterator().next();
					  		         
					  		         constraint = constraint + activityName;
					  		         
					  		         if(index<cd.getParameters().size()-1)
					  		        	constraint = constraint + ",";
					  		         index++;
					  		       }
					  		      }
					  		      					  		      
					  		      constraint = constraint + ")";
					  		      
					  		    if(!is_valid_constraint) {
									int dialogResult = 0;
					  		    	
									if(!selected){
										selected = true;
					  		    	
										dialogResult = JOptionPane.showConfirmDialog(null, "One or more constraints refer to activities which are not listed in the activities repository!\nSuch constraints can not be properly imported, unless the missing activities are not imported in the repository.\nDo you want to import the activities in the activities repository?", "ATTENTION!", JOptionPane.YES_NO_OPTION);
									}


					  		    	if(dialogResult == JOptionPane.YES_OPTION){
					  		    		for(int h=0;h<activities_not_in_the_repo_vector.size();h++) {
					  		    			String specific_activity = activities_not_in_the_repo_vector.elementAt(h);
					  		    			
					  		    			Constants.getActivitiesRepository_vector().addElement(specific_activity);											
					  		    			Constants.getAlphabetPerspective().getAlphabetListModel().addElement(specific_activity);
											Constants.getTracesPerspective().getAlphabetListModel().addElement(specific_activity);
					  		    		}
										Constants.getConstraintsPerspective().getConstraintsListModel().addElement(constraint);	
					  		    	}
									//else   do nothing
					  		    }
					  		    else if(!Constants.getConstraintsPerspective().getConstraintsListModel().contains(constraint))
		            				Constants.getConstraintsPerspective().getConstraintsListModel().addElement(constraint);					  		      
					  			 
					  	}
					  	}
					  		catch (Exception exception) {
								exception.printStackTrace();
					        }
				        }
				        }
				  }
			});
		
		_view.getImportDOTAutomatonMenuItem().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = new JFileChooser();
				 
				    FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter("DOT file (*.dot)", "dot");

				 	fileChooser.setDialogTitle("Import Automaton");
				 	fileChooser.setAcceptAllFileFilterUsed(false);
				 	fileChooser.setFileFilter(xmlfilter);
				 	
				 	String workingDirectoryName = System.getProperty("user.dir");
				 	File workingDirectory = new File(workingDirectoryName + File.separator + "resources" + File.separator + "declarative models");
				 	fileChooser.setCurrentDirectory(workingDirectory);
				 				 
			        int returnValue = fileChooser.showOpenDialog(Constants.getDesktop());

			        
			        if (returnValue == JFileChooser.APPROVE_OPTION) {

						File selectedFile = fileChooser.getSelectedFile();
			        	String model_learning_constraint = "DFA{" + selectedFile.getAbsolutePath() + "}";

						

						
				         
			        	String[] options = new String[] {"Yes", "No"};
		        	    int response = JOptionPane.showOptionDialog(null, "Lose the previously defined Declare constraints?", "Import Declare constraints from a XML file",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

		        	    if(response==0) {

													//
													Automaton automaton = null;
	       	         		
													Vector<String> activities_not_in_the_repo_vector = new Vector<String>();
													boolean is_valid_constraint = true;
													String DFA_file_path = model_learning_constraint.replace("DFA{", "");
													DFA_file_path = DFA_file_path.replace("}", "");            			
													//ltl_formula = temporal_constraint;
															
															
														try {
															automaton = Utilities.getAutomatonForModelLearning(DFA_file_path);
															//automaton = Utilities.getAutomatonForModelLearningDot(DFA_file_path);
															State s = automaton.getInit();
															Iterator<State> it = automaton.iterator();
															while (it.hasNext()) {
																State ss = it.next();
																Iterator<Transition> transitions = ss.getOutput().iterator();
																while (transitions.hasNext()) {
																	Transition t = transitions.next();
					   
																	 //System.out.println(t.getSource().toString() +" "+ t.toString()+" "+t.getTarget().toString());
					
																	 if(!Constants.getActivitiesRepository_vector().contains(t.getPositiveLabel())) {
																		activities_not_in_the_repo_vector.addElement(t.getPositiveLabel());
																		is_valid_constraint = false;
																	}
					
																}
														   }
														   if(!is_valid_constraint) {
															int dialogResult = 0;
															dialogResult = JOptionPane.showConfirmDialog(null, "One or more transitions of the automaton refer to activities which are not listed in the activities repository!\nThe automaton can not be properly imported, unless the missing activities are not imported in the repository.\nDo you want to import the activities in the activities repository?", "ATTENTION!", JOptionPane.YES_NO_OPTION);
															
															if(dialogResult == JOptionPane.YES_OPTION){
																for(int h=0;h<activities_not_in_the_repo_vector.size();h++) {
																	String specific_activity = activities_not_in_the_repo_vector.elementAt(h);
																	
																	if(!Constants.getActivitiesRepository_vector().contains(specific_activity)){
																		Constants.getActivitiesRepository_vector().addElement(specific_activity);											
																		Constants.getAlphabetPerspective().getAlphabetListModel().addElement(specific_activity);
																    	Constants.getTracesPerspective().getAlphabetListModel().addElement(specific_activity);
																	}
																}
																// RESET the ConstraintsPerspective view
																Constants.getConstraintsPerspective().resetComponent();
			        											Constants.getConstraintsPerspective().getConstraintsListModel().addElement(model_learning_constraint);			  
															}
														  //else   do nothing
														}
					
					
													} catch (FileNotFoundException ee) {
														ee.printStackTrace();
														JOptionPane.showMessageDialog(_view, new JLabel("<html>The .dot file is not valid</html>"), "Attention", JOptionPane.ERROR_MESSAGE, new ImageIcon("images/alert_icon.png"));
					
													}
					
											//
			        	
						
				        }
				        }
				  }
			});
			

/*
 
_view.getImportDOTAutomatonMenuItem().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = new JFileChooser();
				 
				    FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter("DOT file (*.dot)", "dot");

				 	fileChooser.setDialogTitle("Import Automaton");
				 	fileChooser.setAcceptAllFileFilterUsed(false);
				 	fileChooser.setFileFilter(xmlfilter);
				 	
				 	String workingDirectoryName = System.getProperty("user.dir");
				 	File workingDirectory = new File(workingDirectoryName + File.separator + "resources" + File.separator + "declarative models");
				 	fileChooser.setCurrentDirectory(workingDirectory);
				 				 
			        int returnValue = fileChooser.showOpenDialog(Constants.getDesktop());
			        
			        if (returnValue == JFileChooser.APPROVE_OPTION) {
				         
			        	String[] options = new String[] {"Yes", "No"};
		        	    int response = JOptionPane.showOptionDialog(null, "Lose the previously defined Declare constraints?", "Import Declare constraints from a XML file",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

		        	    if(response==0) {
			        	
						// RESET the ConstraintsPerspective view
						Constants.getConstraintsPerspective().resetComponent();
								        	    	
			        	File selectedFile = fileChooser.getSelectedFile();
				        			        	
			        	String model_learning_constraint = "DFA{" + selectedFile.getAbsolutePath() + "}";
			        	
			        	Constants.getConstraintsPerspective().getConstraintsListModel().addElement(model_learning_constraint);
			        	
				        }
				        }
				  }
			});
*/
		
/*
		_view.getImportLTLFormulaMenuItem().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = new JFileChooser();
				 
				 FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter("Text file (*.txt)", "txt");

				 	fileChooser.setDialogTitle("Import LTL Formula");
				 	fileChooser.setAcceptAllFileFilterUsed(false);
				 	fileChooser.setFileFilter(xmlfilter);
				 	
				 	String workingDirectoryName = System.getProperty("user.dir");
				 	File workingDirectory = new File(workingDirectoryName + File.separator + "resources" + File.separator + "declarative models");
				 	fileChooser.setCurrentDirectory(workingDirectory);
				 				 
			        int returnValue = fileChooser.showOpenDialog(Constants.getDesktop());
			        
			        if (returnValue == JFileChooser.APPROVE_OPTION) {
				         		        	
			        	File selectedFile = fileChooser.getSelectedFile();
				        String ltl_formula= new String();	        	
			        	try {
							BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
							String line = reader.readLine();
							while(line!=null) {
							   //  System.out.println(line);							     
							     if(line!=null)  {
							    	 ltl_formula+=line;
							     }
							     line = reader.readLine();
							}
																				
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			        	catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
			        	String constraint = "LTL{" + ltl_formula + "}";
			        	
			        	Constants.getConstraintsPerspective().getConstraintsListModel().addElement(constraint);
				     }
				  }
			});
*/
	
	}
}
