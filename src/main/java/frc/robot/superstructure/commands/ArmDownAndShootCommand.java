// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.superstructure.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.superstructure.SuperstructureSubsystem;
import frc.robot.superstructure.swiffer.SwifferMode;
import frc.robot.superstructure.swiffer.commands.SwifferCommand;

/**
 * Lowers the arm and shoots all cargo. This is used for resolving jams in the intake or discarding
 * unwanted cargo, not for scoring.
 */
public class ArmDownAndShootCommand extends SequentialCommandGroup {
  /** Creates a new ArmDownAndShootCommand. */
  public ArmDownAndShootCommand(SuperstructureSubsystem superstructure) {
    addCommands(
        // The shooting mode won't enable until the arm is in position so we manually tell
        // the lights that are are preparing to shoot.
        new InstantCommand(
            () -> superstructure.lights.setSubsystemState(SwifferMode.SHOOTING, false)),
        // Shoot all cargo after the arm is down
        // Since the default superstructure mode is to lift the arm up and since this command is run
        // continuously, adding an end condition here can cause the arm to jump up briefly as the
        // command ends and restarts. We rely on the copilot to know when to cancel this command.
        new SwifferCommand(superstructure.swiffer, SwifferMode.SHOOTING));

    addRequirements(superstructure, superstructure.swiffer);
  }
}
