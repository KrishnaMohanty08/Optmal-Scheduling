package com.krishnamohanty08;

public class CPU {
    private static final CPU instance =new CPU();
    private Process currentProcess;
    private CPU(){
        this.currentProcess=null;
    }

    public static CPU getInstance(){
        return instance;
    }
    public boolean isIdle(){
        return currentProcess==null;
    }

    public void execute(Process process){
        this.currentProcess=process;
        if(this.currentProcess !=null){
            this.currentProcess.setState(ProcessState.RUNNING);
        }
    }
    public Process getCurrProcess(){
        return currentProcess;
    }

    //preemtion
    public Process preempt() {
        Process preempted = this.currentProcess;
        if (preempted != null) {
            preempted.setState(ProcessState.READY);
        }
        this.currentProcess = null;
        return preempted;
    }


}
