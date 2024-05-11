package com.ggkps.superflix.interfaces;

import com.ggkps.superflix.entities.User;
import com.ggkps.superflix.interfaces.Professional;

public interface Administrator extends Professional {

    User addAdministrator();

    boolean removeAdministrator();

    User updateAdministrator();

    User addProfessional();

    boolean removeProfessional();

    User updateProfessional();

    User addSubscriber();

    boolean removeSubscriber();

    User updateSubscriber();

    boolean removeBasicUser();

    User updateBasicUser();

    void addComment();

    void removeComment();

    void updateComment();

}
