package pl.edu.agh.automatedgrader.jtp2.lab2;

import pl.edu.agh.automatedgrader.jtp2.lab2.interfaces.Producer;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DefaultProducer implements Producer {
    protected List<Integer> producedList = new LinkedList<>();
    protected final Queue<Integer> queue;
    protected int howMany;
    protected int sizeLimit;
    protected String name;
    protected final Object consumerLock;
    protected final Object producerLock;
    private static final Logger logger = Logger.getLogger(DefaultProducer.class.getName());

    public DefaultProducer(Queue<Integer> queue, int howMany, int sizeLimit, String name,
                           Object consumerLock, Object producerLock) {
        this.queue = queue;
        this.howMany = howMany;
        this.sizeLimit = sizeLimit;
        this.name = name;
        this.consumerLock = consumerLock;
        this.producerLock = producerLock;
    }

    public List<Integer> getProducedList() {
        return producedList;
    }

    public int getHowMany() {
        return howMany;
    }

    public int getSizeLimit() {
        return sizeLimit;
    }

    public Queue<Integer> getQueue() {
        return queue;
    }

    public Object getConsumerLock() {
        return consumerLock;
    }

    public Object getProducerLock() {
        return producerLock;
    }

    public void run() {
        for(int i = 0; i < howMany; i++) {
            synchronized(producerLock) {
                while(queue.size() == sizeLimit) {
                    logger.log(Level.INFO, "{0}: Queue is full", name);
                    try {
                        producerLock.wait();
                    } catch (InterruptedException e) {
                        logger.log(Level.INFO, "InterruptedException: ", e);
                        Thread.currentThread().interrupt();
                    }
                }

                int number = queue.size() + 1;
                queue.add(number);
                producedList.add(number);
                logger.log(Level.INFO, "{0}: Added {1}", new Object[] {name, number});
            }
            synchronized(consumerLock) {
                consumerLock.notify();
            }
        }
    }
}
