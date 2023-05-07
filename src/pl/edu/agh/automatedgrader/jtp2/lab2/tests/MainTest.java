package pl.edu.agh.automatedgrader.jtp2.lab2.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;

import pl.edu.agh.automatedgrader.jtp2.lab2.DefaultMain;

class MainTest
{
	private MainTest()
	{
		//empty
	}

	@SuppressWarnings("static-method")
	@RepeatedTest(20)
	void test()
	{
		DefaultMain main = new DefaultMain();
		int howMany = 2;
		int sizeLimit = 3;
		int consumerCount = 10;
		int producerCount = 10;
		main.produceConsume(howMany, sizeLimit, consumerCount, producerCount);
		Assertions.assertEquals(main.getConsumers().size(), consumerCount);
		Assertions.assertEquals(main.getProducers().size(), producerCount);
		Assertions.assertTrue(main.getQueue().isEmpty());
		for (int i = 0; i < howMany; i++)
		{
			Assertions.assertTrue(main.getConsumers().get(i).getConsumedList().size() == 2);
		}
	}

}
