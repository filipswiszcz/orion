package pl.filipswiszcz.orion.entity;

public final class User {

    private final int id;

    private final String name, surname;
    private final String email;

    // location, flags, whishlist, favourites, gender etc.

    private boolean verified = false;
    private boolean logged = false;

    public User(final int id, final String name, final String surname,
        final String email, final boolean verified) {
            this.id = id; // TODO AtomicInteger?
            this.name = name;
            this.surname = surname;
            this.email = email;
        }
    
}
