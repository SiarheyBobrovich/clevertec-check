package ru.clevertec.check.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.check.entity.Good;

public interface GoodRepository extends JpaRepository<Good, Long> {
}
