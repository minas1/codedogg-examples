
package com.codedogg.handlerotationwithviewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class TimerViewModel extends ViewModel implements Timer.Listener {

    private final Timer timer = new Timer();

    private MutableLiveData<Double> elapsedTimeLiveData = new MutableLiveData<>();

    private MutableLiveData<Boolean> isStartedLiveData = new MutableLiveData<>();

    private TimerViewModel() {

        timer.setListener(this);
    }

    public synchronized void start() {

        if (timer.start()) {
            isStartedLiveData.setValue(true);
        }
    }

    public synchronized void pause() {

        timer.pause();
        isStartedLiveData.setValue(false);
    }

    public synchronized void reset() {

        timer.reset();
    }

    @Override
    public void onTimeUpdated(double elapsedTime) {

        elapsedTimeLiveData.postValue(elapsedTime);
    }

    /**
     * @return elapsed time in seconds
     */
    public LiveData<Double> getElapsedTime() {
        return elapsedTimeLiveData;
    }

    public LiveData<Boolean> isStarted() {
        return isStartedLiveData;
    }

    @Override
    protected void onCleared() {

        timer.dispose();
        super.onCleared();
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @SuppressWarnings("unchecked")
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new TimerViewModel();
        }
    }
}
