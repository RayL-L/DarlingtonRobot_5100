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
import org.firstinspires.ftc.teamcode.subsystem.Intake;
import org.firstinspires.ftc.teamcode.subsystem.Shooter;
import org.opencv.core.Mat;


public class TwooYaRed extends ScrappyAutoBase {


    public static final Pose2d startingPose = new Pose2d(-55, 55, Math.toRadians(315));
    public Action firstMove;

    private Intake intake;
    public TwooYaRed() {
        super(ScrappySettings.AllianceType.RED, ScrappySettings.AllianceSide.FAR, startingPose);
    }
    @Override
    public void initAuto() {
        intake = new Intake(hardwareMap);
        firstMove = robot.m_drive.actionBuilder(startingPose)

                .strafeTo(new Vector2d(-24, 24))
                .stopAndAdd(() -> {
                    intake.shoot();
                    sleep(800);
                    intake.convey();
                    sleep(2000);
                    intake.stop();
                })

                .strafeToLinearHeading(new Vector2d(-10, 14), Math.toRadians(270))
                .waitSeconds(2)

                .strafeToLinearHeading(new Vector2d(-32, 12), Math.toRadians(180))

                .strafeTo(new Vector2d(-55, 12))
                .afterTime(0.2, () -> {
                    intake.startIntake();
                })
                .afterTime(1.85, () -> {
                    intake.stopIntake();
                })
                .strafeToLinearHeading(new Vector2d(-24,24), Math.toRadians(315))
                .stopAndAdd(() -> {
                    intake.will();
                    sleep(400);
                    intake.willStop();
                    intake.shoot();
                    sleep(800);
                    intake.convey();
                    sleep(2000);
                    intake.stop();
                })
                .build();


    }



    @Override
    public void getUt() {
        schedule(

                new SequentialCommandGroup(
                        new RunAction(firstMove)


                )

        );
    }


}
