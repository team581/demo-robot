// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.superstructure.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.superstructure.SuperstructureSubsystem;
import frc.robot.superstructure.lifter.LifterPosition;
import frc.robot.superstructure.lifter.commands.LifterCommand;
import frc.robot.superstructure.swiffer.commands.SwifferShootCommand;
import frc.robot.superstructure.swiffer.commands.SwifferStopCommand;

/** Puts the lifter up and shoots all cargo. */
public class LifterUpAndSwifferShootCommand extends SequentialCommandGroup {
  /** Creates a new LifterUpAndSwifferShootCommand. */
  public LifterUpAndSwifferShootCommand(SuperstructureSubsystem superstructure) {
    addCommands(
        // Lifter up
        new LifterCommand(superstructure.lifter, LifterPosition.UP),
        // Shoot all cargo
        new SwifferShootCommand(superstructure.swiffer),
        // Stop the flywheel once finished
        new SwifferStopCommand(superstructure.swiffer));

    addRequirements(superstructure);
  }
}