package com.krishnamohanty08;

public class Process {
    private String processID;
    private int arrivalTime;
    private int burstTime; //duration 
    private int remainingTime;
    private int priority;
    private ProcessState state;

    private int turnOverTime; //CT_AT
    private int completionTime; //timeStamp
    private int waitingTime; //TAT-BT
    private int responseTime; 

    public Process(String processID,int arrivalTime,int burstTime,int remainingTime){
        this.processID=processID;
        this.arrivalTime=arrivalTime;
        this.remainingTime=burstTime;
        this.burstTime=burstTime;
        this.priority=priority;
        this.state=state;
    }

    //Getters
    public String getProcessID(){return processID;}
    public int getArrivalTime(){return arrivalTime;}
    public int getRemainingTime(){return remainingTime;}
    public int getBurstTime(){return burstTime;}
    public int getPriority(){return priority;}
    public ProcessState getState(){return state;}
    public int getCompletionTime(){return completionTime;} 
    
    public void setRemainingTime(int remainingTime){
        this.remainingTime=remainingTime;
    }
    public void setState(ProcessState state){
        this.state=state;
    }
    public void setCompletionTime(int completionTime){
        this.completionTime=completionTime;
    }
}
