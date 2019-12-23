package org.micromoo.account;

public final class User {
    public User(final String user_id, final String user_name, final String user_password, final String user_type) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_type = user_type;

        System.out.println(">> " + this.user_id);
        System.out.println(">> " + this.user_name);
        System.out.println(">> " + this.user_password);
        System.out.println(">> " + this.user_type);
    }

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
