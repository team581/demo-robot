// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.auto.commands;

import com.pathplanner.lib.PathPlanner;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.drive.DriveSubsystem;
import frc.robot.localization.Localization;
import frc.robot.localization.commands.SeedLocalizationCommand;
import frc.robot.superstructure.SuperstructureSubsystem;
import frc.robot.superstructure.commands.ArmUpAndSwifferShootCommand;
import frc.robot.superstructure.commands.ArmUpAndSwifferStopCommand;
import lib.pathplanner.PPCommand;

public class SouthSingleBallAutoCommand extends SequentialCommandGroup {
  /** Creates a new SouthSingleBallAutoCommand. */
  public SouthSingleBallAutoCommand(
      DriveSubsystem driveSubsystem,
      SuperstructureSubsystem superstructure,
      Localization localization) {
    final var path = PathPlanner.loadPath("SouthSingleBallAuto", 4, 2.25);

    addCommands(
        new SeedLocalizationCommand(localization, path),
        new ArmUpAndSwifferShootCommand(superstructure),
        new ArmUpAndSwifferStopCommand(superstructure),
        new PPCommand(path, driveSubsystem, localization));

    addRequirements(driveSubsystem, superstructure);
  }
}
