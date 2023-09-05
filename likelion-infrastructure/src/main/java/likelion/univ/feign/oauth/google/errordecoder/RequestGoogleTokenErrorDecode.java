package likelion.univ.feign.oauth.google.errordecoder;

import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;

public class RequestGoogleTokenErrorDecode implements ErrorDecoder{

    @Override
    public Exception decode(String methodKey, Response response) {

        switch (response.status()){
//            case 400:
//                return new BadRequestException(HttpStatus.valueOf(response.status()));
//            case 404:
//                return new NotFoundException(HttpStatus.valueOf(response.status()));
//            case 500:
//                return new InternalServerException(HttpStatus.valueOf(response.status()));
//            case 501:
            case 502:
            case 503:
                return new RetryableException(response.status(),response.reason(), response.request().httpMethod(), null,response.request());
        }
        return new ErrorDecoder.Default().decode(methodKey, response);
    }
}