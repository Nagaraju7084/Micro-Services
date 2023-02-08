package com.broadcastreferral.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.broadcastreferral.entity.BroadcastReferral;

public interface BroadcastReferralRepository extends MongoRepository<BroadcastReferral, Long> {

}
