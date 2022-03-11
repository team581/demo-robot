// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.vision;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;

/** A vision target for the vision system. */
public abstract class VisionTarget {
  /** The index of this vision target's pipeline. */
  public final int pipeline;

  protected final VisionSubsystemBase visionSubsystem;
  /**
   * The height from the floor to this vision target, in meters.
   *
   * @see
   *     <p>The <code>h2</code> distance in this diagram
   *     https://docs.limelightvision.io/en/latest/cs_estimating_distance.html
   */
  private final double heightFromFloor;

  protected VisionTarget(
      VisionSubsystemBase visionSubsystem, double heightFromFloor, int pipeline) {
    this.visionSubsystem = visionSubsystem;
    this.heightFromFloor = heightFromFloor;
    this.pipeline = pipeline;
  }

  /**
   * Calculates the distance "as the crow flies" in meters between the camera and this vision
   * target.
   *
   * <p>This value can be used for calculating the y-axis error.
   *
   * @see
   *     <p>Returns the <code>d</code> distance from this diagram
   *     https://docs.limelightvision.io/en/latest/cs_estimating_distance.html
   */
  private double getDistance() {
    final var h1 = visionSubsystem.heightFromFloor;
    final var h2 = heightFromFloor;

    final var a1 = visionSubsystem.angleOfElevation;
    final var a2 = Units.degreesToRadians(visionSubsystem.getY());

    return (h2 - h1) / Math.tan(a1 + a2);
  }

  /**
   * Calculates the strafe distance in meters between the camera and this vision target.
   *
   * <p>This is the x component of the Manhattan distance between the camera and this vision target.
   *
   * <p>This value can be used for calculating the x-axis error.
   */
  private double getStrafeDistance() {
    final var d = getDistance();
    final var theta = getRotation();

    return d * Math.sin(theta.getRadians());
  }

  private Rotation2d getRotation() {
    return new Rotation2d(Units.degreesToRadians(visionSubsystem.getX()));
  }

  /**
   * A pose where the vision target is the origin (vision target centric) except also kinda robot
   * centric. This value is not a pose, it's more like the error between the vision target and your
   * robot. For example, if you are orbiting around the vision target while maintaining the distance
   * directly between the robot and the vision target, this pose will not change.
   *
   * <p>You can assume that the data in NetworkTables is for this vision target.
   */
  public Pose2d getRobotPose() {
    final var x = getStrafeDistance();
    final var y = getDistance();
    final var theta = getRotation();

    return new Pose2d(x, y, theta);
  }
}