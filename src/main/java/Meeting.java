public class Meeting extends Task {
    protected String topic;
    protected String project;
    protected String start;

    public String getTopic() {
        return topic;
    }

    public String getProject() {
        return project;
    }

    public String getStart() {
        return start;
    }

    public Meeting(int id, String topic, String project, String start) {
        super(id);
        this.topic = topic;
        this.project = project;
        this.start = start;
    }
    @Override
    public boolean matches(String query) {
        if (topic.contains(query)) {
            return true;
        }
        if (project.contains(query)) {
            return true;
        }
        return false;
    }@Override
    public boolean strictMatches(String query) {
        if (topic.equals(query)) {
            return true;
        }
        if (project.equals(query)) {
            return true;
        }
        return false;
    }
}
