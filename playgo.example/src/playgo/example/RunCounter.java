package playgo.example;

import org.yakindu.scr.TimerService;
import org.yakindu.scr.counter.RunnableCounter;

public class RunCounter {

	public static void main(String[] args) throws InterruptedException {

		TimerService timer = new TimerService();

		RunnableCounter counter = new RunnableCounter();
		counter.setTimer(timer);

		Thread t = new Thread(counter);
		counter.init();
		counter.enter();
		t.start();

		counter.getSCICounter().raiseStart();

		do {

			Thread.sleep(1000);
			System.out.println("counter.state == "
					+ counter.getSCICounter().getState());

			if (counter.getSCICounter().getState() >= 4)
				counter.getSCICounter().raiseInterrupt();

		} while (counter.isActive() && !counter.isFinal());

		System.out.println("active: " + counter.isActive());
		System.out.println("final: " + counter.isFinal());
		

	}

}
