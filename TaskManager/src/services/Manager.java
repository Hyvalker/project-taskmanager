package services;

import model.Priority;
import model.Task;

import repository.TaskRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Manager {

    List<Task> taskList = new ArrayList<>();

    private static Priority getPriority(Scanner scanner) {
        System.out.println("Escolha o nível de prioridade da tarefa: ");
        System.out.println("1 - BAIXA");
        System.out.println("2 - MÉDIA");
        System.out.println("3 - ALTA");
        int choice = scanner.nextInt();
        scanner.nextLine();

        return switch (choice) {
            case 1 -> Priority.LOW;
            case 2 -> Priority.MEDIUM;
            case 3 -> Priority.HIGH;
            default -> {
                System.out.println("Opção inválida. Definindo prioridade como MÉDIA por padrão.");
                yield Priority.MEDIUM;
            }
        };
    }

     public void createTask(Scanner scanner){

         System.out.println("Digite o número de identificação (ID) da tarefa: ");
         int id = scanner.nextInt();
         scanner.nextLine();
         for (Task t : taskList) {
             if (t.getId() == id) {
                 System.out.println("ID já cadastrado.");
                 return;
             }
         }

         System.out.println("Digite o título da tarefa: ");
         String title = scanner.nextLine();

         System.out.println("Digite a descrição da tarefa: ");
         String description = scanner.nextLine();

         System.out.println("Digite a data limite para entrega da tarefa: ");
         String textDeadline = scanner.nextLine();
         LocalDate deadline = LocalDate.parse(textDeadline);

         boolean isDone = false;

         System.out.println("Digite o nível de prioridade da tarefa: ");
         Priority priority = getPriority(scanner);

         Task task = new Task (id, title, description, deadline, isDone, priority);
         taskList.add(task);

         TaskRepository.saveTask(task);
         System.out.println("Tarefa criada e salva com sucesso!");
     }


}
