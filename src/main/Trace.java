package main;

import java.util.Hashtable;
import java.util.Vector;

public class Trace {
	
	private String id; // Unique ID of the trace for internal use (e.g., Trace#1, ..., Trace#N).
					   // --- This value is STABLE after the transition between the TracesPerspective panel and the ConstraintsPerspective panel.
	
	private String name; // Unique name of the trace taken from the XES file (i.e., the value of the "concept:name" attribute of the tag <trace>).
						 // If the trace is created by a user (NOT imported from a XES file), the name will be equal to the ID of the trace.
	 					 // --- This value is STABLE after the transition between the TracesPerspective panel and the ConstraintsPerspective panel.
	
	private Vector<String> trace_alphabet_vector; // Alphabet of the activities of the trace (e.g., a,b,c,d, ... ecc.).
	 											  // --- This value is STABLE after the transition between the TracesPerspective panel and the ConstraintsPerspective panel.
	
	private Vector<String> original_trace_content_vector; // Original and ordered content of the trace (e.g., a,b,a,c,d,a, ... ecc.).
	 													  // --- This value is STABLE after the transition between the TracesPerspective panel and the ConstraintsPerspective panel.
	
	private String original_trace_content_string; // Original and ordered content of the trace (e.g., a,b,a,c,d,a, ... ecc.) recorded in a STRING.
	  											  // --- This value is STABLE after the transition between the TracesPerspective panel and the ConstraintsPerspective panel.
	
	private Vector<String> trace_content_with_activities_instances_vector; // Ordered content of the trace with the explicit representation of activity instances 
																		   // (e.g., if the original trace content is <a,b,a,c,d,a>, this vector will contain a0,b0,a1,c0,d0,a2, ... ecc.).
																		   // --- This value is STABLE after the transition between the TracesPerspective panel and the ConstraintsPerspective panel.
	
	private Hashtable<String, Integer> number_of_activity_instances_hashtable = new Hashtable<String, Integer>(); // Hashtable used to record the number of activity instances of a trace.
																											      // For example, if the content of the trace is <a,b,a>, this Hashtable 
																												  // will contain two keys (a,b) with the following values: {a=2,b=1}.
	 																											  // --- This value is STABLE after the transition between the TracesPerspective panel and the ConstraintsPerspective panel.
	
	private Hashtable<String, String> associations_to_activity_instances_hashtable; // Hashtable used to record the association between activity instances and their "activity model".
    																					      // For example, if the content of the trace is <a,b,a>, this Hashtable 
	  																					      // will contain three keys (a0,a1,b0) with the following values: {a0=a,a1=a,b0=b}.
	 																						  // --- This value is STABLE after the transition between the TracesPerspective panel and the ConstraintsPerspective panel.
	
	private Vector<String> trace_missing_activities_vector; // Vector containing the activities that appear in some Declare/LTL constraint 
												 			// but NOT in the trace (e.g., if the content of the trace is <a,c,e> and there is a constraint 
															// choice(c,d) --> d is a "missing" activity).
	 														// --- This value is STABLE after the transition between the ConstraintsPerspective panel and the PlannerPerspective panel.	
	
	private Vector<String> trace_alphabet_with_missing_activities_of_the_constraints_vector; // Alphabet of the activities of the trace and of the constraints.
																							 // For example, if the content of the trace is <a,c,e> and there is a constraint 
																							 // choice(c,d) (therefore, d is a "missing" activity), then this vector will contain a,c,d,e.
																							 // --- This value is STABLE after the transition between the ConstraintsPerspective panel and the PlannerPerspective panel.
	
	public Trace(String trace_id, String trace_name) {		
		id = trace_id;
		name = trace_name;
		trace_alphabet_vector = new Vector<String>();
		original_trace_content_vector = new Vector<String>();
		original_trace_content_string = new String();
		trace_content_with_activities_instances_vector = new Vector<String>();
		number_of_activity_instances_hashtable = new Hashtable<String, Integer>();
		associations_to_activity_instances_hashtable = new Hashtable<String,String>();
		trace_missing_activities_vector = new Vector<String>();
		trace_alphabet_with_missing_activities_of_the_constraints_vector = new Vector<String>();
	}
	
	public String getTraceID() {
		return id;
	}
	
	public void setTraceID(String trace_ID) {
		id = trace_ID;
	}
	
	public String getTraceName() {
		return name;
	}
	
	public void setTraceName(String name) {
		this.name = name;
	}
	
	public Vector<String> getTraceAlphabet_vector() {
		return trace_alphabet_vector;
	}

	public void setTraceAlphabet_vector(Vector<String> tr_alphabet_vector) {
		this.trace_alphabet_vector = tr_alphabet_vector;
	}	
	
	public Vector<String> getOriginalTraceContent_vector() {
		return original_trace_content_vector;
	}

	public String getOriginalTraceContent_string() {
		return original_trace_content_string;
	}

	public void setOriginalTraceContentString(String original_trace_content_string) {
		this.original_trace_content_string = original_trace_content_string;
	}

	public void setOriginalTraceContent_vector(Vector<String> trace_content) {
		original_trace_content_vector = trace_content;
	}
	
	public Vector<String> getTraceContentWithActivitiesInstances_vector() {
		return trace_content_with_activities_instances_vector;
	}

	public void setTraceContentWithActivitiesInstances_vector(Vector<String> pddl_trace_content) {
		trace_content_with_activities_instances_vector = pddl_trace_content;
	}
	
	public Vector<String> getTraceMissingActivities_vector() {
		return trace_missing_activities_vector;
	}

	public void setTraceMissingActivities_vector(Vector<String> missing_activities_vector) {
		this.trace_missing_activities_vector = missing_activities_vector;
	}

	public Hashtable<String, String> getAssociationsToActivityInstances_Hashtable() {
		return associations_to_activity_instances_hashtable;
	}

	public void setAssociationsToActivityInstances_Hashtable(Hashtable<String, String> trace_hashtable) {
		this.associations_to_activity_instances_hashtable = trace_hashtable;
	}
	
	public Hashtable<String, Integer> getNumberOfActivityInstances_Hashtable() {
		return number_of_activity_instances_hashtable;
	}

	public void setNumberOfActivityInstances_Hashtable(Hashtable<String, Integer> trace_hashtable) {
		this.number_of_activity_instances_hashtable = trace_hashtable;
	}
	
	public Vector<String> getTraceAlphabetWithMissingActivitiesOfTheConstraints_vector() {
		return trace_alphabet_with_missing_activities_of_the_constraints_vector;
	}

	public void setTraceAlphabetWithMissingActivitiesOfTheConstraints_vector(Vector<String> trace_alphabet_with_missing_activities_of_the_constraints_vector) {
		this.trace_alphabet_with_missing_activities_of_the_constraints_vector = trace_alphabet_with_missing_activities_of_the_constraints_vector;
	}

	public String getTraceNumber() {
		
		String[] split = this.getTraceID().split("#");
		return split[1];
		
	}

}
