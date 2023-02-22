package com.MTN.rest.Repository;

import com.MTN.rest.models.HRUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HRUserRepository extends JpaRepository<HRUser, Long> {

    HRUser findByUsername(String username);

}
