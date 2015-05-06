package org.yakindu.scr.counter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.yakindu.scr.ITimer;
import org.yakindu.scr.ITimerCallback;

/**
 * Runnable wrapper of CounterStatemachine. This wrapper provides a thread safe, runnable instance of the statemachine.
 * The wrapper implements the {@link Runnable} interface and can be started in a thread by the client code. 
 * The run method then starts the main event processing loop for this statemachine.
 * 
 * This feature is in beta state. Currently not supported are
 * - interface observer
 * - operation callbacks
 * 
 * Please report bugs and issues... 
 */

public class RunnableCounter implements ICounterStatemachine, Runnable {
	
	
	/** 
	 * The events are queued using a blocking queue without capacity restriction. This queue holds
	 * Runnable instances that process the events. 
	 */
	protected BlockingQueue<Runnable> eventQueue = new LinkedBlockingQueue<Runnable>();
	
	/**
	 * The core statemachine is simply wrapped and the event processing will be delegated to that statemachine instance.
	 * This instance will be created implicitly.
	 */
	protected CounterStatemachine statemachine = new CounterStatemachine();
	
	/**
	 * Interface object for SCICounter
	 */		
	protected SCICounter sCICounter = new SCICounter() {
		public void raiseStart() {
			eventQueue.add( new Runnable() {
				
				@Override
				public void run() {
					synchronized (statemachine) {
						statemachine.getSCICounter().raiseStart();
						statemachine.runCycle();
					}
				}
			});
		}
		
		public void raiseNew_cycle() {
			eventQueue.add( new Runnable() {
				
				@Override
				public void run() {
					synchronized (statemachine) {
						statemachine.getSCICounter().raiseNew_cycle();
						statemachine.runCycle();
					}
				}
			});
		}
		
		public void raiseInterrupt() {
			eventQueue.add( new Runnable() {
				
				@Override
				public void run() {
					synchronized (statemachine) {
						statemachine.getSCICounter().raiseInterrupt();
						statemachine.runCycle();
					}
				}
			});
		}
		
		public long getState() {
			synchronized(statemachine) {
				return statemachine.getSCICounter().getState();
			}
		}
		
		public void setState(final long value) {
			synchronized(statemachine) {
				statemachine.getSCICounter().setState(value);
			}
		}
		
	};
	
	
	public SCICounter getSCICounter() {
		return sCICounter;
	}
	/*========== TIME EVENT HANDLING ================
	
	/** An external timer instance is required. */
	protected ITimer externalTimer;
	
	/** Internally we use a timer proxy that queues time events together with other input events. */
	protected ITimer timerProxy = new ITimer() {
		/** Simply delegate to external timer with a modified callback. */
		@Override
		public void setTimer(ITimerCallback callback, int eventID, long time,
				boolean isPeriodic) {
			externalTimer.setTimer(RunnableCounter.this, eventID, time, isPeriodic);
		}
		
		@Override
		public void unsetTimer(ITimerCallback callback, int eventID) {
			externalTimer.unsetTimer(RunnableCounter.this, eventID);
		}
	};
	
	/**
	 * Set the {@link ITimer} for the state machine. It must be set
	 * externally on a timed state machine before a run cycle can be correct
	 * executed.
	 * 
	 * @param timer
	 */
	public void setTimer(ITimer timer) {
		synchronized(statemachine) {
			this.externalTimer = timer;
			/* the wrapped statemachine uses timer proxy as timer */
			statemachine.setTimer(timerProxy);
		}
	}
	
	/**
	* Returns the currently used timer.
	* 
	* @return {@link ITimer}
	*/
	public ITimer getTimer() {
		return externalTimer;
	}
	
	public void timeElapsed(int eventID) {
		eventQueue.add(new Runnable() {
	
			@Override
			public void run() {
				synchronized (statemachine) {
					statemachine.timeElapsed(eventID);
					statemachine.runCycle();
				}
			}
		});
	}
	
	
	/**
	 * init() will be delegated thread safe to the wrapped statemachine. 
	 */ 
	public void init() {
		synchronized(statemachine) {
			statemachine.init();
		}
	}
	
	/**
	 * enter() will be delegated thread safe to the wrapped statemachine.  
	 */ 
	public void enter() {
		synchronized(statemachine) {
			statemachine.enter();
		}
	}
	
	/**
	 * exit() will be delegated thread safe to the wrapped statemachine.  
	 */ 
	public void exit() {
		synchronized(statemachine) {
			statemachine.exit();
		}
	}
	
	/**
	 * isActive() will be delegated thread safe to the wrapped statemachine.  
	 */ 
	public boolean isActive() {
		synchronized(statemachine) {
			return statemachine.isActive();
		}
	}
	
	/**
	 * isFinal() will be delegated thread safe to the wrapped statemachine.  
	 */ 
	public boolean isFinal() {
		synchronized(statemachine) {
			return statemachine.isFinal();
		}
	}
	
	
	/**
	 * runCycle() will be delegated thread safe to the wrapped statemachine.  
	 */ 
	@Override
	public void runCycle() {
		synchronized (statemachine) {
			statemachine.runCycle();
		}
	}

	/**
	 * This method will start the main execution loop for the statemachine. 
	 * First it will init and enter the statemachine implicitly and then will start processing events 
	 * from the event queue until the thread is interrupted. 
	 */
	@Override
	public void run() {
	
		boolean terminate = false;
		
		while(!(terminate || Thread.currentThread().isInterrupted())) {
	
			try {
				
				Runnable eventProcessor = eventQueue.take();
				eventProcessor.run();
				
			} catch (InterruptedException e) {
				terminate = true;
			}
		}			
	}

	
}
