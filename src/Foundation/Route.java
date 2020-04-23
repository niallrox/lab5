package Foundation;

import java.time.LocalDate;

public class Route implements Comparable<Route> {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Location from; //Поле может быть null
    private Location to; //Поле не может быть null
    private Long distance; //Поле не может быть null, Значение поля должно быть больше 1
    public Route(long id, String name, Coordinates coordinates, Location from, Location to, Long distance){
        this.id= id ;
        this.name=name;
        this.coordinates=coordinates;
        this.creationDate=LocalDate.now();
        this.from=from;
        this.to=to;
        this.distance=distance;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int compareTo(Route route) {
        if (this.distance == route.distance) {
            return 0;
        } else if (this.distance > route.distance) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", location from=" + from +
                ", location to=" + to +
                ", distance=" + distance +
                '}';
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Location getFrom() {
        return from;
    }

    public Location getTo() {
        return to;
    }

    public Long getDistance() {
        return distance;
    }

    public String getName() {
        return name;
    }
}

