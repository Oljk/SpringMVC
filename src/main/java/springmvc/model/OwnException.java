package springmvc.model;

/**
 * Исключение собственное, чтобы в него потом обернуть исключения, если метод не должен их выбрасывать
 * если будет нужно
 */
public class OwnException extends Exception {
    public OwnException(String s) {
        super(s);
    }
}
