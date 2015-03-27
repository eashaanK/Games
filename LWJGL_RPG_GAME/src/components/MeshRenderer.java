
package components;

import rendering.*;


public class MeshRenderer extends GameComponent
{
	private Mesh     m_mesh;
	private Material m_material;

	public MeshRenderer(Mesh mesh, Material material)
	{
		this.m_mesh = mesh;
		this.m_material = material;
	}

	@Override
	public void Render(Shader shader, RenderingEngine renderingEngine)
	{
		shader.Bind();
		shader.UpdateUniforms(GetTransform(), m_material, renderingEngine);
		m_mesh.Draw();
	}
}
