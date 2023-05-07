package pl.edu.agh.automatedgrader.jtp2.lab1;

import pl.edu.agh.automatedgrader.jtp2.lab1.interfaces.Main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DefaultMain implements Main {
    private static final Logger logger = Logger.getLogger(DefaultMain.class.getName());

    public List<Integer> sum(int count, int threadCount, int firstElement, int secondElement) {
        List<Integer> list = new ArrayList<>();
        list.add(firstElement);
        list.add(secondElement);

        List<Thread> threadList = new LinkedList<>();

        for(int i = 0; i < threadCount; i++) {
            threadList.add(new Thread(new DefaultSum(list, count)));
            threadList.get(i).start();
        }

        for(Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                logger.log(Level.INFO, "InterruptedException: ", e);
                Thread.currentThread().interrupt();
            }
        }

        return list;
    }
}
