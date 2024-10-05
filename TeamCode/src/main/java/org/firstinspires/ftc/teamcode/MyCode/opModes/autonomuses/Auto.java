package org.firstinspires.ftc.teamcode.MyCode.opModes.autonomuses;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MyCode.subSystems.BetterLift;
import org.firstinspires.ftc.teamcode.RoadRunner.MecanumDrive;

@Config
@Autonomous(name = "Autonomous")
public class Auto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Action trajectoryAction1;
        Action trajectoryAction2;
        BetterLift lift = new BetterLift(this,false);
        MecanumDrive mecanumDrive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, Math.toRadians(0)));
        trajectoryAction1 = mecanumDrive.actionBuilder(mecanumDrive.pose)
                .strafeTo(new Vector2d(100, 200))
                .waitSeconds(1)
                .strafeTo(new Vector2d(200, 300))
                .build();
        trajectoryAction2 = mecanumDrive.actionBuilder(mecanumDrive.pose)
                .strafeTo(new Vector2d(400, 500))
                .waitSeconds(1)
                .strafeTo(new Vector2d(100, 200))
                .build();


        Actions.runBlocking(new SequentialAction(
                        trajectoryAction1
                )
        );
    }

}

