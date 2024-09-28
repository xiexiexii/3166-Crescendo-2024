package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;

public class SpinUpForSecsCommand extends Command{

    //for S E C S ... not what you're thinking 
    
    //instantiate stuff
    double m_targetSecs;
    ShooterSubsystem m_ShooterSubsystem;
    Timer timer = new Timer();

    public SpinUpForSecsCommand(double targetSecs, ShooterSubsystem shooterSubsystem){
        //definitions and setting parameters equal to members
        m_targetSecs = targetSecs;
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
        m_ShooterSubsystem.speakerSpinup();
    }

    @Override
    public void end(boolean interrupted){
        m_ShooterSubsystem.stopSpinup();
    }

    @Override
    public boolean isFinished(){
        return timer.hasElapsed(m_targetSecs);
    }

}