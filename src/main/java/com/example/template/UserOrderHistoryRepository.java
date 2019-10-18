package com.example.template;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserOrderHistoryRepository extends CrudRepository<UserOrderHistory, Long> {

}
