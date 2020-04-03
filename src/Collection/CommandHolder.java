package Collection;

import Ex.IncorrectValue;
import Ex.NoArgument;
import Ex.NullValue;
import Input.InputInterface;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Класс который вызывает методы у CommandManager
 */
public class CommandHolder {

    public CommandHolder(RouteCollection routeCollection) {
        this.routeCollection = routeCollection;
        this.commandmanager = new CommandManager(routeCollection);
    }

    private CommandManager commandmanager;
    private RouteCollection routeCollection;
    private String[] Command;
    private CommandReader reader = new CommandReader();

    public void CommandHandler(CommandManager commandmanager) {
        this.commandmanager = commandmanager;
    }

    /**
     * С помошью класса CommandReader принимимает команды и вызвает их у CommandManager
     * @param inputCommand
     * @throws JAXBException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IncorrectValue
     * @throws NullValue
     */
    public void doCommand(InputInterface inputCommand) throws JAXBException, IOException, ParserConfigurationException, SAXException, IncorrectValue, NullValue {
        Command = reader.returnCommand(inputCommand);

        switch (Command[0]) {
            case "":
                break;
            case "help":
                commandmanager.help();
                break;
            case "info":
                commandmanager.info();
                break;
            case "show":
                commandmanager.show();
                break;
            case "add":
                commandmanager.add(inputCommand);
                break;
            case "update":
                try {
                    if (Command.length < 2) throw new NoArgument("Введите айди");
                    commandmanager.update(Integer.parseInt(Command[1]), inputCommand);
                } catch (NoArgument noArgument) {
                    noArgument.getMessage();
                } catch (NumberFormatException n) {
                    System.out.println("Введите целое положительное число");
                } catch (Exception e) {
                    System.out.println("Нет такого элемента");
                }
                break;
            case "remove_by_id":
                try {
                    if (Command.length < 2) throw new NoArgument("Введите айди");
                    commandmanager.remove(Integer.parseInt(Command[1]));
                } catch (NoArgument e) {
                    e.getMessage();
                } catch (NumberFormatException e) {
                    System.out.println("Введите целое положительное число");
                } catch (Exception e) {
                    System.out.println("Такого айди не существует");
                }
                break;
            case "clear":
                commandmanager.clear();
                break;
            case "exit":
                System.exit(0);
                break;
            case "save":
                try {
                    commandmanager.save();
                } catch (Exception s) {
                    System.out.println("Не сохраняется" + s.getMessage());
                }
                break;
            case "execute_script":
                try {
                    if (Command.length < 2) throw new NoArgument("Введите имя скрипта");
                    commandmanager.executeScript(Command[1]);
                } catch (NoArgument noArgument) {
                    noArgument.getMessage();
                } catch (FileNotFoundException e) {
                    System.out.println("Такого файла нет");
                }
                break;
            case "remove_lower":
                commandmanager.removeLower(inputCommand);
                break;
            case "add_if_max":
                commandmanager.addifmax(inputCommand);
                break;
            case "remove_head":
                commandmanager.removeHead();
                break;
            case "min_by_distance":
                commandmanager.minByDistance();
                break;
            case "max_by_from":
                commandmanager.maxByFrom();
                break;
            case "print_field_ascending_distance":
                commandmanager.printFieldAscendingDistance();
                break;
            default:
                System.out.println("Такой команды нет. Введите help");
        }
    }
}
