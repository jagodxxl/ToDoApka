package com.home;

import com.home.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-01-17T20:43:31")
@StaticMetamodel(Todos.class)
public class Todos_ { 

    public static volatile SingularAttribute<Todos, Date> targetDate;
    public static volatile SingularAttribute<Todos, String> description;
    public static volatile SingularAttribute<Todos, Long> id;
    public static volatile SingularAttribute<Todos, Boolean> isDone;
    public static volatile SingularAttribute<Todos, User> user;

}