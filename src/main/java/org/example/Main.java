package org.example;

import org.example.render.Window;
import org.example.shader.ShaderTextured;
import org.example.shapes.Square;
import org.example.shapes.Triangle;

import java.io.IOException;

import static org.lwjgl.opengl.GL.createCapabilities;
import static org.lwjgl.opengl.GL11.*;

public class Main {
    private static Window window;
    private static int width = 600;
    private static int height = 400;
    private static String title = "OpenGL Window";

    public static int getWidth() {
        return width;
    }
    public static int getHeight() {
        return height;
    }

    public void run() throws IOException {
        window = new Window(width, height, title);
        loop();
        window.terminate();
    }

    private void loop() throws IOException {
        createCapabilities();

        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        ShaderTextured shader = new ShaderTextured();
        shader.bindAttributes();

        while (!window.shouldClose()) {
            shader.start();

            new Square(0.1f, 0.1f, 0.1f, 0.1f,  "src/main/resources/textures/brick_wall.png");
            new Triangle(0.1f, 0.1f, 0.5f, 0.0f, "src/main/resources/textures/img.png");

            shader.stop();
            window.update();
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().run();
    }
}