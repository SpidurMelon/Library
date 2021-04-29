package en.lib.pooling;

public interface Poolable {
	public Poolable createNew();
	public void enable();
	public void disable();
	public boolean isEnabled();
}
