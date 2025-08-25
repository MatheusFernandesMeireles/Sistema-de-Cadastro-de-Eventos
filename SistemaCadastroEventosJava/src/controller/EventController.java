package controller;

import model.Event;
import model.EventCategory;
import model.User;
import utils.FileManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class EventController {
    private final Scanner scanner = new Scanner(System.in);
    private final User user;
    private final List<Event> events;

    public EventController(User user) {
        this.user = user;
        this.events = FileManager.loadEvents();
    }

    public void start() {
        int option;
        do {
            showMenu();
            option = scanner.nextInt();
            scanner.nextLine(); // limpar buffer
            switch (option) {
                case 1 -> cadastrarEvento();
                case 2 -> listarEventos();
                case 3 -> confirmarParticipacao();
                case 4 -> listarEventosConfirmados();
                case 5 -> cancelarParticipacao();
                case 6 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        } while (option != 6);

        FileManager.saveEvents(events);
    }

    private void showMenu() {
        System.out.println("\n---- Menu Principal ----");
        System.out.println("1. Cadastrar evento");
        System.out.println("2. Listar eventos disponíveis");
        System.out.println("3. Confirmar participação em evento");
        System.out.println("4. Ver eventos confirmados");
        System.out.println("5. Cancelar participação");
        System.out.println("6. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void cadastrarEvento() {
        System.out.println("\n-- Cadastro de Evento --");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        System.out.println("Categorias:");
        for (EventCategory cat : EventCategory.values()) {
            System.out.println("- " + cat);
        }

        System.out.print("Categoria: ");
        EventCategory categoria = EventCategory.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Data e hora (formato: yyyy-MM-dd HH:mm): ");
        String dataHoraStr = scanner.nextLine();
        LocalDateTime dataHora = LocalDateTime.parse(dataHoraStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        Event evento = new Event(nome, endereco, categoria, dataHora, descricao);
        events.add(evento);
        System.out.println("Evento cadastrado com sucesso!");
    }

    private void listarEventos() {
        if (events.isEmpty()) {
            System.out.println("Nenhum evento cadastrado.");
            return;
        }

        System.out.println("\n-- Eventos Disponíveis --");

        List<Event> futuros = new ArrayList<>();
        List<Event> ocorrendo = new ArrayList<>();
        List<Event> passados = new ArrayList<>();

        for (Event e : events) {
            if (e.hasOccurred()) {
                passados.add(e);
            } else if (e.isOngoing()) {
                ocorrendo.add(e);
            } else {
                futuros.add(e);
            }
        }

        System.out.println("\n[Eventos em andamento]");
        imprimirEventos(ocorrendo);

        System.out.println("\n[Próximos eventos]");
        imprimirEventos(futuros);

        System.out.println("\n[Eventos passados]");
        imprimirEventos(passados);
    }

    private void imprimirEventos(List<Event> lista) {
        lista.sort(Comparator.comparing(Event::getDateTime));
        int index = 1;
        for (Event e : lista) {
            System.out.printf("\n%d. %s (%s)\nLocal: %s\nData: %s\nDescrição: %s\n",
                    index++,
                    e.getName(),
                    e.getCategory(),
                    e.getAddress(),
                    e.getDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                    e.getDescription());
        }

        if (lista.isEmpty()) {
            System.out.println("Nenhum.");
        }
    }

    private void confirmarParticipacao() {
        if (events.isEmpty()) {
            System.out.println("Nenhum evento disponível para participar.");
            return;
        }

        listarEventos();
        System.out.print("Digite o número do evento para confirmar participação: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // limpar buffer

        if (index < 1 || index > events.size()) {
            System.out.println("Opção inválida.");
            return;
        }

        Event evento = events.get(index - 1);
        user.joinEvent(evento);
        System.out.println("Participação confirmada!");
    }

    private void listarEventosConfirmados() {
        List<Event> eventos = user.getEventsJoined();
        if (eventos.isEmpty()) {
            System.out.println("Você não confirmou participação em nenhum evento.");
            return;
        }

        System.out.println("\n-- Eventos Confirmados --");
        int index = 1;
        for (Event e : eventos) {
            System.out.printf("%d. %s (%s) - %s\n", index++, e.getName(), e.getCategory(),
                    e.getDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        }
    }

    private void cancelarParticipacao() {
        List<Event> eventos = user.getEventsJoined();
        if (eventos.isEmpty()) {
            System.out.println("Você não está participando de nenhum evento.");
            return;
        }

        listarEventosConfirmados();
        System.out.print("Digite o número do evento para cancelar: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // limpar buffer

        if (index < 1 || index > eventos.size()) {
            System.out.println("Opção inválida.");
            return;
        }

        Event evento = eventos.get(index - 1);
        user.cancelParticipation(evento);
        System.out.println("Participação cancelada com sucesso!");
    }
}
