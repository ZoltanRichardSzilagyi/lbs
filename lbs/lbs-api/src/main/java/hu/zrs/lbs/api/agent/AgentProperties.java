package hu.zrs.lbs.api.agent;

public interface AgentProperties<K, V> {

	void addProperty(K key, V value);

	V getProperty(K key);

}
