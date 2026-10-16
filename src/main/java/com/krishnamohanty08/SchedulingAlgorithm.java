package com.krishnamohanty08;

import java.util.List;

public interface SchedulingAlgorithm {
    Process getNextProcess(List<Process> readyQueue);
}

