package org.yakindu.sct.generator.playgo

import org.yakindu.base.types.typesystem.GenericTypeSystem
import org.yakindu.sct.generator.java.Statemachine
import org.yakindu.sct.model.sexec.ExecutionFlow
import org.yakindu.sct.model.sgen.GeneratorEntry
import org.yakindu.sct.model.stext.stext.EventDefinition

class PlaygoStatemachine extends Statemachine {

	override protected content(ExecutionFlow flow, GeneratorEntry entry) '''
		«entry.licenseText»
		package «flow.getImplementationPackageName(entry)»;
		«flow.createImports(entry)»
		
		public class «flow.statemachineClassName» implements «flow.statemachineInterfaceName», IExecutionEngineSCT {
			
			«flow.createFieldDeclarations(entry)»
		
			«flow.createPlayGoFieldDeclarations»
			
			«flow.createToString»
				
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
			
			«flow.isFinal»
			
			«flow.setExecutionBridge»
			
			«flow.initEE»
			
			«flow.activateMethod»
			
			«flow.setPropertyValue»
			
			«flow.systemEvent»
			
		}
	'''

//	override protected createConstructor(ExecutionFlow flow)'''
//		public «flow.statemachineClassName»() {
//			
//			«FOR scope : flow.interfaceScopes»
//			«scope.interfaceName.asEscapedIdentifier» = new «scope.getInterfaceImplName()»();
//			«ENDFOR»
//			register(SystemModelMain.getInstance()); // consider moving this piece of code to the init function
//		}
//	'''
	
	override protected createConstructor(ExecutionFlow flow){
		return super.createConstructor(flow) + 
	 '''
		
		public «flow.statemachineClassName»(String selfObjName, String selfClassName) {
			this();
			
			this.selfObjectName = selfObjName;
			this.selfClassName = selfClassName;
		}
		
	'''
	}
	
	def public createToString(ExecutionFlow flow) '''
		public String toString(){
			return this.getClass().getSimpleName() + "[" + this.selfObjectName+":" +this.selfClassName + "]";
		}
	'''
	
	override protected createImports(ExecutionFlow flow, GeneratorEntry entry) {
		return super.createImports(flow, entry) + 
		'''import il.ac.wis.cs.playgo.ee.sct.IExecutionEngineSCT;
		import il.ac.wis.cs.playgo.playtoolkit.ebridge.IExecutionBridge;
		import il.ac.wis.cs.playgo.ee.sct.ExecutionBridge2SCT;
		import org.yakindu.scr.TimerService;
		'''
	}
	
	def protected createPlayGoFieldDeclarations(ExecutionFlow flow)'''
		private ExecutionBridge2SCT ebridge = null;
		
		private String selfObjectName = null;
		private String selfClassName = null;
		
		public String getSelfObjectName(){
			return selfObjectName;
		}
		
		public String getSelfClassName(){
			return selfClassName;
		}
	'''
	

	def protected isFinal(ExecutionFlow flow) '''
		@Override
		public boolean isFinal() {
			return false;
		}
		
	'''
	
	def protected setExecutionBridge(ExecutionFlow flow) '''
		public void setExecutionBridge(IExecutionBridge eb) {
			this.ebridge = (ExecutionBridge2SCT) eb;
		}
	'''
	
	def protected initEE(ExecutionFlow flow) '''
		public void initEE() {
			this.setTimer(new TimerService());

			// enter the sm and active the Idle state
	
			this.init();
			this.enter();
		}
	'''
	
	def protected activateMethod(ExecutionFlow flow) '''
		public void activateMethod(String className, String objectName,
			String methodName, String... arguments) {
			ebridge.activateMethod(className, objectName, methodName, arguments);
		}
	'''
	
	def protected setPropertyValue(ExecutionFlow flow) '''
		public void setPropertyValue(String className, String objectName,
			String propertyName, String value) {
			ebridge.setPropertyValue(className, objectName, propertyName, value);
		}
	'''
	
	def protected systemEvent(ExecutionFlow flow) '''
		public void systemEvent(String targetClassName, String targetObjectName, String eventName) {
			if(ebridge.isOriginatedFromExecutionEngine()) {
					if(targetClassName.equalsIgnoreCase("self")){
						ebridge.systemEvent(selfClassName, selfObjectName, eventName);
					} else {
						ebridge.systemEventSelfExcluded(selfClassName, selfObjectName, targetClassName, eventName);
					}
				}
				
		}
	'''
	
	override protected def generateInEventDefinition(EventDefinition event){
		val className = event.scope.interfaceName.substring(3);
		'''
		«IF event.type != null && !isSame(event.type, getType(GenericTypeSystem.VOID))»
			public void raise«event.name.asName»(«event.type.targetLanguageName» value) {
				«event.symbol» = true;
				«event.valueIdentifier» = value;
				systemEvent("«className»", selfObjectName, "«event.name»");
				}
			}
			
			private «event.type.targetLanguageName» get«event.name.asName»Value() {
				«event.getIllegalAccessValidation()»
				return «event.valueIdentifier»;
			}
			
		«ELSE»
			public void raise«event.name.asName»() {
				«event.symbol» = true;
				systemEvent("«className»", selfObjectName, "«event.name»");
			}
			
		«ENDIF»
	'''
	}
	
	
}
