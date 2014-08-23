package net.rockey.auth.manager;

import org.springframework.stereotype.Service;

import net.rockey.auth.model.AuthUser;
import net.rockey.core.hibernate.HibernateEntityDao;

@Service
public class UserManager extends HibernateEntityDao<AuthUser> {

}

