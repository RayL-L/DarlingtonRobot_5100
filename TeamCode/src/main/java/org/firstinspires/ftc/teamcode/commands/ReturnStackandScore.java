package org.firstinspires.ftc.teamcode.commands;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.WaitUntilCommand;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.autonomous.ScrappyAutoBase;
import org.firstinspires.ftc.vision.VisionPortal;


public class ReturnStackandScore extends SequentialCommandGroup {
    public ReturnStackandScore(ScrappyAutoBase base, Action undertrussewithscore) {
        addCommands(



                //_________REPEAT__________________________

                new WaitCommand(120),

                new AprilTagPoseRED(base, 8, false, 15, new PoseVelocity2d(new Vector2d(15, 0), 0)),


                new WaitCommand(120),



                new WaitCommand(400),



                new WaitCommand(400),




                new RunAction(undertrussewithscore),


                new WaitCommand(100),
                new InstantCommand( base.robot.m_dropper::drop ),
                new WaitCommand(300)


        );
    }
}
