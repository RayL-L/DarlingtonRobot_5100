package org.firstinspires.ftc.teamcode.tests;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;

public final class SplineTest extends LinearOpMode {
    @Override
    public void runOpMode() {
        Pose2d beginPose = new Pose2d(0, 0, 0);
        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);

        waitForStart();

        Actions.runBlocking(
            drive.actionBuilder(beginPose)


                    .strafeTo(new Vector2d(30, 30))
                    .waitSeconds(0.4)
                    .turnTo(Math.toRadians(180))
                    .waitSeconds(0.4)
                    .splineToSplineHeading(new Pose2d(0,0, Math.toRadians(90)), Math.PI/2, new TranslationalVelConstraint(80))
                        //                    .splineTo(new Vector2d(10, 30), Math.PI / 2)

                    /*
                    .splineTo(new Vector2d(30, 30), Math.PI / 2)
                    .splineTo(new Vector2d(0, 60), Math.PI)
                    .waitSeconds(1.16)
                    .splineTo(new Vector2d(30, 30), Math.PI / 2)
                    .splineTo(new Vector2d(0, 60), Math.PI)
                    .waitSeconds(1.16)
                    .splineTo(new Vector2d(30, 30), Math.PI / 2)
                    .splineTo(new Vector2d(0, 60), Math.PI)
                    .waitSeconds(1.16)
                    .splineTo(new Vector2d(30, 30), Math.PI / 2)
                    .splineTo(new Vector2d(0, 60), Math.PI)
                    .waitSeconds(1.16)

                     */
                    .build() );
    }
}
