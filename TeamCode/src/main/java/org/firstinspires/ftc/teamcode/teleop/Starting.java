package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;


@TeleOp(name = "DarbotsTeleop", group = "competition")

public class Starting extends LinearOpMode {

    //x: square, y: triangle, a: x, b: circle

    private DcMotor LF;
    private DcMotor LB;
    private DcMotor RF;
    private DcMotor RB;
    private DcMotor lift;
    private DcMotor extender;
    private Servo tilt;// 3
    private Servo grab;//0
    private Servo bucket;
    private double Speed;

    private boolean extend = false;
    private boolean extend2 = false;



    @Override

    public void runOpMode() {

        LF = hardwareMap.get(DcMotor.class, "LF");
        LB = hardwareMap.get(DcMotor.class, "LB");
        RF = hardwareMap.get(DcMotor.class, "RF");
        RB = hardwareMap.get(DcMotor.class, "RB");
        lift = hardwareMap.get(DcMotor.class, "lift");
        extender = hardwareMap.get(DcMotor.class, "extender");


        extender.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        tilt = hardwareMap.get(Servo.class, "tilt");
        grab = hardwareMap.get(Servo.class, "grab");
        bucket = hardwareMap.get(Servo.class, "bucket");


        LF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);



        LF.setDirection(DcMotorSimple.Direction.FORWARD);
        LB.setDirection(DcMotorSimple.Direction.FORWARD);
        RF.setDirection(DcMotorSimple.Direction.FORWARD);
        RB.setDirection(DcMotorSimple.Direction.REVERSE);


        bucket.setPosition(0.74);
        //pour == 0.21, down == 0.76
        tilt.setPosition(1);
        //down == 0.338    up == 0.87
        grab.setPosition(1);

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
                    lift.setTargetPosition(-65);
                    lift.setPower(0.8);
                    lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                }
                if (currentDriverTwoGamepad.y && !previousDriverTwoGamepad.y) {
                    bucket.setPosition(0.28);
                }
                if (currentDriverTwoGamepad.a && !previousDriverTwoGamepad.a) {
                    bucket.setPosition(0.74);
                }
                if (currentDriverTwoGamepad.x && !previousDriverTwoGamepad.x) {
                    Speed = 0.4;
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
                if (currentDriverOneGamepad.right_bumper && !previousDriverOneGamepad.right_bumper){
                    tilt.setPosition(1);
                }
                if (currentDriverOneGamepad.left_bumper && !previousDriverOneGamepad.left_bumper){
                    tilt.setPosition(0.18);
                }
                if (currentDriverOneGamepad.b && !previousDriverOneGamepad.b) {
                   grab.setPosition(0.67);
                }
                if (currentDriverOneGamepad.x && !previousDriverOneGamepad.x) {
                    grab.setPosition(1);
                }


                if ( (lift.getCurrentPosition() >=-90 && lift.getCurrentPosition()<= 10) && ( Math.abs(lift.getCurrentPosition() - lift.getTargetPosition()) <= 20   )  ){
                    lift.setPower(0);
                }


                SetDriving(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);

                //\\SetDriving2(-gamepad2.right_stick_y);

            telemetry.addData("bucket", bucket.getPosition());
            telemetry.addData("tilt", tilt.getPosition());
            telemetry.addData("grab", grab.getPosition());
            telemetry.addData("lift current pos ", lift.getCurrentPosition());
            telemetry.addData("lift target pos ", lift.getTargetPosition());
            telemetry.addData("lift power", lift.getPower());
            telemetry.addData("extender power", extender.getPower());
            telemetry.addData("imu", imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
            telemetry.update();






//            double x = gamepad1.left_stick_x;
//            double y = -gamepad1.left_stick_y;
//            double rx = gamepad1.right_stick_x;
//
//
//            if (gamepad1.options) {
//                imu.resetYaw();
//            }
//
//            /*double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
//            double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
//            double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);
//            drive.setDrivePowers(new PoseVelocity2d(new Vector2d(rotX, rotY).times(1), rx));*/
//
//            double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
//            double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
//            double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);
//
//            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
//            double frontLeftPower = (rotY + rotX + rx) / denominator * Speed;
//            double backLeftPower = (rotY - rotX + rx) / denominator * Speed;
//            double frontRightPower = (rotY - rotX - rx) / denominator * Speed;
//            double backRightPower = (rotY + rotX - rx) / denominator * Speed;
//
//            LF.setPower(frontLeftPower);
//            LB.setPower(backLeftPower);
//            RF.setPower(frontRightPower);
//            RB.setPower(backRightPower);

        }




    }








    public void SetDriving ( double driving, double rotate, double strafe) {


        double leftFront = (driving + rotate + strafe) * Speed;
        double leftBack = (driving - rotate + strafe) * Speed;
        double rightFront = (driving - rotate - strafe) * Speed;
        double rightBack = (driving + rotate - strafe) * Speed;


        LF.setPower(leftFront);
        LB.setPower(leftBack);
        RF.setPower(rightFront);
        RB.setPower(rightBack);
    }

    public void SetDriving2 (double extenderPower){
        double Power = extenderPower * 0.6;

        extender.setPower(Power);
    }
}