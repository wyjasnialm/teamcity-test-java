package pl.edu.agh.automatedgrader.jtp2.lab1.tests;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import pl.edu.agh.automatedgrader.jtp2.lab1.DefaultMain;

class MainTest
{
	private MainTest()
	{
		//empty
	}

	@SuppressWarnings("static-method")
	@Test
	void test()
	{
		DefaultMain main = new DefaultMain();
		int firstElement = 1;
		int secondElement = 2;
		int elementCountInEachThread = 2;
		int threadCount = 10;
		List<Integer> list = main.sum(elementCountInEachThread, threadCount, firstElement, secondElement);
		int elementsCount = elementCountInEachThread * threadCount + 2;
		Assertions.assertEquals(list.size(), elementsCount);
		Assertions.assertEquals(list.get(0), firstElement);
		Assertions.assertEquals(list.get(1), secondElement);
		int previousPreviousElement = firstElement;
		int previousElement = secondElement;
		for (int i = 2; i < elementsCount; i++)
		{
			Assertions.assertEquals(list.get(i), previousPreviousElement + previousElement);
			previousPreviousElement = list.get(i - 1).intValue();
			previousElement = list.get(i).intValue();
		}
	}

}
