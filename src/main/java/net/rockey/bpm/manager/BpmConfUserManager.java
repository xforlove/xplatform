package net.rockey.bpm.manager;

import org.springframework.stereotype.Service;

import net.rockey.bpm.model.BpmConfUser;
import net.rockey.core.hibernate.HibernateEntityDao;

@Service
public class BpmConfUserManager extends HibernateEntityDao<BpmConfUser> {

}
