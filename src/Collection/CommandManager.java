package Collection;

import Ex.IncorrectValue;
import Ex.NullValue;
import Foundation.Coordinates;
import Foundation.Location;
import Foundation.Route;
import Input.InputInterface;
import org.xml.sax.SAXException;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.*;

/**
 * Класс в котором описаны комманды
 */

public class CommandManager {
    long id = 1;
    public RouteCollection routeCollection = new RouteCollection();
    int ik = 0;

    public CommandManager(RouteCollection routeCollection) {
        this.routeCollection = routeCollection;
    }

    /**
     * Выводит справку по доступным командам
     */
    public void help() {
        System.out.println(
                "help: Вывести справку по доступным командам " +
                        "\ninfo: Вывести информацию о коллекции " +
                        "\nshow: Вывести все элементы коллекции в строковом представлении " +
                        "\nadd: Добавить новый элемент в коллекцию " +
                        "\nupdate id: Обновить значение элемента коллекции, id которого равен заданному " +
                        "\nremove_by_id id: Удалить элемент из коллекции по его id " +
                        "\nclear: Очистить коллекцию " +
                        "\nsave: Сохранить коллекцию в файл " +
                        "\nexecute_script file_name: Считать и исполнить скрипт из указанного файла " +
                        "\nexit: Завершить программу (без сохранения в файл) " +
                        "\nremove_head: вывести первый элемент коллекции и удалить его " +
                        "\nadd_if_max: добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции" +
                        "\nremove_lower: удалить из коллекции все элементы, меньшие, чем заданный " +
                        "\nmin_by_distance: вывести любой объект из коллекции, значение поля distance которого является минимальным " +
                        "\nmax_by_from: вывести любой объект из коллекции, значение поля from которого является максимальным " +
                        "\nprint_field_ascending_distance distance: вывести значения поля distance в порядке возрастания " +
                        "............................................________\n" +
                        "....................................,.-‘”...................``~.,\n" +
                        ".............................,.-”...................................\"-.,\n" +
                        ".........................,/...............................................”:,\n" +
                        ".....................,?......................................................\\,\n" +
                        ".................../...........................................................,}\n" +
                        "................./......................................................,:`^`..}\n" +
                        ".............../...................................................,:”........./\n" +
                        "..............?.....__.........................................:`.........../\n" +
                        "............./__.(.....\"~-,_..............................,:`........../\n" +
                        ".........../(_....”~,_........\"~,_....................,:`........_/\n" +
                        "..........{.._$;_......”=,_.......\"-,_.......,.-~-,},.~”;/....}\n" +
                        "...........((.....*~_.......”=-._......\";,,./`..../”............../\n" +
                        "...,,,___.\\`~,......\"~.,....................`.....}............../\n" +
                        "............(....`=-,,.......`........................(......;_,,-”\n" +
                        "............/.`~,......`-...............................\\....../\\\n" +
                        ".............\\`~.*-,.....................................|,./.....\\,__\n" +
                        ",,_..........}.>-._\\...................................|..............`=~-,\n" +
                        ".....`=~-,_\\_......`\\,.................................\\\n" +
                        "...................`=~-,,.\\,...............................\\\n" +
                        "................................`:,,...........................`\\..............__\n" +
                        ".....................................`=-,...................,%`>--==``\n" +
                        "........................................_\\..........._,-%.......`\\\n" +
                        "...................................,<`.._|_,-&``................`\\\n" +
                        "\n");
    }

    /**
     * Выводит информацию о коллекции
     */
    public void info() {
        System.out.println(routeCollection.toString());
    }

    /**
     * Выводит все элементы коллекции в строковом представлении
     */
    public void show() {
        if (routeCollection.getCollection().size() != 0) {
            routeCollection.getCollection().forEach((xuy) -> System.out.println(xuy));
        } else System.out.println("Коллекция пуста.");

    }

    /**
     * Метод считывает элемент и заносит параметры, создавая тем самым объект
     * @param command
     * @return
     * @throws IncorrectValue
     * @throws NullValue
     */
    public Route readElement(InputInterface command) throws IncorrectValue, NullValue {
        Route route;
        for (int i=0;i<routeCollection.getCollection().size();i++){
            if (id==routeCollection.getCollection().get(i).getId()) {
                id++;
                i=-1;
            }
        }
        String name;
        do {
            command.output("Введите имя:");
            name = command.getNextInput().trim();

        } while (name.equals(""));

        String x1;
        Integer x = null;
        do {
            command.output("Coordinates:Введите координаты, x:");
            x1 = command.getNextInput().trim();
            try {
                x = Integer.parseInt(x1);
                if (x < -310) {
                    x = null;
                    System.out.println("Поле должно быть больше -310");
                }
            } catch (NumberFormatException n) {
                System.out.println("Это не число");
            }
        } while (x == null);

        Integer y = null;
        String y1;
        do {
            command.output("Введите координаты y:");
            y1 = command.getNextInput();
            try {
                y = Integer.parseInt(y1);
                if (y < -921) {
                    y = null;
                    System.out.println("Поле должно быть больше -921");
                }
            } catch (NumberFormatException n) {
                System.out.println("Это не число");
            }
        } while (y == null);

        String x2;
        Float xl = null;
        do {
            command.output("Location from:Введите координаты, x:");
            x2 = command.getNextInput().trim();
            if (x2 == "") {
                xl = null;
            } else {
                try {
                    xl = Float.parseFloat(x2);
                    float p = xl;
                    int o = (int) p;
                    int i = x2.length() - String.valueOf(o).length() - 1;
                    if (i > 6) {
                        System.out.println("Длина дробной части-" + i + ".Происходит округление, чтобы число не округлялось длина дробной части должна быть меньше 7");
                    }
                } catch (NumberFormatException n) {
                    System.out.println("Это не число");
                }
            }
        }
        while (xl == null);

        String y2;
        Double yl = null;
        do {
            command.output("Введите координаты, y:");
            y2 = command.getNextInput().trim();
            if (y2 == "") {
                yl = null;
            } else {
                try {
                    yl = Double.parseDouble(y2);
                    double p = yl;
                    int o = (int) p;
                    int i = y2.length() - String.valueOf(o).length() - 1;
                    if (i > 15) {
                        System.out.println("Длина дробной части-" + i + ".Происходит округление, чтобы число не округлялось длина дробной части должна быть меньше 16");
                    }
                } catch (NumberFormatException n) {
                    System.out.println("Это не число");
                }
            }
        } while (yl == null);

        String z;
        Integer zl = null;
        do {
            command.output("Введите координаты, z:");
            z = command.getNextInput().trim();
            if (z == "") {
                zl = null;
            } else {
                try {
                    zl = Integer.parseInt(z);
                } catch (NumberFormatException n) {
                    System.out.println("Это не число");
                }
            }
        } while (zl == null);

        String namel;
        do {
            command.output("Введите имя локации:");
            namel = command.getNextInput().trim();
        } while (namel.equals(""));

        String x3;
        Float xl1 = null;
        do {
            command.output("Location to:Введите координаты, x:");
            x3 = command.getNextInput().trim();
            if (x3 == "") {
                xl1 = null;
            } else {
                try {
                    xl1 = Float.parseFloat(x3);
                    float p = xl1;
                    int o = (int) p;
                    int i = x3.length() - String.valueOf(o).length() - 1;
                    if (i > 6) {
                        System.out.println("Длина дробной части-" + i + ".Происходит округление, чтобы число не округлялось длина дробной части должна быть меньше 7");
                    }
                } catch (NumberFormatException n) {
                    System.out.println("Это не число");
                }
            }
        } while (xl1 == null);

        String y3;
        Double yl1 = null;
        do {
            command.output("Введите координаты, y:");
            y3 = command.getNextInput().trim();
            if (y3 == "") {
                yl1 = null;
            } else {
                try {
                    yl1 = Double.parseDouble(y3);
                    double p = yl1;
                    int o = (int) p;
                    int i = y3.length() - String.valueOf(o).length() - 1;
                    if (i > 15) {
                        System.out.println("Длина дробной части-" + i + ".Происходит округление, чтобы число не округлялось длина дробной части должна быть меньше 16");
                    }
                } catch (NumberFormatException n) {
                    System.out.println("Это не число");
                }
            }
        } while (yl1 == null);

        String z1;
        Integer zl1 = null;
        do {
            command.output("Введите координаты, z:");
            z1 = command.getNextInput().trim();
            if (z1 == "") {
                zl1 = null;
            } else {
                try {
                    zl1 = Integer.parseInt(z1);
                } catch (NumberFormatException n) {
                    System.out.println("Это не число");
                }
            }
        } while (zl1 == null);

        String namel1;
        do {
            command.output("Введите имя локации:");
            namel1 = command.getNextInput().trim();
        } while (namel1.equals(""));

        String distance;
        Long distance1 = null;
        do {
            command.output("Введите расстояние ");
            distance = command.getNextInput().trim();
            try {
                distance1 = Long.parseLong(distance);
                if (distance1 <= 1) {
                    distance1 = null;
                    System.out.println("Поле должно быть больше 1");
                }
            } catch (NumberFormatException n) {
                System.out.println("Это не число");
            }
        } while (distance1 == null);
        route = new Route(id, name, new Coordinates(x, y), new Location(xl, yl, zl, namel), new Location(xl1, yl1, zl1, namel1), distance1);
        return route;
    }

    /**
     * Удаляет элемент из коллекции по его id
     * @param id
     */
    public void remove(int id) {
        if (routeCollection.getCollection().size() != 0) {
            int k = 0;
            for (int i = 0; i < routeCollection.getCollection().size(); i++) {
                if (routeCollection.getCollection().get(i).getId() == id) {
                    k++;
                }
            }
            if (k > 0) {
                for (int i = 0; i < routeCollection.getCollection().size(); i++) {
                    if (routeCollection.getCollection().get(i).getId() == id) {
                        routeCollection.getCollection().remove(i);
                    }
                }
                System.out.println("Элемент коллекции удалён.");
            } else System.out.println("Такого id нет 8f7giohih4es3asriujpokpjig6d5d7y");
        } else {
            System.out.println("Коллекция пуста");
        }
    }

    /**
     * Очищает коллекцию
     */
    public void clear() {
        if (routeCollection.getCollection().size() != 0) {
            routeCollection.getCollection().clear();
            System.out.println("Коллекция очищена.");
        } else {
            System.out.println("Коллекция пуста");
        }
    }

    /**
     * Добавляет новый элемент в коллекцию
     * @param c
     * @throws IncorrectValue
     * @throws NullValue
     */
    public void add(InputInterface c) throws IncorrectValue, NullValue {
        routeCollection.getCollection().add(this.readElement(c));
        System.out.println("Элемент создан");
    }

    /**
     * Обновляет значение элемента коллекции, id которого равен заданному
     * @param id
     * @param c
     * @throws IncorrectValue
     * @throws NullValue
     */
    public void update(long id, InputInterface c) throws IncorrectValue, NullValue {
        if (routeCollection.getCollection().size() != 0) {
            int k = 0;
            for (int i = 0; i < routeCollection.getCollection().size(); i++) {
                if (routeCollection.getCollection().get(i).getId() == id) {
                    k++;
                }
            }
            if (k > 0) {
                Route r = this.readElement(c);
                for (int i = 0; i < routeCollection.getCollection().size(); i++) {
                    if (routeCollection.getCollection().get(i).getId() == id) {
                        routeCollection.getCollection().remove(i);
                        r.setId(id);
                        routeCollection.getCollection().add(r);
                    }
                }
                System.out.println("Элемент коллекции обновлен.");
            } else {
                System.out.println("Такого id нет");
            }


        } else {
            System.out.println("Коллекция пуста");
        }
    }

    /**
     * Сохраняет коллекцию в файл
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public void save() throws IOException, ParserConfigurationException, SAXException {
        File outfile = new File(System.getenv("hleb"));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outfile))) {
            String con = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "<routecollection>\n";
            writer.write(con);
            System.out.println("----------------------------");
            for (int temp = 0; temp < routeCollection.getCollection().size(); temp++) {
                String content = "    <route>\n" + "        <id>" + routeCollection.getCollection().get(temp).getId() + "</id>\n";
                content = content + "        <name>" + routeCollection.getCollection().get(temp).getName() + "</name>\n";
                content = content + "        <coordinates><x>" + routeCollection.getCollection().get(temp).getCoordinates().getX() + "</x><y>" + routeCollection.getCollection().get(temp).getCoordinates().getY() + "</y></coordinates>\n";
                content = content + "        <locationfrom><x>" + routeCollection.getCollection().get(temp).getFrom().getX() + "</x><y>" + routeCollection.getCollection().get(temp).getFrom().getY() + "</y><z>" + routeCollection.getCollection().get(temp).getFrom().getZ() + "</z><name>" + routeCollection.getCollection().get(temp).getFrom().getName() + "</name></locationfrom>\n";
                content = content + "        <locationto><x>" + routeCollection.getCollection().get(temp).getTo().getX() + "</x><y>" + routeCollection.getCollection().get(temp).getTo().getY() + "</y><z>" + routeCollection.getCollection().get(temp).getTo().getZ() + "</z><name>" + routeCollection.getCollection().get(temp).getTo().getName() + "</name></locationto>\n";
                content = content + "        <distance>" + routeCollection.getCollection().get(temp).getDistance() + "</distance>\n    </route>\n";
                writer.write(content);
            }

            String cont = "</routecollection>";
            writer.write(cont);
        }
        System.out.println("Коллекция сохранена");
    }

    /**
     * удаляет из коллекции все элементы, меньшие, чем заданный
     * @param c
     * @throws NullValue
     * @throws IncorrectValue
     */
    public void removeLower(InputInterface c) throws NullValue, IncorrectValue {
        if (routeCollection.getCollection().size() != 0) {
            Route route = this.readElement(c);
            for (int i = 0; i < routeCollection.getCollection().size(); i++) {
                if (routeCollection.getCollection().get(i).compareTo(route) == -1) {
                    routeCollection.getCollection().remove(i);
                }
            }
            System.out.println("Успешно удалено!");
        } else {
            System.out.println("Коллекция пуста");
        }

    }

    /**
     * выводит первый элемент коллекции и удаляет его
     */
    public void removeHead() {
        if (routeCollection.getCollection().size() != 0) {
            System.out.println(routeCollection.getFirst());
            routeCollection.getCollection().remove(0);
        } else {
            System.out.println("Коллекция пуста");
        }
    }

    /**
     * добавляет новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции
     * @param c
     * @throws IncorrectValue
     * @throws NullValue
     */
    public void addifmax(InputInterface c) throws IncorrectValue, NullValue {
        if (routeCollection.getCollection().size() != 0) {
            int count = 0;
            Route r = this.readElement(c);
            for (int i = 0; i < routeCollection.getCollection().size(); i++) {
                if (routeCollection.getCollection().get(i).compareTo(r) == -1) {
                    count++;
                } else {
                    break;
                }
                if (count == routeCollection.getCollection().size()) {
                    routeCollection.getCollection().add(r);
                }
            }
        } else {
            Route z = this.readElement(c);
            routeCollection.getCollection().add(z);
        }
    }

    /**
     * выводит любой объект из коллекции, значение поля distance которого является минимальным
     */
    public void minByDistance() {
        if (routeCollection.getCollection().size() != 0) {
            Route h = routeCollection.getCollection().get(0);
            Long g = routeCollection.getCollection().get(0).getDistance();
            for (int i = 1; i < routeCollection.getCollection().size(); i++) {
                if (routeCollection.getCollection().get(i).getDistance() < g) {
                    h = routeCollection.getCollection().get(i);
                    g = routeCollection.getCollection().get(i).getDistance();
                }
            }
            System.out.println(h);

        } else {
            System.out.println("Коллекция пуста");
        }
    }

    /**
     * выводит любой объект из коллекции, значение поля from которого является максимальным
     */
    public void maxByFrom() {
        if (routeCollection.getCollection().size() != 0) {
            Route h = routeCollection.getCollection().get(0);
            Location g = routeCollection.getCollection().get(0).getFrom();
            for (int i = 1; i < routeCollection.getCollection().size(); i++) {
                if (routeCollection.getCollection().get(i).getFrom().getX() + routeCollection.getCollection().get(i).getFrom().getY() + routeCollection.getCollection().get(i).getFrom().getZ() > g.getX() + g.getY() + g.getZ()) {
                    h = routeCollection.getCollection().get(i);
                    g = routeCollection.getCollection().get(i).getFrom();
                }
            }
            System.out.println(h);
        } else {
            System.out.println("Коллекция пуста");
        }
    }

    /**
     * выводит значения поля distance в порядке возрастания
     */
    public void printFieldAscendingDistance() {
        if (routeCollection.getCollection().size() != 0) {
            List<Long> collection = new LinkedList<>();
            for (int i = 0; i < routeCollection.getCollection().size(); i++) {
                collection.add(routeCollection.getCollection().get(i).getDistance());
            }
            Comparator<Long> comparator = Comparator.comparing(obj -> obj.longValue());
            Collections.sort(collection, comparator);
            for (Long d : collection) {
                System.out.print(d.toString() + " ");
            }
        } else {
            System.out.println("Коллекция пуста");
        }
    }

    /**
     * Считывает и исполняет скрипт из указанного файла
     * @param fileName
     * @throws IOException
     * @throws JAXBException
     */
    public void executeScript(String fileName) throws IOException, JAXBException {
        String userCommand;
        String[] finalUserCommand;
        try {
            BufferedInputStream script = new BufferedInputStream(new FileInputStream(fileName));
            try (Scanner commandReader = new Scanner(script)) {
                while (commandReader.hasNextLine()) {
                    userCommand = commandReader.nextLine();
                    finalUserCommand = userCommand.trim().split(" ", 2);
                    try {
                        switch (finalUserCommand[0]) {
                            case "":
                                break;
                            case "help":
                                help();
                                break;
                            case "info":
                                info();
                                break;
                            case "show":
                                show();
                                break;
                            case "add":
                                int id = (int) ((Math.random() * 1000) + 1);
                                for (int i = 0; i < routeCollection.getCollection().size(); i++) {
                                    if (routeCollection.getIds()[i] == id) {
                                        id = (int) ((Math.random() * 1000) + 1);
                                        i = -1;
                                    }
                                }
                                String[] arr = new String[12];
                                for (int i = 0; i < arr.length; i++) {
                                    userCommand = commandReader.nextLine().toLowerCase();
                                    arr[i] = userCommand;
                                }
                                for (int i = 0; i < arr.length; i++) {
                                    if (arr[i]==null){
                                        System.out.println("Введено недостаточно данных");
                                        break;}
                                }
                                Route route = new Route(id, arr[0], new Coordinates(Integer.parseInt(arr[1]), Integer.parseInt(arr[2])), new Location(Float.parseFloat(arr[3]), Double.parseDouble(arr[4]), Integer.parseInt(arr[5]), arr[6]), new Location(Float.parseFloat(arr[7]), Double.parseDouble(arr[8]), Integer.parseInt(arr[9]), arr[10]), Long.parseLong(arr[11]));
                                routeCollection.getCollection().add(route);
                                System.out.println("добавлено");
                                break;
                            case "update":
                                Long id1 = Long.parseLong(finalUserCommand[1]);
                                int k = 0;
                                for (int i = 0; i < routeCollection.getCollection().size(); i++) {
                                    if (routeCollection.getCollection().get(i).getId() == id1) {
                                        k++;
                                    }
                                }
                                if (k > 0) {
                                    String[] arra = new String[12];
                                    for (int i = 0; i < arra.length; i++) {
                                        userCommand = commandReader.nextLine();
                                        arra[i] = userCommand;
                                    }
                                    Route r = new Route(id1, arra[0], new Coordinates(Integer.parseInt(arra[1]), Integer.parseInt(arra[2])), new Location(Float.parseFloat(arra[3]), Double.parseDouble(arra[4]), Integer.parseInt(arra[5]), arra[6]), new Location(Float.parseFloat(arra[7]), Double.parseDouble(arra[8]), Integer.parseInt(arra[9]), arra[10]), Long.parseLong(arra[11]));
                                    for (int i = 0; i < routeCollection.getCollection().size(); i++) {
                                        if (routeCollection.getCollection().get(i).getId() == id1) {
                                            System.out.println(routeCollection.getCollection().get(i).toString());
                                            routeCollection.getCollection().remove(i);
                                            r.setId(id1);
                                            routeCollection.getCollection().add(r);
                                        }
                                    }
                                    System.out.println("Элемент коллекции обновлен.");
                                } else {
                                    System.out.println("Такого id нет");
                                }
                                break;
                            case "remove_by_id":
                                remove(Integer.parseInt(finalUserCommand[1]));
                                break;
                            case "clear":
                                clear();
                                break;
                            case "save":
                                save();
                                break;
                            case "execute_script":
                                ik++;
                                if (ik <= 3) {
                                    executeScript(finalUserCommand[1]);
                                } else {
                                    ik = 0;
                                    System.out.println("Ограничение переполнения стека");
                                }
                                break;
                            case "add_if_max":
                                int id2 = (int) ((Math.random() * 1000) + 1);
                                for (int i = 0; i < routeCollection.getCollection().size(); i++) {
                                    if (routeCollection.getIds()[i] == id2) {
                                        id2 = (int) ((Math.random() * 1000) + 1);
                                        i = -1;
                                    }
                                }
                                int count = 0;
                                String[] arra = new String[12];
                                for (int i = 0; i < arra.length; i++) {
                                    userCommand = commandReader.nextLine();
                                    arra[i] = userCommand;
                                }
                                Route r = new Route(id2, arra[0], new Coordinates(Integer.parseInt(arra[1]), Integer.parseInt(arra[2])), new Location(Float.parseFloat(arra[3]), Double.parseDouble(arra[4]), Integer.parseInt(arra[5]), arra[6]), new Location(Float.parseFloat(arra[7]), Double.parseDouble(arra[8]), Integer.parseInt(arra[9]), arra[10]), Long.parseLong(arra[11]));
                                for (int i = 0; i < routeCollection.getCollection().size(); i++) {
                                    if (routeCollection.getCollection().get(i).compareTo(r) == 1) {
                                        count++;
                                    }
                                }
                                if (count == routeCollection.getCollection().size()) {
                                    routeCollection.getCollection().add(r);
                                }
                                break;
                            case "remove_lower":
                                int id3 = (int) ((Math.random() * 1000) + 1);
                                for (int i = 0; i < routeCollection.getCollection().size(); i++) {
                                    if (routeCollection.getIds()[i] == id3) {
                                        id3 = (int) ((Math.random() * 1000) + 1);
                                        i = -1;
                                    }
                                }
                                String[] array = new String[12];
                                for (int i = 0; i < array.length; i++) {
                                    userCommand = commandReader.nextLine();
                                    array[i] = userCommand;
                                }
                                Route ro = new Route(id3, array[0], new Coordinates(Integer.parseInt(array[1]), Integer.parseInt(array[2])), new Location(Float.parseFloat(array[3]), Double.parseDouble(array[4]), Integer.parseInt(array[5]), array[6]), new Location(Float.parseFloat(array[7]), Double.parseDouble(array[8]), Integer.parseInt(array[9]), array[10]), Long.parseLong(array[11]));
                                for (int i = 0; i < routeCollection.getCollection().size(); i++) {
                                    if (routeCollection.getCollection().get(i).compareTo(ro) == -1) {
                                        routeCollection.getCollection().remove(i);
                                    }
                                }
                                System.out.println("Успешно удалено!");
                                break;
                            case "remove_head":
                                removeHead();
                                break;
                            case "min_by_distance":
                                minByDistance();
                                break;
                            case "max_by_from":
                                maxByFrom();
                                break;
                            case "print_field_ascending_distance":
                                printFieldAscendingDistance();
                                break;
                            case "exit":
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Такой команды нет.");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Отсутствует аргумент");
                    } catch (SAXException e) {
                        System.out.println("Сакс Эксепшн");
                    } catch (ParserConfigurationException e) {
                        System.out.println("Ошибка парсинга");
                    }
                    System.out.println();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файла по указанному пути не существует.");
        }
    }
}


