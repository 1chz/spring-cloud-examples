package io.github.shirohoo.openfeign.config;

import feign.FeignException;
import feign.Request.HttpMethod;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


// FeignClient 가 API 호출 중 발생하는 에러를 처리할 커스텀 클래스. Bean 으로 등록되어 있어야 동작한다
@Slf4j
@Component
public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        String requestPayload = new String(response.request().body(), StandardCharsets.UTF_8);
        int responseStatus = response.status();

        log.info("request payload={}, response status={}, response payload={}", requestPayload, responseStatus, response.body());

        switch (responseStatus) {
            case 400:
                return new BadRequestException();
            case 401:
                return new UnauthorizedException();
            case 403:
                return new ForbiddenException();
            case 404:
                return new NotFoundException();
            case 405:
                return new MethodNotAllowedException();
            case 406:
                return new NotAcceptableException();
            case 409:
                return new ConflictException();
            case 410:
                return new GoneException();
            case 415:
                return new UnsupportedMediaTypeException();
            case 429:
                return new TooManyRequestsException();
            case 422:
                return new UnprocessableEntityException();
            case 500:
                // RetryableException을 던지면 Retry 설정이 동작한다.
                return new RetryableException(responseStatus, "to retry", HttpMethod.GET, null, response.request());
            default:
                return FeignException.errorStatus(methodKey, response);
        }
    }

    private class BadRequestException extends Exception {}

    private class UnauthorizedException extends Exception {}

    private class ForbiddenException extends Exception {}

    private class NotFoundException extends Exception {}

    private class MethodNotAllowedException extends Exception {}

    private class NotAcceptableException extends Exception {}

    private class ConflictException extends Exception {}

    private class GoneException extends Exception {}

    private class UnsupportedMediaTypeException extends Exception {}

    private class TooManyRequestsException extends Exception {}

    private class UnprocessableEntityException extends Exception {}
}
