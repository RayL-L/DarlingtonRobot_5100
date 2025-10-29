package org.firstinspires.ftc.teamcode;

import static com.qualcomm.hardware.rev.RevHubOrientationOnRobot.xyzOrientation;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.PIDCoefficients;

@Config
public class ScrappySettings {
    public enum AllianceType {
        BLUE, RED
    }
    public enum AllianceSide {
        CLOSE, FAR
    }

    public static boolean IS_COMPETITION = true;
    public static double FRONT_CAMERA_OFFSET_X = 0.0;
    public static double FRONT_CAMERA_OFFSET_Y = 5.5;
    public static double BACK_CAMERA_OFFSET_X = -4;
    public static double BACK_CAMERA_OFFSET_Y = 6.95;
    public static PIDCoefficients SLIDE_PID = new PIDCoefficients(0, 0 , 0);
    public static double SLIDE_KV = 0;
    public static double SLIDE_KA = 0;
    public static double SLIDE_KG = 0;
                                                                                                //mess around with the Z to see if it helps straighten angle
    public static RevHubOrientationOnRobot CONTROL_HUB_ORIENTATION = new RevHubOrientationOnRobot(xyzOrientation(180, 90, -21.9095));
}
