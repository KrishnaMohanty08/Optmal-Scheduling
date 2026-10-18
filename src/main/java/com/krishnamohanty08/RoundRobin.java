package com.krishnamohanty08;

import java.util.List;

public class RoundRobin implements SchedulingAlgorithm {
    private int timeQuantum;

    public RoundRobin(int timeQuantum) {
        this.timeQuantum = timeQuantum;
    }
    public Process getNextProcess(List<Process> readyQueue){
        if(readyQueue.isEmpty())return null;
        return readyQueue.remove(0);
    }
    public boolean shouldPreempt(Process currProcess,int timeOnCPU){
        return timeOnCPU>=timeQuantum;
    }
}
