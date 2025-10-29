package org.firstinspires.ftc.teamcode.subsystem;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

public class Intake extends SubsystemBase {
    public static double GRABBER_ONE_IDLE_POS = 0;

    public static double GRABBER_ONE_GRAB_POS = 1;

    public static double GRABBER_TWO_IDLE_POS = 0.33;
    public static double GRABBER_TWO_GRAB_POS = 0.23;
    public static double GRABBER_TWO_FULLGRAB = 0.19;


    private final Servo tilt;
    private final Servo grab;
    private final Servo bucket;
    private final DcMotor lift;

    private final DcMotor shootL;

    private final DcMotor extender;


    private double m_intakeSpeed = 1;

    public Intake(final HardwareMap hwMap) {
        tilt = hwMap.get(Servo.class, "tilt");
        grab = hwMap.get(Servo.class, "grab");
        bucket = hwMap.get(Servo.class, "bucket");
        lift = hwMap.get(DcMotor.class, "lift");
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        extender = hwMap.get(DcMotor.class, "extender");
        shootL = hwMap.get(DcMotor.class, "shootL");
    }

    public void initial(){
        bucket.setPosition(0.798);
        grab.setPosition(0.99);
        tilt.setPosition(1);
    }

    public void tiltUp(){
        tilt.setPosition(1);
    }


    public void lauch(){
    }
    public void tiltMid(){tilt.setPosition(0.7);}

    public void tiltDown(){
        tilt.setPosition(0.18);
    }

    public void grabUp(){
        grab.setPosition(0.67);
    }

    public void grabMid(){
        grab.setPosition(0.99);
    }

    public void grabDown(){
        grab.setPosition(1);
    }

    public void liftRaise(){
        lift.setTargetPosition(-3050);
        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lift.setPower(0.8);
    }

    public void extend() {
        extender.setPower(0.55);
    }

    public void unextend() {
        extender.setPower(0);
    }

    public void dextend() {
        extender.setPower(-0.55);
    }

    public void liftDown(){
        lift.setTargetPosition(-25);
        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lift.setPower(0.8);
    }

    public void pourBucket(){
        bucket.setPosition(0.28);
    }

    public void downBucket(){
        bucket.setPosition(0.74);
    }


}
