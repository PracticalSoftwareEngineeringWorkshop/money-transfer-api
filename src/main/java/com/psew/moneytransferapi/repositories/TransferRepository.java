package com.psew.moneytransferapi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.psew.moneytransferapi.domains.Account;
import com.psew.moneytransferapi.domains.Transfer;

@Repository
public interface TransferRepository extends CrudRepository<Transfer, Long> {

}
