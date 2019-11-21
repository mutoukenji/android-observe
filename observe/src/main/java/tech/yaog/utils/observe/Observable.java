package tech.yaog.utils.observe;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable <T> {
    protected T data;

    protected List<Observer<T>> observers = new ArrayList<>();

    public void registerObserver(Observer<T> observer) {
        observers.add(observer);
    }

    public void unregisterObserver(Observer<T> observer) {
        observers.remove(observer);
    }

    public void update(T data) {
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
