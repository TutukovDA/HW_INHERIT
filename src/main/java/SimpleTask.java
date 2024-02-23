public class SimpleTask extends Task {
    protected String title;

    public String getTitle() {
        return title;
    }

    public SimpleTask(int id, String title) {
        super(id);
        this.title = title;
    }

    @Override
    public boolean matches(String query) {
        if (title.contains(query)) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public boolean strictMatches(String query) {
        if (title.equals(query)) {
            return true;
        } else {
            return false;
        }
    }
}
