package hu.zrs.lbs.translator.gradle.resolver;

import java.util.Map;

import hu.zrs.lbs.api.task.Task;
import hu.zrs.lbs.api.translator.Translatable;
import hu.zrs.lbs.api.translator.Translator;
import hu.zrs.lbs.api.translator.resolver.TranslatorResolver;

public class GradleTranslatorResolver implements TranslatorResolver {

	private final Map<Class<?>, Translator<?>> registeredTranslators;

	public GradleTranslatorResolver(Map<Class<?>, Translator<?>> registeredTranslators) {
		this.registeredTranslators = registeredTranslators;
	}

	@Override
	public Translator<?> resolve(Translatable translatable) {
		Translator<?> translator = null;
		translator = registeredTranslators.get(translatable.getClass());
		if (translator == null && translatable instanceof Task) {
			translator = resolveDefaultTaskTranslator();
		}
		return translator;
	}

	private Translator<?> resolveDefaultTaskTranslator() {
		return registeredTranslators.get(Task.class);
	}

}
