package com.radams.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;


/**
 * A generic dao
 */
public class GenericDao<T> {

    private Class<T> type;
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Instantiates a generic dao
     * @param type the entity type
     */
    public GenericDao(Class<T> type) {
        this.type = type;
    }

}
