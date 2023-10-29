package pl.filipswiszcz.orion.memory;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import pl.filipswiszcz.orion.entity.User;

public final class Repository {

    private final Collection<User> users = ConcurrentHashMap.newKeySet(); // logged users
    
}
