package org.firstinspires.ftc.teamcode.autonomuses;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.acmerobotics.dashboard.config.Config;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.subSystems.BetterLift;

@Config
@Autonomous(name = "autonomus")
public class Autonomus extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Action trajectoryAction1;
        Action trajectoryAction2;
        BetterLift lift = new BetterLift(this);
        MecanumDrive mecanumDrive = new MecanumDrive(hardwareMap, new Pose2d(11.8, 61.7, Math.toRadians(0)));
        trajectoryAction1 = mecanumDrive.actionBuilder(mecanumDrive.pose)
                .strafeTo(new Vector2d(100,200))
                .waitSeconds(1)
                .strafeTo(new Vector2d(200,300))
                .build();
        trajectoryAction2 = mecanumDrive.actionBuilder(mecanumDrive.pose)
                .strafeTo(new Vector2d(400,500))
                .waitSeconds(1)
                .strafeTo(new Vector2d(100,200))
                .build();




        if (opModeIsActive()&&!isStopRequested()){

            Actions.runBlocking(new SequentialAction(
                    trajectoryAction1,
                    lift.setLevel(1),
                    lift.setLevel(0),
                    trajectoryAction2,
                    lift.setLevel(2)
                    )
            );
        }

        }
    }

