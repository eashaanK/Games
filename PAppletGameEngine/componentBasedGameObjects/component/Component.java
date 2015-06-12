package component;

public interface Component {
	public GameObject gameObject = null;
	public void input();
	public void update();
	public void close();
}
