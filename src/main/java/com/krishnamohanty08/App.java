package com.krishnamohanty08;

import com.krishnamohanty08.Dispatcher;
import java.util.ArrayList;
import java.util.List;

public class App {
    
    public static void main(String[] args) {
        
        // 1. Initialize the workload (Process ID, Arrival Time, Burst Time, Priority)
        List<Process> initialJobs = new ArrayList<>();
        
        // Creating a staggered arrival scenario to test the queue
        initialJobs.add(new Process("P1", 0, 5, 1)); 
        initialJobs.add(new Process("P2", 1, 3, 1)); 
        initialJobs.add(new Process("P3", 2, 8, 1)); 
        initialJobs.add(new Process("P4", 4, 2, 1)); 

        // 2. Select the strategy (The Rulebook)
        SchedulingAlgorithm fcfsStrategy = new FCFSAlgorithm();

        // 3. Assemble the engine
        Dispatcher dispatcher = new Dispatcher(initialJobs, fcfsStrategy);
        
        // 4. Ignition
        dispatcher.startSimulation();
    }
}