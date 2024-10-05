package org.firstinspires.ftc.teamcode.MyCode.opModes.teleOps;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.MyCode.subSystems.Arm;
import org.firstinspires.ftc.teamcode.MyCode.subSystems.BetterLift;
import org.firstinspires.ftc.teamcode.MyCode.subSystems.Intake;
import org.firstinspires.ftc.teamcode.MyCode.utils.Gamepads.EasyGamepad;
import org.firstinspires.ftc.teamcode.RoadRunner.MecanumDrive;

import java.util.ArrayList;
import java.util.List;

@TeleOp(name = "teleOp")
public class teleOp extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(new Vector2d(0, 0), 0));
        BetterLift lift = new BetterLift(this,false);
        Arm arm = new Arm(this,false);
        Intake intake = new Intake(hardwareMap);
        EasyGamepad easyGamepad1 = new EasyGamepad(gamepad1);
        EasyGamepad easyGamepad2 = new EasyGamepad(gamepad2);
        List<Action> runningActions = new ArrayList<>();
        FtcDashboard dash = FtcDashboard.getInstance();
        Action trajectoryAction1;
        Action trajectoryAction2;
        Action trajectoryAction3;
        Action trajectoryActionCloseOut;

        waitForStart();
        while (opModeIsActive()) {
            easyGamepad1.update(gamepad1);
            easyGamepad2.update(gamepad2);
            TelemetryPacket packet = new TelemetryPacket();

            // updated based on gamepads
            drive.fieldDrive(new Pose2d(easyGamepad1.getLeftStickXPower(), easyGamepad1.getLeftStickYPower(),easyGamepad1.getRightStickXPower()));
            if (easyGamepad1.getRightBumper()) {
                runningActions.add(intake.intake(easyGamepad1.getRightBumper()));
            }
            if (easyGamepad2.getDPadLeft()){
                runningActions.add(arm.setAngle(3));
            }
            if (easyGamepad2.getDPadUp()){
                runningActions.add(arm.setAngle(2));
            }
            if (easyGamepad2.getDPadRight()){
                runningActions.add(arm.setAngle(1));
            }
            if (easyGamepad2.getDPadDown()){
                runningActions.add(arm.setAngle(0));
            }

            // update running actions
            List<Action> newActions = new ArrayList<>();
            for (Action action : runningActions) {
                action.preview(packet.fieldOverlay());
                if (action.run(packet)) {
                    newActions.add(action);
                }
            }
            runningActions = newActions;
            dash.sendTelemetryPacket(packet);
        }
    }
}
