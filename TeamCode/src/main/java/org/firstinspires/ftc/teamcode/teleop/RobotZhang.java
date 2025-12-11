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


@TeleOp(name = "ZhangTeleop", group = "competition")

public class RobotZhang extends LinearOpMode {

    //x: square, y: triangle, a: x, b: circle


    private DcMotor RB, LB, RF, LF, Intake, Ramp, shootL, shootR;


    private double speed = 0.6;

    @Override



    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        LF = hardwareMap.get(DcMotor.class, "LF");
        LB = hardwareMap.get(DcMotor.class, "LB");
        RF = hardwareMap.get(DcMotor.class, "RF");
        RB = hardwareMap.get(DcMotor.class, "RB");

        Intake = hardwareMap.get(DcMotor.class, "Intake");
        Ramp = hardwareMap.get(DcMotor.class, "Ramp");
        shootR = hardwareMap.get(DcMotor.class, "shootR");
        shootL = hardwareMap.get(DcMotor.class, "shootL");





        LF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Ramp.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shootR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shootL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //!!!
        LF.setDirection(DcMotorSimple.Direction.REVERSE);
        LB.setDirection(DcMotorSimple.Direction.REVERSE);
        RF.setDirection(DcMotorSimple.Direction.REVERSE);
        RB.setDirection(DcMotorSimple.Direction.REVERSE);
        Intake.setDirection(DcMotorSimple.Direction.FORWARD);
        Ramp.setDirection(DcMotorSimple.Direction.REVERSE);
        shootR.setDirection(DcMotorSimple.Direction.REVERSE);
        shootL.setDirection(DcMotorSimple.Direction.FORWARD);

        Gamepad currentDriverOneGamepad = new Gamepad();
        Gamepad previousDriverOneGamepad = new Gamepad();
        Gamepad currentDriverTwoGamepad = new Gamepad();
        Gamepad previousDriverTwoGamepad = new Gamepad();



        waitForStart();

        while (opModeIsActive()) {

                try {

                    previousDriverOneGamepad.copy(currentDriverOneGamepad);
                    currentDriverOneGamepad.copy(gamepad1);


                    previousDriverTwoGamepad.copy(currentDriverTwoGamepad);
                    currentDriverTwoGamepad.copy(gamepad2);


                } catch (Error ignore) {
                }
                //Ramp
                if(currentDriverTwoGamepad.left_bumper){
                    Ramp.setDirection(DcMotorSimple.Direction.REVERSE);
                    Ramp.setPower(1);
                }
                else{
                    Ramp.setDirection(DcMotorSimple.Direction.REVERSE);
                    Ramp.setPower(0);
                }
                //Intake on when player1 trigger pressed
                if(currentDriverTwoGamepad.right_trigger>0){
                    Intake.setDirection(DcMotorSimple.Direction.FORWARD);
                    Intake.setPower(0.8);
                }
                else{
                    Intake.setDirection(DcMotorSimple.Direction.FORWARD);
                    Intake.setPower(0);
                }
                //shooter
                if(currentDriverTwoGamepad.left_trigger>0){
                    shootL.setPower(1);
                    shootR.setPower(1);
                }
                else{
                    shootL.setPower(0);
                    shootR.setPower(0);
                }


                //Increase Speed when dpadup pressed
                if(currentDriverOneGamepad.dpad_up && !previousDriverOneGamepad.dpad_up){
                    if(speed==0.3){
                        speed=0.6;
                    }
                    else{
                        speed=0.85;
                    }
                }
                //if the ball get stuck
                if(currentDriverTwoGamepad.circle){
                    Intake.setDirection(DcMotorSimple.Direction.REVERSE);
                    Intake.setPower(0.8);
                }
                //if the ball needs to go down
                if(currentDriverTwoGamepad.triangle){
                    Ramp.setDirection(DcMotorSimple.Direction.FORWARD);
                    Ramp.setPower(0.5);
                }
                //Decrease Speed when dpaddown pressed
                if(currentDriverOneGamepad.dpad_down && !previousDriverOneGamepad.dpad_down){
                    if(speed==0.85){
                        speed=0.6;
                    }
                    else{
                        speed=0.3;
                    }
                }

//            if(currentDriverOneGamepad.dpad_down){
//                RB.setPower(0.2);
//            }
//            if(currentDriverOneGamepad.dpad_up){
//                RB.setPower(0.2);
//            }
//            if(currentDriverOneGamepad.dpad_right){
//                RB.setPower(0.2);
//            }
//            if(currentDriverOneGamepad.dpad_left){
//                RB.setPower(0.2);
//            }

                SetDriving(gamepad1.left_stick_y, gamepad1.left_stick_x*-1, gamepad1.right_stick_x*-1, speed);
        }

        //Telemetry does not word D:
        telemetry.addData("Speed: ", speed);
        telemetry.addData("Gamepad1 Right Trigger: ", gamepad1.right_trigger);
        telemetry.addData("Intake Speed: ", Intake.getPower());
        telemetry.update();


    }








    public void SetDriving(double drive, double strafe, double rotate, double Speed) {
        double max;



        double leftFrontPower  = drive + strafe + rotate;
        double rightFrontPower = drive - strafe - rotate;
        double leftBackPower   = drive - strafe + rotate;
        double rightBackPower  = drive + strafe - rotate;


        max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
        max = Math.max(max, Math.abs(leftBackPower));
        max = Math.max(max, Math.abs(rightBackPower));

        if (max > 1.0) {
            leftFrontPower  /= max;
            rightFrontPower /= max;
            leftBackPower   /= max;
            rightBackPower  /= max;
        }

        // Send calculated power to wheels
        LF.setPower(leftFrontPower * Speed);
        RF.setPower(rightFrontPower * Speed);
        LB.setPower(leftBackPower * Speed);
        RB.setPower(rightBackPower * Speed);

    }
}