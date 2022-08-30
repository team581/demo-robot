// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.superstructure.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.superstructure.SuperstructureSubsystem;
import frc.robot.superstructure.swiffer.SwifferMode;
import frc.robot.superstructure.swiffer.commands.SwifferCommand;

/** Lift the swiffer up and stop the flywheel. */
public class ArmUpAndStopCommand extends ParallelCommandGroup {
  /** Creates a new ArmUpAndStopCommand. */
  public ArmUpAndStopCommand(SuperstructureSubsystem superstructure) {
    addCommands(
        // Arm up
        // Stop the flywheel
        new SwifferCommand(superstructure.swiffer, SwifferMode.STOPPED)
            .until(() -> superstructure.swiffer.atGoal(SwifferMode.STOPPED)));

    addRequirements(superstructure, superstructure.swiffer);
  }
}
