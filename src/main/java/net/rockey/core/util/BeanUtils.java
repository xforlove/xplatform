package net.rockey.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * ��չApache Commons BeanUtils, �ṩһЩ���䷽��ȱʧ���ܵķ�װ.
 */
public class BeanUtils /* extends org.apache.commons.beanutils.BeanUtils */{

	/** logger. */
	private static Logger logger = LoggerFactory.getLogger(BeanUtils.class);

	/** getter prefix length. */
	public static final int LENGTH_GETTER_PREFIX = "get".length();

	/** �����Ĺ��췽��. */
	protected BeanUtils() {
	}

	/**
	 * ѭ������ת��,��ȡ�����DeclaredField.
	 * 
	 * @param object
	 *            ����ʵ��
	 * @param propertyName
	 *            ������
	 * @return ���ض�Ӧ��Field
	 * @throws NoSuchFieldException
	 *             ���û�и�Fieldʱ�׳�
	 */
	public static Field getDeclaredField(Object object, String propertyName)
			throws NoSuchFieldException {
		Assert.notNull(object);
		Assert.hasText(propertyName);
		return getDeclaredField(object.getClass(), propertyName);
	}

	/**
	 * ѭ������ת��,��ȡ�����DeclaredField.
	 * 
	 * @param clazz
	 *            ����
	 * @param propertyName
	 *            ������
	 * @return ���ض�Ӧ��Field
	 * @throws NoSuchFieldException
	 *             ���û�и�Fieldʱ�׳�.
	 */
	public static Field getDeclaredField(Class clazz, String propertyName)
			throws NoSuchFieldException {
		Assert.notNull(clazz);
		Assert.hasText(propertyName);

		for (Class superClass = clazz; superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredField(propertyName);
			} catch (NoSuchFieldException ex) {
				// Field���ڵ�ǰ�ඨ��,��������ת��
				logger.debug(ex.getMessage(), ex);
			}
		}

		throw new NoSuchFieldException("No such field: " + clazz.getName()
				+ '.' + propertyName);
	}

	/**
	 * ������ȡ�������ֵ,����private,protected���η������.
	 * 
	 * @param object
	 *            ����ʵ��
	 * @param propertyName
	 *            ������
	 * @return ǿ�ƻ������ֵ
	 * @throws NoSuchFieldException
	 *             ���û�и�Fieldʱ�׳�.
	 */
	public static Object forceGetProperty(Object object, String propertyName)
			throws NoSuchFieldException, IllegalAccessException {
		return getFieldValue(object, propertyName, true);
	}

	public static Object safeGetFieldValue(Object object, String fieldName) {
		return safeGetFieldValue(object, fieldName, true);
	}

	public static Object safeGetFieldValue(Object object, String fieldName,
			boolean targetAccessible) {
		try {
			return getFieldValue(object, fieldName, targetAccessible);
		} catch (NoSuchFieldException ex) {
			logger.warn("", ex);
		} catch (IllegalAccessException ex) {
			logger.warn("", ex);
		}
		return null;
	}

	public static Object getFieldValue(Object object, String fieldName)
			throws NoSuchFieldException, IllegalAccessException {
		return getFieldValue(object, fieldName, false);
	}

	public static Object getFieldValue(Object object, String fieldName,
			boolean targetAccessible) throws NoSuchFieldException,
			IllegalAccessException {
		Assert.notNull(object);
		Assert.hasText(fieldName);
		Field field = getDeclaredField(object, fieldName);
		boolean accessible = field.isAccessible();
		field.setAccessible(targetAccessible);
		Object result = field.get(object);
		field.setAccessible(accessible);
		return result;
	}

	/**
	 * �������ö������ֵ,����private,protected���η������.
	 * 
	 * @param object
	 *            ����ʵ��
	 * @param propertyName
	 *            ������
	 * @param newValue
	 *            ���������ֵ
	 * @throws NoSuchFieldException
	 *             ���û�и�Fieldʱ�׳�.
	 */
	public static void forceSetProperty(Object object, String propertyName,
			Object newValue) throws NoSuchFieldException,
			IllegalAccessException {
		setFieldValue(object, propertyName, newValue, true);
	}

	public static void safeSetFieldValue(Object object, String fieldName,
			Object newValue) {
		safeSetFieldValue(object, fieldName, newValue, true);
	}

	public static void safeSetFieldValue(Object object, String fieldName,
			Object newValue, boolean targetAccessible) {
		try {
			setFieldValue(object, fieldName, newValue, targetAccessible);
		} catch (NoSuchFieldException ex) {
			logger.warn("", ex);
		} catch (IllegalAccessException ex) {
			logger.warn("", ex);
		}
	}

	public static void setFieldValue(Object object, String propertyName,
			Object newValue, boolean targetAccessible)
			throws NoSuchFieldException, IllegalAccessException {
		Assert.notNull(object);
		Assert.hasText(propertyName);
		Field field = getDeclaredField(object, propertyName);
		boolean accessible = field.isAccessible();
		field.setAccessible(targetAccessible);
		field.set(object, newValue);
		field.setAccessible(accessible);
	}

	/**
	 * �������ö�����,����private,protected���η������.
	 * 
	 * @param object
	 *            ����ʵ��
	 * @param methodName
	 *            ������
	 * @param params
	 *            ��������
	 * @return Object �������÷��صĽ�����
	 * @throws NoSuchMethodException
	 *             ���û�и�Methodʱ�׳�.
	 */
	public static Object invokePrivateMethod(Object object, String methodName,
			Object... params) throws NoSuchMethodException,
			IllegalAccessException, InvocationTargetException {
		return invokeMethod(object, methodName, true, params);
	}

	public static Object safeInvokeMethod(Object object, Method method,
			Object... params) {
		try {
			return method.invoke(object, params);
		} catch (IllegalAccessException ex) {
			logger.warn("", ex);
		} catch (InvocationTargetException ex) {
			logger.warn("", ex);
		}
		return null;
	}

	public static Object safeInvokeMethod(Object object, String methodName,
			Object... params) {
		try {
			return invokeMethod(object, methodName, params);
		} catch (NoSuchMethodException ex) {
			logger.warn("", ex);
		} catch (IllegalAccessException ex) {
			logger.warn("", ex);
		} catch (InvocationTargetException ex) {
			logger.warn("", ex);
		}
		return null;
	}

	public static Object invokeMethod(Object object, String methodName,
			Object... params) throws NoSuchMethodException,
			IllegalAccessException, InvocationTargetException {
		return invokeMethod(object, methodName, false, params);
	}

	public static Object invokeMethod(Object object, String methodName,
			boolean targetAccessible, Object... params)
			throws NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		Assert.notNull(object);
		Assert.hasText(methodName);
		Class[] types = new Class[params.length];
		for (int i = 0; i < params.length; i++) {
			types[i] = params[i].getClass();
		}
		Class clazz = object.getClass();
		Method method = null;
		for (Class superClass = clazz; superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				method = superClass.getDeclaredMethod(methodName, types);
				break;
			} catch (NoSuchMethodException ex) {
				// �������ڵ�ǰ�ඨ��,��������ת��
				logger.debug(ex.getMessage(), ex);
			}
		}
		if (method == null) {
			throw new NoSuchMethodException("No Such Method : "
					+ clazz.getSimpleName() + "." + methodName
					+ Arrays.asList(types));
		}
		boolean accessible = method.isAccessible();
		method.setAccessible(targetAccessible);
		Object result = method.invoke(object, params);
		method.setAccessible(accessible);
		return result;
	}

	/**
	 * ��Field������ȡ��Field�б�.
	 * 
	 * @param object
	 *            ����ʵ��
	 * @param type
	 *            ����
	 * @return ���Զ����б�
	 */
	public static List<Field> getFieldsByType(Object object, Class type) {
		List<Field> list = new ArrayList<Field>();
		Field[] fields = object.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (field.getType().isAssignableFrom(type)) {
				list.add(field);
			}
		}
		return list;
	}

	/**
	 * ��FieldName���Field������.
	 * 
	 * @param type
	 *            ����
	 * @param name
	 *            ������
	 * @return ���Ե�����
	 * @throws NoSuchFieldException
	 *             ָ�����Բ�����ʱ���׳��쳣
	 */
	public static Class getPropertyType(Class type, String name)
			throws NoSuchFieldException {
		return getDeclaredField(type, name).getType();
	}

	/**
	 * ���field��getter�������.
	 * 
	 * @param type
	 *            ����
	 * @param fieldName
	 *            ������
	 * @return getter������
	 * @throws NoSuchFieldException
	 *             field������ʱ�׳��쳣
	 * 
	 * @todo: ʹ��reflectUtils��ķ�������ʣ������ʵ�ַ�ʽ����������field������method���߼���������
	 *        ʵ���ϣ���ʹû��fieldҲ���Ե�����method��
	 */
	public static String getGetterName(Class type, String fieldName)
			throws NoSuchFieldException {
		Assert.notNull(type, "Type required");
		Assert.hasText(fieldName, "FieldName required");
		Class fieldType = getDeclaredField(type, fieldName).getType();
		if ((fieldType == boolean.class) || (fieldType == Boolean.class)) {
			return "is" + StringUtils.capitalize(fieldName);
		} else {
			return "get" + StringUtils.capitalize(fieldName);
		}
	}

	/**
	 * ���field��getter����,����Ҳ����÷���,����null.
	 * 
	 * @param type
	 *            ����
	 * @param fieldName
	 *            ������
	 * @return getter��������
	 */
	public static Method getGetterMethod(Class type, String fieldName) {
		try {
			return type.getMethod(getGetterName(type, fieldName));
		} catch (NoSuchMethodException ex) {
			logger.error(ex.getMessage(), ex);
		} catch (NoSuchFieldException ex) {
			logger.error(ex.getMessage(), ex);
		}
		return null;
	}

	public static String getFieldName(String methodName) {
		String fieldName = methodName.substring(LENGTH_GETTER_PREFIX);
		return fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1);
	}
}
