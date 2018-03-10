package org.usfirst.frc.team2848.robot.util;

public class AveragingFilter {
	
	int size;
	double[] buffer;
	
	public AveragingFilter(int size){
		this.size = size;
		
		buffer = new double[this.size];
	}
	
	public double addValueGetAverage(double newValue) {
		buffer[this.size - 1] = newValue;
		double bufferSum = newValue;
		
		for(int i = 0; i < this.size - 1; i++){
			bufferSum += buffer[i];
			buffer[i] = buffer[i+1];
		}
		
		return bufferSum/size;
	}

}
