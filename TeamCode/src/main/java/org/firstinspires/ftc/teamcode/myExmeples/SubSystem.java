package org.firstinspires.ftc.teamcode.myExmeples;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.firstinspires.ftc.teamcode.actions.SetPowerAction;

@Disabled
public class SubSystem {
    private double power = 0;
    private DcMotorEx motor = null;
    SetPowerAction setPowerAction = new SetPowerAction();


    public SubSystem(OpMode opMode) {
        motor = opMode.hardwareMap.get(DcMotorEx.class, "motor");
        setPowerAction.setMotor(motor);
    }

    public Action setPower(double power) {
        this.power = power;
        return setPowerAction;
    }

}

//    public class SetPower implements Action {
//        @Override
//        public boolean run(@NonNull TelemetryPacket packet) {
//            motor.setPower(power);
//            return power>0;
//        }
//}
//    public Action setPowerNum(double power){
//        this.power = power;
//        return new SetPower();
//    }

