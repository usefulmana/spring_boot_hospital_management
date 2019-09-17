package rmit.spring.hospital.exceptions;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(String message, Long id){
        System.out.println(message + id);
    }
}
