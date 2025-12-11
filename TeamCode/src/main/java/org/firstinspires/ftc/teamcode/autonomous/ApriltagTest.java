package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.mech.AprilTagWebCam;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;


@Autonomous
public class ApriltagTest extends OpMode {

    AprilTagWebCam aprilTagWebCam = new AprilTagWebCam();
    @Override
    public void init(){

        aprilTagWebCam.init(hardwareMap, telemetry);
    }

    @Override
    public void loop(){
        aprilTagWebCam.update();
        AprilTagDetection id22 = aprilTagWebCam.getTagBySpecificId(22);
        aprilTagWebCam.displayDetectionTelemetry(id22);
    }
}
