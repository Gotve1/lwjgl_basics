package org.example.shapes;

import org.example.Main;
import org.example.render.Mesh;
import org.example.render.MeshLoader;
import org.example.render.Texture;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class Triangle {
    private static int textureId;
    private static boolean initialized = false;

    public Triangle(float width, float height, float x, float y, String texturePath) {
        int textureId = Texture.loadTexture(texturePath);
        float aspectRatio = (float) Main.getWidth() / Main.getHeight();


        float[] vertices = {
            -width + x, -height * aspectRatio, 0.0f,
             width + x, -height * aspectRatio, 0.0f,
             width + x,  height * aspectRatio, 0.0f
        };

        float[] uvCoords = {
            0.0f, 0.0f,
            1.0f, 0.0f,
            1.0f, 1.0f
        };

        int[] indices = {
            0, 1, 2
        };

        Mesh mesh = MeshLoader.createMesh(vertices, indices, uvCoords);

        glBindVertexArray(mesh.getVao());
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        Texture.bind(textureId);
        glDrawElements(GL_TRIANGLES, mesh.getVertices(), GL_UNSIGNED_INT, 0);
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glBindVertexArray(0);
    }

}
