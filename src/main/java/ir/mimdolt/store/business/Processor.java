package ir.mimdolt.store.business;

/**
 * Created by Hadi Jeddi Zahed on 11/11/2016.
 */
public class Processor {
    public static <T> T createInstance(Class clazz) throws InstantiationException, IllegalAccessException{
        T t = (T) clazz.newInstance();
        return t;
    }
}
