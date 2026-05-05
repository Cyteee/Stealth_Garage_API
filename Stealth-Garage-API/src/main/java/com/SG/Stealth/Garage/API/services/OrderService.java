package com.SG.Stealth.Garage.API.services;

import com.SG.Stealth.Garage.API.repositories.MaintenceRecordRepository;
import com.SG.Stealth.Garage.API.strategies.MaintenanceTaskStrategy;
import com.SG.Stealth.Garage.API.strategies.OilChangeTaskStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderService {

    @Autowired
    private List<MaintenanceTaskStrategy> tasks;

    public double calculateTotalOrderCost(){
        double total = 0.0;

        for (MaintenanceTaskStrategy task: tasks){
            total = total + task.calculateCost();
        }
        return total;
    }
}
