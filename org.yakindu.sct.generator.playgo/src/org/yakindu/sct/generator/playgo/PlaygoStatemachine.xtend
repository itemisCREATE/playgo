package org.yakindu.sct.generator.playgo

import org.yakindu.sct.generator.java.Statemachine
import org.yakindu.sct.model.sexec.ExecutionFlow
import org.yakindu.sct.model.sgen.GeneratorEntry

class PlaygoStatemachine extends Statemachine {

	override protected content(ExecutionFlow flow, GeneratorEntry entry) '''
		«entry.licenseText»
		package «flow.getImplementationPackageName(entry)»;
		«flow.createImports(entry)»
		
		public class «flow.statemachineClassName» extends PlayableFramework implements «flow.statemachineInterfaceName» {
			«flow.createFieldDeclarations(entry)»
		
				
			«flow.createConstructor»
			
			«flow.initFunction»
			
			«flow.enterFunction»
			
			«flow.exitFunction»
			
			«flow.clearInEventsFunction»
			
			«flow.clearOutEventsFunction»
			
			«flow.activeFunction»
			
			«flow.timingFunctions»
			
			«flow.interfaceAccessors»
			
			«flow.internalScopeFunctions»
			
			«flow.defaultInterfaceFunctions(entry)»
			
			«flow.functionImplementations»
			
			«flow.runCycleFunction»
		}
	'''

	override protected createImports(ExecutionFlow flow, GeneratorEntry entry) {
		return super.createImports(flow, entry) + '''import org.playgo.framework.*;'''
	}

}
