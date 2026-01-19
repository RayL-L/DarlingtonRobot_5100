package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.MecanumDrive;


@TeleOp(name = "ZhangTeleop", group = "competition")

public class RobotZhang extends LinearOpMode {

    //x: square, y: triangle, a: x, b: circle

    private ElapsedTime runtime = new ElapsedTime();

    private ElapsedTime spintime = new ElapsedTime();


    private DcMotor RB, LB, RF, LF, Intake, Ramp, shootL, shootR;

    private double rpm = 0.0;

    private long lastTime = 0;
    private int lastPos1 = 0;
    private int lastPos2 = 0;
    private final double CPR = 112.0;



    // ===== Shooter calibration data (measured) =====
// Power -> RPM
    private static final double[] SHOOTER_PWR = {0.45, 0.48, 0.60, 0.72, 0.80};
    private static final double[] SHOOTER_RPM = {568,  602,  757,  904, 1006};



    private String shootDistance = "far";

    private double FAR_RPM   = 560;
    private double CLOSE_RPM = 260;

    private double targetRPM = 560;


    private double speed = 0.7;

    private boolean driveReversed = false;

    boolean shooterEnabled = false;


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

        shootL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        shootR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        shootL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        shootR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);




        waitForStart();

        lastTime = System.nanoTime();
        lastPos1 = shootL.getCurrentPosition();
        lastPos2 = shootR.getCurrentPosition();

        while (opModeIsActive()) {

            try {

                previousDriverOneGamepad.copy(currentDriverOneGamepad);
                currentDriverOneGamepad.copy(gamepad1);


                previousDriverTwoGamepad.copy(currentDriverTwoGamepad);
                currentDriverTwoGamepad.copy(gamepad2);


            } catch (Exception e) {
                telemetry.addData("Gamepad error", e.getMessage());
            }

            if(currentDriverTwoGamepad.square && !previousDriverTwoGamepad.square){
                if (shootDistance.equals("far")) {
                    shootDistance = "close";
                    targetRPM = CLOSE_RPM;
                } else {
                    shootDistance = "far";
                    targetRPM = FAR_RPM;
                }
            }

            //double avgGunSpeedCurrent = ((Math.abs(shootL.getPower()) + Math.abs(shootR.getPower()))/2);
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

            //OPEN or CLOSE the SHOOTING MOTOR
            if (currentDriverTwoGamepad.right_bumper && !previousDriverTwoGamepad.right_bumper) {
                shooterEnabled = !shooterEnabled;
            }


            // 微调 ONLY 当前模式
            if (currentDriverTwoGamepad.dpad_up && !previousDriverTwoGamepad.dpad_up) {
                targetRPM += 10;
            }
            if (currentDriverTwoGamepad.dpad_down && !previousDriverTwoGamepad.dpad_down) {
                targetRPM -= 10;
            }
            if (currentDriverTwoGamepad.dpad_right && !previousDriverTwoGamepad.dpad_right) {
                targetRPM += 50;
            }
            if (currentDriverTwoGamepad.dpad_left && !previousDriverTwoGamepad.dpad_left) {
                targetRPM -= 50;
            }


            long now = System.nanoTime();
            double dt = (now - lastTime) / 1_000_000_000.0; // seconds

            if (dt >= 0.05) {

                int pos1 = shootL.getCurrentPosition();
                int pos2 = shootR.getCurrentPosition();

                double delta1 = pos1 - lastPos1;
                double delta2 = pos2 - lastPos2;

                double avgTicks = (Math.abs(delta1) + Math.abs(delta2)) / 2.0;
                double ticksPerSec = avgTicks / dt;
                rpm = (ticksPerSec / CPR) * 60.0;

                lastPos1 = pos1;
                lastPos2 = pos2;
                lastTime = now;
            }

            targetRPM = Math.max(0, Math.min(1200, targetRPM));


            // 控制 targetSpeed
            if (shooterEnabled) {
                targetSpinRPM(targetRPM, rpm);
            } else {
                targetSpinRPM(0, rpm);
            }



            //Toggle

            if (currentDriverOneGamepad.cross && !previousDriverOneGamepad.cross) {
                driveReversed = !driveReversed;  // Toggle direction
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



            //Should be theoretically workable

            double drive  = gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x * -1;
            double rotate = gamepad1.right_stick_x * -1;


            if (driveReversed) {
                drive  = -drive;
                strafe = -strafe;
                //rotate = -rotate
            }

            SetDriving(drive, strafe, rotate, speed);

            telemetry.addData("ShooterPower", shootL.getPower());
            telemetry.addData("CurrentRPM", rpm);

            telemetry.addData("Enc L", shootL.getCurrentPosition());
            telemetry.addData("Enc R", shootR.getCurrentPosition());

            telemetry.addData("Target RPM", targetRPM);
            telemetry.addData("Shooter Mode", shootDistance);
            telemetry.addData("Shooter Enabled", shooterEnabled);
            telemetry.update();
        }



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

    public void targetSpinRPM(double targetRPM, double currentRPM) {

        if (targetRPM <= 0) {
            shootL.setPower(0);
            shootR.setPower(0);
            return;
        }

        // ===== Feedforward (经验值) =====
        // 你之前的数据：1000 RPM ≈ 0.80 power
        double kF = 0.000708;   // power per RPM
        double nominalVoltage=13.0;
        double batteryVoltage=getBatteryVoltage();
        double ff = targetRPM * kF * (nominalVoltage/batteryVoltage) ;

        // ===== Proportional correction =====
        double error = targetRPM - currentRPM;

        double kP_up = 0.0006;   // 低于目标：正常推
        double kP_down = 0.00035; // 高于目标：更用力刹

        double kP = (error >= 0) ? kP_up : kP_down;

        double power = ff + kP * error;

        // Clamp
        power = Math.max(0.0, Math.min(1.0, power));

        shootL.setPower(power);
        shootR.setPower(power);
    }

    private double getBatteryVoltage() {
        double minVoltage = Double.POSITIVE_INFINITY;

        for (com.qualcomm.robotcore.hardware.VoltageSensor sensor
                : hardwareMap.voltageSensor) {

            double voltage = sensor.getVoltage();

            if (voltage > 0) {
                minVoltage = Math.min(minVoltage, voltage);
            }
        }

        return minVoltage;
    }




}