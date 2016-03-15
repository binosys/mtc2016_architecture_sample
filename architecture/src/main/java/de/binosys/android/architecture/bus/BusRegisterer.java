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
package de.binosys.android.architecture.bus;

import android.support.annotation.VisibleForTesting;

import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;



/**
 * This class is a helper for registering all members (recursively) of an object which are type annotated with
 *
 * @BusObserver <p/>
 * It also caches the effected members during register(). Hence unregister() can be executed much faster.
 * <p/>
 */
@Singleton
public class BusRegisterer {

    IBusSystem bus;

    private List<String> targetPackages;

    private Set<Class<?>> observers;

    private Map<Object, List<Object>> registrations = new HashMap<>();

    /**
     * @param targetPackages list of packages which shall be processed. This is generally your application root package and libraries that use @BusObserver.
     * @param bus            to which all @BusObserver(s) are (un)registered
     */
    @Inject
    public BusRegisterer(List<String> targetPackages, IBusSystem bus) {

        this.bus = bus;
        this.targetPackages = targetPackages;
        this.observers = new HashSet<>();

        for (String targetPackage : targetPackages) {
            prefetchAllObserversOf(targetPackage);
        }
    }


    /**
     * This method pre-fetches all possible observers in the target package.
     * This is used later on in register() to avoid reflection calls for checking the type annotation of fields.
     *
     * @param targetPackage
     */
    private void prefetchAllObserversOf(String targetPackage) {

        List<Class<?>> clazzs = new ArrayList<>();
        Reflections reflections = new Reflections(targetPackage);
        Set<Class<?>> set = reflections.getTypesAnnotatedWith(BusObserver.class);
        clazzs.addAll(set);

        observers.addAll(clazzs);
    }

    public void register(Object object) {

        List<Object> registrationList = new ArrayList<>();
        Map<Object, Object> alreadyCheckedObjects = new HashMap<>();
        register(object, registrationList, alreadyCheckedObjects);

        registrations.put(object, registrationList);
    }

    private void register(Object object, List<Object> registrationList, Map<Object, Object> alreadyChecked) {

        alreadyChecked.put(object, object);
        if (object.getClass().isAnnotationPresent(BusObserver.class)) {
            bus.register(object);
            registrationList.add(object);
        }

        handleFieldsOf(object, registrationList, alreadyChecked);
    }

    private void handleFieldsOf(Object object, List<Object> registrationList, Map<Object, Object> alreadyChecked) {

        Field[] fields = object.getClass().getDeclaredFields();

        Object fieldInstance;
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                fieldInstance = field.get(object);

                if (fieldInstance != null && !alreadyChecked.containsKey(fieldInstance)) {

                    String fieldInstanceName = fieldInstance.getClass().getName();

                    if (isRelevant(fieldInstanceName)) {
                        register(fieldInstance, registrationList, alreadyChecked);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isRelevant(String fieldInstanceName) {

        boolean isRelevant = false;

        for (String targetPackage : targetPackages) {
            if (fieldInstanceName.contains(targetPackage)) {
                isRelevant = true;
                break;
            }
        }
        return isRelevant;
    }


    public void unregister(Object object) {

        List<Object> registrationList = registrations.get(object);
        for (Object registeredObject : registrationList) {
            bus.unregister(registeredObject);
        }
        registrations.remove(object);
    }

    @VisibleForTesting
    Set<Class<?>> getObservers() {

        return observers;
    }

    @VisibleForTesting
    Map<Object, List<Object>> getRegistrations() {

        return registrations;
    }

}
