package org.yakindu.scr.counter;
import org.yakindu.scr.ITimer;

public class CounterStatemachine implements ICounterStatemachine {
	private final boolean[] timeEvents = new boolean[3];

	private final class SCICounterImpl implements SCICounter {

		private boolean start;

		public void raiseStart() {
			start = true;
		}

		private boolean new_cycle;

		public void raiseNew_cycle() {
			new_cycle = true;
		}

		private boolean interrupt;

		public void raiseInterrupt() {
			interrupt = true;
		}

		private long state;
		public long getState() {
			return state;
		}

		public void setState(long value) {
			this.state = value;
		}

		public void clearEvents() {
			start = false;
			new_cycle = false;
			interrupt = false;
		}

	}

	private SCICounterImpl sCICounter;

	public enum State {
		main_region_init, main_region_s1, main_region_s2, main_region_s3, main_region__final_, $NullState$
	};

	private final State[] stateVector = new State[1];

	private int nextStateIndex;

	private ITimer timer;

	public CounterStatemachine() {

		sCICounter = new SCICounterImpl();
	}

	public void init() {
		if (timer == null) {
			throw new IllegalStateException("timer not set.");
		}
		for (int i = 0; i < 1; i++) {
			stateVector[i] = State.$NullState$;
		}

		clearEvents();
		clearOutEvents();

		sCICounter.state = 0;
	}

	public void enter() {
		if (timer == null) {
			throw new IllegalStateException("timer not set.");
		}
		entryAction();

		nextStateIndex = 0;
		stateVector[0] = State.main_region_init;
	}

	public void exit() {
		switch (stateVector[0]) {
			case main_region_init :
				nextStateIndex = 0;
				stateVector[0] = State.$NullState$;
				break;

			case main_region_s1 :
				nextStateIndex = 0;
				stateVector[0] = State.$NullState$;

				timer.unsetTimer(this, 0);
				break;

			case main_region_s2 :
				nextStateIndex = 0;
				stateVector[0] = State.$NullState$;

				timer.unsetTimer(this, 1);
				break;

			case main_region_s3 :
				nextStateIndex = 0;
				stateVector[0] = State.$NullState$;

				timer.unsetTimer(this, 2);
				break;

			case main_region__final_ :
				nextStateIndex = 0;
				stateVector[0] = State.$NullState$;
				break;

			default :
				break;
		}

		exitAction();
	}

	/**
	 * @see IStatemachine#isActive()
	 */
	@Override
	public boolean isActive() {

		return stateVector[0] != State.$NullState$;
	}

	/** 
	 * @see IStatemachine#isFinal() 
	 */
	@Override
	public boolean isFinal() {
		return (stateVector[0] == State.main_region__final_);
	}

	/**
	 * This method resets the incoming events (time events included).
	 */
	protected void clearEvents() {
		sCICounter.clearEvents();

		for (int i = 0; i < timeEvents.length; i++) {
			timeEvents[i] = false;
		}
	}

	/**
	 * This method resets the outgoing events.
	 */
	protected void clearOutEvents() {
	}

	/**
	 * Returns true if the given state is currently active otherwise false.
	 */
	public boolean isStateActive(State state) {
		switch (state) {
			case main_region_init :
				return stateVector[0] == State.main_region_init;
			case main_region_s1 :
				return stateVector[0] == State.main_region_s1;
			case main_region_s2 :
				return stateVector[0] == State.main_region_s2;
			case main_region_s3 :
				return stateVector[0] == State.main_region_s3;
			case main_region__final_ :
				return stateVector[0] == State.main_region__final_;
			default :
				return false;
		}
	}

	/**
	 * Set the {@link ITimer} for the state machine. It must be set
	 * externally on a timed state machine before a run cycle can be correct
	 * executed.
	 * 
	 * @param timer
	 */
	public void setTimer(ITimer timer) {
		this.timer = timer;
	}

	/**
	 * Returns the currently used timer.
	 * 
	 * @return {@link ITimer}
	 */
	public ITimer getTimer() {
		return timer;
	}

	public void timeElapsed(int eventID) {
		timeEvents[eventID] = true;
	}

	public SCICounter getSCICounter() {
		return sCICounter;
	}

	/* Entry action for statechart 'counter'. */
	private void entryAction() {
	}

	/* Exit action for state 'counter'. */
	private void exitAction() {
	}

	/* The reactions of state init. */
	private void react_main_region_init() {
		if (sCICounter.start) {
			nextStateIndex = 0;
			stateVector[0] = State.$NullState$;

			timer.setTimer(this, 0, 2 * 1000, false);

			sCICounter.state += 1;

			nextStateIndex = 0;
			stateVector[0] = State.main_region_s1;
		}
	}

	/* The reactions of state s1. */
	private void react_main_region_s1() {
		if ((timeEvents[0]) && sCICounter.state == 1) {
			nextStateIndex = 0;
			stateVector[0] = State.$NullState$;

			timer.unsetTimer(this, 0);

			timer.setTimer(this, 1, 2 * 1000, false);

			sCICounter.state += 1;

			nextStateIndex = 0;
			stateVector[0] = State.main_region_s2;
		} else {
			if (sCICounter.interrupt) {
				nextStateIndex = 0;
				stateVector[0] = State.$NullState$;

				timer.unsetTimer(this, 0);

				nextStateIndex = 0;
				stateVector[0] = State.main_region__final_;
			}
		}
	}

	/* The reactions of state s2. */
	private void react_main_region_s2() {
		if ((timeEvents[1]) && sCICounter.state == 2) {
			nextStateIndex = 0;
			stateVector[0] = State.$NullState$;

			timer.unsetTimer(this, 1);

			timer.setTimer(this, 2, 2 * 1000, false);

			sCICounter.state += 1;

			nextStateIndex = 0;
			stateVector[0] = State.main_region_s3;
		} else {
			if (sCICounter.interrupt) {
				nextStateIndex = 0;
				stateVector[0] = State.$NullState$;

				timer.unsetTimer(this, 1);

				nextStateIndex = 0;
				stateVector[0] = State.main_region__final_;
			}
		}
	}

	/* The reactions of state s3. */
	private void react_main_region_s3() {
		if ((timeEvents[2]) && sCICounter.state == 3) {
			nextStateIndex = 0;
			stateVector[0] = State.$NullState$;

			timer.unsetTimer(this, 2);

			sCICounter.raiseNew_cycle();

			timer.setTimer(this, 0, 2 * 1000, false);

			sCICounter.state += 1;

			nextStateIndex = 0;
			stateVector[0] = State.main_region_s1;
		} else {
			if (sCICounter.interrupt) {
				nextStateIndex = 0;
				stateVector[0] = State.$NullState$;

				timer.unsetTimer(this, 2);

				nextStateIndex = 0;
				stateVector[0] = State.main_region__final_;
			}
		}
	}

	/* The reactions of state null. */
	private void react_main_region__final_() {
	}

	public void runCycle() {

		clearOutEvents();

		for (nextStateIndex = 0; nextStateIndex < stateVector.length; nextStateIndex++) {

			switch (stateVector[nextStateIndex]) {
				case main_region_init :
					react_main_region_init();
					break;
				case main_region_s1 :
					react_main_region_s1();
					break;
				case main_region_s2 :
					react_main_region_s2();
					break;
				case main_region_s3 :
					react_main_region_s3();
					break;
				case main_region__final_ :
					react_main_region__final_();
					break;
				default :
					// $NullState$
			}
		}

		clearEvents();
	}
}
