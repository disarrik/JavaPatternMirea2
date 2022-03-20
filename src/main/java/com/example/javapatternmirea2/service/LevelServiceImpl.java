package com.example.javapatternmirea2.service;

import com.example.javapatternmirea2.entity.Level;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelServiceImpl implements LevelService{
    private SessionFactory sessionFactory;

    @Autowired
    public LevelServiceImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveOrUpdate(Level level) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(level);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Level level) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session
                .createQuery("delete from Level  where :id = id")
                .setParameter("id", level.getId())
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Level> getAll() {
        Session session = sessionFactory.openSession();
        List<Level> levels = session
                .createQuery("select l from Level l", Level.class)
                .getResultList();
        session.close();
        return levels;
    }

}
