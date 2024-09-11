package org.firstinspires.ftc.teamcode.autonomuses;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

// RR-specific imports
import com.acmerobotics.dashboard.config.Config;

// Non-RR imports

import org.firstinspires.ftc.teamcode.MecanumDrive;

@Config
@Autonomous(name = "auto")
public class Autonomus extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        TrajectoryActionBuilder trajectoryAction2;
        Action trajectoryActionChosen;
        MecanumDrive mecanumDrive = new MecanumDrive(hardwareMap, new Pose2d(11.8, 61.7, Math.toRadians(90)));
        trajectoryAction2 = mecanumDrive.actionBuilder(mecanumDrive.pose)
                .strafeTo(new Vector2d(100,200))
                .waitSeconds(1)
                .strafeTo(new Vector2d(200,300));
        if (opModeIsActive()&&!isStopRequested()){

            Actions.runBlocking(new SequentialAction(
                    trajectoryAction2.build()
                    )

            );
        }







        }
    }

