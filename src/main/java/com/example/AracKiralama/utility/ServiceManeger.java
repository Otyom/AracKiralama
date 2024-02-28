package com.example.AracKiralama.utility;

import com.example.AracKiralama.entity.BaseEntity;
import com.example.AracKiralama.entity.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ServiceManeger<T extends BaseEntity,ID>implements IServiceManeger<T,ID>{

    private final JpaRepository<T,ID> jpaRepository;

    public ServiceManeger(JpaRepository<T, ID> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(T t) {
        t.setCreateDate(LocalDateTime.now().toString());
        t.setUpdateDate(LocalDateTime.now().toString());
        t.setStatus(Status.ACTIVE);
        jpaRepository.save(t);
    }

    @Override
    public void deleteById(ID id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public void delete(T t) {
        t.setStatus(Status.DELETED);
        update(t);
    }

    @Override
    public void update(T t) {
        t.setUpdateDate(LocalDateTime.now().toString());
        jpaRepository.save(t);
    }

    @Override
    public Optional<T> findById(ID id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<T> getAll() {
        return jpaRepository.findAll();
    }
}
