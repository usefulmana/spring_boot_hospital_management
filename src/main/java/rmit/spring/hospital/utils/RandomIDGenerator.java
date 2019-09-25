package rmit.spring.hospital.utils;

import org.apache.commons.lang.RandomStringUtils;


public class RandomIDGenerator {

    public static String generateId(){
        return "p" + RandomStringUtils.random(6, true, true);
    }
}
