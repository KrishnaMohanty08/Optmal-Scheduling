package com.krishnamohanty08;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Dispatcher {

    private void printGanttChart() {
        if (timeline.isEmpty()) return;

        List<String> blocks = new ArrayList<>();
        List<Integer> times = new ArrayList<>();
        
        // Compress the timeline array into contiguous blocks
        String currentJob = timeline.get(0);
        int startTime = 0;
        
        for (int i = 1; i < timeline.size(); i++) {
            if (!timeline.get(i).equals(currentJob)) {
                blocks.add(currentJob);
                times.add(startTime);
                currentJob = timeline.get(i);
                startTime = i;
            }
        }
        // Capture the final block
        blocks.add(currentJob);
        times.add(startTime);
        times.add(timeline.size()); 

        // Draw the ASCII Chart
        StringBuilder top = new StringBuilder("+");
        StringBuilder mid = new StringBuilder("|");
        StringBuilder bot = new StringBuilder("+");
        StringBuilder timeLine = new StringBuilder(String.format("%-8d", times.get(0)));

        for (int i = 0; i < blocks.size(); i++) {
            String p = blocks.get(i);
            top.append("-------+");
            mid.append(String.format(" %-5s |", p));
            bot.append("-------+");
            timeLine.append(String.format("%-8d", times.get(i + 1)));
        }

        System.out.println("\n=== GANTT CHART ===");
        System.out.println(top.toString());
        System.out.println(mid.toString());
        System.out.println(bot.toString());
        System.out.println(timeLine.toString());
        System.out.println("===================\n");

        
    }
    // 1. State variables
    private int time;
    protected  List<Process> jobPool;
    protected List<Process> readyQueue;
    protected List<Process> completedJob;
    protected List<String> timeline;
    private SchedulingAlgorithm strategy;
    private CPU cpu;

    private int currentQuantumSlice;

    // 2. Constructor
    public Dispatcher(List<Process> initialJobs, SchedulingAlgorithm strategy) {
        this.time = 0;
        this.timeline = new ArrayList<>();
        this.jobPool = new ArrayList<>(initialJobs);
        this.readyQueue = new ArrayList<>();
        this.completedJob = new ArrayList<>();
        this.strategy = strategy;
        this.cpu = CPU.getInstance();

        this.currentQuantumSlice = 0;
    }

    // 3. The Engine Loop
    public void startSimulation() {

        System.out.println("Starting simulation with strategy: " + strategy.getClass().getSimpleName());

        // The loop continues as long as there is still work to do anywhere in the
        // system
        while (!jobPool.isEmpty() || !readyQueue.isEmpty() || !cpu.isIdle()) {

            // STEP 1: Process Arrivals (The Intake)            
            Iterator<Process> iterator = jobPool.iterator();
            while (iterator.hasNext()) {
                Process p = iterator.next();
                if (p.getArrivalTime() == time) {

                    p.setState(ProcessState.READY);
                    readyQueue.add(p);
                    iterator.remove();
                }
            }
            // STEP 2: Execute the Work (The Burn)
            if (!cpu.isIdle()) {            
                Process current = cpu.getCurrProcess();
                current.setRemainingTime(current.getRemainingTime() - 1);
                currentQuantumSlice++;
                // System.out.println("Executing " + current.getProcessID() + " at time " + time);
            }
            // STEP 3: Check for Termination (The Exit)
            if (!cpu.isIdle()){
                if(cpu.getCurrProcess().getRemainingTime() <= 0 ) {
                    Process finishedProcess = cpu.preempt();
                    finishedProcess.setState(ProcessState.TERMINATED);
                    finishedProcess.setCompletionTime(this.time);
                    completedJob.add(finishedProcess);
                    currentQuantumSlice=0;
                }
                else if(strategy.shouldPreempt(cpu.getCurrProcess(), currentQuantumSlice)){
                    Process preemptProcess=cpu.preempt();
                    preemptProcess.setState(ProcessState.READY);
                    readyQueue.add(preemptProcess);
                    currentQuantumSlice=0;
                }
            }

            // STEP 4: Dispatch the Next Job (The Handoff)
            if (cpu.isIdle()) {
                Process nextJob = strategy.getNextProcess(readyQueue);
                if (nextJob != null) {
                    cpu.execute(nextJob);
                }
            }

            // STEP 5: Advance the Clock
            if (cpu.isIdle()) {
                timeline.add("IDLE");
            } else {
                timeline.add(cpu.getCurrProcess().getProcessID());
            }
            time++;
            
        }

        System.out.println("Simulation complete at time: " + time);
        printGanttChart();
    }
    protected void onSimulationFinished() {}
}
