package ua.com.epam.library.util;

import de.cronn.reflection.util.PropertyUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import ua.com.epam.library.util.annotation.Marker;

import java.beans.PropertyDescriptor;
import java.util.Collection;

public class RequestMapper {

    public static <T> T mapToRequest(HttpServletRequest request, Class<T> type) {
        try {
            T instance = type.getConstructor().newInstance();

            Collection<PropertyDescriptor> descriptors = getListOfPropertyDescriptors(instance);

            for (PropertyDescriptor descriptor : descriptors) {
                String fileName = descriptor.getName();
                String requestParameter = request.getParameter(fileName);

                if (descriptor.getPropertyType().equals(Integer.TYPE) || descriptor.getPropertyType().equals(Long.TYPE)) {
                    int value = 0;
                    try {
                        value = Integer.parseInt(requestParameter);
                    } catch (Exception ignored) {
                    }
                    PropertyUtils.writeDirectly(instance, fileName, value);
                    continue;
                }

                if (descriptor.getPropertyType().equals(String.class)) {
                    if (PropertyUtils.hasAnnotationOfProperty(type, descriptor, Marker.class)) {
                        Part part = request.getPart(fileName);
                        PropertyUtils.writeDirectly(instance, fileName, part.getSubmittedFileName());
                        continue;
                    }
                    PropertyUtils.writeDirectly(instance, fileName, requestParameter);
                    continue;
                }
                throw new RuntimeException("Unknown Property");
            }
            return instance;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static <T> Collection<PropertyDescriptor> getListOfPropertyDescriptors(T instance) {
        return PropertyUtils
                .getPropertyDescriptors(instance)
                .stream()
                .filter(d -> !d.getName().equals("class")).toList();
    }
}
