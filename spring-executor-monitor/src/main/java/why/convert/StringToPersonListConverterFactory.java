package why.convert;

import com.alibaba.fastjson.TypeReference;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;
import pojo.Person;

import java.util.List;

@Component
public final class StringToPersonListConverterFactory implements ConverterFactory<String, List<Person>> {

    @Override
    public <T extends List<Person>> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToPerson<>(targetType);
    }

    private static final class StringToPerson<T extends List<Person>> implements Converter<String, T> {

        private final Class<T> targetType;

        public StringToPerson(Class<T> targetType) {
            this.targetType = targetType;
        }

        @Override
        public T convert(String source) {
            if (source.isEmpty()) {
                return null;
            }
            return com.alibaba.fastjson.JSON.parseObject(source, new TypeReference<T>() {
            });
        }
    }

}