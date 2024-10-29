package com.example.springsqsrediselasticache.model;

import lombok.Builder;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Builder
public class Message implements Serializable {
    @Serial
    private static final long serialVersionUID = 5L;

    @Override
    public String toString() {
        return "Message{" +
                "content='" + content + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message Message = (Message) o;
        return Objects.equals(content, Message.content) &&
                Objects.equals(id, Message.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, id);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Message() {
    }

    private String content;
    private Integer id;

    public Message(String content, Integer id) {
        this.content = content;
        this.id = id;
    }
}
