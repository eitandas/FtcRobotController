package org.firstinspires.ftc.teamcode.utils;

import com.qualcomm.robotcore.util.ElapsedTime;

public class PID
{

    private double kP;
    private double kI;
    private double kD;
    private double kF;
    private double p = 0;
    private double i = 0;
    private double d = 0;
    private double f = 0;
    private double setPoint = 0;
    private double position = 0;
    private double integralSum = 0;
    private double lastError = 0;
    private double tolerance = 0;
    private double error = 0;
    private double out = 0;
    private boolean setPointReached = false;
    ElapsedTime timer = new ElapsedTime();


    public PID(double kP, double kI, double kD, double kF, double tolerance) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.kF = kF;
        this.tolerance = tolerance;
    }
    public PID(double kP, double kI, double kD, double kF) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.kF = kF;
    }


    public double calculate(double postion ,double setPoint){
        this.position= postion;
        this.setPoint = setPoint;
        timer.startTime();
        error = setPoint - postion;

        p = error*kP;
        i = (integralSum + (error * timer.seconds()))*kI;
        d = ((error - lastError) / timer.seconds())*kD;

        out = p + i + d;

        integralSum = integralSum+postion;
        lastError = postion;
        timer.reset();
        return out;
    }

    public boolean isReached(){
        return Math.abs(error)<tolerance;
    }
}
