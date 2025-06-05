import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager manager = Managers.getDefault();

        while (true) {
            System.out.println("\nВыберите действие:");
            System.out.println("1. Создать Task");
            System.out.println("2. Создать Epic");
            System.out.println("3. Создать Subtask");
            System.out.println("4. Получить Task по ID");
            System.out.println("5. Получить Epic по ID");
            System.out.println("6. Получить Subtask по ID");
            System.out.println("7. Удалить Task по ID");
            System.out.println("8. Удалить Epic по ID");
            System.out.println("9. Удалить Subtask по ID");
            System.out.println("10. Показать все задачи");
            System.out.println("11. Показать историю просмотров");
            System.out.println("0. Выход");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    System.out.print("Введите название Task: ");
                    String taskTitle = scanner.nextLine();
                    System.out.print("Введите описание Task: ");
                    String taskDesc = scanner.nextLine();
                    Task task = new Task(taskTitle, taskDesc, Status.NEW);
                    manager.addTask(task);
                    System.out.println("Task создан с ID: " + task.getId());
                    break;
                case "2":
                    System.out.print("Введите название Epic: ");
                    String epicTitle = scanner.nextLine();
                    System.out.print("Введите описание Epic: ");
                    String epicDesc = scanner.nextLine();
                    Epic epic = new Epic(epicTitle, epicDesc, Status.NEW);
                    manager.addEpic(epic);
                    System.out.println("Epic создан с ID: " + epic.getId());
                    break;
                case "3":
                    System.out.print("Введите название Subtask: ");
                    String subTitle = scanner.nextLine();
                    System.out.print("Введите описание Subtask: ");
                    String subDesc = scanner.nextLine();
                    System.out.print("Введите ID Epic, к которому относится подзадача: ");
                    int epicId = Integer.parseInt(scanner.nextLine());
                    Subtask subtask = new Subtask(subTitle, subDesc, Status.NEW, epicId);
                    manager.addSubtask(subtask);
                    System.out.println("Subtask создан с ID: " + subtask.getId());
                    break;
                case "4":
                    System.out.print("Введите ID Task: ");
                    int taskId = Integer.parseInt(scanner.nextLine());
                    Task t = manager.getTaskById(taskId);
                    System.out.println(t != null ? t : "Task не найден.");
                    break;
                case "5":
                    System.out.print("Введите ID Epic: ");
                    int eId = Integer.parseInt(scanner.nextLine());
                    Epic e = manager.getEpicById(eId);
                    System.out.println(e != null ? e : "Epic не найден.");
                    break;
                case "6":
                    System.out.print("Введите ID Subtask: ");
                    int sId = Integer.parseInt(scanner.nextLine());
                    Subtask s = manager.getSubtaskById(sId);
                    System.out.println(s != null ? s : "Subtask не найден.");
                    break;
                case "7":
                    System.out.print("Введите ID Task для удаления: ");
                    int delTaskId = Integer.parseInt(scanner.nextLine());
                    manager.deleteTaskById(delTaskId);
                    System.out.println("Task удалён.");
                    break;
                case "8":
                    System.out.print("Введите ID Epic для удаления: ");
                    int delEpicId = Integer.parseInt(scanner.nextLine());
                    manager.deleteEpicById(delEpicId);
                    System.out.println("Epic удалён.");
                    break;
                case "9":
                    System.out.print("Введите ID Subtask для удаления: ");
                    int delSubId = Integer.parseInt(scanner.nextLine());
                    manager.deleteSubtaskById(delSubId);
                    System.out.println("Subtask удалён.");
                    break;
                case "10":
                    System.out.println("Все задачи:");
                    for (Task taskItem : manager.getAllTasks()) {
                        System.out.println(taskItem);
                    }
                    for (Epic epicItem : manager.getAllEpics()) {
                        System.out.println(epicItem);
                        List<Subtask> subtasks = manager.getSubtasksOfEpic(epicItem.getId());
                        for (Subtask st : subtasks) {
                            System.out.println("  - " + st);
                        }
                    }
                    break;
                case "11":
                    System.out.println("История:");
                    for (Task historyTask : manager.getHistory()) {
                        System.out.println(historyTask);
                    }
                    break;
                case "0":
                    System.out.println("Выход...");
                    return;
                default:
                    System.out.println("Неверная команда.");
            }
        }
    }
}
