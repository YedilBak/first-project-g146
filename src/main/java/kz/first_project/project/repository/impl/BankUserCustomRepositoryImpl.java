package kz.first_project.project.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import kz.first_project.project.model.BankUser;
import kz.first_project.project.repository.BankUserCustomRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BankUserCustomRepositoryImpl implements BankUserCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BankUser> findBankUsersByRatingGreaterThan(double rating) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BankUser> query = cb.createQuery(BankUser.class);
        Root<BankUser> root = query.from(BankUser.class);

        Predicate predicate = cb.greaterThan(root.get("rating"), rating);

        query.select(root).where(predicate);

        return entityManager.createQuery(query).getResultList();
    }


    @Override
    public List<BankUser> findBankUsersByRatingAndFullName(Double rating, String fullName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BankUser> query = cb.createQuery(BankUser.class);
        Root<BankUser> root = query.from(BankUser.class);

        List<Predicate> predicates = new ArrayList<>();

        if(rating!=null){
            predicates.add(cb.greaterThanOrEqualTo(root.get("rating"), rating));
            query.select(root).where(predicates.toArray(new Predicate[0]));
        }

        if(fullName!=null && !fullName.isEmpty()){
            predicates.add(cb.like(root.get("fullName"), fullName));
            query.select(root).where(predicates.toArray(new Predicate[0]));

        }

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<BankUser> findBankUsersByRatingSort() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BankUser> query = cb.createQuery(BankUser.class);
        Root<BankUser> root = query.from(BankUser.class);

        query.select(root).orderBy(cb.desc(root.get("rating")));

        return entityManager.createQuery(query).getResultList();
    }
}
