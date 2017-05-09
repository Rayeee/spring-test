package redis;

import redis.clients.jedis.JedisPubSub;

/**
 * Created by zhugongyi on 2017/5/9.
 */
public class Subscriber extends JedisPubSub {

    public Subscriber() {

    }

    @Override
    public void onMessage(String channel, String message) {
        System.out.println(message);
    }

    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
        System.out.println(pattern + "\t" + subscribedChannels);
    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        System.out.println(channel + "\t" + subscribedChannels);
    }
}
