package org.firstinspires.ftc.teamcode.util.profiling;

public class Timer {
    private long startTime;

    public Timer() {
        startTime = System.currentTimeMillis();
    }

    public void resetTimer() {
        startTime = System.currentTimeMillis();
    }

    public long getElapsedTime() {
        return System.currentTimeMillis() - startTime;
    }

    public double getElapsedTimeSeconds() {
        return (getElapsedTime() / 1000.0);
    }
}
