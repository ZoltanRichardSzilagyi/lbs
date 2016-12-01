package hu.zrs.lbs.agent.mapper;

public interface EntityMapper<S, T> {

	T map(S sourceEntity);

}
