package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Pose2d;
import com.arcrobotics.ftclib.command.Robot;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.commands.BulkCacheHandler;
import org.firstinspires.ftc.teamcode.subsystem.Shooters;
//import org.firstinspires.ftc.teamcode.subsystem.Sensor;

public class ScrappyCore extends Robot {
    public final ScrappySettings.AllianceType ALLIANCE_TYPE;
    public final ScrappySettings.AllianceSide ALLIANCE_SIDE;

    // Subsystems
    public MecanumDrive m_drive;
    public Shooters shootL;
    public  Shooters shootR;







    public ScrappyCore(HardwareMap hardwareMap, ScrappySettings.AllianceType allianceType, ScrappySettings.AllianceSide allianceSide, Pose2d startPose) {
        ALLIANCE_TYPE = allianceType;
        ALLIANCE_SIDE = allianceSide;

        // Schedule to clear cache continuously (manual mode)
        schedule(new BulkCacheHandler(hardwareMap));

        // Initialize subsystems
        m_drive = new MecanumDrive(hardwareMap, startPose);
        shootL = new Shooters(hardwareMap);
        shootR = new Shooters(hardwareMap);


    }
}