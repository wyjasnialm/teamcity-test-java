package pl.edu.agh.automatedgrader.jtp2.lab3;

import pl.edu.agh.automatedgrader.jtp2.lab3.interfaces.Main;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DefaultMain implements Main {
    protected List<DefaultFork> forks = new LinkedList<>();
    protected List<DefaultPhilosopher> philosophers = new LinkedList<>();
    protected List<Thread> philosophersThreads = new LinkedList<>();
    protected DefaultWaiter defaultWaiter = new DefaultWaiter();
    private static final Logger logger = Logger.getLogger(DefaultMain.class.getName());

    public void eatingThinking(int howMany, int numberOfPhilosophers, int maxTimeForEating, int maxTimeForThinking) {
        for(int i = 0; i < numberOfPhilosophers; i++) {
            forks.add(new DefaultFork(i));
        }

        for(int i = 0; i < numberOfPhilosophers; i++) {
            int rightForkId = (i == 0 ? numberOfPhilosophers - 1 : i - 1);
            DefaultPhilosopher tmpDefaultPhilosopher = new DefaultPhilosopher(howMany, forks.get(i), forks.get(rightForkId),
                    defaultWaiter, maxTimeForEating, maxTimeForThinking, "Philosopher" + i);
            philosophers.add(tmpDefaultPhilosopher);
            philosophersThreads.add(new Thread(tmpDefaultPhilosopher));
            philosophersThreads.get(i).start();
        }

        for(Thread philosopherThread : philosophersThreads) {
            try {
                philosopherThread.join();
            } catch (InterruptedException e) {
                logger.log(Level.INFO, "InterruptedException: ", e);
                Thread.currentThread().interrupt();
            }
        }
    }

    public List<DefaultFork> getForks() {
        return forks;
    }

    public List<DefaultPhilosopher> getPhilosophers() {
        return philosophers;
    }

    public DefaultWaiter getDefaultWaiter() {
        return defaultWaiter;
    }
}
