# JMonkeyInterface

`JMonkeyInterface` is a prototype integration project that connects a 3D scene built with the jMonkeyEngine to MS4ME developer simulator models.

The current implementation uses a jMonkey-based flood-monitoring scene to generate simulated sensor readings, sends those values to the MS4ME side, and displays responses from the simulator back inside the game HUD.

## Goal

The goal of this project is to bridge:

- `jMonkeyEngine` as the real-time 3D visualization layer
- `MS4ME dev simulator` as the model/simulation layer

In practice, this means the visual environment can act as a live producer of sensor data, while the MS4ME models can process that data and return values that influence or explain what is happening in the scene.

## Current Prototype

The repository contains an early prototype centered on a flood-monitoring scenario:

- Three lakes are rendered in a jMonkey terrain scene.
- Water heights change over time inside the simulation.
- Each lake acts like a sensor source (`Sensor1` to `Sensor3`).
- Sensor values are sent to the MS4ME side through a Java client/server integration.
- Returned model values are shown in the on-screen HUD.

## Repository Structure

- `3D Models/BasicGame/`
  Legacy NetBeans + Ant jMonkey project containing the 3D application and assets.
- `3D Models/BasicGame/src/mygame/Main.java`
  Main application entry point for the flood-monitoring visualization.
- `3D Models/BasicGame/src/mygame/TestSceneWater.java`
  Standalone water-scene experiment/example.
- `3D Models/BasicGame/src/mygame/WaterUI.java`
  Keyboard controls for tweaking water rendering parameters in the test scene.
- `models/`
  MS4ME simulator model files (`.dnl`, `.ses`) used by the prototype.
- `server/`
  Java-side integration/server code intended to communicate with MS4ME.

## How It Works

At a high level, the prototype works like this:

1. The jMonkey application starts and loads a terrain-based 3D scene.
2. Three water areas represent monitored lakes.
3. The application updates water depth values over time.
4. Each updated value is packaged as a request such as `Sensor1`, `Sensor2`, or `Sensor3`.
5. These requests are sent to the MS4ME integration layer.
6. Responses coming back from the model side are rendered in the HUD.

This makes the jMonkey scene a visual front end for MS4ME-driven simulation behavior.

## Technology Stack

- Java 7-era codebase
- jMonkeyEngine 3.1
- NetBeans project metadata (`nbproject`)
- Ant build (`build.xml`)
- Native libraries for LWJGL/OpenAL/Bullet included in the project folder

## Requirements

To work with this project as it exists today, you will likely need:

- Java JDK compatible with the original source level (`1.7`)
- NetBeans or another environment that can import legacy Ant-based Java projects
- jMonkeyEngine 3.1 libraries configured in NetBeans
- The external `MS4meServer.jar` dependency expected by the jMonkey project

Note: the NetBeans project properties currently reference `MS4meServer.jar` from a user-specific absolute path. You will need to update that dependency on your machine before the project can build successfully.

## Running the jMonkey Application

The main visualization entry point is:

- `3D Models/BasicGame/src/mygame/Main.java`

Typical workflow:

1. Open `3D Models/BasicGame` as a NetBeans/Ant project.
2. Fix any missing library references, especially jMonkeyEngine libraries and `MS4meServer.jar`.
3. Build and run the project.
4. Start the MS4ME simulator/integration side so the socket communication can succeed.

The application creates a client connection on port `4242`, so the corresponding MS4ME-side service is expected to be available there.

## MS4ME Models

The `models/` folder contains the simulator artifacts used by the project, including:

- `FloodMonitoring.ses`
- `Gateway.dnl`
- `JMonkeyInt.dnl`
- `Sensor1.dnl`
- `Sensor2.dnl`
- `Sensor3.dnl`
- `Sensor4.dnl`
- `StimuliGenerator1.dnl`
- `StimuliGenerator2.dnl`
- `StimuliGenerator3.dnl`
- `StimuliGenerator4.dnl`

These files define the MS4ME-side structure for receiving and processing the sensor/stimulus data coming from the jMonkey visualization.

## Project Status

This repository should currently be treated as a research/prototype integration rather than a polished, production-ready application.

Some signs of that:

- Legacy Java and jMonkeyEngine versions
- NetBeans-specific project setup
- External dependencies referenced through machine-specific paths
- Server-side code that appears to be partial or in-progress

## Suggested Next Steps

If you plan to continue this project, good next improvements would be:

- Replace absolute library paths with project-local or build-managed dependencies
- Add a reproducible build using Maven or Gradle
- Document the MS4ME communication protocol in more detail
- Clarify how to launch the simulator models alongside the jMonkey client
- Clean up and complete the server-side integration code

## Summary

`JMonkeyInterface` is an integration experiment that uses jMonkeyEngine as a visual simulation front end and MS4ME as the underlying developer simulator/modeling environment. The included prototype demonstrates a flood-monitoring scenario where dynamic lake levels are sent from the 3D scene to MS4ME and simulator responses are shown back in the interface.
