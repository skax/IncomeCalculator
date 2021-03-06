package kmiecik.michal.earningscalculator.infrastructure.rest;

import io.vavr.Function1;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import io.vavr.control.Either;
import kmiecik.michal.earningscalculator.domain.errorhandling.AppError;
import kmiecik.michal.earningscalculator.domain.errorhandling.ErrorReason;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class ResponseResolver {

    private final static Map<ErrorReason, HttpStatus> HTTP_STATUS_MAP =
            HashMap.of(
                    ErrorReason.COUNTRY_NOT_SUPPORTED, HttpStatus.BAD_REQUEST,
                    ErrorReason.COUNTRY_CANNOT_BE_NULL, HttpStatus.BAD_REQUEST,
                    ErrorReason.FORM_CANNOT_BE_NULL, HttpStatus.BAD_REQUEST,
                    ErrorReason.INCOME_CANNOT_BE_NULL, HttpStatus.BAD_REQUEST,
                    ErrorReason.INVALID_INCOME_VALUE, HttpStatus.BAD_REQUEST,
                    ErrorReason.INVALID_INCOME_FORMAT, HttpStatus.BAD_REQUEST
            );

    <T, R> ResponseEntity<?> resolve(final Either<AppError, T> either, final Function1<T, R> mapper) {
        return either
                .map(mapper)
                .map(this::createObject)
                .getOrElseGet(this::createError);
    }

    private ResponseEntity<Object> createObject(final Object object) {
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    private ResponseEntity<Object> createError(final AppError error) {
        return new ResponseEntity<>(error, HTTP_STATUS_MAP.getOrElse(error.getErrorReason(), HttpStatus.BAD_REQUEST));
    }

}