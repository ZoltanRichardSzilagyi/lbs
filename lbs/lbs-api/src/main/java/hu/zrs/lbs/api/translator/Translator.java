package hu.zrs.lbs.api.translator;

public interface Translator<T extends Translatable> {

	String translate(T translatable);

}
