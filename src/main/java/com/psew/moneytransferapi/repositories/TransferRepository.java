package com.psew.moneytransferapi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.psew.moneytransferapi.domains.Account;
import com.psew.moneytransferapi.domains.Transfer;

/**
 * A database repository that extends Create, Read, Update and Delete Repository.
 * It comes with built-in methods for reading from and storing to a database table.
 *
 * @author Biniam Asnake
 */
@Repository
public interface TransferRepository extends CrudRepository<Transfer, Long> {

}
