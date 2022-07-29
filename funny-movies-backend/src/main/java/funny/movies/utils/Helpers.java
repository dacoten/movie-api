package funny.movies.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.TreeMap;

public class Helpers {
	// Copy one class's fields into another class's identical fields
	public static void copy(final Object from, final Object to) {
		Map<String, Field> fromFields = analyze(from);
		Map<String, Field> toFields = analyze(to);
		fromFields.keySet().retainAll(toFields.keySet());
		for (Map.Entry<String, Field> fromFieldEntry : fromFields.entrySet()) {
			final String name = fromFieldEntry.getKey();
			final Field sourceField = fromFieldEntry.getValue();
			final Field targetField = toFields.get(name);
			if (targetField.getType().isAssignableFrom(sourceField.getType())) {
				sourceField.setAccessible(true);
				if (Modifier.isFinal(targetField.getModifiers()))
					continue;
				targetField.setAccessible(true);
				try {
					targetField.set(to, sourceField.get(from));
				} catch (IllegalAccessException e) {
					throw new IllegalStateException("Can't access field!");
				}
			}
		}
	}

	private static Map<String, Field> analyze(Object object) {
		if (object == null)
			throw new NullPointerException();

		Map<String, Field> map = new TreeMap<String, Field>();

		Class<?> current = object.getClass();
		while (current != Object.class) {
			for (Field field : current.getDeclaredFields()) {
				if (!Modifier.isStatic(field.getModifiers())) {
					if (!map.containsKey(field.getName())) {
						map.put(field.getName(), field);
					}
				}
			}
			current = current.getSuperclass();
		}
		return map;
	}

}
