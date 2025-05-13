package org.example.shapes;

import org.example.Main;
import org.example.render.Mesh;
import org.example.render.MeshLoader;
import org.example.render.Texture;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class Triangle implements Shape {

    private float width, height, x, y;
    private Mesh mesh;

    public Triangle(float width, float height, float x, float y) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;

        initMesh();
    }

    private void initMesh() {
        float aspectRatio = (float) Main.getWidth() / Main.getHeight();

        float[] vertices = {
                -width + x, -height * aspectRatio + y, 0.0f,
                 width + x, -height * aspectRatio + y, 0.0f,
                 width + x,  height * aspectRatio + y, 0.0f
        };

        float[] uvCoords = {
                0.0f, 0.0f,
                1.0f, 0.0f,
                1.0f, 1.0f
        };

        int[] indices = {
                0, 1, 2
        };

        mesh = MeshLoader.createMesh(vertices, indices, uvCoords);
    }

    @Override
    public Mesh getMesh() {
        return mesh;
    }
}
