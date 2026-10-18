package com.krishnamohanty08;

import java.util.List;

public class MetricsEvaluator extends Dispatcher {

    public MetricsEvaluator(List<Process> initialJobs, SchedulingAlgorithm strategy) {
        super(initialJobs, strategy);
    }

    @Override
    protected void onSimulationFinished() {
        // Call your evaluator method and print the returned result
        double avgWait = evaluateMetrics();
        System.out.println("---------------------------------------");
        System.out.printf("Final Average Waiting Time: %.2f ticks\n", avgWait);
        System.out.println("===========================\n");
    }
    
    // Removed the parameter since 'completedJob' is inherited from the parent
    public double evaluateMetrics() {
        int sum = 0; 
        
        if (completedJob.isEmpty()) {
            return 0.0;
        }
        
        // Single loop to calculate and sum the times
        for (Process metric : completedJob) {
            metric.setTurnOverTime(metric.getCompletionTime() - metric.getArrivalTime());
            metric.setWaitingTime(metric.getTurnOverTime() - metric.getBurstTime());
            
            // Safety catch for negative waiting times
            if (metric.getWaitingTime() < 0) {
                metric.setWaitingTime(0);
            }
            
            sum += metric.getWaitingTime();
        }
        
        return (double) sum / completedJob.size();
    }
}