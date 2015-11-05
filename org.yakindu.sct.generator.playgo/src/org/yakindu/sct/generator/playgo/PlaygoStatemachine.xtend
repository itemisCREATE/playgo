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
			
			«flow.objectPropertyChanged»
			
			«flow.getPropertyValue»
				
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
		«IF flow.timed»
			import org.yakindu.scr.TimerService;
		«ENDIF»
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
			«IF flow.timed»
				this.setTimer(new TimerService());
			«ENDIF»

			// enter the statemachine and activate the Idle state
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
				ebridge.systemEvent(this.toString(), selfClassName, selfObjectName, eventName);
			} else {
				ebridge.systemEventSelfExcluded(this.toString(), selfClassName, selfObjectName, targetClassName, eventName);
			}
		}
	'''
	
	def protected objectPropertyChanged(ExecutionFlow flow) '''
		public void objectPropertyChanged(String className, String objectName,
										String propertyName, String type, String value) {
			ebridge.objectPropertyChanged(className, objectName, propertyName, type, value);
		}
	'''
	
	def protected getPropertyValue(ExecutionFlow flow)'''
		public Object getPropertyValue(String objectName, String className,
				String propertyName, String type) {
			return ebridge.getPropertyValue(objectName, className, propertyName, type);
			
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
				// trace("«event.name»");
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
				// trace("«event.name»");
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
				// trace("«event.name»");
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
				// trace("«event.name»");
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
				trace("«variable.scope.interfaceName.substring(3/* removing the SCI prefix to leave the interface name as the user defines it*/)».«variable.name»" + " = " + String.valueOf(value));
			}
		«ENDIF»
	'''

	override protected writeableFieldDeclaration(VariableDefinition variable){
		'''protected «variable.type.targetLanguageName» «variable.symbol»;'''
	}
	
	override protected runCycleFunction(ExecutionFlow flow)'''
		public void runCycle() {
			
			State[] tempStateVector = stateVector.clone();
			
			// copy of the original def protected runCycleFunction(ExecutionFlow flow) from Statemachine.xtend:
			clearOutEvents();
			
			for (nextStateIndex = 0; nextStateIndex < stateVector.length; nextStateIndex++) {
				
				switch (stateVector[nextStateIndex]) {
				«FOR state : flow.states»
					«IF state.reactSequence!=null»
						case «state.stateName.asEscapedIdentifier»:
							«state.reactSequence.functionName»();
							break;
					«ENDIF»
				«ENDFOR»
				default:
					// «getNullStateName()»
				}
			}
			
			clearEvents();
			
			// end of copy of the original def protected runCycleFunction(ExecutionFlow flow) from Statemachine.xtend:
			
			// gather the information about state changes in all regions. if a tracer aspect exists, this info will be 
			// added to the PlayGo PlayoutView
			for (int i = 0; i < stateVector.length; i++)
			{
				String debugMsg = new String();
				if (!stateVector[i].toString().equals(tempStateVector[i].toString())){
					if (debugMsg.isEmpty()){
						debugMsg += "moved to";
					}
					debugMsg = debugMsg + " " + stateVector[i].toString();
				}
				if (!debugMsg.isEmpty()){
					trace(debugMsg);
				}
			}
		}
	'''
	
	
		override def protected initFunction(ExecutionFlow flow) '''
		public void init() {
			«IF flow.timed»
			if (timer == null) {
				throw new IllegalStateException("timer not set.");
			}
			«ENDIF»
			for (int i = 0; i < «flow.stateVector.size»; i++) {
				stateVector[i] = State.$NullState$;
			}
			
			«IF flow.hasHistory»
			for (int i = 0; i < «flow.historyVector.size»; i++) {
				historyVector[i] = State.$NullState$;
			}
			«ENDIF»
			clearEvents();
			clearOutEvents();
			
			«flow.initSequence.code»
			
			«FOR scope: flow.interfaceScopes»
				«FOR variable : scope.variableDefinitions »
					«writeableFieldInit(scope.interfaceName.asEscapedIdentifier, variable)»
				«ENDFOR»
			«ENDFOR»
«««			«FOR variable : flow.internalScopeVariables»
«««					«internalFieldInit(variable)»
«««			«ENDFOR»
			
		}
	'''
	
//	def internalFieldInit(VariableDefinition variable) {
//		'''«variable.type.targetLanguageName» «variable.symbol» = «variable.initialValue»;'''
//	}
	
	def writeableFieldInit(String interfaceName, VariableDefinition variable) {
		'''«interfaceName».«variable.symbol» = («variable.type.targetLanguageName»)getPropertyValue(selfObjectName, selfClassName, "«variable.symbol»", "«variable.type.targetLanguageName»");'''
	}
	
}
