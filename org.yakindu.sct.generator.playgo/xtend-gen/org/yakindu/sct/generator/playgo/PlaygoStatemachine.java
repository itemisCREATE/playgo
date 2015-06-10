package org.yakindu.sct.generator.playgo;

import com.google.common.base.Objects;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.yakindu.base.types.Direction;
import org.yakindu.base.types.Type;
import org.yakindu.base.types.typesystem.GenericTypeSystem;
import org.yakindu.sct.generator.java.Statemachine;
import org.yakindu.sct.model.sexec.ExecutionFlow;
import org.yakindu.sct.model.sgen.GeneratorEntry;
import org.yakindu.sct.model.stext.stext.EventDefinition;
import org.yakindu.sct.model.stext.stext.InterfaceScope;

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
    _builder.append(" extends PlayableFramework implements ");
    String _statemachineInterfaceName = this._naming.statemachineInterfaceName(flow);
    _builder.append(_statemachineInterfaceName, "");
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _createFieldDeclarations = this.createFieldDeclarations(flow, entry);
    _builder.append(_createFieldDeclarations, "\t");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
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
    CharSequence _initialize = this.initialize(flow);
    _builder.append(_initialize, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _isFinal = this.isFinal(flow);
    _builder.append(_isFinal, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  @Override
  protected CharSequence createImports(final ExecutionFlow flow, final GeneratorEntry entry) {
    CharSequence _createImports = super.createImports(flow, entry);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import il.ac.wis.cs.playgo.playtoolkit.container.PlayableFramework;");
    return (_createImports + _builder.toString());
  }
  
  protected CharSequence initialize(final ExecutionFlow flow) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public void initialize() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("// TODO Auto-generated method stub\t");
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
    _builder.append("// TODO Auto-generated method stub\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return false;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  @Override
  public CharSequence generateEventDefinition(final EventDefinition event, final GeneratorEntry entry, final InterfaceScope scope) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("private boolean ");
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
    _builder.append("Utils.invokeMethod(new String(\"");
    String _interfaceName = this._naming.getInterfaceName(scope);
    _builder.append(_interfaceName, "");
    _builder.append("\").substring(3), \"");
    String _name = event.getName();
    _builder.append(_name, "");
    _builder.append("\", null, null);");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
}
