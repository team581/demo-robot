// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.drive.wheel;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.SensorVelocityMeasPeriod;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import frc.robot.Constants;
import frc.robot.misc.exceptions.UnknownTargetRobotException;
import frc.robot.misc.io.Falcon500IO;

public class WheelIOFalcon500 extends Falcon500IO implements WheelIO {
  private final WPI_TalonFX motor;

  public WheelIOFalcon500(Corner corner) {
    switch (Constants.getRobot()) {
      case COMP_BOT:
        setGearing(12.75);
        switch (corner) {
          case FRONT_LEFT:
            motor = new WPI_TalonFX(14);
            break;
          case FRONT_RIGHT:
            motor = new WPI_TalonFX(11);
            motor.setInverted(true);
            break;
          case REAR_LEFT:
            motor = new WPI_TalonFX(12);
            break;
          case REAR_RIGHT:
            motor = new WPI_TalonFX(13);
            motor.setInverted(true);
            break;
          default:
            throw new IllegalArgumentException("Unknown corner");
        }
        break;
      case TEST_2020_BOT:
        setGearing(10.71);
        switch (corner) {
          case FRONT_LEFT:
            motor = new WPI_TalonFX(10);
            break;
          case FRONT_RIGHT:
            motor = new WPI_TalonFX(11);
            motor.setInverted(true);
            break;
          case REAR_LEFT:
            motor = new WPI_TalonFX(12);
            break;
          case REAR_RIGHT:
            motor = new WPI_TalonFX(13);
            motor.setInverted(true);
            break;
          default:
            throw new IllegalArgumentException("Unknown corner");
        }
        break;
      case SIM_BOT:
        setGearing(1);
        switch (corner) {
          case FRONT_LEFT:
            motor = new WPI_TalonFX(1);
            break;
          case FRONT_RIGHT:
            motor = new WPI_TalonFX(2);
            motor.setInverted(true);
            break;
          case REAR_LEFT:
            motor = new WPI_TalonFX(3);
            break;
          case REAR_RIGHT:
            motor = new WPI_TalonFX(4);
            motor.setInverted(true);
            break;
          default:
            throw new IllegalArgumentException("Unknown corner");
        }
        break;
      default:
        throw new UnknownTargetRobotException();
    }

    // TODO: These values probably need to be tuned - see tuning instructions
    // https://docs.ctre-phoenix.com/en/stable/ch14_MCSensor.html#recommended-procedure
    motor.configVelocityMeasurementPeriod(SensorVelocityMeasPeriod.Period_1Ms);
    motor.configVelocityMeasurementWindow(1);
  }

  @Override
  public void updateInputs(Inputs inputs) {
    inputs.appliedVolts = motor.getMotorOutputVoltage();
    inputs.currentAmps = motor.getSupplyCurrent();
    inputs.tempCelcius = motor.getTemperature();
    inputs.positionRadians = sensorUnitsToRadians(motor.getSelectedSensorPosition());
    inputs.velocityRadiansPerSecond = sensorUnitsToRadians(motor.getSelectedSensorVelocity() * 10);
  }

  @Override
  public void setVoltage(double outputVolts) {
    motor.setVoltage(outputVolts);
  }

  @Override
  public void zeroEncoder() {
    motor.setSelectedSensorPosition(0);
  }

  @Override
  public MotorController getMotorController() {
    return motor;
  }
}
