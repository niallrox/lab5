package Collection;

import Foundation.Route;
import java.util.*;

/**
 * Класс хранящий коллекцию
 */
public class RouteCollection {
    public LinkedList<Route> route = new LinkedList<>();
    private Date date;

    public LinkedList<Route> getCollection() {
        return route;
    }

    public Route getFirst() {
        return route.getFirst();
    }

    public Long[] getIds() {
        Long[] id = new Long[route.size()];
        for (int i = 0; i < route.size(); i++) {
            id[i] = route.get(i).getId();
        }
        return id;
    }


    @Override
    public String toString() {
        return "Тип коллекции: " + this.getCollection().getClass() +
                "\nДата инициализации: " + date +
                "\nКоличество элементов: " + this.getCollection().size();
    }


    public void setDate(Date date) {
        this.date = date;
    }
}
