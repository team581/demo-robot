// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.lifter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.lifter.LifterSubsystem;
import frc.robot.lifter.LifterPosition;

/** A command to move the lifter to a desired position. */
public class LifterCommand extends CommandBase {
  private final LifterSubsystem lifter;
  private final LifterPosition goalPosition;

  /** Creates a new LifterCommand. */
  public LifterCommand(LifterSubsystem lifter, LifterPosition goalPosition) {
    this.lifter = lifter;
    this.goalPosition = goalPosition;

    addRequirements(lifter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    lifter.setDesiredPosition(goalPosition);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return lifter.atPosition(goalPosition);
  }
}