package hu.zrs.lbs.api.translator.resolver;

import hu.zrs.lbs.api.translator.Translatable;
import hu.zrs.lbs.api.translator.Translator;

public interface TranslatorResolver {

	<T extends Translatable> Translator<T> resolve(T translatable);

}
