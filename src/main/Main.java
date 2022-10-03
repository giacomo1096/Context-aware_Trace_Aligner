package main; 

import java.io.File;
import view.Desktop;

public class Main {
	
	public static void main(String[] args) {
		
		//Force the executable files of Fast-downward and LPG to be executable
		new File("translate_script").setExecutable(true);
		new File("preprocess_script").setExecutable(true);		
		new File("planner_subopt_script").setExecutable(true);			
		new File("planner_opt_script").setExecutable(true);			
		new File("lpg_script").setExecutable(true);		
		new File("fast-downward/src/translate/translate.py").setExecutable(true);			
		new File("fast-downward/src/preprocess/preprocess").setExecutable(true);	
		new File("fast-downward/src/search/downward").setExecutable(true);
		new File("fast-downward/src/search/unitcost").setExecutable(true);
		new File("fast-downward/src/search/downward-release").setExecutable(true);
		new File("LPG/lpg").setExecutable(true);

		new File("run_FD").setExecutable(true);
		new File("run_FD_all").setExecutable(true);
		new File("run_SYMBA").setExecutable(true);
		new File("run_SYMBA_all").setExecutable(true);
		new File("checkNumberOfTraces").setExecutable(true);
				
	    new Desktop();
	}

}

