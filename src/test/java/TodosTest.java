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
    public void shouldNotmatchesTodosSimpleTaskTest() {
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
    public void shouldNotMatchesTodosEpicTest() {
        String[] subtasks = {"Чай", "Кофе", "Потанцуем"};

        Epic epic = new Epic(55, subtasks);

        Assertions.assertFalse(epic.matches("Яйца"));
    }    @Test
    public void shouldNotStrictMatchesTodosEpicTest() {
        String[] subtasks = {"Чай", "Кофе", "Потанцуем"};

        Epic epic = new Epic(55, subtasks);

        Assertions.assertFalse(epic.strictMatches("Яйца"));
    }
    @Test
    public void matchesTodosMeetingEpicFirstVarTest() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};

        Epic epic = new Epic(55, subtasks);

        Assertions.assertTrue(epic.matches("Молоко"));

    }

    @Test
    public void matchesTodosMeetingEpicSecondVarTest() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};

        Epic epic = new Epic(55, subtasks);

        Assertions.assertTrue(epic.matches("Яйца"));
    }

    @Test
    public void matchesTodosMeetingEpicTreeVarTest() {
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
}
