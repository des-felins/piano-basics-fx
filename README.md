# From Minor to Major: Solfeggio Basics

A desktop application aimed at helping users learn and practice scales and chords for piano.
Note that the application is a work-in-progress, features listed below will be implemented gradually.

## Target Audience

- Beginner to intermediate piano students.
- JavaFX and music tech enthusiasts.

## Features

- Centralized bean lifecycle management and dependency injection with Spring Boot.
- Smooth and modern UI with JavaFX.
- Embedded SQLite for saving user details, progress, and preferences.

### TODO

- Personalized themes & settings.
- Custom exercises for major/minor scales and chords.
- Interactive exercises for identifying chords, intervals, and scales by ear.
- Visual progress tracker and personal stats dashboard.
- Custom chord generator.
- High-quality sampled piano sounds to listen to chords, scales, or notes.
- Integration with the instrument (mic or wired) for interactive learning process.
- Immediate hints when you miss a note or chord.
- Virtual keyboard to do exercises in absence of an instrument.
- Native Image integration for creating platform-specific executables that don't require JVM to run.

## Technology Stack 

- Java 21
- JavaFX 21
- Maven
- SQLite
- Java Sound API? External library for audio?

## Running the application

Download Liberica JDK with JavaFX support:

   ```bash
   sdk install java 21.0.6.fx-librca
   ```

Build and run the application:

   ```bash
   ./mvnw spring-boot:run
   ```