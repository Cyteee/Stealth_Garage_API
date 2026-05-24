package com.SG.Stealth.Garage.API.strategies;

import org.springframework.stereotype.Component;

@Component
public class OilChangeTaskStrategy implements MaintenanceTaskStrategy {

    @Override
    public double calculateCost(){
        return 150.0;
    }
}
