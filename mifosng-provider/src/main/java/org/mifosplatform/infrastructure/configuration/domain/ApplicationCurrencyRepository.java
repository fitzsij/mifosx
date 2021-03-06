package org.mifosplatform.infrastructure.configuration.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ApplicationCurrencyRepository extends JpaRepository<ApplicationCurrency, Long>, JpaSpecificationExecutor<ApplicationCurrency> {

	ApplicationCurrency findOneByCode(String currencyCode);
}