package org.firstinspires.ftc.teamcode.MyCode.subSystems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    CRServo intake = null;
    private double power = 0;
    private boolean isButtonPressed= false;

    public Intake(HardwareMap hardwareMap) {
        intake = hardwareMap.get(CRServo.class, "intake");
    }

    public Action intake(boolean isButtonPressed) {
        this.isButtonPressed = isButtonPressed;
        power = 1;
        return new SetPowerAction();
    }

    public Action outtake(boolean isButtonPressed) {
        this.isButtonPressed = isButtonPressed;
        power = -1;
        return new SetPowerAction();
    }

    public class SetPowerAction implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            intake.setPower(power);
            return isButtonPressed;
        }
    }
}
