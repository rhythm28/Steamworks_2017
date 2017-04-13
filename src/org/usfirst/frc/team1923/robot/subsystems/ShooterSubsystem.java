package org.usfirst.frc.team1923.robot.subsystems;

import org.usfirst.frc.team1923.robot.RobotMap;
import org.usfirst.frc.team1923.robot.commands.shooter.ShooterCalibrateCommand;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShooterSubsystem extends Subsystem {

	private final double P_CONSTANT = 0;
	private final double I_CONSTANT = 0;
	private final double D_CONSTANT = 0;
	private final double F_CONSTANT = 0;
	private CANTalon shooter;
	private double speed;

	public final double allowableError = 100;

	public ShooterSubsystem() {
		this.shooter = new CANTalon(RobotMap.SHOOTER_PORT);

		this.shooter.configPeakOutputVoltage(12, -12);
		this.shooter.configNominalOutputVoltage(0, 0);

		this.shooter.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);

		this.shooter.setPID(P_CONSTANT, I_CONSTANT, D_CONSTANT);
		this.shooter.setF(F_CONSTANT);

	}

	public void set(double power) {
		shooter.changeControlMode(TalonControlMode.PercentVbus);
		shooter.set(power);
		speed = shooter.getEncVelocity();
		SmartDashboard.putNumber("Shooter Encoder Speed", speed);
	}

	public void setSetpoint(double setpoint) {
		shooter.changeControlMode(TalonControlMode.Speed);
		SmartDashboard.putNumber("Shooter Encoder Speed", speed);
		shooter.setSetpoint(setpoint);
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new ShooterCalibrateCommand());
	}

	public double getSpeed() {
		speed = shooter.getEncVelocity();
		SmartDashboard.putNumber("Shooter Encoder Speed", speed);
		return speed;
	}

	public double getError() {
		return shooter.getError();
	}

}
