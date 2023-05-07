package pl.edu.agh.automatedgrader.jtp2.lab3.interfaces;

import pl.edu.agh.automatedgrader.jtp2.lab3.DefaultWaiter;

public interface Philosopher extends Runnable
{
	int getHowMany();

	Fork getLeftFork();

	Fork getRightFork();

	DefaultWaiter getDefaultWaiter();
}
