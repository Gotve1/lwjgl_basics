package org.example.render;

import org.example.shader.ShaderTextured;
import org.example.shapes.Square;
import java.io.IOException;

public class Render {

    ShaderTextured shader = new ShaderTextured();

    public Render() throws IOException {
    }

    public void cleanup() {
        shader.start();


        Square.renderSquare();
        //Triangle.renderTriangle();


        shader.stop();
    }
}
