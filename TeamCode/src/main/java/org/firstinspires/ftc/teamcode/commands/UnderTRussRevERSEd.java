package org.firstinspires.ftc.teamcode.commands;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.autonomous.ScrappyAutoBase;
import org.firstinspires.ftc.teamcode.vision.AprilTagLocalization;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.List;


public class UnderTRussRevERSEd extends CommandBase {
    private final ScrappyAutoBase m_base;

    private final double m_disAway;
    private Command m_runOnDetected = null;
    private PoseVelocity2d moffset;
    private Action m_action = null;
    private boolean m_finished = false;
    public UnderTRussRevERSEd(ScrappyAutoBase base ,double disAway) {
        m_base = base;
        m_disAway = disAway;
    }

    @Override
    public void initialize() {
//        avgX /= 3;
//        avgY /= 3;

        m_base.robot.m_drive.pose = new Pose2d(58.8, m_disAway, 180);




        m_action = m_base.robot.m_drive.actionBuilder(m_base.robot.m_drive.pose)



                .lineToXConstantHeading(45).afterDisp(2, new InstantAction(() -> {
                    m_base.robot.m_lift.toInitial();
                }))




                .splineToConstantHeading(new Vector2d(15,-56.0), Math.PI).afterDisp(3, new InstantAction(() -> {

                    m_base.robot.m_lift.SetRaiseLPosition(0.18);
                    m_base.robot.m_lift.SetRaiseRPosition(0.18);


                    m_base.robot.m_dropper.back();

                        })
                )


                //give the slide time to reset


                .splineToLinearHeading(new Pose2d(-31, -55.9, Math.toRadians(180)),3.141592653589793238462643383279502884197)






                .build();

    }

    @Override
    public void execute() {
        if (m_action != null) {
            TelemetryPacket packet = new TelemetryPacket();
            m_action.preview(packet.fieldOverlay());
            m_finished = !m_action.run(packet);
            FtcDashboard.getInstance().sendTelemetryPacket(packet);
        }
    }

    @Override
    public boolean isFinished() {
        return m_finished;
    }
}
