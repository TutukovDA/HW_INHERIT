import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodosTest {
    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void matchesTodosSimpleTaskTest() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        Assertions.assertTrue(simpleTask.matches("Позвони"));
    }

    @Test
    public void shouldNotMatchesTodosSimpleTaskTest() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        Assertions.assertFalse(simpleTask.matches("Перезвонить родителям"));
    }


    @Test
    public void strictMatchesTodosSimpleTaskTest() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        Assertions.assertTrue(simpleTask.matches("Позвонить родителям"));
    }

    @Test
    public void matchesTodosMeetingTopicTest() {
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Assertions.assertTrue(meeting.matches("Выкатка 3й версии приложения"));
    }

    @Test
    public void matchesTodosMeetingProjectTest() {
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Assertions.assertTrue(meeting.matches("Приложение НетоБанка"));
    }

    @Test
    public void shouldNotMatchesTodosMeetingTest() {
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Assertions.assertFalse(meeting.matches("Проверка False-branch"));
    }

    @Test
    public void strictMatchesTodosMeetingProjectTest() {
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Assertions.assertTrue(meeting.strictMatches("Приложение НетоБанка"));
    }

    @Test
    public void shouldNotMatchesTodosEpicTest() {
        String[] subtasks = {"Чай", "Кофе", "Потанцуем"};

        Epic epic = new Epic(55, subtasks);

        Assertions.assertFalse(epic.matches("Яйца"));
    }

    @Test
    public void shouldNotStrictMatchesTodosEpicTest() {
        String[] subtasks = {"Чай", "Кофе", "Потанцуем"};

        Epic epic = new Epic(55, subtasks);

        Assertions.assertFalse(epic.strictMatches("Яйца"));
    }

    @Test
    public void matchesTodosEpicFirstVarTest() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};

        Epic epic = new Epic(55, subtasks);

        Assertions.assertTrue(epic.matches("Молоко"));
    }

    @Test
    public void matchesTodosEpicSecondVarTest() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};

        Epic epic = new Epic(55, subtasks);

        Assertions.assertTrue(epic.matches("Яйца"));
    }

    @Test
    public void matchesTodosEpicTreeVarTest() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};

        Epic epic = new Epic(55, subtasks);

        Assertions.assertTrue(epic.matches("Хлеб"));
    }

    @Test
    public void shouldNotSearchTasksOfDifferentQuery() {
        SimpleTask simpleTask = new SimpleTask(5, "молоко");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(43,
                "Молоко",
                "Молокопроизводство",
                "20.04.2024");

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] actual = todos.search("Молоко");
        Task[] expected = {epic, meeting};

        Assertions.assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldSearchThreeTasksOfQuery() {
        SimpleTask simpleTask = new SimpleTask(5, "Молоко и сметана");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(43,
                "Молоко",
                "Молокопроизводство",
                "20.04.2024");

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] actual = todos.search("Молоко");
        Task[] expected = {simpleTask, epic, meeting};

        Assertions.assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldNotStrictSearchTasksOfDifferentQuery() {
        SimpleTask simpleTask = new SimpleTask(5, "молоко");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(43,
                "Молоко",
                "Молокопроизводство",
                "20.04.2024");

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] actual = todos.strictSearch("молоко");
        Task[] expected = {simpleTask};

        Assertions.assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldStrictSearchTwoTasksOfQuery() {
        SimpleTask simpleTask = new SimpleTask(5, "Молоко и сметана");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(43,
                "Молоко",
                "Молокопроизводство",
                "20.04.2024");

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] actual = todos.strictSearch("Молоко");
        Task[] expected = {epic, meeting};

        Assertions.assertArrayEquals(actual, expected);
    }

    @Test
    public void searchMultipleMatches() {
        SimpleTask simpleTask = new SimpleTask(1, "Купить книгу");

        String[] subtask = {"Купить книгу", "Скачать фильм", "Пробежка"};
        Epic epic = new Epic(4, subtask);

        Meeting meeting = new Meeting(
                2,
                "Купить книгу",
                "Курсы повышения квалификации",
                "21/07/2024"
        );

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.search("Купить книгу");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchOneEpicMatches() {
        SimpleTask simpleTask = new SimpleTask(1, "Купить книгу");

        String[] subtask = {"Купить книгу", "Скачать фильм", "Пробежка"};
        Epic epic = new Epic(4, subtask);

        Meeting meeting = new Meeting(
                2,
                "Купить книгу",
                "Курсы повышения квалификации",
                "21/07/2024"
        );

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {epic};
        Task[] actual = todos.search("Скачать фильм");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchOneMeetingMatches() {
        SimpleTask simpleTask = new SimpleTask(1, "Купить книгу");

        String[] subtask = {"Купить книгу", "Скачать фильм", "Пробежка"};
        Epic epic = new Epic(4, subtask);

        Meeting meeting = new Meeting(
                2,
                "Купить книгу",
                "Курсы повышения квалификации",
                "21/07/2024"
        );

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {meeting};
        Task[] actual = todos.search("Курсы повышения квалификации");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchNoMatches() {
        SimpleTask simpleTask = new SimpleTask(1, "Купить книгу");

        String[] subtask = {"Купить книгу", "Скачать фильм", "Пробежка"};
        Epic epic = new Epic(4, subtask);

        Meeting meeting = new Meeting(
                2,
                "Купить книгу",
                "Курсы повышения квалификации",
                "21/07/2024"
        );

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {};
        Task[] actual = todos.search("Отпуск");
        Assertions.assertArrayEquals(expected, actual);
    }
}
