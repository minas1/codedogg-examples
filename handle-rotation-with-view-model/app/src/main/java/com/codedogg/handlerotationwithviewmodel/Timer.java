package com.codedogg.handlerotationwithviewmodel;

import androidx.annotation.Nullable;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Timer {

    interface Listener {

        void onTimeUpdated(double elapsedTime);
    }

    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    /** Total elapsed time */
    private long elapsedTime;

    /** Start time since last update */
    private long startTime;

    private boolean isStarted;

    @Nullable
    private volatile Listener listener;

    @Nullable
    private ScheduledFuture<?> scheduledFuture;

    public void setListener(@Nullable Listener listener) {

        this.listener = listener;

        if (listener != null) {
            listener.onTimeUpdated(elapsedTime / 1e9);
        }
    }

    /**
     *
     * @return {@code true} when the timer has been started, {@code false} if it was already running.
     */
    public synchronized boolean start() {

        if (isStarted) {
            return false;
        }

        isStarted = true;
        startTime = System.nanoTime();

        Listener listener = Timer.this.listener;
        if (listener != null) {
            listener.onTimeUpdated(elapsedTime / 1e9);
        }

        startTask();

        return true;
    }

    private void startTask() {

        scheduledFuture = executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {

                synchronized (Timer.this) {

                    long currentTime = System.nanoTime();

                    long dt = currentTime - startTime;
                    elapsedTime += dt;

                    startTime = currentTime;
                }

                Listener listener = Timer.this.listener;
                if (listener != null) {
                    listener.onTimeUpdated(elapsedTime / 1e9);
                }
            }
        }, 0, 100, TimeUnit.MILLISECONDS);
    }

    public synchronized void pause() {

        isStarted = false;
        startTime = 0;

        stopScheduledJob();
    }

    public synchronized void reset() {

        elapsedTime = 0;
        startTime = System.nanoTime();

        Listener listener = Timer.this.listener;
        if (listener != null) {
            listener.onTimeUpdated(0.0);
        }
    }

    private void stopScheduledJob() {

        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
            scheduledFuture = null;
        }
    }

    public void dispose() {

        executorService.shutdown();
    }
}
