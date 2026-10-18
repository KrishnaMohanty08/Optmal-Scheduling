package com.krishnamohanty08;
import java.util.List;

public class FCFSAlgorithm implements SchedulingAlgorithm {
    public Process getNextProcess(List<Process> readyQueue){

        if(readyQueue.isEmpty())return null;
        return readyQueue.remove(0);

    }
    public boolean shouldPreempt(Process currProcess,int timeOnCPU){
        return false;
    }
}
