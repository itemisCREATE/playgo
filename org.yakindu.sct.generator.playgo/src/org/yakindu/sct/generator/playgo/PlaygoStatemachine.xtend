package org.yakindu.sct.generator.playgo

import org.yakindu.base.types.typesystem.GenericTypeSystem
import org.yakindu.sct.generator.java.Statemachine
import org.yakindu.sct.model.sexec.ExecutionFlow
import org.yakindu.sct.model.sgen.GeneratorEntry
import org.yakindu.sct.model.stext.stext.EventDefinition
import org.yakindu.sct.model.stext.stext.VariableDefinition
import org.yakindu.sct.model.stext.stext.InterfaceScope

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
			
			«flow.trace»
			
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
			// ToDo: add implementation to call setter
		}
	'''
	
	def protected systemEvent(ExecutionFlow flow) '''
		public void systemEvent(String targetClassName, String targetObjectName, String eventName) {
			if(targetClassName.equalsIgnoreCase("self")){
				ebridge.systemEvent(this.getClass().getSimpleName(), selfClassName, selfObjectName, eventName);
			} else {
				ebridge.systemEventSelfExcluded(this.getClass().getSimpleName(), selfClassName, selfObjectName, targetClassName, eventName);
			}
		}
	'''
	def protected trace(ExecutionFlow flow) '''
		public void trace(String eventName) {
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
			
			public void «event.name.asIdentifier»(«event.type.targetLanguageName» value) {
				«event.symbol» = true;
				«event.valueIdentifier» = value;
				trace("«event.name»");
				// System.out.println("in " + "«className»." + «event.name.asIdentifier»" + "[" + selfObjectName+ ":" + selfClassName + "]");
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
			
			public void «event.name.asIdentifier»() {
				«event.symbol» = true;
				trace("«event.name»");
				//System.out.println("in " + "«className»." +"«event.name.asIdentifier»"+ "[" + selfObjectName+ ":" + selfClassName + "]");
			}
			
		«ENDIF»
	'''
	}
		
		
	override protected def generateOutEventDefinition(EventDefinition event, GeneratorEntry entry, InterfaceScope scope) {
		val className = event.scope.interfaceName.substring(3);
		'''
		public boolean isRaised«event.name.asName»() {
			return «event.symbol»;
		}
		
		«IF event.type != null && !isSame(event.type, getType(GenericTypeSystem.VOID))»
			private void raise«event.name.asName»(«event.type.targetLanguageName» value) {
				«event.symbol» = true;
				«event.valueIdentifier» = value;
				«IF entry.createInterfaceObserver»
				for («scope.interfaceListenerName» listener : listeners) {
					listener.on«event.name.asEscapedName»Raised(value);
				}
				systemEvent("«className»", selfObjectName, "«event.name»");
				«ENDIF»
			}
			
			private void «event.name.asIdentifier»(«event.type.targetLanguageName» value) {
				«event.symbol» = true;
				«event.valueIdentifier» = value;
				«IF entry.createInterfaceObserver»
				for («scope.interfaceListenerName» listener : listeners) {
					listener.on«event.name.asEscapedName»Raised(value);
				}
				«ENDIF»
				trace("«event.name»");
				//System.out.println("in " + "«className»." +"«event.name.asIdentifier»" + "[" + selfObjectName+ ":" + selfClassName + "]");
			}
			
			public «event.type.targetLanguageName» get«event.name.asName»Value() {
				«event.getIllegalAccessValidation()»
				return «event.valueIdentifier»;
			}
		«ELSE»
			private void raise«event.name.asName»() {
				«event.symbol» = true;
				«IF entry.createInterfaceObserver»
					for («scope.interfaceListenerName» listener : listeners) {
						listener.on«event.name.asEscapedName»Raised();
					}
				«ENDIF»
				systemEvent("«className»", selfObjectName, "«event.name»");
			}
			
			private void «event.name.asIdentifier»() {
				«event.symbol» = true;
				«IF entry.createInterfaceObserver»
					for («scope.interfaceListenerName» listener : listeners) {
						listener.on«event.name.asEscapedName»Raised();
					}
				«ENDIF»
				trace("«event.name»");
				//System.out.println("in " + "«className»." +"«event.name.asIdentifier»" + "[" + selfObjectName+ ":" + selfClassName + "]");
			}
		«ENDIF»
	'''
	}
	
	override protected def generateVariableDefinition(VariableDefinition variable) '''
		«IF !variable.const»
			«variable.writeableFieldDeclaration»
		«ENDIF»
		public «variable.type.targetLanguageName» «variable.getter» {
			return «variable.symbol»;
		}
		
		«IF !variable.readonly && !variable.const»
			public void «variable.setter»(«variable.type.targetLanguageName» value) {
				this.«variable.symbol» = value;
				ebridge.objectPropertyChanged(selfClassName, selfObjectName, "«variable.name»", "«variable.type.targetLanguageName»", String.valueOf(value));
			}
		«ENDIF»
	'''
	
	
}
