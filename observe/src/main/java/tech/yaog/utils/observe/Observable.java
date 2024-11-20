package tech.yaog.utils.observe;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable <T> {
    private boolean initialized = false;

    protected T data;

    protected List<Observer<T>> observers = new ArrayList<>();

    public Observable() {
    }

    public Observable(T data) {
        if (data != null) {
            update(data);
        }
    }

    public void registerObserver(Observer<T> observer) {
        observers.add(observer);
        if (initialized) {
            observer.onDataChanged(data);
        }
    }

    public void unregisterObserver(Observer<T> observer) {
        observers.remove(observer);
    }

    public void update(T data) {
        initialized = true;
        boolean changed = false;
        if (data != null) {
            if (this.data == null) {
                changed = true;
            }
            else {
                changed = !data.equals(this.data);
            }
        }
        else {
            changed = this.data == null;
        }
        if (changed) {
            this.data = data;
            for (Observer<T> observer : observers) {
                observer.onDataChanged(this.data);
            }
        }
    }
}
