package facebook;

/**
 * Created by user on 6/10/2015.
 */
public class FriendInfo {
    private String id;
    private String name;
    private int age;
    private String avatarUrl;

    public FriendInfo(String id, String name, int age, String avatarUrl) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.avatarUrl = avatarUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
