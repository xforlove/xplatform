package net.rockey.doc.manager;

import net.rockey.core.hibernate.HibernateEntityDao;
import net.rockey.doc.model.DocInfo;

import org.springframework.stereotype.Service;

@Service
public class DocInfoManager extends HibernateEntityDao<DocInfo> {

}
