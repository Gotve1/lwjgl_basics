package org.example.shader;

import java.io.IOException;

public class ShaderTextured extends Shader {

    public ShaderTextured(String vertex, String fragment) throws IOException {
        super(vertex, fragment);
    }

    @Override
    public void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
    }

    @Override
    protected void getAllUniformLocations() {}
}