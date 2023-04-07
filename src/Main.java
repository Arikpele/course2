import model.MyCalender;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scaner = new Scanner(System.in)) {
           label:
           while (true) {
               System.out.println("Выберите пункт меню");
               printMenu();
               if (scaner.hasNextInt()) {
                   int menu = scaner.nextInt();
                   switch (menu) {
                       case 1:
                           MyCalender.addTask(scaner);
                           break;
                       case 2:
                           MyCalender.editTask(scaner);
                           break;
                       case 3:
                           MyCalender.deleteTask(scaner);
                           break;
                       case 4:
                           MyCalender.getTaskByDay(scaner);
                           break;
                       case 5:
                           MyCalender.printArchivedTask();
                           break;
                       case 6:
                           model.MyCalender.getGroupedByDate();
                           break;
                       case 0:
                           break label;
                   }
               } else{
                       scaner.next();
                       System.out.println("Выберите пункт меню из списка");
                   }
               }
           }
        }


    private static void printMenu() {
        System.out.println("""
1.Добавить задачу
2.Редактировать задачу
3.Удалять задачу
4.Получить задачи на указанный день
5.Получить архивные задачи

0.Выход                
                """

        );
    }
}
//6.получить сгруппированные задачи