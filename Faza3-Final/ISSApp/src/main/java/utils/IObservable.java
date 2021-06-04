package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public interface IObservable {
    List<IObserver> observers = new ArrayList<IObserver>();

    void addObserver(IObserver e);
    void removeObserver(IObserver e);
    void notifyObservers();
}
