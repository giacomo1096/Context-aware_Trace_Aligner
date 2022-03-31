package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import main.Constants;

import view.LTLformulaPerspective;

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
            	
            	if(Constants.getConstraintsPerspective().getConstraintsListModel().contains(ltl_formula))
    				JOptionPane.showMessageDialog(_view, new JLabel("<html>The LTL formula '" + ltl_formula + "' <font color=\"red\">already exists</font>!</html>"), "Attention", JOptionPane.ERROR_MESSAGE, new ImageIcon("images/alert_icon.png"));
    			else {
    			 	Constants.getConstraintsPerspective().getConstraintsListModel().addElement("LTL{" + ltl_formula + "}");
            		_view.dispose();
    			}
            }
        });
		
	}

}
