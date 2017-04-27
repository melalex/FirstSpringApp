package ua.room414.spring.core.beans;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;

/**
 * @author Alexander Melashchenko
 * @version 1.0 26 Apr 2017
 */
@Component
public class Event {
    private long id;
    private String message;
    private Date date;
    private DateFormat dateFormat;

    public Event() {
    }

    public Event(Date date, DateFormat dateFormat) {
        this.date = date;
        this.dateFormat = dateFormat;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (id != event.id) return false;
        if (message != null ? !message.equals(event.message) : event.message != null) return false;
        return date != null ? date.equals(event.date) : event.date == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", date=" + dateFormat.format(date) +
                '}';
    }
}
