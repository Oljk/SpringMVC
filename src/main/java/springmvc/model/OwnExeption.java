package springmvc.model;

/**
 * Исключение собственное, чтобы в него потом обернуть исключения, если метод не должен их выбрасывать
 * если будет нужно
 */
public class OwnExeption extends NullPointerException {
    public OwnExeption(String s) {
        super(s);
    }
}
