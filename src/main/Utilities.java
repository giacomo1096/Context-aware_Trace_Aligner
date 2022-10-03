package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import org.processmining.ltl2automaton.plugins.automaton.Automaton;
import org.processmining.ltl2automaton.plugins.automaton.State;
import org.processmining.ltl2automaton.plugins.automaton.Transition;

public class Utilities {
	
	public static Automaton getAutomatonForModelLearning(String filename) throws FileNotFoundException {
        File file = new File(filename);
        BufferedReader br = null;
        
        br = new BufferedReader(new FileReader(file));
        
        String st;
        HashMap<Integer, State> states = new HashMap<>();
        Automaton n = new Automaton();

        try {
			while ((st = br.readLine()) != null) {
				st = st.trim();
			    if (st.contains("fake") || st.contains("digraph") || st.contains("}"))
			        continue;
			        //Transaction
			    else if (st.contains("[label")) {
			        //Transaction
			        String[] splited = st.split(" ");
			        String source = splited[0].trim();
			        String target = splited[2].trim();

			        String label = splited[3].substring(7, splited[3].length() - 1);
			        
			        //System.out.println("\nDOT PARSER:\n source: "+source + " target: "+target+" label: "+label+"\n");
			        
			        
			        
			        Transition t = new Transition(states.get(Integer.parseInt(source)), states.get(Integer.parseInt(target)), label); //DO NOT DELETE! --> Aggiunge allo stato source la transizione!! 


			    }
			    // States
			    else {
			        String[] splited = st.split(" ");

			        if (splited.length > 2) {
			            String label = splited[0];
			            State s = new State(Integer.parseInt(label));
			            s.setAccepting(true);
			            n.addState(s);
			            n.setInitial(s);
			            states.put(Integer.parseInt(label), s);
			        } else if (splited.length == 2) {
			            String label = splited[0];
			            State s = new State(Integer.parseInt(label));
			            n.addState(s);
			            String text = splited[1].substring(1, splited[1].length() - 1);
			            if (text.equals("shape=doublecircle")) {
			                s.setAccepting(true);
			            } else {
			                n.setInitial(s);
			            }
			            states.put(Integer.parseInt(label), s);
			        } else {
			            String label = splited[0];
			            State s = new State(Integer.parseInt(label));
			            n.addState(s);
			            states.put(Integer.parseInt(label), s);
			        }


			    }
			}
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return n;
	}
	
	/*
	public static Automaton getAutomatonForModelLearning(String filename) throws FileNotFoundException {
        File file = new File(filename);
        BufferedReader br = null;
        
        br = new BufferedReader(new FileReader(file));
        
        String st;
        HashMap<Integer, State> states = new HashMap<>();
        Automaton n = new Automaton();

        try {
			while ((st = br.readLine()) != null) {
			    if (st.contains("fake") || st.contains("digraph") || st.contains("}"))
			        continue;
			        //Transaction
			    else if (st.contains("[label")) {
			        //Transaction
			        String[] splited = st.split(" ");
			        String source = splited[0].trim();
			        String target = splited[2].trim();

			        String label = splited[3].substring(7, splited[3].length() - 1);
			        
			        //System.out.println("\n source: "+source + " target: "+target+" label: "+label+"\n");
			        
			        
			        
			        Transition t = new Transition(states.get(Integer.parseInt(source)), states.get(Integer.parseInt(target)), label); //Aggiunge allo stato source la transizione!! 

			        State source1 = states.get(Integer.parseInt(source)); //SERVE ?

			    }
			    // States
			    else {
			        st = st.trim();
			        String[] splited = st.split(" ");

			        if (splited.length > 2) {
			            String label = splited[0];
			            State s = new State(Integer.parseInt(label));
			            s.setAccepting(true);
			            n.addState(s);
			            n.setInitial(s);
			            states.put(Integer.parseInt(label), s);
			        } else if (splited.length == 2) {
			            String label = splited[0];
			            State s = new State(Integer.parseInt(label));
			            n.addState(s);
			            String text = splited[1].substring(1, splited[1].length() - 1);
			            if (text.equals("shape=doublecircle")) {
			                s.setAccepting(true);
			            } else {
			                n.setInitial(s);
			            }
			            states.put(Integer.parseInt(label), s);
			        } else {
			            String label = splited[0];
			            State s = new State(Integer.parseInt(label));
			            n.addState(s);
			            states.put(Integer.parseInt(label), s);
			        }


			    }
			}
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        //SERVE ?
        // 	INIZIO
        State s = n.getInit();

        //System.out.println("ID stato iniziale " + s.getId());
        //System.out.println("# stati automa " + n.getStateCount());

        //System.out.println("# transizioni stato iniziale " + s.getOutputSize());
        //System.out.println("# transizioni automa " + n.getTransitionCount());
        Iterator<State> it = n.iterator();
        while (it.hasNext()) {
            State ss = it.next();
            Iterator<Transition> transitions = ss.getOutput().iterator();
            while (transitions.hasNext()) {
                Transition t = transitions.next();
                //System.out.println("Source " + t.getSource() + " Target " + t.getTarget() + " Label " + t.getLabel().getaLiteral());
            }
        }
        
        // FINE
        
        
        return n;
	}
	
	 */
	
	 //not used
	public static Automaton getAutomatonForModelLearningDot(String filename) throws FileNotFoundException {
        File file = new File(filename);
        BufferedReader br = null;
        
        br = new BufferedReader(new FileReader(file));
        
        String st;
        HashMap<Integer, State> states = new HashMap<>();
        Automaton n = new Automaton();

        try {
            while ((st = br.readLine()) != null) {
                if (st.contains("rankdir") || st.contains("digraph") || st.contains("}"))
                    continue;
                    //States
                else if (st.contains("[shape")) {

                    st = st.trim();

                    String[] splited = st.split(" ");
                    String label = splited[0];
                    String text = splited[1].substring(1, splited[1].length() - 11);
                    if (label.equals("initial")) continue;
                    if(!states.containsKey(Integer.parseInt(label))) {
                        State s = new State(Integer.parseInt(label));
                        states.put(Integer.parseInt(label), s);
                        n.addState(s);
                    }
                    if (text.equals("shape=doublecircle")) {
                        State s = states.get((Integer.parseInt(label)));
                        s.setAccepting(true);
                    }
                }
                else if (st.contains("[label")) {
                    //Transaction
                    st = st.trim();
                    int sindex = st.indexOf('=');
                    int findex = st.indexOf(']');

                    String[] splited = st.split(" ");
                    String source = splited[0].trim();
                    String target = splited[2].trim();

                    String label = st.substring(sindex+2,findex-1).toLowerCase().replace(" ","");

                    String[] v = label.split("\\\\n");
                    for(String e : v) {

                        if (!states.containsKey(Integer.parseInt(source))) {
                            State s = new State(Integer.parseInt(source));
                            states.put((Integer.parseInt(source)), s);
                            n.addState(s);
                        }
                        if (!states.containsKey(Integer.parseInt(target))) {
                            State s = new State(Integer.parseInt(target));
                            states.put((Integer.parseInt(target)), s);
                            n.addState(s);
                        }
                        Transition t = new Transition(states.get(Integer.parseInt(source)), states.get(Integer.parseInt(target)), e);

                        State source1 = states.get(Integer.parseInt(source));
                    }

                }
                else if(st.contains("initial")){
                    //get automaton initial state
                    if(st.contains("[shape")) continue;
                    st = st.trim();
                    String[] splited = st.split(" ");
                    n.setInitial(states.get(Integer.parseInt(splited[2])));
                }
            }
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        State s = n.getInit();

        //System.out.println("ID stato iniziale " + s.getId());
        //System.out.println("# stati automa " + n.getStateCount());

        //System.out.println("# transizioni stato iniziale " + s.getOutputSize());
        //System.out.println("# transizioni automa " + n.getTransitionCount());
        Iterator<State> it = n.iterator();
        while (it.hasNext()) {
            State ss = it.next();
            Iterator<Transition> transitions = ss.getOutput().iterator();
            while (transitions.hasNext()) {
                Transition t = transitions.next();
                //System.out.println("Source " + t.getSource() + " Target " + t.getTarget() + " Label " + t.getLabel().getaLiteral());
            }
        }
        
        
        
        return n;
	}

	
 		//
		// Method to generate k-combinations of a set of elements in an array. 
	    // You just need to pass the array and the "k" value,  which is an integer that represents the length of the unique subsets 
	    // you want to generate out of the original array. 
		// -- ATTENTION -- This method is customized for the plan-based declarative aligner.
		// It removes any combination that contains two transitions of the same automaton
	    //
	
	public static void findCombinationsOfTransitions(Object[] arr, String label, int len, int original_k_value, int startPosition, String[] result) {
	    if (len == 0){
	       	       
	    	//String str = "";
	    	 
	        Vector<String> automata_ID_of_relevant_transitions_involved_in_a_combination_vector = new Vector<String>();
	        Vector<String> combination_of_relevant_transitions_vector = new Vector<String>();
	        
	        for(String relevant_transition : result)
	        {
	        	int first_underscore = relevant_transition.indexOf("_");
				int last_underscore = relevant_transition.lastIndexOf("_");
				String automaton_id = relevant_transition.substring(first_underscore+1, last_underscore);
	        	
				//
				// If a combination under construction contains two relevant transitions of the same automaton, 
				// the combination is immediately discarded. Conversely, if the combination contains ONLY relevant transitions
				// taken from different automata, a new "RelevantTransition" object
				//
				if(automata_ID_of_relevant_transitions_involved_in_a_combination_vector.contains(automaton_id)) {
					//str += transition.toString() + ", " + "(" + automata_id + ")";
					return;
				}
				else  {
					//str += transition.toString() + ", ";					
					combination_of_relevant_transitions_vector.addElement(relevant_transition);
					automata_ID_of_relevant_transitions_involved_in_a_combination_vector.addElement(automaton_id);
				}
	        }
	        
	        // System.out.print(label + " -- ");
	        // System.out.println(str);
	        
	        Vector<String> original_transitions_associated_to_the_label_vector = new Vector<String>();
	        for(int hu=0;hu<arr.length;hu++) {
	        	original_transitions_associated_to_the_label_vector.addElement(arr[hu].toString());
	        }
	        	        
	        String cotID = "ct" + Constants.getCombinationOfRelevantTransitions_vector().size();
	        
	        CombinationOfRelevantTransitions cot = new CombinationOfRelevantTransitions(cotID, label, original_k_value, combination_of_relevant_transitions_vector, original_transitions_associated_to_the_label_vector);
	                
	        // System.out.println(cot.getId() + " -- " + cot.getLabel() + " --> " + cot.getCombination_of_transitions_vector() + " -- " + cot.getOriginal_transitions_associated_to_the_label_vector() + " -- " + cot.getPDDL_preconditions() + " -- " + cot.getPDDL_effects());
	        	        
	        Constants.getCombinationOfRelevantTransitions_vector().addElement(cot);
	        /*
	        if(Constants.getMenuPerspective().getSinkStatesMenuItem().isSelected() && cot.containsSinkstates()) {
	        	Constants.getCombinationOfRelevantTransitions_vector().removeElement(cot);
	        	// System.out.println("This combination of transition contains a sink state ");
	        } */
	        return;
	    }       
	    for (int i = startPosition; i <= arr.length-len; i++){
	        result[result.length - len] = arr[i].toString();
	        findCombinationsOfTransitions(arr, label, len-1, original_k_value, i+1, result);
	    }
	}
	
	public static void findCombinationsOfTransitions_sync_move(Object[] arr, String label, int len, int original_k_value, int startPosition, String[] result) {
	    if (len == 0){
	       	       
	    	//String str = "";
	    	 
	        Vector<String> automata_ID_of_relevant_transitions_involved_in_a_combination_vector = new Vector<String>();
	        Vector<String> combination_of_relevant_transitions_vector = new Vector<String>();
	        
	        for(String relevant_transition : result)
	        {
	        	int first_underscore = relevant_transition.indexOf("_");
				int last_underscore = relevant_transition.lastIndexOf("_");
				String automaton_id = relevant_transition.substring(first_underscore+1, last_underscore);
	        	
				//
				// If a combination under construction contains two relevant transitions of the same automaton, 
				// the combination is immediately discarded. Conversely, if the combination contains ONLY relevant transitions
				// taken from different automata, a new "RelevantTransition" object
				//
				if(automata_ID_of_relevant_transitions_involved_in_a_combination_vector.contains(automaton_id)) {
					//str += transition.toString() + ", " + "(" + automata_id + ")";
					return;
				}
				else  {
					//str += transition.toString() + ", ";					
					combination_of_relevant_transitions_vector.addElement(relevant_transition);
					automata_ID_of_relevant_transitions_involved_in_a_combination_vector.addElement(automaton_id);
				}
	        }
	        
	        // System.out.print(label + " -- ");
	        // System.out.println(str);
	        
	        Vector<String> original_transitions_associated_to_the_label_vector = new Vector<String>();
	        for(int hu=0;hu<arr.length;hu++) {
	        	original_transitions_associated_to_the_label_vector.addElement(arr[hu].toString());
	        }
	        	        
	        String cotID = "ct" + Constants.getCombinationOfRelevantTransitions_vector_sync_move().size();
	        
	        CombinationOfRelevantTransitions cot = new CombinationOfRelevantTransitions(cotID, label, original_k_value, combination_of_relevant_transitions_vector, original_transitions_associated_to_the_label_vector);
	                
	        // System.out.println(cot.getId() + " -- " + cot.getLabel() + " --> " + cot.getCombination_of_transitions_vector() + " -- " + cot.getOriginal_transitions_associated_to_the_label_vector() + " -- " + cot.getPDDL_preconditions() + " -- " + cot.getPDDL_effects());
	        	        
	        Constants.getCombinationOfRelevantTransitions_vector_sync_move().addElement(cot);
	        /*
	        if(Constants.getMenuPerspective().getSinkStatesMenuItem().isSelected() && cot.containsSinkstates()) {
	        	Constants.getCombinationOfRelevantTransitions_vector_sync_move().removeElement(cot);
	        	// System.out.println("This combination of transition contains a sink state ");
	        } */
	        return;
	    }       
	    for (int i = startPosition; i <= arr.length-len; i++){
	        result[result.length - len] = arr[i].toString();
	        findCombinationsOfTransitions_sync_move(arr, label, len-1, original_k_value, i+1, result);
	    }
	}
	
	public static void findCombinationsOfTransitions_del_move(Object[] arr, String label, int len, int original_k_value, int startPosition, String[] result) {
	    if (len == 0){
	       	       
	    	//String str = "";
	    	 
	        Vector<String> automata_ID_of_relevant_transitions_involved_in_a_combination_vector = new Vector<String>();
	        Vector<String> combination_of_relevant_transitions_vector = new Vector<String>();
	        
	        for(String relevant_transition : result)
	        {
	        	int first_underscore = relevant_transition.indexOf("_");
				int last_underscore = relevant_transition.lastIndexOf("_");
				String automaton_id = relevant_transition.substring(first_underscore+1, last_underscore);
	        	
				//
				// If a combination under construction contains two relevant transitions of the same automaton, 
				// the combination is immediately discarded. Conversely, if the combination contains ONLY relevant transitions
				// taken from different automata, a new "RelevantTransition" object
				//
				if(automata_ID_of_relevant_transitions_involved_in_a_combination_vector.contains(automaton_id)) {
					//str += transition.toString() + ", " + "(" + automata_id + ")";
					return;
				}
				else  {
					//str += transition.toString() + ", ";					
					combination_of_relevant_transitions_vector.addElement(relevant_transition);
					automata_ID_of_relevant_transitions_involved_in_a_combination_vector.addElement(automaton_id);
				}
	        }
	        
	        // System.out.print(label + " -- ");
	        // System.out.println(str);
	        
	        Vector<String> original_transitions_associated_to_the_label_vector = new Vector<String>();
	        for(int hu=0;hu<arr.length;hu++) {
	        	original_transitions_associated_to_the_label_vector.addElement(arr[hu].toString());
	        }
	        	        
	        String cotID = "ct" + Constants.getCombinationOfRelevantTransitions_vector_del_move().size();
	        
	        CombinationOfRelevantTransitions cot = new CombinationOfRelevantTransitions(cotID, label, original_k_value, combination_of_relevant_transitions_vector, original_transitions_associated_to_the_label_vector);
	                
	        // System.out.println(cot.getId() + " -- " + cot.getLabel() + " --> " + cot.getCombination_of_transitions_vector() + " -- " + cot.getOriginal_transitions_associated_to_the_label_vector() + " -- " + cot.getPDDL_preconditions() + " -- " + cot.getPDDL_effects());
	        	        
	        Constants.getCombinationOfRelevantTransitions_vector_del_move().addElement(cot);
	        /*
	        if(Constants.getMenuPerspective().getSinkStatesMenuItem().isSelected() && cot.containsSinkstates()) {
	        	Constants.getCombinationOfRelevantTransitions_vector_del_move().removeElement(cot);
	        	// System.out.println("This combination of transition contains a sink state ");
	        } */
	        return;
	    }       
	    for (int i = startPosition; i <= arr.length-len; i++){
	        result[result.length - len] = arr[i].toString();
	        findCombinationsOfTransitions_del_move(arr, label, len-1, original_k_value, i+1, result);
	    }
	}
	
	
	public static void findCombinationsOfAcceptingStates(Object[] arr, int len, int startPosition, String[] result) {
	    
		if (len == 0){
	       	       
	    	//String str = "";
	    	 
	        Vector<String> automata_ID_of_accepting_states_involved_in_a_combination_vector = new Vector<String>();
	        Vector<String> combination_of_accepting_states_vector = new Vector<String>();
	        
	        for(String accepting_state : result)
	        {
	        	int first_underscore = accepting_state.indexOf("_");
				int last_underscore = accepting_state.lastIndexOf("_");
				String automaton_id = accepting_state.substring(first_underscore+1, last_underscore);
	        	
				//
				// If a combination under construction contains two accepting states of the same automaton, 
				// the combination is immediately discarded. Conversely, if the combination contains ONLY accepting states
				// taken from different automata, a new "CombinationofAcceptingState" object is generated. 
				//
				if(automata_ID_of_accepting_states_involved_in_a_combination_vector.contains(automaton_id)) {
					//str += transition.toString() + ", " + "(" + automata_id + ")";
					return;
				}
				else  {
					//str += transition.toString() + ", ";					
					combination_of_accepting_states_vector.addElement(accepting_state);
					automata_ID_of_accepting_states_involved_in_a_combination_vector.addElement(automaton_id);
				}
	        }
	        
	        // System.out.print(label + " -- ");
	        // System.out.println(str);
	        	        	        
	        String cosID = "cs" + Constants.getCombinationOfAcceptingStates_vector().size();
	        
	        CombinationOfAcceptingStates coas = new CombinationOfAcceptingStates(cosID, combination_of_accepting_states_vector);
	                
	        //System.out.println(coas.getId() + " --> " + coas.getCombinationOfAcceptingStates_vector());
	        	        
	        Constants.getCombinationOfAcceptingStates_vector().addElement(coas);
	        return;
	    }       
	    for (int i = startPosition; i <= arr.length-len; i++){
	        result[result.length - len] = arr[i].toString();
	        findCombinationsOfAcceptingStates(arr, len-1, i+1, result);
	    }
	}
	
	
	
	public static StringBuffer createPropositionalDomain(Trace trace) {
			
		StringBuffer PDDL_domain_buffer = new StringBuffer();
		
		PDDL_domain_buffer.append("(define (domain Mining)\n");
		PDDL_domain_buffer.append("(:requirements :typing :equality)\n");
		PDDL_domain_buffer.append("(:types state)\n\n");
		
		PDDL_domain_buffer.append("(:predicates\n");	
		PDDL_domain_buffer.append("(currstate ?s - state)\n");			
		PDDL_domain_buffer.append(")\n\n");			
		
		//if(Constants.getPlannerPerspective().getCostCheckBox().isSelected()) {
			PDDL_domain_buffer.append("(:functions\n");	
			PDDL_domain_buffer.append("(total-cost)\n");			
			PDDL_domain_buffer.append(")\n\n");		
		//}
		
		for(int i=0;i<Constants.getCombinationOfRelevantTransitions_vector().size();i++) {
			
			CombinationOfRelevantTransitions cot = Constants.getCombinationOfRelevantTransitions_vector().elementAt(i);
			String label_of_the_cot =  cot.getLabel();
				
			//
			// If a combination of transitions is related to a label (i.e., to a symbol of the alphabet) not contained in the 
			// alphabet of the trace or in the alphabet of the automata, no action will be generated for this transition.
			//
			if(trace.getTraceAlphabet_vector().contains(label_of_the_cot) || Constants.getAlphabetOfTheConstraints_vector().contains(label_of_the_cot)) {
				
				//
				// Generate an ADD action for any combination of transitions
				//
				
				//label_of_the_cot = label_of_the_cot.replaceAll("\"", "");
				
				PDDL_domain_buffer.append("(:action add" + "-" + label_of_the_cot + "-" + cot.getId() + "\n");
				PDDL_domain_buffer.append(":precondition ");
								
				if(cot.getNumberOfConditionsInThePDDLPreconditions()>1) PDDL_domain_buffer.append("(and ");
								
				PDDL_domain_buffer.append(cot.getPDDL_preconditions());
								
				if(cot.getNumberOfConditionsInThePDDLPreconditions()>1) 
					PDDL_domain_buffer.append(")\n");
				else 
					PDDL_domain_buffer.append("\n");
				
				PDDL_domain_buffer.append(":effect (and ");
				PDDL_domain_buffer.append(cot.getPDDL_effects());
				
				//if(Constants.getPlannerPerspective().getCostCheckBox().isSelected()) {
					PDDL_domain_buffer.append(" (increase (total-cost) ");	
										
						for(int yu=0;yu<Constants.getActivitiesCost_vector().size();yu++) {
							Vector<String> specificTraceCostVector = Constants.getActivitiesCost_vector().elementAt(yu);	
							if(specificTraceCostVector.elementAt(0).equalsIgnoreCase(label_of_the_cot)) {
								int fixed_cost = Integer.parseInt(specificTraceCostVector.elementAt(1));
								int variable_cost = cot.getCost();
								int total_cost = fixed_cost + variable_cost;
								PDDL_domain_buffer.append(String.valueOf(total_cost) + "))\n");
								break;
						}
					}
				//}
				//else {PDDL_domain_buffer.append(")\n");}
				
				PDDL_domain_buffer.append(")\n\n");
				
			}
		}
		
		//
		// Generate a Move Sync for any combination of transitions.
		//
		for(int i=0;i<Constants.getCombinationOfRelevantTransitions_vector_sync_move().size();i++) {
			CombinationOfRelevantTransitions cot = Constants.getCombinationOfRelevantTransitions_vector_sync_move().elementAt(i);
			String label_of_the_cot =  cot.getLabel();
			
			if(trace.getTraceAlphabet_vector().contains(label_of_the_cot) || Constants.getAlphabetOfTheConstraints_vector().contains(label_of_the_cot)) {
				
				for(int k=0;k<trace.getOriginalTraceContent_vector().size();k++) {
					
					if(trace.getOriginalTraceContent_vector().elementAt(k).equalsIgnoreCase(label_of_the_cot)) {
						PDDL_domain_buffer.append("(:action sync" + "-" + label_of_the_cot + "-" + cot.getId() + "\n");
						PDDL_domain_buffer.append(":precondition (and ");
						PDDL_domain_buffer.append("(currstate t" + k + ") ");
						PDDL_domain_buffer.append(cot.getPDDL_preconditions() + ")\n");
						PDDL_domain_buffer.append(":effect (and ");
						int j = k+1;
						PDDL_domain_buffer.append("(not (currstate t" + k + ")) " + "(currstate t" + j + ") " );
						PDDL_domain_buffer.append(cot.getPDDL_effects() + ")\n");
						PDDL_domain_buffer.append(")\n\n");
					}
				}
			}			
		}
				
		
		//
		// For any activity of the trace, generate a Move Sync, representing a further syncronous move, which can be performed in any state different from the ones in the preconditions of the combinations.
		//
		for(int gk=0;gk<trace.getOriginalTraceContent_vector().size();gk++) {

			int j = gk+1;
			
			//Generate a MOVE SYNC
						
			StringBuffer preconditionsSB = new StringBuffer();
						
			String act = trace.getOriginalTraceContent_vector().elementAt(gk);
			Collection<String> values =  new ArrayList<String>();
			Collection<String> cost_automaton_values = new ArrayList<String>();
			
			Iterator<String> it_1 = Constants.getRelevantTransitions_map().get(act).iterator();
			while(it_1.hasNext()) {
				String value = new String((String) it_1.next());
				values.add(value);
			}
			
			Iterator<String> it_2 = Constants.getCostAutomatonRelevantTransitions_map().get(act).iterator();
			while(it_2.hasNext()) {
				String value = new String((String) it_2.next());
				cost_automaton_values.add(value);
			}
			
			values.addAll(cost_automaton_values);

			Object[] values_array = values.toArray();
			
			for(int l=0;l<values_array.length;l++) {
				String transition_id = values_array[l].toString();
				RelevantTransition rt = Utilities.getRelevantTransition(transition_id);
				preconditionsSB.append("(not " + rt.getPDDL_preconditions() + ") ");
			}
			
			PDDL_domain_buffer.append("(:action sync" + "-" + act + "-t" + gk + "t" + j + "\n");
			PDDL_domain_buffer.append(":precondition ");
			
			if(values_array.length>0) PDDL_domain_buffer.append("(and ");
			
			
			PDDL_domain_buffer.append("(currstate t" + gk + ") ");
			
			if(values_array.length>0) PDDL_domain_buffer.append(preconditionsSB + ")\n");
			
			
			else PDDL_domain_buffer.append("\n");
			
			PDDL_domain_buffer.append(":effect ");
			PDDL_domain_buffer.append("(and (not (currstate t" + gk + ")) " + "(currstate t" + j + "))" );
			PDDL_domain_buffer.append(") \n\n");
			
		}
		
		//
		// Generate a Del action for any combination of transitions of the cost automaton.
		//
		for(int i=0;i<Constants.getCombinationOfRelevantTransitions_vector_del_move().size();i++) {
			CombinationOfRelevantTransitions cot = Constants.getCombinationOfRelevantTransitions_vector_del_move().elementAt(i);

			String label_of_the_cot =  cot.getLabel();
			
			if(trace.getTraceAlphabet_vector().contains(label_of_the_cot) || Constants.getAlphabetOfTheConstraints_vector().contains(label_of_the_cot)) {
				
				for(int k=0;k<trace.getOriginalTraceContent_vector().size();k++) {
					
					if(trace.getOriginalTraceContent_vector().elementAt(k).equalsIgnoreCase(label_of_the_cot)) {
						PDDL_domain_buffer.append("(:action del" + "-" + label_of_the_cot + "-" + cot.getId() + "\n");
						PDDL_domain_buffer.append(":precondition (and ");
						PDDL_domain_buffer.append("(currstate t" + k + ") ");
						PDDL_domain_buffer.append(cot.getPDDL_preconditions() + ")\n");
						PDDL_domain_buffer.append(":effect (and ");
						int j = k+1;
						PDDL_domain_buffer.append("(not (currstate t" + k + ")) " + "(currstate t" + j + ") " );
						PDDL_domain_buffer.append(cot.getPDDL_effects());
						
						//if(Constants.getPlannerPerspective().getCostCheckBox().isSelected()) {
						
							PDDL_domain_buffer.append(" (increase (total-cost) ");
							
							for(int yu=0;yu<Constants.getActivitiesCost_vector().size();yu++) {
								Vector<String> specificTraceCostVector = Constants.getActivitiesCost_vector().elementAt(yu);	
								if(specificTraceCostVector.elementAt(0).equalsIgnoreCase(label_of_the_cot)) {
									int fixed_cost = Integer.parseInt(specificTraceCostVector.elementAt(2));
									int variable_cost = cot.getCost();
									int total_cost = fixed_cost + variable_cost;
									PDDL_domain_buffer.append(String.valueOf(total_cost) + "))\n");
									break;
								}
							}
							
						//}
						//else {PDDL_domain_buffer.append(")\n");}
						
						PDDL_domain_buffer.append(") \n\n");
					}
				}
			}			
		}
		
		
		//
		// For any activity of the trace, generate a Del action, representing a further Del move, which can be performed in any state different from the ones in the preconditions of the combinations.
		//
		for(int gk=0;gk<trace.getOriginalTraceContent_vector().size();gk++) {

			int j = gk+1;
			
			//Generate a DEL ACTION
						
			StringBuffer preconditionsSB = new StringBuffer();
						
			String act = trace.getOriginalTraceContent_vector().elementAt(gk);
			
			Collection<String> values =  new ArrayList<String>();
			
			Iterator<String> it_1 = Constants.getCostAutomatonRelevantTransitions_map().get("del_"+act).iterator();
			while(it_1.hasNext()) {
				String value = new String((String) it_1.next());
				values.add(value);
			}
			
			Object[] values_array = values.toArray();
			
			for(int l=0;l<values_array.length;l++) {
				String transition_id = values_array[l].toString();
				RelevantTransition rt = Utilities.getRelevantTransition(transition_id);
				preconditionsSB.append("(not " + rt.getPDDL_preconditions() + ") ");
			}
			
			PDDL_domain_buffer.append("(:action del" + "-" + act + "-t" + gk + "t" + j + "\n");
			PDDL_domain_buffer.append(":precondition ");
			
			if(values_array.length>0) PDDL_domain_buffer.append("(and ");
			
			
			PDDL_domain_buffer.append("(currstate t" + gk + ") ");
			
			if(values_array.length>0) PDDL_domain_buffer.append(preconditionsSB + ")\n");
			
			
			else PDDL_domain_buffer.append("\n");
			
			PDDL_domain_buffer.append(":effect ");
			PDDL_domain_buffer.append("(and (not (currstate t" + gk + ")) " + "(currstate t" + j + ") " );
			
			//if(Constants.getPlannerPerspective().getCostCheckBox().isSelected()) {
				
				PDDL_domain_buffer.append(" (increase (total-cost) ");
				
				for(int yu=0;yu<Constants.getActivitiesCost_vector().size();yu++) {
					Vector<String> specificTraceCostVector = Constants.getActivitiesCost_vector().elementAt(yu);	
					if(specificTraceCostVector.elementAt(0).equalsIgnoreCase(act)) { //
						int fixed_cost = Integer.parseInt(specificTraceCostVector.elementAt(2));
						PDDL_domain_buffer.append(String.valueOf(fixed_cost) + "))\n");
						break;
					}
				}
				
			//}
			//else {PDDL_domain_buffer.append(") \n\n");}
			
			PDDL_domain_buffer.append(") \n\n");
						
		}

					
			StringBuffer PDDL_temp_effects_sb = new StringBuffer(":effect (and ");
			
			//
			// Vector used to record the ID of the automata related to the abstract states.
			//
			Vector<String> automata_id_of_abstract_states_vector = new Vector<String>(); 
						
			for(int op=0;op<Constants.getAutomataAbstractAcceptingStates_vector().size();op++) {
				String abstract_state_id = Constants.getAutomataAbstractAcceptingStates_vector().elementAt(op);
				
				PDDL_temp_effects_sb.append("(currstate " + abstract_state_id + ") ");
				
				//System.out.println(state_id);
				int first_underscore = abstract_state_id.indexOf("_");
				int last_underscore = abstract_state_id.lastIndexOf("_");
				String automaton_id = abstract_state_id.substring(first_underscore+1, last_underscore);
				automata_id_of_abstract_states_vector.addElement(automaton_id);
			}
					
			//
			// For any combination of accepting states, we generate a PDDL action to reach all the abstract states of the automata. 
			// 
			//
			if(Constants.getCombinationOfAcceptingStates_vector().size() > 1) {
			for(int jk=0;jk<Constants.getCombinationOfAcceptingStates_vector().size();jk++) {
				
				StringBuffer PDDL_temp_effects_2_sb = new StringBuffer();
						
				CombinationOfAcceptingStates coas = Constants.getCombinationOfAcceptingStates_vector().elementAt(jk);
				
				PDDL_domain_buffer.append("(:action goto" + "-abstract_states-" + coas.getId() +  "\n");
				PDDL_domain_buffer.append(":precondition (and (currstate t" + trace.getOriginalTraceContent_vector().size()+ ") ");
				
				for(int hgf=0;hgf<coas.getCombinationOfAcceptingStates_vector().size();hgf++) {
					String specific_accepting_state_ID_in_the_combination = coas.getCombinationOfAcceptingStates_vector().elementAt(hgf);
					PDDL_domain_buffer.append("(currstate " + specific_accepting_state_ID_in_the_combination + ") ");
					
					int first_underscore_2 = specific_accepting_state_ID_in_the_combination.indexOf("_");
					int last_underscore_2 = specific_accepting_state_ID_in_the_combination.lastIndexOf("_");
					String automaton_id_2 = specific_accepting_state_ID_in_the_combination.substring(first_underscore_2+1, last_underscore_2);
					if(automata_id_of_abstract_states_vector.contains(automaton_id_2)) {
						PDDL_temp_effects_2_sb.append("(not (currstate " + specific_accepting_state_ID_in_the_combination + ")) ");
					}
				}
				PDDL_domain_buffer.append(")\n" + PDDL_temp_effects_sb + PDDL_temp_effects_2_sb + ")\n)\n\n");
				
			}
			}
			
		PDDL_domain_buffer.append(")");
		
		return PDDL_domain_buffer;
	}
	
	
	public static StringBuffer createPropositionalProblem(Trace trace) {

		StringBuffer PDDL_objects_buffer = new StringBuffer();	
		StringBuffer PDDL_init_buffer = new StringBuffer();
		StringBuffer PDDL_cost_buffer = new StringBuffer();
		StringBuffer PDDL_goal_buffer = new StringBuffer();
		StringBuffer PDDL_problem_buffer = new StringBuffer();
		
		PDDL_objects_buffer.append("(define (problem Align) (:domain Mining)\n");
		PDDL_objects_buffer.append("(:objects\n");	
		
		for(int l=0;l<=trace.getOriginalTraceContent_vector().size();l++) {
			PDDL_objects_buffer.append("t" + l + " - state\n");
		}

		PDDL_objects_buffer.append(Constants.getPDDLAutomataAllStates_sb());
		PDDL_objects_buffer.append(")\n");	
		
		//
		// Definition of the INITIAL STATE
		//
		PDDL_init_buffer = new StringBuffer("(:init\n");
		PDDL_init_buffer.append("(currstate t0)\n");
		
		PDDL_init_buffer.append(Constants.getPDDLAutomataInitialStates_sb());
				
		//if(Constants.getPlannerPerspective().getCostCheckBox().isSelected()) {
			PDDL_cost_buffer.append("(= (total-cost) 0)\n");
			PDDL_init_buffer.append(PDDL_cost_buffer);
		//}
		PDDL_init_buffer.append(")\n");	
		
		//
		// Definition of the GOAL STATE
		//
		
		PDDL_goal_buffer.append("(:goal\n");
		PDDL_goal_buffer.append("(and\n");
				
		PDDL_goal_buffer.append("(currstate t" + trace.getOriginalTraceContent_vector().size() + ")\n");
		
		PDDL_goal_buffer.append(Constants.getPDDLAutomataAcceptingStates_sb());
		

		PDDL_goal_buffer.append("))\n");
		
		//if(Constants.getPlannerPerspective().getCostCheckBox().isSelected()) 
			PDDL_goal_buffer.append("(:metric minimize (total-cost))\n");	
		
		PDDL_problem_buffer.append(PDDL_objects_buffer);
		PDDL_problem_buffer.append(PDDL_init_buffer);
		PDDL_problem_buffer.append(PDDL_goal_buffer);	
		PDDL_problem_buffer.append(")");	

		return PDDL_problem_buffer;
	}
	
	public static RelevantTransition getRelevantTransition(String tr_id) {
		
		RelevantTransition rt = null;
				
		for(int l=0;l<Constants.getRelevantTransitions_vector().size();l++) {
			rt = Constants.getRelevantTransitions_vector().elementAt(l);
			
			if(rt.getId().equalsIgnoreCase(tr_id))
				return rt;
		}
		
		//aggiunto. cerca anche tra le trans rilevanti del cost automaton.
		for(int l=0; l<Constants.getCostAutomatonRelevantTransitions_vector().size(); l++) {
			rt = Constants.getCostAutomatonRelevantTransitions_vector().elementAt(l);
			
			if(rt.getId().equalsIgnoreCase(tr_id))
				return rt;
		}
		
		return rt;
	}
	
	//
	// Method used to empty the content of a folder whose name is passed as argument.
	//
	public static void emptyFolder(String folderName) {
	 	File index = new File(folderName);            	
    	String[]entries = index.list();
    	for(String s: entries){
    	    File currentFile = new File(index.getPath(),s);
    	    currentFile.delete();
    	}
	}
		
	//
	// Method used to create a new file with a specific content.
	//
	public static void createFile(String nomeFile, StringBuffer buffer) {
		 
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


	//
	// Method that returns TRUE if the string passed as input is in the UPPER CASE format.
	//
	public static boolean isUpperCase(String str){
		
		for(int i=0; i<str.length(); i++){
			char c = str.charAt(i);
			
			if(Character.isUpperCase(c))
				return true;
			}
		return false;
	}
	
	//
	// Method that takes in input a trace id and returns the corresponding Trace object.
	//
	public static Trace getTraceById(String str){
		Trace trace = null;
		for(int k=0;k<Constants.getAllTraces_vector().size();k++) {
	   		 trace = Constants.getAllTraces_vector().elementAt(k);
	   		 if(trace.getTraceID().equalsIgnoreCase(str)) {
	   			 return trace;
	   		 }	   	
		}
		return trace;
	}

	//
	// Method that prints the details related to an object of kind Trace passed in input.
	//
	public static void printTraceDetails(Trace trace){
		System.out.println("Trace ID: " + trace.getTraceID());
		System.out.println("Name: " + trace.getTraceName());		
		System.out.println("Alphabet vector: " + trace.getTraceAlphabet_vector());
		System.out.println("Original Content vector: " + trace.getOriginalTraceContent_vector());
		System.out.println("Original Content string: " + trace.getOriginalTraceContent_string());
		System.out.println("Content With Activities Instances vector: " + trace.getTraceContentWithActivitiesInstances_vector());		
		System.out.println("Number Of Activity Instances vector: " + trace.getNumberOfActivityInstances_Hashtable());
		System.out.println("Associations To Activity Instances: " + trace.getAssociationsToActivityInstances_Hashtable());
		System.out.println("Missing Activities: " + trace.getTraceMissingActivities_vector());
		System.out.println("Alphabet with missing activities of the constraints: " + trace.getTraceAlphabetWithMissingActivitiesOfTheConstraints_vector());
		System.out.println();
	}
	
}