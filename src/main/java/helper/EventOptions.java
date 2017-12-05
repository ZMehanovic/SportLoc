package helper;

public enum EventOptions {
    CREATE("create"),
    DELETE("delete"),
    UPDATE("update");

    private String option;

    EventOptions(String option) {
        this.option = option;
    }

    public String getOption() {
        return option;
    }
    
    public static EventOptions fromString(String option) {
    	return EventOptions.valueOf(option);
    }
}
