package pl.edu.agh.automatedgrader.jtp2.lab2;

import pl.edu.agh.automatedgrader.jtp2.lab2.interfaces.Consumer;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DefaultConsumer implements Consumer {
    protected List<Integer> consumedList = new LinkedList<>();
    protected final Queue<Integer> queue;
    protected int howMany;
    protected String name;
    protected final Object consumerLock;
    protected final Object producerLock;
    private static final Logger logger = Logger.getLogger(DefaultConsumer.class.getName());

    public DefaultConsumer(Queue<Integer> queue, int howMany, String name, Object consumerLock, Object producerLock) {
        this.queue = queue;
        this.howMany = howMany;
        this.name = name;
        this.consumerLock = consumerLock;
        this.producerLock = producerLock;
    }

    public List<Integer> getConsumedList() {
        return consumedList;
    }

    public int getHowMany() {
        return howMany;
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
            synchronized(consumerLock) {
                while(queue.isEmpty()) {
                    logger.log(Level.INFO, "{0}: Queue is empty", name);
                    try {
                        consumerLock.wait();
                    } catch (InterruptedException e) {
                        logger.log(Level.INFO, "InterruptedException: ", e);
                        Thread.currentThread().interrupt();
                    }
                }

                consumedList.add(queue.element());
                logger.log(Level.INFO, "{0}: Removed {1}", new Object[] {name, queue.remove()});
            }
            synchronized(producerLock) {
                producerLock.notify();
            }
        }
    }
}
