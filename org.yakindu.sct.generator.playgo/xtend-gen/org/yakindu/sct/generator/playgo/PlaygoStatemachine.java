package org.yakindu.sct.generator.playgo;

import com.google.common.base.Objects;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.yakindu.base.types.Type;
import org.yakindu.base.types.typesystem.GenericTypeSystem;
import org.yakindu.sct.generator.java.Statemachine;
import org.yakindu.sct.model.sexec.ExecutionFlow;
import org.yakindu.sct.model.sgen.GeneratorEntry;
import org.yakindu.sct.model.stext.stext.EventDefinition;
import org.yakindu.sct.model.stext.stext.InterfaceScope;
import org.yakindu.sct.model.stext.stext.VariableDefinition;

@SuppressWarnings("all")
public class PlaygoStatemachine extends Statemachine {
  @Override
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
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  @Override
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
    return _builder;
  }
  
  @Override
  protected CharSequence createImports(final ExecutionFlow flow, final GeneratorEntry entry) {
    CharSequence _createImports = super.createImports(flow, entry);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import il.ac.wis.cs.playgo.ee.sct.IExecutionEngineSCT;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("import il.ac.wis.cs.playgo.playtoolkit.ebridge.IExecutionBridge;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("import il.ac.wis.cs.playgo.ee.sct.ExecutionBridge2SCT;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("import org.yakindu.scr.TimerService;");
    _builder.newLine();
    return (_createImports + _builder.toString());
  }
  
  protected CharSequence createPlayGoFieldDeclarations(final ExecutionFlow flow) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("private ExecutionBridge2SCT ebridge = null;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("private String selfObjectName = null;");
    _builder.newLine();
    _builder.append("private String selfClassName = null;");
    _builder.newLine();
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
    _builder.append("this.setTimer(new TimerService());");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("// enter the sm and active the Idle state");
    _builder.newLine();
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
    _builder.append("if(ebridge.isOriginatedFromExecutionEngine()) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("if(targetClassName.equalsIgnoreCase(\"self\")){");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("ebridge.systemEvent(selfClassName, selfObjectName, eventName);");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("} else {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("ebridge.systemEventSelfExcluded(selfClassName, selfObjectName, targetClassName, eventName);");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  @Override
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
          _builder.append("\t");
          _builder.append("systemEvent(\"");
          _builder.append(className, "\t");
          _builder.append("\", selfObjectName, \"");
          String _name_1 = event.getName();
          _builder.append(_name_1, "\t");
          _builder.append("\");");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("}");
          _builder.newLine();
          _builder.append("}");
          _builder.newLine();
          _builder.newLine();
          _builder.append("private ");
          Type _type_4 = event.getType();
          String _targetLanguageName_1 = this._iCodegenTypeSystemAccess.getTargetLanguageName(_type_4);
          _builder.append(_targetLanguageName_1, "");
          _builder.append(" get");
          String _name_2 = event.getName();
          String _asName_1 = this._naming.asName(_name_2);
          _builder.append(_asName_1, "");
          _builder.append("Value() {");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          CharSequence _illegalAccessValidation = this.getIllegalAccessValidation(event);
          _builder.append(_illegalAccessValidation, "\t");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("return ");
          String _valueIdentifier_1 = this._naming.getValueIdentifier(event);
          _builder.append(_valueIdentifier_1, "\t");
          _builder.append(";");
          _builder.newLineIfNotEmpty();
          _builder.append("}");
          _builder.newLine();
          _builder.newLine();
        } else {
          _builder.append("public void raise");
          String _name_3 = event.getName();
          String _asName_2 = this._naming.asName(_name_3);
          _builder.append(_asName_2, "");
          _builder.append("() {");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          String _symbol_1 = this._naming.getSymbol(event);
          _builder.append(_symbol_1, "\t");
          _builder.append(" = true;");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("systemEvent(\"");
          _builder.append(className, "\t");
          _builder.append("\", selfObjectName, \"");
          String _name_4 = event.getName();
          _builder.append(_name_4, "\t");
          _builder.append("\");");
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
  
  @Override
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
        _builder.append("\t");
        _builder.append("ebridge.objectPropertyChanged(selfClassName, selfObjectName, \"");
        String _name = variable.getName();
        _builder.append(_name, "\t");
        _builder.append("\", \"");
        Type _type_2 = variable.getType();
        String _targetLanguageName_2 = this._iCodegenTypeSystemAccess.getTargetLanguageName(_type_2);
        _builder.append(_targetLanguageName_2, "\t");
        _builder.append("\", String.valueOf(value));");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
      }
    }
    return _builder;
  }
}
