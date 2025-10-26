package com.xxxx.ddd.infrastucture.persistence.repository;

import com.xxxx.ddd.domain.repository.HiDomainRepository;
import org.springframework.stereotype.Service;

@Service
public class HiInfrasRepositoryImpl implements HiDomainRepository {
    @Override
    public String sayHi(String who) {
        return who;
    }
}
