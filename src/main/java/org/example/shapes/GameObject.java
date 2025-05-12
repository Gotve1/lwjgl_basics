package org.example.shapes;

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

    public void render() {
        shader.start();
        shape.render(texturePath);
        shader.stop();
    }
}