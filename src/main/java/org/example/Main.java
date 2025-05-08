package org.example;

import org.example.render.Render;
import org.example.render.Window;
import org.example.shader.ShaderTextured;

import java.io.IOException;

import static org.lwjgl.opengl.GL.createCapabilities;
import static org.lwjgl.opengl.GL11.*;

public class Main {
    public static Window window;
    public static int width = 600;
    public static int height = 400;

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public void run() throws IOException {
        window = new Window(width, height, "OpenGL Window");
        loop();
        window.terminate();
    }

    private void loop() throws IOException {
        createCapabilities();

        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        ShaderTextured shader = new ShaderTextured();
        Render render = new Render();
        shader.bindAttributes();

        while (!window.shouldClose()) {

            render.cleanup();

            window.update();
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().run();
    }
}