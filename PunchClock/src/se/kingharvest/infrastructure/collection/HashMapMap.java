package se.kingharvest.infrastructure.collection;

import java.util.HashMap;

public class HashMapMap<K1, K2, V> {

	HashMap<K1, HashMap<K2, V>> _hashMapMap;
	
	public void put(K1 key1, K2 key2, V value)
	{
		if(_hashMapMap == null)
			_hashMapMap = new HashMap<K1, HashMap<K2, V>>();
		
		HashMap<K2, V> mapEntry = null;
		if(!_hashMapMap.containsKey(key1))
		{
			mapEntry = new HashMap<K2, V>();
			_hashMapMap.put(key1, mapEntry);
		}
		else
			mapEntry = _hashMapMap.get(key1);
		
		mapEntry.put(key2, value);
	}
	
	public boolean containsKeys(K1 key1, K2 key2)
	{
		if(_hashMapMap == null)
			return false;
		
		if(!_hashMapMap.containsKey(key1))
			return false;
		
		HashMap<K2, V> mapEntry = _hashMapMap.get(key1);
		return mapEntry.containsKey(key2);
	}

	public V get(K1 key1, K2 key2)
	{
		if(_hashMapMap == null)
			return null;
		
		if(!_hashMapMap.containsKey(key1))
			return null;

		HashMap<K2, V> mapEntry = _hashMapMap.get(key1);
		if(!mapEntry.containsKey(key2))
			return null;
		
		return mapEntry.get(key2);
	}
}
