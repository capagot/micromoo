package user_manager;

public final class User {
    public final String getID() {
        return user_id;
    }

    public final String getName() {
        return user_name;
    }

    public final String getPassword() {
        return user_password;
    }

    public final String getType() {
        return user_type;
    }

    private String user_id;
    private String user_name;
    private String user_password;
    private String user_type;
}
