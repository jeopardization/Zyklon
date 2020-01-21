package respectful.rapist.client;

public class PlayerManager {
    public String[] friends = {};
    public String[] enemies = {};

    public boolean isFriend(String name) {
        if (friends != null && friends.length > 0) {
            for (String friend : friends) {
                return friend.equalsIgnoreCase(name);
            }
        }
        return false;
    }

    public boolean isEnemy(String name) {
        if (enemies != null && enemies.length > 0) {
            for (String enemy : enemies) {
                return enemy.equalsIgnoreCase(name);
            }
        }
        return false;
    }
}
