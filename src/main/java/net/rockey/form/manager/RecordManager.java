package net.rockey.form.manager;

import net.rockey.core.hibernate.HibernateEntityDao;
import net.rockey.form.model.Record;

import org.springframework.stereotype.Service;

@Service
public class RecordManager extends HibernateEntityDao<Record> {

}
