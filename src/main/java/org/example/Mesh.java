package org.example;

public class Mesh {

    private int vao;
    private int vertices;

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
}
