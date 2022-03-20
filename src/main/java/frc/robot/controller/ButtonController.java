// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.controller;

import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * A wrapper around {@link LogitechF310DirectInputController} for triggering commands using buttons.
 */
public class ButtonController {
  public final Button aButton;
  public final Button bButton;
  public final Button xButton;
  public final Button yButton;

  public final Button leftTrigger;
  public final Button rightTrigger;

  public final Button leftBumper;
  public final Button rightBumper;

  public final Button leftStick;
  public final Button rightStick;

  public final Button backButton;
  public final Button startButton;

  public ButtonController(LogitechF310DirectInputController controller) {
    xButton =
        new JoystickButton(controller, LogitechF310DirectInputController.Button.X_BUTTON.value);
    aButton =
        new JoystickButton(controller, LogitechF310DirectInputController.Button.A_BUTTON.value);
    bButton =
        new JoystickButton(controller, LogitechF310DirectInputController.Button.B_BUTTON.value);
    yButton =
        new JoystickButton(controller, LogitechF310DirectInputController.Button.Y_BUTTON.value);

    leftBumper =
        new JoystickButton(controller, LogitechF310DirectInputController.Button.LEFT_BUMPER.value);
    rightBumper =
        new JoystickButton(controller, LogitechF310DirectInputController.Button.RIGHT_BUMPER.value);

    leftTrigger =
        new JoystickButton(controller, LogitechF310DirectInputController.Button.LEFT_TRIGGER.value);
    rightTrigger =
        new JoystickButton(
            controller, LogitechF310DirectInputController.Button.RIGHT_TRIGGER.value);

    backButton =
        new JoystickButton(controller, LogitechF310DirectInputController.Button.BACK_BUTTON.value);
    startButton =
        new JoystickButton(controller, LogitechF310DirectInputController.Button.START_BUTTON.value);

    leftStick =
        new JoystickButton(controller, LogitechF310DirectInputController.Button.LEFT_STICK.value);
    rightStick =
        new JoystickButton(controller, LogitechF310DirectInputController.Button.RIGHT_STICK.value);
  }
}
