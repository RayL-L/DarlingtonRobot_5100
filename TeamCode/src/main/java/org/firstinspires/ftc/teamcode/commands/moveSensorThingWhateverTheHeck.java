package org.firstinspires.ftc.teamcode.commands;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.autonomous.ScrappyAutoBase;
import org.firstinspires.ftc.teamcode.vision.AprilTagLocalization;


public class moveSensorThingWhateverTheHeck extends CommandBase {
    private final ScrappyAutoBase m_base;

    private final ElapsedTime timer = new ElapsedTime();
    private final double m_disAway;
    private Command m_runOnDetected = null;
    private PoseVelocity2d moffset;
    private Action m_action = null;
    private boolean m_finished = false;
    public moveSensorThingWhateverTheHeck(ScrappyAutoBase base, double disAway) {
        m_base = base;

        m_disAway = disAway;

    }

    @Override
    public void initialize() {
        timer.reset();
        m_base.robot.m_drive.setDrivePowers(new PoseVelocity2d(new Vector2d(-0.3, 0), 0));


    }

    @Override
    public void execute() {
       if(m_base.robot.msensor.getDistence()  < 20 || timer.seconds() > 7) {
           m_base.robot.m_drive.setDrivePowers(new PoseVelocity2d(new Vector2d(0, 0), 0));
       }




    }

    @Override
    public boolean isFinished() {
        return m_finished;
    }
}
