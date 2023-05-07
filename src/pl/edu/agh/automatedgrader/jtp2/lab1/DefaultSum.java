package pl.edu.agh.automatedgrader.jtp2.lab1;

import pl.edu.agh.automatedgrader.jtp2.lab1.interfaces.Sum;

import java.util.List;

public class DefaultSum implements Sum {
    protected final List<Integer> list;
    protected int howMany;

    public DefaultSum(List<Integer> list, int howMany) {
        this.list = list;
        this.howMany = howMany;
    }

    public int getHowMany() {
        return howMany;
    }

    public List<Integer> getList() {
        return list;
    }

    public void run() {
        for(int i = 0; i < howMany; i++) {
            synchronized(list) {
                int firstElement = list.get(list.size() - 2);
                int secondElement = list.get(list.size() - 1);
                list.add(firstElement + secondElement);
                list.notifyAll();
            }
        }
    }
}
