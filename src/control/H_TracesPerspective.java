package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Hashtable;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import main.Constants;
import main.Trace;
import view.TracesPerspective;

public class H_TracesPerspective {
	
	public TracesPerspective _view = null;
	
	public H_TracesPerspective (TracesPerspective i_view){
		_view = i_view;
		installListeners();
	}

	private void installListeners() {	
	
		_view.getRightButton().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
            	if(_view.getAlphabetList().getSelectedIndex() == -1) {            		
            		JOptionPane.showMessageDialog(null, "Please select a valid activity!", "ATTENTION!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/info_icon.png"));
            	}   
            	else if(_view.getTracesComboBox().getSelectedItem().toString().equalsIgnoreCase(" --- ")) {            		
            		JOptionPane.showMessageDialog(null, "Please choose an existing trace (or create a new trace) for inserting the selected activity!", "ATTENTION!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/info_icon.png"));
            	}   
            	else {
            		String selected_element = (String) _view.getAlphabetList().getSelectedValue();
            		_view.getTraceListModel().addElement(selected_element);     
            		
            		//Update the selected trace and rebuilds every time the "structures" associated to the trace
            		String selected_trace_name = (String) _view.getTracesComboBox().getSelectedItem();
            		Vector<Trace> all_traces_vector = Constants.getAllTraces_vector();
            		
            		for(int h=0;h<all_traces_vector.size();h++) {
            			Trace t = all_traces_vector.elementAt(h);
            			
            			if(t.getTraceID().equalsIgnoreCase(selected_trace_name)) {
            				updateTrace(t);
            				break;
            			}
            		}
            	}
            }
        });
		
		_view.getRemoveButton().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
            	if(_view.getTraceList().getSelectedIndex() == -1) { //no activity selected
            		JOptionPane.showMessageDialog(null, "Please select a activity to be removed from the trace!", "ATTENTION!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/info_icon.png"));
            	} 
               	else {
                    	int index = _view.getTraceList().getSelectedIndex();   	
                		_view.getTraceListModel().removeElementAt(index);
                		                		
                		//Update the selected trace and rebuilds every time the "structures" associated to the trace
                		String selected_trace_name = (String) _view.getTracesComboBox().getSelectedItem();
                		Vector<Trace> all_traces_vector = Constants.getAllTraces_vector();
                		
                		for(int h=0;h<all_traces_vector.size();h++) {
                			Trace t = all_traces_vector.elementAt(h);
                			
                			if(t.getTraceID().equalsIgnoreCase(selected_trace_name)) {
                				updateTrace(t);
                				break;
                			}
                		}
                	}
            }
        });
		
		_view.getUpButton().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
            	if(_view.getTraceList().getSelectedIndex() == -1) {            		
            		JOptionPane.showMessageDialog(null, "Please select the activity of the trace you want to move!", "ATTENTION!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/info_icon.png"));
            	}            	
            	else {

            		if(_view.getTraceList().getSelectedIndex() > 0) {
            			
            			int index = _view.getTraceList().getSelectedIndex();
            			String element = (String) _view.getTraceList().getSelectedValue();
            			
            			int index_down = index -1;
            			_view.getTraceListModel().removeElementAt(index);
            			_view.getTraceListModel().insertElementAt(element, index_down);
            			_view.getTraceList().setSelectedIndex(index_down);
            			
            			
            			//Update the selected trace and rebuilds every time the "structures" associated to the trace
                		String selected_trace_name = (String) _view.getTracesComboBox().getSelectedItem();
                		Vector<Trace> all_traces_vector = Constants.getAllTraces_vector();
                		
                		for(int h=0;h<all_traces_vector.size();h++) {
                			Trace t = all_traces_vector.elementAt(h);
                			
                			if(t.getTraceID().equalsIgnoreCase(selected_trace_name)) {
                				updateTrace(t);
                				break;
                			}
                		}
            		}
            	}
            	           	
            }
        });
		
		_view.getDownButton().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
            	if(_view.getTraceList().getSelectedIndex() == -1) {            		
            		JOptionPane.showMessageDialog(null, "Please select the activity of the trace to be moved!", "ATTENTION!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/info_icon.png"));
            	}            	
            	else {

            		if(_view.getTraceList().getSelectedIndex() + 1 < _view.getTraceListModel().size()) {
            			
            			int index = _view.getTraceList().getSelectedIndex();
            			String element = (String) _view.getTraceList().getSelectedValue();
            			
            			int index_up = index + 1;
            			_view.getTraceListModel().removeElementAt(index);
            			_view.getTraceListModel().insertElementAt(element, index_up);
            			_view.getTraceList().setSelectedIndex(index_up);
            			
            			//
            			// Update the selected trace and rebuilds every time the "structures" associated to the trace.
                		//
            			String selected_trace_name = (String) _view.getTracesComboBox().getSelectedItem();
                		Vector<Trace> all_traces_vector = Constants.getAllTraces_vector();
                		
                		for(int h=0;h<all_traces_vector.size();h++) {
                			Trace t = all_traces_vector.elementAt(h);
                			
                			if(t.getTraceID().equalsIgnoreCase(selected_trace_name)) {
                				updateTrace(t);
                				break;
                			}
                		}
            		}
            	}
            	           	
            }
        });
		
		_view.getEditTraceButton().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
            	    String[] options = new String[] {"Create", "Remove", "Cancel"};
            	    int response = JOptionPane.showOptionDialog(null, "Do you want to create a new trace, \nor to remove an existing one?", "Create/Remove Traces", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            	    //
            	    // CREATE a new trace
            	    //
            	    if(response==0) {
            	    	int number_of_elements_in_combo_box = _view.getTracesComboBox().getItemCount();
            	    	
            	    	String new_trace_id = new String();
            	    	
            	    	for(int i=1;i<=number_of_elements_in_combo_box;i++) {
            	    		new_trace_id = "Trace#" + i;            	    		
            	    		String trace_id = _view.getTracesComboBox().getItemAt(i);
            	    		if(!new_trace_id.equalsIgnoreCase(trace_id)) {
            	    			_view.getTracesComboBox().insertItemAt(new_trace_id, i);
            	    			_view.getTracesComboBox().setSelectedIndex(i);
            	    			_view.resetTraceListModel();
            	    			break;
            	    		}
            	    	}
            	    	Constants.getAllTraces_vector().addElement(new Trace(new_trace_id, new_trace_id));
            	    }

            	    // REMOVE an existing trace
            	    else if(response==1) {
            	    	String trace_to_be_removed_ID = (String) _view.getTracesComboBox().getSelectedItem();
            	    	
            	    	if (trace_to_be_removed_ID.equalsIgnoreCase(" --- "))
            	    		JOptionPane.showMessageDialog(null, "Please select a valid trace to remove!", "ATTENTION!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/info_icon.png"));
            	    	else {
            	    		_view.getTracesComboBox().removeItem(trace_to_be_removed_ID);
            	    		_view.resetTraceListModel();
            	    		_view.getTracesComboBox().setSelectedItem(" --- ");
            	    		
            	    		//Remove the selected trace from the global vector of traces
                    		Vector<Trace> all_traces_vector = Constants.getAllTraces_vector();
                    		
                    		for(int h=0;h<all_traces_vector.size();h++) {
                    			Trace t = all_traces_vector.elementAt(h);
                    			
                    			if(t.getTraceID().equalsIgnoreCase(trace_to_be_removed_ID)) {
                    				Constants.getAllTraces_vector().removeElementAt(h);
                    				break;
                    			}
                    		}
            	    		
            	    		JOptionPane.showMessageDialog(null, trace_to_be_removed_ID + " correctly removed!", "CONFIRMATION!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/info_icon.png"));
            	    	}
            	 }
            }
        });
            	
		
		_view.getTracesComboBox().addItemListener(new ItemListener()
		{
			 public void itemStateChanged(ItemEvent event)
			{
				String selected_trace_ID = (String) _view.getTracesComboBox().getSelectedItem();
			 
				if (event.getStateChange() == ItemEvent.SELECTED && !(selected_trace_ID.equalsIgnoreCase(" --- ")) )
				 {
					_view.resetTraceListModel();
					
					Vector<Trace> all_traces_vector = Constants.getAllTraces_vector();
            		
            		for(int h=0;h<all_traces_vector.size();h++) {
            			Trace t = all_traces_vector.elementAt(h);
            			
            			if(t.getTraceID().equalsIgnoreCase(selected_trace_ID)) {
            				
            				for(int index=0;index<t.getOriginalTraceContent_vector().size();index++) {
            					_view.getTraceListModel().addElement(t.getOriginalTraceContent_vector().elementAt(index));
            				}
            				
            				break;
            			}
            		}
					
				 }
				else if(event.getStateChange() == ItemEvent.SELECTED && (selected_trace_ID.equalsIgnoreCase(" --- "))) {
					_view.resetTraceListModel();
				}
			}
		});
		
		_view.getNextStepButton().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
            	if(Constants.getAllTraces_vector().size()==0)  {
            		JOptionPane.showMessageDialog(null, "There is no trace defined for the log!\nAt least a trace (even if empty) is required to run the software!", "ATTENTION!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/info_icon.png"));
            	}
            	else {
            		//
            		// Reset and reinitialize the global vector containing the alphabet of the activities of the trace
            		//
            		Constants.setAlphabetOfTheTraces_vector(new Vector<String>());
            		
            		for(int k=0;k<Constants.getAllTraces_vector().size();k++) {
            			Trace trace = Constants.getAllTraces_vector().elementAt(k);
            			for(int j=0;j<trace.getTraceAlphabet_vector().size();j++) {
            				String symbol_of_the_alphabet = trace.getTraceAlphabet_vector().elementAt(j);
            				if(!Constants.getAlphabetOfTheTraces_vector().contains(symbol_of_the_alphabet))
                    			Constants.getAlphabetOfTheTraces_vector().addElement(symbol_of_the_alphabet);
            		}     		
    					
            		}
            		
            		//System.out.println("Complete Alphabet: " + Constants.getActivitiesRepository_vector());
            		//System.out.println("Alphabet of the traces: " + Constants.getAlphabetOfTheTraces_vector());
            		
            		
            		Constants.getAlphabetPerspective().setComponentEnabled(false);
       			 	Constants.getTracesPerspective().setComponentEnabled(false);
       			 	Constants.getConstraintsPerspective().setComponentEnabled(true);
       			 	
	       			Constants.getConstraintsPerspective().getFirstActivityComboBox().removeAllItems();
	     			Constants.getConstraintsPerspective().getSecondActivityComboBox().removeAllItems();
	     			Constants.getConstraintsPerspective().getFirstActivityComboBox().addItem("---");
	    			Constants.getConstraintsPerspective().getSecondActivityComboBox().addItem("---");
	    			
	    			for(int i=0;i<_view.getAlphabetListModel().getSize();i++) {
            			String string = (String) _view.getAlphabetListModel().getElementAt(i);
            			Constants.getConstraintsPerspective().getFirstActivityComboBox().addItem(string);
            			Constants.getConstraintsPerspective().getSecondActivityComboBox().addItem(string);
            			}
             		 
             		 Constants.getConstraintsPerspective().getFirstActivityComboBox().setEnabled(false);
        			 Constants.getConstraintsPerspective().getSecondActivityComboBox().setEnabled(false);
	    			
	    			}
            	
            	Constants.getMenuPerspective().getImportMenu().setEnabled(true);
            	Constants.getMenuPerspective().getImportDeclareMenuItem().setEnabled(true);
             	Constants.getMenuPerspective().getImportDOTAutomatonMenuItem().setEnabled(true);            	
             	
            }
        });
		
		_view.getPreviousStepButton().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
        			 Constants.getAlphabetPerspective().setComponentEnabled(true);
        			 Constants.getTracesPerspective().setComponentEnabled(false);

            }
        });
		
	}
	
	private void updateTrace(Trace trace) {
		
		trace.setTraceAlphabet_vector(new Vector<String>());	
		trace.setOriginalTraceContent_vector(new Vector<String>());
		trace.setOriginalTraceContentString(new String());		
		trace.setTraceContentWithActivitiesInstances_vector(new Vector<String>());		
		trace.setNumberOfActivityInstances_Hashtable(new Hashtable<String,Integer>());
		trace.setAssociationsToActivityInstances_Hashtable(new Hashtable<String,String>());
		
		String trace_content = new String();
		
		for(int j=0;j<_view.getTraceListModel().size();j++) {
  			
			String string = (String) _view.getTraceListModel().getElementAt(j);
  			
				//
				// Update of the alphabet of the single trace.
				//
				if(!trace.getTraceAlphabet_vector().contains(string)) {
					trace.getTraceAlphabet_vector().addElement(string);
				}
			
				//
  				// Update of the Hashtable used to record the number of activity instances of a trace.
			    // For example, if the content of the trace is <a,b,a>, this Hashtable 
			    // will contain two keys (a,b) with the following values: {a=2,b=1}.
				//
				if(trace.getNumberOfActivityInstances_Hashtable().containsKey(string)) {
  					int number_of_instances = trace.getNumberOfActivityInstances_Hashtable().get(string);
  					number_of_instances = number_of_instances + 1;
  					trace.getNumberOfActivityInstances_Hashtable().put(string, number_of_instances);
  				}
  				else {
  					trace.getNumberOfActivityInstances_Hashtable().put(string, 1);
  				}

  			for(int p=0;p<_view.getTraceListModel().size();p++) {				
  				
  				String string_key = string + p;
  				
  				if(!trace.getAssociationsToActivityInstances_Hashtable().containsKey(string_key)) {
  					
  					//
  	  				// Update of the Hashtable used to record the association between activity instances and their "activity model".
					// For example, if the original content of the trace is <a,b,a>, this Hashtable will contain three keys (a0,a1,b0) 
  					// with the following values: {a0=a,a1=a,b0=b}.
  					//
  					trace.getAssociationsToActivityInstances_Hashtable().put(string_key,string);
  					
  					//
  				    // Update of the ordered content of the trace with the explicit representation of activity instances 
					// (e.g., if the original trace content is <a,b,a,c,d,a>, this vector will contain a0,b0,a1,c0,d0,a2, ... ecc.).
  					//
  					trace.getTraceContentWithActivitiesInstances_vector().addElement(string_key);
  					
  					//
  					// Update of the original and ordered content of the trace (e.g., a,b,a,c,d,a, ... ecc.).
  					//
  					trace.getOriginalTraceContent_vector().addElement(string);
  					
  					//
  					// Update the String with the original and ordered content of the trace (e.g., a,b,a,c,d,a, ... ecc.).
  					//
  					trace_content += string + ",";
  					
  					break;
  				}
  			} 			
  			
   		 }
		trace.setOriginalTraceContentString(trace_content.substring(0, trace_content.length()-1));

	}

}
