# JMonkeyInterface

## Abstract

`JMonkeyInterface` is an experimental software artifact intended to investigate the integration of the jMonkeyEngine real-time 3D environment with the MS4ME developer simulator. The project explores how an interactive visualization layer can be coupled with model-driven simulation components, allowing simulated environmental states to be transmitted to MS4ME and processed results to be returned to the visualization in near real time.

The present implementation instantiates this objective through a flood-monitoring scenario in which a jMonkey-based terrain scene generates dynamic water-level observations, communicates these observations to the MS4ME side, and renders returned values within the application interface.

## Research Objective

The central objective of this project is to establish a bidirectional interface between:

- `jMonkeyEngine`, used as an interactive 3D visualization and simulation front end
- `MS4ME dev simulator`, used as the model execution and decision-processing layer

From a research perspective, the project serves as a proof of concept for coupling visual simulation environments with external model-based systems. Such coupling enables the 3D scene to operate as both a presentation layer and a source of simulated stimuli, while MS4ME provides structured processing of those stimuli.

## Prototype Scenario

The current prototype is organized around a flood-monitoring use case. Within the scene, three separate water bodies are represented and their heights evolve over time. These values are interpreted as sensor outputs and transmitted to the MS4ME integration layer under identifiers such as `Sensor1`, `Sensor2`, and `Sensor3`. Responses returned by the model side are displayed in the on-screen heads-up display, thereby making the interaction between the visualization and the simulator directly observable.

## Repository Organization

- `3D Models/BasicGame/`
  Legacy NetBeans/Ant jMonkey project containing source code, native libraries, and scene assets.
- `3D Models/BasicGame/src/mygame/Main.java`
  Primary application entry point for the flood-monitoring visualization.
- `3D Models/BasicGame/src/mygame/TestSceneWater.java`
  Auxiliary scene used for water-rendering experimentation.
- `3D Models/BasicGame/src/mygame/WaterUI.java`
  Utility class for interactive adjustment of water-rendering parameters in the test scene.
- `models/`
  MS4ME simulator artifacts (`.dnl`, `.ses`) associated with the prototype.
- `server/`
  Java-side communication code intended to support the interface to MS4ME.

## System Interaction

At the conceptual level, the system operates according to the following sequence:

1. The jMonkey application initializes a terrain-based 3D environment.
2. Dynamic water elements represent monitored lakes or reservoirs.
3. Water-height values are updated during execution.
4. Updated values are encapsulated as sensor-oriented requests.
5. These requests are transmitted to the MS4ME integration layer.
6. Model outputs returned from MS4ME are visualized in the application HUD.

This interaction pattern positions the jMonkey application as an experimental front end for observing, stimulating, and interpreting model behavior.

## Technical Context

The repository reflects a legacy Java desktop prototype with the following characteristics:

- Java source level `1.7`
- jMonkeyEngine `3.1`
- NetBeans project metadata (`nbproject`)
- Ant-based build configuration (`build.xml`)
- Bundled native runtime libraries for LWJGL, OpenAL, and Bullet

These choices suggest that the project should be understood primarily as a historical or exploratory prototype rather than a contemporary production system.

## Build and Execution Considerations

To execute the project in its present form, the following environment is likely required:

- A Java JDK compatible with source level `1.7`
- NetBeans, or another environment capable of importing legacy Ant-based Java projects
- jMonkeyEngine `3.1` libraries configured for the project
- The external `MS4meServer.jar` dependency referenced by the jMonkey application

The current NetBeans configuration references `MS4meServer.jar` through a machine-specific absolute path. Consequently, this dependency must be reconfigured locally before the project can be built successfully.

The principal visualization entry point is:

- `3D Models/BasicGame/src/mygame/Main.java`

A typical execution sequence is as follows:

1. Import `3D Models/BasicGame` as a NetBeans/Ant project.
2. Resolve missing library references, including the jMonkeyEngine libraries and `MS4meServer.jar`.
3. Build and run the application.
4. Ensure that the corresponding MS4ME-side service is active and reachable.

The current client implementation attempts to establish communication through port `4242`, implying that the complementary MS4ME-side service should be exposed there.

## MS4ME Artifacts

The `models/` directory contains the simulator artifacts associated with this prototype, including:

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

These artifacts appear to define the MS4ME-side structure responsible for receiving, routing, and processing stimuli originating from the visualization layer.

## Limitations

The repository currently exhibits several characteristics typical of an early-stage research prototype:

- Dependence on legacy Java and jMonkeyEngine versions
- NetBeans-specific project configuration
- Absolute, user-specific dependency paths
- Partial or incomplete communication/server-side code

Accordingly, the project should be interpreted as an integration study or proof of concept rather than a finalized software package.

## Future Work

Several extensions would improve the reproducibility and research utility of the project:

- Replace machine-specific library references with project-local or dependency-managed configuration
- Introduce a reproducible build process through Maven or Gradle
- Document the communication protocol between the visualization layer and MS4ME
- Specify the procedure for launching the simulator models and the interface concurrently
- Complete and validate the server-side integration code

## Conclusion

`JMonkeyInterface` demonstrates an exploratory coupling between a real-time 3D visualization environment and the MS4ME developer simulator. In its current state, the project provides an initial proof of concept for representing environmental dynamics visually, transmitting those dynamics as model inputs, and reflecting simulator outputs back into the interface for observation and analysis.
