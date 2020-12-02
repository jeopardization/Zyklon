package respectful.rapist.client;

public class Players {
    public String[] friends = {};
    public String[] enemies = {};

    public boolean isFriend(String name) {
        if (friends != null && friends.length > 0) {
            for (String friend : friends) {
                if (friend.equalsIgnoreCase(name)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isEnemy(String name) {
        if (enemies != null && enemies.length > 0) {
            for (String enemy : enemies) {
                if (enemy.equalsIgnoreCase(name)) {
                    return true;
                }
            }
        }
        return false;
    }
}
