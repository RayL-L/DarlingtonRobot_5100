package org.firstinspires.ftc.teamcode.subsystem;

import static java.lang.Thread.sleep;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Shooters extends SubsystemBase {
    public static double GRABBER_ONE_IDLE_POS = 0;

    public static double GRABBER_ONE_GRAB_POS = 1;

    public static double GRABBER_TWO_IDLE_POS = 0.33;
    public static double GRABBER_TWO_GRAB_POS = 0.23;
    public static double GRABBER_TWO_FULLGRAB = 0.19;


//    private final Servo tilt;
//    private final Servo grab;
//    private final Servo bucket;
//    private final DcMotor lift;
//
        private final DcMotor shootL;

        private  final DcMotor shootR;

        private final DcMotor Intak;
        private final DcMotor Ramp;

//
//    private final DcMotor extender;


    private double m_intakeSpeed = 1;

    public Shooters(final HardwareMap hwMap) {
        shootL = hwMap.get(DcMotor.class, "shootL");
        shootR = hwMap.get(DcMotor.class, "shootR");
        Intak = hwMap.get(DcMotor.class, "Intake");
        Ramp = hwMap.get(DcMotor.class, "Ramp");

    }

    public void initial(){

    }


    public void shoot(){
        shootL.setPower(1);
        shootR.setPower(-1);
    }

    public void stop(){
        shootL.setPower(0);
        shootR.setPower(0);
        Ramp.setPower(0);
        Intak.setPower(0);
    }

    public void convey(){
        Ramp.setPower(-1);
        Intak.setPower(1);
    }

    public void startIntake(){
        Intak.setPower(1);
        Ramp.setPower(-0.7);
    }

    public void stopIntake(){
        Intak.setPower(0);
        Ramp.setPower(0);
    }

    public void will(){
        Ramp.setPower(0.4);
    }
    public void willStop(){
        Ramp.setPower(0);
    }



}
