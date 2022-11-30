package ru.netology.authorizationservice.repopsitory;

import ru.netology.authorizationservice.model.Authorities;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final String TOM = "Tom";
    private final String TOM_PASSWORD = "123456789";
    private final String BOB = "Bob";
    private final String BOB_PASSWORD = "qazwsxedcrfv";

    @Override
    public List<Authorities> getUserAuthorities(String user, String password) {
        List<Authorities> authorities = new ArrayList<>();
        if (user.equals(TOM) && password.equals(TOM_PASSWORD)) {
            authorities.add(Authorities.WRITE);
            authorities.add(Authorities.READ);
        } else if (user.equals(BOB) && password.equals(BOB_PASSWORD)) {
            authorities.add(Authorities.DELETE);
        }
        return authorities;
    }

}