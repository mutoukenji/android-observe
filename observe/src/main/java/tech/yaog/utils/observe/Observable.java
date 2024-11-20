package tech.yaog.utils.observe;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象类 Observable，用于实现观察者模式。
 * 该类维护一个数据对象，并在数据变化时通知所有注册的观察者。
 * @param <T> 泛型，表示观察的数据类型。
 */
public abstract class Observable<T> {
    // 标志是否已经初始化
    private boolean initialized = false;

    // 被观察的数据对象
    protected T data;

    // 保存所有注册的观察者
    protected List<Observer<T>> observers = new ArrayList<>();

    /**
     * 默认构造函数
     */
    public Observable() {
    }

    /**
     * 带参数的构造函数，允许直接初始化数据。
     * 如果数据不为空，会直接调用 update 方法。
     * @param data 初始数据
     */
    public Observable(T data) {
        if (data != null) {
            update(data); // 初始化数据并通知观察者
        }
    }

    /**
     * 注册一个观察者到观察者列表中。
     * 如果数据已经初始化，会立即通知该观察者当前数据的状态。
     * @param observer 要注册的观察者
     */
    public void registerObserver(Observer<T> observer) {
        observers.add(observer); // 添加观察者到列表
        if (initialized) {
            // 如果数据已经初始化，立即通知观察者
            observer.onDataChanged(data);
        }
    }

    /**
     * 取消注册一个观察者。
     * @param observer 要取消的观察者
     */
    public void unregisterObserver(Observer<T> observer) {
        observers.remove(observer); // 从列表中移除观察者
    }

    /**
     * 更新数据并通知所有注册的观察者（如果数据发生变化）。
     * @param data 新的数据
     */
    public void update(T data) {
        initialized = true; // 标记为已初始化
        boolean changed = false; // 标志数据是否发生变化

        if (data != null) {
            if (this.data == null) {
                changed = true; // 如果当前数据为空，新数据不为空，标记为已改变
            } else {
                changed = !data.equals(this.data); // 比较新旧数据是否相等
            }
        } else {
            changed = this.data != null; // 如果新数据为 null，只有当前数据不为 null 才认为发生变化
        }

        if (changed) {
            this.data = data; // 更新数据
            // 通知所有观察者数据发生变化
            for (Observer<T> observer : observers) {
                observer.onDataChanged(this.data);
            }
        }
    }
}
