package org.yakindu.sct.generator.playgo

import org.yakindu.base.types.typesystem.GenericTypeSystem
import org.yakindu.sct.generator.java.Statemachine
import org.yakindu.sct.model.sexec.ExecutionFlow
import org.yakindu.sct.model.sgen.GeneratorEntry
import org.yakindu.sct.model.stext.stext.EventDefinition
import org.yakindu.sct.model.stext.stext.VariableDefinition
import org.yakindu.sct.model.stext.stext.InterfaceScope
import org.yakindu.base.types.Direction

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
			
			«flow.generateStateVectorGetter»
			
			«flow.generateWaitingRegionsGetter»
			
			«flow.clearInEventsFunction»
			
			«flow.clearOutEventsFunction»
			
			«flow.activeFunction»
			
			«flow.timingFunctions»
			
			«flow.interfaceAccessors»
			
			«flow.internalScopeFunctions»
			
			«flow.defaultInterfaceFunctions(entry)»
			
			«flow.functionImplementations»
			
			«flow.runCycleFunction»
			
			«flow.initSuperCycle»
			
			«flow.endSuperCycle»
			
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
	
	override protected createConstructor(ExecutionFlow flow){
		return super.createConstructor(flow) + 
	 '''
		
		public «flow.statemachineClassName»(String selfObjName, String selfClassName) {
			this();
			
			this.selfObjectName = selfObjName;
			this.selfClassName = selfClassName;
			
			«FOR state : flow.states»
			statesAbsoluteName.put("«state.stateName.asEscapedIdentifier»","«state.name»");
			«ENDFOR»
			statesAbsoluteName.put("«getNullStateName()»", "«getNullStateName()»");
		}
		
	'''
	}
	
	def public createToString(ExecutionFlow flow) '''
		public String toString(){
			return this.getClass().getSimpleName() + "[" + this.selfObjectName+":" +this.selfClassName + "]";
		}
		
		// returns the case sensitive name of the statemachine
		public String getStatemachineName(){
			return "«flow.name»"; 
		}
		
	'''
	
	override protected createImports(ExecutionFlow flow, GeneratorEntry entry) {
		return super.createImports(flow, entry) + 
		'''
		import java.util.ArrayList;
		import java.util.HashMap;
		
		import il.ac.wis.cs.playgo.ee.sct.IExecutionEngineSCT;
		import il.ac.wis.cs.playgo.playtoolkit.ebridge.IExecutionBridge;
		import il.ac.wis.cs.playgo.ee.sct.ExecutionBridge2SCT;
		«IF flow.timed»
			import org.yakindu.scr.TimerService;
		«ENDIF»
		'''
	}
	
	def protected createPlayGoFieldDeclarations(ExecutionFlow flow)'''
		private final ArrayList<String> waitingRegions = new ArrayList<String>();
		
		private ExecutionBridge2SCT ebridge = null;
		
		private String selfObjectName = null;
		private String selfClassName = null;
		
		private String activeRegion = "«flow.statemachineName.asIdentifier»";
		
		public String getSelfObjectName(){
			return selfObjectName;
		}
		
		public String getSelfClassName(){
			return selfClassName;
		}
		
		public String getActiveRegion(){
			return activeRegion;
		}
		
		public HashMap<String, String> statesAbsoluteName  = new HashMap<String, String>();
		
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
	
	def public generateStateVectorGetter(ExecutionFlow flow)'''
		public ArrayList<String> getStateVector(){
			ArrayList<String> stateList = new ArrayList<String>();
		
			for (State state : stateVector) {
				stateList.add(statesAbsoluteName.get(state.toString()));
			}
			return stateList;
		}
		
	'''
	
	def public generateWaitingRegionsGetter(ExecutionFlow flow)'''
	public ArrayList<String> getWaitingRegions() {
		return waitingRegions;
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
			if(targetClassName.equals(selfClassName) && targetObjectName != null && targetObjectName.equals(selfObjectName)){
				ebridge.systemEvent(this.toString(), selfClassName, selfObjectName, eventName);
			} else {
				ebridge.systemEventSelfExcluded(this.toString(), selfClassName, selfObjectName, targetClassName, eventName);
			}
		}
	'''
	
	def protected objectPropertyChanged(ExecutionFlow flow) '''
		public void objectPropertyChanged(String className, String objectName,
										String propertyName, String type, String value) {
			ebridge.setOriginatedFromExecutionEngine(this.toString());
			ebridge.objectPropertyChanged(className, objectName, propertyName, type, value);
			ebridge.setOriginatedFromExecutionEngine(null);
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
				«IF event.scope.defaultInterface»
					systemEvent(selfClassName, selfObjectName, "«event.name»");
				«ELSE»
					systemEvent("«className»", null, "«event.name»");
				«ENDIF»
				waitingRegions.remove(activeRegion);
			}
			
			public void «event.name.asIdentifier»(«event.type.targetLanguageName» value) {
				«event.symbol» = true;
				«event.valueIdentifier» = value;
			}
			
			private «event.type.targetLanguageName» get«event.name.asName»Value() {
				«event.getIllegalAccessValidation()»
				return «event.valueIdentifier»;
			}
			
		«ELSE»
			public void raise«event.name.asName»() {
				«event.symbol» = true;
				«IF event.scope.defaultInterface»
					systemEvent(selfClassName, selfObjectName, "«event.name»");
				«ELSE»
					systemEvent("«className»", null, "«event.name»");
				«ENDIF»
				waitingRegions.remove(activeRegion);
					
			}
			
			public void «event.name.asIdentifier»() {
				«event.symbol» = true;
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
					«IF event.scope.defaultInterface»
						systemEvent(selfClassName, selfObjectName, "«event.name»");
					«ELSE»
						systemEvent("«className»", null, "«event.name»");
					«ENDIF»
					waitingRegions.remove(activeRegion);
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
				«IF event.scope.defaultInterface»
					systemEvent(selfClassName, selfObjectName, "«event.name»");
				«ELSE»
					systemEvent("«className»", null, "«event.name»");
				«ENDIF»
				waitingRegions.remove(activeRegion);
			}
			
			private void «event.name.asIdentifier»() {
				«event.symbol» = true;
				«IF entry.createInterfaceObserver»
					for («scope.interfaceListenerName» listener : listeners) {
						listener.on«event.name.asEscapedName»Raised();
					}
				«ENDIF»
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
				«IF variable.scope.defaultInterface»
					trace("«variable.name»" + " = " + String.valueOf(value));
				«ELSE»
					trace("«variable.scope.interfaceName.substring(3/* removing the SCI prefix to leave the interface name as the user defines it*/)».«variable.name»" + " = " + String.valueOf(value));
				«ENDIF»
			}
		«ENDIF»
	'''

	override protected writeableFieldDeclaration(VariableDefinition variable){
		'''protected «variable.type.targetLanguageName» «variable.symbol»;'''
	}
	
	override protected runCycleFunction(ExecutionFlow flow)'''
		public void runCycle() {
			
			boolean stateVectorChanged = false;
			activeRegion = "«flow.statemachineName.asIdentifier»";
			
			// copy of the original def protected runCycleFunction(ExecutionFlow flow) from Statemachine.xtend:
			clearOutEvents();
			
			for (nextStateIndex = 0; nextStateIndex < stateVector.length; nextStateIndex++) {
	
				State[] tempStateVector = stateVector.clone();
				
				switch (stateVector[nextStateIndex]) {
				«FOR state : flow.states»
					«IF state.reactSequence!=null»
						case «state.stateName.asEscapedIdentifier»:
							// trigger transitions (i.e., call the corresponding react method) only for regions that are waiting regions
							if (waitingRegions.contains("«state.superScope.name»")) {
								activeRegion = "«state.superScope.name»";
								«state.reactSequence.functionName»();
							}
							break;
					«ENDIF»
				«ENDFOR»
				default:
					// «getNullStateName()»
				}
		
				// if the stateVector changed and if a Tracer aspect exists, this info will be added to the PlayGo PlayoutView:
				for (int i = 0; i < stateVector.length; i++) { // need to iterate over all regions, as one reaction may effect many regions (e.g., in case of entrance to orthogonal state)
					String debugMsg = new String();
					if (! stateVector[i].toString().equals(tempStateVector[i].toString())) {
						stateVectorChanged = true;
						debugMsg = "Moved to "
								+ statesAbsoluteName.get(stateVector[i].toString());
					}
					if (!debugMsg.isEmpty()) {
						trace(debugMsg);
					}
				}
			}
			
			clearEvents();
			
			// Call runCycle recursively until the statemachine has no internal actions to perform.
			if (stateVectorChanged){
				runCycle(); // runCycle (i.e., advance the statemachine) as long as it has internal actions to perform
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
	
	def initSuperCycle(ExecutionFlow flow){
		'''
		public void initSuperCycle(){
			// clearEvents(); // this removes also external/user events... must not clear all events...
			waitingRegions.clear();
			«FOR region: flow.regions»
					waitingRegions.add("«region.name»");
			«ENDFOR»
		}
		'''
	}
	
		def endSuperCycle(ExecutionFlow flow){
		'''
		public void endSuperCycle(){
			clearEvents(); 
		}
		'''
	}
	
	override protected generateEventDefinition(EventDefinition event, GeneratorEntry entry, InterfaceScope scope) '''
		public boolean «event.symbol»;
		
		«IF event.type != null && !isSame(event.type, getType(GenericTypeSystem.VOID))»
			private «event.type.targetLanguageName» «event.valueIdentifier»;
		«ENDIF»
		
		«IF event.direction == Direction::IN»
			«event.generateInEventDefinition»
		«ENDIF»
		
		«IF event.direction == Direction::OUT»
			«event.generateOutEventDefinition(entry, scope)»
		«ENDIF»
	'''
	
}
