/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;


import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.DistancePid;
import frc.robot.Helpers;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.TurnPid;

/**
 * An example command.  You can replace me with your own command.
 */
public class TestTurnLeft90 extends Command {
    
    private Robot _robot;
    private int stoppedCounter;
    private boolean testCompleted;
    private TurnPid _turnPid;
    Timer _timer;
    double _startTime;

  public TestTurnLeft90(Robot robot) {
    // Use requires() here to declare subsystem dependencies
    _robot = robot;
    requires(_robot.driveTrain);
    stoppedCounter = 0;
    


  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    
    // _robot.leftMaster.setSelectedSensorPosition(0, 0, 10);
    // _robot.rightMaster.setSelectedSensorPosition(0, 0, 10);
    SmartDashboard.putString("Instructions", "The Robot will turn left 90 degrees, you can press button 2 to stop");
    SmartDashboard.putString("Status", "Running turn left 90 degrees");
    testCompleted = false;
    // _robot.navX.reset();
    // _robot.navX.zeroYaw();
    stoppedCounter = 0;
    _turnPid = new TurnPid(_robot);
    _turnPid.SetTargetAngle(_robot.navX.getYaw()-90);
    _timer = new Timer();
    _timer.start();
    _startTime = _timer.get();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(_robot.stick.getRawButton(1)){
      double power = _turnPid.GetAnglePidOutput(_robot.navX.getYaw());
      
      _robot.driveTrain.Move(-power, power); 
      if (power == 0){
          stoppedCounter ++;
          if(stoppedCounter == 1){

            SmartDashboard.putNumber("test time", _timer.get());
          }
      }else{
          stoppedCounter = 0;
          SmartDashboard.putNumber("test time", 0);
      }
      if (stoppedCounter > 25){
          testCompleted = true;
      }

    }
    else{
      _robot.driveTrain.Move(0, 0); 
  }
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    
    boolean done = _robot.stick.getRawButton(2) || testCompleted;
    if(done){
        
        SmartDashboard.putString("Status", "Completed turn left 90");
        return true;
    }
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    
    _robot.driveTrain.Move(0, 0); 
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    SmartDashboard.putString("Status", "Turn let 90 inches interupted");
  }
}
