package pl.edu.agh.automatedgrader.jtp2.lab1.interfaces;

import java.util.List;

public interface Sum extends Runnable
{
	//how many elements each thread should add
	int getHowMany();

	List<Integer> getList();
}
