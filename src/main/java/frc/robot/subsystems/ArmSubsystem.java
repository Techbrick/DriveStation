/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;


/**
 * Add your docs here.
 */
public class ArmSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private Robot _robot;
  private TalonSRX mc_arm;
  public ArmSubsystem(Robot r) {
    _robot = r;
    mc_arm = new TalonSRX(RobotMap.mc_arm_CANID);

    mc_arm.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 20);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void resetZero() {
    mc_arm.setSelectedSensorPosition(0, 0, 20);
  }

  public int getEncoderTicks() {
    return mc_arm.getSensorCollection().getPulseWidthPosition();
  }

  public void moveUp() {
    mc_arm.
  }

  public void moveDown() {

  }

  
}
