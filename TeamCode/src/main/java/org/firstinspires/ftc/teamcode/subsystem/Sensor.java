package org.firstinspires.ftc.teamcode.subsystem;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Sensor extends SubsystemBase {
    private final Rev2mDistanceSensor distanceSensor;

    public Sensor(final HardwareMap hwMap){
        distanceSensor = hwMap.get(Rev2mDistanceSensor.class, "distance");
    }

    public  double getDistence(){
        return  distanceSensor.getDistance(DistanceUnit.CM);
    }
}
