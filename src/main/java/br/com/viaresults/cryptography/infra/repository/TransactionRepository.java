package br.com.viaresults.cryptography.infra.repository;

import br.com.viaresults.cryptography.infra.entity.Transaction;
import io.micrometer.observation.annotation.Observed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Observed
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
