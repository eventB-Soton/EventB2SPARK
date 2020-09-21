package ac.soton.eventb.internal.generator.spark.handler;

import static org.eclipse.ui.handlers.HandlerUtil.getCurrentSelection;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eventb.core.IMachineRoot;
import org.eventb.core.ISCInvariant;
import org.eventb.core.ISCMachineRoot;


public class SparkTranslate extends AbstractHandler {
	
	

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		final ISelection selection = getCurrentSelection(event);
		if (selection instanceof IStructuredSelection) {
			final IStructuredSelection ssel = (IStructuredSelection) selection;
			if (ssel.size() != 1) {
				return null;
			}
			final IMachineRoot obj = (IMachineRoot) ssel.getFirstElement();
			
			ISCMachineRoot smch = obj.getSCMachineRoot();
						
			try {
				ISCInvariant[] contexts = smch.getSCInvariants();
				
				for (ISCInvariant context : contexts) {
					
					System.out.println(context.getPredicate(smch.getTypeEnvironment()).getTag());
					System.out.println(context.getPredicate(smch.getTypeEnvironment()).getChild(1).getTag());
					System.out.println(context.getPredicate(smch.getTypeEnvironment()).getChild(1).getChildCount());
					
					if (context.getPredicate(smch.getTypeEnvironment()).getChild(1).getChildCount() > 0) {
					
						System.out.println(context.getPredicate(smch.getTypeEnvironment()).getChild(1).getChild(0).toString());
					
					}
					System.out.println(context.getPredicate(smch.getTypeEnvironment()).toString());
					
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		
		}
	
		return null;
	}
	
//	private void printContext(ISCContext con,ISCMachineRoot smch) throws CoreException {
//		ISCCarrierSet[] constants = con.getSCCarrierSets();
//		
//		for (ISCCarrierSet constant : constants) {
//			System.out.println(smch.getTypeEnvironment().getType(constant.getIdentifierString()).toString());
//		}
//	}
//	
//	private void printEvent(ISCEvent event, ISCMachineRoot smch) throws CoreException {
//		
//		ISCParameter[] params = event.getSCParameters();
//		
//		for (ISCParameter p : params) {
//			System.out.println(event.getTypeEnvironment(smch.getTypeEnvironment()
//					));
//			System.out.println(p.getIdentifierString());
//			System.out.println();
//		}
//	}
	

//	private String translate(Formula f, ISCMachineRoot smch, ITypeEnvironmentBuilder builder) throws CoreException {
//		
//		if (f.getChildCount() == 0) {
//			
//			String types[] = EventBSCUtilsExtra.typeOfIdentifier(f.toString(), smch, builder);
//			
//			return (f.toString() + types[0]);
//			
//		} else {
//			
//			if (f.getTag() == 107) {
//				
//				Formula<?> c1 = f.getChild(0);
//				Formula<?> c2 = f.getChild(1);
//				
//				if (c1.getChildCount() == 0) {
//					String[] types = EventBSCUtilsExtra.typeOfIdentifier(c1.toString(), smch, builder);
//					
//					if (types[1].equals("element")) {
//						
//						if (c2.getChildCount() == 0) {
//							
//							return (translate(c2,smch,builder) + " (" + c1.toString() + ")");
//							
//						} else if (c2.getTag() == 5) { // RHS of form {e1}
//							
//							return (c1.toString() + " = " + c2.getChild(0).toString());
//							
//						} else if (c2.getTag() == 301) { // union on RHS
//							
//							return ("isMemberUnion (" + c1.toString() + "," + translate(c2.getChild(0), smch, builder) + "," + translate(c2.getChild(1),smch,builder) + ")");
//							
//						} else if (c2.getTag() == 302) { // intersection on RHS
//							
//							return ("isMemberIntersection (" + c1.toString() + "," + translate(c2.getChild(0),smch,builder) + "," + translate(c2.getChild(1),smch,builder) + ")");
//							
//						} else if (c2.getTag() == 213) { // set difference on RHS
//							
//							return ("isMemberDifference (" + c1.toString() + "," + translate(c2.getChild(0),smch,builder) + "," + translate(c2.getChild(1),smch,builder) + ")");
//							
//						} else if (c2.getTag() == 756) { // domain operator on RHS
//							
//							return ("inDomain (" + c1.toString() + "," + c2.getChild(0).toString() + ")");
//							
//						} else if (c2.getTag() == 757) { // range op on RHS
//							
//							return ("inRange (" + c1.toString() + "," + c2.getChild(0).toString() + ")");
//							
//						} else if (c2.getTag() == 227) { // rel image op on RHS
//							
//							return ("inRelationalImage (" + c2.getChild(0).toString() + "," + translate(c2.getChild(1),smch,builder) + ")");
//							
//						}
//						
//					} else if (types[1].equals("set")) { // set on LHS, so powerset op on RHS
//						
//						return ("inPowerSet (" + translate(c1,smch,builder) + "," + translate(c2.getChild(0),smch,builder) + ")");
//						
//					} else if (types[1].equals("relation")) { // relation on LHS
//						
//						if (c2.getTag() == 752) { // powerset op on RHS
//							
//							return ("inPowerSet (" + c1.toString() + "," + c2.getChild(0).toString() + ")");
//							
//						} else if (c2.getTag() == 202) { // relation op on RHS
//							
//							return ("relationOfSets (" + c1.toString() + "," + translate(c2.getChild(0),smch,builder) + "," + translate(c2.getChild(1),smch,builder) + ")");
//							
//						} else if (c2.getTag() == 204) { // partial surjective rel op on RHS
//							
//							return ("isPartialSurjectiveRelation (" + c1.toString() + "," + translate(c2.getChild(0),smch,builder) + "," + translate(c2.getChild(1),smch,builder) + ")");
//							
//						} else if (c2.getTag() == 203) { // total relation op on RHS
//							
//							return ("isTotalRelation (" + c1.toString() + "," + translate(c2.getChild(0),smch,builder) + "," + translate(c2.getChild(1),smch,builder) + ")");
//							
//						} else if (c2.getTag() == 205) { // total surjective rel op on RHS
//							
//							return ("isTotalSurjectiveRelation (" + c1.toString() + "," + translate(c2.getChild(0),smch,builder) + "," + translate(c2.getChild(1),smch,builder) + ")");
//							
//						} else if (c2.getTag() == 206) { // partial func. op on RHS
//							
//							return ("isPartialFunction (" + c1.toString() + "," + translate(c2.getChild(0),smch,builder) + "," + translate(c2.getChild(1),smch,builder) + ")");
//							
//						} else if (c2.getTag() == 207) { // total func. op on RHS
//							
//							return ("isTotalFunction (" + c1.toString() + "," + translate(c2.getChild(0),smch,builder) + "," + translate(c2.getChild(1),smch,builder) + ")");
//							
//						} else if (c2.getTag() == 208) { // partial injection op on RHS
//							
//							return ("isPartialInjection (" + c1.toString() + "," + translate(c2.getChild(0),smch,builder) + "," + translate(c2.getChild(1),smch,builder) + ")");
//							
//						} else if (c2.getTag() == 209) { // total injection op on RHS
//							
//							return ("isTotalInjection (" + c1.toString() + "," + translate(c2.getChild(0),smch,builder) + "," + translate(c2.getChild(1),smch,builder) + ")");
//							
//						} else if (c2.getTag() == 210) { // partial surjection op on RHS
//							
//							return ("isPartialSurjection (" + c1.toString() + "," + translate(c2.getChild(0),smch,builder) + "," + translate(c2.getChild(1),smch,builder) + ")");
//							
//						} else if (c2.getTag() == 211) { // total surjection op on RHS
//							
//							return ("isTotalSurjection (" + c1.toString() + "," + translate(c2.getChild(0),smch,builder) + "," + translate(c2.getChild(1),smch,builder) + ")");
//							
//						} else if (c2.getTag() == 212) { // bijection op on RHS
//							
//							return ("isBijection (" + c1.toString() + "," + translate(c2.getChild(0),smch,builder) + "," + translate(c2.getChild(1),smch,builder) + ")");
//							
//						}
//						
//					}
//				} else if (c1.getChildCount() == 2) { // c1 is a 2-tuple
//					
//					if (c2.getChildCount() == 0) { // c2 is a relation
//						
//						return (c2.toString() + " (" + c1.getChild(0).toString() + "," + c2.getChild(1).toString() + ")");
//						
//					} else if (c2.getTag() == 5) { // RHS is a tuple in {}
//						
//						return (c1.getChild(0).toString() + " = " + c2.getChild(0).getChild(0).toString() + " and then " + c1.getChild(1).toString() + " = " + c2.getChild(0).getChild(1).toString());
//						
//					} else if (c2.getTag() == 214) { // RHS has cartesian product op
//						
//						return ("inCartesianProduct (" + c1.getChild(0).toString() + "," + c1.getChild(1).toString() + "," + translate(c2.getChild(0),smch,builder) + "," + translate(c2.getChild(1),smch,builder) + ")");
//						
//					} else if (c2.getTag() == 301) { // union
//						
//						if (c2.getChild(1).getChildCount() == 0) {
//							return ("isMemberUnion (" + c1.getChild(0).toString() + "," + c1.getChild(1).toString() + "," + c2.getChild(0).toString() + "," + c2.getChild(1).toString() + ")");
//						} else {
//							return ("isMemberUnion (" + c1.getChild(0).toString() + "," + c1.getChild(1).toString() + "," + c2.getChild(0).toString() + "," + c2.getChild(1).getChild(0).getChild(0).toString() + "," + c2.getChild(1).getChild(0).getChild(1).toString() + ")");
//						}
//						
//					} else if (c2.getTag() == 302) { //intersection
//						
//						if (c2.getChild(1).getChildCount() == 0) {
//							return ("isMemberIntersection (" + c1.getChild(0).toString() + "," + c1.getChild(1).toString() + "," + c2.getChild(0).toString() + "," + c2.getChild(1).toString() + ")");
//						} else {
//							return ("isMemberIntersection (" + c1.getChild(0).toString() + "," + c1.getChild(1).toString() + "," + c2.getChild(0).toString() + "," + c2.getChild(1).getChild(0).getChild(0).toString() + "," + c2.getChild(1).getChild(0).getChild(1).toString() + ")");
//						}
//						
//					} else if (c2.getTag() == 213) { // set difference
//						
//						if (c2.getChild(1).getChildCount() == 0) {
//							return ("isMemberDifference (" + c1.getChild(0).toString() + "," + c1.getChild(1).toString() + "," + c2.getChild(0).toString() + "," + c2.getChild(1).toString() + ")");
//						} else {
//							return ("isMemberDifference (" + c1.getChild(0).toString() + "," + c1.getChild(1).toString() + "," + c2.getChild(0).toString() + "," + c2.getChild(1).getChild(0).getChild(0).toString() + "," + c2.getChild(1).getChild(0).getChild(1).toString() + ")");
//						}
//						
//					} else if (c2.getTag() == 763) { // inverse
//						
//						return ("inConverse (" + c1.getChild(0).toString() + "," + c1.getChild(1).toString() + "," + c2.getChild(0).toString() + ")");
//						
//					} else if (c2.getTag() == 217) { // domain restriction
//						
//						if (c2.getChild(0).getChildCount() == 0) {
//							
//							return ("inDomainRestriction (" + c1.getChild(0).toString() + "," + c1.getChild(1).toString() + "," + c2.getChild(1).toString() + "," + c2.getChild(0).toString() + ")");
//							
//						} else {
//							
//							return ("inDomainRestriction (" + c1.getChild(0).toString() + "," + c1.getChild(1).toString() + "," + c2.getChild(1).toString() + "," + c2.getChild(0).getChild(0).toString() + ")");
//							
//						}
//						
//					} else if (c2.getTag() == 218) { // domain subtraction
//						
//						if (c2.getChild(0).getChildCount() == 0) {
//							
//							return ("inDomainSubtraction (" + c1.getChild(0).toString() + "," + c1.getChild(1).toString() + "," + c2.getChild(1).toString() + "," + c2.getChild(0).toString() + ")");
//							
//						} else {
//							
//							return ("inDomainSubtraction (" + c1.getChild(0).toString() + "," + c1.getChild(1).toString() + "," + c2.getChild(1).toString() + "," + c2.getChild(0).getChild(0).toString() + ")");
//							
//						}
//						
//					} else if (c2.getTag() == 219) { // range restriction
//						
//						if (c2.getChild(1).getChildCount() == 0) {
//							
//							return ("inRangeRestriction (" + c1.getChild(0).toString() + "," + c1.getChild(1).toString() + "," + c2.getChild(0).toString() + "," + c2.getChild(1).toString() + ")");
//							
//						} else {
//							
//							return ("inRangeRestriction (" + c1.getChild(0).toString() + "," + c1.getChild(1).toString() + "," + c2.getChild(0).toString() + "," + c2.getChild(1).getChild(0).toString() + ")");
//							
//						}
//						
//					} else if (c2.getTag() == 220) {
//						
//						if (c2.getChild(1).getChildCount() == 0) {
//							
//							return ("inRangeSubtraction (" + c1.getChild(0).toString() + "," + c1.getChild(1).toString() + "," + c2.getChild(0).toString() + "," + c2.getChild(1).toString() + ")");
//							
//						} else {
//							
//							return ("inRangeSubtraction (" + c1.getChild(0).toString() + "," + c1.getChild(1).toString() + "," + c2.getChild(0).toString() + "," + c2.getChild(1).getChild(0).toString() + ")");
//							
//						}
//						
//					} else if (c2.getTag() == 304) {
//						
//						return ("inComposition (" + c1.getChild(0).toString() + "," + c1.getChild(1).toString() + "," + c2.getChild(0).toString() + "," + c2.getChild(1).toString() + ")");
//						
//					} else if (c2.getTag() == 305) {
//						
//						return ("inOverride (" + c1.getChild(0).toString() + "," + c1.getChild(1).toString() + "," + c2.getChild(0).toString() + "," + c2.getChild(1).toString() + ")");
//						
//					}
//				}
//				
//			} else if (f.getTag() == 108) {
//				
//				Formula<?> c1 = f.getChild(0);
//				Formula<?> c2 = f.getChild(1);
//				
//				
//				
//				if (c1.getChildCount() == 0) {
//					
//					String[] types = EventBSCUtilsExtra.typeOfIdentifier(c1.toString(), smch, builder);
//					
//					if (types[1].equals("element")) {
//						
//						if (c2.getChildCount() == 0) {
//							
//							
//							
//						}
//						
//					}
//					
//				}
//				
//			} else if (f.getTag() == 101) { // equals
//				
//				Formula<?> c1 = f.getChild(0);
//				Formula<?> c2 = f.getChild(1);
//				
//				if (c1.getChildCount() == 0) {
//					
//					String[] types = EventBSCUtilsExtra.typeOfIdentifier(c1.toString(), smch, builder);
//					
//					if (types[1].equals("element")) {
//						
//						if (c2.getChildCount() == 0) {
//							
//							return (c1.toString() + " = " + c2.toString());
//							
//						} else if (c2.getTag() == 226) {
//							
//							return (c2.getChild(0).toString() + " (" + c2.getChild(1).toString() + "," + c1.toString() + ")");
//							
//						}
//						
//					} else if (types[1].equals("set")) {
//						
//						if (c2.getChildCount() == 0) {
//							
//							if (c2.getTag() == 407) { // empty set on RHS
//								 
//								return ("isEmpty (" + translate(c1,smch,builder) + ")");
//								
//							} else if (c2.getTag() == 1) { // normal set on RHS
//								
//								return (translate(c1,smch,builder) + " = " + translate(c2,smch,builder));
//								
//							}
//							
//						} else if (c2.getChildCount() == 1) {
//							
//							if (c2.getTag() == 5) { // singleton on RHS
//								
//								return ("equalsSingleton (" + translate(c1,smch,builder) + "," + c2.getChild(0).toString() + ")");
//								
//							} else if (c2.getTag() == 756) { // domain operator on RHS
//								
//								return ("setEqualsDomain (" + translate(c1,smch,builder) + "," + c2.getChild(0).toString() + ")");
//								
//							} else if (c2.getTag() == 757) {
//								
//								return ("setEqualsRange (" + translate(c1,smch,builder) + "," + c2.getChild(0).toString() + ")");
//								
//							}
//							
//						} else if (c2.getChildCount() == 2) {
//							
//							if (c2.getTag() == 213) { // difference on RHS
//								
//								if (c2.getChild(1).getChildCount() == 0) {
//									
//									return ("equalsDifference (" + translate(c1,smch,builder) + "," + translate(c2.getChild(0),smch,builder) + "," + translate(c2.getChild(1),smch,builder) + ")");
//									
//								} else {
//									
//									return ("equalsDifference (" + translate(c1,smch,builder) + "," + translate(c2.getChild(0),smch,builder) + "," + c2.getChild(1).getChild(0).toString() + ")");
//									
//								}
//								
//							} else if (c2.getTag() == 301) { // union
//								
//								if (c2.getChild(1).getChildCount() == 0) {
//									
//									return ("equalsUnion (" + translate(c1,smch,builder) + "," + translate(c2.getChild(0),smch,builder) + "," + translate(c2.getChild(1),smch,builder) + ")");
//									
//								} else {
//									
//									return ("equalsUnion (" + translate(c1,smch,builder) + "," + translate(c2.getChild(0),smch,builder) + "," + c2.getChild(1).getChild(0).toString() + ")");
//									
//								}
//								
//							} else if (c2.getTag() == 302) { // intersection
//								
//								if (c2.getChild(1).getChildCount() == 0) {
//									
//									return ("equalsIntersection (" + translate(c1,smch,builder) + "," + translate(c2.getChild(0),smch,builder) + "," + translate(c2.getChild(1),smch,builder) + ")");
//									
//								} else {
//									
//									return ("equalsIntersection (" + translate(c1,smch,builder) + "," + translate(c2.getChild(0),smch,builder) + "," + c2.getChild(1).getChild(0).toString() + ")");
//									
//								}
//								
//							} else if (c2.getTag() == 227) { // relational image
//								
//								if (c2.getChild(1).getChildCount() == 0) {
//									
//									return ("equalsRelationalImage (" + translate(c1,smch,builder) + "," + c2.getChild(0).toString() + "," + translate(c2.getChild(1),smch,builder));
//									
//								} else {
//									
//									return ("equalsRelationalImage (" + translate(c1,smch,builder) + "," + c2.getChild(0).toString() + "," + c2.getChild(1).getChild(0).toString());
//									
//								}
//								
//							}
//							
//						}
//						
//					} else if (types[1].equals("relation")) {
//						
//						if (c2.getChildCount() == 0) {
//							
//							if (c2.getTag() == 407) { // empty set on RHS
//								 
//								return ("isEmpty (" + c1.toString() + ")");
//								
//							} else if (c2.getTag() == 1) { // normal relation on RHS
//								
//								return (c1.toString() + " = " + c2.toString());
//								
//							}
//							
//						} else if (c2.getChildCount() == 1) {
//							
//							if (c2.getTag() == 5) {
//								
//								return ("equalsSingleton (" + c1.toString() + "," + translate(c2,smch,builder) + ")");
//								
//							} else if (c2.getTag() == 763) { // inverse
//								
//								return ("equalsConverse (" + c1.toString() + "," + c2.getChild(0).toString() + ")");
//								
//							}
//							
//						} else if (c2.getChildCount() == 2) {
//							
//							if (c2.getTag() == 213) { // difference
//								
//								if (c2.getChild(1).getChildCount() == 0) { // normal relations on RHS
//									
//									return ("equalsDifference (" + c1.toString() + "," + c2.getChild(0).toString() + "," + c2.getChild(1).toString() + ")");
//									
//								} else if (c2.getChild(1).getTag() == 5) { // singleton relation on RHS
//									
//									return ("equalsDifference (" + c1.toString() + "," + c2.getChild(0).toString() + "," + translate(c2.getChild(1),smch,builder) + ")");
//									
//								}
//								
//							} else if (c2.getTag() == 301) { //union
//								
//								if (c2.getChild(1).getChildCount() == 0) { // normal relations on RHS
//									
//									return ("equalsUnion (" + c1.toString() + "," + c2.getChild(0).toString() + "," + c2.getChild(1).toString() + ")");
//									
//								} else if (c2.getChild(1).getTag() == 5) { // singleton relation on RHS
//									
//									return ("equalsUnion (" + c1.toString() + "," + c2.getChild(0).toString() + "," + translate(c2.getChild(1),smch,builder) + ")");
//									
//								}
//								
//							} else if (c2.getTag() == 302) { //intersection
//								
//								if (c2.getChild(1).getChildCount() == 0) { // normal relations on RHS
//									
//									return ("equalsIntersection (" + c1.toString() + "," + c2.getChild(0).toString() + "," + c2.getChild(1).toString() + ")");
//									
//								}
//								
//							} else if (c2.getTag() == 217) { // domain restriction
//								
//								return ("equalsDomainRestriction (" + c1.toString() + "," + c2.getChild(1).toString() + "," + translate(c2.getChild(0),smch,builder) + ")");
//								
//							} else if (c2.getTag() == 218) { // domain subtraction
//								
//								return ("equalsDomainSubtraction (" + c1.toString() + "," + c2.getChild(1).toString() + "," + translate(c2.getChild(0),smch,builder) + ")");
//								
//							} else if (c2.getTag() == 219) { // range restriction
//								
//								return ("equalsRangeRestriction (" + c1.toString() + "," + c2.getChild(0).toString() + "," + translate(c2.getChild(1),smch,builder) + ")");
//								
//							} else if (c2.getTag() == 220) { // range subtraction
//								
//								return ("equalsRangeSubtraction (" + c1.toString() + "," + c2.getChild(0).toString() + "," + translate(c2.getChild(1),smch,builder) + ")");
//								
//							}
//							
//						}
//						
//					}
//					
//				} else if (c1.getChildCount() == 2) {
//					
//					String[] types = EventBSCUtilsExtra.typeOfIdentifier(c1.getChild(1).toString(), smch, builder);
//					
//					if (types[1].equals("element")) {
//						
//						if (c2.getChildCount() == 0) {
//							
//							return (c1.getChild(0).toString() + " (" + c1.getChild(1).toString() + "," + c2.toString() + ")");
//							
//						} else if (c2.getChildCount() == 2) {
//							
//							return ("functionApplicationEquality (" + c1.getChild(0).toString() + "," + c1.getChild(1).toString() + "," + c2.getChild(0).toString() + "," + c2.getChild(1).toString() + ")");
//							
//						}
//						
//					} else if (types[1].equals("set")) {
//						
//						if (c1.getTag() == 301) {
//							
//							return ("unionEmpty (" + translate(c1.getChild(0),smch,builder) + "," + translate(c1.getChild(1),smch,builder) + ")");
//							
//						} else if (c1.getTag() == 302) {
//							
//							return ("intersectionEmpty (" + translate(c1.getChild(0),smch,builder) + "," + translate(c1.getChild(1),smch,builder) + ")");
//							
//						}
//						
//					} else if (types[1].equals("relation")) {
//						
//						if (c1.getTag() == 301) {
//							
//							return ("unionEmpty (" + c1.getChild(0).toString() + "," + c1.getChild(1).toString() + ")");
//							
//						} else if (c1.getTag() == 302) {
//							
//							return ("intersectionEmpty (" + c1.getChild(0).toString() + "," + c1.getChild(1).toString() + ")");
//							
//						}
//						
//					}
//					
//				}
//				
//			} else if (f.getTag() == 5) { // singleton in bracket
//				
//				if (f.getChild(0).getChildCount() == 0) { // value in bracket is single
//					
//					return (f.getChild(0).toString());
//					
//				} else {  // value in bracket is a tuple
//					
//					return (f.getChild(0).getChild(0).toString() + "," + f.getChild(0).getChild(1).toString());
//					
//				}
//				
//			}
//			
//		}
//		return "";
//	}
	

}
