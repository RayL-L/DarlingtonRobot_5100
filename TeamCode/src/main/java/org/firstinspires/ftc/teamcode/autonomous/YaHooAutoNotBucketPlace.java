package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;


@Autonomous(name = " -HooYa", group = "competition")

public class YaHooAutoNotBucketPlace extends LinearOpMode {



    private DcMotor RB, LB, RF, LF, Intake, Ramp, shootL, shootR;


    private double Speed;


    public void runOpMode() {

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
        if (opModeIsActive()){
            LF.setPower(0.7);

        }

    }



}