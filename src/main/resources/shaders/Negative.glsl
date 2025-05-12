#version 330 core

in vec2 pass_uvs;

out vec4 out_Color;

uniform sampler2D textureSampler;

void main(){

    vec4 textureColor = texture(textureSampler, pass_uvs);
    vec4 negativeColor = vec4(1.0 - textureColor.rgb, textureColor.a);

    out_Color = texture(textureSampler,pass_uvs);
    out_Color = negativeColor;
}