package com.psew.moneytransferapi.repositories;

import com.psew.moneytransferapi.domains.Account;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

/**
 * A database repository that extends Create, Read, Update and Delete Repository.
 * It comes with built-in methods for reading from and storing to a database table.
 *
 * @author Biniam Asnake
 */
@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    // This is how you create a custom query to perform database operation
    @Modifying
    @Transactional
    @Query(value = "UPDATE Account " +
            "SET firstName = :firstName, " +
            "    lastName = :lastName, " +
            "    email = :email, " +
            "    phoneNumber = :phoneNumber, " +
            "    pin = :pin " +
            "WHERE id = :accountId")
    void updateAccount(@Param("accountId") Long accountId, @Param("firstName") String firstName, @Param("lastName") String lastName,
                       @Param("email") String email, @Param("phoneNumber") String phoneNumber, @Param("pin") Integer pin) throws SQLException;;
}
