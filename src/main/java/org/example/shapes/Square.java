package org.example.shapes;

import org.example.Main;
import org.example.render.Mesh;
import org.example.render.MeshLoader;
import org.example.render.Texture;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class Square {
    private static int textureId;
    private static boolean initialized = false;

    public static void init() {
        if (!initialized) {
            textureId = Texture.loadTexture("src/main/resources/textures/brick_wall.png");
            initialized = true;
        }
    }

    public static void renderSquare() {
        float aspectRatio = (float) Main.getWidth() / Main.getHeight();
        float[] vertices = {
                -0.5f, -0.5f * aspectRatio, 0.0f,  // Bottom-left
                0.5f, -0.5f * aspectRatio, 0.0f,  // Bottom-right
                0.5f,  0.5f * aspectRatio, 0.0f,  // Top-right
                -0.5f,  0.5f * aspectRatio, 0.0f   // Top-left
        };

        float[] uvCoords = {
                0.0f, 0.0f,
                1.0f, 0.0f,
                1.0f, 1.0f,
                0.0f, 1.0f
        };

        int[] indices = {
                0, 1, 2, 3
        };

        Mesh mesh = MeshLoader.createMesh(vertices, indices, uvCoords);

        glBindVertexArray(mesh.getVao());
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        Texture.bind(textureId);
        glDrawElements(GL_TRIANGLE_FAN, mesh.getVertices(), GL_UNSIGNED_INT, 0);
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glBindVertexArray(0);

    }
}