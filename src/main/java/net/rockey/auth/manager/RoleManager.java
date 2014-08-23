package net.rockey.auth.manager;

import org.springframework.stereotype.Service;

import net.rockey.auth.model.AuthRole;
import net.rockey.core.hibernate.HibernateEntityDao;

@Service
public class RoleManager extends HibernateEntityDao<AuthRole> {

}
