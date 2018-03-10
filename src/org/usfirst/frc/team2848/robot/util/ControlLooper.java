//package org.usfirst.frc.team2848.robot.util;
//
//import java.util.ArrayList;
//import java.util.Timer;
//
//public class ControlLooper {
//	
//	public final long kPeriod_ns;
//
//    private boolean running_ = false;
//    
//    private ArrayList<ControlFunction> ControlFunctions = null;
//
//    private final Object taskRunningLock_ = new Object();
//    private double timestamp_ = 0;
//    
//    LoopRun updateScheduler = new LoopRun();
//
//    public ControlLooper(long period_ns) {
//    	kPeriod_ns = period_ns;
//    }
//
//    public synchronized void start() {
//        if (!running_) {
//            synchronized (taskRunningLock_) {
//                timestamp_ = System.nanoTime();
//                for (ControlFunction function : ControlFunctions) {
//                    function.init();
//                }
//                running_ = true;
//            }
//            updateScheduler.start();            
//        }
//    }
//    
//    class LoopRun extends Thread {
//    	public void run(){
//    		
//    		synchronized (taskRunningLock_) {
//	            timestamp_ = System.currentTimeMillis();
//	            System.out.println("Time: " + timestamp_);
//	            for (ControlFunction function : ControlFunctions) {
//	                function.update();
//	            }
//	        }
//    		long elapsedTime = (long) (System.currentTimeMillis() - timestamp_);
//            updateScheduler.schedule(new LoopRun(), kPeriod_ms - elapsedTime);
//    	}
//    }
//
//    public synchronized void stop() {
//        if (running_) {
//            System.out.println("Stopping loops");
//            synchronized (taskRunningLock_) {
//                timestamp_ = System.currentTimeMillis();
//                for (ControlFunction function : ControlFunctions) {
//                    function.stop();
//                }
//                running_ = false;
//            }
//            updateScheduler.cancel();
//        }
//    }
//}
