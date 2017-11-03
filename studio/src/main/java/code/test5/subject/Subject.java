package code.test5.subject;


import code.test5.observer.Observer;

public interface Subject {
    void addObserver(Observer observer);

    void deleteObserver(Observer observer);

    void notiyf();
}
