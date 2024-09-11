package org.firstinspires.ftc.teamcode.subSystems;

import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.actions.SetPowerAction;

@Disabled
public class Lift {
    private double power = 0;
    private DcMotorEx liftMotor = null;
    SetPowerAction setPowerAction = new SetPowerAction();


    public Lift(OpMode opMode) {
        liftMotor = opMode.hardwareMap.get(DcMotorEx.class, "motor");
        setPowerAction.setMotor(liftMotor);
    }

    public Action setPower(double power) {
        this.power = power;
        return setPowerAction;
    }
}


