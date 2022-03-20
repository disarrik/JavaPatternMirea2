package com.example.javapatternmirea2.service;

import com.example.javapatternmirea2.entity.Game;
import com.example.javapatternmirea2.entity.Level;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

@Service
public class GameServiceImpl implements GameService{
    private SessionFactory sessionFactory;
    private LevelService levelService;


    @Autowired
    public GameServiceImpl(SessionFactory sessionFactory, LevelService levelService) {
        this.sessionFactory = sessionFactory;
        this.levelService = levelService;
    }


    @PostConstruct
    void init() {
        Game game1 = new Game("1 game", new Date());
        Game game2 = new Game("2 game", new Date());
        saveOrUpdate(game1);
        saveOrUpdate(game2);
        List<Level> levels = List.of(
                new Level(12, "1 level"),
                new Level(15, "2 level")
        );
        levels.forEach(
                l -> {
                    l.setGame(game1);
                    levelService.saveOrUpdate(l);
                }
        );
        game1.setLevels(levels);
    }

    @Override
    public void saveOrUpdate(Game game) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(game);
        if (game.getLevels() != null) {
            game.getLevels().forEach(
                    l -> {
                        levelService.saveOrUpdate(l);
                    }
            );
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Game game) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session
                .createQuery("delete from Game where id = :id")
                .setParameter("id", game.getId())
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Game> getAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Game> result =  session
                .createQuery("select g from Game g", Game.class)
                .getResultList();
        session.getTransaction().commit();
        System.out.println(result.get(0).getLevels().getClass());
        //session.close(); //нужно оставить открытой для того, чтобы потом в этой же сессии произошел lazy loading полей
        return result;
    }

    @Override
    public Game getById(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Game result =  session
                .createQuery("from Game g where g.id = :id", Game.class)
                .setParameter("id", id)
                .getSingleResult();
        session.getTransaction().commit();
        session.close();
        return result;
    }
}
