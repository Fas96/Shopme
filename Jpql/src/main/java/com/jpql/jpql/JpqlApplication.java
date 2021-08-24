package com.jpql.jpql;

import entities.Member;
import entities.Team;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.*;
import java.util.List;

@SpringBootApplication
public class JpqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpqlApplication.class, args);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        //all
        EntityTransaction tx = em.getTransaction();
        tx.begin();


        try{
            Team team= Team.builder()
                    .name("chelsea")
                    .build();
            em.persist(team);
            Member m=Member.builder()
                    .username("Fas")
                    .age(25)
                    .team(team)
                    .build();


            em.persist(m);

            TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);

            List<Member> list = query.getResultList();
            System.out.println("====================");
            System.out.println(list);
            System.out.println("====================");

            tx.commit();

        }catch (Exception e){
            tx.rollback();
            System.out.println(e.getCause());
        }finally {
            em.close();
            emf.close();
        }

    }

}
