/*
 * generated by Xtext 2.21.0
 */
package org.example.expressions;


/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
public class ExpressionsStandaloneSetup extends ExpressionsStandaloneSetupGenerated {

	public static void doSetup() {
		new ExpressionsStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}
