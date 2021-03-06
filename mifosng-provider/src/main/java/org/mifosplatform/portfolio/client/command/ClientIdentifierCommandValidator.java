package org.mifosplatform.portfolio.client.command;

import java.util.ArrayList;
import java.util.List;

import org.mifosplatform.infrastructure.core.data.ApiParameterError;
import org.mifosplatform.infrastructure.core.data.DataValidatorBuilder;
import org.mifosplatform.infrastructure.core.exception.PlatformApiDataValidationException;

public class ClientIdentifierCommandValidator {

    private final ClientIdentifierCommand command;

    public ClientIdentifierCommandValidator(ClientIdentifierCommand command) {
        this.command = command;
    }

    public void validateForUpdate() {
        List<ApiParameterError> dataValidationErrors = new ArrayList<ApiParameterError>();

        DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors).resource("clientIdentifier");

        baseDataValidator.reset().parameter("documentKey").value(command.getDocumentKey()).ignoreIfNull().notBlank();

        if (command.isDocumentTypeChanged()) {
            baseDataValidator.reset().parameter("documentTypeId").value(command.getDocumentTypeId()).notNull().integerGreaterThanZero();
        }

        baseDataValidator.reset().anyOfNotNull(command.getDocumentTypeId(), command.getDocumentKey());

        if (!dataValidationErrors.isEmpty()) { throw new PlatformApiDataValidationException("validation.msg.validation.errors.exist",
                "Validation errors exist.", dataValidationErrors); }
    }

    public void validateForCreate() {
        List<ApiParameterError> dataValidationErrors = new ArrayList<ApiParameterError>();

        DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors).resource("clientIdentifier");

        baseDataValidator.reset().parameter("documentTypeId").value(command.getDocumentTypeId()).notNull().integerGreaterThanZero();
        baseDataValidator.reset().parameter("clientId").value(command.getClientId()).notNull().integerGreaterThanZero();
        baseDataValidator.reset().parameter("documentKey").value(command.getDocumentKey()).notBlank();

        if (!dataValidationErrors.isEmpty()) { throw new PlatformApiDataValidationException("validation.msg.validation.errors.exist",
                "Validation errors exist.", dataValidationErrors); }
    }
}
