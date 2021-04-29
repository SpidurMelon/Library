package en.lib.pooling;

import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class Pool<T extends Poolable> extends ArrayList<T> {
	private T type;
	
	public <S extends Poolable> Pool(S initialPoolable, int capacity) {
		type = (T)initialPoolable;
		if (capacity > 0) {
			for (int i = 0; i < capacity; i++) {
				add((T)type.createNew());
			}
		} else {
			add((T)type.createNew());
		}
	} 
	
	public int oldestObject = 0;
	public Poolable getObject() {
		for (int i = 0; i < size(); i++) {
			if (!get(i).isEnabled()) {
				return get(i);
			}
		}
		return getOldestObject();
	}
	
	public Poolable getOldestObject() {
		if (oldestObject < size()-1) {
			oldestObject++;
		} else {
			oldestObject = 0;
		}
		return get(oldestObject);
	}
}
