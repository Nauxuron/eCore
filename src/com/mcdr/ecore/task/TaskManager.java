package com.mcdr.ecore.task;

import com.mcdr.ecore.eCore;

public abstract class TaskManager {
	public static Integer drawFlameTaskId = null;
	public static void start() {
		startFlameEffect();
	}
	
	
	private static int scheduleSyncRepeatingTask(BaseTask baseTask, double period) {
		if (period > 0) {
			long periodInTicks = (long) (period * 20);
			return eCore.scheduler.scheduleSyncRepeatingTask(eCore.instance, baseTask, periodInTicks, periodInTicks);
		}
		else return -1;
	}
	
	public static void stop() {
		eCore.scheduler.cancelTasks(eCore.instance);
	}
	
	public static void startFlameEffect(){
		if(drawFlameTaskId == null || drawFlameTaskId == -1){
			drawFlameTaskId = scheduleSyncRepeatingTask(new DrawFlameEffect(), 0.8);
		}
	}
	
	public static void stopFlameEffect(){
		if(drawFlameTaskId != null){
			eCore.scheduler.cancelTask(drawFlameTaskId);
			drawFlameTaskId = null;
		}
	}
	
	public static void restart() {
		stop();
		start();
	}
}

abstract class BaseTask implements Runnable {}