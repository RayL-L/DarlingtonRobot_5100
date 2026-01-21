# FTC Team 5100 – [Season Name]

FTC robot code for the **2025-2026** season, developed using **Android Studio** and **Road Runner**.

---

## Overview

This repository contains the full robot code for **FTC Team 5100** during the **2025-2026** season.
The codebase supports:
- Road Runner autonomous trajectories
- Modular subsystems architecture
- Multiple autonomous start positions
- Driver Hub configuration & selection
- TeleOp with shooting and intake function

---

## Hardware

- Control Hub
- Driver Hub
- Expansion Hub
- Drivetrain: [Mecanum]
- Motors: [model]
- Servos: [model]
- IMU
- Webcam (for AprilTag)

---

## Software Stack

- Android Studio
- FTC SDK **11.0.0**
- Road Runner
- FTCLib
- OpenCV / AprilTag (if applicable)

---

## Setup & Installation

1. Clone this repository
2. Open in **Android Studio**
3. Allow Gradle to sync
4. Connect Driver Hub via USB or Wi-Fi
5. Build and install the app

---

## Autonomous

Autonomous is implemented using **Road Runner**.

Features include:
- TrajectorySequence-based paths
- Multiple start positions
- Optional vision-based decision making

Start position and alliance should be determined and set before running.

---

## TeleOp

- Field-centric mecanum drive
- Gamepad 1: drivetrain control
- Gamepad 2: mechanisms and scoring

---

## Tuning

Make sure to tune:
- Track width
- Encoder ticks per revolution

---

## How to Run

1. Power on the robot
2. Open **Driver Hub**
3. Select an OpMode
4. Press **INIT**
5. Press **PLAY**

---

## Contributing

- Create a feature branch for new work
- Test code on robot before pushing
- Use clear commit messages

---

## Team

FTC Team 5100 – DarBot

Developers:
- [Kerui(Ray) Lu]
- [Fanghe(Will) Zhang]

Mentors:
- [Owen Kinney]

---

## License

This project is intended for educational use in the FIRST Tech Challenge.
