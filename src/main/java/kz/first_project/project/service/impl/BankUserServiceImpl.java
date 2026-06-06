package kz.first_project.project.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import kz.first_project.project.dto.BankUserDto;
import kz.first_project.project.model.BankUser;
import kz.first_project.project.repository.BankUserRepository;
import kz.first_project.project.service.BankUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BankUserServiceImpl implements BankUserService {

    private final BankUserRepository bankUserRepository;


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

        if (rating != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("rating"), rating));
            query.select(root).where(predicates.toArray(new Predicate[0]));
        }

        if (fullName != null && !fullName.isEmpty()) {
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

    @Override
    public List<BankUser> getAllUsersFromBase() {
        return bankUserRepository.findAll();
    }

    @Override
    public List<BankUser> getAllUsersByWord(String word) {
        return bankUserRepository.searchByWord(word);
    }


    @Override
    public void addUserToBase(BankUser bankUser) {
        bankUserRepository.save(bankUser);
    }

    @Override
    public BankUser getUserById(int id) {
        return bankUserRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteUserById(int id) {
        bankUserRepository.deleteById(id);
    }


    @Override
    public Page<BankUser> findAll(Pageable pageable) {
        return bankUserRepository.findAll(pageable);
    }

    @Override
    public Page<BankUser> findByRatingGreaterThan(double rating, Pageable pageable) {
        return bankUserRepository.findByRatingGreaterThan(rating, pageable);
    }

    @Override
    public BankUserDto toDto(BankUser bankUser) {

        BankUserDto bankUserDto = new BankUserDto();

        bankUserDto.setId(bankUser.getId());
        bankUserDto.setCity(bankUser.getCity());
        bankUserDto.setIin(bankUser.getIin());
        bankUserDto.setRating(bankUser.getRating());
        bankUserDto.setNameSurname(bankUser.getFullName());

        return bankUserDto;
    }

    @Override
    public List<BankUserDto> toDtoList(List<BankUser> bankUsers) {

        List<BankUserDto> bankUserDtos = new ArrayList<>();

        for(BankUser user: bankUsers){
            bankUserDtos.add(toDto(user));
        }

        return bankUserDtos;
    }
}
