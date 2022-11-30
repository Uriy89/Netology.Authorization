package ru.netology.authorizationservice.repopsitory;

import ru.netology.authorizationservice.model.Authorities;

import java.util.List;

public interface UserRepository {

    List<Authorities> getUserAuthorities(String user, String password);
}
