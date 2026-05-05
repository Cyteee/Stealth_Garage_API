package com.SG.Stealth.Garage.API.strategies;

public class OilChangeTaskStrategy implements MaintenanceTaskStrategy {

    @Override
    public double calculateCost(){
        return 150.0;
    }
}
