// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.superstructure.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.superstructure.SuperstructureSubsystem;
import frc.robot.superstructure.swiffer.SwifferMode;
import frc.robot.superstructure.swiffer.commands.SwifferCommand;

/** Lowers the arm while snarfing. */
public class ArmDownAndSnarfCommand extends ParallelCommandGroup {
  /** Creates a new ArmDownAndSnarfCommand. */
  public ArmDownAndSnarfCommand(SuperstructureSubsystem superstructure) {
    addCommands(
        // While that's happening we begin spinning up the swiffer
        new SwifferCommand(superstructure.swiffer, SwifferMode.SNARFING));

    addRequirements(superstructure, superstructure.swiffer);
  }
}
