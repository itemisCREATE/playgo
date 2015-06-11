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
		
		public class «flow.statemachineClassName» extends ExecutionBridge implements «flow.statemachineInterfaceName» {
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
		return super.createImports(flow, entry) + '''import il.ac.wis.cs.playgo.playtoolkit.ebridge.ExecutionBridge;'''
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
	
	override protected def generateInEventDefinition(EventDefinition event){
		val className = event.scope.interfaceName.substring(3);
		'''
		«IF event.type != null && !isSame(event.type, getType(GenericTypeSystem.VOID))»
			public void raise«event.name.asName»(«event.type.targetLanguageName» value) {
				«event.symbol» = true;
				«event.valueIdentifier» = value;
				systemEvent("«className»", "«event.name»");
			}
			
			private «event.type.targetLanguageName» get«event.name.asName»Value() {
				«event.getIllegalAccessValidation()»
				return «event.valueIdentifier»;
			}
			
		«ELSE»
			public void raise«event.name.asName»() {
				«event.symbol» = true;
				systemEvent("«className»", "«event.name»");
			}
			
		«ENDIF»
	'''
	}
}
