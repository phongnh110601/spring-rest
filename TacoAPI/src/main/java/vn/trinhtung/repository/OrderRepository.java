package vn.trinhtung.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.trinhtung.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
