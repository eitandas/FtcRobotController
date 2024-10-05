package org.firstinspires.ftc.teamcode.MyCode.subSystems;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.MyCode.utils.PID.PIDFController;

public class BetterLift {

    DcMotorEx liftMotor = null;
    Telemetry telemetry;
    private boolean finishedHanging = false;
    private double power = 0;
    private boolean isDebug = false;
    private final double AMP_LIMIT = 0;


    public BetterLift(OpMode opMode, boolean isDebug) {
        telemetry = opMode.telemetry;
        this.isDebug = isDebug;
        liftMotor = opMode.hardwareMap.get(DcMotorEx.class, "liftMotor");
        if (isDebug) {
            opMode.telemetry.addData("LiftConstructor", true);
        }
    }

    public void init() {
        liftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        resetEncoders();
        if (isDebug) {
            telemetry.addData("LiftInit", true);
        }
    }

    public Action hanging() {
        if (liftMotor.getCurrent(CurrentUnit.AMPS.AMPS) <= AMP_LIMIT && power < 0) {
            resetEncoders();
            if (isDebug) {
                telemetry.addData("LiftEncoder", liftMotor.getCurrentPosition());
            }
        }
        return new setPowerAction();

    }

    public void resetEncoders() {
        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public DcMotorEx getLiftMotor() {
        return liftMotor;
    }

    public void setLiftMotor(DcMotorEx liftMotor) {
        this.liftMotor = liftMotor;
    }

    public boolean isFinishedHanging() {
        return finishedHanging;
    }

    public void setFinishedHangingNot(boolean finishedHanging) {
        this.finishedHanging = finishedHanging;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public Telemetry getTelemetry() {
        return telemetry;
    }

    public void setTelemetry(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    public class setPowerAction implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            while (liftMotor.getCurrentPosition() >= 0 && !finishedHanging) {
                liftMotor.setPower(1);
                if (isDebug) {
                    telemetryPacket.put("liftUp", true);
                    telemetryPacket.put("power", liftMotor.getPower());
                    telemetry.addData("liftUp", true);
                    telemetry.addData("Power",liftMotor.getPower());
                }
            }
            while (liftMotor.getCurrentPosition() <= 0 && !finishedHanging) {
                liftMotor.setPower(-1);
                if (isDebug) {
                    telemetry.addData("firstAscend", true);
                }
            }
            while (liftMotor.getCurrentPosition() <= 0 && !finishedHanging) {
                liftMotor.setPower(1);
                if (isDebug) {
                    telemetryPacket.put("liftUpSecondTime", true);
                    telemetry.addData("liftUpSecondTime", true);
                }
            }
            if (isDebug) {
                telemetryPacket.put("secondAscend", true);
                telemetry.addData("secondAscend", true);
            }
            if (!finishedHanging) {
                liftMotor.setPower(-1);
            }
            finishedHanging = true;

            return finishedHanging;

        }
    }
}