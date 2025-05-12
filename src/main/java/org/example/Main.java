package org.example;

import org.example.render.Window;
import org.example.shader.ShaderTextured;
import org.example.shapes.GameObject;
import org.example.shapes.Square;
import org.example.shapes.Triangle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL.createCapabilities;
import static org.lwjgl.opengl.GL11.*;

public class Main {

    private static Window window;
    private static int width = 600;
    private static int height = 400;
    private static String title = "OpenGL Window";
    private List<GameObject> gameObjects;

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public void run() throws IOException {
        window = new Window(width, height, title);
        window.alignWindowCenter();
        initialize();
        loop();
        window.terminate();
    }

    private void initialize() throws IOException {
        createCapabilities();
        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        ShaderTextured normalShader = new ShaderTextured("src/main/resources/shaders/Vertex.glsl",
                "src/main/resources/shaders/Fragment.glsl");

        ShaderTextured negativeShader = new ShaderTextured("src/main/resources/shaders/Vertex.glsl",
                "src/main/resources/shaders/Negative.glsl");

        normalShader.bindAttributes();
        negativeShader.bindAttributes();

        gameObjects = new ArrayList<>();

        gameObjects.add(new GameObject(
                normalShader,
                new Square(0.1f, 0.1f, 0.1f, 0.0f),
                "src/main/resources/textures/brick_wall.png"
        ));

        gameObjects.add(new GameObject(
                negativeShader,
                new Triangle(0.1f, 0.1f, 0.5f, 0.0f),
                "src/main/resources/textures/brick_wall.png"
        ));
    }


    private void loop() throws IOException {
        while (!window.shouldClose()) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            for (GameObject gameObject : gameObjects) {
                gameObject.render();
            }

            window.update();
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().run();
    }
}