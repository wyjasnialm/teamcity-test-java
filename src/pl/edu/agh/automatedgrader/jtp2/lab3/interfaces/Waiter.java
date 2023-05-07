package pl.edu.agh.automatedgrader.jtp2.lab3.interfaces;

import java.util.Set;

import pl.edu.agh.automatedgrader.jtp2.lab3.DefaultPhilosopher;

public interface Waiter
{
	Set<Fork> getUsedForks();

	Set<DefaultPhilosopher> getWaitingPhilosophers();
}
