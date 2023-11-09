package com.kmhoon.common.repository.refrigerator;

import com.kmhoon.common.model.entity.refrigerator.RefrigeratorItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefrigeratorItemRepository extends JpaRepository<RefrigeratorItem, Long> {
}
