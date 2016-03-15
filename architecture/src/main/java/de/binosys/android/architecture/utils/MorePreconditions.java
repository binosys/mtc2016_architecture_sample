/*
 * Copyright Binosys GmbH(c)2015.All rights reserved.
 *
 * Licensed under the Apache License,Version2.0(the"License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,software
 * distributed under the License is distributed on an"AS IS"BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.binosys.android.architecture.utils;


import android.app.Application;
import android.content.Context;
import android.net.Uri;

import java.util.Collection;
import java.util.Map;

import javax.annotation.Nonnull;


/**
 * Extension of guava {@link Preconditions} class.
 *
 */
public final class MorePreconditions
{

	private final static String EMPTY_STRING	= "";


	/**
	 * @param expression
	 */
	public static void checkArgument(boolean expression)
	{
		Preconditions.checkArgument(expression);
	}


	/**
	 * @param expression
	 * @param errorMessage
	 */
	public static void checkArgument(boolean expression, Object errorMessage)
	{
		Preconditions.checkArgument(expression, errorMessage);
	}


	/**
	 * @param expression
	 * @param errorMessageTemplate
	 * @param errorMessageArgs
	 */
	public static void checkArgument(boolean expression, String errorMessageTemplate,
			Object... errorMessageArgs)
	{
		Preconditions.checkArgument(expression, errorMessageTemplate, errorMessageArgs);
	}


	/**
	 * Checks that {@link Collection} contains T.
	 * 
	 * @param set
	 *            not null
	 * @param value
	 *            not null
	 */
	public static <T> void checkContains(@Nonnull Collection<T> set, @Nonnull T value)
	{
		Preconditions.checkNotNull(set, "Collection");
		Preconditions.checkNotNull(value, "value");

		if (!set.contains(value))
		{
			throw new IllegalStateException("Collection does not contain :" + value);
		}
	}


	/**
	 * Ensures that a {@link Map} contains K
	 * 
	 * @param map
	 * @param key
	 */
	public static <K, V> void checkContainsKey(@Nonnull Map<K, V> map, @Nonnull K key)
	{
		Preconditions.checkNotNull(map, "Map");
		Preconditions.checkNotNull(key, "key");

		if (!map.containsKey(key))
		{
			throw new IllegalStateException("Map does not contain key:" + key);
		}
	}


	/**
	 * Ensures that a {@link Map} contains V
	 * 
	 * @param map
	 * @param value
	 */
	public static <K, V> void checkContainsValue(@Nonnull Map<K, V> map, @Nonnull V value)
	{
		Preconditions.checkNotNull(map, "Map");
		Preconditions.checkNotNull(value, "value");

		if (!map.containsValue(value))
		{
			throw new IllegalStateException("Map does not contain value:" + value);
		}
	}


	/**
	 * @param index
	 * @param size
	 * @return
	 */
	public static int checkElementIndex(int index, int size)
	{
		return Preconditions.checkElementIndex(index, size);
	}


	/**
	 * @param index
	 * @param size
	 * @param desc
	 * @return
	 */
	public static int checkElementIndex(int index, int size, String desc)
	{
		return Preconditions.checkElementIndex(index, size, desc);
	}


	/**
	 * Checks if an {@link Object} is an instance if a given {@link Class}
	 * 
	 * @param <T>
	 * @param objectToCheck
	 * @param clazzOfInstance
	 * @return
	 */
	public static <T> void checkInstance(@Nonnull Object objectToCheck, @Nonnull Class<T> clazzOfInstance)
	{
		checkNotNull(objectToCheck, "Object objectToCheck");
		checkNotNull(clazzOfInstance, "Class<T> clazzOfInstance");
		if (!clazzOfInstance.isAssignableFrom(objectToCheck.getClass()))
		{
			throw new IllegalStateException();
		}
	}


	/**
	 * Checks if an {@link Object} is an instance if a given {@link Class}
	 * 
	 * @param <T>
	 * @param objectToCheck
	 * @param clazzOfInstance
	 * @param message
	 * @return
	 */
	public static <T> void checkInstance(@Nonnull Object objectToCheck, @Nonnull Class<T> clazzOfInstance,
										 @Nonnull String message)
	{
		checkNotNull(message, "String message");
		checkArgument(!"".equals(message), "String message is empty.");
		checkNotNull(objectToCheck, "Object objectToCheck");
		checkNotNull(clazzOfInstance, "Class<T> clazzOfInstance");
		if (!clazzOfInstance.isAssignableFrom(objectToCheck.getClass()))
		{
			throw new IllegalStateException(message);
		}
	}


	/**
	 * Checks if current {@link Context} is an instance of {@link Application} context.
	 */
	public static void checkIsApplicationContext(Context context)
	{
		checkNotNull(context);
		if (!(context instanceof Application))
		{
			throw new IllegalStateException("Context is not an Application Context");
		}
	}


	/**
	 * Ensures that a {@link Map} not contains K
	 * 
	 * @param map
	 * @param key
	 */
	public static <K, V> void checkNotContainsKey(@Nonnull Map<K, V> map, @Nonnull K key)
	{
		Preconditions.checkNotNull(map, "Map");
		Preconditions.checkNotNull(key, "key");

		if (map.containsKey(key))
		{
			throw new IllegalStateException("Map does contain key:" + key);
		}
	}


	/**
	 * Ensures that a {@link Map} does not contain V
	 * 
	 * @param map
	 * @param value
	 */
	public static <K, V> void checkNotContainsValue(@Nonnull Map<K, V> map, @Nonnull V value)
	{
		Preconditions.checkNotNull(map, "Map");
		Preconditions.checkNotNull(value, "value");

		if (map.containsValue(value))
		{
			throw new IllegalStateException("Map does contain value:" + value);
		}
	}


	/**
	 * Checks if {@link Collection} is empty.
	 * 
	 * @param collection
	 */
	public static <T> void checkNotEmpty(@Nonnull Collection<T> collection)
	{
		Preconditions.checkNotNull(collection, "collection");

		if (collection.isEmpty())
		{
			throw new IllegalStateException("Collection is empty");
		}
	}


	/**
	 * Start of Addons
	 */

	/**
	 * Checks if {@link Collection} is empty.
	 * 
	 * @param collection
	 * @param message
	 */
	public static <T> void checkNotEmpty(@Nonnull Collection<T> collection, String message)
	{
		Preconditions.checkNotNull(collection, "collection");

		if (collection.isEmpty())
		{
			throw new IllegalStateException(String.valueOf(message));
		}
	}


	/**
	 * Checks if {@link String} is not <code>null</code> and empty and contains no whitespace (trim).
	 * 
	 * @param value
	 */
	public static <T> void checkNotEmpty(@Nonnull String value, @Nonnull String message)
	{
		Preconditions.checkNotNull(value, "String");
		if (EMPTY_STRING.equals(value))
		{
			throw new IllegalArgumentException("String is empty");
		}
		if (value.trim().length() == 0)
		{
			throw new IllegalArgumentException(String.valueOf(message));
		}
	}


	/**
	 * @param reference
	 * @return
	 */
	public static <T> T checkNotNull(T reference)
	{
		return Preconditions.checkNotNull(reference);
	}


	/**
	 * @param reference
	 * @param errorMessage
	 * @return
	 */
	public static <T> T checkNotNull(T reference, Object errorMessage)
	{
		return Preconditions.checkNotNull(reference, errorMessage);
	}


	/**
	 * @param reference
	 * @param errorMessageTemplate
	 * @param errorMessageArgs
	 * @return
	 */
	public static <T> T checkNotNull(T reference, String errorMessageTemplate,
			Object... errorMessageArgs)
	{
		return Preconditions.checkNotNull(reference, errorMessageTemplate, errorMessageArgs);
	}


	/**
	 * @param index
	 * @param size
	 * @return
	 */
	public static int checkPositionIndex(int index, int size)
	{
		return Preconditions.checkPositionIndex(index, size);
	}


	/**
	 * @param index
	 * @param size
	 * @param desc
	 * @return
	 */
	public static int checkPositionIndex(int index, int size, String desc)
	{
		return Preconditions.checkPositionIndex(index, size, desc);
	}


	/**
	 * @param start
	 * @param end
	 * @param size
	 */
	public static void checkPositionIndexes(int start, int end, int size)
	{
		Preconditions.checkPositionIndexes(start, end, size);
	}


	/**
	 * Checks that {@link Uri} has schema 'content://'.
	 * 
	 * @param uri
	 */
	public static <T> void checkSchemaIsContent(@Nonnull Uri uri)
	{
		Preconditions.checkNotNull(uri, "uri");
		if (!uri.getScheme().equalsIgnoreCase("content://"))
		{
			throw new IllegalArgumentException("Wrong scheme, need content:// but was '" + uri.getScheme() + "'");
		}
	}


	/**
	 * @param expression
	 */
	public static void checkState(boolean expression)
	{
		Preconditions.checkState(expression);
	}


	/**
	 * @param expression
	 * @param errorMessage
	 */
	public static void checkState(boolean expression, Object errorMessage)
	{
		Preconditions.checkState(expression, errorMessage);
	}


	/**
	 * @param expression
	 * @param errorMessageTemplate
	 * @param errorMessageArgs
	 */
	public static void checkState(boolean expression, String errorMessageTemplate,
			Object... errorMessageArgs)
	{
		Preconditions.checkState(expression, errorMessageTemplate, errorMessageArgs);
	}


	/**
	 * Checks that String starts with given string.<br>
	 * Method is case sensitive!
	 * 
	 * @param <T>
	 * @param startOfString
	 * @param toCheck
	 *            eg http://
	 */
	public static <T> void checkStringStartsWith(@Nonnull String startOfString, @Nonnull String toCheck)
	{
		Preconditions.checkNotNull(startOfString, "start of string");
		checkNotEmpty(startOfString, "start of string");
		Preconditions.checkNotNull(toCheck, "string to check");
		checkNotEmpty(toCheck, "string to check");

		if (toCheck.startsWith(startOfString))
		{
			return;
		}
		throw new IllegalArgumentException("Expected that '" + toCheck + "' starts with '" + startOfString + "'");

	}


	/**
	 * 
	 */
	private MorePreconditions()
	{
	}

}
