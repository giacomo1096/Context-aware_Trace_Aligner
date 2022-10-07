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
import view.CostPerspective3;
import view.PlannerPerspective;

public class H_CostPerspective3 {
	
	public CostPerspective3 _view = null;
	
	public H_CostPerspective3 (CostPerspective3 i_view){
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
	
		_view.getPatternComboBox().addItemListener(new ItemListener()
		{

			 public void itemStateChanged(ItemEvent event)
			{				
				 String pattern = (String) _view.getPatternComboBox().getSelectedItem();
				 
				 if (event.getStateChange() == ItemEvent.SELECTED && pattern.equalsIgnoreCase("---") )
				 {
					 _view.getFirstActivityComboBox().setSelectedIndex(0);
					 _view.getSecondActivityComboBox().setSelectedIndex(0);
					 _view.getFirstActivityComboBox().setEnabled(false);
					 _view.getSecondActivityComboBox().setEnabled(false);
					 _view.getCostField1().setEnabled(false);
					 _view.getCostField2().setEnabled(false);

				 }

				 // 1 attività, 1 costo
				 else if (event.getStateChange() == ItemEvent.SELECTED && pattern.equalsIgnoreCase("pattern1") )
				 {
					 _view.getFirstActivityComboBox().setSelectedIndex(0);
					 _view.getSecondActivityComboBox().setSelectedIndex(0);
					 _view.getFirstActivityComboBox().setEnabled(true);
					 _view.getSecondActivityComboBox().setEnabled(false);
					 _view.getCostField1().setEnabled(true);
					 _view.getCostField2().setEnabled(false);
				 }

				 // 2 attivtià, 2 costi
				 else if (event.getStateChange() == ItemEvent.SELECTED && pattern.equalsIgnoreCase("pattern2") )
				 {
					 _view.getFirstActivityComboBox().setSelectedIndex(0);
					 _view.getSecondActivityComboBox().setSelectedIndex(0);
					 _view.getFirstActivityComboBox().setEnabled(true);
					 _view.getSecondActivityComboBox().setEnabled(true);
					 _view.getCostField1().setEnabled(true);
					 _view.getCostField2().setEnabled(true);
				 }
				 // 2 attivtià, 2 costi
				 else if (event.getStateChange() == ItemEvent.SELECTED && pattern.equalsIgnoreCase("pattern3") )
				 {
					 _view.getFirstActivityComboBox().setSelectedIndex(0);
					 _view.getSecondActivityComboBox().setSelectedIndex(0);
					 _view.getFirstActivityComboBox().setEnabled(true);
					 _view.getSecondActivityComboBox().setEnabled(true);
					 _view.getCostField1().setEnabled(true);
					 _view.getCostField2().setEnabled(true);
				 }
				 
				 // // 1 attivtià, 1 costo
				 else 
				 {
					_view.getFirstActivityComboBox().setSelectedIndex(0);
					_view.getSecondActivityComboBox().setSelectedIndex(0);
					_view.getFirstActivityComboBox().setEnabled(true);
					_view.getSecondActivityComboBox().setEnabled(false);
					_view.getCostField1().setEnabled(true);
					_view.getCostField2().setEnabled(false);
				}
				 
			}
		});
		
		_view.getRightButton().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
            	if(((String)_view.getPatternComboBox().getSelectedItem()).equalsIgnoreCase("---")) {            		
            		JOptionPane.showMessageDialog(null, "Please select a valid pattern!", "ATTENTION!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/info_icon.png"));
            	}         
            	

            	else if(((String)_view.getPatternComboBox().getSelectedItem()).equalsIgnoreCase("pattern1") || ((String)_view.getPatternComboBox().getSelectedItem()).equalsIgnoreCase("pattern4")) {
            			
            		if(((String)_view.getFirstActivityComboBox().getSelectedItem()).equalsIgnoreCase("---")) {
            	       		JOptionPane.showMessageDialog(null, "Please associate a valid activity!", "ATTENTION!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/info_icon.png"));
            			}
					if(_view.getCostField1().getText().equalsIgnoreCase("")){
						JOptionPane.showMessageDialog(null, "Please associate a valid cost!", "ATTENTION!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/info_icon.png"));
					}
					if(!(((String)_view.getFirstActivityComboBox().getSelectedItem()).equalsIgnoreCase("---")) && !(_view.getCostField1().getText().equalsIgnoreCase("")))
            		{
            			String pattern = (String)_view.getPatternComboBox().getSelectedItem();
            			String activity = (String)_view.getFirstActivityComboBox().getSelectedItem();
						String cost = _view.getCostField1().getText();
            			String pattern_model = pattern + "(" + activity + ",-," + cost + ",-,)";
            			if(_view.getPatternsListModel().contains(pattern_model))
            				JOptionPane.showMessageDialog(null, "The pattern '" + pattern_model + "' already exists!", "ATTENTION!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/info_icon.png"));
            			else
            				_view.getPatternsListModel().addElement(pattern_model);
            		}
            	}

				else if(((String)_view.getPatternComboBox().getSelectedItem()).equalsIgnoreCase("pattern2") || ((String)_view.getPatternComboBox().getSelectedItem()).equalsIgnoreCase("pattern3")) {
            			
            		if((((String)_view.getFirstActivityComboBox().getSelectedItem()).equalsIgnoreCase("---")) || (((String)_view.getSecondActivityComboBox().getSelectedItem()).equalsIgnoreCase("---")) ) {
            	       		JOptionPane.showMessageDialog(null, "Please associate a valid activity!", "ATTENTION!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/info_icon.png"));
            			}
					if((_view.getCostField1().getText().equalsIgnoreCase("")) || (_view.getCostField2().getText().equalsIgnoreCase(""))){
						JOptionPane.showMessageDialog(null, "Please associate a valid cost!", "ATTENTION!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/info_icon.png"));
					}
					if(!(((String)_view.getFirstActivityComboBox().getSelectedItem()).equalsIgnoreCase("---")) && !(_view.getCostField1().getText().equalsIgnoreCase("")))
            		{
            			String pattern = (String)_view.getPatternComboBox().getSelectedItem();
            			String activity1 = (String)_view.getFirstActivityComboBox().getSelectedItem();
						String activity2 = (String)_view.getSecondActivityComboBox().getSelectedItem();
						String cost1 = _view.getCostField1().getText();
						String cost2 = _view.getCostField2().getText();
            			String pattern_model = pattern + "(" + activity1 + "," + activity2 + "," + cost1 + "," + cost2 + ")";
            			if(_view.getPatternsListModel().contains(pattern_model))
            				JOptionPane.showMessageDialog(null, "The pattern '" + pattern_model + "' already exists!", "ATTENTION!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/info_icon.png"));
            			else
            				_view.getPatternsListModel().addElement(pattern_model);
            		}
            	}         	           	
            }
        });

		_view.getRemoveButton().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
            	
            	if(_view.getPatternsList().getSelectedIndex() == -1) { //no constraint selected
            		JOptionPane.showMessageDialog(null, "Please select a constraint to remove!", "ATTENTION!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/info_icon.png"));
            	} 
               	else {
                    	int index = _view.getPatternsList().getSelectedIndex();   	
                		_view.getPatternsListModel().removeElementAt(index);
                		
                	}
            	
            }
        });
		

	}

}
