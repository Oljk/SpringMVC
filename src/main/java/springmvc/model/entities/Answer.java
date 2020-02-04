package springmvc.model.entities;

public class Answer<T> {
    private String target;
    private boolean status;
    private T object;

    public Answer(String target, boolean status, T object) {
        this.target = target;
        this.status = status;
        this.object = object;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
