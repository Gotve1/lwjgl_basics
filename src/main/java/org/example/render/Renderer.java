package org.example.render;

import org.example.shapes.Shape;
import org.example.shader.ShaderTextured;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class Renderer {
    private static final Map<String, Integer> textureCache = new HashMap<>();
    
    public void render(Shape shape, ShaderTextured shader, String texturePath) {
        shader.start();
        
        // Get or load texture
        int textureId = textureCache.computeIfAbsent(texturePath, Texture::loadTexture);
        
        // Bind and render
        glBindVertexArray(shape.getMesh().getVao());
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        Texture.bind(textureId);
        glDrawElements(GL_TRIANGLE_FAN, shape.getMesh().getVertices(), GL_UNSIGNED_INT, 0);
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glBindVertexArray(0);
        
        shader.stop();
    }
    
    public void cleanup() {
        // Clean up all cached textures
        textureCache.values().forEach(textureId -> {
            glDeleteTextures(textureId);
        });
        textureCache.clear();
    }
}