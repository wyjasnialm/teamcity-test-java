package pl.edu.agh.automatedgrader.jtp2.lab3;

import pl.edu.agh.automatedgrader.jtp2.lab3.interfaces.Fork;
import pl.edu.agh.automatedgrader.jtp2.lab3.interfaces.Waiter;

import java.util.HashSet;
import java.util.Set;

public class DefaultWaiter implements Waiter {
    protected Set<Fork> usedForks = new HashSet<>();
    protected Set<DefaultPhilosopher> waitingPhilosophers = new HashSet<>();
    protected Set<DefaultPhilosopher> philosophersToNotify = new HashSet<>();

    public Set<Fork> getUsedForks() {
        return usedForks;
    }

    public Set<DefaultPhilosopher> getWaitingPhilosophers() {
        return waitingPhilosophers;
    }

    private void addUsedForks(Fork leftFork, Fork rightFork) {
        usedForks.add(leftFork);
        usedForks.add(rightFork);
    }

    public synchronized void removeUsedForks(Fork leftFork, Fork rightFork) {
        usedForks.remove(leftFork);
        usedForks.remove(rightFork);

        for(DefaultPhilosopher defaultPhilosopher : waitingPhilosophers) {
            if(defaultPhilosopher.getLeftFork() == rightFork || defaultPhilosopher.getRightFork() == leftFork) {
                if(grantPermission(defaultPhilosopher, false)) {
                    philosophersToNotify.add(defaultPhilosopher);
                }
            }
        }

        for(DefaultPhilosopher defaultPhilosopher : philosophersToNotify) {
            synchronized(defaultPhilosopher) {
                defaultPhilosopher.notifyAll();
            }
        }

        philosophersToNotify.clear();
    }

    private void addWaitingPhilosopher(DefaultPhilosopher defaultPhilosopher) {
        waitingPhilosophers.add(defaultPhilosopher);
    }

    private void removeWaitingPhilosopher(DefaultPhilosopher defaultPhilosopher) {
        waitingPhilosophers.remove(defaultPhilosopher);
    }

    public synchronized boolean grantPermission(DefaultPhilosopher defaultPhilosopher, boolean takeForks) {
        Fork leftFork = defaultPhilosopher.getLeftFork();
        Fork rightFork = defaultPhilosopher.getRightFork();

        if(usedForks.contains(leftFork) || usedForks.contains(rightFork)) {
            if(!waitingPhilosophers.contains(defaultPhilosopher)) addWaitingPhilosopher(defaultPhilosopher);
            return false;
        }
        else {
            if(takeForks) {
                addUsedForks(leftFork, rightFork);
                if(waitingPhilosophers.contains(defaultPhilosopher)) removeWaitingPhilosopher(defaultPhilosopher);
            }
            return true;
        }
    }
}
