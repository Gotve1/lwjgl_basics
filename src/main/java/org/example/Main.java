package org.example;

import org.example.render.Render;
import org.example.render.Window;
import org.example.shader.ShaderTextured;
import org.example.shapes.Square;
import org.example.shapes.Triangle;
import org.lwjgl.glfw.GLFWErrorCallback;

import java.io.IOException;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL.createCapabilities;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Main {
    public static Window window;

    public void run() throws IOException {
        window = new Window(600, 400, "OpenGL Window");
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