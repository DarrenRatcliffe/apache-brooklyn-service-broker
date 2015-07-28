package org.cloudfoundry.community.servicebroker.brooklyn.config;

import org.cloudfoundry.community.servicebroker.brooklyn.service.plan.CatalogPlanStrategy;
import org.cloudfoundry.community.servicebroker.brooklyn.service.plan.SizePlanStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("size-plan")
public class SizePlanConfig {
    
    @Bean
    public CatalogPlanStrategy planStrategy(BrooklynConfig brooklynConfig){
        return new SizePlanStrategy(brooklynConfig);
    }

}