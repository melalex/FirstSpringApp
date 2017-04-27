package ua.room414.spring.core.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author Alexander Melashchenko
 * @version 1.0 26 Apr 2017
 */
@Component
@PropertySource("classpath:client.properties")
public class Client {
    private long id;
    private String fullName;
    private String greeting;

    public Client() {
    }

    @Autowired
    public Client(@Value("${id}") long id, @Value("${fullName}") String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGreeting() {
        return greeting;
    }

    @Autowired
    public void setGreeting(@Value("${greeting}") String greeting) {
        this.greeting = greeting;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (id != client.id) return false;
        if (fullName != null ? !fullName.equals(client.fullName) : client.fullName != null) return false;
        return greeting != null ? greeting.equals(client.greeting) : client.greeting == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (greeting != null ? greeting.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", greeting='" + greeting + '\'' +
                '}';
    }
}
