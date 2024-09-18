package vn.hoidanit.laptopshop.repository;

import org.springframework.stereotype.Repository;

import vn.hoidanit.laptopshop.domain.OrderDetail;

@Repository
public interface OrderDetailRepository extends org.springframework.data.jpa.repository.JpaRepository<OrderDetail, Long> {

}
