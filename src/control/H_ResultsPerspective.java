package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import view.ResultsPerspective;

public class H_ResultsPerspective {
	
	public ResultsPerspective _view = null;
	
	public H_ResultsPerspective (ResultsPerspective i_view){
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
            	_view.getPlanner_thread().interrupt();    
            	_view.dispose();
            	}
        });
		
		_view.getOkButton().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
            	_view.getPlanner_thread().interrupt();           	
               	_view.dispose();
            }
        });
						
	}
	
}