/**
 * Copyright (c) 2015 committers of YAKINDU and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * 	committers of YAKINDU - initial API and implementation
 * 
 */

package org.yakindu.sct.generator.playgo;

import org.yakindu.sct.generator.java.JavaCodeGenerator;
import org.yakindu.sct.generator.java.Statemachine;
import org.yakindu.sct.model.sgen.GeneratorEntry;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.util.Modules;

/**
 * 
 * @author andreas muelder - Initial contribution and API
 * 
 */
public class PlaygoCodeGenerator extends JavaCodeGenerator {

	@Override
	protected Module getChildInjectorModule(GeneratorEntry entry) {
		Module module = super.getChildInjectorModule(entry);
		return Modules.override(module).with(new Module() {
			public void configure(Binder binder) {
				// Provide override bindings here
				binder.bind(Statemachine.class).to(PlaygoStatemachine.class);
			}
		});
	}
}
