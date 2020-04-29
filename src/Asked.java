import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.Date;

import Collection.*;
import Ex.ErrorCheck;
import Ex.IncorrectValue;
import Ex.NullValue;
import Foundation.Coordinates;
import Foundation.Location;
import Foundation.Route;
import Input.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Добавляет коллекцию из файла и запускает интерактивное приложение
 */
public class Asked {
    public static RouteCollection routeCollection = new RouteCollection();

    public static void app(File file) throws JAXBException, IOException {

        try {
            Route route;
            int id;
            String name;
            Integer x;
            Integer y;
            float x1;
            double y1;
            Integer z1;
            String name1;
            float x2;
            double y2;
            Integer z2;
            String name2;
            long distance;

            File fXmlFile = new File(String.valueOf(file));
            BufferedInputStream script = new BufferedInputStream(new FileInputStream(fXmlFile));
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dbFactory.setValidating(true);
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            ErrorCheck e = new ErrorCheck();
            dBuilder.setErrorHandler(e);
            Document doc = dBuilder.parse(script);

//optional, but recommended
//read this - http://stackoverflow.com/questions/13786607/normaliza..
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("route");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                int c = 0;
                Node nNode = nList.item(temp);
                org.w3c.dom.Element eElement = (org.w3c.dom.Element) nNode;
                if (eElement.getElementsByTagName("id").item(0).getTextContent().equals("") || Integer.parseInt(eElement.getElementsByTagName("id").item(0).getTextContent()) < 1) {
                    System.out.println("id не может быть null, а также больше 0");
                    System.exit(1);
                } else {
                    id = Integer.parseInt(eElement.getElementsByTagName("id").item(0).getTextContent());
                    for (int i = 0; i < routeCollection.getCollection().size(); i++) {
                        if (routeCollection.getIds()[i] == id) {
                            c++;
                        }
                    }
                    if (c == 0) {
                        if (eElement.getElementsByTagName("name").item(0).getTextContent().equals("")) {
                            System.out.println("Имя не может быть пустой строкой");
                            System.exit(1);
                        } else {
                            name = eElement.getElementsByTagName("name").item(0).getTextContent();
                            if (eElement.getElementsByTagName("coordinates").item(0).getFirstChild().getTextContent().equals("") || Integer.parseInt(eElement.getElementsByTagName("coordinates").item(0).getFirstChild().getTextContent()) < -310) {
                                System.out.println("Coordinates : x - Значение поля должно быть больше -310, Поле не может быть null");
                                System.exit(1);
                            } else {
                                x = Integer.parseInt(eElement.getElementsByTagName("coordinates").item(0).getFirstChild().getTextContent());
                                if (Integer.parseInt(eElement.getElementsByTagName("coordinates").item(0).getLastChild().getTextContent()) < -921) {
                                    System.out.println("Значение поля должно быть больше -921");
                                    System.exit(1);
                                } else {
                                    y = Integer.parseInt(eElement.getElementsByTagName("coordinates").item(0).getLastChild().getTextContent());
                                    if (eElement.getElementsByTagName("locationfrom").item(0).getFirstChild().getTextContent().equals("")) {
                                        x1 = 0;
                                    } else {
                                        x1 = Float.parseFloat(eElement.getElementsByTagName("locationfrom").item(0).getFirstChild().getTextContent());
                                        float p = x1;
                                        int o = (int) p;
                                        int i = eElement.getElementsByTagName("locationfrom").item(0).getFirstChild().getTextContent().length() - String.valueOf(o).length() - 1;
                                        if (i > 6) {
                                            System.out.println("Элемент с ID:" + id + " Location from : Координата x. Длина дробной части " + i + ".Происходит округление, чтобы число не округлялось длина дробной части должна быть меньше 7");
                                        }
                                    }
                                    if (eElement.getElementsByTagName("locationfrom").item(0).getFirstChild().getNextSibling().getTextContent().equals("")) {
                                        y1 = 0;
                                    } else {
                                        y1 = Double.parseDouble(eElement.getElementsByTagName("locationfrom").item(0).getFirstChild().getNextSibling().getTextContent());
                                        double p1 = y1;
                                        int o1 = (int) p1;
                                        int i1 = eElement.getElementsByTagName("locationfrom").item(0).getFirstChild().getNextSibling().getTextContent().length() - String.valueOf(o1).length() - 1;
                                        if (i1 > 15) {
                                            System.out.println("Элемент с ID:" + id + " Location from : Координата y. Длина дробной части-" + i1 + ".Происходит округление, чтобы число не округлялось длина дробной части должна быть меньше 16");
                                        }
                                    }
                                    if (eElement.getElementsByTagName("locationfrom").item(0).getFirstChild().getNextSibling().getNextSibling().getTextContent().equals("")) {
                                        z1 = 0;
                                    } else {
                                        z1 = Integer.parseInt(eElement.getElementsByTagName("locationfrom").item(0).getFirstChild().getNextSibling().getNextSibling().getTextContent());
                                    }
                                    name1 = eElement.getElementsByTagName("locationfrom").item(0).getLastChild().getTextContent();
                                    if (name1.equals("")) {
                                        System.out.println("Имя не может быть пустой строкой");
                                        System.exit(1);
                                    }
                                    if (eElement.getElementsByTagName("locationto").item(0).getFirstChild().getTextContent().equals("")) {
                                        x2 = 0;
                                    } else {
                                        x2 = Float.parseFloat(eElement.getElementsByTagName("locationto").item(0).getFirstChild().getTextContent());
                                        float p2 = x1;
                                        int o2 = (int) p2;
                                        int i2 = eElement.getElementsByTagName("locationto").item(0).getFirstChild().getTextContent().length() - String.valueOf(o2).length() - 1;
                                        if (i2 > 6) {
                                            System.out.println("Элемент с ID:" + id + " Location to : Координата x. Длина дробной части-" + i2 + ".Происходит округление, чтобы число не округлялось длина дробной части должна быть меньше 7");
                                        }
                                    }
                                    if (eElement.getElementsByTagName("locationto").item(0).getFirstChild().getNextSibling().getTextContent().equals("")) {
                                        y2 = 0;
                                    } else {
                                        y2 = Double.parseDouble(eElement.getElementsByTagName("locationto").item(0).getFirstChild().getNextSibling().getTextContent());
                                        double p3 = y2;
                                        int o3 = (int) p3;
                                        int i3 = eElement.getElementsByTagName("locationto").item(0).getFirstChild().getNextSibling().getTextContent().length() - String.valueOf(o3).length() - 1;
                                        if (i3 > 15) {
                                            System.out.println("Элемент с ID:" + id + " Location to : Координата y. Длина дробной части-" + i3 + ".Происходит округление, чтобы число не округлялось длина дробной части должна быть меньше 16");
                                        }
                                    }
                                    if (eElement.getElementsByTagName("locationto").item(0).getFirstChild().getNextSibling().getNextSibling().getTextContent().equals("")) {
                                        z2 = 0;
                                    } else {
                                        z2 = Integer.parseInt(eElement.getElementsByTagName("locationto").item(0).getFirstChild().getNextSibling().getNextSibling().getTextContent());
                                    }
                                    name2 = eElement.getElementsByTagName("locationto").item(0).getLastChild().getTextContent();
                                    if (name2.equals("")) {
                                        System.out.println("Имя не может быть пустой строкой");
                                        System.exit(1);
                                    }
                                    if (eElement.getElementsByTagName("distance").item(0).getTextContent().equals("") || Long.parseLong(eElement.getElementsByTagName("distance").item(0).getTextContent()) <= 1) {
                                        System.out.println("Поле не может быть null, Значение поля должно быть больше 1");
                                        System.exit(1);
                                    } else {
                                        distance = Long.parseLong(eElement.getElementsByTagName("distance").item(0).getTextContent());
                                        route = new Route(id, name, new Coordinates(x, y), new Location(x1, y1, z1, name1), new Location(x2, y2, z2, name2), distance);
                                        routeCollection.getCollection().add(route);
                                    }
                                }
                            }
                        }
                    } else {
                        System.out.println("\nЭлемент с таким ID(" + id + ") уже существует введите другой");
                    }
                    if (temp == nList.getLength() - 1) {
                        routeCollection.setDate(new Date());
                        if (routeCollection.getCollection().size() != 0) {
                            System.out.println("\nЭлементы добавлены в коллекцию");
                            System.out.println("----------------------------");
                        }
                        CommandHolder handler = new CommandHolder(routeCollection);
                        TextInput terminal = new TextInput();
                        terminal.output("Здравствуйте, вы находитесь в интерактивном режиме! Введите help для просмотра возможных команд");
                        while (!terminal.getNextInput().equals("exit")) {
                            handler.doCommand(terminal);
                        }
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            System.out.println("Ошибка парсинга");
            ;
        } catch (SAXParseException e) {
            System.out.println("Отредактируйте документ");
        } catch (NullValue nullValue) {
            System.out.println("Значение равно null");
            ;
        } catch (IncorrectValue incorrectValue) {
            System.out.println("Неверное значение");
            ;
        } catch (SAXException e) {
            System.out.println("Ошибка в XML файле");
            ;
        } catch (NumberFormatException e) {
            System.out.println("Я вас умоляю, не вводите строки туда, где должны быть числа");
            e.printStackTrace();


        }
    }
}