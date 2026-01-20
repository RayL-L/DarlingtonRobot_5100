package org.firstinspires.ftc.teamcode.meepmeep;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;

import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.awt.Image;
import java.io.InputStream;
import java.util.Objects;

import javax.imageio.ImageIO;


public class MeepMeepTest {

    public static boolean blue = false;
    public static boolean close = false;



    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity bot = new DefaultBotBuilder(meepMeep)
                .setConstraints(
                        50,     // maxVel
                        50,            // maxAccel
                        Math.PI,       // maxAngVel
                        Math.PI,       // maxAngAccel
                        13.73          // trackWidth
                )
                .build();

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

        // ===== BALLS and SHOOTING =====

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

        bot.runAction(
                bot.getDrive().actionBuilder(startPose)
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
                        .build()
        );


        Image bg;
        try (InputStream is = Objects.requireNonNull(
                MeepMeepTest.class.getResourceAsStream("/decode_field.png"),
                "Image NOT found. Put it in MeepMeep/src/main/resources and rebuild."
        )) {
            bg = ImageIO.read(is);
            if (bg == null) {
                throw new RuntimeException("ImageIO could not decode the PNG. Try re-saving it as a standard PNG.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        meepMeep.setBackground(bg)
                .setBackgroundAlpha(0.95f)
                .setDarkMode(true)
                .addEntity(bot)
                .start();
    }
}




