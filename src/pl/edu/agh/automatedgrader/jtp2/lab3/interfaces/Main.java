package pl.edu.agh.automatedgrader.jtp2.lab3.interfaces;

import java.util.List;

import pl.edu.agh.automatedgrader.jtp2.lab3.DefaultFork;
import pl.edu.agh.automatedgrader.jtp2.lab3.DefaultPhilosopher;
import pl.edu.agh.automatedgrader.jtp2.lab3.DefaultWaiter;

public interface Main
{
	//times are integer in milliseconds
	void eatingThinking(int howMany, int numberOfPhilosophers, int maxTimeForEating, int maxTimeForThinking);

	List<DefaultFork> getForks();

	List<DefaultPhilosopher> getPhilosophers();

	DefaultWaiter getDefaultWaiter();
}
