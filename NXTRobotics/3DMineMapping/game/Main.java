
package game;

import components.Camera;

import core.CoreEngine;

public class Main
{
	public static void main(String[] args)
	{
		CoreEngine engine = new CoreEngine(800, 600, 60, new TestGame());
		engine.CreateWindow("3D Game Engine");
		engine.Start();
	}
}
