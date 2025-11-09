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

    public void createTask(Scanner scanner) {

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
        for (Task t : taskList) {
            if (title.equalsIgnoreCase(t.getTitle())) {
                System.out.println("Título já cadastrado para a tarefa com o ID: " + t.getId());
                return;
            }
        }

        System.out.println("Digite a descrição da tarefa: ");
        String description = scanner.nextLine();

        System.out.println("Digite a data limite para entrega da tarefa: ");
        String textDeadline = scanner.nextLine();
        LocalDate deadline = LocalDate.parse(textDeadline);

        boolean isDone = false;

        System.out.println("Digite o nível de prioridade da tarefa: ");
        Priority priority = getPriority(scanner);

        Task task = new Task(id, title, description, deadline, isDone, priority);
        taskList.add(task);

        TaskRepository.saveTask(task);
        System.out.println("Tarefa criada e salva com sucesso!");
    }

    public void loadTaskList() {
        taskList = TaskRepository.readTask();
        System.out.println("Lista de tarefas carregada com sucesso!");
    }

    public void listTasks() {
        if (taskList.isEmpty()) {
            System.out.println("Nenhuma tarefa encontrada. Inicialize uma lista de tarefas ou cadastre uma nova tarefa.");
            return;
        }
        for (Task t : taskList) {
            System.out.println(t);
        }
    }

    public void searchTask(Scanner scanner) {
        Task t = findTask(scanner);
        if (t == null) {
            System.out.println("Tarefa não encontrada. Cadastre uma tarefa ou carregue o arquivo de tarefas salvas.");
            return;
        }else {
            System.out.println("Tarefa encontrada:");
            System.out.println(t);
        }
    }

    public Task findTask(Scanner scanner) {
        System.out.println("Deseja buscar a tarefa pelo ID ou pelo título? ");
        System.out.println("1 - ID");
        System.out.println("2 - Título");
        int choice = scanner.nextInt();
        scanner.nextLine();

        while (choice < 1 || choice > 2) {
            System.out.println("Entrada inválida. Por favor digite apenas 1 para ID ou 2 para Título");
            choice = scanner.nextInt();
            scanner.nextLine();
        }

        if (choice == 1) {
            System.out.println("Digite o ID da tarefa: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            for (Task t : taskList) {
                if (t.getId() == id) return t;
            }

        } else {
            System.out.println("Digite o título da tarefa: ");
            String title = scanner.nextLine();
            for (Task t : taskList) {
                if (t.getTitle().equalsIgnoreCase(title)) return t;
            }
        }
        return null;
    }

    public void editTask(Scanner scanner) {
        Task t = findTask(scanner);
        if (t == null) {
            System.out.println("Tarefa não encontrada.");
            return;
        }
        System.out.println("Tarefa encontrada: " + t);
        System.out.println("Escolha o que deseja editar: ");
        System.out.println("1 - ID");
        System.out.println("2 - Título");
        System.out.println("3 - Descrição");
        System.out.println("4 - Data limite para entrega");
        System.out.println("5 - Status de conclusão");
        System.out.println("6 - Nível de prioridade");

        int editChoice = scanner.nextInt();
        scanner.nextLine();

        switch (editChoice) {
            case 1 -> {
                System.out.println("Digite o novo ID");
                int newId = scanner.nextInt();
                scanner.nextLine();

                boolean idExists = false;
                for (Task task : taskList) {
                    if (newId == task.getId()) {
                        System.out.println("ID já cadastrado.");
                        idExists = true;
                        break;
                    }
                }
                if (!idExists) {
                    t.setId(newId);
                }
            }
            case 2 -> {
                System.out.println("Digite o novo título: ");
                String newTitle = scanner.nextLine();
                boolean titleExists = false;
                for (Task task : taskList) {
                    if (newTitle.equalsIgnoreCase(task.getTitle())) {
                        System.out.println("Título já cadastrado.");
                        titleExists = true;
                        break;
                    }
                }
                if (!titleExists) {
                    t.setTitle(newTitle);
                }
            }
            case 3 -> {
                System.out.println("Digite a nova descrição: ");
                String newDescription = scanner.nextLine();
                t.setDescription(newDescription);
            }
            case 4 -> {
                System.out.println("Digite a nova data limite para entrega: ");
                String newTextDate = scanner.nextLine();
                LocalDate newDeadline = LocalDate.parse(newTextDate);
                t.setDeadline(newDeadline);
            }
            case 5 -> {
                System.out.println("Digite se a tarefa está concluída ou não");
                System.out.println("1 - Concluída");
                System.out.println("2 - Não concluída");
                int newDoneStatus = scanner.nextInt();
                scanner.nextLine();

                while (newDoneStatus < 1 || newDoneStatus > 2) {
                    System.out.println("Entrada inválida. Digite apenas 1 (concluída) ou 2 (não concluída).");
                    newDoneStatus = scanner.nextInt();
                    scanner.nextLine();
                }
                t.setDone(newDoneStatus == 1);
            }
            case 6 -> {
                System.out.println("Selecione o novo nível de prioridade: ");
                Priority newPriority = getPriority(scanner);
                t.setPriority(newPriority);
            }
        }
        System.out.println("Tarefa atualizada: ");
        System.out.println(t);
        TaskRepository.saveAll(taskList);
    }
}



