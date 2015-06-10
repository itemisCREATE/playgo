package org.yakindu.sct.generator.playgo

import org.yakindu.sct.generator.java.Statemachine
import org.yakindu.sct.model.sexec.ExecutionFlow
import org.yakindu.sct.model.sgen.GeneratorEntry
import org.yakindu.sct.model.stext.stext.EventDefinition
import org.yakindu.sct.model.stext.stext.InterfaceScope
import org.yakindu.base.types.Direction
import org.yakindu.base.types.typesystem.GenericTypeSystem

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
			
			«flow.initialize»
			
			«flow.isFinal»
			
		}
	'''

	override protected createImports(ExecutionFlow flow, GeneratorEntry entry) {
		return super.createImports(flow, entry) + '''import il.ac.wis.cs.playgo.playtoolkit.container.PlayableFramework;'''
	}
	
	def protected initialize(ExecutionFlow flow) '''
		@Override
		public void initialize() {
			// TODO Auto-generated method stub	
		}
	'''
	
	def protected isFinal(ExecutionFlow flow) '''
		@Override
		public boolean isFinal() {
			// TODO Auto-generated method stub	
			return false;
		}
	'''
	
	override generateEventDefinition(EventDefinition event, GeneratorEntry entry, InterfaceScope scope){ '''
		private boolean «event.symbol»;
		
		«IF event.type != null && !isSame(event.type, getType(GenericTypeSystem.VOID))»
			private «event.type.targetLanguageName» «event.valueIdentifier»;
		«ENDIF»
		
		«IF event.direction == Direction::IN»
			«event.generateInEventDefinition»
		«ENDIF»
		
		«IF event.direction == Direction::OUT»
			«event.generateOutEventDefinition(entry, scope)»
		«ENDIF»
		Utils.invokeMethod(new String("«scope.interfaceName»").substring(3), "«event.name»", null, null);
	'''

	}
}
