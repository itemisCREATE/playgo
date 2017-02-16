package org.yakindu.sct.generator.playgo;

import com.google.common.base.Objects;
import java.util.ArrayList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.yakindu.base.types.Direction;
import org.yakindu.base.types.Type;
import org.yakindu.base.types.typesystem.GenericTypeSystem;
import org.yakindu.sct.generator.java.Statemachine;
import org.yakindu.sct.model.sexec.ExecutionFlow;
import org.yakindu.sct.model.sexec.ExecutionRegion;
import org.yakindu.sct.model.sexec.ExecutionScope;
import org.yakindu.sct.model.sexec.ExecutionState;
import org.yakindu.sct.model.sexec.Sequence;
import org.yakindu.sct.model.sexec.StateVector;
import org.yakindu.sct.model.sexec.TimeEvent;
import org.yakindu.sct.model.sgen.GeneratorEntry;
import org.yakindu.sct.model.stext.stext.EventDefinition;
import org.yakindu.sct.model.stext.stext.InterfaceScope;
import org.yakindu.sct.model.stext.stext.InternalScope;
import org.yakindu.sct.model.stext.stext.VariableDefinition;

@SuppressWarnings("all")
public class PlaygoStatemachine extends Statemachine {
  protected CharSequence content(final ExecutionFlow flow, final GeneratorEntry entry) {
    StringConcatenation _builder = new StringConcatenation();
    String _licenseText = this._genmodelEntries.getLicenseText(entry);
    _builder.append(_licenseText, "");
    _builder.newLineIfNotEmpty();
    _builder.append("package ");
    String _implementationPackageName = this._genmodelEntries.getImplementationPackageName(flow, entry);
    _builder.append(_implementationPackageName, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    CharSequence _createImports = this.createImports(flow, entry);
    _builder.append(_createImports, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("public class ");
    String _statemachineClassName = this._naming.statemachineClassName(flow);
    _builder.append(_statemachineClassName, "");
    _builder.append(" implements ");
    String _statemachineInterfaceName = this._naming.statemachineInterfaceName(flow);
    _builder.append(_statemachineInterfaceName, "");
    _builder.append(", IExecutionEngineSCT {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _createFieldDeclarations = this.createFieldDeclarations(flow, entry);
    _builder.append(_createFieldDeclarations, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _createPlayGoFieldDeclarations = this.createPlayGoFieldDeclarations(flow);
    _builder.append(_createPlayGoFieldDeclarations, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _createToString = this.createToString(flow);
    _builder.append(_createToString, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _createConstructor = this.createConstructor(flow);
    _builder.append(_createConstructor, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _initFunction = this.initFunction(flow);
    _builder.append(_initFunction, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _enterFunction = this.enterFunction(flow);
    _builder.append(_enterFunction, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _exitFunction = this.exitFunction(flow);
    _builder.append(_exitFunction, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _generateStateVectorGetter = this.generateStateVectorGetter(flow);
    _builder.append(_generateStateVectorGetter, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _generateWaitingRegionsGetter = this.generateWaitingRegionsGetter(flow);
    _builder.append(_generateWaitingRegionsGetter, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _clearInEventsFunction = this.clearInEventsFunction(flow);
    _builder.append(_clearInEventsFunction, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _clearOutEventsFunction = this.clearOutEventsFunction(flow);
    _builder.append(_clearOutEventsFunction, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _isActiveFunction = this.isActiveFunction(flow);
    _builder.append(_isActiveFunction, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _isStateActiveFunction = this.isStateActiveFunction(flow);
    _builder.append(_isStateActiveFunction, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _timingFunctions = this.timingFunctions(flow);
    _builder.append(_timingFunctions, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _interfaceAccessors = this.interfaceAccessors(flow);
    _builder.append(_interfaceAccessors, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _internalScopeFunctions = this.internalScopeFunctions(flow);
    _builder.append(_internalScopeFunctions, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _defaultInterfaceFunctions = this.defaultInterfaceFunctions(flow, entry);
    _builder.append(_defaultInterfaceFunctions, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _functionImplementations = this.functionImplementations(flow);
    _builder.append(_functionImplementations, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _runCycleFunction = this.runCycleFunction(flow);
    _builder.append(_runCycleFunction, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _initSuperCycle = this.initSuperCycle(flow);
    _builder.append(_initSuperCycle, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _endSuperCycle = this.endSuperCycle(flow);
    _builder.append(_endSuperCycle, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _isFinal = this.isFinal(flow);
    _builder.append(_isFinal, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _setExecutionBridge = this.setExecutionBridge(flow);
    _builder.append(_setExecutionBridge, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _initEE = this.initEE(flow);
    _builder.append(_initEE, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _activateMethod = this.activateMethod(flow);
    _builder.append(_activateMethod, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _setPropertyValue = this.setPropertyValue(flow);
    _builder.append(_setPropertyValue, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _systemEvent = this.systemEvent(flow);
    _builder.append(_systemEvent, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _objectPropertyChanged = this.objectPropertyChanged(flow);
    _builder.append(_objectPropertyChanged, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _propertyValue = this.getPropertyValue(flow);
    _builder.append(_propertyValue, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _trace = this.trace(flow);
    _builder.append(_trace, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence createConstructor(final ExecutionFlow flow) {
    CharSequence _createConstructor = super.createConstructor(flow);
    StringConcatenation _builder = new StringConcatenation();
    _builder.newLine();
    _builder.append("public ");
    String _statemachineClassName = this._naming.statemachineClassName(flow);
    _builder.append(_statemachineClassName, "");
    _builder.append("(String selfObjName, String selfClassName) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("this();");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("this.selfObjectName = selfObjName;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("this.selfClassName = selfClassName;");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    {
      EList<ExecutionState> _states = flow.getStates();
      for(final ExecutionState state : _states) {
        _builder.append("\t");
        _builder.append("statesAbsoluteName.put(\"");
        String _stateName = this._naming.stateName(state);
        String _asEscapedIdentifier = this._naming.asEscapedIdentifier(_stateName);
        _builder.append(_asEscapedIdentifier, "\t");
        _builder.append("\",\"");
        String _name = state.getName();
        _builder.append(_name, "\t");
        _builder.append("\");");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.append("statesAbsoluteName.put(\"");
    String _nullStateName = this._naming.getNullStateName();
    _builder.append(_nullStateName, "\t");
    _builder.append("\", \"");
    String _nullStateName_1 = this._naming.getNullStateName();
    _builder.append(_nullStateName_1, "\t");
    _builder.append("\");");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    return (_createConstructor + _builder.toString());
  }
  
  public CharSequence createToString(final ExecutionFlow flow) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public String toString(){");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return this.getClass().getSimpleName() + \"[\" + this.selfObjectName+\":\" +this.selfClassName + \"]\";");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("// returns the case sensitive name of the statemachine");
    _builder.newLine();
    _builder.append("public String getStatemachineName(){");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return \"");
    String _name = flow.getName();
    _builder.append(_name, "\t");
    _builder.append("\"; ");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence createImports(final ExecutionFlow flow, final GeneratorEntry entry) {
    CharSequence _createImports = super.createImports(flow, entry);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import java.util.ArrayList;");
    _builder.newLine();
    _builder.append("import java.util.HashMap;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import il.ac.wis.cs.playgo.ee.sct.IExecutionEngineSCT;");
    _builder.newLine();
    _builder.append("import il.ac.wis.cs.playgo.playtoolkit.ebridge.IExecutionBridge;");
    _builder.newLine();
    _builder.append("import il.ac.wis.cs.playgo.ee.sct.ExecutionBridge2SCT;");
    _builder.newLine();
    _builder.append("/*");
    _builder.newLine();
    {
      boolean _isTimed = this._navigation.isTimed(flow);
      if (_isTimed) {
        _builder.append("import org.yakindu.scr.TimerService;");
        _builder.newLine();
      }
    }
    _builder.append("*/");
    _builder.newLine();
    return (_createImports + _builder.toString());
  }
  
  protected CharSequence createFieldDeclarations(final ExecutionFlow flow, final GeneratorEntry entry) {
    StringConcatenation _builder = new StringConcatenation();
    {
      ArrayList<EventDefinition> _internalScopeEvents = this._navigation.getInternalScopeEvents(flow);
      for(final EventDefinition event : _internalScopeEvents) {
        _builder.append("\t");
        _builder.append("private boolean ");
        String _symbol = this._naming.getSymbol(event);
        _builder.append(_symbol, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.newLine();
        {
          boolean _and = false;
          Type _type = event.getType();
          boolean _notEquals = (!Objects.equal(_type, null));
          if (!_notEquals) {
            _and = false;
          } else {
            Type _type_1 = event.getType();
            Type _type_2 = this._iTypeSystem.getType(GenericTypeSystem.VOID);
            boolean _isSame = this._iTypeSystem.isSame(_type_1, _type_2);
            boolean _not = (!_isSame);
            _and = _not;
          }
          if (_and) {
            _builder.append("\t");
            _builder.append("private ");
            Type _type_3 = event.getType();
            String _targetLanguageName = this._iCodegenTypeSystemAccess.getTargetLanguageName(_type_3);
            _builder.append(_targetLanguageName, "\t");
            _builder.append(" ");
            String _valueIdentifier = this._naming.getValueIdentifier(event);
            _builder.append(_valueIdentifier, "\t");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("\t");
    _builder.append("private final boolean[] timeEvents = new boolean[");
    ArrayList<TimeEvent> _timeEvents = this._navigation.getTimeEvents(flow);
    int _size = _timeEvents.size();
    _builder.append(_size, "\t");
    _builder.append("];");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    {
      Iterable<InterfaceScope> _interfaceScopes = this._navigation.getInterfaceScopes(flow);
      for(final InterfaceScope scope : _interfaceScopes) {
        _builder.append("\t");
        CharSequence _implementation = this.toImplementation(scope, entry);
        _builder.append(_implementation, "\t");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("private ");
        String _interfaceImplName = this._naming.getInterfaceImplName(scope);
        _builder.append(_interfaceImplName, "\t");
        _builder.append(" ");
        String _interfaceName = this._naming.getInterfaceName(scope);
        String _asEscapedIdentifier = this._naming.asEscapedIdentifier(_interfaceName);
        _builder.append(_asEscapedIdentifier, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public enum State {");
    _builder.newLine();
    {
      EList<ExecutionState> _states = flow.getStates();
      for(final ExecutionState state : _states) {
        _builder.append("\t\t");
        String _stateName = this._naming.stateName(state);
        String _asEscapedIdentifier_1 = this._naming.asEscapedIdentifier(_stateName);
        _builder.append(_asEscapedIdentifier_1, "\t\t");
        _builder.append(",");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t\t");
    String _nullStateName = this._naming.getNullStateName();
    _builder.append(_nullStateName, "\t\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("};");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    {
      ArrayList<VariableDefinition> _internalScopeVariables = this._navigation.getInternalScopeVariables(flow);
      for(final VariableDefinition variable : _internalScopeVariables) {
        {
          boolean _isConst = variable.isConst();
          boolean _not_1 = (!_isConst);
          if (_not_1) {
            _builder.append("\t");
            CharSequence _writeableFieldDeclaration = this.writeableFieldDeclaration(variable);
            _builder.append(_writeableFieldDeclaration, "\t");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("\t");
    _builder.newLine();
    {
      boolean _hasHistory = this._navigation.hasHistory(flow);
      if (_hasHistory) {
        _builder.append("\t");
        _builder.append("private State[] historyVector = new State[");
        StateVector _historyVector = flow.getHistoryVector();
        int _size_1 = _historyVector.getSize();
        _builder.append(_size_1, "\t");
        _builder.append("];");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.append("private final State[] stateVector = new State[");
    StateVector _stateVector = flow.getStateVector();
    int _size_2 = _stateVector.getSize();
    _builder.append(_size_2, "\t");
    _builder.append("];");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private int nextStateIndex;");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private ExecutionBridge2SCT timer;");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    {
      Iterable<InternalScope> _internalScopes = this._navigation.getInternalScopes(flow);
      for(final InternalScope internal : _internalScopes) {
        {
          boolean _hasOperations = this._navigation.hasOperations(internal);
          if (_hasOperations) {
            _builder.append("\t");
            _builder.append("private ");
            String _internalOperationCallbackName = this._naming.getInternalOperationCallbackName(internal);
            _builder.append(_internalOperationCallbackName, "\t");
            _builder.append(" operationCallback;");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder;
  }
  
  protected CharSequence createPlayGoFieldDeclarations(final ExecutionFlow flow) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("private final ArrayList<String> waitingRegions = new ArrayList<String>();");
    _builder.newLine();
    _builder.newLine();
    _builder.append("private ExecutionBridge2SCT ebridge = null;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("private String selfObjectName = null;");
    _builder.newLine();
    _builder.append("private String selfClassName = null;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("private String activeRegion = \"");
    String _statemachineName = this._naming.statemachineName(flow);
    String _asIdentifier = this._naming.asIdentifier(_statemachineName);
    _builder.append(_asIdentifier, "");
    _builder.append("\";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("public String getSelfObjectName(){");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return selfObjectName;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public String getSelfClassName(){");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return selfClassName;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public String getActiveRegion(){");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return activeRegion;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public HashMap<String, String> statesAbsoluteName  = new HashMap<String, String>();");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence isFinal(final ExecutionFlow flow) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public boolean isFinal() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return false;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence setExecutionBridge(final ExecutionFlow flow) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public void setExecutionBridge(IExecutionBridge eb) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("this.ebridge = (ExecutionBridge2SCT) eb;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence initEE(final ExecutionFlow flow) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public void initEE() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/*");
    {
      boolean _isTimed = this._navigation.isTimed(flow);
      if (_isTimed) {
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("this.setTimer(new TimerService());");
        _builder.newLine();
        _builder.append("\t\t\t");
      }
    }
    _builder.append("*/");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("this.setTimer(this.ebridge);");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("// enter the statemachine and activate the Idle state");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("this.init();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("this.enter();");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateStateVectorGetter(final ExecutionFlow flow) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public ArrayList<String> getStateVector(){");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("ArrayList<String> stateList = new ArrayList<String>();");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("for (State state : stateVector) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("stateList.add(statesAbsoluteName.get(state.toString()));");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return stateList;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateWaitingRegionsGetter(final ExecutionFlow flow) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public ArrayList<String> getWaitingRegions() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return waitingRegions;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence activateMethod(final ExecutionFlow flow) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public void activateMethod(String className, String objectName,");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("String methodName, String... arguments) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("ebridge.activateMethod(className, objectName, methodName, arguments);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence setPropertyValue(final ExecutionFlow flow) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public void setPropertyValue(String className, String objectName,");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("String propertyName, String value) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("// ToDo: add implementation to call setter");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence systemEvent(final ExecutionFlow flow) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public void systemEvent(String targetClassName, String targetObjectName, String eventName) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if(targetClassName.equals(selfClassName) && targetObjectName != null && targetObjectName.equals(selfObjectName)){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("ebridge.systemEvent(this.toString(), selfClassName, selfObjectName, eventName);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("} else {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("ebridge.systemEventSelfExcluded(this.toString(), selfClassName, selfObjectName, targetClassName, eventName);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence objectPropertyChanged(final ExecutionFlow flow) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public void objectPropertyChanged(String className, String objectName,");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t\t");
    _builder.append("String propertyName, String type, String value) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("ebridge.setOriginatedFromExecutionEngine(this.toString());");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("//ebridge.objectPropertyChanged(this.toString(), className, objectName, propertyName, type, value);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("ebridge.setOriginatedFromExecutionEngine(null);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence getPropertyValue(final ExecutionFlow flow) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public Object getPropertyValue(String objectName, String className,");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("String propertyName, String type) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return ebridge.getPropertyValue(objectName, className, propertyName, type);");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence trace(final ExecutionFlow flow) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public void trace(String eventName) {");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence generateInEventDefinition(final EventDefinition event) {
    CharSequence _xblockexpression = null;
    {
      InterfaceScope _scope = this._navigation.scope(event);
      String _interfaceName = this._naming.getInterfaceName(_scope);
      final String className = _interfaceName.substring(3);
      StringConcatenation _builder = new StringConcatenation();
      {
        boolean _and = false;
        Type _type = event.getType();
        boolean _notEquals = (!Objects.equal(_type, null));
        if (!_notEquals) {
          _and = false;
        } else {
          Type _type_1 = event.getType();
          Type _type_2 = this._iTypeSystem.getType(GenericTypeSystem.VOID);
          boolean _isSame = this._iTypeSystem.isSame(_type_1, _type_2);
          boolean _not = (!_isSame);
          _and = _not;
        }
        if (_and) {
          _builder.append("public void raise");
          String _name = event.getName();
          String _asName = this._naming.asName(_name);
          _builder.append(_asName, "");
          _builder.append("(");
          Type _type_3 = event.getType();
          String _targetLanguageName = this._iCodegenTypeSystemAccess.getTargetLanguageName(_type_3);
          _builder.append(_targetLanguageName, "");
          _builder.append(" value) {");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          String _symbol = this._naming.getSymbol(event);
          _builder.append(_symbol, "\t");
          _builder.append(" = true;");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          String _valueIdentifier = this._naming.getValueIdentifier(event);
          _builder.append(_valueIdentifier, "\t");
          _builder.append(" = value;");
          _builder.newLineIfNotEmpty();
          {
            InterfaceScope _scope_1 = this._navigation.scope(event);
            boolean _isDefaultInterface = this._navigation.isDefaultInterface(_scope_1);
            if (_isDefaultInterface) {
              _builder.append("\t");
              _builder.append("systemEvent(selfClassName, selfObjectName, \"");
              String _name_1 = event.getName();
              _builder.append(_name_1, "\t");
              _builder.append("\");");
              _builder.newLineIfNotEmpty();
            } else {
              _builder.append("\t");
              _builder.append("systemEvent(\"");
              _builder.append(className, "\t");
              _builder.append("\", null, \"");
              String _name_2 = event.getName();
              _builder.append(_name_2, "\t");
              _builder.append("\");");
              _builder.newLineIfNotEmpty();
            }
          }
          _builder.append("\t");
          _builder.append("waitingRegions.remove(activeRegion);");
          _builder.newLine();
          _builder.append("}");
          _builder.newLine();
          _builder.newLine();
          _builder.append("public void ");
          String _name_3 = event.getName();
          String _asIdentifier = this._naming.asIdentifier(_name_3);
          _builder.append(_asIdentifier, "");
          _builder.append("(");
          Type _type_4 = event.getType();
          String _targetLanguageName_1 = this._iCodegenTypeSystemAccess.getTargetLanguageName(_type_4);
          _builder.append(_targetLanguageName_1, "");
          _builder.append(" value) {");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          String _symbol_1 = this._naming.getSymbol(event);
          _builder.append(_symbol_1, "\t");
          _builder.append(" = true;");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          String _valueIdentifier_1 = this._naming.getValueIdentifier(event);
          _builder.append(_valueIdentifier_1, "\t");
          _builder.append(" = value;");
          _builder.newLineIfNotEmpty();
          _builder.append("}");
          _builder.newLine();
          _builder.newLine();
          _builder.append("private ");
          Type _type_5 = event.getType();
          String _targetLanguageName_2 = this._iCodegenTypeSystemAccess.getTargetLanguageName(_type_5);
          _builder.append(_targetLanguageName_2, "");
          _builder.append(" get");
          String _name_4 = event.getName();
          String _asName_1 = this._naming.asName(_name_4);
          _builder.append(_asName_1, "");
          _builder.append("Value() {");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          CharSequence _illegalAccessValidation = this.getIllegalAccessValidation(event);
          _builder.append(_illegalAccessValidation, "\t");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("return ");
          String _valueIdentifier_2 = this._naming.getValueIdentifier(event);
          _builder.append(_valueIdentifier_2, "\t");
          _builder.append(";");
          _builder.newLineIfNotEmpty();
          _builder.append("}");
          _builder.newLine();
          _builder.newLine();
        } else {
          _builder.append("public void raise");
          String _name_5 = event.getName();
          String _asName_2 = this._naming.asName(_name_5);
          _builder.append(_asName_2, "");
          _builder.append("() {");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          String _symbol_2 = this._naming.getSymbol(event);
          _builder.append(_symbol_2, "\t");
          _builder.append(" = true;");
          _builder.newLineIfNotEmpty();
          {
            InterfaceScope _scope_2 = this._navigation.scope(event);
            boolean _isDefaultInterface_1 = this._navigation.isDefaultInterface(_scope_2);
            if (_isDefaultInterface_1) {
              _builder.append("\t");
              _builder.append("systemEvent(selfClassName, selfObjectName, \"");
              String _name_6 = event.getName();
              _builder.append(_name_6, "\t");
              _builder.append("\");");
              _builder.newLineIfNotEmpty();
            } else {
              _builder.append("\t");
              _builder.append("systemEvent(\"");
              _builder.append(className, "\t");
              _builder.append("\", null, \"");
              String _name_7 = event.getName();
              _builder.append(_name_7, "\t");
              _builder.append("\");");
              _builder.newLineIfNotEmpty();
            }
          }
          _builder.append("\t");
          _builder.append("waitingRegions.remove(activeRegion);");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.newLine();
          _builder.append("}");
          _builder.newLine();
          _builder.newLine();
          _builder.append("public void ");
          String _name_8 = event.getName();
          String _asIdentifier_1 = this._naming.asIdentifier(_name_8);
          _builder.append(_asIdentifier_1, "");
          _builder.append("() {");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          String _symbol_3 = this._naming.getSymbol(event);
          _builder.append(_symbol_3, "\t");
          _builder.append(" = true;");
          _builder.newLineIfNotEmpty();
          _builder.append("}");
          _builder.newLine();
          _builder.newLine();
        }
      }
      _xblockexpression = _builder;
    }
    return _xblockexpression;
  }
  
  protected CharSequence generateOutEventDefinition(final EventDefinition event, final GeneratorEntry entry, final InterfaceScope scope) {
    CharSequence _xblockexpression = null;
    {
      InterfaceScope _scope = this._navigation.scope(event);
      String _interfaceName = this._naming.getInterfaceName(_scope);
      final String className = _interfaceName.substring(3);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("public boolean isRaised");
      String _name = event.getName();
      String _asName = this._naming.asName(_name);
      _builder.append(_asName, "");
      _builder.append("() {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("return ");
      String _symbol = this._naming.getSymbol(event);
      _builder.append(_symbol, "\t");
      _builder.append(";");
      _builder.newLineIfNotEmpty();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      {
        boolean _and = false;
        Type _type = event.getType();
        boolean _notEquals = (!Objects.equal(_type, null));
        if (!_notEquals) {
          _and = false;
        } else {
          Type _type_1 = event.getType();
          Type _type_2 = this._iTypeSystem.getType(GenericTypeSystem.VOID);
          boolean _isSame = this._iTypeSystem.isSame(_type_1, _type_2);
          boolean _not = (!_isSame);
          _and = _not;
        }
        if (_and) {
          _builder.append("private void raise");
          String _name_1 = event.getName();
          String _asName_1 = this._naming.asName(_name_1);
          _builder.append(_asName_1, "");
          _builder.append("(");
          Type _type_3 = event.getType();
          String _targetLanguageName = this._iCodegenTypeSystemAccess.getTargetLanguageName(_type_3);
          _builder.append(_targetLanguageName, "");
          _builder.append(" value) {");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          String _symbol_1 = this._naming.getSymbol(event);
          _builder.append(_symbol_1, "\t");
          _builder.append(" = true;");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          String _valueIdentifier = this._naming.getValueIdentifier(event);
          _builder.append(_valueIdentifier, "\t");
          _builder.append(" = value;");
          _builder.newLineIfNotEmpty();
          {
            boolean _createInterfaceObserver = this._genmodelEntries.createInterfaceObserver(entry);
            if (_createInterfaceObserver) {
              _builder.append("\t");
              _builder.append("for (");
              String _interfaceListenerName = this._naming.getInterfaceListenerName(scope);
              _builder.append(_interfaceListenerName, "\t");
              _builder.append(" listener : listeners) {");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("\t");
              _builder.append("listener.on");
              String _name_2 = event.getName();
              String _asEscapedName = this._naming.asEscapedName(_name_2);
              _builder.append(_asEscapedName, "\t\t");
              _builder.append("Raised(value);");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("}");
              _builder.newLine();
              {
                InterfaceScope _scope_1 = this._navigation.scope(event);
                boolean _isDefaultInterface = this._navigation.isDefaultInterface(_scope_1);
                if (_isDefaultInterface) {
                  _builder.append("\t");
                  _builder.append("systemEvent(selfClassName, selfObjectName, \"");
                  String _name_3 = event.getName();
                  _builder.append(_name_3, "\t");
                  _builder.append("\");");
                  _builder.newLineIfNotEmpty();
                } else {
                  _builder.append("\t");
                  _builder.append("systemEvent(\"");
                  _builder.append(className, "\t");
                  _builder.append("\", null, \"");
                  String _name_4 = event.getName();
                  _builder.append(_name_4, "\t");
                  _builder.append("\");");
                  _builder.newLineIfNotEmpty();
                }
              }
              _builder.append("\t");
              _builder.append("waitingRegions.remove(activeRegion);");
              _builder.newLine();
            }
          }
          _builder.append("}");
          _builder.newLine();
          _builder.newLine();
          _builder.append("private void ");
          String _name_5 = event.getName();
          String _asIdentifier = this._naming.asIdentifier(_name_5);
          _builder.append(_asIdentifier, "");
          _builder.append("(");
          Type _type_4 = event.getType();
          String _targetLanguageName_1 = this._iCodegenTypeSystemAccess.getTargetLanguageName(_type_4);
          _builder.append(_targetLanguageName_1, "");
          _builder.append(" value) {");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          String _symbol_2 = this._naming.getSymbol(event);
          _builder.append(_symbol_2, "\t");
          _builder.append(" = true;");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          String _valueIdentifier_1 = this._naming.getValueIdentifier(event);
          _builder.append(_valueIdentifier_1, "\t");
          _builder.append(" = value;");
          _builder.newLineIfNotEmpty();
          {
            boolean _createInterfaceObserver_1 = this._genmodelEntries.createInterfaceObserver(entry);
            if (_createInterfaceObserver_1) {
              _builder.append("\t");
              _builder.append("for (");
              String _interfaceListenerName_1 = this._naming.getInterfaceListenerName(scope);
              _builder.append(_interfaceListenerName_1, "\t");
              _builder.append(" listener : listeners) {");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("\t");
              _builder.append("listener.on");
              String _name_6 = event.getName();
              String _asEscapedName_1 = this._naming.asEscapedName(_name_6);
              _builder.append(_asEscapedName_1, "\t\t");
              _builder.append("Raised(value);");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("}");
              _builder.newLine();
            }
          }
          _builder.append("}");
          _builder.newLine();
          _builder.newLine();
          _builder.append("public ");
          Type _type_5 = event.getType();
          String _targetLanguageName_2 = this._iCodegenTypeSystemAccess.getTargetLanguageName(_type_5);
          _builder.append(_targetLanguageName_2, "");
          _builder.append(" get");
          String _name_7 = event.getName();
          String _asName_2 = this._naming.asName(_name_7);
          _builder.append(_asName_2, "");
          _builder.append("Value() {");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          CharSequence _illegalAccessValidation = this.getIllegalAccessValidation(event);
          _builder.append(_illegalAccessValidation, "\t");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("return ");
          String _valueIdentifier_2 = this._naming.getValueIdentifier(event);
          _builder.append(_valueIdentifier_2, "\t");
          _builder.append(";");
          _builder.newLineIfNotEmpty();
          _builder.append("}");
          _builder.newLine();
        } else {
          _builder.append("private void raise");
          String _name_8 = event.getName();
          String _asName_3 = this._naming.asName(_name_8);
          _builder.append(_asName_3, "");
          _builder.append("() {");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          String _symbol_3 = this._naming.getSymbol(event);
          _builder.append(_symbol_3, "\t");
          _builder.append(" = true;");
          _builder.newLineIfNotEmpty();
          {
            boolean _createInterfaceObserver_2 = this._genmodelEntries.createInterfaceObserver(entry);
            if (_createInterfaceObserver_2) {
              _builder.append("\t");
              _builder.append("for (");
              String _interfaceListenerName_2 = this._naming.getInterfaceListenerName(scope);
              _builder.append(_interfaceListenerName_2, "\t");
              _builder.append(" listener : listeners) {");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("\t");
              _builder.append("listener.on");
              String _name_9 = event.getName();
              String _asEscapedName_2 = this._naming.asEscapedName(_name_9);
              _builder.append(_asEscapedName_2, "\t\t");
              _builder.append("Raised();");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("}");
              _builder.newLine();
            }
          }
          {
            InterfaceScope _scope_2 = this._navigation.scope(event);
            boolean _isDefaultInterface_1 = this._navigation.isDefaultInterface(_scope_2);
            if (_isDefaultInterface_1) {
              _builder.append("\t");
              _builder.append("systemEvent(selfClassName, selfObjectName, \"");
              String _name_10 = event.getName();
              _builder.append(_name_10, "\t");
              _builder.append("\");");
              _builder.newLineIfNotEmpty();
            } else {
              _builder.append("\t");
              _builder.append("systemEvent(\"");
              _builder.append(className, "\t");
              _builder.append("\", null, \"");
              String _name_11 = event.getName();
              _builder.append(_name_11, "\t");
              _builder.append("\");");
              _builder.newLineIfNotEmpty();
            }
          }
          _builder.append("\t");
          _builder.append("waitingRegions.remove(activeRegion);");
          _builder.newLine();
          _builder.append("}");
          _builder.newLine();
          _builder.newLine();
          _builder.append("private void ");
          String _name_12 = event.getName();
          String _asIdentifier_1 = this._naming.asIdentifier(_name_12);
          _builder.append(_asIdentifier_1, "");
          _builder.append("() {");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          String _symbol_4 = this._naming.getSymbol(event);
          _builder.append(_symbol_4, "\t");
          _builder.append(" = true;");
          _builder.newLineIfNotEmpty();
          {
            boolean _createInterfaceObserver_3 = this._genmodelEntries.createInterfaceObserver(entry);
            if (_createInterfaceObserver_3) {
              _builder.append("\t");
              _builder.append("for (");
              String _interfaceListenerName_3 = this._naming.getInterfaceListenerName(scope);
              _builder.append(_interfaceListenerName_3, "\t");
              _builder.append(" listener : listeners) {");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("\t");
              _builder.append("listener.on");
              String _name_13 = event.getName();
              String _asEscapedName_3 = this._naming.asEscapedName(_name_13);
              _builder.append(_asEscapedName_3, "\t\t");
              _builder.append("Raised();");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("}");
              _builder.newLine();
            }
          }
          _builder.append("}");
          _builder.newLine();
        }
      }
      _xblockexpression = _builder;
    }
    return _xblockexpression;
  }
  
  protected CharSequence generateVariableDefinition(final VariableDefinition variable) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _isConst = variable.isConst();
      boolean _not = (!_isConst);
      if (_not) {
        CharSequence _writeableFieldDeclaration = this.writeableFieldDeclaration(variable);
        _builder.append(_writeableFieldDeclaration, "");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("public ");
    Type _type = variable.getType();
    String _targetLanguageName = this._iCodegenTypeSystemAccess.getTargetLanguageName(_type);
    _builder.append(_targetLanguageName, "");
    _builder.append(" ");
    String _ter = this._naming.getter(variable);
    _builder.append(_ter, "");
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("return ");
    String _symbol = this._naming.getSymbol(variable);
    _builder.append(_symbol, "\t");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    {
      boolean _and = false;
      boolean _isReadonly = variable.isReadonly();
      boolean _not_1 = (!_isReadonly);
      if (!_not_1) {
        _and = false;
      } else {
        boolean _isConst_1 = variable.isConst();
        boolean _not_2 = (!_isConst_1);
        _and = _not_2;
      }
      if (_and) {
        _builder.append("public void ");
        String _setter = this._naming.setter(variable);
        _builder.append(_setter, "");
        _builder.append("(");
        Type _type_1 = variable.getType();
        String _targetLanguageName_1 = this._iCodegenTypeSystemAccess.getTargetLanguageName(_type_1);
        _builder.append(_targetLanguageName_1, "");
        _builder.append(" value) {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("this.");
        String _symbol_1 = this._naming.getSymbol(variable);
        _builder.append(_symbol_1, "\t");
        _builder.append(" = value;");
        _builder.newLineIfNotEmpty();
        {
          InterfaceScope _scope = this._navigation.scope(variable);
          boolean _isDefaultInterface = this._navigation.isDefaultInterface(_scope);
          if (_isDefaultInterface) {
            _builder.append("\t");
            _builder.append("trace(\"");
            String _name = variable.getName();
            _builder.append(_name, "\t");
            _builder.append("\" + \" = \" + String.valueOf(value) + \" (external change)\");");
            _builder.newLineIfNotEmpty();
          } else {
            _builder.append("\t");
            _builder.append("trace(\"");
            InterfaceScope _scope_1 = this._navigation.scope(variable);
            String _interfaceName = this._naming.getInterfaceName(_scope_1);
            String _substring = _interfaceName.substring(3);
            _builder.append(_substring, "\t");
            _builder.append(".");
            String _name_1 = variable.getName();
            _builder.append(_name_1, "\t");
            _builder.append("\" + \" = \" + String.valueOf(value) + \" (external change)\");");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("}");
        _builder.newLine();
      }
    }
    return _builder;
  }
  
  protected CharSequence writeableFieldDeclaration(final VariableDefinition variable) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("protected ");
    Type _type = variable.getType();
    String _targetLanguageName = this._iCodegenTypeSystemAccess.getTargetLanguageName(_type);
    _builder.append(_targetLanguageName, "");
    _builder.append(" ");
    String _symbol = this._naming.getSymbol(variable);
    _builder.append(_symbol, "");
    _builder.append(";");
    return _builder;
  }
  
  protected CharSequence runCycleFunction(final ExecutionFlow flow) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public void runCycle() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("boolean stateVectorChanged = false;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("activeRegion = \"");
    String _statemachineName = this._naming.statemachineName(flow);
    String _asIdentifier = this._naming.asIdentifier(_statemachineName);
    _builder.append(_asIdentifier, "\t");
    _builder.append("\";");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("// copy of the original def protected runCycleFunction(ExecutionFlow flow) from Statemachine.xtend:");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("clearOutEvents();");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("for (nextStateIndex = 0; nextStateIndex < stateVector.length; nextStateIndex++) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("State[] tempStateVector = stateVector.clone();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("switch (stateVector[nextStateIndex]) {");
    _builder.newLine();
    {
      EList<ExecutionState> _states = flow.getStates();
      for(final ExecutionState state : _states) {
        {
          Sequence _reactSequence = state.getReactSequence();
          boolean _notEquals = (!Objects.equal(_reactSequence, null));
          if (_notEquals) {
            _builder.append("\t\t");
            _builder.append("case ");
            String _stateName = this._naming.stateName(state);
            String _asEscapedIdentifier = this._naming.asEscapedIdentifier(_stateName);
            _builder.append(_asEscapedIdentifier, "\t\t");
            _builder.append(":");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t");
            _builder.append("\t");
            _builder.append("// trigger transitions (i.e., call the corresponding react method) only for regions that are waiting regions");
            _builder.newLine();
            _builder.append("\t\t");
            _builder.append("\t");
            _builder.append("if (waitingRegions.contains(\"");
            ExecutionScope _superScope = state.getSuperScope();
            String _name = _superScope.getName();
            _builder.append(_name, "\t\t\t");
            _builder.append("\")) {");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t");
            _builder.append("\t\t");
            _builder.append("activeRegion = \"");
            ExecutionScope _superScope_1 = state.getSuperScope();
            String _name_1 = _superScope_1.getName();
            _builder.append(_name_1, "\t\t\t\t");
            _builder.append("\";");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t");
            _builder.append("\t\t");
            Sequence _reactSequence_1 = state.getReactSequence();
            String _functionName = this._naming.functionName(_reactSequence_1);
            _builder.append(_functionName, "\t\t\t\t");
            _builder.append("();");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t");
            _builder.append("\t");
            _builder.append("}");
            _builder.newLine();
            _builder.append("\t\t");
            _builder.append("\t");
            _builder.append("break;");
            _builder.newLine();
          }
        }
      }
    }
    _builder.append("\t\t");
    _builder.append("default:");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("// ");
    String _nullStateName = this._naming.getNullStateName();
    _builder.append(_nullStateName, "\t\t\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// if the stateVector changed and if a Tracer aspect exists, this info will be added to the PlayGo PlayoutView:");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("for (int i = 0; i < stateVector.length; i++) { // need to iterate over all regions, as one reaction may effect many regions (e.g., in case of entrance to orthogonal state)");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("String debugMsg = new String();");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("if (! stateVector[i].toString().equals(tempStateVector[i].toString())) {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("stateVectorChanged = true;");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("debugMsg = \"Moved to \"");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("+ statesAbsoluteName.get(stateVector[i].toString());");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("if (!debugMsg.isEmpty()) {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("trace(debugMsg);");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("clearEvents();");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("// Call runCycle recursively until the statemachine has no internal actions to perform.");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if (stateVectorChanged){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("runCycle(); // runCycle (i.e., advance the statemachine) as long as it has internal actions to perform");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence initFunction(final ExecutionFlow flow) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public void init() {");
    _builder.newLine();
    {
      boolean _isTimed = this._navigation.isTimed(flow);
      if (_isTimed) {
        _builder.append("\t");
        _builder.append("if (timer == null) {");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("throw new IllegalStateException(\"timer not set.\");");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
      }
    }
    _builder.append("\t");
    _builder.append("for (int i = 0; i < ");
    StateVector _stateVector = flow.getStateVector();
    int _size = _stateVector.getSize();
    _builder.append(_size, "\t");
    _builder.append("; i++) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("stateVector[i] = State.$NullState$;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    {
      boolean _hasHistory = this._navigation.hasHistory(flow);
      if (_hasHistory) {
        _builder.append("\t");
        _builder.append("for (int i = 0; i < ");
        StateVector _historyVector = flow.getHistoryVector();
        int _size_1 = _historyVector.getSize();
        _builder.append(_size_1, "\t");
        _builder.append("; i++) {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("historyVector[i] = State.$NullState$;");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
      }
    }
    _builder.append("\t");
    _builder.append("clearEvents();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("clearOutEvents();");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    Sequence _initSequence = flow.getInitSequence();
    CharSequence _code = this._flowCode.code(_initSequence);
    _builder.append(_code, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    {
      Iterable<InterfaceScope> _interfaceScopes = this._navigation.getInterfaceScopes(flow);
      for(final InterfaceScope scope : _interfaceScopes) {
        {
          Iterable<VariableDefinition> _variableDefinitions = this._navigation.getVariableDefinitions(scope);
          for(final VariableDefinition variable : _variableDefinitions) {
            _builder.append("\t");
            String _interfaceName = this._naming.getInterfaceName(scope);
            String _asEscapedIdentifier = this._naming.asEscapedIdentifier(_interfaceName);
            CharSequence _writeableFieldInit = this.writeableFieldInit(_asEscapedIdentifier, variable);
            _builder.append(_writeableFieldInit, "\t");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("\t");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence writeableFieldInit(final String interfaceName, final VariableDefinition variable) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(interfaceName, "");
    _builder.append(".");
    String _symbol = this._naming.getSymbol(variable);
    _builder.append(_symbol, "");
    _builder.append(" = (");
    Type _type = variable.getType();
    String _targetLanguageName = this._iCodegenTypeSystemAccess.getTargetLanguageName(_type);
    _builder.append(_targetLanguageName, "");
    _builder.append(")getPropertyValue(selfObjectName, selfClassName, \"");
    String _symbol_1 = this._naming.getSymbol(variable);
    _builder.append(_symbol_1, "");
    _builder.append("\", \"");
    Type _type_1 = variable.getType();
    String _targetLanguageName_1 = this._iCodegenTypeSystemAccess.getTargetLanguageName(_type_1);
    _builder.append(_targetLanguageName_1, "");
    _builder.append("\");");
    return _builder;
  }
  
  public CharSequence initSuperCycle(final ExecutionFlow flow) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public void initSuperCycle(){");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("// clearEvents(); // this removes also external/user events... must not clear all events...");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("waitingRegions.clear();");
    _builder.newLine();
    {
      EList<ExecutionRegion> _regions = flow.getRegions();
      for(final ExecutionRegion region : _regions) {
        _builder.append("\t");
        _builder.append("waitingRegions.add(\"");
        String _name = region.getName();
        _builder.append(_name, "\t");
        _builder.append("\");");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence endSuperCycle(final ExecutionFlow flow) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public void endSuperCycle(){");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("clearEvents(); ");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence generateEventDefinition(final EventDefinition event, final GeneratorEntry entry, final InterfaceScope scope) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public boolean ");
    String _symbol = this._naming.getSymbol(event);
    _builder.append(_symbol, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    {
      boolean _and = false;
      Type _type = event.getType();
      boolean _notEquals = (!Objects.equal(_type, null));
      if (!_notEquals) {
        _and = false;
      } else {
        Type _type_1 = event.getType();
        Type _type_2 = this._iTypeSystem.getType(GenericTypeSystem.VOID);
        boolean _isSame = this._iTypeSystem.isSame(_type_1, _type_2);
        boolean _not = (!_isSame);
        _and = _not;
      }
      if (_and) {
        _builder.append("private ");
        Type _type_3 = event.getType();
        String _targetLanguageName = this._iCodegenTypeSystemAccess.getTargetLanguageName(_type_3);
        _builder.append(_targetLanguageName, "");
        _builder.append(" ");
        String _valueIdentifier = this._naming.getValueIdentifier(event);
        _builder.append(_valueIdentifier, "");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.newLine();
    {
      Direction _direction = event.getDirection();
      boolean _equals = Objects.equal(_direction, Direction.IN);
      if (_equals) {
        CharSequence _generateInEventDefinition = this.generateInEventDefinition(event);
        _builder.append(_generateInEventDefinition, "");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.newLine();
    {
      Direction _direction_1 = event.getDirection();
      boolean _equals_1 = Objects.equal(_direction_1, Direction.OUT);
      if (_equals_1) {
        CharSequence _generateOutEventDefinition = this.generateOutEventDefinition(event, entry, scope);
        _builder.append(_generateOutEventDefinition, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  protected CharSequence timingFunctions(final ExecutionFlow flow) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append("* Set the timer for the state machine to be implemented by the ExecutionBridge. It must be set");
    _builder.newLine();
    _builder.append("* externally on a timed state machine before a run cycle can be correctly");
    _builder.newLine();
    _builder.append("* executed.");
    _builder.newLine();
    _builder.append("* ");
    _builder.newLine();
    _builder.append("* @param timer");
    _builder.newLine();
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public void setTimer(ExecutionBridge2SCT timer) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("this.timer = timer;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.newLine();
    _builder.append("public ExecutionBridge2SCT getTimer() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return timer;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public void timeElapsed(int eventID) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("timeEvents[eventID] = true;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
