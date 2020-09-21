package ac.soton.eventb.internal.generator.spark.handler;

import java.util.Collection;

import org.eclipse.core.runtime.CoreException;
import org.eventb.core.ISCMachineRoot;
import org.eventb.core.ast.ITypeEnvironmentBuilder;

import ch.ethz.eventb.utils.EventBSCUtils;


public class EventBSCUtilsExtra extends EventBSCUtils {

	public static String[] typeOfIdentifier(String id, ISCMachineRoot smch, ITypeEnvironmentBuilder builder) throws CoreException {
		
		String type1;
		String type2;
		
		String type = builder.getType(id).toString();
		
		if (type.contains("×")) {
			type1 = "";
			type2 = "relation";
		} else if (type.contains("ℙ")) {
			type1 = "";
			type2 = "set";
		} else {
			type1 = "";
			type2 = "element";
		}
		
		Collection<String> cSets = EventBSCUtils.getSCSeenCarrierSetIdentifierStrings(smch);
		
		for (String set : cSets) {
			if (set.equals(id)) {
				type1 = "cs";
				break;
			}
		}
		
		String[] arr = {type1,type2};
		return arr;
	}

	
	
}
