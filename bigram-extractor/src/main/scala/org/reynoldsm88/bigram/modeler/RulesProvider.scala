package org.reynoldsm88.bigram.modeler

import org.kie.api.{KieBase, KieServices}

trait RulesProvider {
    def rules( group : String, artifact : String, version : String ) : KieBase
}

trait ClasspathRulesProvider extends RulesProvider {
    @Override
    def rules( group : String, artifact : String, version : String ) : KieBase = {
        KieServices.Factory.get.newKieClasspathContainer().getKieBase
    }
}