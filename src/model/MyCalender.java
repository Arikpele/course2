package model;

import exception.TaskNotFoundException;
import exception.WrongInputException;
import model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class MyCalender {
    public static final Map<Integer, Task> actualTask = new HashMap<>();
    public static final Map<Integer, Task> archivedTask = new HashMap<>();

    public static void addTask(Scanner scanner) {
        try {
            scanner.nextLine();
            System.out.println("Введите названеи задачи");
            String title = ValidateUtils.checkString(scanner.nextLine());
            System.out.println("Введите описание задачи");
            String description = ValidateUtils.checkString(scanner.nextLine());
            System.out.println("Введите тип задачи:0 рабочая  1 лчная");
            TaskType taskType = TaskType.values()[scanner.nextInt()];
            System.out.println("Введите повторяемость задачи: 0 - однократная, 1 - Ежедневная, 2 - Еженедельная, 3 - Ежемесячная, 4- Ежегодная");
            int occurrence = scanner.nextInt();
            System.out.println("Введите дату dd.MM.yyyy HH:mm");
            scanner.nextLine();
            createEvent(scanner, title, description, taskType, occurrence);
            System.out.println("Для выхода нажмите Enter");
            scanner.nextLine();
        } catch (WrongInputException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createEvent(Scanner scanner, String title, String description, TaskType taskType, int occerance) {
        try {
            LocalDateTime eventDate = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
            Task task;
            try {
                task = createTask(occerance, title, description, taskType, eventDate);
                System.out.println("Создана задача " + task);

            } catch (WrongInputException e) {
                System.out.println(e.getMessage());
            }
        } catch (DateTimeParseException e) {
            System.out.println("проверить формат");
            createEvent(scanner, title, description, taskType, occerance);
        }
    }

    public static void editTask(Scanner scanner) {
        try {
            System.out.println("Редактирование задачи: введите id");
            printActualTask();
            int id = scanner.nextInt();
            if (!actualTask.containsKey(id)) {
                throw new TaskNotFoundException("Задача не найдена");
            }
            System.out.println("Редактирование 0-заголовок 1-описание 2-тип 3-дата");
            int menuCase = scanner.nextInt();
            switch (menuCase) {
                case 0 -> {
                    scanner.nextLine();
                    System.out.println("Введите название задачи");
                    String title = scanner.nextLine();
                    Task task = actualTask.get(id);
                    task.setTitle(title);
                }
                case 1 -> {
                    scanner.nextLine();
                    System.out.println("Введите описание задачи: ");
                    String description = scanner.nextLine();
                    Task task = actualTask.get(id);
                    task.setDescription(description);

                }
                case 2 -> {
                    scanner.nextLine();
                    System.out.println("введите тип задачи: ");
                    String tip = scanner.nextLine();
                    Task task = actualTask.get(id);
                    task.setTitle(tip);
                }
                case 3 -> {
                    scanner.nextLine();
                    System.out.println("введите дату: ");
                    String date = scanner.nextLine();
                    Task task = actualTask.get(id);
                    task.setTitle(date);
                }
            }
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteTask(Scanner scanner) {
        System.out.println("Текущие задачи\n");
        printActualTask();
        try {
            System.out.println("Для удаления введите ID задачи\n");
            int id = scanner.nextInt();
            if (actualTask.containsKey(id)) {
                Task removedTask = actualTask.get(id);
                removedTask.setArchived(true);
                archivedTask.put(id, removedTask);
                System.out.println("Задача " + id + "удалена\n");
            } else {
                throw new TaskNotFoundException();
            }

        } catch (TaskNotFoundException e) {
            e.printStackTrace();
            System.out.println("Такой задачи не существует\n");
        }
    }

    public static void getTaskByDay(Scanner scanner) {
        System.out.println("Введите дату как dd.MM.yyyy:");
        try {
            String date = scanner.next();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy:");
            LocalDate reqestedDate = LocalDate.parse(date, dateFormatter);
            List<Task> foundEvents = findTaskByDate(reqestedDate);
            System.out.println("События на - " + reqestedDate + " ");
            for (Task task : foundEvents) {
                System.out.println(task);
            }
        } catch (DateTimeParseException e) {
            System.out.println("Проверьте формат даты");
        }
        scanner.nextLine();
        System.out.println("для выхода нажмите Enter");
    }


    private static List<Task> findTaskByDate(LocalDate date) {
        List<Task> tasks = new ArrayList<>();
        for (
                Task task : actualTask.values()) {
            if (task.checkOccurrence(date.atStartOfDay())) {
                tasks.add(task);
            }
        }
        return tasks;
    }

    private static Task createTask(int occurrence, String title, String description, TaskType taskType, LocalDateTime localDateTime) throws WrongInputException {
        return switch (occurrence) {
            case 0 -> {
                OncelyTask oncelyTask = new OncelyTask(title, description, taskType, localDateTime);
                actualTask.put(oncelyTask.getId(), oncelyTask);
                yield oncelyTask;
            }
            case 1 -> {
                DailyTask task = new DailyTask(title, description, taskType, localDateTime);
                actualTask.put(task.getId(), task);
                yield task;
            }
            case 2 -> {
                WeekyTask task = new WeekyTask(title, description, taskType, localDateTime);
                actualTask.put(task.getId(), task);
                yield task;
            }
            case 3 -> {
                MonthlyTask task = new MonthlyTask(title, description, taskType, localDateTime);
                actualTask.put(task.getId(), task);
                yield task;
            }
            case 4 -> {
                YearlyTask task = new YearlyTask(title, description, taskType, localDateTime);
                actualTask.put(task.getId(), task);
                yield task;
            }
            default -> null;
        };
    }

    private static void printActualTask() {
        for (Task task : actualTask.values()) {
            System.out.println(task);
        }
    }

    public static void printArchivedTask() {
        for (Task task : archivedTask.values()) {
            System.out.println(task);
        }
    }

    public static void getGroupedByDate() {
        Map<LocalDate, ArrayList<Task>> taskMap = new HashMap<>();
        for (Map.Entry<Integer, Task> entry : actualTask.entrySet()) {
            Task task = entry.getValue();
            LocalDate localDate = task.getFirstDate().toLocalDate();
            if (taskMap.containsKey(localDate)) {
                ArrayList<Task> tasks = taskMap.get(localDate);
                tasks.add(task);
            } else{
//                taskMap.put(localDate, new ArrayList<>(Collection.singletonList(task)));
            }
        }
        for (Map.Entry<LocalDate, ArrayList<Task>> taskEntry : taskMap.entrySet()) {
            System.out.println(taskEntry.getKey() + " : " + taskEntry.getValue());
        }
    }

}
