package app;

import services.Manager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Manager manager = new Manager();

        boolean running = true;

        while(running) {
            System.out.println("\n--- Lista de Tarefas ---");
            System.out.println("1 - Criar nova tarefa");
            System.out.println("2 - Carregar tarefas salvas");
            System.out.println("3 - Lista todas as tarefas");
            System.out.println("4 - Buscar tarefa");
            System.out.println("5 - Editar tarefa");
            System.out.println("6 - Remover tarefa da lista");
            System.out.println("0 - Sair");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch(option) {
                case 1 -> manager.createTask(scanner);
                case 2 -> manager.loadTaskList();
                case 3 -> manager.listTasks();
                case 4 -> manager.searchTask(scanner);
                case 5 -> manager.editTask(scanner);
                case 6 -> manager.removeTask(scanner);
                case 0 -> {
                    System.out.println("Encerrando o programa.");
                    running = false;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
        scanner.close();
    }
}
