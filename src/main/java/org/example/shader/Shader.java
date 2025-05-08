package org.example.shader;

import org.example.vector.Matrix4f;
import org.example.vector.Vector3f;
import org.lwjgl.BufferUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL20.*;

public abstract class Shader {

    private int programId;
    private int vertexId;
    private int fragmentId;

    private FloatBuffer matrix = BufferUtils.createFloatBuffer(16);

    public Shader(String Vert, String Frag) throws IOException {
//        URL vertUrl = Shader.class.getResource(Vert);
        File vertFile = new File(String.valueOf(Vert));
        String vertFilePath = vertFile.getAbsolutePath();

//        URL fragUrl = Shader.class.getResource(Frag);
        File fragFile = new File(String.valueOf(Frag));
        String fragFilePath = fragFile.getAbsolutePath();

        vertexId = loadShader(vertFilePath, GL_VERTEX_SHADER);
        fragmentId = loadShader(fragFilePath, GL_FRAGMENT_SHADER);
        programId = glCreateProgram();
        glAttachShader(programId, vertexId);
        glAttachShader(programId, fragmentId);
        bindAttributes();
        glLinkProgram(programId);
        glValidateProgram(programId);
        getAllUniformLocations();
    }

    public void start() {
        glUseProgram(programId);
    }

    public void stop() {
        glUseProgram(0);
    }

    protected abstract void bindAttributes();

    protected abstract void getAllUniformLocations();

    private static int loadShader(String shader, int type) throws IOException {
        StringBuilder shaderSource = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(shader));
            String line;

            while ((line = reader.readLine()) != null) {
                shaderSource.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            throw new IOException("Could not read file: " + shader, e);
        }
        int ID = glCreateShader(type);
        glShaderSource(ID, shaderSource);
        glCompileShader(ID);

        if(glGetShaderi(ID, GL_COMPILE_STATUS)==GL_FALSE) {
            System.out.println(glGetShaderInfoLog(ID, 512));
            System.err.println("Couldn't compile the shader");
            System.exit(-1);
        }
        return ID;
    }

    protected int getUniformLocation(String uniformName) {
        return glGetUniformLocation(programId, uniformName);
    }

    protected void bindAttribute(int attribute, String variableName) {
        glBindAttribLocation(programId, attribute, variableName);
    }

    protected void loadFloat(int location, float value) {
        glUniform1f(location, value);
    }

    protected void loadVector(int location, Vector3f vector) {
        glUniform3f(location, vector.x, vector.y, vector.z);
    }

    protected void loadBoolean(int location, boolean value) {
        float tovec = 0;
        if(value) {
            tovec = 1;
        }
        glUniform1f(location, tovec);
    }

    protected void loadMatrix(int location, Matrix4f value) {
        value.store(matrix);
        matrix.flip();
        glUniformMatrix4fv(location, false, matrix);
    }
}
