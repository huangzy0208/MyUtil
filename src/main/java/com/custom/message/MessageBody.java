package com.custom.message;

public class MessageBody<T> {

    public MessageBody() {
    }

    T bodyElement;

    public MessageBody(final T objElement) {
        this.bodyElement = objElement;
    }

    public T getBodyElement() {
        return this.bodyElement;
    }

    public void setBodyElement(final T bodyElement) {
        this.bodyElement = bodyElement;
    }

    @Override
    public String toString() {
        return "MessageBody{" +
               "bodyElement=" + bodyElement +
               '}';
    }
}
