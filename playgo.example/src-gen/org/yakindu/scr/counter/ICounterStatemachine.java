package org.yakindu.scr.counter;
import org.yakindu.scr.IStatemachine;
import org.yakindu.scr.ITimerCallback;

public interface ICounterStatemachine extends ITimerCallback, IStatemachine {
	public interface SCICounter {
		public void raiseStart();
		public void raiseNew_cycle();
		public void raiseInterrupt();
		public long getState();
		public void setState(long value);

	}

	public SCICounter getSCICounter();

}
