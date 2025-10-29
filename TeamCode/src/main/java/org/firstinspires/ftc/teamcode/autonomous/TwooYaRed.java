package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.ScrappySettings;
import org.firstinspires.ftc.teamcode.commands.AprilTagPoseRED;
import org.firstinspires.ftc.teamcode.commands.RunAction;
import org.opencv.core.Mat;


public class TwooYaRed extends ScrappyAutoBase {


    public static final Pose2d startingPose = new Pose2d(30, 52, Math.toRadians(180));
    public Action firstMove;




    public TwooYaRed() {
        super(ScrappySettings.AllianceType.RED, ScrappySettings.AllianceSide.FAR, startingPose);

    }



    @Override
    public void initAuto() {
        firstMove = robot.m_drive.actionBuilder(startingPose)
//                .stopAndAdd(() -> {
//                    robot.tilt.tiltDown();
//                    sleep(300);
//                    robot.bucket.initial();
//                    sleep(300); // strafeto splineto
//                })
                .strafeTo(new Vector2d(30,40))
                .strafeToLinearHeading(new Vector2d(54,52), Math.toRadians(225))
                .waitSeconds(0.5)
                .build();
//                .stopAndAdd(() -> {
//                            robot.lift.liftRaise();
//                            sleep(1800);
//                            robot.bucket.pourBucket();
//                            sleep(950);
//                            robot.lift.liftDown();
//                            robot.bucket.downBucket();
//                        }
//                )


    }



    @Override
    public void getUt() {
        schedule(

                new SequentialCommandGroup(
                        new RunAction(firstMove)
//                        new RunAction(firstMove).alongWith(
////                                new SequentialCommandGroup(
////                                        new InstantCommand(() ->{
////                                            robot.lift.liftRaise();
////                                        }),
////                                        new WaitCommand(2500),
////                                        new InstantCommand(() ->{
////                                            robot.bucket.pourBucket();
////                                        }),
////                                        new WaitCommand(1100),
////                                        new InstantCommand(() ->{
////                                            robot.lift.liftDown();
////                                            robot.bucket.downBucket();
////                                        })
////                                )
//                        )







                )

        );
    }


}
