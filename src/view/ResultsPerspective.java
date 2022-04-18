package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import control.H_ResultsPerspective;

import main.Constants;
import main.Trace;

public class ResultsPerspective extends JDialog
{

	private static final long serialVersionUID = 1L;	
	private JTextArea resultsArea;
	private JScrollPane resultsScrollPane;	
	private JLabel resultsLabel;
	private Thread planner_thread;
	private JButton okButton;
	
	//StringBuffer used to record the statistics of the planning execution
	StringBuffer csvBuffer = new StringBuffer();
	
	private String results_log_file;
	private String results_csv_file;
	private int number_of_traces_to_check_from = 0;
	private int number_of_traces_to_check_to = 0;
	private int number_of_traces_with_failure = 0;
	private int number_of_traces_aligned = 0;
	private Vector<String> traces_with_failure_vector = new Vector<String>();
	private double log_fitness;
	
	private long totalTranslationTime = 0;
	private long totalPreprocessingTime = 0;
	private long totalSearchingSubOptTime = 0;
	private long totalSearchingOptTime = 0;
	
	
	protected H_ResultsPerspective _handler;
	
	public ResultsPerspective()
	{
		super();
		initComponent();
		initHandler();
		this.setModal(true);
		this.setVisible(true);
	}

	public void initComponent()
	{		
		Container content = this.getContentPane();
	    
	    content.setLayout(new FlowLayout()); 	
	   	
		resultsArea = new JTextArea();
		resultsArea.setEditable(false);
		resultsScrollPane = new JScrollPane(resultsArea);
		resultsScrollPane.setPreferredSize(new Dimension(490,480));
		resultsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		resultsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);   
		
		resultsLabel = new JLabel("Alignment Process: ");
		resultsLabel.setPreferredSize(new Dimension(490,25));
				  
		okButton = new JButton("OK");
		
		this.add(resultsLabel);	
	    this.add(resultsScrollPane);	
	   
	    this.add(okButton); 
		
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	    this.setLocation ( ( screenSize.width / 2 ) - ( this.getWidth ( ) / 2 ), (
	    screenSize.height / 2 ) - ( this.getHeight ( ) / 2 ) );

	    this.invokePlanner(this);
	    
		this.setTitle("Traces Alignment");
	    this.setSize(500, 600);
	    this.setResizable(true);
	   	   
	}
	
	private void initHandler() {
		
		_handler = new H_ResultsPerspective(this);
		
	}
	
	public void invokePlanner(ResultsPerspective resP)  {
		
		final ResultsPerspective rp = resP;
		
		planner_thread = new Thread(new Runnable() {
                		
             public void run() {
            	 try
                 {        
            	
                Timestamp tmsp_log = new Timestamp(new java.util.Date().getTime());	 
                
		                results_log_file = "results/res_" + tmsp_log + ".txt"; 
		            	scriviFile(results_log_file, new StringBuffer(""));
		            	
		            	//results_csv_file = "results/csv_" + tmsp_log + ".csv"; 
		            	//scriviFile(results_csv_file, new StringBuffer(""));
            	
            	if(Constants.getPlannerPerspective().getNumber_of_Traces_checkBox().isSelected()) {
            		number_of_traces_to_check_from = Constants.getPlannerPerspective().getNumber_of_traces_ComboBox_FROM().getSelectedIndex();
            		number_of_traces_to_check_to = Constants.getPlannerPerspective().getNumber_of_traces_ComboBox_TO().getSelectedIndex();
            	}			
            	else {
            		number_of_traces_to_check_from = 1;
            		number_of_traces_to_check_to = Constants.getAllTraces_vector().size();
            	}
            	           	
            	for(int k=number_of_traces_to_check_from-1;k<number_of_traces_to_check_to;k++) {
            		
            			int trace_number = k+1;
            			           			
            			//StringBuffer used to record the logs of the planner execution
            			StringBuffer logBuffer = new StringBuffer();
            		
            			//StringBuffer used to record the statistics of the planning execution
            			//csvBuffer = new StringBuffer();
            			
            			Trace trace = Constants.getAllTraces_vector().elementAt(k);
                 		
     	            	Constants.setPDDLActivitiesVector(new Vector<String>());     

     	            	/*
     	            	StringBuffer sb_domain = createDomain(trace);
     	            	StringBuffer sb_problem = createProblem(trace);
     	            	
     	            	
     	            	if(Constants.getPlannerPerspective().getFDOptimalCheckBox().isSelected()) {
	     	            	scriviFile("fast-downward/src/Conformance_Checking/domain.pddl", sb_domain);
	     	            	scriviFile("fast-downward/src/Conformance_Checking/problem.pddl", sb_problem);	 
     	            	}
     	            	
     	            	if(Constants.getPlannerPerspective().getSymBAoptimalCheckBox().isSelected()) {
	     	            	scriviFile("seq-opt-symba-2/Conformance_Checking/domain.pddl", sb_domain);
	     	            	scriviFile("seq-opt-symba-2/Conformance_Checking/problem.pddl", sb_problem);	 
     	            	}
     	            	
     	            	if(Constants.getPlannerPerspective().getLPGCheckBox().isSelected()) {
	     	            	scriviFile("LPG/Conformance_Checking/domain.pddl", sb_domain);
	     	            	scriviFile("LPG/Conformance_Checking/problem.pddl", sb_problem);	 
     	            	}
     	            	*/
     	            	
     	         rp.getResultsArea().append("*******************************\n");   	
            	 rp.getResultsArea().append("ALIGNMENT FOR " + trace.getTraceID() + "\n");
     	         rp.getResultsArea().append("*******************************\n"); 
            	      
     	         logBuffer.append("*******************************\n");   
     	         logBuffer.append("ALIGNMENT FOR " + trace.getTraceID() + "\n");
     	         logBuffer.append("*******************************\n");   

     	        	 //Clean the 'output.sas' file of Fast Downward
     	        	 File outputSAS_file = new File("fast-downward/src/sas_plan");
     	        	 PrintWriter writer = new PrintWriter(outputSAS_file);
     	        	 writer.print("");
     	        	 writer.close();
     	        	 
     	        	 File outputSYMBA_file = new File("seq-opt-symba-2/out.txt");
    	        	 writer = new PrintWriter(outputSYMBA_file);
    	        	 writer.print("");
    	        	 writer.close();
     	        	 
     	        	 
     	    		 resultsArea.append(">>>> ORIGINAL TRACE: " + trace.getOriginalTraceContent_vector()+"\n");
     	    		 
     	    		 //resultsArea.append(">>>> DECLARE RULES: " + Constants.getAllConstraints_vector()+"\n");
     	    		 //resultsArea.append(">>>> STARTING TRACE in PDDL: " + trace.getTraceContentWithActivitiesInstances_vector() + "\n\n");
     	        	 
     	    		 //rp.getResultsArea().append("---- START THE ALIGNMENT PROCESS ----\n");
     	    		 //logBuffer.append("---- START THE ALIGNMENT PROCESS ----\n");
     	    		
	            	 Thread.sleep(5000);

	            	 Timestamp tmsp1 = new Timestamp(new java.util.Date().getTime());
	            			            	
	            		            	 
	            	 System.out.println(trace_number);
	            	 Process pr = Runtime.getRuntime().exec("gnome-terminal -e ./run_SYMBA 3");                 		
	         	     pr.waitFor();
	         	     
	         	     //Timestamp tmsp2_a = new Timestamp(new java.util.Date().getTime());
	         	     	      	        	            	     
	        	     //long translation_time = tmsp2_a.getTime()-tmsp1.getTime();
	        	     
	        	     //rp.getResultsArea().append(">>>> TRANSLATION TIME : ");
	        	     //rp.getResultsArea().append(translation_time + " ms.\n");
	        	     
	        	     //logBuffer.append(">>>> TRANSLATION TIME : ");
	        	     //logBuffer.append(translation_time + " ms.\n");
	        	     	         	   
	        	     //totalTranslationTime += translation_time;
	        	     
	         	     Thread.sleep(5000);
	         	     
	         	    	
	         	     //Update the .csv File	
	         	     //csvBuffer.append(trace.getTraceNumber() + ",");
	         	     //csvBuffer.append(trace.getOriginalTraceContent_vector().size() + ",");
	         	     //csvBuffer.append((translation_time / 1000.0) + ",");
	         	     
	         	     //Timestamp tmsp2_b = new Timestamp(new java.util.Date().getTime());
	         	    
	         	     //Process pr2 = Runtime.getRuntime().exec("gnome-terminal -e ./preprocess_script");                 		
	        	     //pr2.waitFor();
	        	     
	            	 //Timestamp tmsp3 = new Timestamp(new java.util.Date().getTime());
	  	     
	        	     //long preprocessing_time = tmsp3.getTime()-tmsp2_b.getTime();
	        	     
	        	     //rp.getResultsArea().append(">>>> PREPROCESSING TIME : ");
	        	     //rp.getResultsArea().append(preprocessing_time + " ms.\n\n");
	        	     
	        	     //logBuffer.append(">>>> PREPROCESSING TIME : ");
	        	     //logBuffer.append(preprocessing_time + " ms.\n");
	        	     
	        	     //Update the .csv File	
	         	     //csvBuffer.append((preprocessing_time / 1000.0) + ",");

	        	     //totalPreprocessingTime += preprocessing_time;
	        	      	               	     
        	     if(Constants.getPlannerPerspective().getFDOptimalCheckBox().isSelected())  {
            	     
     	        	 //Remove the 'sas_plan' file with the plan
     	        	 //File SASplan_file = new File("fast-downward/src/sas_plan");
     	        	 //SASplan_file.delete();
        	    	 
        	    	 Thread.sleep(1000);
        	    	 
        	    	 rp.getResultsArea().append("\n---- SEARCH HEURISTIC: Blind A* ----\n");                	    	 
        	    	 logBuffer.append("\n---- SEARCH HEURISTIC: Blind A* ----\n");
        	    	 
        	    	 //Timestamp tmsp6 = new Timestamp(new java.util.Date().getTime());
            	     //Process pr3 = Runtime.getRuntime().exec("gnome-terminal -e ./planner_opt_script");                  		
             	     //pr3.waitFor();
             	     
        	    	 Timestamp tmsp7 = new Timestamp(new java.util.Date().getTime());
             	     
             	     long searching_time = tmsp7.getTime()-tmsp1.getTime();
             	     totalSearchingOptTime += searching_time;
             	    
            	     rp.getResultsArea().append(">>>> SEARCHING TIME : ");
            	     rp.getResultsArea().append(searching_time + " ms.\n");
            	     
            	     logBuffer.append(">>>> SEARCHING TIME : ");
	        	     logBuffer.append(searching_time + " ms.\n");
	        	     
	        	     //Update the .csv File	
	         	     //csvBuffer.append((searching_time / 1000.0) + ",");
	        	     
	        	     try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(results_log_file, true)))) {
	        	    	    out.println(logBuffer);
	        	     }catch (IOException e) {System.out.println("Il file non esiste");}
	        	                	     
            	     Thread.sleep(1000);
            	     
             	     rp.createResults(trace,"Fast-Downward");
                 }
        	     
        	    
            	
            	}
            	 int total_number_of_traces = number_of_traces_to_check_to - number_of_traces_to_check_from + 1;
            	 int total_number_of_traces_analyzed = total_number_of_traces - number_of_traces_with_failure;
            	             	 
            	 rp.getResultsArea().append("\n*******************************\n"); 
            	 
            	 rp.getResultsArea().append("NUMBER OF TRACES ANALYZED : " + total_number_of_traces_analyzed + "\n");
            	 rp.getResultsArea().append("NUMBER OF TRACES NOT ANALYZED (due to a planner failure) : " + number_of_traces_with_failure + "\n");
            	 if(number_of_traces_with_failure>0) 
            		 rp.getResultsArea().append("\nLIST OF TRACES NOT ANALYZED (due a planner failure) : " + traces_with_failure_vector + "\n");
            	 rp.getResultsArea().append("NUMBER OF TRACES REQUIRING THE ALIGNMENT : " + number_of_traces_aligned + "\n");            	            	 
            	 rp.getResultsArea().append("FITNESS : " + log_fitness/total_number_of_traces_analyzed + "\n");
            	 rp.getResultsArea().append("\n*******************************\n");   	
    	         
    	         rp.getResultsArea().append("TOTAL TRANSLATION TIME (in ms.) : " + totalTranslationTime + " ms\n");
    	         rp.getResultsArea().append("AVERAGE TRANSLATION TIME (in ms.) : " + ((long)(totalTranslationTime / total_number_of_traces)) + " ms\n");    	         
    	         double seconds = (totalTranslationTime / 1000.0);
    	         rp.getResultsArea().append("TOTAL TRANSLATION TIME (in seconds) : " + seconds + " sec\n");
    	         rp.getResultsArea().append("AVERAGE TRANSLATION TIME (in ms.) : " + ((double)(seconds / total_number_of_traces)) + " sec");
   	         
    	         rp.getResultsArea().append("\n-------------------------------\n");   
    	         
    	         rp.getResultsArea().append("TOTAL PREPROCESSING TIME (in ms.) : " + totalPreprocessingTime + " ms\n");
    	         rp.getResultsArea().append("AVERAGE PREPROCESSING TIME (in ms.) : " + ((long)(totalPreprocessingTime / total_number_of_traces_analyzed)) + " ms\n");    	         
    	         seconds = (totalPreprocessingTime / 1000.0);    	         
    	         rp.getResultsArea().append("TOTAL PREPROCESSING TIME (in seconds) : " + seconds + " sec\n");
    	         rp.getResultsArea().append("AVERAGE PREPROCESSING TIME (in ms.) : " + ((double)(seconds / total_number_of_traces_analyzed)) + " sec");
    	         
    	         if(Constants.getPlannerPerspective().getFDOptimalCheckBox().isSelected()) {
    	        	 rp.getResultsArea().append("\n-------------------------------\n");
    	        	 rp.getResultsArea().append("TOTAL SEARCHING TIME (in ms.) - Blind A* : " + totalSearchingOptTime + "\n");
    	        	 rp.getResultsArea().append("AVERAGE SEARCHING TIME (in ms.) - Blind A* : " + ((long)(totalSearchingOptTime / total_number_of_traces_analyzed)) + " ms\n");    	 
    	        	 seconds = (totalSearchingOptTime / 1000.0); 	        	 
    	        	 rp.getResultsArea().append("TOTAL SEARCHING TIME (in seconds) - Blind A* : " + seconds + " sec\n");
    	        	 rp.getResultsArea().append("AVERAGE SEARCHING TIME (in seconds) - Blind A* : " + ((double)(seconds / total_number_of_traces_analyzed)) + " sec");
    	         }
    	         
    	         rp.getResultsArea().append("\n*******************************\n"); 
    	         
    	         StringBuffer resultBuffer = new StringBuffer();
    	         
    	         resultBuffer.append("\n*******************************\n"); 
            	 
    	         resultBuffer.append("NUMBER OF TRACES ANALYZED : " + total_number_of_traces_analyzed + "\n");
    	         resultBuffer.append("NUMBER OF TRACES NOT ANALYZED (due to a planner failure) : " + number_of_traces_with_failure + "\n");
            	 if(number_of_traces_with_failure>0)
            		 resultBuffer.append("\nLIST OF TRACES NOT ANALYZED (due a planner failure) : " + traces_with_failure_vector + "\n");
            	 resultBuffer.append("NUMBER OF TRACES REQUIRING THE ALIGNMENT : " + number_of_traces_aligned + "\n");            	            	 
            	 resultBuffer.append("FITNESS : " + log_fitness/total_number_of_traces_analyzed + "\n");
            	 resultBuffer.append("\n*******************************\n");   	
    	         
    	         resultBuffer.append("TOTAL TRANSLATION TIME (in ms.) : " + totalTranslationTime + " ms\n");
    	         resultBuffer.append("AVERAGE TRANSLATION TIME (in ms.) : " + ((long)(totalTranslationTime / total_number_of_traces)) + " ms\n");    	         
    	         seconds = (totalTranslationTime / 1000.0);
    	         resultBuffer.append("TOTAL TRANSLATION TIME (in seconds) : " + seconds + " sec\n");
    	         resultBuffer.append("AVERAGE TRANSLATION TIME (in ms.) : " + ((double)(seconds / total_number_of_traces)) + " sec");
   	         
    	         resultBuffer.append("\n-------------------------------\n");   
    	         
    	         resultBuffer.append("TOTAL PREPROCESSING TIME (in ms.) : " + totalPreprocessingTime + " ms\n");
    	         resultBuffer.append("AVERAGE PREPROCESSING TIME (in ms.) : " + ((long)(totalPreprocessingTime / total_number_of_traces_analyzed)) + " ms\n");    	         
    	         seconds = (totalPreprocessingTime / 1000.0);    	         
    	         resultBuffer.append("TOTAL PREPROCESSING TIME (in seconds) : " + seconds + " sec\n");
    	         resultBuffer.append("AVERAGE PREPROCESSING TIME (in ms.) : " + ((double)(seconds / total_number_of_traces_analyzed)) + " sec");
    	         
    	         if(Constants.getPlannerPerspective().getFDOptimalCheckBox().isSelected()) {
    	        	 resultBuffer.append("\n-------------------------------\n");
    	        	 resultBuffer.append("TOTAL SEARCHING TIME (in ms.) - Blind A* : " + totalSearchingOptTime + "\n");
    	        	 resultBuffer.append("AVERAGE SEARCHING TIME (in ms.) - Blind A* : " + ((long)(totalSearchingOptTime / total_number_of_traces_analyzed)) + " ms\n");    	 
    	        	 seconds = (totalSearchingOptTime / 1000.0); 	        	 
    	        	 resultBuffer.append("TOTAL SEARCHING TIME (in seconds) - Blind A* : " + seconds + " sec\n");
    	        	 resultBuffer.append("AVERAGE SEARCHING TIME (in seconds) - Blind A* : " + ((double)(seconds / total_number_of_traces_analyzed)) + " sec");
    	         }
    	         
    	         resultBuffer.append("\n*******************************\n"); 
    	         
    		     try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(results_log_file, true)))) {
    		    	    out.println(resultBuffer);
    		     }catch (IOException e) {System.out.println("Il file non esiste");}	
    	         
    		    
    		     
    	    }
        	 
             catch(Exception e)
     	    {e.printStackTrace();
     	    }
        	 
         }
     });
	planner_thread.start();
}

	
	public void createResults(Trace trace, String planner_name) {
		
		//
		//Create the results to be shown by the planner
		//
		if(planner_name.equalsIgnoreCase("fast-downward"))
			new File("fast-downward/src/sas_plan");
		else if(planner_name.equalsIgnoreCase("SYMBA"))
			new File("seq-opt-symba-2/out");
    	    
   	    Vector<String> plan_vector = new Vector<String>();
   	     
   	 BufferedReader reader = null;
   	       try
   	    	 {
   	        if(planner_name.equalsIgnoreCase("fast-downward")) {
			 reader = new BufferedReader(new FileReader("fast-downward/src/sas_plan"));
   	        }
			else if(planner_name.equalsIgnoreCase("SYMBA")) {
			 reader = new BufferedReader(new FileReader("seq-opt-symba-2/out"));
			}
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
        
        
		/////////////////////////////////////////

		StringBuffer logBuffer = new StringBuffer();
	    logBuffer.append(">>>> ORIGINAL TRACE: " + trace.getOriginalTraceContent_vector()+"\n");
	    logBuffer.append(">>>> DECLARE RULES: " + Constants.getAllConstraints_vector()+"\n");
	    logBuffer.append(">>>> STARTING TRACE in PDDL: " + trace.getTraceContentWithActivitiesInstances_vector() + "\n");
	
		Vector<String> intermediate_trace_vector = new Vector<String>(trace.getTraceContentWithActivitiesInstances_vector()); 
		
		number_of_traces_aligned++;
		
		double trace_fitness = 0.0;
		int trace_alignment_cost = 0;
		int trace_reference_alignment_cost = 0;
		
		int number_of_alignments = 0;
		
		for (int index=0;index<plan_vector.size();index++) {

			int indice = index+1;
			
			if(index==0) {
			resultsArea.append("###############################\n");
			logBuffer.append("###############################\n");
			}		
			String planning_action = (String) plan_vector.elementAt(index);
			
			String[] split = planning_action.split("\\(");
			String[] split1 = split[1].split("\\)");
			
			String[] complete_action = split1[0].split(" ");
			
			String action = complete_action[0];
			
			if(action.equalsIgnoreCase("addToTheEmptyTrace")) {
				
				number_of_alignments ++;

				resultsArea.append(">>>> PLANNING ACTION # " + indice + ": " + split1[0] + "\n");
			    logBuffer.append(">>>> PLANNING ACTION # " + indice + ": " + split1[0] + "\n");				
				
				String task = complete_action[1];
				intermediate_trace_vector.insertElementAt(task, 0);

				String task_name = trace.getAssociationsToActivityInstances_Hashtable().get(task);
				trace_alignment_cost += getCostOfAdding(task_name);
				
				resultsArea.append("++++ EFFECT ON THE TRACE : add '" + task + "' to the trace\n");
			    logBuffer.append("++++ EFFECT ON THE TRACE : add '" + task + "' to the trace\n");		
			    
				resultsArea.append("^^^^ INT.TRACE: " + intermediate_trace_vector+"\n");
			    logBuffer.append("^^^^ INT.TRACE: " + intermediate_trace_vector+"\n");		
			}
			else if(action.equalsIgnoreCase("addAtTheBeginning")) {
				
				number_of_alignments ++;
				
				resultsArea.append(">>>> PLANNING ACTION # " + indice + ": " +  split1[0] + "\n");
			    logBuffer.append(">>>> PLANNING ACTION # " + indice + ": " + split1[0] + "\n");		
			    
				String task = complete_action[2];
				intermediate_trace_vector.insertElementAt(task, 0);
				
				String task_name = trace.getAssociationsToActivityInstances_Hashtable().get(task);
				trace_alignment_cost += getCostOfAdding(task_name);
				
				resultsArea.append("++++ EFFECT ON THE TRACE : add task '" + task + "' at the beginning of the trace\n");
			    logBuffer.append("++++ EFFECT ON THE TRACE : add task '" + task + "' at the beginning of the trace\n");		
			    
				resultsArea.append("^^^^ INT.TRACE: " + intermediate_trace_vector+"\n");
				logBuffer.append("^^^^ INT.TRACE: " + intermediate_trace_vector+"\n");
				
			}
			else if(action.equalsIgnoreCase("addAtTheEnd")) {
				
				number_of_alignments ++;
				
				resultsArea.append(">>>> PLANNING ACTION # " + indice + ": " + split1[0] + "\n");
			    logBuffer.append(">>>> PLANNING ACTION # " + indice + ": " + split1[0] + "\n");		
			    
				String task = complete_action[2];
				intermediate_trace_vector.insertElementAt(task,intermediate_trace_vector.size());
				
				String task_name = trace.getAssociationsToActivityInstances_Hashtable().get(task);
				trace_alignment_cost += getCostOfAdding(task_name);
				
				resultsArea.append("++++ EFFECT ON THE TRACE : add task '" + task + "' at the end of the trace\n");
			    logBuffer.append("++++ EFFECT ON THE TRACE : add task '" + task + "' at the end of the trace\n");
			    
				resultsArea.append("^^^^ INT.TRACE: " + intermediate_trace_vector+"\n");
				logBuffer.append("^^^^ INT.TRACE: " + intermediate_trace_vector+"\n");
			}
			else if(action.equalsIgnoreCase("addBetween")) {
				
				number_of_alignments ++;
				
				resultsArea.append(">>>> PLANNING ACTION # " + indice + ": " + split1[0] + "\n");				
			    logBuffer.append(">>>> PLANNING ACTION # " + indice + ": " + split1[0] + "\n");		
			    
				String task_prev = complete_action[1];
				int index_task_prev = intermediate_trace_vector.indexOf(task_prev);
				String task = complete_action[2];
				intermediate_trace_vector.insertElementAt(task,index_task_prev+1);
				
				String task_name = trace.getAssociationsToActivityInstances_Hashtable().get(task);
				trace_alignment_cost += getCostOfAdding(task_name);
				
				resultsArea.append("++++ EFFECT ON THE TRACE : add '" + task + "' after task '" + task_prev + "' \n");
				logBuffer.append("++++ EFFECT ON THE TRACE : add '" + task + "' after task '" + task_prev + "' \n");
				
				resultsArea.append("^^^^ INT.TRACE: " + intermediate_trace_vector+"\n");
				logBuffer.append("^^^^ INT.TRACE: " + intermediate_trace_vector+"\n");
			}
			
			else if(action.equalsIgnoreCase("deleteSingleTask")) {
				
				number_of_alignments ++;
				
				resultsArea.append(">>>> PLANNING ACTION # " + indice + ": " + split1[0] + "\n");
			    logBuffer.append(">>>> PLANNING ACTION # " + indice + ": " + split1[0] + "\n");		
			    
				String task = complete_action[1];
				intermediate_trace_vector.removeElement(task);
				
				String task_name = trace.getAssociationsToActivityInstances_Hashtable().get(task);
				trace_alignment_cost += getCostOfDeleting(task_name);
				
				resultsArea.append("++++ EFFECT ON THE TRACE : remove task '" + task + "' from the trace\n");
				logBuffer.append("++++ EFFECT ON THE TRACE : remove task '" + task + "' from the trace\n");
				
				resultsArea.append("^^^^ INT.TRACE: " + intermediate_trace_vector+"\n");
				logBuffer.append("^^^^ INT.TRACE: " + intermediate_trace_vector+"\n");
				
			}
			else if(action.equalsIgnoreCase("deleteAtTheBeginning")) {
				
				number_of_alignments ++;
				
				resultsArea.append(">>>> PLANNING ACTION # " + indice + ": " + split1[0] + "\n");
			    logBuffer.append(">>>> PLANNING ACTION # " + indice + ": " + split1[0] + "\n");		
			    
				String task = complete_action[1];
				intermediate_trace_vector.removeElement(task);
				
				String task_name = trace.getAssociationsToActivityInstances_Hashtable().get(task);
				trace_alignment_cost += getCostOfDeleting(task_name);
				
				resultsArea.append("++++ EFFECT ON THE TRACE : remove task '" + task + "' from the trace\n");
				logBuffer.append("++++ EFFECT ON THE TRACE : remove task '" + task + "' from the trace\n");
				
				resultsArea.append("^^^^ INT.TRACE: " + intermediate_trace_vector+"\n");
				logBuffer.append("^^^^ INT.TRACE: " + intermediate_trace_vector+"\n");
				
			}
			else if(action.equalsIgnoreCase("deleteAtTheEnd")) {
				
				number_of_alignments ++;
				
				resultsArea.append(">>>> PLANNING ACTION # " + indice + ": " + split1[0] + "\n");
			    logBuffer.append(">>>> PLANNING ACTION # " + indice + ": " + split1[0] + "\n");		
			    
				String task = complete_action[2];
				intermediate_trace_vector.removeElement(task);
				
				String task_name = trace.getAssociationsToActivityInstances_Hashtable().get(task);
				trace_alignment_cost += getCostOfDeleting(task_name);
				
				resultsArea.append("++++ EFFECT ON THE TRACE : remove task '" + task + "' from the trace\n");
				logBuffer.append("++++ EFFECT ON THE TRACE : remove task '" + task + "' from the trace\n");
				
				resultsArea.append("^^^^ INT.TRACE: " + intermediate_trace_vector+"\n");
				logBuffer.append("^^^^ INT.TRACE: " + intermediate_trace_vector+"\n");
				
			}
			else if(action.equalsIgnoreCase("deleteBetween")) {
				
				number_of_alignments ++;
				
				resultsArea.append(">>>> PLANNING ACTION # " + indice + ": " + split1[0] + "\n");
			    logBuffer.append(">>>> PLANNING ACTION # " + indice + ": " + split1[0] + "\n");		
			    
				String task = complete_action[2];
				intermediate_trace_vector.removeElement(task);
				
				String task_name = trace.getAssociationsToActivityInstances_Hashtable().get(task);
				trace_alignment_cost += getCostOfDeleting(task_name);
				
				resultsArea.append("++++ EFFECT ON THE TRACE : remove task '" + task + "' from the trace\n");
				logBuffer.append("++++ EFFECT ON THE TRACE : remove task '" + task + "' from the trace\n");
				
				resultsArea.append("^^^^ INT.TRACE: " + intermediate_trace_vector+"\n");
				logBuffer.append("^^^^ INT.TRACE: " + intermediate_trace_vector+"\n");
			}
		}
		
		Vector<String> aligned_trace_vector = new Vector<String>();
		for(int index = 0;index<intermediate_trace_vector.size();index++) {
			
			String el = (String) intermediate_trace_vector.elementAt(index);
			aligned_trace_vector.addElement(el.substring(0,el.length()-1));
			
		}
		
		trace_reference_alignment_cost += getCostOfRemovingTrace(trace.getOriginalTraceContent_vector()) + getCostOfCreatingTrace(aligned_trace_vector);
		trace_fitness = 1 - ((double)trace_alignment_cost/trace_reference_alignment_cost);
		log_fitness += trace_fitness;
		
		
		//Update the .csv File	
	     csvBuffer.append(number_of_alignments + "," + trace_fitness);
	     
		resultsArea.append("###############################\n");
		resultsArea.append("<<<< ALIGNED PDDL TRACE : " + intermediate_trace_vector +"\n");
		resultsArea.append("<<<< ALIGNED TRACE : " + aligned_trace_vector +"\n\n");
		resultsArea.append("<<<< TRACE FITNESS : " + trace_fitness + "\n\n");
		
		logBuffer.append("###############################\n");
		logBuffer.append("<<<< ALIGNED PDDL TRACE : " + intermediate_trace_vector +"\n");
		logBuffer.append("<<<< ALIGNED TRACE : " + aligned_trace_vector + "\n");
		logBuffer.append("<<<< TRACE FITNESS : " + trace_fitness + "\n\n");
	    
	     try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(results_log_file, true)))) {
	    	    out.println(logBuffer);
	     }catch (IOException e) {System.out.println("Il file non esiste");}		

	}

	public JTextArea getResultsArea() {
		return resultsArea;
	}

	public JButton getOkButton() {
		return okButton;
	}

	private StringBuffer createDomain(Trace trace) {
		
		StringBuffer PDDL_domain_buffer = new StringBuffer();
		StringBuffer PDDL_rules_buffer = new StringBuffer();
		
		PDDL_domain_buffer.append("(define (domain Mining)\n");
		PDDL_domain_buffer.append("(:requirements :derived-predicates :typing :equality)\n");
		PDDL_domain_buffer.append("(:types ");
		
		for(int i=0;i<trace.getTraceAlphabetWithMissingActivitiesOfTheConstraints_vector().size();i++) {
			PDDL_domain_buffer.append(trace.getTraceAlphabetWithMissingActivitiesOfTheConstraints_vector().elementAt(i) + " ");
		}
		
		PDDL_domain_buffer.append("- task)\n\n");		
		
		PDDL_domain_buffer.append(";;\n");
		PDDL_domain_buffer.append(";;Basic Predicates\n");	
		PDDL_domain_buffer.append(";;\n");

		PDDL_domain_buffer.append("(:predicates\n");
		PDDL_domain_buffer.append("(pre ?x - task ?y - task)\n");				 
		PDDL_domain_buffer.append("(traced ?x - task)\n");					
		PDDL_domain_buffer.append("(first_task_of_the_trace ?x - task)\n");					
		PDDL_domain_buffer.append("(last_task_of_the_trace ?x - task)\n\n");	
		
		PDDL_domain_buffer.append(";;\n");
		PDDL_domain_buffer.append(";;DECLARE LTL rules\n");	
		PDDL_domain_buffer.append(";;\n");

		int number_of_response_rules = 0;
		int number_of_precedence_rules = 0;
		Vector<String> response_between_rules_vector = new Vector<String>();
		Vector<String> precedence_between_rules_vector = new Vector<String>();
		
		for(int j=0;j<Constants.getAllConstraints_vector().size();j++) {
			
			String DECLARE_rule = (String) Constants.getAllConstraints_vector().elementAt(j);
			
			String single_rule_array[] = DECLARE_rule.split("\\(");
			
			String declare_rule_name = single_rule_array[0];  
			
			//
			//DECLARE RULE: EXISTENCE(A) --- (EVENTUALLY A) - An instance of the task A must occur at least once in the trace.
			//
			if(declare_rule_name.equalsIgnoreCase("existence")) {				
				String[] task = single_rule_array[1].split("\\)");
				PDDL_domain_buffer.append("(existence-" + task[0] + ")\n");
				
				Constants.getPDDLActivitiesVector().addElement("existence-" + task[0]);
				
				PDDL_rules_buffer.append(";;\n");
				PDDL_rules_buffer.append(";;EXISTENCE-" + task[0] + " - (EVENTUALLY " + task[0] + ") - An instance of the task " + task[0] + " must occur at least once in the trace\n");				
				PDDL_rules_buffer.append(";;\n\n");

				PDDL_rules_buffer.append("(:derived (existence-" + task[0] + ")\n");
				PDDL_rules_buffer.append("(exists (?t - " + task[0] + ") (traced ?t)))\n\n");				
			}
			//
			//DECLARE RULE: ABSENCE(A) --- (NOT EVENTUALLY A) - No instance of the task A can occur in the trace (A NEVER occurs in the trace).
			//
			else if(declare_rule_name.equalsIgnoreCase("absence")) {
				String[] task = single_rule_array[1].split("\\)");
				PDDL_domain_buffer.append("(absence-" + task[0] + ")\n");
				
				Constants.getPDDLActivitiesVector().addElement("absence-" + task[0]);
				
				PDDL_rules_buffer.append(";;\n");
				PDDL_rules_buffer.append(";;ABSENCE-" + task[0] + " - (NOT (EVENTUALLY " + task[0] + ")) - " + task[0] + " never occur in the trace\n");				
				PDDL_rules_buffer.append(";;\n\n");

				PDDL_rules_buffer.append("(:derived (absence-" + task[0] + ")\n");
				PDDL_rules_buffer.append("(not (exists (?t - " + task[0] + ") (traced ?t))))\n\n");
			}
			//
			//DECLARE RULE: INIT(A) --- A - An instance of the task A must occur in the first position of the trace.
			//
			if(declare_rule_name.equalsIgnoreCase("init")) {				
				String[] task = single_rule_array[1].split("\\)");
				PDDL_domain_buffer.append("(init-" + task[0] + ")\n");
				
				Constants.getPDDLActivitiesVector().addElement("init-" + task[0]);
				
				PDDL_rules_buffer.append(";;\n");
				PDDL_rules_buffer.append(";;INIT-" + task[0] + " - (INIT " + task[0] + ") - An instance of the task " + task[0] + " must occur in the first position of the trace\n");				
				PDDL_rules_buffer.append(";;\n\n");

				PDDL_rules_buffer.append("(:derived (init-" + task[0] + ")\n");
				PDDL_rules_buffer.append("(exists (?t - " + task[0] + ") (first_task_of_the_trace ?t)))\n\n");				
			}
			//
			//DECLARE RULE: LAST(A) --- A - An instance of the task A must occur in the last position of the trace.
			//
			if(declare_rule_name.equalsIgnoreCase("last")) {				
				String[] task = single_rule_array[1].split("\\)");
				PDDL_domain_buffer.append("(last-" + task[0] + ")\n");
				
				Constants.getPDDLActivitiesVector().addElement("last-" + task[0]);
				
				PDDL_rules_buffer.append(";;\n");
				PDDL_rules_buffer.append(";;LAST-" + task[0] + " - (LAST " + task[0] + ") - An instance of the task " + task[0] + " must occur in the last position of the trace\n");				
				PDDL_rules_buffer.append(";;\n\n");

				PDDL_rules_buffer.append("(:derived (init-" + task[0] + ")\n");
				PDDL_rules_buffer.append("(exists (?t - " + task[0] + ") (last_task_of_the_trace ?t)))\n\n");				
			}			
			//
			//DECLARE RULE: CHOICE(A,B) --- (EVENTUALLY A OR EVENTUALLY B) - A or B occur eventually in the trace. The formula is TRUE also if they both occur in the trace, while it is FALSE if no one of them occurs in the trace.
			//
			else if(declare_rule_name.equalsIgnoreCase("choice")) {
				String[] tasks = single_rule_array[1].split("\\)");
				String[] task = tasks[0].split(",");
				PDDL_domain_buffer.append("(choice-" + task[0] + "-" + task[1] + ")\n");
				
				Constants.getPDDLActivitiesVector().addElement("choice-" + task[0] + "-" + task[1]);
				
				PDDL_rules_buffer.append(";;\n");
				PDDL_rules_buffer.append(";;CHOICE-" + task[0] + "-" + task[1] + " - (EVENTUALLY " + task[0] + " OR EVENTUALLY " + task[1] + ") - " + task[0] + " or " + task[1] + " occur eventually in the trace. The formula is TRUE also if they both occur in the trace\n");				
				PDDL_rules_buffer.append(";;\n\n");
				
				PDDL_rules_buffer.append("(:derived (choice-" + task[0] + "-" + task[1] + ")\n");
				PDDL_rules_buffer.append("(or (exists (?ta - " + task[0] + ") (traced ?ta))\n");
				PDDL_rules_buffer.append("(exists (?tb - " + task[1] + ") (traced ?tb))))\n\n");
			}
			//
			//DECLARE RULE: EXCLUSIVE_CHOICE(A,B) --- (EVENTUALLY A OR EVENTUALLY B) AND (NOT (EVENTUALLY A AND EVENTUALLY B)) - Only instances of A or only instances of B can occur eventually in the trace (but not together). The formula is FALSE also if no instance of A and if no instance of B occur in the trace.
			//			
			else if(declare_rule_name.equalsIgnoreCase("exclusive choice")) {
				String[] tasks = single_rule_array[1].split("\\)");
				String[] task = tasks[0].split(",");
				PDDL_domain_buffer.append("(ex_choice-" + task[0] + "-" + task[1] + ")\n");				
				
				Constants.getPDDLActivitiesVector().addElement("ex_choice-" + task[0] + "-" + task[1]);
				
				PDDL_rules_buffer.append(";;\n");
				PDDL_rules_buffer.append(";;EXCLUSIVE_CHOICE-" + task[0] + "-" + task[1] + " - (EVENTUALLY " + task[0] + " OR EVENTUALLY " + task[1] + ") AND (NOT (EVENTUALLY " + task[0] + " AND EVENTUALLY " + task[1] + ")) - Only instances of " + task[0] + " or only instances of " + task[1] + " can occur eventually in the trace (but not together). The formula is FALSE also if no instance of A and if no instance of B occur in the trace.\n");				
				PDDL_rules_buffer.append(";;\n\n");
				
				PDDL_rules_buffer.append("(:derived (ex_choice-" + task[0] + "-" + task[1] + ")\n");
				PDDL_rules_buffer.append("(and \n");
				PDDL_rules_buffer.append("(exists (?ta - " + task[0] + " ?tb - " + task[1] + ") (or (traced ?ta) (traced ?tb)))\n");
				PDDL_rules_buffer.append("(not (exists (?ta - " + task[0] + " ?tb - " + task[1] + ") (and (traced ?ta) (traced ?tb))))))\n\n");				
			}
			//
			//DECLARE RULE: RESPONDED_EXISTENCE(A,B) --- (EVENTUALLY A --> EVENTUALLY B) - If an instance of A occurs in the trace, then an instance of B must occur in the trace as well (before or after A, the order here is not important).
			//		
			else if(declare_rule_name.equalsIgnoreCase("responded existence")) {
				String[] tasks = single_rule_array[1].split("\\)");
				String[] task = tasks[0].split(",");
				PDDL_domain_buffer.append("(resp_existence-" + task[0] + "-" + task[1] + ")\n");				
				
				Constants.getPDDLActivitiesVector().addElement("resp_existence-" + task[0] + "-" + task[1]);
				
				PDDL_rules_buffer.append(";;\n");
				PDDL_rules_buffer.append(";;RESPONDED_EXISTENCE-" + task[0] + "-" + task[1] + " - (EVENTUALLY " + task[0] + " --> EVENTUALLY " + task[1] + ") - If an instance of " + task[0] + " occurs in the trace, then an instance of " + task[1] + " must occur in the trace as well (before or after " + task[0] + ", the order here is not important). The formula is TRUE also if " + task[0] + " never appears in the trace.\n");				
				PDDL_rules_buffer.append(";;\n\n");
				
				PDDL_rules_buffer.append("(:derived (resp_existence-" + task[0] + "-" + task[1] + ")\n");
				
				PDDL_rules_buffer.append("(forall (?ta - " + task[0] + ")\n");
				PDDL_rules_buffer.append("(or (not (traced ?ta))\n");
				//PDDL_rules_buffer.append("(and (traced ?ta)\n");
				PDDL_rules_buffer.append("(exists (?tb - " + task[1] + ") (traced ?tb)))))\n\n");
			}
			//
			//DECLARE RULE: NOT RESPONDED EXISTENCE(A,B) --- EVENTUALLY A IMPLIES NOT EVENTUALLY B - If an instance of A occurs in the trace, then no instance of B can occur in the trace.
			//					
			else if(declare_rule_name.equalsIgnoreCase("not responded existence")) {
				String[] tasks = single_rule_array[1].split("\\)");
				String[] task = tasks[0].split(",");
				
				PDDL_domain_buffer.append("(not_resp_existence-" + task[0] + "-" + task[1] + ")\n");	
				
				Constants.getPDDLActivitiesVector().addElement("not_resp_existence-" + task[0] + "-" + task[1]);
				
				PDDL_rules_buffer.append(";;\n");
				PDDL_rules_buffer.append(";;NOT RESPONDED EXISTENCE-" + task[0] + "-" + task[1] + " - (EVENTUALLY " + task[0] + " --> EVENTUALLY " + task[1] + ") - If an instance of " + task[0] + " occurs in the trace, then no instance of " + task[1] + " can occur in the trace.\n");				
				PDDL_rules_buffer.append(";;\n\n");
				
				PDDL_rules_buffer.append("(:derived (not_resp_existence-" + task[0] + "-" + task[1] + ")\n");
				PDDL_rules_buffer.append("(forall (?ta - " + task[0] + ")\n");
				PDDL_rules_buffer.append("(or (not (traced ?ta))\n");
				PDDL_rules_buffer.append("(not (exists (?tb - " + task[1] + ") (traced ?tb))))))\n\n");
			}		
			//
			//DECLARE RULE: CO-EXISTENCE(A,B) --- (EVENTUALLY A --> EVENTUALLY B) AND (EVENTUALLY B --> EVENTUALLY A) - If an instance of A/B occurs in the trace, then an instance of B/A must occur in the trace as well (before or after A/B, the order here is not important). Basically, the rule is true if A and B occurs at least once in the trace, or if they never occur.
			//		
			else if(declare_rule_name.equalsIgnoreCase("co-existence")) {
				String[] tasks = single_rule_array[1].split("\\)");
				String[] task = tasks[0].split(",");
				PDDL_domain_buffer.append("(co_existence-" + task[0] + "-" + task[1] + ")\n");				
				
				Constants.getPDDLActivitiesVector().addElement("co_existence-" + task[0] + "-" + task[1]);
				
				PDDL_rules_buffer.append(";;\n");
				PDDL_rules_buffer.append(";;CO_EXISTENCE-" + task[0] + "-" + task[1] + " - (EVENTUALLY " + task[0] + " --> EVENTUALLY " + task[1] + ") AND (EVENTUALLY " + task[1] + " --> EVENTUALLY " + task[0] + ") - If an instance of " + task[0] + " (or " + task[1] + ") occurs in the trace, then an instance of " + task[1] + " (or " + task[0] + ") must occur in the trace as well (before or after " + task[0] + " (or " + task[1] + "), the order here is not important). The formula is TRUE also if " + task[0] + " and " + task[1] + " never occur in the trace.\n");				
				PDDL_rules_buffer.append(";;\n\n");
				
				PDDL_rules_buffer.append("(:derived (co_existence-" + task[0] + "-" + task[1] + ")\n");
				PDDL_rules_buffer.append("(or\n");
				PDDL_rules_buffer.append("(forall (?ta - " + task[0] + " ?tb - " + task[1] + ")\n");				
				PDDL_rules_buffer.append("(and (not (traced ?ta)) (not (traced ?tb))))\n");
				PDDL_rules_buffer.append("(exists (?ta - " + task[0] + " ?tb - " + task[1] + ")\n");		
				PDDL_rules_buffer.append("(and (traced ?ta) (traced ?tb)))))\n\n");
			}
			//
			//DECLARE RULE: NOT CO-EXISTENCE(A,B) --- EVENTUALLY A IMPLIES NOT EVENTUALLY B - If an instance of A occurs in the trace, then no instance of B can occur in the trace. Basically, it is equivalent to the NOT RESPONDED EXISTENCE rule.
			//					
			else if(declare_rule_name.equalsIgnoreCase("not co-existence")) {
				String[] tasks = single_rule_array[1].split("\\)");
				String[] task = tasks[0].split(",");
				
				PDDL_domain_buffer.append("(not_co_existence-" + task[0] + "-" + task[1] + ")\n");	
				
				Constants.getPDDLActivitiesVector().addElement("not_co_existence-" + task[0] + "-" + task[1]);
				
				PDDL_rules_buffer.append(";;\n");
				PDDL_rules_buffer.append(";;NOT CO-EXISTENCE-" + task[0] + "-" + task[1] + " - (EVENTUALLY " + task[0] + " --> EVENTUALLY " + task[1] + ") - If an instance of " + task[0] + " occurs in the trace, then no instance of " + task[1] + " can occur in the trace. Basically, it is equivalent to the NOT RESPONDED EXISTENCE rule.\n");				
				PDDL_rules_buffer.append(";;\n\n");
				
				
				PDDL_rules_buffer.append("(:derived (not_co_existence-" + task[0] + "-" + task[1] + ")\n");
				PDDL_rules_buffer.append("(forall (?ta - " + task[0] + ")\n");
				PDDL_rules_buffer.append("(or (not (traced ?ta))\n");
				PDDL_rules_buffer.append("(not (exists (?tb - " + task[1] + ") (traced ?tb))))))\n\n");
			
				/*
				PDDL_rules_buffer.append("(:derived (not_co_existence-" + task[0] + "-" + task[1] + ")\n");
				PDDL_rules_buffer.append("(forall (?ta - " + task[0] + " ?tb - " + task[1] + ")\n");
				PDDL_rules_buffer.append("(or (not (traced ?ta)) (not (traced ?tb)))))\n\n");
			    */
			}		
			
			
			//
			//DECLARE RULE: RESPONSE(A,B) --- (GLOBALLY(A --> EVENTUALLY B) - If an instance of A occurs in the trace, then an instance of B must occur somewhere in the trace after A.
			//
			else if(declare_rule_name.equalsIgnoreCase("response")) {
				String[] tasks = single_rule_array[1].split("\\)");
				String[] task = tasks[0].split(",");
				
				if(number_of_response_rules==0) {
					/*
					PDDL_domain_buffer.append("(response ?t1 - task ?t2 - task)\n");
					
					PDDL_rules_buffer.append(";;\n");
					PDDL_rules_buffer.append(";;GENERAL RESPONSE RULE.\n");				
					PDDL_rules_buffer.append(";;\n\n");
					
					PDDL_rules_buffer.append("(:derived (response ?t1 - task ?t2 - task)\n");
					PDDL_rules_buffer.append("(or (not (traced ?t1))\n");				
					PDDL_rules_buffer.append("(pre ?t1 ?t2)\n");		
					PDDL_rules_buffer.append("(exists (?t3 - task) (and (pre ?t1 ?t3) (response ?t3 ?t2)))))\n\n");
					*/
					
					PDDL_domain_buffer.append("(response ?t1 - task ?t2 - task)\n");
					PDDL_domain_buffer.append("(rstmp1 ?t1 - task ?t2 - task)\n");
					PDDL_domain_buffer.append("(rstmp2 ?t1 - task ?t2 - task ?t3 - task)\n");
					
					PDDL_rules_buffer.append(";;\n");
					PDDL_rules_buffer.append(";;GENERAL RESPONSE RULE.\n");				
					PDDL_rules_buffer.append(";;\n\n");
					
					PDDL_rules_buffer.append("(:derived (response ?t1 - task ?t2 - task)\n");
					PDDL_rules_buffer.append("(or (not (traced ?t1))\n");				
					PDDL_rules_buffer.append("(pre ?t1 ?t2)\n");		
					PDDL_rules_buffer.append("(rstmp1 ?t1 ?t2)))\n\n");
					
					PDDL_rules_buffer.append("(:derived (rstmp1 ?t1 - task ?t2 - task)\n");
					PDDL_rules_buffer.append("(exists (?t3 - task) (rstmp2 ?t1 ?t2 ?t3)))\n\n");
										
					PDDL_rules_buffer.append("(:derived (rstmp2 ?t1 - task ?t2 - task ?t3 - task)\n");
					PDDL_rules_buffer.append("(and (pre ?t1 ?t3) (response ?t3 ?t2)))\n\n");	
					
				}
				
				PDDL_domain_buffer.append("(response-" + task[0] + "-" + task[1] + ")\n");			
				
				Constants.getPDDLActivitiesVector().addElement("response-" + task[0] + "-" + task[1]);
				
				PDDL_rules_buffer.append(";;\n");
				PDDL_rules_buffer.append(";;RESPONSE-" + task[0] + "-" + task[1] + " - (GLOBALLY(" + task[0] + " --> EVENTUALLY " + task[1] + ")) - If an instance of " + task[0] + " occurs in the trace, then an instance of " + task[1] + " must occur in the trace after " + task[0] + ".\n");				
				PDDL_rules_buffer.append(";;\n\n");
				
				PDDL_rules_buffer.append("(:derived (response-" + task[0] + "-" + task[1] + ")\n");
				PDDL_rules_buffer.append("(forall (?ta - " + task[0] + ")\n");
				PDDL_rules_buffer.append("(or (not (traced ?ta))\n\n");
				PDDL_rules_buffer.append("(exists (?tb - " + task[1] + ") (response ?ta ?tb)))))\n\n");

				number_of_response_rules++;
			}
			//
			//DECLARE RULE: PRECEDENCE(A,B) --- (NOT B WEAK UNTIL A) - If an instance of B occurs in the trace, then an instance of A must occur before that instance of B in the trace.
			//
			else if(declare_rule_name.equalsIgnoreCase("precedence")) {
				String[] tasks = single_rule_array[1].split("\\)");
				String[] task = tasks[0].split(",");
				
				if(number_of_precedence_rules==0) {
					
					/*
					PDDL_domain_buffer.append("(precedence ?t1 - task ?t2 - task)\n");
					
					PDDL_rules_buffer.append(";;\n");
					PDDL_rules_buffer.append(";;GENERAL PRECEDENCE RULE.\n");				
					PDDL_rules_buffer.append(";;\n\n");
					
					PDDL_rules_buffer.append("(:derived (precedence ?t1 - task ?t2 - task)\n");
					PDDL_rules_buffer.append("(or (not (traced ?t2))\n");				
					PDDL_rules_buffer.append("(pre ?t1 ?t2)\n");		
					PDDL_rules_buffer.append("(exists (?t3 - task) (and (pre ?t3 ?t2) (precedence ?t1 ?t3)))))\n\n");
					*/
					
					PDDL_domain_buffer.append("(precedence ?t1 - task ?t2 - task)\n");
					PDDL_domain_buffer.append("(prtmp1 ?t1 - task ?t2 - task)\n");
					PDDL_domain_buffer.append("(prtmp2 ?t1 - task ?t2 - task ?t3 - task)\n");
										
					PDDL_rules_buffer.append(";;\n");
					PDDL_rules_buffer.append(";;GENERAL PRECEDENCE RULE.\n");				
					PDDL_rules_buffer.append(";;\n\n");
					
					PDDL_rules_buffer.append("(:derived (precedence ?t1 - task ?t2 - task)\n");
					PDDL_rules_buffer.append("(or (not (traced ?t2))\n");				
					PDDL_rules_buffer.append("(pre ?t1 ?t2)\n");		
					PDDL_rules_buffer.append("(prtmp1 ?t1 ?t2)))\n\n");
					
					PDDL_rules_buffer.append("(:derived (prtmp1 ?t1 - task ?t2 - task)\n");
					PDDL_rules_buffer.append("(exists (?t3 - task) (prtmp2 ?t1 ?t2 ?t3)))\n\n");
					
					PDDL_rules_buffer.append("(:derived (prtmp2 ?t1 - task ?t2 - task ?t3 - task)\n");
					PDDL_rules_buffer.append("(and (pre ?t3 ?t2) (precedence ?t1 ?t3)))\n\n");					
				
				}
				
				PDDL_domain_buffer.append("(precedence-" + task[0] + "-" + task[1] + ")\n");			
				
				Constants.getPDDLActivitiesVector().addElement("precedence-" + task[0] + "-" + task[1]);
				
				PDDL_rules_buffer.append(";;\n");
				PDDL_rules_buffer.append(";;PRECEDENCE-" + task[0] + "-" + task[1] + " - (NOT " + task[1] + " WEAK UNTIL " + task[0] + ") - If an instance of " + task[1] + " occurs in the trace, then an instance of " + task[0] + " must occur before that instance of " + task[0] + " in the trace.\n");				
				PDDL_rules_buffer.append(";;\n\n");
				
				PDDL_rules_buffer.append("(:derived (precedence-" + task[0] + "-" + task[1] + ")\n");
				PDDL_rules_buffer.append("(forall (?tb - " + task[1] + ")\n");
				PDDL_rules_buffer.append("(exists (?ta - " + task[0] + ") (precedence ?ta ?tb))))\n\n");

				number_of_precedence_rules++;
			}	
			//
			//DECLARE RULE: SUCCESSION(A,B) --- (GLOBALLY(A --> EVENTUALLY B)) AND (NOT B WEAK UNTIL A) - If an instance of A occurs in the trace, then an instance of B must occur somewhere in the trace after A, and if an instance of B occurs in the trace, then an instance of A must occur before that instance of B in the trace.
			//
			else if(declare_rule_name.equalsIgnoreCase("succession")) {
				String[] tasks = single_rule_array[1].split("\\)");
				String[] task = tasks[0].split(",");
			
				if(number_of_response_rules==0) {
					PDDL_domain_buffer.append("(response ?t1 - task ?t2 - task)\n");
					
					PDDL_rules_buffer.append(";;\n");
					PDDL_rules_buffer.append(";;GENERAL RESPONSE RULE.\n");				
					PDDL_rules_buffer.append(";;\n\n");
					
					PDDL_rules_buffer.append("(:derived (response ?t1 - task ?t2 - task)\n");
					PDDL_rules_buffer.append("(or (not (traced ?t1))\n");				
					PDDL_rules_buffer.append("(pre ?t1 ?t2)\n");		
					PDDL_rules_buffer.append("(exists (?t3 - task) (and (pre ?t1 ?t3) (response ?t3 ?t2)))))\n\n");
				}
				
				if(number_of_precedence_rules==0) {
					PDDL_domain_buffer.append("(precedence ?t1 - task ?t2 - task)\n");
					
					PDDL_rules_buffer.append(";;\n");
					PDDL_rules_buffer.append(";;GENERAL PRECEDENCE RULE.\n");				
					PDDL_rules_buffer.append(";;\n\n");
					
					PDDL_rules_buffer.append("(:derived (precedence ?t1 - task ?t2 - task)\n");
					PDDL_rules_buffer.append("(or (not (traced ?t2))\n");				
					PDDL_rules_buffer.append("(pre ?t1 ?t2)\n");		
					PDDL_rules_buffer.append("(exists (?t3 - task) (and (pre ?t3 ?t2) (precedence ?t1 ?t3)))))\n\n");
				}
				
				PDDL_domain_buffer.append("(succession-" + task[0] + "-" + task[1] + ")\n");			
				
				Constants.getPDDLActivitiesVector().addElement("succession-" + task[0] + "-" + task[1]);
				
				PDDL_rules_buffer.append(";;\n");
				PDDL_rules_buffer.append(";;SUCCESSION-" + task[0] + "-" + task[1] + " - (GLOBALLY(" + task[0] + " --> EVENTUALLY " + task[1] + ")) AND (NOT " + task[1] + " WEAK UNTIL " + task[0] + ") - If an instance of " + task[0] + " occurs in the trace, then an instance of " + task[1] + " must occur in the trace after " + task[0] + ", and if an instance of " + task[1] + " occurs in the trace, then an instance of " + task[0] + " must occur before that instance of " + task[0] + " in the trace.\n");				
				PDDL_rules_buffer.append(";;\n\n");
				
				PDDL_rules_buffer.append("(:derived (succession-" + task[0] + "-" + task[1] + ")\n");
				PDDL_rules_buffer.append("(and\n");
				PDDL_rules_buffer.append("(forall (?ta - " + task[0] + ")\n");
				PDDL_rules_buffer.append("(exists (?tb - " + task[1] + ") (response ?ta ?tb)))\n");
				PDDL_rules_buffer.append("(forall (?tb - " + task[1] + ")\n");
				PDDL_rules_buffer.append("(exists (?ta - " + task[0] + ") (precedence ?ta ?tb)))))\n\n");
				
				number_of_response_rules++;
				number_of_precedence_rules++;
			}
			//
			//DECLARE RULE: CHAIN RESPONSE(A,B) --- GLOBALLY(A IMPLIES NEXT B) - If an instance of A occurs in the trace, then an instance of B must occur in the next step of the trace.
			//					
			else if(declare_rule_name.equalsIgnoreCase("chain response")) {
				String[] tasks = single_rule_array[1].split("\\)");
				String[] task = tasks[0].split(",");
				
				PDDL_domain_buffer.append("(chain_response-" + task[0] + "-" + task[1] + ")\n");	
				
				Constants.getPDDLActivitiesVector().addElement("chain_response-" + task[0] + "-" + task[1]);
				
				PDDL_rules_buffer.append(";;\n");
				PDDL_rules_buffer.append(";;CHAIN RESPONSE-" + task[0] + "-" + task[1] + " - (GLOBALLY(" + task[0] + " --> NEXT " + task[1] + ")) - If an instance of " + task[0] + " occurs in the trace, then an instance of " + task[1] + " must occur in the next step of the trace after " + task[0] + ".\n");				
				PDDL_rules_buffer.append(";;\n\n");
				
				PDDL_rules_buffer.append("(:derived (chain_response-" + task[0] + "-" + task[1] + ")\n");
				PDDL_rules_buffer.append("(forall (?ta - " + task[0] + ")\n");
				PDDL_rules_buffer.append("(or (not (traced ?ta))\n");
				PDDL_rules_buffer.append("(exists (?tb - " + task[1] + ") (pre ?ta ?tb)))))\n\n");
			}		
			//
			//DECLARE RULE: CHAIN PRECEDENCE(A,B) --- GLOBALLY(NEXT B IMPLIES A) - If an instance of B occurs in the trace, then an instance of A must occur in the previous step of the trace.
			//					
			else if(declare_rule_name.equalsIgnoreCase("chain precedence")) {
				String[] tasks = single_rule_array[1].split("\\)");
				String[] task = tasks[0].split(",");
				
				PDDL_domain_buffer.append("(chain_precedence-" + task[0] + "-" + task[1] + ")\n");	
				
				Constants.getPDDLActivitiesVector().addElement("chain_precedence-" + task[0] + "-" + task[1]);
				
				PDDL_rules_buffer.append(";;\n");
				PDDL_rules_buffer.append(";;CHAIN PRECEDENCE-" + task[0] + "-" + task[1] + " - (GLOBALLY(NEXT" + task[1] + " --> " + task[0] + ")) - If an instance of " + task[1] + " occurs in the trace, then an instance of " + task[0] + " must occur in the previous step of the trace.\n");				
				PDDL_rules_buffer.append(";;\n\n");
				
				PDDL_rules_buffer.append("(:derived (chain_precedence-" + task[0] + "-" + task[1] + ")\n");
				PDDL_rules_buffer.append("(forall (?tb - " + task[1] + ")\n");
				PDDL_rules_buffer.append("(or (not (traced ?tb))\n");
				PDDL_rules_buffer.append("(exists (?ta - " + task[0] + ") (pre ?ta ?tb)))))\n\n");
			}	
			//
			//DECLARE RULE: CHAIN SUCCESSION(A,B) --- GLOBALLY(A IMPLIES NEXT B) AND GLOBALLY(NEXT B IMPLIES A) - If an instance of A occurs in the trace, then an instance of B must occur in the next step of the trace. Furthermore, if an instance of B occurs in the trace, then an instance of A must occur in the previous step of the trace.
			//					
			else if(declare_rule_name.equalsIgnoreCase("chain succession")) {
				String[] tasks = single_rule_array[1].split("\\)");
				String[] task = tasks[0].split(",");
				
				PDDL_domain_buffer.append("(chain_succession-" + task[0] + "-" + task[1] + ")\n");	
				
				Constants.getPDDLActivitiesVector().addElement("chain_succession-" + task[0] + "-" + task[1]);
				
				PDDL_rules_buffer.append(";;\n");
				PDDL_rules_buffer.append(";;CHAIN SUCCESSION-" + task[0] + "-" + task[1] + " - (GLOBALLY(" + task[0] + " --> NEXT " + task[1] + ")) AND (GLOBALLY(NEXT " + task[1] + " IMPLIES " + task[0] + ")) - If an instance of " + task[0] + " occurs in the trace, then an instance of " + task[1] + " must occur in the next step of the trace after " + task[0] + ". Furthermore, if an instance of B occurs in the trace, then an instance of A must occur in the previous step of the trace.\n");				
				PDDL_rules_buffer.append(";;\n\n");
				
				/*
				PDDL_rules_buffer.append("(:derived (chain_succession-" + task[0] + "-" + task[1] + ")\n");
				PDDL_rules_buffer.append("(and\n");
				PDDL_rules_buffer.append("(forall (?ta - " + task[0] + ")\n");
				PDDL_rules_buffer.append("(or (not (traced ?ta))\n");
				PDDL_rules_buffer.append("(exists (?tb - " + task[1] + ") (pre ?ta ?tb))))\n");
				PDDL_rules_buffer.append("(forall (?tb - " + task[1] + ")\n");
				PDDL_rules_buffer.append("(or (not (traced ?tb))\n");
				PDDL_rules_buffer.append("(exists (?ta - " + task[0] + ") (pre ?ta ?tb))))))\n\n");
				*/
				
				PDDL_rules_buffer.append("(:derived (chain_succession-" + task[0] + "-" + task[1] + ")\n");
				PDDL_rules_buffer.append("(forall (?ta - " + task[0] + "?tb - " + task[1] + ")\n");
				PDDL_rules_buffer.append("(or (and (not (traced ?ta)) (not (traced ?tb)))\n");
				PDDL_rules_buffer.append("(pre ?ta ?tb) (pre ?tb ?ta))))\n");
			}	
			//
			//DECLARE RULE: NOT RESPONSE(A,B) --- (GLOBALLY(A --> NOT(EVENTUALLY B)) - If an instance of A occurs in the trace, then no instance of B can occur somewhere in the trace after A.
			//
			else if(declare_rule_name.equalsIgnoreCase("not response")) {
				String[] tasks = single_rule_array[1].split("\\)");
				String[] task = tasks[0].split(",");
				
				if(number_of_response_rules==0) {
					PDDL_domain_buffer.append("(response ?t1 - task ?t2 - task)\n");
					PDDL_domain_buffer.append("(rstmp1 ?t1 - task ?t2 - task)\n");
					PDDL_domain_buffer.append("(rstmp2 ?t1 - task ?t2 - task ?t3 - task)\n");
					
					PDDL_rules_buffer.append(";;\n");
					PDDL_rules_buffer.append(";;GENERAL RESPONSE RULE.\n");				
					PDDL_rules_buffer.append(";;\n\n");
					
					PDDL_rules_buffer.append("(:derived (response ?t1 - task ?t2 - task)\n");
					PDDL_rules_buffer.append("(or (not (traced ?t1))\n");				
					PDDL_rules_buffer.append("(pre ?t1 ?t2)\n");		
					PDDL_rules_buffer.append("(rstmp1 ?t1 ?t2)))\n\n");
					
					PDDL_rules_buffer.append("(:derived (rstmp1 ?t1 - task ?t2 - task)\n");
					PDDL_rules_buffer.append("(exists (?t3 - task) (rstmp2 ?t1 ?t2 ?t3)))\n\n");
										
					PDDL_rules_buffer.append("(:derived (rstmp2 ?t1 - task ?t2 - task ?t3 - task)\n");
					PDDL_rules_buffer.append("(and (pre ?t1 ?t3) (response ?t3 ?t2)))\n\n");					

				}
				
				PDDL_domain_buffer.append("(not_response-" + task[0] + "-" + task[1] + ")\n");			
				
				Constants.getPDDLActivitiesVector().addElement("not_response-" + task[0] + "-" + task[1]);
				
				PDDL_rules_buffer.append(";;\n");
				PDDL_rules_buffer.append(";;NOT RESPONSE-" + task[0] + "-" + task[1] + " - (GLOBALLY(" + task[0] + " --> NOT(EVENTUALLY " + task[1] + "))) - If an instance of " + task[0] + " occurs in the trace, then no instance of " + task[1] + " can occur in the trace after " + task[0] + ".\n");				
				PDDL_rules_buffer.append(";;\n\n");
				
				PDDL_rules_buffer.append("(:derived (not_response-" + task[0] + "-" + task[1] + ")\n");
				PDDL_rules_buffer.append("(forall (?ta - " + task[0] + ")\n");
				PDDL_rules_buffer.append("(or (not (traced ?ta))\n");
				PDDL_rules_buffer.append("(not (exists (?tb - " + task[1] + ") (response ?ta ?tb))))))\n\n");

				number_of_response_rules++;
			}
			//
			//DECLARE RULE: NOT PRECEDENCE(A,B) --- (GLOBALLY(A --> NOT(EVENTUALLY B)) - It is equivalent to the "NOT RESPONSE" rule. If an instance of A occurs in the trace, then no instance of B can occur somewhere in the trace after A. This means also that no instance of A can appear before B.
			//
			else if(declare_rule_name.equalsIgnoreCase("not precedence")) {
				String[] tasks = single_rule_array[1].split("\\)");
				String[] task = tasks[0].split(",");
				
				if(number_of_precedence_rules==0) {
					PDDL_domain_buffer.append("(precedence ?t1 - task ?t2 - task)\n");
					
					PDDL_rules_buffer.append(";;\n");
					PDDL_rules_buffer.append(";;GENERAL PRECEDENCE RULE.\n");				
					PDDL_rules_buffer.append(";;\n\n");
					
					PDDL_rules_buffer.append("(:derived (precedence ?t1 - task ?t2 - task)\n");
					PDDL_rules_buffer.append("(or (not (traced ?t2))\n");				
					PDDL_rules_buffer.append("(pre ?t1 ?t2)\n");		
					PDDL_rules_buffer.append("(exists (?t3 - task) (and (pre ?t3 ?t2) (precedence ?t1 ?t3)))))\n\n");
				}
				
				PDDL_domain_buffer.append("(not_precedence-" + task[0] + "-" + task[1] + ")\n");			
				
				Constants.getPDDLActivitiesVector().addElement("not_precedence-" + task[0] + "-" + task[1]);
				
				PDDL_rules_buffer.append(";;\n");
				PDDL_rules_buffer.append(";;NOT PRECEDENCE-" + task[0] + "-" + task[1] + " - (GLOBALLY(" + task[0] + " --> NOT(EVENTUALLY " + task[1] + "))) - If an instance of " + task[0] + " occurs in the trace, then no instance of " + task[1] + " can occur in the trace after " + task[0] + ".\n");				
				PDDL_rules_buffer.append(";;\n\n");
				
				PDDL_rules_buffer.append("(:derived (not_precedence-" + task[0] + "-" + task[1] + ")\n");
				PDDL_rules_buffer.append("(forall (?tb - " + task[1] + ")\n");
				PDDL_rules_buffer.append("(or (not (traced ?tb))\n");
				PDDL_rules_buffer.append("(not (exists (?ta - " + task[0] + ") (precedence ?ta ?tb))))))\n\n");

				number_of_precedence_rules++;
			}
			//
			//DECLARE RULE: NOT SUCCESSION(A,B) --- (GLOBALLY(A --> NOT(EVENTUALLY B)) - If an instance of A occurs in the trace, then no instance of B can occur somewhere in the trace after A.
			//
			else if(declare_rule_name.equalsIgnoreCase("not succession")) {
				String[] tasks = single_rule_array[1].split("\\)");
				String[] task = tasks[0].split(",");
				
				if(number_of_response_rules==0) {
					PDDL_domain_buffer.append("(response ?t1 - task ?t2 - task)\n");
					PDDL_domain_buffer.append("(rstmp1 ?t1 - task ?t2 - task)\n");
					PDDL_domain_buffer.append("(rstmp2 ?t1 - task ?t2 - task ?t3 - task)\n");
					
					PDDL_rules_buffer.append(";;\n");
					PDDL_rules_buffer.append(";;GENERAL RESPONSE RULE.\n");				
					PDDL_rules_buffer.append(";;\n\n");
					
					PDDL_rules_buffer.append("(:derived (response ?t1 - task ?t2 - task)\n");
					PDDL_rules_buffer.append("(or (not (traced ?t1))\n");				
					PDDL_rules_buffer.append("(pre ?t1 ?t2)\n");		
					PDDL_rules_buffer.append("(rstmp1 ?t1 ?t2)))\n\n");
					
					PDDL_rules_buffer.append("(:derived (rstmp1 ?t1 - task ?t2 - task)\n");
					PDDL_rules_buffer.append("(exists (?t3 - task) (rstmp2 ?t1 ?t2 ?t3)))\n\n");
										
					PDDL_rules_buffer.append("(:derived (rstmp2 ?t1 - task ?t2 - task ?t3 - task)\n");
					PDDL_rules_buffer.append("(and (pre ?t1 ?t3) (response ?t3 ?t2)))\n\n");					

				}
				
				PDDL_domain_buffer.append("(not_succession-" + task[0] + "-" + task[1] + ")\n");			
				
				Constants.getPDDLActivitiesVector().addElement("not_succession-" + task[0] + "-" + task[1]);
				
				PDDL_rules_buffer.append(";;\n");
				PDDL_rules_buffer.append(";;NOT SUCCESSION-" + task[0] + "-" + task[1] + " - (GLOBALLY(" + task[0] + " --> NOT(EVENTUALLY " + task[1] + "))) - If an instance of " + task[0] + " occurs in the trace, then no instance of " + task[1] + " can occur in the trace after " + task[0] + ".\n");				
				PDDL_rules_buffer.append(";;\n\n");
				
				PDDL_rules_buffer.append("(:derived (not_succession-" + task[0] + "-" + task[1] + ")\n");
				PDDL_rules_buffer.append("(forall (?ta - " + task[0] + ")\n");
				PDDL_rules_buffer.append("(or (not (traced ?ta))\n");
				PDDL_rules_buffer.append("(not (exists (?tb - " + task[1] + ") (response ?ta ?tb))))))\n\n");

				number_of_response_rules++;
			}
			//
			//DECLARE RULE: NOT CHAIN RESPONSE(A,B) --- GLOBALLY(A IMPLIES NOT (NEXT B)) - If an instance of A occurs in the trace, then no instance of B can not occur in the next step of the trace after A.
			//					
			else if(declare_rule_name.equalsIgnoreCase("not chain response")) {
				String[] tasks = single_rule_array[1].split("\\)");
				String[] task = tasks[0].split(",");
				
				PDDL_domain_buffer.append("(not_chain_response-" + task[0] + "-" + task[1] + ")\n");	
				
				Constants.getPDDLActivitiesVector().addElement("not_chain_response-" + task[0] + "-" + task[1]);
				
				PDDL_rules_buffer.append(";;\n");
				PDDL_rules_buffer.append(";;NOT CHAIN RESPONSE-" + task[0] + "-" + task[1] + " - (GLOBALLY(" + task[0] + " --> NOT (NEXT " + task[1] + "))) - If an instance of " + task[0] + " occurs in the trace, then no instance of " + task[1] + " can occur in the next step of the trace after " + task[0] + ".\n");				
				PDDL_rules_buffer.append(";;\n\n");
				
				PDDL_rules_buffer.append("(:derived (not_chain_response-" + task[0] + "-" + task[1] + ")\n");
				PDDL_rules_buffer.append("(forall (?ta - " + task[0] + ")\n");
				PDDL_rules_buffer.append("(or (not (traced ?ta))\n");
				PDDL_rules_buffer.append("(not (exists (?tb - " + task[1] + ") (pre ?ta ?tb))))))\n\n");
			}
			//
			//DECLARE RULE: NOT CHAIN PRECEDENCE(A,B) --- GLOBALLY(NEXT B IMPLIES (NOT A)) - If an instance of B occurs in the trace, then no instance of A can occur in the previous step of the trace.
			//					
			else if(declare_rule_name.equalsIgnoreCase("not chain precedence")) {
				String[] tasks = single_rule_array[1].split("\\)");
				String[] task = tasks[0].split(",");
				
				PDDL_domain_buffer.append("(not_chain_precedence-" + task[0] + "-" + task[1] + ")\n");	
				
				Constants.getPDDLActivitiesVector().addElement("not_chain_precedence-" + task[0] + "-" + task[1]);
				
				PDDL_rules_buffer.append(";;\n");
				PDDL_rules_buffer.append(";;NOT CHAIN PRECEDENCE-" + task[0] + "-" + task[1] + " - (GLOBALLY(NEXT " + task[1] + " --> NOT(" + task[0] + "))) - If an instance of " + task[1] + " occurs in the trace, then no instance of " + task[0] + " can occur in the previous step of the trace.\n");				
				PDDL_rules_buffer.append(";;\n\n");
				
				PDDL_rules_buffer.append("(:derived (not_chain_precedence-" + task[0] + "-" + task[1] + ")\n");
				PDDL_rules_buffer.append("(forall (?tb - " + task[1] + ")\n");
				PDDL_rules_buffer.append("(or (not (traced ?tb))\n");
				PDDL_rules_buffer.append("(not (exists (?ta - " + task[0] + ") (pre ?ta ?tb))))))\n\n");
			}	
			//
			//DECLARE RULE: NOT CHAIN SUCCESSION(A,B) --- GLOBALLY(A IMPLIES NOT (NEXT B)) AND GLOBALLY(NEXT B IMPLIES (NOT A)) - If an instance of A occurs in the trace, then no instance of B can not occur in the next step of the trace after A. Moreover, if an instance of B occurs in the trace, then no instance of A can occur in the previous step of the trace.
			//					
			else if(declare_rule_name.equalsIgnoreCase("not chain succession")) {
				String[] tasks = single_rule_array[1].split("\\)");
				String[] task = tasks[0].split(",");
				
				PDDL_domain_buffer.append("(not_chain_succession-" + task[0] + "-" + task[1] + ")\n");	
				
				Constants.getPDDLActivitiesVector().addElement("not_chain_succession-" + task[0] + "-" + task[1]);
				
				PDDL_rules_buffer.append(";;\n");
				PDDL_rules_buffer.append(";;NOT CHAIN SUCCESSION-" + task[0] + "-" + task[1] + " - (GLOBALLY(" + task[0] + " --> NOT (NEXT " + task[1] + "))) AND (GLOBALLY(NEXT " + task[1] + " --> NOT(" + task[0] + "))) - If an instance of " + task[0] + " occurs in the trace, then no instance of " + task[1] + " can occur in the next step of the trace after " + task[0] + ". Moreover, If an instance of " + task[1] + " occurs in the trace, then no instance of " + task[0] + " can occur in the previous step of the trace.\n");				
				PDDL_rules_buffer.append(";;\n\n");
				
				PDDL_rules_buffer.append("(:derived (not_chain_succession-" + task[0] + "-" + task[1] + ")\n");
				PDDL_rules_buffer.append("(forall (?ta - " + task[0] + ")\n");
				PDDL_rules_buffer.append("(or (not (traced ?ta))\n");
				PDDL_rules_buffer.append("(not (exists (?tb - " + task[1] + ") (pre ?ta ?tb))))))\n\n");
			}
			//
			//DECLARE RULE: ALTERNATE RESPONSE(A,B) --- (GLOBALLY (A --> (NEXT (NOT A)) UNTIL B)) - If an instance of A occurs in the trace, then an instance of B must occur somewhere in the trace after A, but no other instance of A can occur between A and B.
			//
			else if(declare_rule_name.equalsIgnoreCase("alternate response")) {
				String[] tasks = single_rule_array[1].split("\\)");
				String[] task = tasks[0].split(",");
								
				if(!response_between_rules_vector.contains("response_with_no_" + task[0] + "_in_between"))  {
					
					PDDL_domain_buffer.append("(response_with_no_" + task[0] + "_in_between ?t1 - task ?t2 - task)\n");
					
					PDDL_rules_buffer.append(";;\n");
					PDDL_rules_buffer.append(";;GENERAL ALTERNATE RESPONSE RULE.\n");				
					PDDL_rules_buffer.append(";;\n\n");
					
					PDDL_rules_buffer.append("(:derived (response_with_no_" + task[0] + "_in_between ?t1 - task ?t2 - task)\n");
					PDDL_rules_buffer.append("(or\n");				
					PDDL_rules_buffer.append("(pre ?t1 ?t2)\n");		
					PDDL_rules_buffer.append("(and (not (exists (?ta - " + task[0] + ") (pre ?t1 ?ta)))\n");
					PDDL_rules_buffer.append("(exists (?t3 - task) (and (pre ?t1 ?t3) (response_with_no_" + task[0] + "_in_between ?t3 ?t2))))))\n\n");
					
					response_between_rules_vector.addElement("response_with_no_" + task[0] + "_in_between");
				}
						
				PDDL_domain_buffer.append("(alt_response-" + task[0] + "-" + task[1] + ")\n");			
				
				Constants.getPDDLActivitiesVector().addElement("alt_response-" + task[0] + "-" + task[1]);
				
				PDDL_rules_buffer.append(";;\n");
				PDDL_rules_buffer.append(";;ALTERNATE RESPONSE-" + task[0] + "-" + task[1] + " - (GLOBALLY(" + task[0] + " --> NEXT (NOT " + task[0] + " UNTIL " + task[1] + "))) - If an instance of " + task[0] + " occurs in the trace, then an instance of " + task[1] + " must occur in the trace after " + task[0] + ", but no other instances of " + task[0] + " can occur between " + task[0] + " and " + task[1] + ".\n");				
				PDDL_rules_buffer.append(";;\n\n");
				
				PDDL_rules_buffer.append("(:derived (alt_response-" + task[0] + "-" + task[1] + ")\n");
				PDDL_rules_buffer.append("(forall (?ta - " + task[0] + ")\n");			
				PDDL_rules_buffer.append("(or\n");
				PDDL_rules_buffer.append("(not (traced ?ta))\n");				
				PDDL_rules_buffer.append("(exists (?tb - " + task[1] + ") (response_with_no_" + task[0] + "_in_between ?ta ?tb)))))\n\n");
			}
			//
			//DECLARE RULE: ALTERNATE PRECEDENCE(A,B) --- (NOT B WEAK UNTIL A) AND (GLOBALLY (B --> (NEXT (NOT B)) WEAK UNTIL A)) - If an instance of B occurs in the trace, then an instance of A must occur somewhere in the trace before B, without other occurrences of B in between.
			//
			else if(declare_rule_name.equalsIgnoreCase("alternate precedence")) {
				String[] tasks = single_rule_array[1].split("\\)");
				String[] task = tasks[0].split(",");
				
				
				if(!precedence_between_rules_vector.contains("precedence_with_no_" + task[1] + "_in_between"))  {
					
					PDDL_domain_buffer.append("(precedence_with_no_" + task[1] + "_in_between ?t1 - task ?t2 - task)\n");
					
					PDDL_rules_buffer.append(";;\n");
					PDDL_rules_buffer.append(";;GENERAL ALTERNATE PRECEDENCE RULE.\n");				
					PDDL_rules_buffer.append(";;\n\n");
					
					PDDL_rules_buffer.append("(:derived (precedence_with_no_" + task[1] + "_in_between ?t1 - task ?t2 - task)\n");
					PDDL_rules_buffer.append("(or\n");				
					PDDL_rules_buffer.append("(pre ?t1 ?t2)\n");		
					PDDL_rules_buffer.append("(and (not (exists (?tb - " + task[1] + ") (pre ?tb ?t2)))\n");
					PDDL_rules_buffer.append("(exists (?t3 - task) (and (pre ?t3 ?t2) (precedence_with_no_" + task[1] + "_in_between ?t1 ?t3))))))\n\n");
					
					precedence_between_rules_vector.addElement("precedence_with_no_" + task[1] + "_in_between");
				}
						
				PDDL_domain_buffer.append("(alt_precedence-" + task[0] + "-" + task[1] + ")\n");			
				
				Constants.getPDDLActivitiesVector().addElement("alt_precedence-" + task[0] + "-" + task[1]);
				
				PDDL_rules_buffer.append(";;\n");
				PDDL_rules_buffer.append(";;ALTERNATE PRECEDENCE-" + task[0] + "-" + task[1] + " - (NOT " + task[1] + " WEAK UNTIL " + task[0] + ") AND (GLOBALLY(" + task[1] + " --> NEXT (NOT " + task[1] + " WEAK UNTIL " + task[0] + "))) - If an instance of " + task[1] + " occurs in the trace, then an instance of " + task[0] + " must occur in the trace before " + task[1] + ", but no other instances of " + task[1] + " can occur in between.\n");				
				PDDL_rules_buffer.append(";;\n\n");
				
				PDDL_rules_buffer.append("(:derived (alt_precedence-" + task[0] + "-" + task[1] + ")\n");
				PDDL_rules_buffer.append("(forall (?tb - " + task[1] + ")\n");
				PDDL_rules_buffer.append("(or\n");
				PDDL_rules_buffer.append("(not (traced ?tb)) \n");
				PDDL_rules_buffer.append("(exists (?ta - " + task[0] + ") (precedence_with_no_" + task[1] + "_in_between ?ta ?tb)))))\n\n");
			}
			//
			//DECLARE RULE: ALTERNATE SUCCESSION(A,B) --- (GLOBALLY (A --> (NEXT (NOT A)) UNTIL B)) AND (NOT B WEAK UNTIL A) AND (GLOBALLY (B --> (NEXT (NOT B)) WEAK UNTIL A)) - If an instance of A occurs in the trace, then an instance of B must occur somewhere in the trace after A, but no other instance of A can occur between A and B. Moreover, if an instance of B occurs in the trace, then an instance of A must occur somewhere in the trace before B, without other occurrences of B in between.
			//
			else if(declare_rule_name.equalsIgnoreCase("alternate succession")) {
				String[] tasks = single_rule_array[1].split("\\)");
				String[] task = tasks[0].split(",");				
				
				if(!response_between_rules_vector.contains("response_with_no_" + task[0] + "_in_between"))  {
					
					PDDL_domain_buffer.append("(response_with_no_" + task[0] + "_in_between ?t1 - task ?t2 - task)\n");
					
					PDDL_rules_buffer.append(";;\n");
					PDDL_rules_buffer.append(";;GENERAL ALTERNATE RESPONSE RULE.\n");				
					PDDL_rules_buffer.append(";;\n\n");
					
					PDDL_rules_buffer.append("(:derived (response_with_no_" + task[0] + "_in_between ?t1 - task ?t2 - task)\n");
					PDDL_rules_buffer.append("(or\n");				
					PDDL_rules_buffer.append("(pre ?t1 ?t2)\n");		
					PDDL_rules_buffer.append("(and (not (exists (?ta - " + task[0] + ") (pre ?t1 ?ta)))\n");
					PDDL_rules_buffer.append("(exists (?t3 - task) (and (pre ?t1 ?t3) (response_with_no_" + task[0] + "_in_between ?t3 ?t2))))))\n\n");
					
					response_between_rules_vector.addElement("response_with_no_" + task[0] + "_in_between");
				}
				
				if(!precedence_between_rules_vector.contains("precedence_with_no_" + task[1] + "_in_between"))  {
					
					PDDL_domain_buffer.append("(precedence_with_no_" + task[1] + "_in_between ?t1 - task ?t2 - task)\n");
					
					PDDL_rules_buffer.append(";;\n");
					PDDL_rules_buffer.append(";;GENERAL ALTERNATE PRECEDENCE RULE.\n");				
					PDDL_rules_buffer.append(";;\n\n");
					
					PDDL_rules_buffer.append("(:derived (precedence_with_no_" + task[1] + "_in_between ?t1 - task ?t2 - task)\n");
					PDDL_rules_buffer.append("(or\n");				
					PDDL_rules_buffer.append("(pre ?t1 ?t2)\n");		
					PDDL_rules_buffer.append("(and (not (exists (?tb - " + task[1] + ") (pre ?tb ?t2)))\n");
					PDDL_rules_buffer.append("(exists (?t3 - task) (and (pre ?t3 ?t2) (precedence_with_no_" + task[1] + "_in_between ?t1 ?t3))))))\n\n");
					
					precedence_between_rules_vector.addElement("precedence_with_no_" + task[1] + "_in_between");
				}
						
				PDDL_domain_buffer.append("(alt_succession-" + task[0] + "-" + task[1] + ")\n");			
				
				Constants.getPDDLActivitiesVector().addElement("alt_succession-" + task[0] + "-" + task[1]);
				
				PDDL_rules_buffer.append(";;\n");
				PDDL_rules_buffer.append(";;ALTERNATE SUCCESSION-" + task[0] + "-" + task[1] + " - (GLOBALLY(" + task[0] + " --> NEXT (NOT " + task[0] + " UNTIL " + task[1] + "))) - If an instance of " + task[0] + " occurs in the trace, then an instance of " + task[1] + " must occur in the trace after " + task[0] + ", but no other instances of " + task[0] + " can occur between " + task[0] + " and " + task[1] + ".\n");				
				PDDL_rules_buffer.append(";;\n\n");
				
				PDDL_rules_buffer.append("(:derived (alt_succession-" + task[0] + "-" + task[1] + ")\n");
				PDDL_rules_buffer.append("(and\n");
				PDDL_rules_buffer.append("(forall (?ta - " + task[0] + ")\n");			
				PDDL_rules_buffer.append("(or\n");
				PDDL_rules_buffer.append("(not (traced ?ta))\n");				
				PDDL_rules_buffer.append("(exists (?tb - " + task[1] + ") (response_with_no_" + task[0] + "_in_between ?ta ?tb))))\n\n");
				PDDL_rules_buffer.append("(forall (?tb - " + task[1] + ")\n");
				PDDL_rules_buffer.append("(or\n");
				PDDL_rules_buffer.append("(not (traced ?tb)) \n");
				PDDL_rules_buffer.append("(exists (?ta - " + task[0] + ") (precedence_with_no_" + task[1] + "_in_between ?ta ?tb))))))\n\n");
			}
		}
		
		PDDL_domain_buffer.append(")\n\n");			
		
		
		//PDDL FUNCTIONS TO REPRESENT THE COST OF ADDING/REMOVING TASKS IN/FROM THE TRACE
		
		//if(Constants.getPlannerPerspective().getCostCheckBox().isSelected()) {
			
			PDDL_domain_buffer.append(";;\n");
			PDDL_domain_buffer.append(";;FUNCTIONS\n");		
			PDDL_domain_buffer.append(";;\n\n");
			
			PDDL_domain_buffer.append("(:functions\n");
			PDDL_domain_buffer.append("(adding-cost ?t -task)\n");			
			PDDL_domain_buffer.append("(removing-cost ?t -task)\n");						 
			PDDL_domain_buffer.append("(total-cost)\n");					 
			PDDL_domain_buffer.append(")\n");	
					
		//}
		
		//INSERT PDDL ACTIONS REPRESENTING TASKS 
		
		PDDL_domain_buffer.append(";;\n");
		PDDL_domain_buffer.append(";;PLANNING ACTIONS\n");		
		PDDL_domain_buffer.append(";;\n\n");

		PDDL_domain_buffer.append(";;\n");
		PDDL_domain_buffer.append(";;If the trace is empty, the action --addToTheEmptyTrace-- adds a task 'x1' (which is actually not in the trace) to the trace.\n");		
		PDDL_domain_buffer.append(";;After the action happening, 'x1' will be contemporaneously the first and the last task of the trace.\n");		
		PDDL_domain_buffer.append(";;\n\n");

		PDDL_domain_buffer.append("(:action addToTheEmptyTrace\n");		
		PDDL_domain_buffer.append(":parameters (?x1 - task)\n");		
		PDDL_domain_buffer.append(":precondition (not (exists (?x - task) (traced ?x)))\n");		 
		PDDL_domain_buffer.append(":effect (and (traced ?x1) (first_task_of_the_trace ?x1) (last_task_of_the_trace ?x1)");			
		//if(Constants.getPlannerPerspective().getCostCheckBox().isSelected()) {
		PDDL_domain_buffer.append(" (increase (total-cost) (adding-cost ?x1))");	
		//}
		PDDL_domain_buffer.append(")\n");	
		PDDL_domain_buffer.append(")\n\n");		
		
		PDDL_domain_buffer.append(";;\n");
		PDDL_domain_buffer.append(";;If 'x1' is the first task of the trace, the action --addAtTheBeginning-- adds a task 'x2' (which is actually not in the trace) before 'x1'.\n");
		PDDL_domain_buffer.append(";;\n\n");

		PDDL_domain_buffer.append("(:action addAtTheBeginning\n");
		PDDL_domain_buffer.append(":parameters (?x1 - task ?x2 - task)\n");		
		PDDL_domain_buffer.append(":precondition (and (not (traced ?x2)) (first_task_of_the_trace ?x1))\n");		 
		PDDL_domain_buffer.append(":effect (and (not (first_task_of_the_trace ?x1)) (first_task_of_the_trace ?x2) (traced ?x2) (pre ?x2 ?x1)");		
		//if(Constants.getPlannerPerspective().getCostCheckBox().isSelected()) {
		PDDL_domain_buffer.append(" (increase (total-cost) (adding-cost ?x2))");	
		//}
		PDDL_domain_buffer.append(")\n");
		PDDL_domain_buffer.append(")\n\n");

		PDDL_domain_buffer.append(";;\n");
		PDDL_domain_buffer.append(";;If 'x1' is the last task of the trace, the action --addAtTheEnd-- adds a task 'x2' (which is actually not in the trace) after 'x1'.\n");
		PDDL_domain_buffer.append(";;\n\n");

		PDDL_domain_buffer.append("(:action addAtTheEnd\n");
		PDDL_domain_buffer.append(":parameters (?x1 - task ?x2 - task)\n");		
		PDDL_domain_buffer.append(":precondition (and (not (traced ?x2)) (last_task_of_the_trace ?x1))\n");		 
		PDDL_domain_buffer.append(":effect (and (not (last_task_of_the_trace ?x1)) (last_task_of_the_trace ?x2) (traced ?x2) (pre ?x1 ?x2)");
		//if(Constants.getPlannerPerspective().getCostCheckBox().isSelected()) {
		PDDL_domain_buffer.append(" (increase (total-cost) (adding-cost ?x2))");	
		//}
		PDDL_domain_buffer.append(")\n");
		PDDL_domain_buffer.append(")\n\n");

		PDDL_domain_buffer.append(";;\n");
		PDDL_domain_buffer.append(";;The action --addBetween-- inserts the task 'x2' between the tasks 'x1' and 'x3' (that are already included in the trace).\n");
		PDDL_domain_buffer.append(";;After the action happening, two new couples of actions will be created: '(x1,x2)' and '(x2,x3)'.\n");
		PDDL_domain_buffer.append(";;\n\n");

		PDDL_domain_buffer.append("(:action addBetween\n");
		PDDL_domain_buffer.append(":parameters (?x1 - task ?x2 - task ?x3 - task)\n");		
		PDDL_domain_buffer.append(":precondition (and (pre ?x1 ?x3) (not (traced ?x2)))\n");			 
		PDDL_domain_buffer.append(":effect (and (traced ?x2) (pre ?x1 ?x2) (pre ?x2 ?x3) (not (pre ?x1 ?x3))");
		//if(Constants.getPlannerPerspective().getCostCheckBox().isSelected()) {
		PDDL_domain_buffer.append(" (increase (total-cost) (adding-cost ?x2))");	
		//}
		PDDL_domain_buffer.append(")\n");
		PDDL_domain_buffer.append(")\n\n");

		PDDL_domain_buffer.append(";;\n");
		PDDL_domain_buffer.append(";;The action --deleteSingleTask-- deletes the task 'x1', if 'x1' it is the only task in the trace.\n");		
		PDDL_domain_buffer.append(";;\n\n");

		PDDL_domain_buffer.append("(:action deleteSingleTask\n");
		PDDL_domain_buffer.append(":parameters (?x1 - task)\n");		
		PDDL_domain_buffer.append(":precondition (and (first_task_of_the_trace ?x1) (last_task_of_the_trace ?x1))\n");			 
		PDDL_domain_buffer.append(":effect (and (not (traced ?x1)) (not (last_task_of_the_trace ?x1)) (not (first_task_of_the_trace ?x1))");			
		//if(Constants.getPlannerPerspective().getCostCheckBox().isSelected()) {
		PDDL_domain_buffer.append(" (increase (total-cost) (removing-cost ?x1))");	
		//}
		PDDL_domain_buffer.append(")\n");
		PDDL_domain_buffer.append(")\n\n");

		PDDL_domain_buffer.append(";;\n");
		PDDL_domain_buffer.append(";;If 'x1' is the first task of the trace, and the task 'x2' follows 'x1', the action --deleteAtTheBeginning-- deletes 'x1' and makes 'x2' the new first task of the trace.\n");		
		PDDL_domain_buffer.append(";;\n\n");

		PDDL_domain_buffer.append("(:action deleteAtTheBeginning\n");
		PDDL_domain_buffer.append(":parameters (?x1 - task ?x2 - task)\n");		
		PDDL_domain_buffer.append(":precondition (and (pre ?x1 ?x2) (first_task_of_the_trace ?x1))\n");		 
		PDDL_domain_buffer.append(":effect (and (not (traced ?x1)) (not (first_task_of_the_trace ?x1)) (first_task_of_the_trace ?x2) (not (pre ?x1 ?x2))");		
		//if(Constants.getPlannerPerspective().getCostCheckBox().isSelected()) {
		PDDL_domain_buffer.append(" (increase (total-cost) (removing-cost ?x1))");	
		//}
		PDDL_domain_buffer.append(")\n");
		PDDL_domain_buffer.append(")\n\n");

		PDDL_domain_buffer.append(";;\n");
		PDDL_domain_buffer.append(";;If 'x2' is the last task of the trace, and the task 'x2' follows 'x1', the action --deleteAtTheEnd-- deletes 'x2' and makes 'x1' the new last task of the trace.\n");		
		PDDL_domain_buffer.append(";;\n\n");

		PDDL_domain_buffer.append("(:action deleteAtTheEnd\n");
		PDDL_domain_buffer.append(":parameters (?x1 - task ?x2 - task)\n");		
		PDDL_domain_buffer.append(":precondition (and (pre ?x1 ?x2) (last_task_of_the_trace ?x2))\n");		 
		PDDL_domain_buffer.append(":effect (and (not (traced ?x2)) (not (last_task_of_the_trace ?x2)) (last_task_of_the_trace ?x1) (not (pre ?x1 ?x2))");			
		//if(Constants.getPlannerPerspective().getCostCheckBox().isSelected()) {
		PDDL_domain_buffer.append(" (increase (total-cost) (removing-cost ?x2))");	
		//}
		PDDL_domain_buffer.append(")\n");
		PDDL_domain_buffer.append(")\n\n");

		PDDL_domain_buffer.append(";;\n");
		PDDL_domain_buffer.append(";;The action --deleteBetween-- deletes the task x2 that was previously situated between the tasks x1 and x3.\n");		
		PDDL_domain_buffer.append(";;\n\n");

		PDDL_domain_buffer.append("(:action deleteBetween\n");
		PDDL_domain_buffer.append(":parameters (?x1 - task ?x2 - task ?x3 - task)\n");		
		PDDL_domain_buffer.append(":precondition (and (pre ?x1 ?x2) (pre ?x2 ?x3))\n");			
		PDDL_domain_buffer.append(":effect (and (not (traced ?x2)) (not (pre ?x1 ?x2)) (not (pre ?x2 ?x3)) (pre ?x1 ?x3)");		
		//if(Constants.getPlannerPerspective().getCostCheckBox().isSelected()) {
		PDDL_domain_buffer.append(" (increase (total-cost) (removing-cost ?x2))");	
		//}
		PDDL_domain_buffer.append(")\n");
		PDDL_domain_buffer.append(")\n\n");

		
		////////////////////////////////////////
		
		PDDL_domain_buffer.append(PDDL_rules_buffer);
		PDDL_domain_buffer.append(")");
		
		return PDDL_domain_buffer;
	}

	private StringBuffer createProblem(Trace trace) {

		StringBuffer PDDL_problem_buffer = new StringBuffer();
		
		PDDL_problem_buffer.append("(define (problem Align) (:domain Mining)\n");
		PDDL_problem_buffer.append("(:objects\n");		
		
		StringBuffer PDDL_cost_buffer = new StringBuffer();
		
		for(int k=0;k<trace.getTraceAlphabetWithMissingActivitiesOfTheConstraints_vector().size();k++) {
					
			Enumeration<String> enum_trace_hashtable = trace.getAssociationsToActivityInstances_Hashtable().keys();
			
			while(enum_trace_hashtable.hasMoreElements()) {
				String key = (String) enum_trace_hashtable.nextElement();
				String value = (String) trace.getAssociationsToActivityInstances_Hashtable().get(key);
				
				if(value.equalsIgnoreCase((String) trace.getTraceAlphabetWithMissingActivitiesOfTheConstraints_vector().elementAt(k)))
					PDDL_problem_buffer.append(key + " ");
			
			}
			
			PDDL_problem_buffer.append("- " + trace.getTraceAlphabetWithMissingActivitiesOfTheConstraints_vector().elementAt(k) + "\n");
		}
		
		PDDL_problem_buffer.append(")\n");		
		PDDL_problem_buffer.append("(:init\n");
		
		
		  ////////////////////////////////////////
	      Enumeration<String> enum_trace_hashtable = trace.getAssociationsToActivityInstances_Hashtable().keys();
			
			while(enum_trace_hashtable.hasMoreElements()) {
				String key = (String) enum_trace_hashtable.nextElement();
				String value = (String) trace.getAssociationsToActivityInstances_Hashtable().get(key);

				//if(Constants.getPlannerPerspective().getCostCheckBox().isSelected()) {
								
					for(int yu=0;yu<Constants.getActivitiesCost_vector().size();yu++) {
						Vector<String> specificTraceCostVector = Constants.getActivitiesCost_vector().elementAt(yu);					
						if(specificTraceCostVector.elementAt(0).equalsIgnoreCase(value)) {
							PDDL_cost_buffer.append("(= (adding-cost " + key + ") " +  specificTraceCostVector.elementAt(1) + ")\n");
							PDDL_cost_buffer.append("(= (removing-cost " + key + ") " +  specificTraceCostVector.elementAt(2) + ")\n");
							break;
						}
					}				
				//}
			}
			////////////////////////////////////////
		
		
		
		if(trace.getTraceContentWithActivitiesInstances_vector().size()==1) { //Case of a single task instance in the trace
			String current_task =  (String) trace.getTraceContentWithActivitiesInstances_vector().elementAt(0);
			PDDL_problem_buffer.append("(first_task_of_the_trace " + current_task + ")\n");
			PDDL_problem_buffer.append("(last_task_of_the_trace " + current_task + ")\n");
			
		}
		else  {
		
			for(int inx=0;inx<trace.getTraceContentWithActivitiesInstances_vector().size()-1;inx++) { //Case of multiple task instances in the trace
				
				String current_task =  (String) trace.getTraceContentWithActivitiesInstances_vector().elementAt(inx);
				String next_task = (String) trace.getTraceContentWithActivitiesInstances_vector().elementAt(inx+1);
				
				if(inx==0) PDDL_problem_buffer.append("(first_task_of_the_trace " + current_task + ")\n");
				
				PDDL_problem_buffer.append("(pre " + current_task + " " + next_task + ")\n");
				
				if(inx==trace.getTraceContentWithActivitiesInstances_vector().size()-2) PDDL_problem_buffer.append("(last_task_of_the_trace " + next_task + ")\n");
				
			}
		}
		
		for(int inx=0;inx<trace.getTraceContentWithActivitiesInstances_vector().size();inx++) {
			PDDL_problem_buffer.append("(traced " + trace.getTraceContentWithActivitiesInstances_vector().elementAt(inx) + ")\n");
		}
		//////////////////////////////////
		//if(Constants.getPlannerPerspective().getCostCheckBox().isSelected()) {
			PDDL_cost_buffer.append("(= (total-cost) 0)");
			PDDL_problem_buffer.append(PDDL_cost_buffer);	
		//}
		/////////////////////////////////
		PDDL_problem_buffer.append(")\n");		
		
		PDDL_problem_buffer.append("(:goal\n");		
		
		
		if(Constants.getPDDLActivitiesVector().size()>1)  {
			PDDL_problem_buffer.append("(and \n");			
				for(int index=0;index<Constants.getPDDLActivitiesVector().size();index++) {
					PDDL_problem_buffer.append("(" + Constants.getPDDLActivitiesVector().elementAt(index) + ")\n");
			    }
			PDDL_problem_buffer.append(")\n");		
		}
		else {
			PDDL_problem_buffer.append("(" + Constants.getPDDLActivitiesVector().elementAt(0) + ")\n");
		}
		PDDL_problem_buffer.append(")\n");		
		/////////////////////
		//if(Constants.getPlannerPerspective().getCostCheckBox().isSelected()) 
			PDDL_problem_buffer.append("(:metric minimize (total-cost))\n");	
		///////////////////	
		PDDL_problem_buffer.append(")");	
		
		return PDDL_problem_buffer;
	}
	
		
	public Thread getPlanner_thread() {
		return planner_thread;
	}

	public void setPlanner_thread(Thread planner_thread) {
		this.planner_thread = planner_thread;
	}

	private void scriviFile(String nomeFile, StringBuffer buffer) {
		 
		File file = null;
	    FileWriter fw = null;
		   
		   try {
			file = new File(nomeFile);
			file.setExecutable(true);
			
			fw = new FileWriter(file);
			fw.write(buffer.toString());
			fw.close();
			
		   //fw.flush();
		   //fw.close();
		   }
		   catch(IOException e) {
		   e.printStackTrace();
		   }
	}
	
	private int getCostOfAdding(String nomeTask) {
		
		for(int kix=0;kix<Constants.getActivitiesCost_vector().size();kix++) {
		
		Vector<String> task_cost_vector = Constants.getActivitiesCost_vector().elementAt(kix);
		
		if(task_cost_vector.elementAt(0).equalsIgnoreCase(nomeTask)) {
			return new Integer(task_cost_vector.elementAt(1));	
		}
		}
		return 0;
	}
	
	private int getCostOfDeleting(String nomeTask) {
	
		for(int kix=0;kix<Constants.getActivitiesCost_vector().size();kix++) {
		
		Vector<String> task_cost_vector = Constants.getActivitiesCost_vector().elementAt(kix);
		
		if(task_cost_vector.elementAt(0).equalsIgnoreCase(nomeTask)) {
			return new Integer(task_cost_vector.elementAt(2));	
		}
		}
		return 0;
	}
	
	private int getCostOfCreatingTrace(Vector<String> trace_vector) {
		
		int total_cost = 0;
		
		for(int kix=0;kix<trace_vector.size();kix++) {			
			String nomeTask = trace_vector.elementAt(kix);
			total_cost += getCostOfAdding(nomeTask);			
		}
		
		return total_cost;
	}
	
	private int getCostOfRemovingTrace(Vector<String> trace_vector) {
		
		int total_cost = 0;
		
		for(int kix=0;kix<trace_vector.size();kix++) {			
			String nomeTask = trace_vector.elementAt(kix);
			total_cost += getCostOfDeleting(nomeTask);			
		}
		
		return total_cost;
	}
	
}