package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.AutoTimeConstants;
import frc.robot.subsystems.ShooterSubsystem;

public class SpinUpShootShortCommand extends Command {
    
    // Instantiate Stuff
    ShooterSubsystem m_ShooterSubsystem;
    Timer timer = new Timer();

    public SpinUpShootShortCommand(ShooterSubsystem shooterSubsystem) {
        
        // Definitions and setting parameters are equal to members!
        m_ShooterSubsystem = shooterSubsystem;
        addRequirements(shooterSubsystem);
    }

    @Override
    public void initialize() {
        timer.start();
        timer.reset();
    }
    
    @Override
    public void execute() {
        if(timer.get() < AutoTimeConstants.spinUpAutoTime2){
            m_ShooterSubsystem.speakerSpinup();
        }
        if(timer.get() > AutoTimeConstants.spinUpAutoTime2 && timer.get() < AutoTimeConstants.shootAutoTime1){
            m_ShooterSubsystem.speakerSpinup();
            m_ShooterSubsystem.speakerShoot();
        }
        if (timer.get() > AutoTimeConstants.shootAutoTime2){
            m_ShooterSubsystem.stopSpinup();
            m_ShooterSubsystem.stopShoot();
        }
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return timer.get()>AutoTimeConstants.shootAutoTime2;
    }
}
