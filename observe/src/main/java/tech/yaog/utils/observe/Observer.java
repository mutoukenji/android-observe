package tech.yaog.utils.observe;

public interface Observer <T> {
    void onDataChanged(T data);
}
