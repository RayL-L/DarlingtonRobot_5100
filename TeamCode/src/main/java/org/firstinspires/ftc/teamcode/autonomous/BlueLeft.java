package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.ScrappySettings;
import org.firstinspires.ftc.teamcode.commands.RunAction;
import org.firstinspires.ftc.teamcode.mech.AprilTagWebCam;
import org.firstinspires.ftc.teamcode.subsystem.Intake;


public class BlueLeft extends ScrappyAutoBase {


    public static final Pose2d startingPose = new Pose2d(-55, 55, Math.toRadians(315));
    public Action firstMove;
    public Action extraBall;

    private Intake intake;
    AprilTagWebCam aprilTagWebCam = new AprilTagWebCam();
    public BlueLeft() {
        super(ScrappySettings.AllianceType.RED, ScrappySettings.AllianceSide.FAR, startingPose);
    }
    @Override
    public void initAuto() {
        aprilTagWebCam.init(hardwareMap, telemetry);
        intake = new Intake(hardwareMap);
        firstMove = robot.m_drive.actionBuilder(startingPose)

                .strafeTo(new Vector2d(-26, 26))
                .stopAndAdd(() -> {
                    intake.shoot();
                    sleep(800);
                    intake.convey();
                    sleep(2000);
                    intake.stop();
                })

                .strafeToLinearHeading(new Vector2d(-5, 14), Math.toRadians(270))
                .waitSeconds(1)
                .build();
    }

    private void build21(){
        extraBall = robot.m_drive.actionBuilder(new Pose2d(-5,14,Math.toRadians(270)))
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

    private void build22(){
        extraBall = robot.m_drive.actionBuilder(new Pose2d(-5,14,Math.toRadians(270)))
                .strafeToLinearHeading(new Vector2d(-32, -12), Math.toRadians(180))

                .strafeTo(new Vector2d(-55, -12))
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

    private void build23(){
        extraBall = robot.m_drive.actionBuilder(new Pose2d(-5,14,Math.toRadians(270)))
                .strafeToLinearHeading(new Vector2d(-32, -24), Math.toRadians(180))

                .strafeTo(new Vector2d(-55, -24))
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
                        new RunAction(firstMove),
                        new InstantCommand(() -> {
                            int tagId = aprilTagWebCam.detectFirstIdWithTimeoutMs(1000);

                            telemetry.addData("Detected tag after firstMove", tagId);
                            telemetry.update();

                            if(tagId == 21){
                                build21();
                            }else if(tagId == 22){
                                build22();
                            }else if(tagId == 23){
                                build23();
                            }

//                            aprilTagWebCam.stop();
                            schedule(new RunAction(extraBall));
                        })


                )

        );
    }


}
