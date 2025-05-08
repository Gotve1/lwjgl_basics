package org.example.shader;

import java.io.IOException;

public class ShaderTextured extends Shader {

    public ShaderTextured() throws IOException {
        super("src/main/resources/shaders/Vertex.glsl",
                "src/main/resources/shaders/Fragment.glsl");
    }

    @Override
    public void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
    }

    @Override
    protected void getAllUniformLocations() {}
}