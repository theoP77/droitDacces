package com.xtensus.qualifiers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.xtensus.hibernate.model.Entite;

/**
 *
 * @author
 */
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Repository
@Qualifier
public @interface Dao {
	Class<? extends Entite> type();
}