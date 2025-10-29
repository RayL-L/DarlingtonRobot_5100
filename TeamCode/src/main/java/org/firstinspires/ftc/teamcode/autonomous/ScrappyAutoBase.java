package org.firstinspires.ftc.teamcode.autonomous;

import android.util.Size;
import com.acmerobotics.roadrunner.Pose2d;
import com.arcrobotics.ftclib.command.CommandOpMode;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraName;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.interfaces.PropDetector;
import org.firstinspires.ftc.teamcode.util.PoseStorage;
import org.firstinspires.ftc.teamcode.ScrappyCore;
import org.firstinspires.ftc.teamcode.ScrappySettings;
import org.firstinspires.ftc.teamcode.vision.AprilTagLocalization;
import org.firstinspires.ftc.teamcode.vision.PropDetectionProcessor;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;







import java.util.ArrayList;
import java.util.List;


public abstract class ScrappyAutoBase extends CommandOpMode {

//    public enum Parking {
//        LEFT, MIDDLE, RIGHT
//    }

    protected final ScrappySettings.AllianceType m_allianceType;
    protected final ScrappySettings.AllianceSide m_allianceSide;
    protected final Pose2d m_startingPose;
    public ScrappyCore robot;
    //private static final boolean USE_WEBCAM = true;
    //public VisionPortal visionPortal;
    private static String[] LABELSBLUE = {
            "BlueTin"
    };
    private static String[] LABELSRED = {
            "REDtin"
    };
    private static String[] LABELS = {""};
    public static double[] XBLUE = {75.0, 295.0, 295.0, 596.0};
    public static double[] XRED = {75.0, 295.0, 295.0, 596.0};
    public static double[] xs = {};
    public static String DETECTION = "right";
    public static double AprilposaeY = 0.0;
    public static double AprilposaeX = 0.0;
    public static int ApriltagNUmBER = 1;
    public static int ApriltagNUmBERRED = 4;
    public static String TFLITE = "";
    public static String TFLITER = "REDtin.tflite";
    public static boolean isfar = false;
    public PropDetector.DetectionResult detectionResult = PropDetector.DetectionResult.LEFT;
    private AprilTagProcessor aprilTag;
    public PropDetectionProcessor detectionProcessor;
    public ScrappyAutoBase m_base;
    //public static WebcamName web1, web2;


    public ScrappyAutoBase(ScrappySettings.AllianceType allianceType, ScrappySettings.AllianceSide allianceSide, Pose2d startPose) {
        m_allianceType = allianceType;
        m_allianceSide = allianceSide;
        m_startingPose = startPose;

    }


    @Override
    public void initialize() {


        robot = new ScrappyCore(hardwareMap, m_allianceType, m_allianceSide, m_startingPose);

        //web1 = hardwareMap.get(WebcamName.class, "Webcam 1");
        //web2 = hardwareMap.get(WebcamName.class, "Webcam 2");

        //CameraName switchableCamera = ClassFactory.getInstance()
        //        .getCameraManager().nameForSwitchableCamera(web2, web1);

        detectionProcessor = new PropDetectionProcessor(m_allianceType, m_allianceSide);
        aprilTag = new AprilTagProcessor.Builder().build();

//        visionPortal = new VisionPortal.Builder()
//                //.setCamera(switchableCamera)
//                .setCameraResolution(new Size(640, 480))
//                .enableLiveView(true)
//                .setStreamFormat(VisionPortal.StreamFormat.YUY2)
//                .addProcessors(detectionProcessor, aprilTag)
//
//
//                 .build();
//
//
//
//        while (visionPortal.getCameraState() != VisionPortal.CameraState.STREAMING) {
//            telemetry.addData("waiting"," d");
//            telemetry.update();
//            sleep(10);
//
//        }
//        //visionPortal.setActiveCamera(web2);
//
//
//        visionPortal.setProcessorEnabled(aprilTag,true);

     //   visionPortal.setProcessorEnabled(detectionProcessor,true);


        telemetry.addData("Starting Trajectories"," d");
        telemetry.update();
        initAuto();

//        robot.bucket.initial();

        while (opModeInInit()) {
            detectionResult = detectionProcessor.getDetectionResult();
            telemetry.addData("Detected = ", detectionResult);
            //telemetry.addData("srvo = ", robot.m_lift.getM_Leftraise());
            telemetry.update();
            sleep(25);
        }

//        visionPortal.setProcessorEnabled(detectionProcessor,false);
//      //  visionPortal.setProcessorEnabled(tfodStack,isfar);
//
//        visionPortal.setProcessorEnabled(aprilTag,true);




        getUt();



    }

    @Override
    public void run() {
        super.run();
        //robot.m_drive.updatePoseEstimate();
        //PoseStorage.currentPose = robot.m_drive.pose;


    }
    public abstract void initAuto();

    public abstract void getUt();




//    public void doCameraSwitchingFOR1() {
//        if (visionPortal.getCameraState() == VisionPortal.CameraState.STREAMING) {
//            //visionPortal.setActiveCamera(web1);
//        }
//
//
//    }
//
//
//    public void doCameraSwitchingFOR2() {
//
//        if (visionPortal.getCameraState() == VisionPortal.CameraState.STREAMING) {
//            //visionPortal.setActiveCamera(web2);
//        }
//    }








    public void localizeWithRawAprilTag(boolean isBackCamera, AprilTagDetection detection) {
        //Pose2d robotPos = AprilTagLocalization.getRobotPositionFromTag(detection, robot.m_drive.pose.heading.toDouble(), isBackCamera);
        //this.robot.m_drive.pose = robotPos;
    }

//    public ArrayList<AprilTagDetection> getAprilTagDetections(boolean isBackCamera) {
//        if (!visionPortal.getProcessorEnabled(aprilTag)) {
//            visionPortal.setProcessorEnabled(aprilTag, true);
//        }
//
//        return aprilTag.getDetections();
//    }






// end method telemetryAprilTag()


}














