package pl.edu.agh.automatedgrader.jtp2.lab2;

import pl.edu.agh.automatedgrader.jtp2.lab2.interfaces.Consumer;
import pl.edu.agh.automatedgrader.jtp2.lab2.interfaces.Main;
import pl.edu.agh.automatedgrader.jtp2.lab2.interfaces.Producer;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DefaultMain implements Main {
    protected List<Consumer> consumers = new LinkedList<>();
    protected List<Thread> consumersThreads = new LinkedList<>();
    protected Object consumerLock = new Object();
    protected List<Producer> producers = new LinkedList<>();
    protected List<Thread> producersThreads = new LinkedList<>();
    protected Object producerLock = new Object();
    protected Queue<Integer> queue;
    private static final Logger logger = Logger.getLogger(DefaultMain.class.getName());

    public void produceConsume(int howMany, int sizeLimit, int consumerCount, int producerCount) {
        queue = new ArrayBlockingQueue<>(sizeLimit);

        for(int i = 0; i < consumerCount; i++) {
            DefaultConsumer tmpDefaultConsumer = new DefaultConsumer(
                    queue, howMany, "Consumer" + i, consumerLock, producerLock);
            consumers.add(tmpDefaultConsumer);
            consumersThreads.add(new Thread(tmpDefaultConsumer));
            consumersThreads.get(i).start();
        }

        for(int i = 0; i < producerCount; i++) {
            DefaultProducer tmpDefaultProducer = new DefaultProducer(
                    queue, howMany, sizeLimit, "Producer" + i, consumerLock, producerLock);
            producers.add(tmpDefaultProducer);
            producersThreads.add(new Thread(tmpDefaultProducer));
            producersThreads.get(i).start();
        }

        for(Thread consumerThread : consumersThreads) {
            try {
                consumerThread.join();
            } catch (InterruptedException e) {
                logger.log(Level.INFO, "InterruptedException: ", e);
                Thread.currentThread().interrupt();
            }
        }

        for(Thread producerThread : producersThreads) {
            try {
                producerThread.join();
            } catch (InterruptedException e) {
                logger.log(Level.INFO, "InterruptedException: ", e);
                Thread.currentThread().interrupt();
            }
        }
    }

    public List<Consumer> getConsumers() {
        return consumers;
    }

    public List<Producer> getProducers() {
        return producers;
    }

    public Queue<Integer> getQueue() {
        return queue;
    }
}
