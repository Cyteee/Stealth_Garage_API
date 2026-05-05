package com.SG.Stealth.Garage.API.repositories.Items.Tools;

import com.SG.Stealth.Garage.API.repositories.Items.MaintenanceTask;

public class OilChangeTask extends MaintenanceTask {

    @Override
    double calculateCost(){
        return 150.0;
    }
}
