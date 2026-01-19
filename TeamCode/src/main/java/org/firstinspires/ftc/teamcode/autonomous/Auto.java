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
    public static boolean blue = false;
    public static boolean close = false;

    public Action route;

    public Auto() {
        // placeholder pose; route uses computed startPose in initAuto()
        super(ScrappySettings.AllianceType.RED, ScrappySettings.AllianceSide.FAR, new Pose2d(0, 0, 0));
    }

    @Override
    public void initAuto() {

        // ===== START POSE =====
        Pose2d startPose;
        if (blue && close)          startPose = new Pose2d(-50, -50, Math.toRadians(53));
        else if (blue)              startPose = new Pose2d(61, -11, Math.toRadians(0));
        else if (close)             startPose = new Pose2d(-50, 50, Math.toRadians(-53));
        else                        startPose = new Pose2d(61, 11, Math.toRadians(0));

        // ===== SHOOTING POSE =====
        Vector2d shootPos;
        double shootHeading;
        if (blue && close) {
            shootPos = new Vector2d(-23.5, -23.5);
            shootHeading = Math.toRadians(45);
        } else if (blue) {
            shootPos = new Vector2d(50, -11);
            shootHeading = Math.toRadians(32);
        } else if (close) {
            shootPos = new Vector2d(-23.5, 23.5);
            shootHeading = Math.toRadians(-45);
        } else {
            shootPos = new Vector2d(50, 11);
            shootHeading = Math.toRadians(-32);
        }

        // ===== BALL / GRAB params =====
        double ball_y = blue ? -23.5 : 23.5;
        double x21 = -11.5, x22 = 12, x23 = 36;
        double grab = blue ? -29 : 29;
        double ballDir = blue ? Math.toRadians(-90) : Math.toRadians(90);

        double first_ball, second_ball;

        if(close){
            first_ball = x21; second_ball = x22;
        }else{
            first_ball = x23; second_ball = x22;
        }

        // ===== ROUTE =====
        route = robot.m_drive.actionBuilder(startPose)
                //preload shooting
                .strafeToLinearHeading(shootPos, shootHeading)

                //first line of balls
                .strafeToLinearHeading(new Vector2d(first_ball, ball_y), ballDir)
                .strafeTo(new Vector2d(first_ball, ball_y + grab))
                .strafeToLinearHeading(shootPos, shootHeading)

                //second line of balls
                .strafeToLinearHeading(new Vector2d(second_ball, ball_y), ballDir)
                .strafeTo(new Vector2d(second_ball, ball_y + grab))
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