package org.firstinspires.ftc.teamcode.teleOps;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Trajectory;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.subSystems.Lift;
@TeleOp(name = "teleOp")
public class teleOp extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Lift lift = new Lift(this);
        IMU imu;
        Action trajectoryAction1;
        Action trajectoryAction2;
        Action trajectoryAction3;
        Action trajectoryActionCloseOut;


        waitForStart();
        while (opModeIsActive()&& !isStopRequested()){
            Actions.runBlocking(new ParallelAction(
                    lift.setPower(gamepad2.left_stick_y)
                    ));
        }
    }
}
