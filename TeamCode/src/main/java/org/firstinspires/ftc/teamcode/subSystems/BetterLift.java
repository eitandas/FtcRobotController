package org.firstinspires.ftc.teamcode.subSystems;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.firstinspires.ftc.teamcode.actions.SetPowerAction;
import org.firstinspires.ftc.teamcode.utils.PID;

public class BetterLift {

    private double power = 0;
    private DcMotorEx liftMotor = null;
    private final double levels[] = {0,1000, 2000, 3000};

    PID pid = new PID(1, 1, 1, 1);


    public BetterLift(OpMode opMode) {
        liftMotor = opMode.hardwareMap.get(DcMotorEx.class, "motor");
    }


    public Action setPower(double power) {
        this.power = power;
        return new SetPowerAction();
    }

    public Action setPowerWithPID(double setPoint) {
            power = pid.calculate(liftMotor.getCurrentPosition(), setPoint);
            return new SetPowerAction();
    }

        public Action setLevel(int level){
            power = pid.calculate(liftMotor.getCurrentPosition(), levels[level]);
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
