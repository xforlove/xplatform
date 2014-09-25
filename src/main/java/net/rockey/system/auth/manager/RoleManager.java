package net.rockey.system.auth.manager;

import org.springframework.stereotype.Service;

import net.rockey.core.hibernate.HibernateEntityDao;
import net.rockey.system.auth.model.AuthRole;

@Service
public class RoleManager extends HibernateEntityDao<AuthRole> {

}
