package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import main.Constants;
import view.CostPerspective;
import view.PlannerPerspective;

public class H_CostPerspective {
	
	public CostPerspective _view = null;
	
	public H_CostPerspective (CostPerspective i_view){
		_view = i_view;
		installListeners();
	}

	private void installListeners() {
		
		_view.getPreviousStepButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Constants.getConstraintsPerspective().setComponentEnabled(true);
				Constants.setBack_from_cost(true);
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
				Constants.getConstraintsPerspective().setComponentEnabled(true);
				Constants.setBack_from_cost(true);
				_view.dispose();
            }
        });

		
		_view.getNextStepButton().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
            	
				_view.dispose();
				//PlannerPerspective ple = Constants.getPlannerPerspective();
				//ple.setModal(true);
	            //ple.setVisible(true);
            	
            }
        });
		
		_view.getImportDFAButton().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {

            	JFileChooser fileChooser = new JFileChooser();
				 
			    FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter("DOT file (*.dot)", "dot");

			 	fileChooser.setDialogTitle("Import DFA Cost Model");
			 	fileChooser.setAcceptAllFileFilterUsed(false);
			 	fileChooser.setFileFilter(xmlfilter);
			 	
			 	String workingDirectoryName = System.getProperty("user.dir");
			 	File workingDirectory = new File(workingDirectoryName + File.separator + "resources" + File.separator + "declarative models");
			 	fileChooser.setCurrentDirectory(workingDirectory);
			 				 
		        int returnValue = fileChooser.showOpenDialog(Constants.getDesktop());
							        	    	
		        	File selectedFile = fileChooser.getSelectedFile();
			        			        	
		        	String DFA_cost_model_file = "DFA{" + selectedFile.getAbsolutePath() + "}\n";
		        	
		        	_view.getCostModelTextArea().append(DFA_cost_model_file);        		        
            }
        });
		
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
	}

}
