package com.krishnamohanty08;

import java.util.ArrayList;
import java.util.List;

public class App {
    
    public static List<Process> getTestWorkload() {
        List<Process> jobs = new ArrayList<>();
        jobs.add(new Process("P1", 0, 5, 1));
        jobs.add(new Process("P2", 1, 3, 1));
        jobs.add(new Process("P3", 2, 8, 1));
        jobs.add(new Process("P4", 4, 2, 1));
        jobs.add(new Process("P5", 1, 3, 1));
        jobs.add(new Process("P6", 2, 10, 1));
        jobs.add(new Process("P7", 4, 2, 1));
        return jobs;
    }

    public static void main(String[] args) {
        
        System.out.println("Executing SJF :");
        Dispatcher sjfDispatcher = new Dispatcher(getTestWorkload(), new SJFAlgorithm());
        sjfDispatcher.startSimulation();

        System.out.println("Executing FCFS :");
        Dispatcher fcfsDispatcher = new Dispatcher(getTestWorkload(), new FCFSAlgorithm());
        fcfsDispatcher.startSimulation();

        System.out.println("Executing RoundRobin :");
        Dispatcher roundRobinDispatcher = new Dispatcher(getTestWorkload(), new RoundRobin(3));
        roundRobinDispatcher.startSimulation();
    
    }
}