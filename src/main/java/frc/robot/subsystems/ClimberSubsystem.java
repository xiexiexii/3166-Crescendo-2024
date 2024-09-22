// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimberConstants;

// Code for the Climbing Mechanism
public class ClimberSubsystem extends SubsystemBase {

  private Talon m_climberMotor1 = new Talon(ClimberConstants.kClimberMotor1);

  public void climb() {
    m_climberMotor1.set(-ClimberConstants.kClimbSpeed);
  }

  public void descend() {
    m_climberMotor1.set(ClimberConstants.kClimbSpeed);
  }

  public void stopClimb() {
    m_climberMotor1.set(0);
  }
}