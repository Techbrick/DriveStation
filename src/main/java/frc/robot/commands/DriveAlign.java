package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.Robot;

public class DriveAlign extends Command{
    private double distance = 0;
    private double turn = 0;
    private double drive = 0;
    private Robot _robot;
    NetworkTableEntry tx;
    NetworkTableEntry ta;

    public DriveAlign(Robot robot){
        requires(_robot.driveTrain);
        _robot = robot;
        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        tx = table.getEntry("tx");
        ta = table.getEntry("ta");
        double x = tx.getDouble(0.0);
        turn = 90 - Math.atan(1/Math.tan(x));
        drive = distance / Math.cos(x);
    }

    protected void initialize(){
        
    }

    protected void execute(){
        //read values periodically
        double x = tx.getDouble(0.0);
        double area = ta.getDouble(0.0);
        //post to smart dashboard periodically
        SmartDashboard.putNumber("LimelightX", x);
        SmartDashboard.putNumber("LimelightArea", area);
        distance = 25.64 / 30 - 0.29 * area / 30;
        
    }

    protected void end(){
    }

    protected boolean isFinished(){
        return true;
    }
}