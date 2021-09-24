package pers.xuliushen.expressstation_cs.pojo;

public class User {
    private boolean isAdmin;//1表示管理员 0表示普通用户
    private String username;//用户名
    private String password;//密码

    public User(boolean isAdmin, String username, String password) {
        this.isAdmin = isAdmin;
        this.username = username;
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
