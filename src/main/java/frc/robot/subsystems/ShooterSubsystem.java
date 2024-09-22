package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase {

    private Talon m_shootMotor1 = new Talon(ShooterConstants.kShootMotor1);
    private Talon m_shootMotor2 = new Talon(ShooterConstants.kShootMotor2);
    private Talon m_shootMotor3 = new Talon(ShooterConstants.kTriggerMotor);

    public void intake() {
        m_shootMotor1.set(-0.6 * ShooterConstants.kSpeakShootSpeed);
        m_shootMotor2.set(-0.6 * ShooterConstants.kSpeakShootSpeed);
        m_shootMotor3.set(-0.6 * ShooterConstants.kSpeakKickerShootSpeed);
    }

    public void speakerSpinup() {
        m_shootMotor1.set(ShooterConstants.kSpeakShootSpeed);
        m_shootMotor2.set(ShooterConstants.kSpeakShootSpeed);
    }

    public void ampSpinUp() {
        m_shootMotor1.set(-ShooterConstants.kAmpTopSpeed);
        m_shootMotor2.set(ShooterConstants.kAmpBottomSpeed);
      }

    public void speakerShoot() {
        m_shootMotor1.set(ShooterConstants.kSpeakShootSpeed);
        m_shootMotor2.set(ShooterConstants.kSpeakShootSpeed);
        m_shootMotor3.set(ShooterConstants.kSpeakKickerShootSpeed);
    }

    public void ampShoot() {
        m_shootMotor1.set(-ShooterConstants.kAmpTopSpeed);
        m_shootMotor2.set(ShooterConstants.kAmpBottomSpeed);
        m_shootMotor3.set(ShooterConstants.kSpeakKickerShootSpeed);
      }

    public void stopSpinup() {
        m_shootMotor1.set(0);
        m_shootMotor2.set(0);
    }

    public void stopShoot() {
        m_shootMotor3.set(0);
    }

    public void stopIntake() {
        m_shootMotor1.set(0);
        m_shootMotor2.set(0);
        m_shootMotor3.set(0);
    }

    public void reverse() {
        m_shootMotor1.set(ShooterConstants.kSpeakReverseSpeed);
        m_shootMotor2.set(ShooterConstants.kSpeakReverseSpeed);
        m_shootMotor3.set(-ShooterConstants.kSpeakKickerShootSpeed);
    }

}