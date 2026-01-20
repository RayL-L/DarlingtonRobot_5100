package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.ScrappySettings;
import org.firstinspires.ftc.teamcode.commands.RunAction;

@Autonomous(name = "Auto", group = "Competition")
public class Auto extends ScrappyAutoBase {

    // toggles
    public static boolean blue = true;
    public static boolean close = true;

    public Action route;

    public Auto() {
        // placeholder pose; route uses computed startPose in initAuto()
        super(ScrappySettings.AllianceType.RED, ScrappySettings.AllianceSide.FAR, new Pose2d(0, 0, 0));
    }

    @Override
    public void initAuto() {

        // ===== START POSE =====
        Pose2d startPose;
        if (blue && close)          startPose = new Pose2d(-50, 50, Math.toRadians(-53));
        else if (blue)              startPose = new Pose2d( -11, -61, Math.toRadians(270));
        else if (close)             startPose = new Pose2d(50,  50, Math.toRadians(-127));
        else                        startPose = new Pose2d( 11,  -61, Math.toRadians(270));

        // ===== SHOOTING POSE =====
        Vector2d shootPos;
        double shootHeading;
        if (blue && close) {
            shootPos = new Vector2d(-23.5, 23.5);
            shootHeading = Math.toRadians(-45);
        } else if (blue) {
            shootPos = new Vector2d(-11, -50);
            shootHeading = Math.toRadians(-60);
        } else if (close) {
            shootPos = new Vector2d(23.5, 23.5);
            shootHeading = Math.toRadians(-135);
        } else {
            shootPos = new Vector2d(11, -50);
            shootHeading = Math.toRadians(-120);
        }

        // ===== BALL / GRAB params =====
        double ball_x = blue ? -23.5 : 23.5;
        double y21 = 11.5, y22 = -12.5, y23 = -36;
        double grab = blue ? -29 : 29;
        double ballDir = blue ? Math.toRadians(180) : Math.toRadians(0);
        double first_ball, second_ball;

        if(close){
            first_ball = y21; second_ball = y22;
        }else{
            first_ball = y23; second_ball = y22;
        }

        // ===== ROUTE =====
        robot.m_drive.pose = startPose;
        route = robot.m_drive.actionBuilder(startPose)
                //preload shooting
                .strafeToLinearHeading(shootPos, shootHeading)

                //first line of balls
                .strafeToLinearHeading(new Vector2d(ball_x, first_ball), ballDir)
                .strafeTo(new Vector2d(ball_x + grab, first_ball))
                .strafeToLinearHeading(shootPos, shootHeading)

                //second line of balls
                .strafeToLinearHeading(new Vector2d(ball_x, second_ball), ballDir)
                .strafeTo(new Vector2d(ball_x + grab, second_ball))
                .strafeToLinearHeading(shootPos, shootHeading)
                .build();
    }

    @Override
    public void getUt() {
        schedule(
                new SequentialCommandGroup(
                        new RunAction(route)
                )
        );
    }
}