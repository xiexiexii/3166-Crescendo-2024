// Imports stuff (again!)

package frc.robot;

import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.ControllerConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.SpinUpShootCommand;
import frc.robot.commands.SpinUpShootShortCommand;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

// This class is where the bulk of the robot should be declared.  Since Command-based is a
// "declarative" paradigm, very little robot logic should actually be handled in the Robot
// periodic methods (other than the scheduler calls).  Instead, the structure of the robot
// (including subsystems, commands, and button mappings) should be declared here.

public class RobotContainer {

  // Robot's Subsystems
  private final DriveSubsystem m_robotDrive = new DriveSubsystem();
  private final ClimberSubsystem m_ClimberSubsystem = new ClimberSubsystem();
  private final ShooterSubsystem m_ShooterSubsystem = new ShooterSubsystem();
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  // Controllers
  CommandXboxController m_driverController = new CommandXboxController(OIConstants.kDriverControllerPort);

  // The container for the robot. Contains subsystems, OI devices, and commands.
  public RobotContainer() {

    // Configure the button bindings
    configureButtonBindings();

    // DEPRECATED: Calibrates Gyro
    // m_robotDrive.calibrateGyro();

    // Configure Default Commands
    m_robotDrive.setDefaultCommand(
      
        // The left stick on the controller controls robot translation.
        // Turning is controlled by the X axis of the right stick.
        new RunCommand(

            // Joystick input to tele-op control
            () -> m_robotDrive.drive(
                -MathUtil.applyDeadband(-m_driverController.getLeftY(), OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(-m_driverController.getLeftX(), OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(m_driverController.getRightX(), OIConstants.kDriveDeadband),
                true, true), m_robotDrive));

    SmartDashboard.putData("AutoMode", m_chooser);

    // Named Command Configuration
    NamedCommands.registerCommand("Spin Up Shoot", new SpinUpShootCommand(m_ShooterSubsystem));
    NamedCommands.registerCommand("Spin Up Shoot Short", new SpinUpShootShortCommand(m_ShooterSubsystem));

    // TODO: New Autos!! 3 forward + park, amp side shoot and park, tune HLS Speaker Side Shoot Park

    // Autos
    m_chooser.addOption("Speaker Front Shoot and Park", m_robotDrive.getAuto("Speaker Front Shoot and Park"));
    // m_chooser.addOption("Speaker AMP Side Shoot and Park", m_robotDrive.getAuto("Speaker AMP Side Shoot and Park"));
    m_chooser.addOption("HLS Speaker Side Shoot Park", m_robotDrive.getAuto("HLS Speaker Shoot Park"));

  }

  // Use this method to define your button to command mappings. Buttons can be
  // created by instantiating a edu.wpi.first.wpilibj.GenericHID or one of its
  // subclasses edu.wpi.first.wpilibj.Joystick or XboxController, and then
  // passing it to a JoystickButton.

  private void configureButtonBindings() {
    
    new JoystickButton(m_driverController.getHID(), ControllerConstants.intakeButton)
      .whileTrue(
        new InstantCommand(() -> m_ShooterSubsystem.intake(), m_ShooterSubsystem))
      .whileFalse(
        new InstantCommand(() -> m_ShooterSubsystem.stopIntake(), m_ShooterSubsystem)
      );

    new Trigger(() -> m_driverController.getRawAxis(ControllerConstants.speakerSpinUpTrigger) > 0.05)
      .whileTrue(
        new InstantCommand(() -> m_ShooterSubsystem.speakerSpinup(), m_ShooterSubsystem))
      .whileFalse(
        new InstantCommand(() -> m_ShooterSubsystem.stopSpinup(), m_ShooterSubsystem)
      );

    new Trigger(() -> m_driverController.getRawAxis(ControllerConstants.speakerShootTrigger) > 0.05)
      .whileTrue(
        new InstantCommand(() -> m_ShooterSubsystem.speakerShoot(), m_ShooterSubsystem))
      .whileFalse(
        new InstantCommand(() -> m_ShooterSubsystem.stopShoot(), m_ShooterSubsystem)
      );

    new JoystickButton(m_driverController.getHID(), ControllerConstants.ampSpinUpButton)
      .whileTrue(
        new InstantCommand(() -> m_ShooterSubsystem.ampSpinUp(), m_ShooterSubsystem))
      .whileFalse(
        new InstantCommand(() -> m_ShooterSubsystem.stopSpinup(), m_ShooterSubsystem)
      );
    
    new JoystickButton(m_driverController.getHID(), ControllerConstants.ampShootButton)
      .whileTrue(
        new InstantCommand(() -> m_ShooterSubsystem.ampShoot(), m_ShooterSubsystem))
      .whileFalse(
        new InstantCommand(() -> m_ShooterSubsystem.stopShoot(), m_ShooterSubsystem)
      );

    new JoystickButton(m_driverController.getHID(), ControllerConstants.climbButton)
      .whileTrue(
        new InstantCommand(() -> m_ClimberSubsystem.climb(), m_ClimberSubsystem))
      .whileFalse(
        new InstantCommand(() -> m_ClimberSubsystem.stopClimb(), m_ClimberSubsystem)
      );
    
    new JoystickButton(m_driverController.getHID(), ControllerConstants.descendButton)
      .whileTrue(
        new InstantCommand(() -> m_ClimberSubsystem.descend(), m_ClimberSubsystem))
      .whileFalse(
        new InstantCommand(() -> m_ClimberSubsystem.stopClimb(), m_ClimberSubsystem)
      );
  }

  public Command getAutonomousCommand() {
    return m_chooser.getSelected();   
  } 
}