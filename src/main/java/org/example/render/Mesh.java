package org.example.render;

public class Mesh {

    private int vao;
    private int vertices;
    private int texture = 0;

    public Mesh(int vao, int vertices) {
        this.vao = vao;
        this.vertices = vertices;
    }

    public int getVao() {
        return vao;
    }

    public int getVertices() {
        return vertices;
    }

    public Mesh setTexture(int texture) {
        this.texture = texture;
        return this;
    }

    public int getTexture() {
        return texture;
    }
}
