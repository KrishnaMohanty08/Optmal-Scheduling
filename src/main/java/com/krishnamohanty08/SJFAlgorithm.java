package com.krishnamohanty08;
import java.util.ArrayList;
import java.util.List;

public class SJFAlgorithm implements SchedulingAlgorithm{
    public Process getNextProcess(List<Process> readyQueue){
        
        
        if(readyQueue.isEmpty())return null;

        if(readyQueue.size()==1)return readyQueue.remove(0);

        int minBurstTime=readyQueue.get(0).getBurstTime();
        int minProcess=0;

        if(readyQueue.size()>1){
            for(int i=1;i<readyQueue.size();i++){
                if(minBurstTime > readyQueue.get(i).getBurstTime()){
                    minBurstTime=readyQueue.get(i).getBurstTime();
                    minProcess=i;
                };

            }
            return readyQueue.remove(minProcess);
        }
        return null;
    }
    public boolean shouldPreempt(Process currProcess,int timeOnCPU){
        return false;
    }
}
