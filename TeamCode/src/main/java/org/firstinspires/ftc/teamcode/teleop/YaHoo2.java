package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Rotation2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitUntilCommand;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.commands.RunAction;
import org.firstinspires.ftc.teamcode.subsystem.Intake;
import org.firstinspires.ftc.teamcode.vision.AprilTagLocalization;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import java.sql.Time;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.function.LongFunction;


@TeleOp(name = "YaTwo V", group = "competition")

public class YaHoo2 extends LinearOpMode {

    //x: square, y: triangle, a: x, b: circle

    private DcMotor LF;
    private DcMotor LB;
    private DcMotor RF;
    private DcMotor RB;
    private DcMotor lift;
    private Servo tilt;// 3
    private Servo grab;//0
    private Servo bucket;
    private double Speed;
    private boolean extend = false;
    private boolean extend2 = false;

    private DcMotor extender;


    @Override

    public void runOpMode() {

        LF = hardwareMap.get(DcMotor.class, "LF");
        LB = hardwareMap.get(DcMotor.class, "LB");
        RF = hardwareMap.get(DcMotor.class, "RF");
        RB = hardwareMap.get(DcMotor.class, "RB");
        lift = hardwareMap.get(DcMotor.class, "lift");
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        tilt = hardwareMap.get(Servo.class, "tilt");
        grab = hardwareMap.get(Servo.class, "grab");
        bucket = hardwareMap.get(Servo.class, "bucket");
        extender = hardwareMap.get(DcMotor.class, "extender");

        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 59, Math.toRadians(180)));



        LF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);



        LF.setDirection(DcMotorSimple.Direction.FORWARD);
        LB.setDirection(DcMotorSimple.Direction.FORWARD);
        RF.setDirection(DcMotorSimple.Direction.FORWARD);
        RB.setDirection(DcMotorSimple.Direction.REVERSE);


        bucket.setPosition(0.798);
        //pour == 0.21, down == 0.76
        tilt.setPosition(0.338);
        //down == 0.338    up == 0.87
        grab.setPosition(0.53);

        //clamp = 0.53, mid == 0.83, open == 0.67




        Gamepad currentDriverOneGamepad = new Gamepad();
        Gamepad previousDriverOneGamepad = new Gamepad();
        Gamepad currentDriverTwoGamepad = new Gamepad();
        Gamepad previousDriverTwoGamepad = new Gamepad();
        Speed = 0.7;

        IMU imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.RIGHT));
        imu.initialize(parameters);

        waitForStart();

        while (opModeIsActive()) {

            try {

                previousDriverOneGamepad.copy(currentDriverOneGamepad);
                currentDriverOneGamepad.copy(gamepad1);


                previousDriverTwoGamepad.copy(currentDriverTwoGamepad);
                currentDriverTwoGamepad.copy(gamepad2);


            } catch (Error ignore) {
            }



            if (currentDriverTwoGamepad.dpad_up && !previousDriverTwoGamepad.dpad_up){
                lift.setTargetPosition(-3050);
                lift.setPower(0.8);
                lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }

            if (currentDriverTwoGamepad.dpad_down && !previousDriverTwoGamepad.dpad_down){
                lift.setTargetPosition(-25);
                lift.setPower(0.8);
                lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
            if (currentDriverTwoGamepad.y && !previousDriverTwoGamepad.y) {
                bucket.setPosition(0.28);
            }
            if (currentDriverTwoGamepad.a && !previousDriverTwoGamepad.a) {
                bucket.setPosition(0.76);
            }






            if (currentDriverOneGamepad.dpad_up && !extend){
                extender.setPower(1);
                extend = true;
            } else if (!currentDriverOneGamepad.dpad_up && extend) {

                extender.setPower(0.23);
                extend = false;
            }


            if (currentDriverOneGamepad.dpad_down && !extend2){
                extender.setPower(-1);
                extend2 = true;
            } else if (!currentDriverOneGamepad.dpad_down && extend2) {
                extender.setPower(-0.34);
                extend2 = false;
            }





            if ( currentDriverOneGamepad.right_trigger > 0.1 && currentDriverOneGamepad.left_trigger < 0.1) {
                extender.setPower(currentDriverOneGamepad.right_trigger);
            } else if (currentDriverOneGamepad.left_trigger >0.1 && currentDriverOneGamepad.right_trigger < 0.1) {
                extender.setPower(currentDriverOneGamepad.left_trigger * -1);
            } else {
                extender.setPower(0);
            }







//

            if (currentDriverOneGamepad.x && !previousDriverOneGamepad.x){
                tilt.setPosition(tilt.getPosition()+0.01);
            }
            if (currentDriverOneGamepad.b && !previousDriverOneGamepad.b){
                tilt.setPosition(tilt.getPosition()-0.01);
            }


            if (currentDriverOneGamepad.y && !previousDriverOneGamepad.y){
                tilt.setPosition(0.338);
            }
            if (currentDriverOneGamepad.a && !previousDriverOneGamepad.a){
                tilt.setPosition(0.94);
            }
            if (currentDriverOneGamepad.x && !previousDriverOneGamepad.x) {
                grab.setPosition(0.7);
            }
            if (currentDriverOneGamepad.b && !previousDriverOneGamepad.b) {
                grab.setPosition(0.93);
            }


            if ( (lift.getCurrentPosition() >=-45 && lift.getCurrentPosition()<= 10) && ( Math.abs(lift.getCurrentPosition() - lift.getTargetPosition()) <= 20   )  ){
                lift.setPower(0);
            }


            //SetDriving(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);

            telemetry.addData("bucket", bucket.getPosition());
            telemetry.addData("tilt", tilt.getPosition());
            telemetry.addData("grab", grab.getPosition());
            telemetry.addData("lift", lift.getCurrentPosition());
            telemetry.addData("imu", imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
            telemetry.update();






            double x = gamepad1.left_stick_x;
            double y = -gamepad1.left_stick_y;
            double rx = gamepad1.right_stick_x;


            if (gamepad1.options) {
                imu.resetYaw();
            }

            /*double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
            double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
            double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);
            drive.setDrivePowers(new PoseVelocity2d(new Vector2d(rotX, rotY).times(1), rx));*/

            double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
            double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
            double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (rotY + rotX + rx) / denominator * Speed;
            double backLeftPower = (rotY - rotX + rx) / denominator * Speed;
            double frontRightPower = (rotY - rotX - rx) / denominator * Speed;
            double backRightPower = (rotY + rotX - rx) / denominator * Speed;

            LF.setPower(frontLeftPower);
            LB.setPower(backLeftPower);
            RF.setPower(frontRightPower);
            RB.setPower(backRightPower);

        }




    }








//    public void SetDriving ( double driving, double rotate, double strafe) {
//
//
//        double leftFront = (driving + rotate + strafe) * Speed;
//        double leftBack = (driving - rotate + strafe) * Speed;
//        double rightFront = (driving - rotate - strafe) * Speed;
//        double rightBack = (driving + rotate - strafe) * Speed;
//
//
//        LF.setPower(leftFront);
//        LB.setPower(leftBack);
//        RF.setPower(rightFront);
//        RB.setPower(rightBack);
//    }















}