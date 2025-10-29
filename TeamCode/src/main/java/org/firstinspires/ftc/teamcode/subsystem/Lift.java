package org.firstinspires.ftc.teamcode.subsystem;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Lift extends SubsystemBase {
    public static double MAX_VEL = 35;
    public static double MAX_ACCEL = 35;
    public final static int LIFT_TOLERANCE = 10;
    private final DcMotorEx m_leftMotor, m_rightMotor;

    public Servo m_Leftraise, m_Rightraise;



    public Lift(final HardwareMap hwMap) {
        m_leftMotor = hwMap.get(DcMotorEx.class, "LSlide");
        m_rightMotor = hwMap.get(DcMotorEx.class, "RSlide");

        m_Leftraise = hwMap.get(Servo.class, "ExtendL");

        m_Rightraise = hwMap.get(Servo.class, "ExtendR");


        m_leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        m_leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m_rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        m_leftMotor.setTargetPosition(0);
        m_rightMotor.setTargetPosition(0);

        m_leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        m_rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        m_leftMotor.setPower(1);
        m_rightMotor.setPower(1);

        m_leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        m_rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        m_Leftraise.setDirection(Servo.Direction.REVERSE);

    //    m_Leftraise.setPosition(0.18);
    //    m_Rightraise.setPosition(0.18);

        // heighest  = 0.53
        //lowest on start =  0.18
        //lowest = 0.180.

    }

    public void toInitial() {

        m_leftMotor.setTargetPosition(0);
        m_rightMotor.setTargetPosition(0);

    }

    public void SetRaiseRPosition(double i) {
        m_Rightraise.setPosition(i);
    }

    public void SetRaiseLPosition(double i) {
        m_Leftraise.setPosition(i);
    }

    public void RasiseHigh() {
        m_Leftraise.setPosition(0.53);
        m_Rightraise.setPosition(0.53);
    }

    public double getRaiseLPosition() {
        return m_Leftraise.getPosition();
    }

    public double getRaiseRPosition() {
        return m_Rightraise.getPosition();
    }

    public void setPosition(int pos) {
        m_leftMotor.setTargetPosition(pos);
        m_rightMotor.setTargetPosition(pos);

    }

    public int getTargetPosition() {
     return m_leftMotor.getTargetPosition();
    }

    public void setRelativePosition(int pos) {

        m_leftMotor.setTargetPosition(m_leftMotor.getTargetPosition() + pos);
        m_rightMotor.setTargetPosition(m_rightMotor.getTargetPosition() + pos);
    }

    public int getPosition() {
        return m_leftMotor.getCurrentPosition();
    }

    public double getM_Leftraise() {
        return m_Leftraise.getPosition();
    }

    public double getVelocity() {
        return (m_leftMotor.getVelocity() + m_rightMotor.getVelocity()) / 2;
    }

    public void setPower(double power) {
        m_leftMotor.setPower(power);
        m_rightMotor.setPower(power);
    }

    public boolean isWithinTolerance(double target) {
        return Math.abs(target - m_leftMotor.getCurrentPosition()) <= LIFT_TOLERANCE;
    }
}