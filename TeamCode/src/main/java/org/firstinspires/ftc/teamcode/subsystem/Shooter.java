package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Shooter {

    private DcMotor RS;
    private DcMotor LS;
    private DcMotor ramp;
    public double shootPower = 1.0;

    public Shooter(HardwareMap hardwareMap) {
        RS = hardwareMap.get(DcMotor.class, "shootR");
        LS = hardwareMap.get(DcMotor.class, "shootL");
        ramp = hardwareMap.get(DcMotor.class, "Ramp");

        RS.setDirection(DcMotor.Direction.REVERSE);
        LS.setDirection(DcMotor.Direction.FORWARD);
        ramp.setDirection(DcMotor.Direction.REVERSE);

    }
    public void spinUp() {
        RS.setPower(shootPower);
        LS.setPower(shootPower);
    }

    public void stop() {
        RS.setPower(0);
        LS.setPower(0);
        ramp.setPower(0);
    }

    public void convey() {
        ramp.setPower(0.8);
    }
}
