package org.firstinspires.ftc.teamcode.autonomous;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.ScrappySettings;
import org.firstinspires.ftc.teamcode.commands.RunAction;
import org.firstinspires.ftc.teamcode.subsystem.AprilTagWebCam;
import org.firstinspires.ftc.teamcode.subsystem.Shooters;

@Autonomous(name = "RedRight", group = "Competition")
public class RedRight extends ScrappyAutoBase{

    public static final Pose2d startingPose = new Pose2d(55, 55, Math.toRadians(225));
    public Action firstMove, extraBall;
    private Shooters shooters;
    AprilTagWebCam aprilTagWebCam = new AprilTagWebCam();
    public RedRight() {
        super(ScrappySettings.AllianceType.RED, ScrappySettings.AllianceSide.FAR, startingPose);
    }
    @Override
    public void initAuto() {
        aprilTagWebCam.init(hardwareMap, telemetry);
        shooters = new Shooters(hardwareMap);
        firstMove = robot.m_drive.actionBuilder(startingPose)

                .strafeTo(new Vector2d(26, 26))
                .stopAndAdd(() -> {
                    shooters.shoot();
                    sleep(800);
                    shooters.convey();
                    sleep(2000);
                    shooters.stop();
                })

                .strafeToLinearHeading(new Vector2d(-5, 14), Math.toRadians(270))
                .waitSeconds(1)
                .build();
    }

    private void build21(){
        extraBall = robot.m_drive.actionBuilder(new Pose2d(-5,14,Math.toRadians(270)))
                .strafeToLinearHeading(new Vector2d(32, 12), Math.toRadians(0))

                .strafeTo(new Vector2d(55, 12))
                .afterTime(0.2, () -> {
                    shooters.startIntake();
                })
                .afterTime(1.85, () -> {
                    shooters.stopIntake();
                })
                .strafeToLinearHeading(new Vector2d(24,24), Math.toRadians(225))
                .stopAndAdd(() -> {
                    shooters.will();
                    sleep(400);
                    shooters.willStop();
                    shooters.shoot();
                    sleep(800);
                    shooters.convey();
                    sleep(2000);
                    shooters.stop();
                })

                //pick another one 22

                .strafeToLinearHeading(new Vector2d(32, -12), Math.toRadians(0))

                .strafeTo(new Vector2d(55, -12))
                .afterTime(0.2, () -> {
                    shooters.startIntake();
                })
                .afterTime(1.85, () -> {
                    shooters.stopIntake();
                })

                .build();
    }

    private void build22(){
        extraBall = robot.m_drive.actionBuilder(new Pose2d(-5,14,Math.toRadians(0)))
                .strafeToLinearHeading(new Vector2d(32, -12), Math.toRadians(0))

                .strafeTo(new Vector2d(55, -12))
                .afterTime(0.2, () -> {
                    shooters.startIntake();
                })
                .afterTime(1.85, () -> {
                    shooters.stopIntake();
                })
                .strafeToLinearHeading(new Vector2d(24,24), Math.toRadians(225))
                .stopAndAdd(() -> {
                    shooters.will();
                    sleep(400);
                    shooters.willStop();
                    shooters.shoot();
                    sleep(800);
                    shooters.convey();
                    sleep(2000);
                    shooters.stop();
                })

                //pick another one 21

                .strafeToLinearHeading(new Vector2d(-32, 12), Math.toRadians(180)) //balls' position

                .strafeTo(new Vector2d(-55, 12))
                .afterTime(0.2, () -> {
                    shooters.startIntake();
                })
                .afterTime(1.85, () -> {
                    shooters.stopIntake();
                }) //picking balls


                .build();
    }

    private void build23(){
        extraBall = robot.m_drive.actionBuilder(new Pose2d(-5,14,Math.toRadians(270)))
                .strafeToLinearHeading(new Vector2d(32, -24), Math.toRadians(0))

                .strafeTo(new Vector2d(55, -24))
                .afterTime(0.2, () -> {
                    shooters.startIntake();
                })
                .afterTime(1.85, () -> {
                    shooters.stopIntake();
                })
                .strafeToLinearHeading(new Vector2d(24,24), Math.toRadians(225))
                .stopAndAdd(() -> {
                    shooters.will();
                    sleep(400);
                    shooters.willStop();
                    shooters.shoot();
                    sleep(800);
                    shooters.convey();
                    sleep(2000);
                    shooters.stop();
                })

                //pick another one 22

                .strafeToLinearHeading(new Vector2d(32, -12), Math.toRadians(0))

                .strafeTo(new Vector2d(55, -12))
                .afterTime(0.2, () -> {
                    shooters.startIntake();
                })
                .afterTime(1.85, () -> {
                    shooters.stopIntake();
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