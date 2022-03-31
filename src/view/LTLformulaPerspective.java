package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

import control.H_LTLformulaPerspective;

public class LTLformulaPerspective extends JDialog{

	private static final long serialVersionUID = 1L;

	protected H_LTLformulaPerspective _handler;
	
    private Container content;
	
	private JPanel northPanel;
	
	private JLabel formulaLabel;
	private JTextArea formulaTextArea;
	private JScrollPane formulaScrollPane;

	private JPanel southPanel;
	private JButton okButton;
	private JButton cancelButton;
		
	public LTLformulaPerspective(){
		super();
		setModal(true);
		initComponent();
		initHandler();
		setVisible(true);
	}

	private void initComponent() {
		
		content = getContentPane();
		content.setLayout(new FlowLayout());
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
		
		this.setTitle("Create a new LTL formula");
		
		northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(440,200));
		northPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		northPanel.setLayout(new FlowLayout());
		
		formulaLabel = new JLabel(" LTL formula:");
		formulaLabel.setPreferredSize(new Dimension(435,30));
		formulaTextArea = new JTextArea();
		formulaTextArea.setPreferredSize(new Dimension(390,130));
		
		formulaScrollPane = new JScrollPane(formulaTextArea);
		formulaScrollPane.setPreferredSize(new Dimension(400,140));
		formulaScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		formulaScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		northPanel.add(formulaLabel);
		northPanel.add(formulaScrollPane);

		okButton = new JButton("Create");
		cancelButton = new JButton("Cancel");
		okButton.setPreferredSize(new Dimension(90,25));
		cancelButton.setPreferredSize(new Dimension(90,25));
		
		southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(340,40));
		southPanel.setLayout(new FlowLayout());
		southPanel.add(okButton);
		southPanel.add(cancelButton);	
			
		this.add(northPanel);
		this.add(southPanel);		
		
		this.setSize(450,300);
		this.setResizable(false);	
		
        int width = this.getWidth();
        int height = this.getHeight();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds((screenSize.width / 2) - (width / 2), (screenSize.height / 2) - (height / 2), width, height);

	}
	
	private void initHandler() {
		
		_handler = new H_LTLformulaPerspective(this);
		
	}

	public JTextArea getFormulaTextArea() {
		return formulaTextArea;
	}

	public JButton getOkButton() {
		return okButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

}