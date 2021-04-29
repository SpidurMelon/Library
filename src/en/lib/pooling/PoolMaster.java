package en.lib.pooling;

import java.util.HashMap;

public class PoolMaster {
	public static HashMap<String, Pool<? extends Poolable>> pools = new HashMap<String, Pool<? extends Poolable>>();
	public static <S extends Poolable> void addPool(String s, S initialPoolable, int initialCapacity) {
		pools.put(s, new Pool<S>(initialPoolable, initialCapacity));
	}
	
	public static Pool<? extends Poolable> getPool(String s) {
		return pools.get(s);
	}
	
	public static Poolable getObject(String s) {
		return getPool(s).getObject();
	}
	
	public static Poolable getOldestObject(String s) {
		return getPool(s).getOldestObject();
	}
}
