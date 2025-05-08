package org.example.render;

import org.example.Main;
import org.example.shader.ShaderTextured;
import org.example.shapes.Square;
import org.example.shapes.Triangle;

import java.io.IOException;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.opengl.GL11.*;

public class Render {

    ShaderTextured shader = new ShaderTextured();

    public Render() throws IOException {
    }

    public void cleanup() {
        shader.start();


        Square.renderSquare();
        Triangle.renderTriangle();


        shader.stop();
    }
}
