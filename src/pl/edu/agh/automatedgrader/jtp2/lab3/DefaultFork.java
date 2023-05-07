package pl.edu.agh.automatedgrader.jtp2.lab3;

import pl.edu.agh.automatedgrader.jtp2.lab3.interfaces.Fork;

public class DefaultFork implements Fork {
    protected int id;

    public DefaultFork(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
