package com.meepmeep;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;









public class MeepMeepTesting{
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                .setConstraints(50, 50, Math.PI, Math.PI, 12.9)
                .build();






        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(23, 48, Math.toRadians(180)))

                .strafeToLinearHeading(new Vector2d(47,47), Math.toRadians(225))
                        .waitSeconds(0.3)
             //   .splineToSplineHeading(new Pose2d(42,43,Math.toRadians(270)), 3*Math.PI/2)
                                .splineToConstantHeading(new Vector2d(42,33.5), 3*Math.PI/2)

                      //  .splineTo(new Vector2d(42,33.5), Math.toRadians(270))
                      //  .splineToLinearHeading(new Pose2d( 42,33.5, Math.toRadians(270)), Math.PI)



          //      .strafeToLinearHeading(new Vector2d(42,33.5), Math.toRadians(270))

                .build());

























//        meepMeep.setBackground(MeepMeep.Background.FIELD_DECODE_JUICE_DARK)
//                .setDarkMode(true)
//                .setBackgroundAlpha(0.95f)
//                .addEntity(myBot)
//                .start();
    }
}