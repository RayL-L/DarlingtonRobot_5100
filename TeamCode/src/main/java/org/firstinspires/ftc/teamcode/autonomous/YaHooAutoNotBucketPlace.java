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


    private DcMotor LF;

    private DcMotor LB;

    private DcMotor RF;

    private DcMotor RB;

    private DcMotor lift;

    private DcMotor tilt;// 3

    private DcMotorEx grab;//0

    private Servo bucket;


    private double Speed;


    public void runOpMode() {

        LF = hardwareMap.get(DcMotor.class, "LF");

        LB = hardwareMap.get(DcMotor.class, "LB");

        RF = hardwareMap.get(DcMotor.class, "RF");

        RB = hardwareMap.get(DcMotor.class, "RB");

        lift = hardwareMap.get(DcMotor.class, "lift");

        tilt = hardwareMap.get(DcMotor.class, "tilt"); //back: 560   front:-135

        grab = hardwareMap.get(DcMotorEx.class, "grab");

        bucket = hardwareMap.get(Servo.class, "bucket"); //down:0.15    up:0.7



        LF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        LB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        RF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        RB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        LF.setDirection(DcMotorSimple.Direction.FORWARD);

        LB.setDirection(DcMotorSimple.Direction.FORWARD);

        RF.setDirection(DcMotorSimple.Direction.REVERSE);

        RB.setDirection(DcMotorSimple.Direction.REVERSE);


        bucket.setPosition(0);

        grab.setPower(0);

        Gamepad currentDriverOneGamepad = new Gamepad();

        Gamepad previousDriverOneGamepad = new Gamepad();

        Gamepad currentDriverTwoGamepad = new Gamepad();

        Gamepad previousDriverTwoGamepad = new Gamepad();

        waitForStart();
        if (opModeIsActive()) {


            LF.setPower(0.8);
            RF.setPower(0.8);
            RB.setPower(0.8);
            LB.setPower(0.8);
            sleep(1800);
            LF.setPower(0);
            RF.setPower(0);
            RB.setPower(0);
            LB.setPower(0);
        }




    }



}