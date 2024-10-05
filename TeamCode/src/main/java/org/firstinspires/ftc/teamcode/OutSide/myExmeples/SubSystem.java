package org.firstinspires.ftc.teamcode.OutSide.myExmeples;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.checkerframework.checker.nullness.qual.NonNull;

@Disabled
public class SubSystem {

    private double power = 0;
    private DcMotorEx liftMotor = null;


    public SubSystem(OpMode opMode) {
        liftMotor = opMode.hardwareMap.get(DcMotorEx.class, "motor");
    }
    public Action setPower(double power) {
        return new SetPowerAction();
    }
    public class SetPowerAction implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            liftMotor.setPower(power);
            return power>0;
        }
    }
}

