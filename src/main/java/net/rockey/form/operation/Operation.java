package net.rockey.form.operation;

import java.util.Map;

public interface Operation<T> {
	T execute(Map<String, Object> parameters);
}
