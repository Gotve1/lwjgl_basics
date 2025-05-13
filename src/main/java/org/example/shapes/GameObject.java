package org.example.shapes;

import org.example.render.Renderer;
import org.example.shader.ShaderTextured;

public class GameObject {
    private final ShaderTextured shader;
    private final Shape shape;
    private final String texturePath;

    public GameObject(ShaderTextured shader, Shape shape, String texturePath) {
        this.shader = shader;
        this.shape = shape;
        this.texturePath = texturePath;
    }

    public void render(Renderer renderer) {
        renderer.render(shape, shader, texturePath);
    }
}