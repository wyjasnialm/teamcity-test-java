package pl.edu.agh.automatedgrader.jtp2.lab3;

import pl.edu.agh.automatedgrader.jtp2.lab3.interfaces.Fork;
import pl.edu.agh.automatedgrader.jtp2.lab3.interfaces.Philosopher;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DefaultPhilosopher implements Philosopher {
    protected int howMany;
    protected Fork leftFork;
    protected Fork rightFork;
    protected DefaultWaiter defaultWaiter;
    protected int maxTimeForEating;
    protected int maxTimeForThinking;
    protected long waitingTime;
    protected String name;
    protected Random random = new Random();
    private static final Logger logger = Logger.getLogger(DefaultPhilosopher.class.getName());
    private static final String IE_STRING = "InterruptedException: ";

    public DefaultPhilosopher(int howMany, Fork leftFork, Fork rightFork, DefaultWaiter defaultWaiter,
                              int maxTimeForEating, int maxTimeForThinking, String name) {
        this.howMany = howMany;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.defaultWaiter = defaultWaiter;
        this.maxTimeForEating = maxTimeForEating;
        this.maxTimeForThinking = maxTimeForThinking;
        this.name = name;
    }

    public int getHowMany() {
        return howMany;
    }

    public Fork getLeftFork() {
        return leftFork;
    }

    public Fork getRightFork() {
        return rightFork;
    }

    public DefaultWaiter getDefaultWaiter() {
        return defaultWaiter;
    }

    public void eat() {
        try {
            Thread.sleep(random.nextInt(maxTimeForEating + 1));
        } catch (InterruptedException e) {
            logger.log(Level.INFO, IE_STRING, e);
            Thread.currentThread().interrupt();
        }
    }

    public void think() {
        try {
            Thread.sleep(random.nextInt(maxTimeForThinking + 1));
        } catch (InterruptedException e) {
            logger.log(Level.INFO, IE_STRING, e);
            Thread.currentThread().interrupt();
        }
    }

    public boolean askForPermission() {
        return defaultWaiter.grantPermission(this, true);
    }

    public void returnForks() {
        defaultWaiter.removeUsedForks(leftFork, rightFork);
    }

    public void run() {
        for(int i = 0; i < howMany; i++) {
            synchronized(this) {
                waitingTime = System.currentTimeMillis();
                while(!askForPermission()) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        logger.log(Level.INFO, IE_STRING, e);
                        Thread.currentThread().interrupt();
                    }
                }

                waitingTime = System.currentTimeMillis() - waitingTime;
                logger.log(Level.INFO, "{0}: Waiting time {1}ms", new Object[] {name, waitingTime});
                eat();
                returnForks();
                think();
            }
        }
    }
}
