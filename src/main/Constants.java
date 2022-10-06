package main;

import java.util.Hashtable;
import java.util.Vector;
import javax.swing.JFrame;

import org.processmining.ltl2automaton.plugins.automaton.Automaton;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import view.AlphabetPerspective;
import view.MenuPerspective;
import view.PlannerPerspective;
import view.ConstraintsPerspective;
import view.CostPerspective;
import view.TracesPerspective;

public class Constants {

	//
	// Main frame and required JDialogs/JPanels to realize the GUI of the software.
	//
	private static JFrame desktop;
	private static MenuPerspective menuPerspective;
	private static AlphabetPerspective alphabetPanel;
	private static TracesPerspective tracePanel;
	private static ConstraintsPerspective constraintsPanel;
	
	// -> This component is re-instantiated every time a transition from the ConstraintsPerspective panel to the PlannerPerspective panel takes place.
	private static CostPerspective costPanel;
	
	// -> This component is re-instantiated every time a transition from the ConstraintsPerspective panel to the PlannerPerspective panel takes place.
	private static PlannerPerspective plannerPanel;
		
	//
	// Vector that records the complete alphabet of activities that appear both in the log traces and in the Declare/LTL constraints. 
	// Notice that the repository may contain activities that are never used in any trace or in any constraints. 
	// Conversely, the union of activities appearing in the traces and in the constraints is ALWAYS included in this repository.
	//
	// -> This vector is re-initialized and populated during the transition from the AlphabetPerspective panel to the TracesPerspective Panel (after pressing the button "Next Step"). 
	// -> This vector is re-initialized when the 'New' item of the menu 'File' is pressed.
	// -> This vector is re-initialized and automatically populated when an existing XES file is imported into the software (i.e., when the 'Open' Item of the menu is pressed).
	// -> This vector can be potentially augmented when a new Declare model () or DOT automaton () is imported, if its constraints refer to activities not contained in the vector.
	//
	private static Vector<String> activities_repository_vector = new Vector<String>();	
		
	//
	// Vector that records the alphabet of the activities appearing just in the log traces. 
	// Notice that such an alphabet may include activities NOT used in any Declare/LTL constraint.
	//
	// -> This vector is re-initialized and populated during the transition from the TracesPerspective panel to the ConstraintsPerspective Panel (after pressing the button "Next Step"). 
	//	
	private static Vector<String> alphabet_of_the_traces_vector = new Vector<String>();	
	
	//
	// Vector that records the alphabet of activities that appear in the Declare/LTL constraints.
	// Notice that such an alphabet may include activities that DO NOT appear in any trace.
	//
	// -> This vector is re-initialized and populated during the transition from the ConstraintsPerspective panel to the PlannerPerspective panel (after pressing the button "Next Step"). 
	//
	private static Vector<String> alphabet_of_the_constraints_vector = new Vector<String>();	
		
	//
	// Vector that records all the traces (represented as java objects of kind "Trace") of the log. 
	// -> This vector is re-initialized when a new log is created from scratch (i.e., when the 'New' Item of the menu is pressed) and when 
	//    an existing event log from a XES file is imported (i.e., when the 'Open' Item of the menu is pressed). 
	// -> This vector is populated on-the-fly during the usage of the TracesPerspective panel, each time a new trace is created/removed or when 
	//    an activity is inserted/moved or removed in/from a trace. The pressing of the button "Next Step" DOES NOT modify this vector, which is 
	//    instead updated each time a modification on an existing trace is performed. 
	// -> This vector is re-initialized and populated when an existing XES file is imported into the software (i.e., when the 'Open' Item of the menu is pressed).
	//
	private static Vector<Trace> all_traces_vector = new Vector<Trace>();
	
	//
	// Vector that records all the LTL/Declare constraints, represented as String objects. 
	//
	// -> This vector is re-initialized and populated during the transition from the ConstraintsPerspective panel to the PlannerPerspective panel (after pressing the button "Next Step"). 
	//  
	private static Vector<String> all_constraints_vector = new Vector<String>();
		
	//
	// Vector that records the cost of adding/removing activities in/from a trace
	// It is a Vector of Vectors, where each Vector is built in the following way:
	// - the first element is the name of the activity, 
	// - the second element is the cost of adding the activity into the trace
	// - the third element is the cost of removing the activity from the trace
	//	
	// -> This vector is re-initialized and populated during the transition from the ConstraintsPerspective panel to the PlannerPerspective panel (after pressing the button "Next Step"). 
	//
	private static Vector<Vector<String>> activities_cost_vector = new Vector<Vector<String>>();	
	
	//
	// Variables used to record the minimum length and the maximum length of a log trace.
	//	
	// -> This variables are re-initialized during the transition from the ConstraintsPerspective panel to the PlannerPerspective panel (after pressing the button "Next Step"). 
	//
	private static int minimum_length_of_a_trace = 0;
	private static int maximum_length_of_a_trace = 0;
	
	//
	// This Hashtable records the content of any trace (the KEY is the name of the trace, the VALUE is the original content of the trace).
	// Notice that if a trace TY has the SAME content of an already included trace TX (i.e., if two traces have the same content), TY will be discarded.
	//	
	// -> This variables are re-initialized during the transition from the ConstraintsPerspective panel to the PlannerPerspective panel (after pressing the button "Next Step"). 
	//
	private static Hashtable<String, String> content_of_any_different_trace_Hashtable;
	
	//########################################################################
	/////////////////////// **** AAAI-17 encoding **** ///////////////////////
	//########################################################################
	
	//
	// Vector that records all the automata (objects of kind Automaton) associated to the Declare constraints (for AAAI17 encoding).
	//	
	// -> This vector is re-initialized and populated during the transition from the ConstraintsPerspective panel to the PlannerPerspective panel (after pressing the button "Next Step"). 
	//
	private static Vector<Automaton> automata_vector = new Vector<Automaton>();
	
	//
	// Vector that records all the relevant transitions objects (connecting two different states of an automaton) of the automata associated to the Declare/LTL constraints (for AAAI17 encoding). 
	//	
	// -> This vector is re-initialized and populated during the transition from the ConstraintsPerspective panel to the PlannerPerspective panel (after pressing the button "Next Step"). 
	//
	private static Vector<RelevantTransition> relevant_transitions_vector = new Vector<RelevantTransition>();
	
	
	//
	// Vector that records all the relevant transitions objects of the cost automata. 
	//
	// -> This vector is re-initialized and populated during the transition from the ConstraintsPerspective panel to the PlannerPerspective panel (after pressing the button "Next Step"). 
	//
	private static Vector<RelevantTransition> cost_automaton_relevant_transitions_vector = new Vector<RelevantTransition>();
		
	//
	// Vector that records all the valid combinations of relevant transitions of automata associated to the LTL/Declare constraints 
	// and of cost automata (of the cost automata, it considers only the transitions of type ADD).
	// Each instance of the vector is a unique combination of transitions related to a specific label.
	//	
	// -> This vector is re-initialized and populated during the transition from the ConstraintsPerspective panel to the PlannerPerspective panel (after pressing the button "Next Step"). 
	//
	private static Vector<CombinationOfRelevantTransitions> combination_of_transitions_vector = new Vector<CombinationOfRelevantTransitions>();
	
	//
	// Vector that records all the valid combinations of relevant transitions of cost automata of type SYNC.
	// Each instance of the vector is a unique combination of transitions related to a specific label.
	//	
	// -> This vector is re-initialized and populated during the transition from the ConstraintsPerspective panel to the PlannerPerspective panel (after pressing the button "Next Step"). 
	//
	private static Vector<CombinationOfRelevantTransitions> combination_of_transitions_vector_sync_move = new Vector<CombinationOfRelevantTransitions>();
	
	//
	// Vector that records all the valid combinations of relevant transitions of cost automata of type DEL.
	// Each instance of the vector is a unique combination of transitions related to a specific label.
	//	
	// -> This vector is re-initialized and populated during the transition from the ConstraintsPerspective panel to the PlannerPerspective panel (after pressing the button "Next Step"). 
	//
	private static Vector<CombinationOfRelevantTransitions> combination_of_transitions_vector_del_move = new Vector<CombinationOfRelevantTransitions>();


	
	//
	// The Multimap "relevant_transitions_map" will contain the list of relevant transitions taken from any automaton and associate them 
	// to their specific label (e.g., a=[tr_0_0, tr_1_0], b=[tr_1_2], etc.) (for AAAI17 encoding).        				
	//	
	// -> This map is re-initialized and populated during the transition from the ConstraintsPerspective panel to the PlannerPerspective panel (after pressing the button "Next Step"). 
	//
	private static Multimap<String, String> relevant_transitions_map = HashMultimap.create();
	
	//
	// The Multimap "cost_automaton_relevant_transitions_map" will contain the list of relevant transitions taken from the cost automaton and associate them 
	// to their specific label (e.g., a=[tr_0_0, tr_1_0], b=[tr_1_2], etc.).        				
	//	
	private static Multimap<String, String> cost_automaton_relevant_transitions_map = HashMultimap.create();
		
	
	//
	// Vectors used to record all the states/the accepting states/the initial states of the automata associated to 
	// the LTL/Declare constraints (for AAAI17 encoding).
	//	
	// -> These vectors are re-initialized and populated during the transition from the ConstraintsPerspective panel to the PlannerPerspective panel (after pressing the button "Next Step"). 
	//
	private static Vector<String> automata_all_states_vector = new Vector<String>();
	private static Vector<String> automata_accepting_states_vector = new Vector<String>();
	private static Vector<String> automata_initial_states_vector = new Vector<String>();
	
	//
	// Auxiliary StringBuffers used to record in PDDL (for the initial state) all the states/the accepting states/the initial states 
	// of the automata associated to the Declare/LTL constraints in the PDDL format (for AAAI17 encoding).
	//	
	// -> These StringBuffers are re-initialized and populated during the transition from the ConstraintsPerspective panel to the PlannerPerspective panel (after pressing the button "Next Step"). 
	//
	private static StringBuffer PDDL_automata_all_states_sb = new StringBuffer();
	private static StringBuffer PDDL_automata_accepting_states_sb = new StringBuffer();
	private static StringBuffer PDDL_automata_initial_states_sb = new StringBuffer();
	
	
	//
	// Vector that records all the valid combinations of accepting states (combinations containing 
	// states from the same automaton are discarded) of automata associated to the LTL/Declare constraints (for AAAI17 encoding).
	// Each instance of the vector is a unique combination of accepting states.
	//	
	// -> This vector is re-initialized and populated during the transition from the ConstraintsPerspective panel to the PlannerPerspective panel (after pressing the button "Next Step"). 
	//
	private static Vector<CombinationOfAcceptingStates> combination_of_accepting_states_vector = new Vector<CombinationOfAcceptingStates>();
		
	//
	// Vector used to record all the non accepting sink states of the automata associated to 
	// the Declare/LTL constraints (for AAAI17 encoding).
	//	
	// -> This vector is re-initialized and populated during the transition from the ConstraintsPerspective panel to the PlannerPerspective panel (after pressing the button "Next Step"). 
	//
	private static Vector<String> automata_sink_non_accepting_states_vector = new Vector<String>();
	
	//
	// Vector used to record the name of the "abstract" accepting states required for all those automata associated to the Declare/LTL constraints 
	// having more than one regular accepting state. In such a case, instead of having several accepting states for one automaton, 
	// a further abstract accepting state is generated: at this point any regular accepting state of the automaton will have a transition 
	// ending in such an abstract state (for AAAI17 encoding, to avoid the presence of OR conditions in the goal of the planning problem).
	//	
	// -> This vector is re-initialized and populated during the transition from the ConstraintsPerspective panel to the PlannerPerspective panel (after pressing the button "Next Step"). 
	//
	private static Vector<String> automata_abstract_accepting_states_vector = new Vector<String>();
		
	
	private static Vector<String> PDDL_activities_vector = new Vector<String>();
	

	public static String LTL_NOT = "\u00AC";
	public static String LTL_OR = "\u2228";
	public static String LTL_AND = "\u2227";
	public static String LTL_IMPLIES = "\u2192";
	public static String LTL_eventually = "\u25C7";
	public static String LTL_globally = "\u25A1";
	public static String LTL_next = "\u25CB";	
	public static String LTL_weak_until = "W".toUpperCase();
	public static String LTL_until = "U".toUpperCase();
	
	public static String PDDL_encoding = "AAAI17"; //It can be equal to "AAAI17" or to "ICAPS16".
	

	
	//
	// Boolean variable used to record the decision to discard or not the duplicated traces of a log during the alignment.
	//
	private static boolean discard_duplicated_traces = false;


	private static boolean back_from_cost_perspective = false;

	public static boolean isBack_from_cost() {
		return back_from_cost_perspective;
	}
	public static void setBack_from_cost(boolean back_from_cost_perspective) {
		Constants.back_from_cost_perspective = back_from_cost_perspective;
	}
	
	///////////////////////////////////////
	// -- LIST OF GETTERS AND SETTERS -- //
	///////////////////////////////////////
	
	//
	// Getters and Setters to retrieve and manipulate the main frame and the required JDialogs/JPanels used to realize the GUI of the software.
	//

	public static JFrame getDesktop() {
		return desktop;
	}
	public static void setDesktop(JFrame desk) {
		desktop = desk;
	}
	public static TracesPerspective getTracesPerspective() {
		return tracePanel;
	}
	public static void setTracesPerspective(TracesPerspective trcPanel) {
		tracePanel = trcPanel;
	}
	public static AlphabetPerspective getAlphabetPerspective() {
		return alphabetPanel;
	}
	public static void setAlphabetPerspective(AlphabetPerspective alphPanel) {
		alphabetPanel = alphPanel;
	}	
	public static ConstraintsPerspective getConstraintsPerspective() {
		return constraintsPanel;
	}
	public static void setConstraintsPerspective(ConstraintsPerspective rlPanel) {
		constraintsPanel = rlPanel;
	}
	public static MenuPerspective getMenuPerspective() {
		return menuPerspective;
	}
	public static void setMenuPerspective(MenuPerspective menu) {
		menuPerspective = menu;
	}
	public static CostPerspective getCostPerspective() {
		return costPanel;
	}
	public static void setCostPerspective(CostPerspective cst_persp) {
		costPanel = cst_persp;
	}
	public static PlannerPerspective getPlannerPerspective() {
		return plannerPanel;
	}
	public static void setPlannerPerspective(PlannerPerspective pl_persp) {
		plannerPanel = pl_persp;
	}
	
	//
	// Getters and Setters to retrieve and manipulate the vectors containing the complete repository of activities, the alphabet of the traces and of the constraints.
	//	
	public static Vector<String> getActivitiesRepository_vector() {
		return activities_repository_vector;
	}
	public static void setActivitiesRepository_vector(Vector<String> v) {
		activities_repository_vector = v;
	}
	public static Vector<String> getAlphabetOfTheConstraints_vector() {
		return alphabet_of_the_constraints_vector;
	}
	public static void setAlphabetOfTheConstraints_vector(Vector<String> constraints_alphabet) {
		alphabet_of_the_constraints_vector = constraints_alphabet;
	}
	public static Vector<String> getAlphabetOfTheTraces_vector() {
		return alphabet_of_the_traces_vector;
	}
	public static void setAlphabetOfTheTraces_vector(Vector<String> traces_alphabet) {
		Constants.alphabet_of_the_traces_vector = traces_alphabet;
	}
	
	//
	// Getters and Setters to retrieve and manipulate the vectors containing all the traces and all the LTL/Declare constraints.
	//		
	public static Vector<Trace> getAllTraces_vector() {
		return all_traces_vector;
	}
	public static void setAllTraces_vector(Vector<Trace> all_traces_vector) {
		Constants.all_traces_vector = all_traces_vector;
	}
	public static Vector<String> getAllConstraints_vector() {
		return all_constraints_vector;
	}
	public static void setAllConstraints_vector(Vector<String> cnt_vector) {
		Constants.all_constraints_vector = cnt_vector;
	}
	
	//
	// Getters and Setters to retrieve and manipulate the vector containing the cost of adding/removing activities into/from the trace.
	//			
	public static Vector<Vector<String>> getActivitiesCost_vector() {
		return activities_cost_vector;
	}
	public static void setActivitiesCost_vector(Vector<Vector<String>> cost_vector) {
		Constants.activities_cost_vector = cost_vector;
	}

	/////////////////////// **** AAAI-17 encoding
	
	//
	// Getters and Setters to retrieve and manipulate the vectors containing all the automata and their relevant transitions (for AAAI17 encoding).
	//		
	public static Vector<Automaton> getAutomata_vector() {
		return automata_vector;
	}
	public static void setAutomata_vector(Vector<Automaton> automata_vector) {
		Constants.automata_vector = automata_vector;
	}
	public static Vector<RelevantTransition> getRelevantTransitions_vector() {
		return relevant_transitions_vector;
	}
	public static void setRelevantTransitions_vector(Vector<RelevantTransition> transitions_vector) {
		Constants.relevant_transitions_vector = transitions_vector;
	}
	
	public static Vector<RelevantTransition> getCostAutomatonRelevantTransitions_vector() {
		return cost_automaton_relevant_transitions_vector;
	}
	public static void setCostAutomatonRelevantTransitions_vector(
			Vector<RelevantTransition> cost_automaton_relevant_transitions_vector) {
		Constants.cost_automaton_relevant_transitions_vector = cost_automaton_relevant_transitions_vector;
	}
	
	//
	// Getters and Setters to retrieve and manipulate the vectors containing all the automata and their relevant transitions (for AAAI17 encoding).
	//
	
	public static Vector<CombinationOfRelevantTransitions> getCombinationOfRelevantTransitions_vector() {
		return combination_of_transitions_vector;
	}
	public static void setCombinationOfRelevantTransitions_vector(Vector<CombinationOfRelevantTransitions> combination_of_transitions_vector) {
		Constants.combination_of_transitions_vector = combination_of_transitions_vector;
	}
	public static Multimap<String, String> getRelevantTransitions_map() {
		return relevant_transitions_map;
	}
	public static void setRelevantTransitions_map(Multimap<String, String> relevant_transitions_map) {
		Constants.relevant_transitions_map = relevant_transitions_map;
	}
	
	public static Multimap<String, String> getCostAutomatonRelevantTransitions_map() {
		return cost_automaton_relevant_transitions_map;
	}
	public static void setCostAutomatonRelevantTransitions_map(
			Multimap<String, String> cost_automaton_relevant_transitions_map) {
		Constants.cost_automaton_relevant_transitions_map = cost_automaton_relevant_transitions_map;
	}
	
	
	public static Vector<CombinationOfRelevantTransitions> getCombinationOfRelevantTransitions_vector_sync_move() {
		return combination_of_transitions_vector_sync_move;
	}
	public static void setCombinationOfRelevantTransitions_vector_sync_move(
			Vector<CombinationOfRelevantTransitions> combination_of_transitions_vector_sync_move) {
		Constants.combination_of_transitions_vector_sync_move = combination_of_transitions_vector_sync_move;
	}
	
	public static Vector<CombinationOfRelevantTransitions> getCombinationOfRelevantTransitions_vector_del_move() {
		return combination_of_transitions_vector_del_move;
	}
	public static void setCombinationOfRelevantTransitions_vector_del_move(
			Vector<CombinationOfRelevantTransitions> combination_of_transitions_vector_del_move) {
		Constants.combination_of_transitions_vector_del_move = combination_of_transitions_vector_del_move;
	}
	
	
	//
	// Getters and Setters to retrieve and manipulate the vector containing all the combination of accepting states (for AAAI17 encoding).
	//
	public static Vector<CombinationOfAcceptingStates> getCombinationOfAcceptingStates_vector() {
		return combination_of_accepting_states_vector;
	}
	public static void setCombinationOfAcceptingStates_vector(Vector<CombinationOfAcceptingStates> combination_vector) {
		Constants.combination_of_accepting_states_vector = combination_vector;
	}
	
	//
	// Getters and Setters to retrieve and manipulate the vectors containing the states/the accepting states/the initial states/the non-accepting 
	// sink states/ the abstract accepting sink states of the automata associated to the LTL/Declare constraints (for AAAI17 encoding).
	//	
	public static Vector<String> getAutomataAllStates_vector() {
		return automata_all_states_vector;
	}
	public static Vector<String> getAutomataAcceptingStates_vector() {
		return automata_accepting_states_vector;
	}
	public static Vector<String> getAutomataInitialStates_vector() {
		return automata_initial_states_vector;
	}
	public static void setAutomataAllStates_vector(Vector<String> automata_all_states) {
		Constants.automata_all_states_vector = automata_all_states;
	}
	public static void setAutomataAcceptingStates_vector(Vector<String> automata_accepting_states) {
		Constants.automata_accepting_states_vector = automata_accepting_states;
	}
	public static void setAutomataInitialStates_vector(Vector<String> automata_initial_states) {
		Constants.automata_initial_states_vector = automata_initial_states;
	}
	public static Vector<String> getAutomataSinkNonAcceptingStates_vector() {
		return automata_sink_non_accepting_states_vector;
	}
	public static Vector<String> getAutomataAbstractAcceptingStates_vector() {
		return automata_abstract_accepting_states_vector;
	}
	public static void setAutomataSinkNonAcceptingStates_vector(Vector<String> automata_sink_non_accepting_states) {
		Constants.automata_sink_non_accepting_states_vector = automata_sink_non_accepting_states;
	}
	public static void setAutomataAbstractAcceptingStates_vector(Vector<String> automata_abstract_goal_states) {
		Constants.automata_abstract_accepting_states_vector = automata_abstract_goal_states;
	}
	
	//
	// Getters and Setters to retrieve and manipulate the auxiliary StringBuffers used to record in PDDL all the states/the accepting states/the initial states 
	// of the automata associated to the Declare/LTL constraints in the PDDL format (for AAAI17 encoding). 
	//		
	public static StringBuffer getPDDLAutomataAllStates_sb() {
		return PDDL_automata_all_states_sb;
	}
	public static StringBuffer getPDDLAutomataAcceptingStates_sb() {
		return PDDL_automata_accepting_states_sb;
	}
	public static StringBuffer getPDDLAutomataInitialStates_sb() {
		return PDDL_automata_initial_states_sb;
	}
	public static void setPDDLAutomataAllStates_sb(StringBuffer pDDL_automata_all_states_sb) {
		PDDL_automata_all_states_sb = pDDL_automata_all_states_sb;
	}
	public static void setPDDLAutomataAcceptingStates_sb(StringBuffer pDDL_automata_accepting_states_sb) {
		PDDL_automata_accepting_states_sb = pDDL_automata_accepting_states_sb;
	}
	public static void setPDDLAutomataInitialStates_sb(StringBuffer pDDL_automata_initial_states_sb) {
		PDDL_automata_initial_states_sb = pDDL_automata_initial_states_sb;
	}
	
	/////////////////////// **** END of AAAI-17 encoding
	
	public static Vector<String> getPDDLActivitiesVector() {
		return PDDL_activities_vector;
	}
	public static void setPDDLActivitiesVector(Vector<String> PDDL_activities_vector) {
		Constants.PDDL_activities_vector = PDDL_activities_vector;
	}
	public static String getPDDL_encoding() {
		return PDDL_encoding;
	}
	public static void setPDDL_encoding(String pDDL_encoding) {
		PDDL_encoding = pDDL_encoding;
	}

	
	//
	// Getters and Setters to retrieve and manipulate: 
	// - the variables used to record the minimum length and the maximum length of a log trace.
	// - the boolean variable used to record the decision to discard or not the duplicated traces of a log during the alignment.
	//
	public static int getMinimumLengthOfATrace() {
		return minimum_length_of_a_trace;
	}
	public static int getMaximumLengthOfATrace() {
		return maximum_length_of_a_trace;
	}
	public static void setMinimumLengthOfATrace(int minimum_length_of_a_trace) {
		Constants.minimum_length_of_a_trace = minimum_length_of_a_trace;
	}
	public static void setMaximumLengthOfATrace(int maximum_length_of_a_trace) {
		Constants.maximum_length_of_a_trace = maximum_length_of_a_trace;
	}
	public static boolean isDiscard_duplicated_traces() {
		return discard_duplicated_traces;
	}
	public static void setDiscard_duplicated_traces(boolean discard_duplicated_traces) {
		Constants.discard_duplicated_traces = discard_duplicated_traces;
	}
	
	
	
	
	public static Hashtable<String, String> getContentOfAnyDifferentTrace_Hashtable() {
		return content_of_any_different_trace_Hashtable;
	}
	public static void setContentOfAnyDifferentTrace_Hashtable(Hashtable<String, String> content_of_any_trace_Hashtable) {
		Constants.content_of_any_different_trace_Hashtable = content_of_any_trace_Hashtable;
	}
	
	
	
}
